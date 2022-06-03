package edebe.more_lens.common.item.lens;

import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.item.lens.LensPhantom;

public class LensLoiter extends LensPhantom {
    @Override
    public void updateBurst(IManaBurst burst, ItemStack stack) {
        ThrowableEntity entity = burst.entity();
        World world = entity.world;
        if (world.isRemote) {
            return;
        }
        float motionModifier = 0.1F;
        Vector3 motion = new Vector3(Math.random() - 0.5, Math.random() - 0.5, Math.random() - 0.5).normalize().multiply(motionModifier);
        burst.setBurstMotion(motion.x, motion.y, motion.z);
    }
}
