package net.marios271.quick_commands;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.marios271.quick_commands.config.ModConfigs;
import net.marios271.quick_commands.event.KeyInputHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuickCommands implements ClientModInitializer {
    public static final String MOD_ID = "quick_commands";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        ModConfigs.registerConfigs();
        KeyInputHandler.register();

        LOGGER.info("Initialized " + MOD_ID);

        ClientPlayConnectionEvents.JOIN.register((arg1, arg2, arg3) -> {
            ClientPlayerEntity player = MinecraftClient.getInstance().player;
            assert player != null;

            if (!ModConfigs.REMEMBER_NV){ ModConfigs.NV_STATUS = false; }
            if (ModConfigs.AUTO_NV){ ModConfigs.NV_STATUS = true; }

            if (ModConfigs.NV_STATUS){ player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, -1)); }
        });
    }
}
