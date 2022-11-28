package edebe.more_lens.common.item.block;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import vazkii.botania.api.mana.IManaItem;
import vazkii.botania.api.mana.IManaTooltipDisplay;
import vazkii.botania.common.core.helper.ItemNBTHelper;

import javax.annotation.Nonnull;

public class BlockItemManaBox extends BlockItem implements IManaItem, IManaTooltipDisplay {
    public static final int MAX_MANA = 1000000;

    private static final String TAG_BLOCK_ENTITY = "BlockEntityTag";
    private static final String TAG_MANA = "mana";
    private static final String TAG_ONE_USE = "oneUse";

    public BlockItemManaBox(Properties builder, Block blockIn) {
        super(blockIn, builder);
    }

    @Override
    public void fillItemGroup(@Nonnull ItemGroup tab, @Nonnull NonNullList<ItemStack> stacks) {
        if (this.isInGroup(tab)) {
            stacks.add(new ItemStack(this));

            final ItemStack fullPower = new ItemStack(this);
            setMana(fullPower, MAX_MANA);
            stacks.add(fullPower);
        }
    }

    @Override
    public int getEntityLifespan(ItemStack itemStack, World world) {
        return Integer.MAX_VALUE;
    }

    public static void setMana(ItemStack stack, int mana) {
        CompoundNBT tag = stack.getOrCreateTag();
        tag.putInt(TAG_MANA, mana);
        CompoundNBT blockEntity = tag.getCompound(TAG_BLOCK_ENTITY);
        blockEntity.putInt(TAG_MANA, mana);
        tag.put(TAG_BLOCK_ENTITY, blockEntity);
    }

    @Override
    public int getMana(ItemStack stack) {
        return ItemNBTHelper.getInt(stack, TAG_MANA, 0) * stack.getCount();
    }

    @Override
    public int getMaxMana(ItemStack stack) {
        return MAX_MANA * stack.getCount();
    }

    @Override
    public void addMana(ItemStack stack, int mana) {
        setMana(stack, Math.min(getMana(stack) + mana, getMaxMana(stack)) / stack.getCount());
    }

    @Override
    public boolean canReceiveManaFromPool(ItemStack stack, TileEntity pool) {
        return !ItemNBTHelper.getBoolean(stack, TAG_ONE_USE, false);
    }

    @Override
    public boolean canReceiveManaFromItem(ItemStack stack, ItemStack otherStack) {
        return true;
    }

    @Override
    public boolean canExportManaToPool(ItemStack stack, TileEntity pool) {
        return true;
    }

    @Override
    public boolean canExportManaToItem(ItemStack stack, ItemStack otherStack) {
        return true;
    }

    @Override
    public boolean isNoExport(ItemStack stack) {
        return false;
    }

    @Override
    public float getManaFractionForDisplay(ItemStack stack) {
        return (float) getMana(stack) / (float) getMaxMana(stack);
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return true;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        return 1.0 - getManaFractionForDisplay(stack);
    }

    @Override
    public int getRGBDurabilityForDisplay(ItemStack stack) {
        return MathHelper.hsvToRGB(getManaFractionForDisplay(stack) / 3.0F, 1.0F, 1.0F);
    }
}
