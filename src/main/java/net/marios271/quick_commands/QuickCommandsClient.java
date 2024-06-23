package net.marios271.quick_commands;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.marios271.quick_commands.event.KeyInputHandler;

@Environment(EnvType.CLIENT)
public class QuickCommandsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyInputHandler.register();
    }
}
