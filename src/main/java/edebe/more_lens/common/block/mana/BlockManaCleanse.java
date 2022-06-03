package edebe.more_lens.common.block.mana;

import edebe.more_lens.common.block.ManaCollisionGhostBlock;
import edebe.more_lens.common.block.tile.mana.TileManaCleanse;
import net.minecraft.block.BlockState;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.mana.IManaCollisionGhost;
import vazkii.botania.api.mana.IManaTrigger;
import vazkii.botania.common.block.BlockMod;
import vazkii.botania.common.entity.EntityManaBurst;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

public class BlockManaCleanse extends ManaCollisionGhostBlock implements ITileEntityProvider,IManaTrigger {
    private static final VoxelShape SHAPE = makeCuboidShape(0, 0, 0, 16, 16, 16);

    public BlockManaCleanse(Properties properties) {
        super(properties);
    }

    @Nonnull
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext ctx) {
        return SHAPE;
    }

    @Nonnull
    @Override
    public VoxelShape getCollisionShape(@Nonnull BlockState state, @Nonnull IBlockReader world, @Nonnull BlockPos pos, @Nonnull ISelectionContext context) {
        if (context.getEntity() instanceof EntityManaBurst) {
            return SHAPE;
        } else {
            return super.getCollisionShape(state, world, pos, context);
        }
    }

    @Nonnull
    @Override
    public TileEntity createNewTileEntity(@Nonnull IBlockReader world) {
        return new TileManaCleanse();
    }

    @Override
    public void onBurstCollision(IManaBurst burst, World world, BlockPos pos) {
        TileEntity tile = world.getTileEntity(pos);
        if (tile instanceof TileManaCleanse)
            ((TileManaCleanse)tile).onBurstCollision(burst);
    }
}
