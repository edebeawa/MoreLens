package edebe.more_lens.common.lib;

import edebe.more_lens.common.block.tile.mana.TileManaCleanse;
import net.minecraft.tileentity.TileEntityType;

public class LibTiles {
    public static final TileEntityType<TileManaCleanse> MANA_CLEANSE = TileEntityType.Builder.create(TileManaCleanse::new, LibBlocks.MANA_CLEANSE).build(null);
}
