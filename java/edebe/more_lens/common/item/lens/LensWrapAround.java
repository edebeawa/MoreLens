package edebe.more_lens.common.item.lens;

import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.mana.BurstProperties;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.item.lens.LensStorm;

public class LensWrapAround extends LensStorm {
    private static final String TAG_HAS_POS_TAG = "hasPosTag";
    private static final String TAG_POS_X = "posX";
    private static final String TAG_POS_Y = "posY";
    private static final String TAG_POS_Z = "posZ";

    public void apply(ItemStack stack, BurstProperties props) {
        props.maxMana *= 50;
    }

    @Override
    public void updateBurst(IManaBurst burst, ItemStack stack) {
        ThrowableEntity entity = burst.entity();
        World world = entity.world;
        CompoundNBT cmp = entity.getPersistentData();
        if (!cmp.getBoolean(TAG_HAS_POS_TAG)) {
            cmp.putBoolean(TAG_HAS_POS_TAG, true);
            cmp.putDouble(TAG_POS_X, entity.getPosX());
            cmp.putDouble(TAG_POS_Y, entity.getPosY());
            cmp.putDouble(TAG_POS_Z, entity.getPosZ());
        }
        if (!world.isRemote && !burst.isFake()) {
            applyEffect(burst, cmp.getDouble(TAG_POS_X), cmp.getDouble(TAG_POS_Y), cmp.getDouble(TAG_POS_Z));
        }
    }

    private static void applyEffect(IManaBurst burst, double x, double y, double z) {
        ThrowableEntity entity = burst.entity();

        int orbitTime = burst.getOrbitTime() + 200;
        if (orbitTime == 0) {
            burst.setMinManaLoss(burst.getMinManaLoss() * 3);
        }

        float radius = Math.min(7.5F, (Math.max(40, orbitTime) - 40) / 40F + 1.5F);
        int angle = orbitTime % 360;

        float xTarget = (float) (x + Math.cos(angle * 10 * Math.PI / 180F) * radius);
        float yTarget = (float) y;
        float zTarget = (float) (z + Math.sin(angle * 10 * Math.PI / 180F) * radius);

        Vector3 targetVec = new Vector3(xTarget, yTarget, zTarget);
        Vector3 currentVec = new Vector3(entity.getPosX(), entity.getPosY(), entity.getPosZ());
        Vector3 moveVector = targetVec.subtract(currentVec);

        burst.setBurstMotion(moveVector.x, moveVector.y, moveVector.z);

        burst.setOrbitTime(burst.getOrbitTime() + 1);
    }

    @Override
    public boolean collideBurst(IManaBurst burst, RayTraceResult pos, boolean isManaBlock, boolean dead, ItemStack stack) {
        if (!isManaBlock) {
            dead = false;
            burst.setMinManaLoss(Math.max(0, burst.getMinManaLoss() - 4));
        }

        return dead;
    }
}
