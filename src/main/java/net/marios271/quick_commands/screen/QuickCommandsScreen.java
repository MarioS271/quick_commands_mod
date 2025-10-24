package net.marios271.quick_commands.screen;

import net.marios271.quick_commands.event.KeyInputHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

public class QuickCommandsScreen extends Screen {
    public QuickCommandsScreen() {
        super(Text.translatable("gui_title.quick_commands.commands"));
    }

    @Override
    protected void init() {
        MinecraftClient client = MinecraftClient.getInstance();

        // --- TIME OPTIONS ---
        Text textTimeText = Text.translatable("text.quick_commands.set_time");
        TextWidget text_time = new TextWidget(textTimeText, textRenderer);
        text_time.setPosition(width / 2 - textRenderer.getWidth(textTimeText) / 2 - 110, height / 2 - 110);

        ButtonWidget button_time_day = ButtonWidget.builder(Text.translatable("button.quick_commands.time_day"), button -> execute("time set day"))
                .dimensions(width / 2 - 200, height / 2 - 95, 180, 20)
                .tooltip(Tooltip.of(Text.translatable("button.tooltip.quick_commands.time_day")))
                .build();
        ButtonWidget button_time_noon = ButtonWidget.builder(Text.translatable("button.quick_commands.time_noon"), button -> execute("time set noon"))
                .dimensions(width / 2 - 200, height / 2 - 70, 180, 20)
                .tooltip(Tooltip.of(Text.translatable("button.tooltip.quick_commands.time_noon")))
                .build();
        ButtonWidget button_time_night = ButtonWidget.builder(Text.translatable("button.quick_commands.time_night"), button -> execute("time set night"))
                .dimensions(width / 2 - 200, height / 2 - 45, 180, 20)
                .tooltip(Tooltip.of(Text.translatable("button.tooltip.quick_commands.time_night")))
                .build();
        ButtonWidget button_time_midnight = ButtonWidget.builder(Text.translatable("button.quick_commands.time_midnight"), button -> execute("time set midnight"))
                .dimensions(width / 2 - 200, height / 2 - 20, 180, 20)
                .tooltip(Tooltip.of(Text.translatable("button.tooltip.quick_commands.time_midnight")))
                .build();

        addDrawableChild(text_time);
        addDrawableChild(button_time_day);
        addDrawableChild(button_time_noon);
        addDrawableChild(button_time_night);
        addDrawableChild(button_time_midnight);

        // --- WEATHER OPTIONS ---
        Text textWeatherText = Text.translatable("text.quick_commands.set_weather");
        TextWidget text_weather = new TextWidget(textWeatherText, textRenderer);
        text_weather.setPosition(width / 2 - textRenderer.getWidth(textWeatherText) / 2 - 110, height / 2 + 20);

        ButtonWidget button_weather_clear = ButtonWidget.builder(Text.translatable("button.quick_commands.weather_clear"), button -> execute("weather clear"))
                .dimensions(width / 2 - 200, height / 2 + 35, 180, 20)
                .tooltip(Tooltip.of(Text.translatable("button.tooltip.quick_commands.weather_clear")))
                .build();
        ButtonWidget button_weather_rain = ButtonWidget.builder(Text.translatable("button.quick_commands.weather_rain"), button -> execute("weather rain"))
                .dimensions(width / 2 - 200, height / 2 + 60, 180, 20)
                .tooltip(Tooltip.of(Text.translatable("button.tooltip.quick_commands.weather_rain")))
                .build();
        ButtonWidget button_weather_thunder = ButtonWidget.builder(Text.translatable("button.quick_commands.weather_thunder"), button -> execute("weather thunder"))
                .dimensions(width / 2 - 200, height / 2 + 85, 180, 20)
                .tooltip(Tooltip.of(Text.translatable("button.tooltip.quick_commands.weather_thunder")))
                .build();

        addDrawableChild(text_weather);
        addDrawableChild(button_weather_clear);
        addDrawableChild(button_weather_rain);
        addDrawableChild(button_weather_thunder);

        // --- EFFECT OPTIONS ---
        Text textEffectsText = Text.translatable("text.quick_commands.set_effects");
        TextWidget text_effects = new TextWidget(textEffectsText, textRenderer);
        text_effects.setPosition(width / 2 - textRenderer.getWidth(textEffectsText) / 2 + 94, height / 2 - 110);
        addDrawableChild(text_effects);

        addEffectWidget("resistance", 95);
        addEffectWidget("regeneration", 70);
        addEffectWidget("speed", 45);
        addEffectWidget("jump_boost", 20);

        // --- SCALE SETTER ---
        Text textItemsText = Text.translatable("text.quick_commands.set_scale");
        TextWidget text_items = new TextWidget(textItemsText, textRenderer);
        text_items.setPosition(width / 2 - textRenderer.getWidth(textItemsText) / 2 + 94, height / 2 + 20);
        addDrawableChild(text_items);

        TextFieldWidget textfield_scale = new TextFieldWidget(textRenderer, width / 2 + 12, height / 2 + 35, 180, 20, Text.literal(""));
        textfield_scale.setTooltip(Tooltip.of(Text.translatable("textfield.tooltip.quick_commands.scale")));
        textfield_scale.setTextPredicate((text) -> text.matches("([0-9]*\\.?[0-9]*)?"));

        ButtonWidget button_set_scale = ButtonWidget.builder(Text.translatable("button.quick_commands.set_scale"), button -> {
                    String input = textfield_scale.getText();
                    if (!input.isEmpty()) {
                        try {
                            float scale = Float.parseFloat(input);
                            execute("attribute @s minecraft:scale base set " + scale);
                        } catch (NumberFormatException e) {
                            sendErrorMsg(Text.translatable("text.quick_commands.status.invalid_scale_number"));
                        }
                    } else {
                        sendErrorMsg(Text.translatable("text.quick_commands.status.no_scale_value"));
                    }
                }).dimensions(width / 2 + 12, height / 2 + 60, 88, 20)
                .tooltip(Tooltip.of(Text.translatable("button.tooltip.quick_commands.set_scale")))
                .build();

        ButtonWidget button_reset_scale = ButtonWidget.builder(Text.translatable("button.quick_commands.reset_scale"), button -> execute("attribute @s minecraft:scale base set 1.0"))
                .dimensions(width / 2 + 104, height / 2 + 60, 88, 20)
                .tooltip(Tooltip.of(Text.translatable("button.tooltip.quick_commands.reset_scale")))
                .build();

        addDrawableChild(textfield_scale);
        addDrawableChild(button_set_scale);
        addDrawableChild(button_reset_scale);
    }

    private void addEffectWidget(String effectName, int yOffset) {
        TextWidget text_effect = new TextWidget(Text.translatable("text.quick_commands.effects." + effectName), textRenderer);
        text_effect.setPosition(width / 2 + 34, height / 2 - yOffset + 7);

        TextFieldWidget textField = new TextFieldWidget(textRenderer, width / 2, height / 2 - yOffset, 30, 20, Text.literal(""));
        textField.setTextPredicate((text) -> text.matches("[0-9]*"));
        textField.setMaxLength(3);
        textField.setTooltip(Tooltip.of(Text.translatable("textfield.tooltip.quick_commands.effect_strength")));

        ButtonWidget buttonGive = ButtonWidget.builder(Text.translatable("button.quick_commands.give"), button -> {
                    try {
                        execute("effect give @s " + effectName + " infinite " + Integer.parseInt(textField.getText()) + " true");
                    } catch (NumberFormatException e) {
                        sendErrorMsg(Text.translatable("text.quick_commands.status.no_effect_value"));
                    }
                }).dimensions(width / 2 + 108, height / 2 - yOffset, 50, 20)
                .tooltip(Tooltip.of(Text.translatable("button.tooltip.quick_commands.effect_give")))
                .build();

        ButtonWidget buttonClear = ButtonWidget.builder(Text.translatable("button.quick_commands.clear"), button -> execute("effect clear @s " + effectName))
                .dimensions(width / 2 + 160, height / 2 - yOffset, 50, 20)
                .tooltip(Tooltip.of(Text.translatable("button.tooltip.quick_commands.effect_clear")))
                .build();

        addDrawableChild(text_effect);
        addDrawableChild(textField);
        addDrawableChild(buttonGive);
        addDrawableChild(buttonClear);
    }

    @Override
    public boolean keyPressed(net.minecraft.client.input.KeyInput input) {
        if (KeyInputHandler.openGuiKey.matchesKey(input)) {
            MinecraftClient.getInstance().setScreen(null);
            return true;
        }
        return super.keyPressed(input);
    }


    private void execute(String command) {
        MinecraftClient.getInstance().player.networkHandler.sendChatCommand(command);
        MinecraftClient.getInstance().setScreen(null);
    }

    private void sendErrorMsg(MutableText text) {
        MinecraftClient.getInstance().player.sendMessage(text, false);
        MinecraftClient.getInstance().setScreen(null);
    }
}
