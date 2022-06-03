package edebe.more_lens.common.item.lens;

import edebe.more_lens.common.helper.ManaItemHelper;
import edebe.more_lens.common.helper.RecipeHelper;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.mana.BurstProperties;
import vazkii.botania.api.mana.IManaItem;
import vazkii.botania.api.recipe.IManaInfusionRecipe;
import vazkii.botania.common.item.lens.Lens;

import java.util.List;

public class LensMana extends Lens {
    private static final String TAG_OUTPUT_MODE = "outputMode";

    public static void setMode(ItemStack itemstack,Boolean mode) {
        itemstack.getOrCreateTag().putBoolean(TAG_OUTPUT_MODE,mode);
    }

    public static boolean getMode(ItemStack itemstack) {
        return itemstack.getOrCreateTag().getBoolean(TAG_OUTPUT_MODE);
    }

    @Override
    public void apply(ItemStack stack, BurstProperties props) {
        if (getMode(stack)) {
            props.maxMana = 200000;
        }
        props.motionModifier *= 0.5F;
        props.manaLossPerTick *= 2F;
    }

    @Override
    public void updateBurst(IManaBurst burst, ItemStack stack) {
        ThrowableEntity entity = burst.entity();
        World world = entity.world;
        if (world.isRemote)
            return;
        AxisAlignedBB axis = new AxisAlignedBB(entity.getPosX(), entity.getPosY(), entity.getPosZ(), entity.lastTickPosX,
                entity.lastTickPosY, entity.lastTickPosZ).grow(1);
        List<ItemEntity> entities = world.getEntitiesWithinAABB(ItemEntity.class, axis);
        if(!burst.isFake()) {
            for (ItemEntity items : entities) {
                if (items.cannotPickup() || !items.isAlive())
                    continue;
                ItemStack itemstack = items.getItem();
                if (ManaItemHelper.isManaItem(itemstack)) {
                    IManaItem manaItem = (IManaItem) itemstack.getItem();
                    if (getMode(stack)) {
                        int mana = manaItem.getMaxMana(itemstack) - manaItem.getMana(itemstack);
                        manaItem.addMana(itemstack, burst.getMana());
                        burst.setMana(burst.getMana() > mana ? burst.getMana() - mana : 0);
                    } else {
                        int mana = manaItem.getMana(itemstack);
                        manaItem.addMana(itemstack, -manaItem.getMana(itemstack));
                        burst.setMana(burst.getMana() + mana);
                    }
                } else if (getMode(stack)) {
                    Recipe(burst, itemstack, world, items);
                }
            }
        }
    }

    private void Recipe(IManaBurst burst,ItemStack itemstack,World world,ItemEntity items) {
        int mana = burst.getMana();
        BlockState state = world.getBlockState(burst.getBurstSourceBlockPos().add(0, -1, 0));
        IManaInfusionRecipe recipe = RecipeHelper.getMatchingRecipe(world, itemstack, state);
        if (recipe != null) {
            int manaToConsume = recipe.getManaToConsume();
            if (mana >= manaToConsume) {
                burst.setMana(mana - manaToConsume);
                itemstack.shrink(1);

                ItemStack output = recipe.getRecipeOutput().copy();
                ItemEntity outputItem = new ItemEntity(world, items.getPosX(), items.getPosY()+0.5, items.getPosZ() + 0.5, output);
                outputItem.setPickupDelay(50);
                world.addEntity(outputItem);
            }
        }
    }
}
