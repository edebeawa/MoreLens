package edebe.more_lens.common;

import edebe.more_lens.common.event.lifecycle.ClientSetup;
import edebe.more_lens.common.init.ModBlocks;
import edebe.more_lens.common.init.ModEnchantments;
import edebe.more_lens.common.init.ModItems;
import edebe.more_lens.common.init.ModTiles;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(MoreLens.MODID)
public class MoreLens {
    private static final String PROTOCOL_VERSION = "1";

    public static final String MODID = "more_lens";
    public static final Logger LOGGER = LogManager.getLogger(MoreLens.MODID);
    public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation(MoreLens.MODID, MoreLens.MODID), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);

    public MoreLens() {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new MoreLensFMLBusEvents(this));

        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.register(this);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, ()->()->modBus.addListener(ClientSetup::init));
        modBus.addGenericListener(Block.class, ModBlocks::registerBlocks);
        modBus.addGenericListener(Item.class, ModItems::registerItems);
        modBus.addGenericListener(TileEntityType.class, ModTiles::registerTiles);
        modBus.addGenericListener(Enchantment.class, ModEnchantments::registerEnchantments);
    }

    private static class MoreLensFMLBusEvents {
        private final MoreLens parent;

        MoreLensFMLBusEvents(MoreLens parent) {
            this.parent = parent;
        }
    }
}
