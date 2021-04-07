package com.superruper1209.tds.Common.Items;

import com.superruper1209.tds.Common.Blocks.Stool;
import com.superruper1209.tds.Common.Entities.TheWalkingBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;

public class SimpleItems {
    public static final Item MONSTER_EGG = new SpawnEggItem(TheWalkingBlock.ENTRY, 50, 80, new Item.Properties().group(ItemGroup.MISC)).setRegistryName("tds", "walkingblock_spawnegg");
    public static final Item STOOL = new BlockItem(Stool.ENTRY.getBlock(), new Item.Properties().group(ItemGroup.DECORATIONS).maxStackSize(1)).setRegistryName("tds", "sit");
}
