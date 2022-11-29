package edebe.more_lens.common.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import vazkii.botania.common.item.ItemManaGun;

public class QuickCooldownEnchantment extends Enchantment {
    private static final EnchantmentType MANA_GUN = EnchantmentType.create("MANA_GUN", (itemIn) -> itemIn instanceof ItemManaGun);

    public QuickCooldownEnchantment(Rarity rarityIn, EquipmentSlotType... slots) {
        super(rarityIn, MANA_GUN, slots);
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 12 + (enchantmentLevel - 1) * 20;
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return 50;
    }

    @Override
    public int getMaxLevel() {
        return 10;
    }
}
