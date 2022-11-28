package edebe.more_lens.common.item.lens;

import net.minecraft.item.ItemStack;
import vazkii.botania.api.mana.BurstProperties;

public class LensBounce extends vazkii.botania.common.item.lens.LensBounce {
    public void apply(ItemStack stack, BurstProperties props) {
        props.gravity = 0.03F;
        props.manaLossPerTick = 1F;
    }
}
