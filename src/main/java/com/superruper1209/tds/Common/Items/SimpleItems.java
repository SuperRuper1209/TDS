package com.superruper1209.tds.Common.Items;

import com.superruper1209.tds.Common.Blocks.AnchorTntBlock;
import com.superruper1209.tds.Common.Blocks.BedTntBlock;
import com.superruper1209.tds.Common.Blocks.Stool;
import com.superruper1209.tds.Common.Entities.TheWalkingBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;

public class SimpleItems {
    public static final Item MONSTER_EGG = new SpawnEggItem(TheWalkingBlock.ENTRY, 50, 80, new Item.Properties().group(ItemGroup.MISC)).setRegistryName("tds", "walkingblock_spawnegg");
    public static final Item STOOL = new BlockItem(Stool.ENTRY.getBlock(), new Item.Properties().group(ItemGroup.DECORATIONS).maxStackSize(1)).setRegistryName("tds", "stool");
    public static final Item BED_TNT = new BlockItem(BedTntBlock.ENTRY.getBlock(), new Item.Properties().group(ItemGroup.REDSTONE)).setRegistryName("tds", "bed_tnt");
    public static final Item ANCHOR_TNT = new BlockItem(AnchorTntBlock.ENTRY.getBlock(), new Item.Properties().group(ItemGroup.REDSTONE)).setRegistryName("tds", "anchor_tnt");
}
