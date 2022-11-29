package edebe.more_lens.common.init;

import edebe.more_lens.common.block.tile.mana.*;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.LinkedHashMap;
import java.util.Map;

import static edebe.more_lens.common.helper.RegistryHelper.register;

public final class ModTiles {
    public static final TileEntityType<TileManaCleanse> MANA_CLEANSE = TileEntityType.Builder.create(TileManaCleanse::new, ModBlocks.MANA_CLEANSE).build(null);

    public static void registerTiles(RegistryEvent.Register<TileEntityType<?>> event) {
        IForgeRegistry<TileEntityType<?>> registry = event.getRegistry();
        register(registry, "mana_cleanse", MANA_CLEANSE);
    }
}
