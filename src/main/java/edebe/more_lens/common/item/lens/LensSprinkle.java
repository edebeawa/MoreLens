package edebe.more_lens.common.item.lens;

import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.mana.BurstProperties;
import vazkii.botania.common.entity.ModEntities;
import vazkii.botania.common.item.lens.Lens;

import static edebe.more_lens.common.helper.ManaBurstHelper.spawnBurst;
import static edebe.more_lens.common.helper.MoreLensHelper.getCompositeLens;

public class LensSprinkle extends Lens {
    private static final int BURST_MANA = 120;

    public void apply(ItemStack stack, BurstProperties props) {
        props.maxMana *= BURST_MANA / 2;
    }

    @Override
    public void updateBurst(IManaBurst burst, ItemStack stack) {
        ThrowableEntity entity = burst.entity();
        World world = entity.world;
        if (!world.isRemote && !burst.isFake()) {
            boolean spawnBurst = spawnBurst(ModEntities.MANA_BURST.create(world),entity.getPosX(), entity.getPosY(), entity.getPosZ(),burst.getColor(),BURST_MANA,340,50,1F,1F,getCompositeLens(burst.getSourceLens()),world);
            if (spawnBurst)
                burst.setMana(burst.getMana() - BURST_MANA);
        }
    }
}
