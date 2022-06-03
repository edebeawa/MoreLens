
package edebe.more_lens.common.group;

import edebe.more_lens.api.MoreLensAPI;
import edebe.more_lens.common.lib.LibItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class MoreLensGroup extends ItemGroup {
    public MoreLensGroup() {
        super(MoreLensAPI.MOD_ID + "_group");
        setBackgroundImageName(MoreLensAPI.MOD_ID + ".png");
        setNoTitle();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public ItemStack createIcon() {
        return new ItemStack(LibItems.LENS_MANA_STORM);
    }

    @OnlyIn(Dist.CLIENT)
    public boolean hasSearchBar() {
        return true;
    }
}
