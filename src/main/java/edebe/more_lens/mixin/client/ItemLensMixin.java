package edebe.more_lens.mixin.client;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.common.item.lens.ItemLens;

import java.util.List;

@Mixin(ItemLens.class)
public class ItemLensMixin {
    @Inject(at = @At("HEAD"), method = "func_77624_a", cancellable = true)
    private void addInformation(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flags, CallbackInfo info) {
        if (ItemLens.getStoredColor(stack) == 17) {
            tooltip.add(new TranslationTextComponent("botaniamisc.color", new TranslationTextComponent("more_lens.color.translucent")));
            info.cancel();
        }
    }
}
