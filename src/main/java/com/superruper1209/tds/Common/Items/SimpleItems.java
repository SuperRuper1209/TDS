package com.superruper1209.tds.Common.Items;

import com.superruper1209.tds.Common.Blocks.*;
import com.superruper1209.tds.Common.Entities.TheWalkingBlock;
import net.minecraft.item.*;
import net.minecraftforge.common.ToolType;

public class SimpleItems {
    public static final Item SPOON_STICK = new Item(new Item.Properties().addToolType(ToolType.PICKAXE, 0).tab(ItemGroup.TAB_MISC)).setRegistryName("tds", "spoon_stick");
    public static final Item COMICALLY_LARGE_SPOON = new SwordItem(ItemTier.IRON, 3, -1.4F, (new Item.Properties()).tab(ItemGroup.TAB_COMBAT)).setRegistryName("tds", "comically_large_spoon");

    public static final Item MONSTER_EGG = new SpawnEggItem(TheWalkingBlock.ENTRY, 50, 80, new Item.Properties().tab(ItemGroup.TAB_MISC)).setRegistryName("tds", "walkingblock_spawnegg");
    public static final Item BED_TNT = new BlockItem(BedTntBlock.ENTRY.getBlock(), new Item.Properties().tab(ItemGroup.TAB_REDSTONE)).setRegistryName("tds", "bed_tnt");
    public static final Item ANCHOR_TNT = new BlockItem(AnchorTntBlock.ENTRY.getBlock(), new Item.Properties().tab(ItemGroup.TAB_REDSTONE)).setRegistryName("tds", "anchor_tnt");
    public static final Item SPOON_LOG = new BlockItem(SpoonLog.ENTRY.getBlock(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)).setRegistryName("tds", "spoon_log");
    public static final Item SPOON_LEAVES = new BlockItem(SpoonLeaves.ENTRY.getBlock(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)).setRegistryName("tds", "spoon_leaves");
    public static final Item SPOON_PLANKS = new BlockItem(SimpleBlocks.SPOON_PLANKS.getBlock(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)).setRegistryName("tds", "spoon_planks");
    public static final Item STOOL = new BlockItem(StoolBlock.ENTRY.getBlock(), new Item.Properties().tab(ItemGroup.TAB_DECORATIONS)).setRegistryName("tds", "stool");
}
