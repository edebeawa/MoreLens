package edebe.more_lens.common.init;

import edebe.more_lens.common.group.MoreLensGroup;
import edebe.more_lens.common.item.block.BlockItemManaBox;
import edebe.more_lens.common.item.lens.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

import static edebe.more_lens.common.helper.RegistryHelper.register;

public final class ModItems {
    public static ItemGroup moreLensGroup = new MoreLensGroup();

    public static final Item.Properties properties = new Item.Properties().group(moreLensGroup);

    public static Item.Properties defaultBuilder() {
        return properties.maxStackSize(1).rarity(Rarity.COMMON);
    }

    public static Item.Properties defaultBuilder(Rarity rarity) {
        return properties.maxStackSize(1).rarity(rarity);
    }

    public static Item.Properties defaultBuilder(int maxStackSize) {
        return properties.maxStackSize(maxStackSize).rarity(Rarity.COMMON);
    }

    public static final Item LENS_MANA_STORM = new ItemLens(defaultBuilder(Rarity.UNCOMMON),new LensManaStorm(), ItemLens.PROP_TOUCH);
    public static final Item LENS_SPRINKLE = new ItemLens(defaultBuilder(Rarity.UNCOMMON),new LensSprinkle(), ItemLens.PROP_NONE);
    public static final Item LENS_LOITER = new ItemLens(defaultBuilder(),new LensLoiter(), ItemLens.PROP_NONE);
    public static final Item LENS_BOUNCE = new ItemLens(defaultBuilder(),new LensBounce(), ItemLens.PROP_TOUCH);
    public static final Item LENS_GROW = new ItemLens(defaultBuilder(),new LensGrow(), ItemLens.PROP_NONE);
    public static final Item LENS_WRAP_AROUND = new ItemLens(defaultBuilder(),new LensWrapAround(), ItemLens.PROP_NONE);
    public static final Item LENS_MANA = new ItemLens(defaultBuilder(),new LensMana(), ItemLens.PROP_NONE);

    public static final Item MANA_BOX = new BlockItemManaBox(defaultBuilder(Rarity.RARE), ModBlocks.MANA_BOX);

    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> registry = event.getRegistry();
        registerBlockItems(registry);

        register(registry, "lens_mana_storm", LENS_MANA_STORM);
        register(registry, "lens_sprinkle", LENS_SPRINKLE);
        register(registry, "lens_loiter", LENS_LOITER);
        register(registry, "lens_bounce", LENS_BOUNCE);
        register(registry, "lens_grow", LENS_GROW);
        register(registry, "lens_wrap_around", LENS_WRAP_AROUND);
        register(registry, "lens_mana", LENS_MANA);
        register(registry, "mana_box", MANA_BOX);
    }

    private static void registerBlockItems(IForgeRegistry<Item> registry) {
        Item.Properties properties = defaultBuilder(64);
        register(registry, ModBlocks.MANA_CLEANSE, properties);
        register(registry, ModBlocks.OBSIDIAN_FRAME_GLASS, properties);
    }
}
