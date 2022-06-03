package edebe.more_lens.common.lib;

import edebe.more_lens.common.block.BlockObsidianFrameGlass;
import edebe.more_lens.common.block.mana.BlockManaCleanse;
import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class LibBlocks {
    public static final Block MANA_CLEANSE = new BlockManaCleanse(Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2, 2000));
    public static final Block OBSIDIAN_FRAME_GLASS = new BlockObsidianFrameGlass(Properties.create(Material.GLASS).sound(SoundType.GLASS).hardnessAndResistance(8f, 2048f).harvestLevel(3).harvestTool(ToolType.PICKAXE).setRequiresTool().notSolid().setOpaque((bs, br, bp) -> false));
}
