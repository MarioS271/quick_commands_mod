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
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;
import java.util.Objects;

@Environment(EnvType.CLIENT)
public class QuickCommandsScreen extends Screen {
    public QuickCommandsScreen() {
        super(Text.of("QuickCommands"));
    }

    @Override
    protected void init() {
        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        assert player != null;

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
        text_weather.setPosition(width / 2 - 126, 140);
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
        TextWidget text_effects = new TextWidget(Text.translatable("text.quick_commands.set_effects"), textRenderer).alignCenter();
        text_effects.setPosition(width / 2 + 100, 10);
        addDrawableChild(text_effects);

        // EFFECT / RESISTANCE
        TextWidget text_effect_resistance = new TextWidget(Text.translatable("text.quick_commands.effects.resistance"), textRenderer);
        text_effect_resistance.setPosition(width / 2 + 37, 31);
        TextFieldWidget textfield_resistance = new TextFieldWidget(textRenderer, width / 2 + 1, 25, 30, 20, Text.literal(""));
        textfield_resistance.setTextPredicate((text) -> text.matches("[0-9]*"));
        textfield_resistance.setMaxLength(3);
        textfield_resistance.setTooltip(Tooltip.of(Text.translatable("textfield.tooltip.quick_commands.effect_strength")));
        ButtonWidget button_effect_resistance_give = ButtonWidget.builder(Text.translatable("button.quick_commands.give"), button -> {
                    try { execute("effect give @s resistance infinite " + Integer.parseInt(textfield_resistance.getText()) + " true"); }
                    catch (NumberFormatException e) { player.sendMessage(Text.translatable("text.quick_commands.status.no_effect_value")); }
                })
                .dimensions(width / 2 + 108, 25, 50, 20)
                .tooltip(Tooltip.of(Text.translatable("button.tooltip.quick_commands.effect_give")))
                .build();
        ButtonWidget button_effect_resistance_clear = ButtonWidget.builder(Text.translatable("button.quick_commands.clear"), button -> {
                    execute("effect clear @s resistance");
                })
                .dimensions(width / 2 + 160, 25, 50, 20)
                .tooltip(Tooltip.of(Text.translatable("button.tooltip.quick_commands.effect_clear")))
                .build();
        addDrawableChild(text_effect_resistance);
        addDrawableChild(textfield_resistance);
        addDrawableChild(button_effect_resistance_give);
        addDrawableChild(button_effect_resistance_clear);

        // EFFECT / REGENERATION
        TextWidget text_effect_regeneration = new TextWidget(Text.translatable("text.quick_commands.effects.regeneration"), textRenderer);
        text_effect_regeneration.setPosition(width / 2 + 37, 56);
        TextFieldWidget textfield_regeneration = new TextFieldWidget(textRenderer, width / 2 + 1, 50, 30, 20, Text.literal(""));
        textfield_regeneration.setTextPredicate((text) -> text.matches("[0-9]*"));
        textfield_regeneration.setMaxLength(3);
        textfield_regeneration.setTooltip(Tooltip.of(Text.translatable("textfield.tooltip.quick_commands.effect_strength")));
        ButtonWidget button_effect_regeneration_give = ButtonWidget.builder(Text.translatable("button.quick_commands.give"), button -> {
                    try { execute("effect give @s regeneration infinite " + Integer.parseInt(textfield_regeneration.getText()) + " true"); }
                    catch (NumberFormatException e) { player.sendMessage(Text.translatable("text.quick_commands.status.no_effect_value")); }
                })
                .dimensions(width / 2 + 108, 50, 50, 20)
                .tooltip(Tooltip.of(Text.translatable("button.tooltip.quick_commands.effect_give")))
                .build();
        ButtonWidget button_effect_regeneration_clear = ButtonWidget.builder(Text.translatable("button.quick_commands.clear"), button -> {
                    execute("effect clear @s regeneration");
                })
                .dimensions(width / 2 + 160, 50, 50, 20)
                .tooltip(Tooltip.of(Text.translatable("button.tooltip.quick_commands.effect_clear")))
                .build();
        addDrawableChild(text_effect_regeneration);
        addDrawableChild(textfield_regeneration);
        addDrawableChild(button_effect_regeneration_give);
        addDrawableChild(button_effect_regeneration_clear);

        // EFFECT / SPEED
        TextWidget text_effect_speed = new TextWidget(Text.translatable("text.quick_commands.effects.speed"), textRenderer);
        text_effect_speed.setPosition(width / 2 + 37, 81);
        TextFieldWidget textfield_speed = new TextFieldWidget(textRenderer, width / 2 + 1, 75, 30, 20, Text.literal(""));
        textfield_speed.setTextPredicate((text) -> text.matches("[0-9]*"));
        textfield_speed.setMaxLength(3);
        textfield_speed.setTooltip(Tooltip.of(Text.translatable("textfield.tooltip.quick_commands.effect_strength")));
        ButtonWidget button_effect_speed_give = ButtonWidget.builder(Text.translatable("button.quick_commands.give"), button -> {
                    try { execute("effect give @s speed infinite " + Integer.parseInt(textfield_speed.getText()) + " true"); }
                    catch (NumberFormatException e) { player.sendMessage(Text.translatable("text.quick_commands.status.no_effect_value")); }
                })
                .dimensions(width / 2 + 108, 75, 50, 20)
                .tooltip(Tooltip.of(Text.translatable("button.tooltip.quick_commands.effect_give")))
                .build();
        ButtonWidget button_effect_speed_clear = ButtonWidget.builder(Text.translatable("button.quick_commands.clear"), button -> {
                    execute("effect clear @s speed");
                })
                .dimensions(width / 2 + 160, 75, 50, 20)
                .tooltip(Tooltip.of(Text.translatable("button.tooltip.quick_commands.effect_clear")))
                .build();
        addDrawableChild(text_effect_speed);
        addDrawableChild(textfield_speed);
        addDrawableChild(button_effect_speed_give);
        addDrawableChild(button_effect_speed_clear);

        // EFFECT / JUMP BOOST
        TextWidget text_effect_jump_boost = new TextWidget(Text.translatable("text.quick_commands.effects.jump_boost"), textRenderer);
        text_effect_jump_boost.setPosition(width / 2 + 37, 106);
        TextFieldWidget textfield_jump_boost = new TextFieldWidget(textRenderer, width / 2 + 1, 100, 30, 20, Text.literal(""));
        textfield_jump_boost.setTextPredicate((text) -> text.matches("[0-9]*"));
        textfield_jump_boost.setMaxLength(3);
        textfield_jump_boost.setTooltip(Tooltip.of(Text.translatable("textfield.tooltip.quick_commands.effect_strength")));
        ButtonWidget button_effect_jump_boost_give = ButtonWidget.builder(Text.translatable("button.quick_commands.give"), button -> {
                    try { execute("effect give @s jump_boost infinite " + Integer.parseInt(textfield_jump_boost.getText()) + " true"); }
                    catch (NumberFormatException e) { player.sendMessage(Text.translatable("text.quick_commands.status.no_effect_value")); }
                })
                .dimensions(width / 2 + 108, 100, 50, 20)
                .tooltip(Tooltip.of(Text.translatable("button.tooltip.quick_commands.effect_give")))
                .build();
        ButtonWidget button_effect_jump_boost_clear = ButtonWidget.builder(Text.translatable("button.quick_commands.clear"), button -> {
                    execute("effect clear @s jump_boost");
                })
                .dimensions(width / 2 + 160, 100, 50, 20)
                .tooltip(Tooltip.of(Text.translatable("button.tooltip.quick_commands.effect_clear")))
                .build();
        addDrawableChild(text_effect_jump_boost);
        addDrawableChild(textfield_jump_boost);
        addDrawableChild(button_effect_jump_boost_give);
        addDrawableChild(button_effect_jump_boost_clear);


        // ITEMS
        TextWidget text_items = new TextWidget(Text.translatable("text.quick_commands.set_items"), textRenderer).alignCenter();
        text_items.setPosition(width / 2 + 100, 140);
        TextFieldWidget textfield_item = new TextFieldWidget(textRenderer, width / 2 + 12, 155, 145, 20, Text.literal(""));
        textfield_item.setTooltip(Tooltip.of(Text.translatable("textfield.tooltip.quick_commands.item")));
        TextFieldWidget textfield_item_qty = new TextFieldWidget(textRenderer, width / 2 + 162, 155, 30, 20, Text.literal("1"));
        textfield_item_qty.setTextPredicate((text) -> text.matches("[0-9]*"));
        textfield_item_qty.setTooltip(Tooltip.of(Text.translatable("textfield.tooltip.quick_commands.item_qty")));
        ButtonWidget button_item_give = ButtonWidget.builder(Text.translatable("button.quick_commands.give"), button -> {
                    if (!Objects.equals(textfield_item.getText(), "")) {
                        try { execute("give @s " + textfield_item.getText() + " " + Integer.parseInt(textfield_item_qty.getText())); }
                        catch (NumberFormatException e) { execute("give @s " + textfield_item.getText() + " 1"); }
                    } else {
                        player.sendMessage(Text.translatable("text.quick_commands.status.no_item"));
                    }
                })
                .dimensions(width / 2 + 12, 180, 88, 20)
                .tooltip(Tooltip.of(Text.translatable("button.tooltip.quick_commands.item_give")))
                .build();
        ButtonWidget button_items_clear = ButtonWidget.builder(Text.translatable("button.quick_commands.clear"), button -> {
                    MinecraftClient.getInstance().setScreen(new InventoryClearConfirmScreen());
                })
                .dimensions(width / 2 + 104, 180, 88, 20)
                .tooltip(Tooltip.of(Text.translatable("button.tooltip.quick_commands.item_clear")))
                .build();

        addDrawableChild(text_items);
        addDrawableChild(textfield_item);
        addDrawableChild(textfield_item_qty);
        addDrawableChild(button_item_give);
        addDrawableChild(button_items_clear);
    }

    private void execute(String command){
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        assert player != null;
        player.networkHandler.sendChatCommand(command);
        MinecraftClient.getInstance().setScreen(null);
    }
}
