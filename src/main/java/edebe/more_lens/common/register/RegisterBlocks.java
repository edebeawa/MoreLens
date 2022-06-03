package edebe.more_lens.common.register;

import edebe.more_lens.common.helper.RegistryHelper;
import edebe.more_lens.common.lib.LibBlockNames;
import edebe.more_lens.common.lib.LibBlocks;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class RegisterBlocks {
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();
        RegistryHelper.register(registry, LibBlockNames.MANA_CLEANSE, LibBlocks.MANA_CLEANSE);
        RegistryHelper.register(registry, LibBlockNames.OBSIDIAN_FRAME_GLASS, LibBlocks.OBSIDIAN_FRAME_GLASS);
    }
}
