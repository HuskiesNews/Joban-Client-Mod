package com.jsblock.gui;

import com.jsblock.blocks.SoundLooper;
import com.jsblock.config.ClientConfig;
import com.jsblock.packets.Client;
import com.mojang.blaze3d.vertex.PoseStack;
import mtr.client.IDrawing;
import mtr.data.IGui;
import mtr.mappings.ScreenMapper;
import mtr.screen.WidgetBetterCheckbox;
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
	private final WidgetBetterTextField textBoxSoundVolume;
	private final WidgetBetterTextField textBoxRepeatTick;
	private final WidgetBetterCheckbox checkBoxNeedRedstone;
	private final Button buttonCategory;
	private final BlockPos pos;
	private int selectedCategory;

	private static final int TEXT_PADDING = 16;
	private static final int TEXT_FIELD_WIDTH = 100;
	private static final int FINAL_TEXT_HEIGHT = TEXT_HEIGHT + TEXT_PADDING;
	private static final int MAX_TEXT_LENGTH = 128;
	private static final int BUTTON_WIDTH = 60;
	private static final int BUTTON_HEIGHT = TEXT_HEIGHT + 10;
	private static final int DEFAULT_REPEAT_TICK = 20;
	private static final float DEFAULT_VOLUME = 1;
	private static final SoundSource[] SOURCE_LIST = {SoundSource.MASTER, SoundSource.MUSIC, SoundSource.WEATHER, SoundSource.AMBIENT, SoundSource.PLAYERS};

	public SoundLooperScreen(BlockPos pos) {
		super(new TextComponent(""));
		this.pos = pos;

		buttonCategory = new Button(0, 0, 0, BUTTON_HEIGHT, new TextComponent(""), button -> {
			selectedCategory++;
			if(selectedCategory > SOURCE_LIST.length - 1) {
				selectedCategory = 0;
			}

			button.setMessage(new TextComponent(SOURCE_LIST[selectedCategory].getName()));
		});

		textBoxSoundId = new WidgetBetterTextField(null, "mtr:ticket_barrier", MAX_TEXT_LENGTH);
		textBoxRepeatTick = new WidgetBetterTextField(WidgetBetterTextField.TextFieldFilter.POSITIVE_INTEGER, "20", MAX_TEXT_LENGTH);
		textBoxSoundVolume = new WidgetBetterTextField(WidgetBetterTextField.TextFieldFilter.POSITIVE_FLOATING_POINT, "1", MAX_TEXT_LENGTH);
		checkBoxNeedRedstone = new WidgetBetterCheckbox(0,0, 0, SQUARE_SIZE, new TextComponent(""), checked -> {});
	}

		@Override
		protected void init() {
			super.init();
			int i = 1;
			IDrawing.setPositionAndWidth(buttonCategory, width - SQUARE_SIZE - BUTTON_WIDTH, FINAL_TEXT_HEIGHT * (i++) + SQUARE_SIZE, BUTTON_WIDTH);
			IDrawing.setPositionAndWidth(textBoxSoundId, width - SQUARE_SIZE - TEXT_FIELD_WIDTH, FINAL_TEXT_HEIGHT * (i++) + SQUARE_SIZE, TEXT_FIELD_WIDTH);
			IDrawing.setPositionAndWidth(textBoxSoundVolume, width - SQUARE_SIZE - TEXT_FIELD_WIDTH, FINAL_TEXT_HEIGHT * (i++) + SQUARE_SIZE, TEXT_FIELD_WIDTH);
			IDrawing.setPositionAndWidth(textBoxRepeatTick, width - SQUARE_SIZE - TEXT_FIELD_WIDTH, FINAL_TEXT_HEIGHT * (i++) + SQUARE_SIZE, TEXT_FIELD_WIDTH);
			IDrawing.setPositionAndWidth(checkBoxNeedRedstone, width - SQUARE_SIZE - TEXT_FIELD_WIDTH, FINAL_TEXT_HEIGHT * (i++) + SQUARE_SIZE, TEXT_FIELD_WIDTH);

			final Level world = Minecraft.getInstance().level;
			if (world != null) {
				final BlockEntity entity = world.getBlockEntity(pos);
				if (entity instanceof SoundLooper.TileEntitySoundLooper) {
					textBoxSoundId.setValue(((SoundLooper.TileEntitySoundLooper) entity).getSoundId());
					textBoxRepeatTick.setValue(String.valueOf(((SoundLooper.TileEntitySoundLooper) entity).getLoopInterval()));
					textBoxSoundVolume.setValue(String.valueOf(((SoundLooper.TileEntitySoundLooper) entity).getSoundVolume()));
					checkBoxNeedRedstone.setChecked(((SoundLooper.TileEntitySoundLooper) entity).getNeedRedstone());
					selectedCategory = ((SoundLooper.TileEntitySoundLooper) entity).getSoundCategory();
					buttonCategory.setMessage(new TextComponent(SOURCE_LIST[selectedCategory].getName()));
				}
			}

			addDrawableChild(buttonCategory);
			addDrawableChild(textBoxSoundId);
			addDrawableChild(textBoxSoundVolume);
			addDrawableChild(textBoxRepeatTick);
			addDrawableChild(checkBoxNeedRedstone);
		}

		@Override
		public void render(PoseStack matrices, int mouseX, int mouseY, float delta) {
			try {
				renderBackground(matrices);
				drawCenteredString(matrices, font, new TranslatableComponent("gui.jsblock.looper"), width / 2, TEXT_PADDING, ARGB_WHITE);

				int i = 1;
				drawString(matrices, font, new TranslatableComponent("gui.jsblock.looper.sound_source"), SQUARE_SIZE, FINAL_TEXT_HEIGHT * (i++) + SQUARE_SIZE, ARGB_WHITE);
				drawString(matrices, font, new TranslatableComponent("gui.jsblock.looper.sound_id"), SQUARE_SIZE, FINAL_TEXT_HEIGHT * (i++) + SQUARE_SIZE, ARGB_WHITE);
				drawString(matrices, font, new TranslatableComponent("gui.jsblock.looper.sound_vol"), SQUARE_SIZE, FINAL_TEXT_HEIGHT * (i++) + SQUARE_SIZE, ARGB_WHITE);
				drawString(matrices, font, new TranslatableComponent("gui.jsblock.looper.repeat_tick"), SQUARE_SIZE, FINAL_TEXT_HEIGHT * (i++) + SQUARE_SIZE, ARGB_WHITE);
				drawString(matrices, font, new TranslatableComponent("gui.jsblock.looper.need_redstone"), SQUARE_SIZE, FINAL_TEXT_HEIGHT * (i++) + SQUARE_SIZE, ARGB_WHITE);

				super.render(matrices, mouseX, mouseY, delta);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onClose() {
			int repeatTick;
			try {
				repeatTick = Integer.parseInt(textBoxRepeatTick.getValue());
			} catch (Exception ignored) {
				repeatTick = DEFAULT_REPEAT_TICK;
			}
			float volume;
			try {
				volume = Float.parseFloat(textBoxSoundVolume.getValue());
			} catch (Exception ignored) {
				volume = DEFAULT_VOLUME;
			}

			Client.sendSoundLooperC2S(pos, selectedCategory, textBoxSoundId.getValue(), repeatTick, volume, checkBoxNeedRedstone.selected());
			super.onClose();
		}
}
