package xyz.pixelatedw.mineminenomi.helpers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Map;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.pixelatedw.mineminenomi.Env;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.debug.WyDebug;
import xyz.pixelatedw.mineminenomi.init.ModValues;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;

public class WebAppHelper
{
	
	public static void generateWebAppJSONs()
	{
		if(!WyDebug.isDebug())
			return;
		
		writeFancyAbilitiesList();

		File folder = new File(Env.projectResourceFolder + "/assets/" + Env.PROJECT_ID + "/EXTRA_BOT_FILES/");
		folder.mkdirs();

		if (folder.exists())
		{
			writeDevilFruitsJSON();

			writeSpecialAbilitiesJSON();
		}
	}

	private static void writeDevilFruitsJSON()
	{
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Env.projectResourceFolder + "/assets/" + Env.PROJECT_ID + "/EXTRA_BOT_FILES/devilfruits.json"), "UTF-8")))
		{
			writer.write("{\n");
			writer.write("\"devilfruits\" : [");

			for (Item f : ModValues.devilfruits)
			{
				AkumaNoMiItem fruit = (AkumaNoMiItem) f;
				String fullName = fruit.getName().getFormattedText();
				ItemStack itemStack = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(Env.PROJECT_ID, fullName)));
				Map<String, Object> devilFruitElements = new LinkedHashMap<String, Object>();

				devilFruitElements.put("name", "\"" + itemStack.getDisplayName().getFormattedText() + "\"");
				devilFruitElements.put("type", "\"" + fruit.getType().getName() + "\"");
				devilFruitElements.put("abilities", "[ " + generateAbilitiesString(fruit.abilities) + " ]");

				writer.write("{ ");
				for (String devilFruitKey : devilFruitElements.keySet())
				{
					Object key = devilFruitElements.get(devilFruitKey);
					if (key instanceof String)
						writer.write("\"" + devilFruitKey + "\": " + key + ", ");

				}
				writer.write("},\n");
			}

			writer.write("]}");

			writer.close();
		}
		catch (Exception e)
		{
			e.getStackTrace();
		}
	}

	private static void writeSpecialAbilitiesJSON()
	{
		/*try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Env.projectResourceFolder + "/assets/" + Env.PROJECT_ID + "/EXTRA_BOT_FILES/specialabilities.json"), "UTF-8")))
		{

			writer.write("{\n");
			writer.write("\"specialabilities\" : [");

			// Human Collection
			Ability[] humanAbilities = Stream.of(RokushikiAbilities.abilitiesArray, HakiAbilities.abilitiesArray).flatMap(Stream::of).toArray(Ability[]::new);
			writer.write("{ \"name\": \"Human\", \"type\": \"n/a\", \"abilities\": [ " + generateAbilitiesString(humanAbilities) + " ]},\n");

			// Fishman Collection
			Ability[] fishmanAbilities = Stream.of(FishKarateAbilities.abilitiesArray, HakiAbilities.abilitiesArray).flatMap(Stream::of).toArray(Ability[]::new);
			writer.write("{ \"name\": \"Fishman\", \"type\": \"n/a\", \"abilities\": [ " + generateAbilitiesString(fishmanAbilities) + " ]},\n");

			// Cybord Collection
			Ability[] cyborgAbilities = Stream.of(CyborgAbilities.abilitiesArray, HakiAbilities.abilitiesArray).flatMap(Stream::of).toArray(Ability[]::new);
			writer.write("{ \"name\": \"Cyborg\", \"type\": \"n/a\", \"abilities\": [ " + generateAbilitiesString(cyborgAbilities) + " ]},\n");

			// Swordsman Collection
			writer.write("{ \"name\": \"Swordsman\", \"type\": \"n/a\", \"abilities\": [ " + generateAbilitiesString(SwordsmanAbilities.abilitiesArray) + " ]},\n");

			// Sniper Collection
			writer.write("{ \"name\": \"Sniper\", \"type\": \"n/a\", \"abilities\": [ " + generateAbilitiesString(SniperAbilities.abilitiesArray) + " ]},\n");

			writer.write("]}");

			writer.close();
		}
		catch (Exception e)
		{
			e.getStackTrace();
		}*/
	}

	private static void writeFancyAbilitiesList()
	{
		File folder = new File(Env.projectResourceFolder + "/assets/" + Env.PROJECT_ID + "/EXTRA_BOT_FILES/");
		folder.mkdirs();

		if (folder.exists())
		{
			try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Env.projectResourceFolder + "/assets/" + Env.PROJECT_ID + "/EXTRA_BOT_FILES/fancylist.txt"), "UTF-8")))
			{
				for (AkumaNoMiItem devilFruit : ModValues.devilfruits)
				{
					writer.write(devilFruit.getDisplayName(new ItemStack(devilFruit)).getFormattedText() + "\n");
					for (Ability ability : devilFruit.abilities)
					{
						writer.write("> " + ability.getName() + "\n");
					}
					writer.write("\n");
				}
			}
			catch (Exception e)
			{
				e.getStackTrace();
			}
		}
	}

	private static String generateAbilitiesString(Ability[] abilities)
	{
		return "";
/*		StringBuilder abilitiesString = new StringBuilder();

		for (Ability ability : abilities)
		{
			boolean hasDescription = false;
			StringBuilder abilityString = new StringBuilder();
			abilityString.append("{ ");

			Map<String, Object> loadedParams = new LinkedHashMap<String, Object>();
			AbilityAttribute abilityAttribute = ability.getAttribute();

			
			loadedParams.put("name", abilityAttribute.getAbilityDisplayName());
			loadedParams.put("texture", WyHelper.getResourceName(abilityAttribute.getAbilityTexture()));

			if (abilityAttribute.getAbilityCooldown() > 0)
				loadedParams.put("cooldown", abilityAttribute.getAbilityCooldown() / 20);
			if (abilityAttribute.getAbilityCharges() > 0)
				loadedParams.put("chargeTime", abilityAttribute.getAbilityCharges() / 20);
			if (abilityAttribute.getProjectileDamage() > 1)
				loadedParams.put("projectileDamage", abilityAttribute.getProjectileDamage());
			if (abilityAttribute.hasProjectile() && abilityAttribute.isRepeater())
				loadedParams.put("projectileNumber", (abilityAttribute.getAbilityCooldown() / abilityAttribute.getAbilityRepeaterTime()) / abilityAttribute.getAbilityRepeaterTime());
			if (abilityAttribute.getProjectileExplosionPower() > 0)
				loadedParams.put("projectileExplosion", abilityAttribute.getProjectileExplosionPower());

			if (abilityAttribute.getPotionEffectsForAoE() != null && abilityAttribute.getPotionEffectsForAoE().length > 0)
				loadedParams.put("aoeEffects", "[" + getPotionEffectsFor(abilityAttribute.getPotionEffectsForAoE()) + "]");
			if (abilityAttribute.getPotionEffectsForProjectile() != null && abilityAttribute.getPotionEffectsForProjectile().length > 0)
				loadedParams.put("onHitEffects", "[" + getPotionEffectsFor(abilityAttribute.getPotionEffectsForProjectile()) + "]");
			if (abilityAttribute.getPotionEffectsForUser() != null && abilityAttribute.getPotionEffectsForUser().length > 0)
				loadedParams.put("selfEffects", "[" + getPotionEffectsFor(abilityAttribute.getPotionEffectsForUser()) + "]");

			for (String manualParamKey : ModValues.abilityWebAppExtraParams.keySet())
			{
				if (WyHelper.getResourceName(abilityAttribute.getAttributeName()).equalsIgnoreCase(manualParamKey))
				{
					String[] params = ModValues.abilityWebAppExtraParams.get(manualParamKey);

					for (int j = 0; j < params.length; j++)
					{
						String param = params[j];
						Object paramValue = params[++j];

						if(param.equalsIgnoreCase("desc"))
							hasDescription = true;
						
						try
						{
							paramValue = Integer.parseInt((String) paramValue);
						}
						catch (Exception e)
						{
						}

						if (loadedParams.containsKey(param))
							loadedParams.replace(param, paramValue);
						else
							loadedParams.put(param, paramValue);
					}
				}
			}

			for (String loadedParamKey : loadedParams.keySet())
			{
				Object key = loadedParams.get(loadedParamKey);
				if ((key instanceof Integer || key instanceof Double || key instanceof Float) || (loadedParamKey.equalsIgnoreCase("aoeEffects") || loadedParamKey.equalsIgnoreCase("onHitEffects") || loadedParamKey.equalsIgnoreCase("selfEffects")))
					abilityString.append("\"" + loadedParamKey + "\": " + key + ",");
				else if (loadedParams.get(loadedParamKey) instanceof String)
					abilityString.append("\"" + loadedParamKey + "\": \"" + key + "\",");
			}

			abilityString.append("},");
			abilitiesString.append(abilityString.toString());
			
			if(!hasDescription)
				System.out.println("Ability without description : " + ability.getName());
		}

		return abilitiesString.toString();*/
	}

	private static String getPotionEffectsFor(EffectInstance[] pe)
	{
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < pe.length; i++)
		{
			double d = Math.ceil(((double) pe[i].getDuration() / 24));

			if (i < pe.length - 1)
				builder.append("\"" + I18n.format(pe[i].getEffectName()) + " " + String.format("%.0f", d) + " " + (d == 1 ? "second" : "seconds") + " (" + (pe[i].getAmplifier() > 0 ? "+" : "-") + ")\", ");
			else
				builder.append("\"" + I18n.format(pe[i].getEffectName()) + " " + String.format("%.0f", d) + " " + (d == 1 ? "second" : "seconds") + " (" + (pe[i].getAmplifier() > 0 ? "+" : "-") + ")\"");
		}
		String potionList = builder.toString();

		return potionList;
	}

}
