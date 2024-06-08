package de.hysky.skyblocker.skyblock.item.slottext.adders;

import de.hysky.skyblocker.skyblock.item.slottext.SlotText;
import de.hysky.skyblocker.skyblock.item.slottext.SlotTextAdder;
import de.hysky.skyblocker.utils.RomanNumerals;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CollectionAdder extends SlotTextAdder {
    private static final Pattern COLLECTION = Pattern.compile("^[\\w ]+ (?<level>[IVXLCDM]+)$");

    public CollectionAdder() {
        super("^\\w+ Collections");
    }

    @Override
    public @NotNull List<SlotText> getText(Slot slot) {
        final ItemStack stack = slot.getStack();
        Matcher matcher = COLLECTION.matcher(stack.getName().getString());
        if (matcher.matches()) {
            int level = RomanNumerals.romanToDecimal(matcher.group("level"));
            return List.of(SlotText.bottomRight(Text.literal(String.valueOf(level)).formatted(Formatting.YELLOW)));
        }
        return List.of();
    }
}
