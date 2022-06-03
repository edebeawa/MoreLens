package edebe.more_lens.common.helper;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import vazkii.botania.common.entity.EntityManaBurst;

import javax.annotation.Nullable;

public interface ManaBurstHelper extends MoreLensHelper {
    static boolean spawnBurst(@Nullable EntityManaBurst burst, double x, double y, double z, int color, int mana, int startingMana, int minManaLoss, float manaLossPerTick, float gravity, ItemStack sourceLens, World world) {
        if (burst != null) {
            burst.setColor(color);
            burst.setMana(mana);
            burst.setStartingMana(startingMana);
            burst.setMinManaLoss(minManaLoss);
            burst.setManaLossPerTick(manaLossPerTick);
            burst.setGravity(gravity);
            burst.setSourceLens(sourceLens);

            burst.setPosition(x, y, z);
            world.addEntity(burst);
            return true;
        }
        return false;
    }

    static boolean spawnBurst(@Nullable EntityManaBurst burst, double x, double y, double z, int color, int mana, int startingMana, int minManaLoss, float manaLossPerTick, float gravity, @Nullable Item sourceLens, World world) {
        return spawnBurst(burst,x,y,z,color,mana,startingMana,minManaLoss,manaLossPerTick,gravity,new ItemStack(sourceLens),world);
    }
}
