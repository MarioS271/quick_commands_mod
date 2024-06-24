package net.marios271.quick_commands.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
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
        text_time.setPosition(width / 2 - 120, 10);
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
        text_weather.setPosition(width / 2 - 128, 140);
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


        // EFFECT OPTIONS
        TextWidget text_effects = new TextWidget(Text.translatable("text.quick_commands.set_effects"), textRenderer);
        text_effects.setPosition(width / 2 + 100, 10);
        addDrawableChild(text_effects);

        // EFFECT / NIGHT VISION
        TextWidget text_effect_night_vision = new TextWidget(Text.translatable("text.quick_commands.effects.night_vision"), textRenderer);
        text_effect_night_vision.setPosition(width / 2, 32);
        ButtonWidget button_effect_night_vision_give = ButtonWidget.builder(Text.translatable("button.quick_commands.effect_give"), button -> {
                    execute("effect give @s night_vision infinite 1 true");
                })
                .dimensions(width / 2 + 95, 25, 50, 20)
                .tooltip(Tooltip.of(Text.translatable("button.tooltip.quick_commands.effect_give")))
                .build();
        ButtonWidget button_effect_night_vision_clear = ButtonWidget.builder(Text.translatable("button.quick_commands.effect_clear"), button -> {
                    execute("effect clear @s night_vision");
                })
                .dimensions(width / 2 + 160, 25, 50, 20)
                .tooltip(Tooltip.of(Text.translatable("button.tooltip.quick_commands.effect_clear")))
                .build();
        addDrawableChild(text_effect_night_vision);
        addDrawableChild(button_effect_night_vision_give);
        addDrawableChild(button_effect_night_vision_clear);

        // EFFECT / INVISIBILITY
        TextWidget text_effect_invisibility = new TextWidget(Text.translatable("text.quick_commands.effects.invisibility"), textRenderer);
        text_effect_invisibility.setPosition(width / 2, 56);
        ButtonWidget button_effect_invisibility_give = ButtonWidget.builder(Text.translatable("button.quick_commands.effect_give"), button -> {
                    execute("effect give @s invisibility infinite 1 true");
                })
                .dimensions(width / 2 + 95, 50, 50, 20)
                .tooltip(Tooltip.of(Text.translatable("button.tooltip.quick_commands.effect_give")))
                .build();
        ButtonWidget button_effect_invisibility_clear = ButtonWidget.builder(Text.translatable("button.quick_commands.effect_clear"), button -> {
                    execute("effect clear @s invisibility");
                })
                .dimensions(width / 2 + 160, 50, 50, 20)
                .tooltip(Tooltip.of(Text.translatable("button.tooltip.quick_commands.effect_clear")))
                .build();
        addDrawableChild(text_effect_invisibility);
        addDrawableChild(button_effect_invisibility_give);
        addDrawableChild(button_effect_invisibility_clear);

        // EFFECT / RESISTANCE
        TextWidget text_effect_resistance = new TextWidget(Text.translatable("text.quick_commands.effects.resistance"), textRenderer);
        text_effect_resistance.setPosition(width / 2, 81);
        TextFieldWidget textfield_resistance = new TextFieldWidget(textRenderer, width / 2 + 20, 75, 30, 20, Text.literal("5"));
        textfield_resistance.setTextPredicate((text) -> text.matches("[0-9]*"));
        textfield_resistance.setMaxLength(3);
        ButtonWidget button_effect_resistance_give = ButtonWidget.builder(Text.translatable("button.quick_commands.effect_give"), button -> {
                    try { execute("effect give @s resistance infinite " + Integer.parseInt(textfield_resistance.getText()) + " true"); }
                    catch (NumberFormatException e) { MinecraftClient.getInstance().player.sendMessage(Text.literal(e.toString())); }
                })
                .dimensions(width / 2 + 95, 75, 50, 20)
                .tooltip(Tooltip.of(Text.translatable("button.tooltip.quick_commands.effect_give")))
                .build();
        ButtonWidget button_effect_resistance_clear = ButtonWidget.builder(Text.translatable("button.quick_commands.effect_clear"), button -> {
                    execute("effect clear @s resistance");
                })
                .dimensions(width / 2 + 160, 75, 50, 20)
                .tooltip(Tooltip.of(Text.translatable("button.tooltip.quick_commands.effect_clear")))
                .build();
        addDrawableChild(text_effect_resistance);
        addDrawableChild(textfield_resistance);
        addDrawableChild(button_effect_resistance_give);
        addDrawableChild(button_effect_resistance_clear);
    }

    private void execute(String command){
        assert MinecraftClient.getInstance().player != null;
        MinecraftClient.getInstance().player.networkHandler.sendChatCommand(command);
    }
}
