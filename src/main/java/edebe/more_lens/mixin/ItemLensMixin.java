package edebe.more_lens.mixin;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.common.item.lens.ItemLens;
import vazkii.botania.common.item.lens.LensStorm;

import java.util.List;

@Mixin(ItemLens.class)
public class ItemLensMixin {
    @Inject(at = @At("RETURN"), method = "getLensColor", cancellable = true)
    private void getLensColor(ItemStack stack, CallbackInfoReturnable<Integer> info) {
        if (ItemLens.getStoredColor(stack) == 17) {
            info.setReturnValue(0);
        }
    }
}
