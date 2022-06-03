package edebe.more_lens.common.item.lens;

import edebe.more_lens.common.helper.MoreLensHelper;
import edebe.more_lens.common.lib.LibItems;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import vazkii.botania.common.item.lens.Lens;

import java.util.List;

public class ItemLens extends vazkii.botania.common.item.lens.ItemLens {

    public ItemLens(Properties builder, Lens lens, int props) {
        super(builder, lens, props);
    }

    @Override
    public boolean shouldPull(ItemStack stack) {
        return stack.getItem() != LibItems.LENS_MANA_STORM;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flags) {
        if (stack.getItem() == LibItems.LENS_SPRINKLE) {
            ItemStack compositeLens = MoreLensHelper.getCompositeLens(stack);
            String shortKey = compositeLens.isEmpty()?"item.more_lens.lens_sprinkle.none":compositeLens.getTranslationKey() + ".short";
            shortKey = MoreLensHelper.localization("item.more_lens.lens_sprinkle.effect") + MoreLensHelper.localization(shortKey);
            tooltip.add(new TranslationTextComponent(shortKey).mergeStyle(TextFormatting.GRAY));
        } else if (stack.getItem() == LibItems.LENS_MANA) {
            String key = MoreLensHelper.localization("item.more_lens.lens_mana.mode");
            key = key + MoreLensHelper.localization("item.more_lens.lens_mana." + (LensMana.getMode(stack)?"output":"input"));
            tooltip.add(new TranslationTextComponent(key).mergeStyle(TextFormatting.GRAY));
        } else if (stack.getItem() == LibItems.LENS_EDEBE_GUN) {
            MoreLensHelper.tooltipCreative(tooltip);
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        ActionResult<ItemStack> actionResult = super.onItemRightClick(world, player, hand);
        if (player.isSneaking()) {
            ItemStack itemstack = actionResult.getResult();
            if (itemstack.getItem() == LibItems.LENS_MANA)
            LensMana.setMode(itemstack, !LensMana.getMode(itemstack));
        }
        return actionResult;
    }
}
