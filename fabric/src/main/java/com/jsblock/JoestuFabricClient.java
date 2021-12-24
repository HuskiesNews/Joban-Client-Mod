package com.jsblock;

import net.fabricmc.api.ClientModInitializer;

public class JoestuFabricClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		JoestuClient.init();
	}
}
