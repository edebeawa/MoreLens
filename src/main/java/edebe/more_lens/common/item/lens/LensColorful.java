package edebe.more_lens.common.item.lens;

import edebe.more_lens.common.helper.MoreLensHelper;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.common.item.lens.Lens;

public class LensColorful extends Lens {
    @Override
    public void updateBurst(IManaBurst burst, ItemStack stack) {
        ThrowableEntity entity = burst.entity();
        World world = entity.world;
        if (!world.isRemote && !burst.isFake()) {
            burst.setColor(MoreLensHelper.random(999999999));
        }
    }
}
