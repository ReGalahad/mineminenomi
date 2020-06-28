package xyz.pixelatedw.mineminenomi.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class ClientConfig
{
	public static ClientConfig instance;

	private Map<String, ForgeConfigSpec.BooleanValue> cooldownVisual;

	public static void init()
	{
		Pair<ClientConfig, ForgeConfigSpec> pair = new ForgeConfigSpec.Builder().configure(ClientConfig::new);
		ForgeConfigSpec configSpec = pair.getRight();
		ClientConfig.instance = pair.getLeft();
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, configSpec);
	}

	public ClientConfig(ForgeConfigSpec.Builder builder)
	{
		builder.push("General");
		{
			builder.push("Cooldown Visuals");
			{
				String[] cooldownVisuals = new String[] { "Text", "Color" };
				this.cooldownVisual = new HashMap<String, ForgeConfigSpec.BooleanValue>();

				for (String mode : cooldownVisuals)
					this.cooldownVisual.put(mode, builder.define(mode, true));
			}
			builder.pop();
		}
	}
	
	public String[] getCooldownVisuals()
	{
		String[] newArray = new String[] {};
		return this.cooldownVisual.keySet().toArray(newArray);
	}
}
