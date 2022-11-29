package edebe.more_lens.common.init;

import edebe.more_lens.common.block.BlockObsidianFrameGlass;
import edebe.more_lens.common.block.mana.BlockManaCleanse;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;

import static edebe.more_lens.common.helper.RegistryHelper.register;

public final class ModBlocks {
    public static final Block MANA_CLEANSE = new BlockManaCleanse(Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2, 2000));
    public static final Block OBSIDIAN_FRAME_GLASS = new BlockObsidianFrameGlass(Properties.create(Material.GLASS).sound(SoundType.GLASS).hardnessAndResistance(8f, 2048f).harvestLevel(3).harvestTool(ToolType.PICKAXE).setRequiresTool().notSolid().setAllowsSpawn(ModBlocks::neverAllowSpawn).setOpaque(ModBlocks::isNotSolid).setSuffocates(ModBlocks::isNotSolid).setBlocksVision(ModBlocks::isNotSolid));

    private static Boolean neverAllowSpawn(BlockState state, IBlockReader reader, BlockPos pos, EntityType<?> entity) {
        return false;
    }

    private static boolean isNotSolid(BlockState state, IBlockReader reader, BlockPos pos) {
        return false;
    }

    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> registry = event.getRegistry();
        register(registry, "mana_cleanse", MANA_CLEANSE);
        register(registry, "obsidian_frame_glass", OBSIDIAN_FRAME_GLASS);
    }
}