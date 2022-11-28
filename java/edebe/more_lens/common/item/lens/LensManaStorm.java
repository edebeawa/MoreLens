package edebe.more_lens.common.item.lens;

import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.common.entity.EntityManaStorm;
import vazkii.botania.common.entity.ModEntities;
import vazkii.botania.common.item.lens.LensStorm;

public class LensManaStorm extends LensStorm {
    @Override
    public boolean collideBurst(IManaBurst burst, RayTraceResult pos, boolean isManaBlock, boolean dead, ItemStack stack) {
        ThrowableEntity entity = burst.entity();
        World world = entity.world;
        if (!world.isRemote && !burst.isFake() && pos.getType() == RayTraceResult.Type.BLOCK) {
            BlockPos coords = burst.getBurstSourceBlockPos();
            if (!isManaBlock && !coords.equals(((BlockRayTraceResult) pos).getPos())) {
                EntityManaStorm storm = ModEntities.MANA_STORM.create(world);
                storm.burstColor = burst.getColor();
                storm.setPosition(entity.getPosX(),entity.getPosY(),entity.getPosZ());
                world.addEntity(storm);
            }
        }
        return dead;
    }
}
