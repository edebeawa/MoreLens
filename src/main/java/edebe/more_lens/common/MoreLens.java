package edebe.more_lens.common;

import edebe.more_lens.api.MoreLensAPI;
import edebe.more_lens.client.event.SetupEvent;
import edebe.more_lens.common.register.RegisterBlocks;
import edebe.more_lens.common.register.RegisterItems;
import edebe.more_lens.common.register.RegisterTiles;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(MoreLensAPI.MOD_ID)
public class MoreLens {
    public static final Logger LOGGER = LogManager.getLogger(MoreLensAPI.MOD_ID);
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation(MoreLensAPI.MOD_ID, MoreLensAPI.MOD_ID),
            () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

    public MoreLens() {
        FMLJavaModLoadingContext.get().getModEventBus().register(this);
        MinecraftForge.EVENT_BUS.register(new EdebeBotanyFMLBusEvents(this));

        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.register(this);
        modBus.addGenericListener(Block.class, RegisterBlocks::registerBlocks);
        modBus.addGenericListener(Item.class, RegisterItems::registerItems);
        modBus.addGenericListener(TileEntityType.class, RegisterTiles::registerTiles);
        modBus.addListener(this::clientLoad);
    }

    public void clientLoad(FMLClientSetupEvent event) {
        SetupEvent.init(event);
    }

    private static class EdebeBotanyFMLBusEvents {
        private final MoreLens parent;

        EdebeBotanyFMLBusEvents(MoreLens parent) {
            this.parent = parent;
        }
    }
}
