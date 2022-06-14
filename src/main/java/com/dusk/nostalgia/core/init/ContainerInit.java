package com.dusk.nostalgia.core.init;

import com.dusk.nostalgia.nostalgia;
import com.dusk.nostalgia.common.container.NostalgiaCraftingContainer;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ContainerInit {
	
	public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, nostalgia.MOD_ID);
	
	public static final RegistryObject<MenuType<NostalgiaCraftingContainer>> NOSTALGIA_CRAFTING_TABLE = CONTAINERS.register("nostalgia_crafting_table", () -> new MenuType<>(NostalgiaCraftingContainer::new));
	
	private ContainerInit() {
			
	}
	
}
