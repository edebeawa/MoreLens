package edebe.more_lens.common.register;

import edebe.more_lens.common.lib.LibBlockNames;
import edebe.more_lens.common.lib.LibTiles;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

import static vazkii.botania.common.block.ModBlocks.register;

public class RegisterTiles {
    public static void registerTiles(RegistryEvent.Register<TileEntityType<?>> event) {
        IForgeRegistry<TileEntityType<?>> registry = event.getRegistry();
        register(registry, LibBlockNames.MANA_CLEANSE, LibTiles.MANA_CLEANSE);
    }
}
