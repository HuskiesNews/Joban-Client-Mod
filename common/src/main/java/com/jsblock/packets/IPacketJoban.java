package com.jsblock.packets;

import com.jsblock.Joban;
import net.minecraft.resources.ResourceLocation;

public interface IPacketJoban {
    ResourceLocation PACKET_OPEN_SOUND_LOOPER_SCREEN = new ResourceLocation(Joban.MOD_ID, "packet_open_sound_looper_screen");
    ResourceLocation PACKET_UPDATE_SOUND_LOOPER = new ResourceLocation(Joban.MOD_ID, "packet_update_sound_looper");
}
