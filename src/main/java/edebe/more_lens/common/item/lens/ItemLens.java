package edebe.more_lens.common.item.lens;

import edebe.more_lens.common.init.ModItems;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import vazkii.botania.common.item.lens.Lens;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

import static edebe.more_lens.common.helper.MoreLensHelper.*;

@ParametersAreNonnullByDefault
public class ItemLens extends vazkii.botania.common.item.lens.ItemLens {

    public ItemLens(Properties builder, Lens lens, int props) {
        super(builder, lens, props);
    }

    @Override
    public boolean shouldPull(ItemStack stack) {
        return stack.getItem() != ModItems.LENS_MANA_STORM;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flags) {
        int storedColor = getStoredColor(stack);
        if (storedColor != -1) {
            TranslationTextComponent colorName = new TranslationTextComponent(storedColor == 16 ? "botania.color.rainbow" : "color.minecraft." + DyeColor.byId(storedColor));
            Color realColor = Color.fromInt(getLensColor(stack));
            tooltip.add(new TranslationTextComponent("botaniamisc.color", colorName).modifyStyle(s -> s.setColor(realColor)));
        }

        if (stack.getItem() == ModItems.LENS_SPRINKLE) {
            ItemStack compositeLens = getCompositeLens(stack);
            String shortKey = compositeLens.isEmpty()?"item.more_lens.lens_sprinkle.none":compositeLens.getTranslationKey() + ".short";
            shortKey = localization("item.more_lens.lens_sprinkle.effect") + localization(shortKey);
            tooltip.add(new TranslationTextComponent(shortKey).mergeStyle(TextFormatting.GRAY));
        } else if (stack.getItem() == ModItems.LENS_MANA) {
            String key = localization("item.more_lens.lens_mana.mode");
            key = key + localization("item.more_lens.lens_mana." + (LensMana.getMode(stack)?"output":"input"));
            tooltip.add(new TranslationTextComponent(key).mergeStyle(TextFormatting.GRAY));
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        ActionResult<ItemStack> actionResult = super.onItemRightClick(world, player, hand);
        if (player.isSneaking()) {
            ItemStack itemstack = actionResult.getResult();
            if (itemstack.getItem() == ModItems.LENS_MANA)
            LensMana.setMode(itemstack, !LensMana.getMode(itemstack));
        }
        return actionResult;
    }
}
