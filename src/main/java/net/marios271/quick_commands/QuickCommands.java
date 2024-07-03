package net.marios271.quick_commands;

import net.fabricmc.api.ClientModInitializer;
import net.marios271.quick_commands.event.KeyInputHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuickCommands implements ClientModInitializer {
    public static final String MOD_ID = "quick_commands";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        KeyInputHandler.register();

        LOGGER.info("Initialized " + MOD_ID);
    }
}
