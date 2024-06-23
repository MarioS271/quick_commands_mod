package net.marios271.quick_commands;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuickCommands implements ModInitializer {
	public static final String MOD_ID = "quick_commands";
	public static final Logger LOGGER = LoggerFactory.getLogger("quick_commands");

	@Override
	public void onInitialize() {
		LOGGER.info("initialized " + MOD_ID);
	}
}