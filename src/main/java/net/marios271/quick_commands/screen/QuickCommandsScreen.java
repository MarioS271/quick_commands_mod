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

        // TIME OPTIONS
        TextWidget text_time = new TextWidget(Text.translatable("text.quick_commands.set_time"), textRenderer);
        text_time.setX(width / 2 - 120);
        text_time.setY(10);
        ButtonWidget button_time_day = ButtonWidget.builder(Text.translatable("button.quick_commands.time_day"), button -> {
                    execute("time set day");
                })
                .dimensions(width / 2 - 200, 25, 180, 20)
                .tooltip(Tooltip.of(Text.translatable("button.tooltip.quick_commands.time_day")))
                .build();
        ButtonWidget button_time_noon = ButtonWidget.builder(Text.translatable("button.quick_commands.time_noon"), button -> {
                    execute("time set noon");
                })
                .dimensions(width / 2 - 200, 50, 180, 20)
                .tooltip(Tooltip.of(Text.translatable("button.tooltip.quick_commands.time_noon")))
                .build();
        ButtonWidget button_time_night = ButtonWidget.builder(Text.translatable("button.quick_commands.time_night"), button -> {
                    execute("time set night");
                })
                .dimensions(width / 2 - 200, 75, 180, 20)
                .tooltip(Tooltip.of(Text.translatable("button.tooltip.quick_commands.time_night")))
                .build();
        ButtonWidget button_time_midnight = ButtonWidget.builder(Text.translatable("button.quick_commands.time_midnight"), button -> {
                    execute("time set midnight");
                })
                .dimensions(width / 2 - 200, 100, 180, 20)
                .tooltip(Tooltip.of(Text.translatable("button.tooltip.quick_commands.time_midnight")))
                .build();

        addDrawableChild(text_time);
        addDrawableChild(button_time_day);
        addDrawableChild(button_time_noon);
        addDrawableChild(button_time_night);
        addDrawableChild(button_time_midnight);


        // WEATHER OPTIONS
        TextWidget text_weather = new TextWidget(Text.translatable("text.quick_commands.set_weather"), textRenderer);
        text_weather.setX(width / 2 - 128);
        text_weather.setY(140);
        ButtonWidget button_weather_clear = ButtonWidget.builder(Text.translatable("button.quick_commands.weather_clear"), button -> {
                    execute("weather clear");
                })
                .dimensions(width / 2 - 200, 155, 180, 20)
                .tooltip(Tooltip.of(Text.translatable("button.tooltip.quick_commands.weather_clear")))
                .build();
        ButtonWidget button_weather_rain = ButtonWidget.builder(Text.translatable("button.quick_commands.weather_rain"), button -> {
                    execute("weather rain");
                })
                .dimensions(width / 2 - 200, 180, 180, 20)
                .tooltip(Tooltip.of(Text.translatable("button.tooltip.quick_commands.weather_rain")))
                .build();
        ButtonWidget button_weather_thunder = ButtonWidget.builder(Text.translatable("button.quick_commands.weather_thunder"), button -> {
                    execute("weather thunder");
                })
                .dimensions(width / 2 - 200, 205, 180, 20)
                .tooltip(Tooltip.of(Text.translatable("button.tooltip.quick_commands.weather_thunder")))
                .build();

        addDrawableChild(text_weather);
        addDrawableChild(button_weather_clear);
        addDrawableChild(button_weather_rain);
        addDrawableChild(button_weather_thunder);
    }

    private void execute(String command){
        assert MinecraftClient.getInstance().player != null;
        MinecraftClient.getInstance().player.networkHandler.sendChatCommand(command);
    }
}
