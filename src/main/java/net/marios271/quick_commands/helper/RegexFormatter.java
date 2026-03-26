package net.marios271.quick_commands.helper;

import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class RegexFormatter {
    public static EditBox.TextFormatter of(String allowedPattern, Style invalidStyle) {
        Pattern pattern = Pattern.compile(allowedPattern);
        return (text, offset) -> {
            List<FormattedCharSequence> chars = new ArrayList<>();
            for (int i = 0; i < text.length(); i++) {
                String c = String.valueOf(text.charAt(i));
                Style style = pattern.matcher(c).matches() ? Style.EMPTY : invalidStyle;
                chars.add(FormattedCharSequence.forward(c, style));
            }
            return FormattedCharSequence.composite(chars);
        };
    }
}