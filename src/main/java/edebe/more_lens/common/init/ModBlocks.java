package edebe.more_lens.common.init;

import edebe.more_lens.common.block.BlockObsidianFrameGlass;
import edebe.more_lens.common.block.mana.BlockManaBox;
import edebe.more_lens.common.block.mana.BlockManaCleanse;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

import static edebe.more_lens.common.helper.RegistryHelper.register;

public class ModBlocks {
    private static final AbstractBlock.IExtendedPositionPredicate<EntityType<?>> NO_SPAWN = (state, world, pos, et) -> false;

    public static final Block MANA_CLEANSE = new BlockManaCleanse(Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2, 2000));
    public static final Block OBSIDIAN_FRAME_GLASS = new BlockObsidianFrameGlass(Properties.create(Material.GLASS).sound(SoundType.GLASS).hardnessAndResistance(8f, 2048f).harvestLevel(3).harvestTool(ToolType.PICKAXE).setRequiresTool().notSolid().setOpaque((bs, br, bp) -> false));
    public static final Block MANA_BOX = new BlockManaBox(Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2, 2000).harvestLevel(1).harvestTool(ToolType.PICKAXE).setRequiresTool().notSolid());

    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();
        register(registry, "mana_cleanse", MANA_CLEANSE);
        register(registry, "obsidian_frame_glass", OBSIDIAN_FRAME_GLASS);
        register(registry, "mana_box", MANA_BOX);
    }
}
