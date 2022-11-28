
package edebe.more_lens.common.group;

import edebe.more_lens.common.MoreLens;
import edebe.more_lens.common.init.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class MoreLensGroup extends ItemGroup {
    public MoreLensGroup() {
        super(MoreLens.MODID + "_group");
        setBackgroundImageName(MoreLens.MODID + ".png");
        setNoTitle();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public ItemStack createIcon() {
        return new ItemStack(ModItems.LENS_MANA_STORM);
    }

    @OnlyIn(Dist.CLIENT)
    public boolean hasSearchBar() {
        return true;
    }
}
