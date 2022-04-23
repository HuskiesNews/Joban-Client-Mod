package com.jsblock.gui;

import com.jsblock.blocks.PIDSRVBase;
import com.jsblock.packets.Client;
import com.mojang.blaze3d.vertex.PoseStack;
import mtr.client.IDrawing;
import mtr.data.IGui;
import mtr.mappings.ScreenMapper;
import mtr.packet.IPacket;
import mtr.screen.WidgetBetterCheckbox;
import mtr.screen.WidgetBetterTextField;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public class RVPIDSConfigScreen extends ScreenMapper implements IGui, IPacket {

    private final BlockPos pos1;
    private final BlockPos pos2;
    private final String[] messages;
    private final boolean[] hideArrival;
    private boolean hidePlatformNumber;
    private final WidgetBetterTextField[] textFieldMessages;
    private final WidgetBetterCheckbox[] buttonsHideArrival;
    private final WidgetBetterCheckbox buttonsHidePlatformNumbers;
    private final Component messageText = new TranslatableComponent("gui.mtr.pids_message");
    private final Component hideArrivalText = new TranslatableComponent("gui.mtr.hide_arrival");
    private final Component hidePlatformNumberText = new TranslatableComponent("gui.jsblock.hide_platform_number");

    private static final int MAX_MESSAGE_LENGTH = 2048;

    public RVPIDSConfigScreen(BlockPos pos1, BlockPos pos2, int maxArrivals) {
        super(new TextComponent(""));
        this.pos1 = pos1;
        this.pos2 = pos2;
        messages = new String[maxArrivals];
        for (int i = 0; i < maxArrivals; i++) {
            messages[i] = "";
        }
        hideArrival = new boolean[maxArrivals];

        textFieldMessages = new WidgetBetterTextField[maxArrivals];
        for (int i = 0; i < maxArrivals; i++) {
            textFieldMessages[i] = new WidgetBetterTextField(null, "", MAX_MESSAGE_LENGTH);
        }

        buttonsHideArrival = new WidgetBetterCheckbox[maxArrivals];
        buttonsHidePlatformNumbers = new WidgetBetterCheckbox(0, 0, 0, SQUARE_SIZE, hidePlatformNumberText, checked -> {
        });
        for (int i = 0; i < maxArrivals; i++) {
            buttonsHideArrival[i] = new WidgetBetterCheckbox(0, 0, 0, SQUARE_SIZE, hideArrivalText, checked -> {
            });
        }

        final Level world = Minecraft.getInstance().level;
        if (world != null) {
            final BlockEntity entity = world.getBlockEntity(pos1);
            if (entity instanceof PIDSRVBase.TileEntityBlockPIDSBase) {
                for (int i = 0; i < maxArrivals; i++) {
                    messages[i] = ((PIDSRVBase.TileEntityBlockPIDSBase) entity).getMessage(i);
                    hideArrival[i] = ((PIDSRVBase.TileEntityBlockPIDSBase) entity).getHideArrival(i);
                }
                hidePlatformNumber = ((PIDSRVBase.TileEntityBlockPIDSBase) entity).getHidePlatformNumber();
            }
        }
    }

    @Override
    protected void init() {
        super.init();
        final int textWidth = font.width(hideArrivalText) + SQUARE_SIZE + TEXT_PADDING * 2;

        for (int i = 0; i < textFieldMessages.length; i++) {
            final WidgetBetterTextField textFieldMessage = textFieldMessages[i];
            IDrawing.setPositionAndWidth(textFieldMessage, SQUARE_SIZE + TEXT_FIELD_PADDING / 2, SQUARE_SIZE * 2 + TEXT_FIELD_PADDING / 2 + (SQUARE_SIZE + TEXT_FIELD_PADDING) * i, width - SQUARE_SIZE * 2 - TEXT_FIELD_PADDING - textWidth);
            textFieldMessage.setValue(messages[i]);
            addDrawableChild(textFieldMessage);

            final WidgetBetterCheckbox buttonHideArrival = buttonsHideArrival[i];
            IDrawing.setPositionAndWidth(buttonHideArrival, width - SQUARE_SIZE - textWidth + TEXT_PADDING, SQUARE_SIZE * 2 + TEXT_FIELD_PADDING / 2 + (SQUARE_SIZE + TEXT_FIELD_PADDING) * i, textWidth);
            buttonHideArrival.setChecked(hideArrival[i]);
            addDrawableChild(buttonHideArrival);
        }

        IDrawing.setPositionAndWidth(buttonsHidePlatformNumbers, SQUARE_SIZE + TEXT_FIELD_PADDING / 2, SQUARE_SIZE * 2 + TEXT_FIELD_PADDING / 2 + (SQUARE_SIZE + TEXT_FIELD_PADDING) * textFieldMessages.length, textWidth);
        buttonsHidePlatformNumbers.setChecked(hidePlatformNumber);
        addDrawableChild(buttonsHidePlatformNumbers);
    }

    @Override
    public void tick() {
        for (final WidgetBetterTextField textFieldMessage : textFieldMessages) {
            textFieldMessage.tick();
        }
    }

    @Override
    public void onClose() {
        for (int i = 0; i < textFieldMessages.length; i++) {
            messages[i] = textFieldMessages[i].getValue();
            hideArrival[i] = buttonsHideArrival[i].selected();
        }
        hidePlatformNumber = buttonsHidePlatformNumbers.selected();
        Client.sendRVPIDSConfigC2S(pos1, pos2, messages, hideArrival, hidePlatformNumber);
        super.onClose();
    }

    @Override
    public void render(PoseStack matrices, int mouseX, int mouseY, float delta) {
        try {
            renderBackground(matrices);
            font.draw(matrices, messageText, SQUARE_SIZE + TEXT_PADDING, SQUARE_SIZE + TEXT_PADDING, ARGB_WHITE);
            super.render(matrices, mouseX, mouseY, delta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
