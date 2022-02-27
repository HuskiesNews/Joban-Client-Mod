package com.jsblock.gui;

import com.jsblock.blocks.SoundLooper;
import com.jsblock.config.ClientConfig;
import com.jsblock.packets.Client;
import com.mojang.blaze3d.vertex.PoseStack;
import mtr.client.IDrawing;
import mtr.data.IGui;
import mtr.mappings.ScreenMapper;
import mtr.screen.WidgetBetterTextField;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public class SoundLooperScreen extends ScreenMapper implements IGui {

	private final WidgetBetterTextField textBoxSoundId;
	private final WidgetBetterTextField textBoxRepeatTick;
	private final Button buttonCategory;
	private final BlockPos pos;
	private SoundSource selectedCategory;

	private static final int TEXT_PADDING = 12;
	private static final int TEXT_FIELD_WIDTH = 100;
	private static final int FINAL_TEXT_HEIGHT = TEXT_HEIGHT + TEXT_PADDING;
	private static final int MAX_TEXT_LENGTH = 128;
	private static final int BUTTON_WIDTH = 60;
	private static final int BUTTON_HEIGHT = TEXT_HEIGHT + 8;

	public SoundLooperScreen(BlockPos pos) {
		super(new TextComponent(""));
		this.pos = pos;

		buttonCategory = new Button(0, 0, 0, BUTTON_HEIGHT, new TextComponent(""), button -> {
			button.setMessage(new TextComponent(SoundSource.values()[selectedCategory.ordinal() + 1].getName()));
		});

		textBoxSoundId = new WidgetBetterTextField(null, "minecraft:block.anvil.land", MAX_TEXT_LENGTH);
		textBoxRepeatTick = new WidgetBetterTextField(WidgetBetterTextField.TextFieldFilter.POSITIVE_INTEGER, "20", MAX_TEXT_LENGTH);
	}

		@Override
		protected void init() {
			super.init();

			int i = 1;
			IDrawing.setPositionAndWidth(buttonCategory, width - SQUARE_SIZE - BUTTON_WIDTH, SQUARE_SIZE, BUTTON_WIDTH);
			IDrawing.setPositionAndWidth(textBoxSoundId, width - SQUARE_SIZE - TEXT_FIELD_WIDTH, FINAL_TEXT_HEIGHT * (i++) + SQUARE_SIZE, TEXT_FIELD_WIDTH);
			IDrawing.setPositionAndWidth(textBoxRepeatTick, width - SQUARE_SIZE - TEXT_FIELD_WIDTH, FINAL_TEXT_HEIGHT * (i++) + SQUARE_SIZE, TEXT_FIELD_WIDTH);

			final Level world = Minecraft.getInstance().level;
			if (world != null) {
				final BlockEntity entity = world.getBlockEntity(pos);
				if (entity instanceof SoundLooper.TileEntitySoundLooper) {
					textBoxSoundId.setValue(((SoundLooper.TileEntitySoundLooper) entity).getSoundId());
					textBoxRepeatTick.setValue(String.valueOf(((SoundLooper.TileEntitySoundLooper) entity).getLoopInterval()));
				}
			}

			addDrawableChild(textBoxSoundId);
			addDrawableChild(textBoxRepeatTick);
		}

		@Override
		public void render(PoseStack matrices, int mouseX, int mouseY, float delta) {
			try {
				renderBackground(matrices);
				drawCenteredString(matrices, font, new TranslatableComponent("gui.jsblock.looper"), width / 2, TEXT_PADDING, ARGB_WHITE);

				int i = 1;
				drawString(matrices, font, new TranslatableComponent("gui.jsblock.looper.sound_id"), SQUARE_SIZE, FINAL_TEXT_HEIGHT * (i++) + SQUARE_SIZE, ARGB_WHITE);
				drawString(matrices, font, new TranslatableComponent("gui.jsblock.looper.repeat_tick"), SQUARE_SIZE, FINAL_TEXT_HEIGHT * (i++) + SQUARE_SIZE, ARGB_WHITE);

				super.render(matrices, mouseX, mouseY, delta);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onClose() {
			super.onClose();
			Client.sendSoundLooperC2S(pos, textBoxSoundId.getValue(), selectedCategory.getName(), (int)(Double.parseDouble(textBoxRepeatTick.getValue())));
		}
}
