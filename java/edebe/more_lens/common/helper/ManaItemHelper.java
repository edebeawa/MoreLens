package edebe.more_lens.common.helper;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.mana.IManaItem;

public interface ManaItemHelper {
    static boolean isManaItem(Item item) {
        return item instanceof IManaItem;
    }

    static boolean isManaItem(ItemStack itemStack) {
        return isManaItem(itemStack.getItem());
    }
}
