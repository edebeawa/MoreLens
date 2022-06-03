package edebe.more_lens.common.item.lens;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.mana.BurstProperties;
import vazkii.botania.common.item.lens.Lens;

import java.util.List;

public class LensEdebeGun extends Lens {
    @Override
    public void apply(ItemStack stack, BurstProperties props) {
        props.motionModifier *= 4.5F;
        props.color = 0;
    }

    @Override
    public void updateBurst(IManaBurst burst, ItemStack stack) {
        ThrowableEntity entity = burst.entity();
        if (entity.world.isRemote) {
            return;
        }
        AxisAlignedBB axis = new AxisAlignedBB(entity.getPosX(), entity.getPosY(), entity.getPosZ(), entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ).grow(1);
        List<LivingEntity> entities = entity.world.getEntitiesWithinAABB(LivingEntity.class, axis);
        for (LivingEntity living : entities) {
            if (living instanceof PlayerEntity) {
                continue;
            }

            if (living.hurtTime == 0) {
                int mana = burst.getMana();
                if (!burst.isFake()) {
                    DamageSource src = entity.getShooter() != null
                            ? DamageSource.causeIndirectMagicDamage(entity, entity.getShooter())
                            : DamageSource.MAGIC;
                    living.attackEntityFrom(src, mana);
                    burst.setMana(0);
                }
            }
        }
    }
}
