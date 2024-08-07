package net.marios271.quick_commands.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.marios271.quick_commands.screen.QuickCommandsScreen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_QUICK_COMMANDS = "key.category.quick_commands";
    public static final String KEY_OPEN_GUI = "key.quick_commands.open_gui";

    public static KeyBinding openGuiKey;

    public static void registerKeyInputs(){
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (openGuiKey.wasPressed()) {
                client.setScreen(new QuickCommandsScreen());
            }
        });
    }

    public static void register(){
        openGuiKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_OPEN_GUI,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_F8,
                KEY_CATEGORY_QUICK_COMMANDS
        ));

        registerKeyInputs();
    }
}