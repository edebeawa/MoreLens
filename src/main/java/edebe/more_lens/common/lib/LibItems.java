package edebe.more_lens.common.lib;

import edebe.more_lens.common.item.ItemEdebeGun;
import edebe.more_lens.common.item.lens.*;
import edebe.more_lens.common.register.RegisterGroup;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;

public final class LibItems {
    public static final Item.Properties properties = new Item.Properties().group(RegisterGroup.edebeBotanyGroup);

    public static Item.Properties defaultBuilder() {
        return properties.maxStackSize(1).rarity(Rarity.COMMON);
    }

    public static Item.Properties defaultBuilder(Rarity rarity) {
        return properties.maxStackSize(1).rarity(rarity);
    }

    public static Item.Properties defaultBuilder(int maxStackSize) {
        return properties.maxStackSize(maxStackSize).rarity(Rarity.COMMON);
    }
    public static final Item EDEBE_GUN = new ItemEdebeGun(defaultBuilder(Rarity.RARE));

    public static final Item LENS_MANA_STORM = new ItemLens(defaultBuilder(Rarity.UNCOMMON),new LensManaStorm(),ItemLens.PROP_TOUCH);
    public static final Item LENS_SPRINKLE = new ItemLens(defaultBuilder(Rarity.UNCOMMON),new LensSprinkle(),ItemLens.PROP_NONE);
    public static final Item LENS_LOITER = new ItemLens(defaultBuilder(),new LensLoiter(),ItemLens.PROP_NONE);
    public static final Item LENS_COLORFUL = new ItemLens(defaultBuilder(),new LensColorful(),ItemLens.PROP_NONE);
    public static final Item LENS_BOUNCE = new ItemLens(defaultBuilder(),new LensBounce(),ItemLens.PROP_TOUCH);
    public static final Item LENS_GROW = new ItemLens(defaultBuilder(),new LensGrow(),ItemLens.PROP_NONE);
    public static final Item LENS_WRAP_AROUND = new ItemLens(defaultBuilder(),new LensWrapAround(),ItemLens.PROP_NONE);
    public static final Item LENS_MANA = new ItemLens(defaultBuilder(),new LensMana(),ItemLens.PROP_NONE);
    public static final Item LENS_EDEBE_GUN = new ItemLens(defaultBuilder(Rarity.EPIC),new LensEdebeGun(),ItemLens.PROP_DAMAGE);
}
