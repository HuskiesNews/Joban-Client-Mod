package com.jsblock.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

public class TicketMachineScreen extends mtr.screen.TicketMachineScreen {

	public TicketMachineScreen(int balance) {
		super(balance);
	}

	@Override
	public void render(PoseStack matrices, int mouseX, int mouseY, float delta) {
		/* Call the render method from the class we extend (mtr.gui.TicketMachineScreen). This will bring up the default MTR Ticket Screen */
		super.render(matrices, mouseX, mouseY, delta);

		/* And then we draw our own stuff on top of the MTR Ticket Screen */
		final Component ATMText = new TranslatableComponent("gui.jsblock.ticket_machine_atm_loc");
		font.draw(matrices, ATMText, width / 2F - font.width(ATMText) / 2F, TEXT_PADDING, ARGB_WHITE);
	}
}
