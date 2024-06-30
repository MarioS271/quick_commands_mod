package net.marios271.quick_commands.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.text.Text;
import static net.minecraft.client.resource.language.I18n.translate;
import static net.minecraft.util.math.MathHelper.floor;

@Environment(EnvType.CLIENT)
public class InventoryClearConfirmScreen extends Screen {
    public InventoryClearConfirmScreen() { super(Text.of("Confirm Clear Inventory")); }

    @Override
    protected void init() {
        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;

        String text_str = translate("text.quick_commands.confirm_clear");
        int offset = floor((float) text_str.length() / 2);
        TextWidget text = new TextWidget(Text.translatable("text.quick_commands.confirm_clear"), textRenderer);
        text.setPosition(width / 2 - offset * 5, height / 2 - 10);
        ButtonWidget button_yes = ButtonWidget.builder(Text.translatable("gui.yes"), button -> {
                    assert MinecraftClient.getInstance().player != null;
                    MinecraftClient.getInstance().player.networkHandler.sendChatCommand("clear @s");
                    MinecraftClient.getInstance().setScreen(null);
                })
                .dimensions(width / 2 + 2, height / 2 + 20, 80, 20)
                .build();
        ButtonWidget button_no = ButtonWidget.builder(Text.translatable("gui.no"), button -> {
                    MinecraftClient.getInstance().setScreen(new QuickCommandsScreen());
                })
                .dimensions(width / 2 - 82, height / 2 + 20, 80, 20)
                .build();

        addDrawableChild(text);
        addDrawableChild(button_yes);
        addDrawableChild(button_no);
    }
}