package edebe.more_lens.common.register;

import edebe.more_lens.common.helper.RegistryHelper;
import edebe.more_lens.common.lib.LibBlocks;
import edebe.more_lens.common.lib.LibItemNames;
import edebe.more_lens.common.lib.LibItems;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

public final class RegisterItems {
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        registerBlockItems(registry);

        RegistryHelper.register(registry, LibItemNames.EDEBE_GUN, LibItems.EDEBE_GUN);
        RegistryHelper.register(registry, LibItemNames.LENS_MANA_STORM, LibItems.LENS_MANA_STORM);
        RegistryHelper.register(registry, LibItemNames.LENS_SPRINKLE, LibItems.LENS_SPRINKLE);
        RegistryHelper.register(registry, LibItemNames.LENS_LOITER, LibItems.LENS_LOITER);
        RegistryHelper.register(registry, LibItemNames.LENS_COLORFUL, LibItems.LENS_COLORFUL);
        RegistryHelper.register(registry, LibItemNames.LENS_BOUNCE, LibItems.LENS_BOUNCE);
        RegistryHelper.register(registry, LibItemNames.LENS_GROW, LibItems.LENS_GROW);
        RegistryHelper.register(registry, LibItemNames.LENS_WRAP_AROUND, LibItems.LENS_WRAP_AROUND);
        RegistryHelper.register(registry, LibItemNames.LENS_MANA, LibItems.LENS_MANA);
        RegistryHelper.register(registry, LibItemNames.LENS_EDEBE_GUN, LibItems.LENS_EDEBE_GUN);
    }

    private static void registerBlockItems(IForgeRegistry<Item> registry) {
        Item.Properties properties = LibItems.defaultBuilder(64);
        RegistryHelper.register(registry, LibBlocks.MANA_CLEANSE, properties);
        RegistryHelper.register(registry, LibBlocks.OBSIDIAN_FRAME_GLASS, properties);
    }
}