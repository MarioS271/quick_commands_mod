package net.marios271.quick_commands.event;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.marios271.quick_commands.screen.QuickCommandsScreen;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final KeyMapping.Category QUICK_COMMANDS_CATEGORY = KeyMapping.Category.register(Identifier.parse("key.category.quick_commands"));
    public static final String KEY_OPEN_GUI = "key.quick_commands.open_gui";

    public static KeyMapping openGuiKey;

    public static void registerKeyInputs(){
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (openGuiKey.consumeClick()) {
                client.setScreen(new QuickCommandsScreen());
            }
        });
    }

    public static void register(){
        openGuiKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                KEY_OPEN_GUI,
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_F8,
                QUICK_COMMANDS_CATEGORY
        ));

        registerKeyInputs();
    }
}