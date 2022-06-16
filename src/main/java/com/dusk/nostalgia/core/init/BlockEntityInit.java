package com.dusk.nostalgia.core.init;

import com.dusk.nostalgia.nostalgia;
import com.dusk.nostalgia.common.block.entity.NostalgiaCraftingBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityInit {
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, nostalgia.MOD_ID);
	
	public static final RegistryObject<BlockEntityType<NostalgiaCraftingBlockEntity>> NOSTALGIA_CRAFTING_TABLE = BLOCK_ENTITIES
            .register("example_chest", () -> BlockEntityType.Builder
                    .of(NostalgiaCraftingBlockEntity::new, BlockInit.NOSTALGIA_CRAFTING_TABLE.get()).build(null));
	
	private BlockEntityInit() {
	}
}
