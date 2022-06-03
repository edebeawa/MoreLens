package edebe.more_lens.common.helper;

import edebe.more_lens.common.lib.LibItems;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

import static edebe.more_lens.common.helper.ResourceLocationHelper.prefix;

public interface RegistryHelper {
    static <V extends IForgeRegistryEntry<V>> void register(IForgeRegistry<V> registry, ResourceLocation name, IForgeRegistryEntry<V> thing) {
        registry.register(thing.setRegistryName(name));
    }

    static <V extends IForgeRegistryEntry<V>> void register(IForgeRegistry<V> registry, String name, IForgeRegistryEntry<V> thing) {
        register(registry, prefix(name), thing);
    }

    static <V extends IForgeRegistryEntry<V>> void register(IForgeRegistry<V> registry, Block block, IForgeRegistryEntry<V> thing) {
        register(registry, Registry.BLOCK.getKey(block), thing);
    }

    static <V extends IForgeRegistryEntry<V>> void register(IForgeRegistry<V> registry, Block block, Properties properties) {
        register(registry, block, ((IForgeRegistryEntry<V>)new BlockItem(block, properties)));
    }

    static void registerProperty(Item item, String resourceName, IItemPropertyGetter itemPropertyGetter) {
        ItemModelsProperties.registerProperty(item, new ResourceLocation(resourceName), itemPropertyGetter);
    }

}
