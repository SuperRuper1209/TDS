package com.superruper1209.tds.Common.Blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class SimpleBlocks {
    public static final Block SPOON_PLANKS = new Block(AbstractBlock.Properties.of(Material.WOOD).harvestTool(ToolType.PICKAXE).harvestLevel(1).strength(2, 3)).setRegistryName("tds", "spoon_planks");
}
