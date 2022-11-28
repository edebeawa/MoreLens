package edebe.more_lens.mixin;

import edebe.more_lens.common.init.ModEnchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.botania.common.item.ItemManaGun;

@Mixin(ItemManaGun.class)
public class ItemManaGunMixin {
    @Inject(at = @At("HEAD"), method = "func_77663_a", cancellable = true)
    private void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected, CallbackInfo info) {
        if (getCooldown(stack) > 0) {
            int cooldown = getCooldown(stack) - (EnchantmentHelper.getEnchantmentLevel(ModEnchantments.QUICK_COOLDOWN, stack) + 1);
            setCooldown(stack, cooldown > 0 ? cooldown : 0);
        }
        info.cancel();
    }

    private int getCooldown(ItemStack stack) {
        return stack.getOrCreateTag().getInt("cooldown");
    }

    private void setCooldown(ItemStack stack, int cooldown) {
        CompoundNBT tag = stack.getOrCreateTag();
        tag.putInt("cooldown", cooldown);
    }
}
