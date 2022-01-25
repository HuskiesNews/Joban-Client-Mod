package com.jsblock.gui;

import com.jsblock.config.ClientConfig;
import com.mojang.blaze3d.vertex.PoseStack;
import mtr.client.IDrawing;
import mtr.data.IGui;
import mtr.screen.WidgetBetterTextField;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import mtr.mappings.ScreenMapper;

public class ConfigScreen extends ScreenMapper implements IGui {

	private boolean enableRendering;
	private final Button buttonUseMTRFont;
	private final WidgetBetterTextField textBoxRVPIDSFontChin;
	private final WidgetBetterTextField textBoxRVPIDSFontEng;
	private final WidgetBetterTextField textBoxKCRSignFontChin;
	private final WidgetBetterTextField textBoxKCRSignFontEng;
	private final WidgetBetterTextField textBoxPIDS4FontChin;
	private final WidgetBetterTextField textBoxPIDS4FontEng;

	private static final int TEXT_PADDING = 12;
	private static final int BUTTON_WIDTH = 60;
	private static final int TEXT_FIELD_WIDTH = 100;
	private static final int BUTTON_HEIGHT = TEXT_HEIGHT + 8;
	private static final int FINAL_TEXT_HEIGHT = TEXT_HEIGHT + TEXT_PADDING;
	private static final int MAX_TEXT_LENGTH = 128;

	public ConfigScreen() {
		super(new TextComponent(""));

		buttonUseMTRFont = new Button(0, 0, 0, BUTTON_HEIGHT, new TextComponent(""), button -> {
			enableRendering = ClientConfig.setRenderDisabled(!enableRendering);
			setButtonText(button, enableRendering);
		});

		textBoxRVPIDSFontChin = new WidgetBetterTextField(null, "mtr:mtr", MAX_TEXT_LENGTH);
		textBoxRVPIDSFontEng = new WidgetBetterTextField(null, "mtr:mtr", MAX_TEXT_LENGTH);
		textBoxKCRSignFontChin = new WidgetBetterTextField(null, "jsblock:kcr_chin", MAX_TEXT_LENGTH);
		textBoxKCRSignFontEng = new WidgetBetterTextField(null, "jsblock:kcr_eng", MAX_TEXT_LENGTH);
		textBoxPIDS4FontChin = new WidgetBetterTextField(null, "jsblock:pids_4", MAX_TEXT_LENGTH);
		textBoxPIDS4FontEng = new WidgetBetterTextField(null, "jsblock:pids_4", MAX_TEXT_LENGTH);
	}

		@Override
		protected void init() {
			super.init();
			enableRendering = ClientConfig.getRenderDisabled();

			int i = 1;
			IDrawing.setPositionAndWidth(buttonUseMTRFont, width - SQUARE_SIZE - BUTTON_WIDTH, SQUARE_SIZE, BUTTON_WIDTH);
			IDrawing.setPositionAndWidth(textBoxRVPIDSFontChin, width - SQUARE_SIZE - TEXT_FIELD_WIDTH, FINAL_TEXT_HEIGHT * (i++) + SQUARE_SIZE, TEXT_FIELD_WIDTH);
			IDrawing.setPositionAndWidth(textBoxRVPIDSFontEng, width - SQUARE_SIZE - TEXT_FIELD_WIDTH, FINAL_TEXT_HEIGHT * (i++) + SQUARE_SIZE, TEXT_FIELD_WIDTH);
			IDrawing.setPositionAndWidth(textBoxPIDS4FontChin, width - SQUARE_SIZE - TEXT_FIELD_WIDTH, FINAL_TEXT_HEIGHT * (i++) + SQUARE_SIZE, TEXT_FIELD_WIDTH);
			IDrawing.setPositionAndWidth(textBoxPIDS4FontEng, width - SQUARE_SIZE - TEXT_FIELD_WIDTH, FINAL_TEXT_HEIGHT * (i++) + SQUARE_SIZE, TEXT_FIELD_WIDTH);
			IDrawing.setPositionAndWidth(textBoxKCRSignFontChin, width - SQUARE_SIZE - TEXT_FIELD_WIDTH, FINAL_TEXT_HEIGHT * (i++) + SQUARE_SIZE, TEXT_FIELD_WIDTH);
			IDrawing.setPositionAndWidth(textBoxKCRSignFontEng, width - SQUARE_SIZE - TEXT_FIELD_WIDTH, FINAL_TEXT_HEIGHT * (i++) + SQUARE_SIZE, TEXT_FIELD_WIDTH);

			textBoxRVPIDSFontChin.setValue(ClientConfig.getRVPIDSChinFont());
			textBoxRVPIDSFontEng.setValue(ClientConfig.getRVPIDSEngFont());
			textBoxPIDS4FontChin.setValue(ClientConfig.getPIDS4ChinFont());
			textBoxPIDS4FontEng.setValue(ClientConfig.getPIDS4EngFont());
			textBoxKCRSignFontChin.setValue(ClientConfig.getKCRSignChinFont());
			textBoxKCRSignFontEng.setValue(ClientConfig.getKCRSignEngFont());

			setButtonText(buttonUseMTRFont, enableRendering);

			addDrawableChild(buttonUseMTRFont);
			addDrawableChild(textBoxRVPIDSFontChin);
			addDrawableChild(textBoxRVPIDSFontEng);
			addDrawableChild(textBoxPIDS4FontChin);
			addDrawableChild(textBoxPIDS4FontEng);
			addDrawableChild(textBoxKCRSignFontChin);
			addDrawableChild(textBoxKCRSignFontEng);
		}

		@Override
		public void render(PoseStack matrices, int mouseX, int mouseY, float delta) {
			try {
				renderBackground(matrices);
				drawCenteredString(matrices, font, new TranslatableComponent("gui.jsblock.config"), width / 2, TEXT_PADDING, ARGB_WHITE);

				final int yStart1 = SQUARE_SIZE + TEXT_PADDING / 2;
				int i = 1;
				drawString(matrices, font, new TranslatableComponent("gui.jsblock.config.enable_render"), SQUARE_SIZE, yStart1, ARGB_WHITE);
				drawString(matrices, font, new TranslatableComponent("gui.jsblock.config.rvpids_chin_font"), SQUARE_SIZE, FINAL_TEXT_HEIGHT * (i++) + yStart1, ARGB_WHITE);
				drawString(matrices, font, new TranslatableComponent("gui.jsblock.config.rvpids_eng_font"), SQUARE_SIZE, FINAL_TEXT_HEIGHT * (i++) + yStart1, ARGB_WHITE);
				drawString(matrices, font, new TranslatableComponent("gui.jsblock.config.pids_4_chin_font"), SQUARE_SIZE, FINAL_TEXT_HEIGHT * (i++) + yStart1, ARGB_WHITE);
				drawString(matrices, font, new TranslatableComponent("gui.jsblock.config.pids_4_eng_font"), SQUARE_SIZE, FINAL_TEXT_HEIGHT * (i++) + yStart1, ARGB_WHITE);
				drawString(matrices, font, new TranslatableComponent("gui.jsblock.config.kcrsign_chin_font"), SQUARE_SIZE, FINAL_TEXT_HEIGHT * (i++) + yStart1, ARGB_WHITE);
				drawString(matrices, font, new TranslatableComponent("gui.jsblock.config.kcrsign_eng_font"), SQUARE_SIZE, FINAL_TEXT_HEIGHT * (i++) + yStart1, ARGB_WHITE);

				super.render(matrices, mouseX, mouseY, delta);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onClose() {
			super.onClose();
			ClientConfig.setRVPIDSChinFont(textBoxRVPIDSFontChin.getValue());
			ClientConfig.setRVPIDSEngFont(textBoxRVPIDSFontEng.getValue());
			ClientConfig.setPIDS4ChinFont(textBoxPIDS4FontChin.getValue());
			ClientConfig.setPIDS4EngFont(textBoxPIDS4FontEng.getValue());
			ClientConfig.setKCRSignChinFont(textBoxKCRSignFontChin.getValue());
			ClientConfig.setKCRSignEngFont(textBoxKCRSignFontEng.getValue());
			ClientConfig.writeConfig();
		}

		private static void setButtonText(Button button, boolean state) {
			button.setMessage(new TranslatableComponent(state ? "options.mtr.on" : "options.mtr.off"));
		}
}
