package edebe.more_lens.common.item;

import edebe.more_lens.common.lib.LibItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import vazkii.botania.api.mana.BurstProperties;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.advancements.ManaGunTrigger;
import vazkii.botania.common.core.handler.ModSounds;
import vazkii.botania.common.entity.EntityManaBurst;

import javax.annotation.Nonnull;

public class ItemEdebeGun extends Item implements IManaUsingItem {
    public ItemEdebeGun(Properties props) {
        super(props);
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, @Nonnull Hand hand) {
        ActionResult<ItemStack> actionResult = super.onItemRightClick(world, player, hand);
        ItemStack stack = actionResult.getResult();
        EntityManaBurst burst = getBurst(player, stack, true, hand);
        if (burst != null && ManaItemHandler.instance().requestManaExact(stack, player, burst.getMana(), true)) {
            if (!world.isRemote) {
                world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), ModSounds.manaBlaster, SoundCategory.PLAYERS, 0.6F, 1);
                world.addEntity(burst);
                ManaGunTrigger.INSTANCE.trigger((ServerPlayerEntity) player, stack);
            } else {
                    player.setMotion(player.getMotion().subtract(burst.getMotion().mul(0.1, 0.3, 0.1)));
            }
        } else if (!world.isRemote) {
                world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.PLAYERS, 0.6F, (1.0F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F) * 0.7F);
        }
        return actionResult;
    }

    @Nonnull
    public BurstProperties getBurstProps(PlayerEntity player, ItemStack stack, boolean request, Hand hand) {
        int maxMana = 500;
        int color = 0;
        int ticksBeforeManaLoss = 120;
        float manaLossPerTick = 4F;
        float motionModifier = 5F;
        float gravity = 0F;
        BurstProperties props = new BurstProperties(maxMana, ticksBeforeManaLoss, manaLossPerTick, gravity, motionModifier, color);
        return props;
    }

    private EntityManaBurst getBurst(PlayerEntity player, ItemStack stack, boolean request, Hand hand) {
        EntityManaBurst burst = new EntityManaBurst(player);
        BurstProperties props = getBurstProps(player, stack, request, hand);

        burst.setSourceLens(new ItemStack(LibItems.LENS_EDEBE_GUN));
        if (!request || ManaItemHandler.instance().requestManaExact(stack, player, props.maxMana, false)) {
            burst.setColor(props.color);
            burst.setMana(props.maxMana);
            burst.setStartingMana(props.maxMana);
            burst.setMinManaLoss(props.ticksBeforeManaLoss);
            burst.setManaLossPerTick(props.manaLossPerTick);
            burst.setGravity(props.gravity);
            burst.setBurstMotion(burst.getMotion().getX() * props.motionModifier,
                    burst.getMotion().getY() * props.motionModifier,
                    burst.getMotion().getZ() * props.motionModifier);

            return burst;
        }
        return null;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        return 0;
    }

    @Override
    public boolean usesMana(ItemStack stack) {
        return true;
    }
}
