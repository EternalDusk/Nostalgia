package com.dusk.nostalgia;

import com.dusk.nostalgia.core.init.BlockEntityInit;
import com.dusk.nostalgia.core.init.BlockInit;
import com.dusk.nostalgia.core.init.ContainerInit;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("nostalgia")
public class nostalgia {
	
	public static final String MOD_ID = "nostalgia";
	
	public nostalgia() {
		
		final var bus = FMLJavaModLoadingContext.get().getModEventBus();
		
		MinecraftForge.EVENT_BUS.register(this);
		ContainerInit.CONTAINERS.register(bus);
        BlockInit.BLOCKS.register(bus);
        BlockEntityInit.BLOCK_ENTITIES.register(bus);
	}
}
