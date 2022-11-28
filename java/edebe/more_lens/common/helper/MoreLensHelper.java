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
    static Color hashCodeToColor(int color) {
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = color & 0xFF;
        int a = (color >> 24) & 0xff;
        return new Color(r,g,b,a);
    }

    static int rainbow(float saturation, float value) {
        return MathHelper.hsvToRGB(Botania.proxy.getWorldElapsedTicks() * 2 % 360 / 360F, saturation, value);
    }

    static int rainbow() {
        return rainbow(1F, 1F);
    }

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
