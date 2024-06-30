package net.marios271.quick_commands;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.marios271.quick_commands.config.ConfigModel;

import net.marios271.quick_commands.config.QuickCommandsConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Environment(EnvType.CLIENT)
public class QuickCommands implements ModInitializer {
	public static final String MOD_ID = "quick_commands";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("initialized " + MOD_ID);
	}

	public static final QuickCommandsConfig CONFIG = QuickCommandsConfig.createAndLoad();
}