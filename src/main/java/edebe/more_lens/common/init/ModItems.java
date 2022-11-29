package edebe.more_lens.common.init;

import edebe.more_lens.common.group.MoreLensCreativeTab;
import edebe.more_lens.common.item.lens.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

import static edebe.more_lens.common.helper.RegistryHelper.register;

public final class ModItems {
    public static final Item LENS_MANA_STORM = new ItemLens(unstackable().rarity(Rarity.UNCOMMON),new LensManaStorm(), ItemLens.PROP_TOUCH);
    public static final Item LENS_SPRINKLE = new ItemLens(unstackable().rarity(Rarity.UNCOMMON),new LensSprinkle(), ItemLens.PROP_NONE);
    public static final Item LENS_LOITER = new ItemLens(unstackable(),new LensLoiter(), ItemLens.PROP_NONE);
    public static final Item LENS_BOUNCE = new ItemLens(unstackable(),new LensBounce(), ItemLens.PROP_TOUCH);
    public static final Item LENS_GROW = new ItemLens(unstackable(),new LensGrow(), ItemLens.PROP_NONE);
    public static final Item LENS_WRAP_AROUND = new ItemLens(unstackable(),new LensWrapAround(), ItemLens.PROP_NONE);
    public static final Item LENS_MANA = new ItemLens(unstackable(),new LensMana(), ItemLens.PROP_NONE);

    public static Item.Properties defaultBuilder() {
        return new Item.Properties().group(MoreLensCreativeTab.INSTANCE);
    }

    private static Item.Properties unstackable() {
        return defaultBuilder().maxStackSize(1);
    }

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
    }

    private static void registerBlockItems(IForgeRegistry<Item> registry) {
        Item.Properties properties = defaultBuilder().maxStackSize(64);
        register(registry, ModBlocks.MANA_CLEANSE, properties);
        register(registry, ModBlocks.OBSIDIAN_FRAME_GLASS, properties);
    }
}
