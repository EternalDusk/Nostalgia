package com.dusk.nostalgia.core.init;

import com.dusk.nostalgia.nostalgia;
import com.dusk.nostalgia.common.block.NostalgiaCraftingTableBlock;
import com.dusk.nostalgia.common.container.NostalgiaCraftingMenu;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ContainerInit {
	
	public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, nostalgia.MOD_ID);
	
	public static final RegistryObject<MenuType<NostalgiaCraftingMenu>> NOSTALGIA_CRAFTING_MENU = CONTAINERS.register("nostalgia_crafting_menu", () -> new MenuType<>(NostalgiaCraftingMenu::new));
	
	private ContainerInit() {
	}
	
}
