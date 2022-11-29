package edebe.more_lens.event.lifecycle;

import edebe.more_lens.common.helper.RegistryHelper;
import edebe.more_lens.common.init.ModBlocks;
import edebe.more_lens.common.init.ModItems;
import edebe.more_lens.common.item.lens.LensMana;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientSetup {
    public static void init(FMLClientSetupEvent event) {
        setBlockRenderType();
        registerItemProperty(event);
    }

    public static void setBlockRenderType() {
        RenderTypeLookup.setRenderLayer(ModBlocks.OBSIDIAN_FRAME_GLASS, RenderType.getTranslucent());
    }

    public static void registerItemProperty(FMLClientSetupEvent event) {
        event.enqueueWork(() -> RegistryHelper.registerProperty(ModItems.LENS_MANA, "outputMode", (itemStack, clientWorld, livingEntity) -> (LensMana.getMode(itemStack)?1F:0F)));
    }
}
