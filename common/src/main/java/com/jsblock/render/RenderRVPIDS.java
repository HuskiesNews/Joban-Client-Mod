package com.jsblock.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import mtr.MTR;
import mtr.block.BlockPIDSBase;
import mtr.block.IBlock;
import mtr.config.Config;
import mtr.data.IGui;
import mtr.data.Platform;
import mtr.data.RailwayData;
import mtr.data.Route;
import mtr.gui.ClientCache;
import mtr.gui.ClientData;
import mtr.gui.IDrawing;
import mtr.mappings.BlockEntityMapper;
import mtr.mappings.BlockEntityRendererMapper;
import mtr.render.MoreRenderLayers;
import mtr.render.RenderTrains;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;

import java.util.*;

public class RenderRVPIDS<T extends BlockEntityMapper> extends BlockEntityRendererMapper<T> implements IGui {

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
	private final boolean showPlatforms;
	private final int textColor;
	private List<ClientCache.PlatformRouteDetails> routeData;

	private static final int SWITCH_LANGUAGE_TICKS = 80;
	private static final int CAR_TEXT_COLOR = 0xFF0000;
	private static final int MAX_VIEW_DISTANCE = 16;

	public RenderRVPIDS(BlockEntityRenderDispatcher dispatcher, int maxArrivals, float startX, float startY, float startZ, float maxHeight, int maxWidth, boolean rotate90, boolean renderArrivalNumber, boolean showPlatforms, int textColor) {
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
		this.showPlatforms = showPlatforms;
		this.textColor = textColor;
	}

	@Override
	public void render(T entity, float tickDelta, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay) {
		final Level world = entity.getLevel();
		if (world == null) {
			return;
		}

		final BlockPos pos = entity.getBlockPos();
		final Direction facing = IBlock.getStatePropertySafe(world, pos, HorizontalDirectionalBlock.FACING);
		final Style style = Config.useMTRFont() ? Style.EMPTY.withFont(new ResourceLocation(MTR.MOD_ID, "mtr")) : Style.EMPTY;

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

			final Font textRenderer = Minecraft.getInstance().font;

			matrices.pushPose();
			matrices.translate(0.5, 0, 0.5);
			matrices.mulPose(Vector3f.YP.rotationDegrees((rotate90 ? 90 : 0) - facing.toYRot()));
			matrices.mulPose(Vector3f.ZP.rotationDegrees(180));
			matrices.translate((startX - 8) / 16, -startY / 16 + 0 * maxHeight / maxArrivals / 16, (startZ - 8) / 16 - SMALL_OFFSET * 2);
			matrices.scale(1F / scale, 1F / scale, 1F / scale);

			if (RenderTrains.shouldNotRender(pos, Math.min(MAX_VIEW_DISTANCE, RenderTrains.maxTrainRenderDistance), rotate90 ? null : facing)) {
				final VertexConsumer vertexConsumerPIDSBG = vertexConsumers.getBuffer(MoreRenderLayers.getLight(new ResourceLocation("jsblock:textures/block/pids_5.png"), false));
				matrices.translate(0, -9.5, 0.01);
				drawTexture(matrices, vertexConsumerPIDSBG, startX - 26F / 2, -1.5F, 119F, 65.8F, facing, ARGB_WHITE, MAX_LIGHT_GLOWING);
				matrices.popPose();
				return;
			}

			/* CLOCK */
			Level worlds = entity.getLevel();
			long time = worlds.getDayTime() + 6000;
			long hours = time / 1000;
			long minutes = Math.round((time - (hours * 1000)) / 16.8);
			Component timeString = new TextComponent(String.format("%02d:%02d", hours % 24, minutes % 60)).setStyle(style);
			matrices.pushPose();
			matrices.translate(90, -9.8, -0.01);
			matrices.scale(0.75F, 0.75F, 0.75F);
			renderTextWithOffset(matrices, textRenderer, timeString, 0, 0, 0xFFFFFF);
			matrices.popPose();

			/* WEATHER */
			ResourceLocation weatherTexture = worlds.isThundering() ? new ResourceLocation("jsblock:textures/block/weather_thunder.png") : worlds.isRaining() ? new ResourceLocation("jsblock:textures/block/weather_rainy.png") : new ResourceLocation("jsblock:textures/block/weather_sunny.png");
			final VertexConsumer vertexConsumerWeather = vertexConsumers.getBuffer(MoreRenderLayers.getLight(weatherTexture, false));
			matrices.pushPose();
			matrices.translate(0, -9.5, -0.01);
			drawTexture(matrices, vertexConsumerWeather, startX - 10F, -1.5F, 8F, 8F, facing, ARGB_WHITE, MAX_LIGHT_GLOWING);
			matrices.popPose();

			/* DRAW RV BACKGROUND */
			final VertexConsumer vertexConsumerPIDSBG = vertexConsumers.getBuffer(MoreRenderLayers.getLight(new ResourceLocation("jsblock:textures/block/pids_5.png"), false));
			matrices.translate(0, -9.5, 0.01);
			drawTexture(matrices, vertexConsumerPIDSBG, startX - 26F / 2, -1.5F, 119F, 65.8F, facing, ARGB_WHITE, MAX_LIGHT_GLOWING);
			matrices.popPose();

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

				matrices.pushPose();
				matrices.translate(0.5, 0, 0.5);
				matrices.mulPose(Vector3f.YP.rotationDegrees((rotate90 ? 90 : 0) - facing.toYRot()));
				matrices.mulPose(Vector3f.ZP.rotationDegrees(180));
				matrices.translate((startX - 8) / 16, -startY / 16 + i * maxHeight / maxArrivals / 16, (startZ - 8) / 16 - SMALL_OFFSET * 2);
				matrices.scale(1F / scale, 1F / scale, 1F / scale);

				if (useCustomMessage) {
					final int destinationWidth = textRenderer.width(destinationString);
					if (destinationWidth > totalScaledWidth) {
						matrices.scale(totalScaledWidth / destinationWidth, 1, 1);
					}

					Component destString = new TextComponent(destinationString).setStyle(style);
					renderTextWithOffset(matrices, textRenderer, destString, 0, 0, textColor);
				} else {
					final Route.ScheduleEntry currentSchedule = scheduleList.get(i);
					final Component arrivalText;
					final int seconds = (int) ((currentSchedule.arrivalMillis - System.currentTimeMillis()) / 1000);
					final boolean isCJK = destinationString.codePoints().anyMatch(Character::isIdeographic);
					if (seconds >= 60) {
						arrivalText = new TranslatableComponent(isCJK ? "gui.mtr.arrival_min_cjk" : "gui.mtr.arrival_min", seconds / 60).setStyle(style);
					} else {
						arrivalText = seconds > 0 ? new TranslatableComponent(isCJK ? "gui.mtr.arrival_sec_cjk" : "gui.mtr.arrival_sec", seconds).setStyle(style) : null;
					}
					final Component carText = new TranslatableComponent(isCJK ? "gui.mtr.arrival_car_cjk" : "gui.mtr.arrival_car", currentSchedule.trainCars);

					final float newDestinationMaxWidth = destinationMaxWidth - carLengthMaxWidth;

					if (showPlatforms) {
						final VertexConsumer vertexConsumerStationCircle = vertexConsumers.getBuffer(MoreRenderLayers.getLight(new ResourceLocation("mtr:textures/sign/circle.png"), true));

						if (platform != null) {
							final List<ClientCache.PlatformRouteDetails> platformRouteDetails = ClientData.DATA_CACHE.requestPlatformIdToRoutes(platform.id);
							routeData = platformRouteDetails == null ? new ArrayList<>() : platformRouteDetails;

							final float x = destinationStart + newDestinationMaxWidth;
							drawTexture(matrices, vertexConsumerStationCircle, x - 4.5F / 2, 0, 8, 8, facing, ARGB_BLACK + routeData.get(0).routeColor, 15);
							matrices.pushPose();
							matrices.translate(destinationStart + newDestinationMaxWidth, 1.2F, -0.05);
							matrices.scale(0.8F, 0.8F, 0.8F);
							Component platformText = new TextComponent(platform.name).setStyle(style);
							renderTextWithOffset(matrices, textRenderer, platformText, -0.9F, 0, 0xFFFFFF);
							matrices.popPose();
						}
					}

//					if (showCarLength) {
//						matrices.pushPose();
//						matrices.translate(destinationStart + newDestinationMaxWidth + platformMaxWidth, 0, 0);
//						final int carTextWidth = textRenderer.width(carText);
//						if (carTextWidth > carLengthMaxWidth) {
//							matrices.scale(carLengthMaxWidth / carTextWidth, 1, 1);
//						}
//						textRenderer.draw(matrices, carText, 0, 0, CAR_TEXT_COLOR);
//						matrices.popPose();
//					}

					matrices.pushPose();
					matrices.translate(destinationStart, 0, 0);
					/* + 3 is offset to prevent text overflowing the platform circle */
					final int destinationWidth = textRenderer.width(destinationString) + 3;
					if (destinationWidth > newDestinationMaxWidth) {
						matrices.scale(newDestinationMaxWidth / destinationWidth, 1, 1);
					}

					Component destText = new TextComponent(destinationString).setStyle(style);
					renderTextWithOffset(matrices, textRenderer, destText, 0, 0, 0x000000);
					matrices.popPose();

					if (arrivalText != null) {
						matrices.pushPose();
						final int arrivalWidth = textRenderer.width(arrivalText);
						if (arrivalWidth > arrivalMaxWidth) {
							matrices.translate(destinationStart + newDestinationMaxWidth + platformMaxWidth + carLengthMaxWidth, 0, 0);
							matrices.scale(arrivalMaxWidth / arrivalWidth, 1, 1);
						} else {
							matrices.translate(totalScaledWidth - arrivalWidth, 0, 0);
						}
						renderTextWithOffset(matrices, textRenderer, arrivalText, 0, -0.025F, textColor);
						matrices.popPose();
					}
				}

				matrices.popPose();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void drawTexture(PoseStack matrices, VertexConsumer vertexConsumer, float x, float y, float width, float height, Direction facing, int color, int light) {
		IDrawing.drawTexture(matrices, vertexConsumer, x, y, 0, x + width, y + height, 0, 0, 0, 1, 1, facing, color, light);
	}

	static void renderTextWithOffset(PoseStack matrices, Font textRenderer, Component text, float x, float y, int color) {
		final float finalY;
		final float finalX;
		if (Config.useMTRFont() && text.getString().codePoints().noneMatch(Character::isIdeographic)) {
			finalY = y + 0.5F;
			finalX = x;
		} else {
			finalY = y;
			finalX = x + 0.5F;
		}

		textRenderer.draw(matrices, text, finalX, finalY, color);
	}
}