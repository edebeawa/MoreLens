package edebe.more_lens.common.helper;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import vazkii.botania.common.item.lens.ItemLens;

import java.util.List;
import java.util.Random;

public interface MoreLensHelper {
    static String localization(String translationKey) {
        return new TranslationTextComponent(translationKey).getString();
    }

    static void tooltipCreative(List<ITextComponent> tooltip) {
        tooltip.add(new TranslationTextComponent("botaniamisc.creative").mergeStyle(TextFormatting.GRAY));
    }

    static int random(int bound) {
        return new Random().nextInt(bound + 1);
    }
    static ItemLens itemToItemLens(Item item) {
        return ((ItemLens)item);
    }

    static ItemLens itemToItemLens(ItemStack itemStack) {
        return itemToItemLens(itemStack.getItem());
    }

    static ItemStack compositeLens(ItemStack sourceLens, ItemStack compositeLens) {
        return itemToItemLens(sourceLens).setCompositeLens(sourceLens,compositeLens);
    }

    static ItemStack compositeLens(Item sourceLens, Item compositeLens) {
        return compositeLens(new ItemStack(sourceLens),new ItemStack(compositeLens));
    }

    static ItemStack getCompositeLens(ItemStack sourceLens) {
        return itemToItemLens(sourceLens).getCompositeLens(sourceLens);
    }

    static ItemStack[] getCompositeLensAll(ItemStack sourceLens) {
        return new ItemStack[]{new ItemStack(sourceLens.getItem()),getCompositeLens(sourceLens)};
    }
}
