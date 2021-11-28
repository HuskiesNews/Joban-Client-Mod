package com.jsblock.gui;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class TicketMachineScreen extends mtr.gui.TicketMachineScreen {

    public TicketMachineScreen(int balance) {
        super(balance);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        /* Call the render method from the class we extend (mtr.gui.TicketMachineScreen). This will bring up the default MTR Ticket Screen */
        super.render(matrices, mouseX, mouseY, delta);

        /* And then we draw our own stuff on top of the MTR Ticket Screen */
        final Text ATMText = new TranslatableText("gui.jsblock.ticket_machine_atm_loc");
        textRenderer.draw(matrices, ATMText, (width / 2) - (textRenderer.getWidth(ATMText) / 2), TEXT_PADDING, ARGB_WHITE);
    }
}
