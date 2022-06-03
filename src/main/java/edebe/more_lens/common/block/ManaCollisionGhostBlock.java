package edebe.more_lens.common.block;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import vazkii.botania.api.mana.IManaCollisionGhost;
import vazkii.botania.common.block.BlockMod;

import java.util.Collections;
import java.util.List;

public class ManaCollisionGhostBlock extends BlockMod implements IManaCollisionGhost {
    public ManaCollisionGhostBlock(Properties builder) {
        super(builder);
    }

    @Override
    public boolean isGhost(BlockState state, World world, BlockPos pos) {
        return true;
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        List<ItemStack> dropsOriginal = super.getDrops(state, builder);
        if (!dropsOriginal.isEmpty())
            return dropsOriginal;
        return Collections.singletonList(new ItemStack(this));
    }
}
