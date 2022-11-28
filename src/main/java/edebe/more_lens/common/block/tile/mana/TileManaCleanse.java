package edebe.more_lens.common.block.tile.mana;

import edebe.more_lens.common.init.ModTiles;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.common.block.tile.TileMod;

public class TileManaCleanse extends TileMod {
    public TileManaCleanse() {
        super(ModTiles.MANA_CLEANSE);
    }

    public void onBurstCollision(IManaBurst burst) {
        burst.setSourceLens(ItemStack.EMPTY);
        burst.setColor(0xFFFFFF);
        burst.setGravity(0F);
    }
}
