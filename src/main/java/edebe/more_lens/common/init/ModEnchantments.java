package edebe.more_lens.common.init;

import edebe.more_lens.common.enchantment.QuickCooldownEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

import static edebe.more_lens.common.helper.RegistryHelper.register;

public final class ModEnchantments {
    public static final Enchantment QUICK_COOLDOWN = new QuickCooldownEnchantment(Enchantment.Rarity.UNCOMMON, EquipmentSlotType.MAINHAND);

    public static void registerEnchantments(RegistryEvent.Register<Enchantment> event) {
        IForgeRegistry<Enchantment> registry = event.getRegistry();
        register(registry, "quick_cooldown", QUICK_COOLDOWN);
    }
}
