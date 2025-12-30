package net.marios271.quick_commands.screen;

import net.marios271.quick_commands.handler.KeyInputHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import org.jspecify.annotations.NonNull;

public class QuickCommandsScreen extends Screen {
    public QuickCommandsScreen() {
        super(Component.translatable("gui_title.quick_commands.commands"));
    }

    @Override
    protected void init() {
        // --- TIME OPTIONS ---
        Component ComponentTimeComponent = Component.translatable("text.quick_commands.set_time");
        StringWidget text_time = new StringWidget(ComponentTimeComponent, font);
        text_time.setPosition(width / 2 - font.width(ComponentTimeComponent) / 2 - 110, height / 2 - 110);

        Button button_time_day = Button.builder(Component.translatable("button.quick_commands.time_day"), button -> execute("time set day"))
                .bounds(width / 2 - 200, height / 2 - 95, 180, 20)
                .tooltip(Tooltip.create(Component.translatable("button.tooltip.quick_commands.time_day")))
                .build();
        Button button_time_noon = Button.builder(Component.translatable("button.quick_commands.time_noon"), button -> execute("time set noon"))
                .bounds(width / 2 - 200, height / 2 - 70, 180, 20)
                .tooltip(Tooltip.create(Component.translatable("button.tooltip.quick_commands.time_noon")))
                .build();
        Button button_time_night = Button.builder(Component.translatable("button.quick_commands.time_night"), button -> execute("time set night"))
                .bounds(width / 2 - 200, height / 2 - 45, 180, 20)
                .tooltip(Tooltip.create(Component.translatable("button.tooltip.quick_commands.time_night")))
                .build();
        Button button_time_midnight = Button.builder(Component.translatable("button.quick_commands.time_midnight"), button -> execute("time set midnight"))
                .bounds(width / 2 - 200, height / 2 - 20, 180, 20)
                .tooltip(Tooltip.create(Component.translatable("button.tooltip.quick_commands.time_midnight")))
                .build();

        addRenderableWidget(text_time);
        addRenderableWidget(button_time_day);
        addRenderableWidget(button_time_noon);
        addRenderableWidget(button_time_night);
        addRenderableWidget(button_time_midnight);

        // --- WEATHER OPTIONS ---
        Component ComponentWeatherComponent = Component.translatable("text.quick_commands.set_weather");
        StringWidget text_weather = new StringWidget(ComponentWeatherComponent, font);
        text_weather.setPosition(width / 2 - font.width(ComponentWeatherComponent) / 2 - 110, height / 2 + 20);

        Button button_weather_clear = Button.builder(Component.translatable("button.quick_commands.weather_clear"), button -> execute("weather clear"))
                .bounds(width / 2 - 200, height / 2 + 35, 180, 20)
                .tooltip(Tooltip.create(Component.translatable("button.tooltip.quick_commands.weather_clear")))
                .build();
        Button button_weather_rain = Button.builder(Component.translatable("button.quick_commands.weather_rain"), button -> execute("weather rain"))
                .bounds(width / 2 - 200, height / 2 + 60, 180, 20)
                .tooltip(Tooltip.create(Component.translatable("button.tooltip.quick_commands.weather_rain")))
                .build();
        Button button_weather_thunder = Button.builder(Component.translatable("button.quick_commands.weather_thunder"), button -> execute("weather thunder"))
                .bounds(width / 2 - 200, height / 2 + 85, 180, 20)
                .tooltip(Tooltip.create(Component.translatable("button.tooltip.quick_commands.weather_thunder")))
                .build();

        addRenderableWidget(text_weather);
        addRenderableWidget(button_weather_clear);
        addRenderableWidget(button_weather_rain);
        addRenderableWidget(button_weather_thunder);

        // --- EFFECT OPTIONS ---
        Component ComponentEffectsComponent = Component.translatable("text.quick_commands.set_effects");
        StringWidget text_effects = new StringWidget(ComponentEffectsComponent, font);
        text_effects.setPosition(width / 2 - font.width(ComponentEffectsComponent) / 2 + 94, height / 2 - 110);
        addRenderableWidget(text_effects);

        addEffectWidget("resistance", 95);
        addEffectWidget("regeneration", 70);
        addEffectWidget("speed", 45);
        addEffectWidget("jump_boost", 20);

        // --- SCALE SETTER ---
        Component ComponentItemsComponent = Component.translatable("text.quick_commands.set_scale");
        StringWidget text_items = new StringWidget(ComponentItemsComponent, font);
        text_items.setPosition(width / 2 - font.width(ComponentItemsComponent) / 2 + 94, height / 2 + 20);
        addRenderableWidget(text_items);

        EditBox Textfield_scale = new EditBox(font, width / 2 + 12, height / 2 + 35, 180, 20, Component.literal(""));
        Textfield_scale.setTooltip(Tooltip.create(Component.translatable("textfield.tooltip.quick_commands.scale")));
        Textfield_scale.setFilter((Component) -> Component.matches("([0-9]*\\.?[0-9]*)?"));

        Button button_set_scale = Button.builder(Component.translatable("button.quick_commands.set_scale"), button -> {
                    String input = Textfield_scale.getValue();
                    if (!input.isEmpty()) {
                        try {
                            float scale = Float.parseFloat(input);

                            execute("attribute @s minecraft:scale base set " + scale);
                            execute("attribute @s minecraft:movement_speed base set " + (0.1f * scale));
                        } catch (NumberFormatException e) {
                            sendErrorMsg(Component.translatable("text.quick_commands.status.invalid_scale_number"));
                        }
                    } else {
                        sendErrorMsg(Component.translatable("text.quick_commands.status.no_scale_value"));
                    }
                }).bounds(width / 2 + 12, height / 2 + 60, 88, 20)
                .tooltip(Tooltip.create(Component.translatable("button.tooltip.quick_commands.set_scale")))
                .build();

        Button button_reset_scale = Button.builder(Component.translatable("button.quick_commands.reset_scale"), button -> {
                    execute("attribute @s minecraft:scale base reset");
                    execute("attribute @s minecraft:movement_speed base reset");
                })
                .bounds(width / 2 + 104, height / 2 + 60, 88, 20)
                .tooltip(Tooltip.create(Component.translatable("button.tooltip.quick_commands.reset_scale")))
                .build();

        addRenderableWidget(Textfield_scale);
        addRenderableWidget(button_set_scale);
        addRenderableWidget(button_reset_scale);
    }

    private void addEffectWidget(String effectName, int yOffset) {
        StringWidget text_effect = new StringWidget(Component.translatable("text.quick_commands.effects." + effectName), font);
        text_effect.setPosition(width / 2 + 34, height / 2 - yOffset + 7);

        EditBox ComponentField = new EditBox(font, width / 2, height / 2 - yOffset, 30, 20, Component.literal(""));
        ComponentField.setFilter((Component) -> Component.matches("[0-9]*"));
        ComponentField.setMaxLength(3);
        ComponentField.setTooltip(Tooltip.create(Component.translatable("textfield.tooltip.quick_commands.effect_strength")));

        Button buttonGive = Button.builder(Component.translatable("button.quick_commands.give"), button -> {
                    try {
                        execute("effect give @s " + effectName + " infinite " + Integer.parseInt(ComponentField.getValue()) + " true");
                    } catch (NumberFormatException e) {
                        sendErrorMsg(Component.translatable("text.quick_commands.status.no_effect_value"));
                    }
                }).bounds(width / 2 + 108, height / 2 - yOffset, 50, 20)
                .tooltip(Tooltip.create(Component.translatable("button.tooltip.quick_commands.effect_give")))
                .build();

        Button buttonClear = Button.builder(Component.translatable("button.quick_commands.clear"), button -> execute("effect clear @s " + effectName))
                .bounds(width / 2 + 160, height / 2 - yOffset, 50, 20)
                .tooltip(Tooltip.create(Component.translatable("button.tooltip.quick_commands.effect_clear")))
                .build();

        addRenderableWidget(text_effect);
        addRenderableWidget(ComponentField);
        addRenderableWidget(buttonGive);
        addRenderableWidget(buttonClear);
    }

    @Override
    public boolean keyPressed(@NonNull KeyEvent keyEvent) {
        if (KeyInputHandler.openGuiKey.matches(keyEvent)) {
            this.onClose();
            return true;
        }
        return super.keyPressed(keyEvent);
    }

    private void execute(String command) {
        Minecraft client = Minecraft.getInstance();
        if (client.player == null)
            return;

        client.player.connection.sendCommand(command);
        client.setScreen(null);
    }

    private void sendErrorMsg(MutableComponent text) {
        Minecraft client = Minecraft.getInstance();
        if (client.player == null)
            return;

        client.player.displayClientMessage(text, false);
        client.setScreen(null);
    }
}
