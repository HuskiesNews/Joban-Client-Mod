package com.jsblock.render;

import com.jsblock.config.ClientConfig;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import mtr.MTRClient;
import mtr.block.BlockPIDSBase;
import mtr.block.IBlock;
import mtr.client.ClientCache;
import mtr.client.ClientData;
import mtr.client.IDrawing;
import mtr.data.IGui;
import mtr.data.Platform;
import mtr.data.RailwayData;
import mtr.data.ScheduleEntry;
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
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;

import java.util.*;

import static com.jsblock.gui.IDrawingJoban.renderTextWithOffset;

public class RenderLCDPIDS<T extends BlockEntityMapper> extends BlockEntityRendererMapper<T> implements IGui {

	private final String pidsType;
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
	private final float screenWidth;
	private final int textColor;
	private List<ClientCache.PlatformRouteDetails> routeData;

	private static final int SWITCH_LANGUAGE_TICKS = 80;
	private static final int MAX_VIEW_DISTANCE = 16;

	public RenderLCDPIDS(BlockEntityRenderDispatcher dispatcher, int maxArrivals, float startX, float startY, float startZ, float maxHeight, int maxWidth, boolean rotate90, boolean renderArrivalNumber, boolean showPlatforms, int textColor, float textPadding, boolean appendDotAfterMin, String pidsType) {
		super(dispatcher);
		scale = 230 * maxArrivals / maxHeight;
		totalScaledWidth = scale * maxWidth / 16;
		destinationStart = renderArrivalNumber ? scale * 2 / 16 : 0;
		destinationMaxWidth = totalScaledWidth * 0.33F;
		platformMaxWidth = showPlatforms ? scale * 2F / 16 : 0;
		arrivalMaxWidth = totalScaledWidth - destinationStart - destinationMaxWidth - platformMaxWidth;
		screenWidth = arrivalMaxWidth / 1.35F;
		this.maxArrivals = maxArrivals;
		this.maxHeight = maxHeight;
		this.startX = startX;
		this.startY = startY;
		this.startZ = startZ;
		this.rotate90 = rotate90;
		this.showPlatforms = showPlatforms;
		this.textColor = textColor;
		this.pidsType = pidsType;
	}

	@Override
	public void render(T entity, float tickDelta, PoseStack matrices, MultiBufferSource vertexConsumers, int light, int overlay) {
		final Level world = entity.getLevel();
		if (world == null || ClientConfig.getRenderDisabled()) {
			return;
		}

		final BlockPos pos = entity.getBlockPos();
		final Direction facing = IBlock.getStatePropertySafe(world, pos, HorizontalDirectionalBlock.FACING);
		final String chineseFont;
		final String englishFont;

		if (pidsType.equals("pids_tkl")) {
			chineseFont = ClientConfig.getPIDS4ChinFont();
			englishFont = ClientConfig.getPIDS4EngFont();
		} else {
			chineseFont = "mtr:mtr";
			englishFont = "mtr:mtr";
		}

		/* If the player is too far away from the PIDS that not even the train renders */
		if (RenderTrains.shouldNotRender(pos, Math.min(MAX_VIEW_DISTANCE, RenderTrains.maxTrainRenderDistance), rotate90 ? null : facing)) {
			return;
		}

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
			final Set<ScheduleEntry> schedules;

			final long platformId = RailwayData.getClosePlatformId(ClientData.PLATFORMS, ClientData.DATA_CACHE, pos);
			if (platformId == 0) {
				schedules = new HashSet<>();
			} else {
				final Set<ScheduleEntry> schedulesForPlatform = ClientData.SCHEDULES_FOR_PLATFORM.get(platformId);
				schedules = schedulesForPlatform == null ? new HashSet<>() : schedulesForPlatform;
			}

			final List<ScheduleEntry> scheduleList = new ArrayList<>(schedules);
			Collections.sort(scheduleList);

			final boolean showCarLength;
			int maxCars = 0;
			int minCars = Integer.MAX_VALUE;

			/* Find the maximum and minimum cars out of the schedule list */
			for (final ScheduleEntry scheduleEntry : scheduleList) {
				final int trainCars = scheduleEntry.trainCars;
				if (trainCars > maxCars) {
					maxCars = trainCars;
				}
				if (trainCars < minCars) {
					minCars = trainCars;
				}
			}
			showCarLength = minCars != maxCars;

			final Font textRenderer = Minecraft.getInstance().font;

			matrices.pushPose();
			matrices.translate(0.5, 0, 0.5);
			matrices.mulPose(Vector3f.YP.rotationDegrees((rotate90 ? 90 : 0) - facing.toYRot()));
			matrices.mulPose(Vector3f.ZP.rotationDegrees(180));
			matrices.translate((startX - 8) / 16, -startY / 16 + 0 * maxHeight / maxArrivals / 16, (startZ - 8) / 16 - SMALL_OFFSET * 2);
			matrices.scale(1F / scale, 1F / scale, 1F / scale);

			final MultiBufferSource.BufferSource immediate = MultiBufferSource.immediate(Tesselator.getInstance().getBuilder());

			matrices.popPose();

			/* Loop through each arrival */
			for (int i = 0; i < maxArrivals; i++) {
				final int languageTicks = (int) Math.floor(MTRClient.getGameTick()) / SWITCH_LANGUAGE_TICKS;
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
				matrices.scale(1F / (scale / 2), 1F / (scale / 2), 1F / (scale / 2));

				if (useCustomMessage) {
					renderTextWithOffset(matrices, textRenderer, immediate, destinationString, 0, 0, screenWidth, 4, textColor, MAX_LIGHT_GLOWING, HorizontalAlignment.LEFT, VerticalAlignment.TOP, false, chineseFont, englishFont);
				} else {
					final ScheduleEntry currentSchedule = scheduleList.get(i);
					final Component arrivalText;
					final int seconds = (int) ((currentSchedule.arrivalMillis - System.currentTimeMillis()) / 1000);
					final boolean isCJK = destinationString.codePoints().anyMatch(Character::isIdeographic);
					if (seconds >= 60) {
						arrivalText = new TranslatableComponent(isCJK ? "gui.mtr.arrival_min_cjk" : "gui.mtr.arrival_min", seconds / 60);
					} else {
						arrivalText = seconds > 0 ? new TranslatableComponent(isCJK ? "gui.mtr.arrival_sec_cjk" : "gui.mtr.arrival_sec", seconds) : null;
					}
					final Component carText = new TranslatableComponent(isCJK ? "gui.mtr.arrival_car_cjk" : "gui.mtr.arrival_car", currentSchedule.trainCars);

					/* PLATFORM */
					if (showPlatforms) {
						final VertexConsumer vertexConsumerStationCircle = vertexConsumers.getBuffer(MoreRenderLayers.getLight(new ResourceLocation("mtr:textures/sign/circle.png"), true));

						final Platform platform = ClientData.DATA_CACHE.platformIdMap.get(platformId);
						if (platform != null) {
							final List<ClientCache.PlatformRouteDetails> platformRouteDetails = ClientData.DATA_CACHE.requestPlatformIdToRoutes(platform.id);
							routeData = platformRouteDetails == null ? new ArrayList<>() : platformRouteDetails;
							final float x = destinationStart + destinationMaxWidth;

							/* PLATFORM CIRCLE */
							int routeColor = routeData.isEmpty() ? 0 : routeData.get(0).routeColor;
							drawTexture(matrices, vertexConsumerStationCircle, x, 0, 4, 4, facing, routeColor + ARGB_BLACK, MAX_LIGHT_GLOWING);
							matrices.pushPose();
							matrices.translate(x + 1.95F, 2.2F, -0.05);
							matrices.scale(0.7F, 0.7F, 0.7F);

							renderTextWithOffset(matrices, textRenderer, immediate, platform.name, 0, 0, 4, 3, textColor, MAX_LIGHT_GLOWING, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, true, chineseFont, englishFont);
							matrices.popPose();
						}
					}

					matrices.pushPose();
					matrices.translate(destinationStart, 0, 0);

					renderTextWithOffset(matrices, textRenderer, immediate, destinationString, 0, 0, destinationMaxWidth, 5, textColor, MAX_LIGHT_GLOWING, HorizontalAlignment.LEFT, VerticalAlignment.TOP, false, chineseFont, englishFont);
					matrices.popPose();

					if (arrivalText != null) {
						matrices.pushPose();
						final boolean isShowCar = showCarLength && (languageTicks % 6 == 0 || languageTicks % 6 == 1);

						matrices.translate(screenWidth, 0, 0);

						if (isShowCar) {
							renderTextWithOffset(matrices, textRenderer, immediate, carText.getString(), 0, -0.025F, 15, 5, textColor, MAX_LIGHT_GLOWING, HorizontalAlignment.RIGHT, VerticalAlignment.TOP, false, chineseFont, englishFont);
						} else {
							renderTextWithOffset(matrices, textRenderer, immediate, arrivalText.getString(), 0, -0.025F, 15, 5, textColor, MAX_LIGHT_GLOWING, HorizontalAlignment.RIGHT, VerticalAlignment.TOP, false, chineseFont, englishFont);
						}

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
}