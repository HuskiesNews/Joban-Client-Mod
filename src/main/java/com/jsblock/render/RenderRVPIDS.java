package com.jsblock.render;

import minecraftmappings.BlockEntityRendererMapper;
import mtr.MTR;
import mtr.block.BlockPIDSBase;
import mtr.block.IBlock;
import mtr.config.Config;
import mtr.data.*;
import mtr.gui.ClientCache;
import mtr.gui.ClientData;
import mtr.gui.IDrawing;
import mtr.render.MoreRenderLayers;
import mtr.render.RenderTrains;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import java.util.*;

public class RenderRVPIDS<T extends BlockEntity> extends BlockEntityRendererMapper<T> implements IGui {

	private final float scale;
	private final float totalScaledWidth;
	private final float destinationStart;
	private final float destinationMaxWidth;
	private final float platformMaxWidth;
	private final float arrivalMaxWidth;
	private final int maxArrivals;
	private final float maxHeight;
	private final float startX;
	private final float startY;
	private final float startZ;
	private final boolean rotate90;
	private final boolean renderArrivalNumber;
	private final boolean showPlatforms;
	private final int textColor;
	private final int firstTrainColor;
	private List<ClientCache.PlatformRouteDetails> routeData;
	private final int CLOCK_HEIGHT = 10;

	private static final int SWITCH_LANGUAGE_TICKS = 60;
	private static final int CAR_TEXT_COLOR = 0xFF0000;
	private static final int MAX_VIEW_DISTANCE = 16;

	public RenderRVPIDS(BlockEntityRenderDispatcher dispatcher, int maxArrivals, float startX, float startY, float startZ, float maxHeight, int maxWidth, boolean rotate90, boolean renderArrivalNumber, boolean showPlatforms, int textColor, int firstTrainColor) {
		super(dispatcher);
		scale = 230 * maxArrivals / maxHeight;
		totalScaledWidth = scale * maxWidth / 16;
		destinationStart = renderArrivalNumber ? scale * 2 / 16 : 0;
		destinationMaxWidth = totalScaledWidth * 0.6F;
		platformMaxWidth = showPlatforms ? scale * 2 / 16 : 0;
		arrivalMaxWidth = totalScaledWidth - destinationStart - destinationMaxWidth - platformMaxWidth;
		this.maxArrivals = maxArrivals;
		this.maxHeight = maxHeight;
		this.startX = startX;
		this.startY = startY;
		this.startZ = startZ;
		this.rotate90 = rotate90;
		this.renderArrivalNumber = renderArrivalNumber;
		this.showPlatforms = showPlatforms;
		this.textColor = textColor;
		this.firstTrainColor = firstTrainColor;
	}

	@Override
	public void render(T entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		final WorldAccess world = entity.getWorld();
		if (world == null) {
			return;
		}

		final BlockPos pos = entity.getPos();
		final Direction facing = IBlock.getStatePropertySafe(world, pos, HorizontalFacingBlock.FACING);
		if (RenderTrains.shouldNotRender(pos, Math.min(MAX_VIEW_DISTANCE, RenderTrains.maxTrainRenderDistance), rotate90 ? null : facing)) {
			return;
		}
		final Style style = Config.useMTRFont() ? Style.EMPTY.withFont(new Identifier(MTR.MOD_ID, "mtr")) : Style.EMPTY;

		final String[] customMessages = new String[maxArrivals];
		final boolean[] hideArrival = new boolean[maxArrivals];
		for (int i = 0; i < maxArrivals; i++) {
			if (entity instanceof BlockPIDSBase.TileEntityBlockPIDSBase) {
				customMessages[i] = ((BlockPIDSBase.TileEntityBlockPIDSBase) entity).getMessage(i);
				hideArrival[i] = ((BlockPIDSBase.TileEntityBlockPIDSBase) entity).getHideArrival(i);
			} else {
				customMessages[i] = "";
			}
		}

		try {
			final Set<Route.ScheduleEntry> schedules;

			final Platform platform = RailwayData.getClosePlatform(ClientData.PLATFORMS, pos);
			if (platform == null) {
				schedules = new HashSet<>();
			} else {
				final Set<Route.ScheduleEntry> schedulesForPlatform = ClientData.SCHEDULES_FOR_PLATFORM.get(platform.id);
				schedules = schedulesForPlatform == null ? new HashSet<>() : schedulesForPlatform;
			}

			final List<Route.ScheduleEntry> scheduleList = new ArrayList<>(schedules);
			Collections.sort(scheduleList);

			final boolean showCarLength;
			final float carLengthMaxWidth;
			if (!showPlatforms) {
				int maxCars = 0;
				int minCars = Integer.MAX_VALUE;
				for (final Route.ScheduleEntry scheduleEntry : scheduleList) {
					final int trainCars = scheduleEntry.trainCars;
					if (trainCars > maxCars) {
						maxCars = trainCars;
					}
					if (trainCars < minCars) {
						minCars = trainCars;
					}
				}
				showCarLength = minCars != maxCars;
				carLengthMaxWidth = showCarLength ? scale * 6 / 16 : 0;
			} else {
				showCarLength = false;
				carLengthMaxWidth = 0;
			}

			final TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;

			boolean renderedOnce = false;
			for (int i = 0; i < maxArrivals; i++) {
				final int languageTicks = (int) Math.floor(RenderTrains.getGameTicks()) / SWITCH_LANGUAGE_TICKS;
				final String destinationString;
				final boolean useCustomMessage;
				if (i < scheduleList.size() && !hideArrival[i]) {
					final String[] destinationSplit = scheduleList.get(i).destination.split("\\|");
					if (customMessages[i].isEmpty()) {
						destinationString = IGui.textOrUntitled(destinationSplit[languageTicks % destinationSplit.length]);
						useCustomMessage = false;
					} else {
						final String[] customMessageSplit = customMessages[i].split("\\|");
						final int indexToUse = languageTicks % (destinationSplit.length + customMessageSplit.length);
						if (indexToUse < destinationSplit.length) {
							destinationString = IGui.textOrUntitled(destinationSplit[indexToUse]);
							useCustomMessage = false;
						} else {
							destinationString = customMessageSplit[indexToUse - destinationSplit.length];
							useCustomMessage = true;
						}
					}
				} else {
					final String[] destinationSplit = customMessages[i].split("\\|");
					destinationString = destinationSplit[languageTicks % destinationSplit.length];
					useCustomMessage = true;
				}

				matrices.push();
				matrices.translate(0.5, 0, 0.5);
				matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion((rotate90 ? 90 : 0) - facing.asRotation()));
				matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(180));
				matrices.translate((startX - 8) / 16, -startY / 16 + i * maxHeight / maxArrivals / 16, (startZ - 8) / 16 - SMALL_OFFSET * 2);
				matrices.scale(1F / scale, 1F / scale, 1F / scale);

				if(!renderedOnce) {
					/* CLOCK */
					long currTime = world.getLunarTime();
					Text timeString = new LiteralText(String.format("%02d:%02d", ((currTime / 1000) + 6) % 24, Math.round(currTime / 16.73) % 60)).fillStyle(style);
					matrices.push();
					matrices.translate(90,-10,0);
					matrices.scale(0.85F, 0.85F, 0.85F);
					textRenderer.draw(matrices, timeString, 0, 0, 0xFFFFFF);
					matrices.pop();

					/* DRAW RV BACKGROUND */
					final VertexConsumer vertexConsumerPIDSBG = vertexConsumers.getBuffer(MoreRenderLayers.getLight(new Identifier("jsblock:textures/block/pids_5.png"), false));
					matrices.push();
					matrices.translate(0,-9.5,0.01);
					drawTexture(matrices, vertexConsumerPIDSBG, startX - 26F / 2, -1.5F, 119F, 65.8F, facing, ARGB_WHITE, MAX_LIGHT_GLOWING);
					matrices.pop();
					renderedOnce = true;
				}

				if (useCustomMessage) {
					final int destinationWidth = textRenderer.getWidth(destinationString);
					if (destinationWidth > totalScaledWidth) {
						matrices.scale(totalScaledWidth / destinationWidth, 1, 1);
					}
					Text destString = new LiteralText(destinationString).fillStyle(style);
					textRenderer.draw(matrices, destString, 0, 0, textColor);

					matrices.push();
					matrices.translate(0,0,0.01);
					matrices.pop();
				} else {
					final Route.ScheduleEntry currentSchedule = scheduleList.get(i);

					final Text arrivalText;
					final int seconds = (int) ((currentSchedule.arrivalMillis - System.currentTimeMillis()) / 1000);
					final boolean isCJK = destinationString.codePoints().anyMatch(Character::isIdeographic);
					if (seconds >= 60) {
						arrivalText = new TranslatableText(isCJK ? "gui.mtr.arrival_min_cjk" : "gui.mtr.arrival_min", seconds / 60).fillStyle(style);
					} else {
						arrivalText = seconds > 0 ? new TranslatableText(isCJK ? "gui.mtr.arrival_sec_cjk" : "gui.mtr.arrival_sec", seconds).fillStyle(style) : null;
					}
					final Text carText = new TranslatableText(isCJK ? "gui.mtr.arrival_car_cjk" : "gui.mtr.arrival_car", currentSchedule.trainCars);

					final float newDestinationMaxWidth = destinationMaxWidth - carLengthMaxWidth;

					if (showPlatforms) {
						final VertexConsumer vertexConsumerStationCircle = vertexConsumers.getBuffer(MoreRenderLayers.getLight(new Identifier("mtr:textures/sign/circle.png"), true));

						if (platform != null) {
							final List<ClientCache.PlatformRouteDetails> platformRouteDetails = ClientData.DATA_CACHE.requestPlatformIdToRoutes(platform.id);
							routeData = platformRouteDetails == null ? new ArrayList<>() : platformRouteDetails;

							final float x = destinationStart + newDestinationMaxWidth;
							drawTexture(matrices, vertexConsumerStationCircle, x - 4.5F / 2, 0, 8, 8, facing, ARGB_BLACK + routeData.get(0).routeColor, 15);
							matrices.push();
							matrices.translate(destinationStart + newDestinationMaxWidth, 1.2F, -0.05);
							matrices.scale(0.8F, 0.8F, 0.8F);
							Text platformText = new LiteralText(platform.name).fillStyle(style);
							textRenderer.draw(matrices, platformText, -1F, 0, 0xFFFFFF);
							matrices.pop();
						}
					}

					if (showCarLength) {
						matrices.push();
						matrices.translate(destinationStart + newDestinationMaxWidth + platformMaxWidth, 0, 0);
						final int carTextWidth = textRenderer.getWidth(carText);
						if (carTextWidth > carLengthMaxWidth) {
							matrices.scale(carLengthMaxWidth / carTextWidth, 1, 1);
						}
						textRenderer.draw(matrices, carText, 0, 0, CAR_TEXT_COLOR);
						matrices.pop();
					}

					matrices.push();
					matrices.translate(destinationStart, 0, 0);
					final int destinationWidth = textRenderer.getWidth(destinationString);
					if (destinationWidth > newDestinationMaxWidth) {
						matrices.scale(newDestinationMaxWidth / destinationWidth, 1, 1);
					}

					Text destText = new LiteralText(destinationString).fillStyle(style);
					textRenderer.draw(matrices, destText, 0, 0, 0x000000);
					matrices.pop();

					if (arrivalText != null) {
						matrices.push();
						final int arrivalWidth = textRenderer.getWidth(arrivalText);
						if (arrivalWidth > arrivalMaxWidth) {
							matrices.translate(destinationStart + newDestinationMaxWidth + platformMaxWidth + carLengthMaxWidth, 0, 0);
							matrices.scale(arrivalMaxWidth / arrivalWidth, 1, 1);
						} else {
							matrices.translate(totalScaledWidth - arrivalWidth, 0, 0);
						}
						textRenderer.draw(matrices, arrivalText, 0, 0, textColor);
						matrices.pop();
					}
				}

				matrices.pop();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void drawTexture(MatrixStack matrices, VertexConsumer vertexConsumer, float x, float y, float width, float height, Direction facing, int color, int light) {
		IDrawing.drawTexture(matrices, vertexConsumer, x, y, 0, x + width, y + height, 0, 0, 0, 1, 1, facing, color, light);
	}
}