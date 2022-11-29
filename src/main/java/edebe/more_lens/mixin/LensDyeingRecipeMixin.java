package edebe.more_lens.mixin;

import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.common.crafting.recipe.LensDyeingRecipe;
import vazkii.botania.common.item.ModItems;

@Mixin(LensDyeingRecipe.class)
public class LensDyeingRecipeMixin {
    @Inject(at = @At("RETURN"), method = "getStackColor", cancellable = true)
    private void getLensColor(ItemStack stack, CallbackInfoReturnable<Integer> info) {
        if (stack.getItem() == ModItems.phantomInk) {
            info.setReturnValue(17);
        }
    }
}
