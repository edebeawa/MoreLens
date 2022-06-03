package edebe.more_lens.client.event;

import edebe.more_lens.common.helper.RegistryHelper;
import edebe.more_lens.common.item.lens.LensMana;
import edebe.more_lens.common.lib.LibBlocks;
import edebe.more_lens.common.lib.LibItems;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@OnlyIn(Dist.CLIENT)
public class SetupEvent {
    public static void init(FMLClientSetupEvent event) {
        setBlockRenderType();
        registerItemProperty(event);
    }

    public static void setBlockRenderType() {
        RenderTypeLookup.setRenderLayer(LibBlocks.OBSIDIAN_FRAME_GLASS, RenderType.getTranslucent());
    }

    public static void registerItemProperty(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            RegistryHelper.registerProperty(LibItems.LENS_MANA, "outputMode", (itemStack, clientWorld, livingEntity) -> (LensMana.getMode(itemStack)?1F:0F));
        });
    }
}
