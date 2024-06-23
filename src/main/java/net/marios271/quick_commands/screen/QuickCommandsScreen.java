package net.marios271.quick_commands.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class QuickCommandsScreen extends Screen {
    public QuickCommandsScreen() {
        super(Text.of("QuickCommands"));
    }

    @Override
    protected void init() {
        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;

        TextWidget text_time = new TextWidget(Text.literal("test"), textRenderer);
        ButtonWidget button_day = ButtonWidget.builder(Text.translatable("button.quick_commands.time_day"), button -> {
                    execute("time set day");
                })
                .dimensions(width / 2 - 200, 20, 150, 20)
                .tooltip(Tooltip.of(Text.translatable("button.tooltip.quick_commands.time_day")))
                .build();
        ButtonWidget button_noon = ButtonWidget.builder(Text.translatable("button.quick_commands.time_noon"), button -> {
                    execute("time set noon");
                })
                .dimensions(width / 2 - 200, 45, 150, 20)
                .tooltip(Tooltip.of(Text.translatable("button.tooltip.quick_commands.time_noon")))
                .build();
        ButtonWidget button_night = ButtonWidget.builder(Text.translatable("button.quick_commands.time_night"), button -> {
                    execute("time set night");
                })
                .dimensions(width / 2 - 200, 70, 150, 20)
                .tooltip(Tooltip.of(Text.translatable("button.tooltip.quick_commands.time_night")))
                .build();
        ButtonWidget button_midnight = ButtonWidget.builder(Text.translatable("button.quick_commands.time_midnight"), button -> {
                    execute("time set midnight");
                })
                .dimensions(width / 2 - 200, 95, 150, 20)
                .tooltip(Tooltip.of(Text.translatable("button.tooltip.quick_commands.time_midnight")))
                .build();

        addDrawableChild(text_time);
        addDrawableChild(button_day);
        addDrawableChild(button_noon);
        addDrawableChild(button_night);
        addDrawableChild(button_midnight);
    }

    private void execute(String command){
        assert MinecraftClient.getInstance().player != null;
        MinecraftClient.getInstance().player.networkHandler.sendChatCommand(command);
    }
}
