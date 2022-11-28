package edebe.more_lens.common.block.mana;

import com.mojang.blaze3d.matrix.MatrixStack;
import edebe.more_lens.common.block.tile.mana.TileManaBox;
import edebe.more_lens.common.item.block.BlockItemManaBox;
import edebe.more_lens.common.init.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import vazkii.botania.api.wand.IWandHUD;
import vazkii.botania.api.wand.IWandable;
import vazkii.botania.common.block.BlockMod;
import vazkii.botania.common.entity.EntityManaBurst;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collections;
import java.util.List;

import static edebe.more_lens.common.helper.VoxelShapeHelper.shape;

@ParametersAreNonnullByDefault
public class BlockManaBox extends BlockMod implements ITileEntityProvider, IWandHUD, IWandable {
    private static final VoxelShape SHAPE = shape(0,16,1,15,0,16,1,15,0,16,1,15,IBooleanFunction.ONLY_FIRST);

    public BlockManaBox(Properties builder) {
        super(builder);
    }

    @Nonnull
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext ctx) {
        return SHAPE;
    }

    @NotNull
    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        List<ItemStack> dropsOriginal = super.getDrops(state, builder);
        if (!dropsOriginal.isEmpty()) {
            return dropsOriginal;
        } else {
            ItemStack itemStack = new ItemStack(ModItems.MANA_BOX);
            TileManaBox tile = (TileManaBox) builder.get(LootParameters.BLOCK_ENTITY);
            BlockItemManaBox.setMana(itemStack, tile.getCurrentMana());
            return Collections.singletonList(itemStack);
        }
    }

    @Nonnull
    @Override
    public ActionResultType onBlockActivated(@Nonnull BlockState state, World world, @Nonnull BlockPos pos, PlayerEntity player, @Nonnull Hand hand, @Nonnull BlockRayTraceResult hit) {
        TileEntity te = world.getTileEntity(pos);
        ItemStack stack = player.getHeldItem(hand);
        if (stack.getItem() instanceof DyeItem && te instanceof TileManaBox) {
            DyeColor color = ((DyeItem) stack.getItem()).getDyeColor();
            if (color != ((TileManaBox) te).getColor()) {
                ((TileManaBox) te).setColor(color);
                stack.shrink(1);
                return ActionResultType.SUCCESS;
            }
        }
        return super.onBlockActivated(state, world, pos, player, hand, hit);
    }

    @NotNull
    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        if (context.getEntity() instanceof EntityManaBurst) {
            return SHAPE;
        } else {
            return super.getCollisionShape(state, world, pos, context);
        }
    }

    @Nonnull
    @Override
    public TileEntity createNewTileEntity(@Nonnull IBlockReader world) {
        return new TileManaBox();
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity instanceof ItemEntity) {
            TileManaBox tile = (TileManaBox) world.getTileEntity(pos);
            tile.collideEntityItem((ItemEntity) entity);
        }
    }

    @Override
    public boolean hasComparatorInputOverride(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorInputOverride(BlockState state, World world, BlockPos pos) {
        TileManaBox pool = (TileManaBox) world.getTileEntity(pos);
        return TileManaBox.calculateComparatorLevel(pool.getCurrentMana(), pool.manaCap);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void renderHUD(MatrixStack ms, Minecraft mc, World world, BlockPos pos) {
        ((TileManaBox) world.getTileEntity(pos)).renderHUD(ms, mc);
    }

    @Override
    public boolean onUsedByWand(PlayerEntity player, ItemStack stack, World world, BlockPos pos, Direction side) {
        ((TileManaBox) world.getTileEntity(pos)).onWanded(player);
        return true;
    }
}
