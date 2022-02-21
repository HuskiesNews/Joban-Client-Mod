package com.jsblock;

import net.fabricmc.api.ClientModInitializer;

public class JobanFabricClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		JobanClient.init();
	}
}
