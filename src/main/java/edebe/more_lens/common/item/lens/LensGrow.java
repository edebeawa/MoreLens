package edebe.more_lens.common.item.lens;

import net.minecraft.item.BoneMealItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.common.item.lens.Lens;

public class LensGrow extends Lens {
    @Override
    public boolean collideBurst(IManaBurst burst, RayTraceResult pos, boolean isManaBlock, boolean dead, ItemStack stack) {
        World world = burst.entity().world;
        if (!world.isRemote && !burst.isFake() && pos.getType() == RayTraceResult.Type.BLOCK) {
            BlockPos collidePos = ((BlockRayTraceResult) pos).getPos();
            BlockPos coords = burst.getBurstSourceBlockPos();
            if (!isManaBlock && !coords.equals(((BlockRayTraceResult) pos).getPos())) {
                if (BoneMealItem.applyBonemeal(new ItemStack(Items.BONE_MEAL), world, collidePos) || BoneMealItem.growSeagrass(new ItemStack(Items.BONE_MEAL), world, collidePos, null)) {
                    world.playEvent(2005, collidePos, 0);
                }
            }
        }
        return dead;
    }
}
