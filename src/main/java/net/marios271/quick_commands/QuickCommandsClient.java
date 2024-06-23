package net.marios271.quick_commands;

import net.fabricmc.api.ClientModInitializer;
import net.marios271.quick_commands.event.KeyInputHandler;

public class QuickCommandsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyInputHandler.register();
    }
}
