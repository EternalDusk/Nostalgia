package com.dusk.nostalgia.core.init;

import com.dusk.nostalgia.nostalgia;
import com.dusk.nostalgia.common.block.NostalgiaCraftingTableBlock;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, nostalgia.MOD_ID);
	
	public static final RegistryObject<NostalgiaCraftingTableBlock> NOSTALGIA_CRAFTING_TABLE = BLOCKS.register("nostalgia_crafting_table", () -> new NostalgiaCraftingTableBlock(BlockBehaviour.Properties.copy(Blocks.CRAFTING_TABLE)));
	
	private BlockInit() {
	}
	
}
