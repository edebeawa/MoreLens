package edebe.more_lens.common.helper;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import vazkii.botania.common.Botania;
import vazkii.botania.common.item.lens.ItemLens;

import java.awt.*;
import java.util.List;
import java.util.Random;

public interface MoreLensHelper {
    static String localization(String translationKey) {
        return new TranslationTextComponent(translationKey).getString();
    }

    static ItemLens itemToItemLens(Item item) {
        return ((ItemLens)item);
    }

    static ItemLens itemToItemLens(ItemStack itemStack) {
        return itemToItemLens(itemStack.getItem());
    }

    static ItemStack getCompositeLens(ItemStack sourceLens) {
        return itemToItemLens(sourceLens).getCompositeLens(sourceLens);
    }
}
