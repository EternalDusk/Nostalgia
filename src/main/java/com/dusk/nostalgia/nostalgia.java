package com.dusk.nostalgia;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod("nostalgia")
public class nostalgia {
	
	public static final String MOD_ID = "nostalgia";
	
	public nostalgia() {
		MinecraftForge.EVENT_BUS.register(this);
	}
}
