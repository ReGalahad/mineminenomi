package xyz.pixelatedw.mineminenomi.abilities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModValues;

public class HakiAbilities 
{		
	static
	{
		ModValues.abilityWebAppExtraParams.put("busoshokuhaki", new String[] {"desc", "The user forms an invisible armor around themselves using their willpower, By using this form of Haki, the user can damage Logias.", "dorikiRequiredForHumans", "9000", "dorikiRequiredForFishman", "9000", "dorikiRequiredForCyborgs", "8500"});
		ModValues.abilityWebAppExtraParams.put("kenbunshokuhaki", new String[] {"desc", "Allows the user to sense the presence of others, pointing them to the opponent, Can also locate invisible mobs and players.", "dorikiRequiredForHumans", "5000", "dorikiRequiredForFishman", "4000", "dorikiRequiredForCyborgs", "5500"});
	}
	
	public static Ability KENBUNSHOKU_HAKI = new KenbunshokuHaki();
	public static Ability BUSOSHOKU_HAKI = new BusoshokuHaki();
	
	public static Ability[] abilitiesArray = new Ability[] {KENBUNSHOKU_HAKI, BUSOSHOKU_HAKI};
	
	public static class KenbunshokuHaki extends Ability
	{
		private int hakiTimer = 0;
		
		public KenbunshokuHaki() 
		{
			super(ModAttributes.KENBUNSHOKU_HAKI); 
		}

		@Override
		public void duringPassive(PlayerEntity player, int timer)
		{
			this.hakiTimer++;
			if(this.hakiTimer > 2400)
			{
				player.addPotionEffect(new EffectInstance(Effects.NAUSEA, 100, 0));
				player.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 100, 0));
			}
			else if(this.hakiTimer > 3600)
			{
				player.addPotionEffect(new EffectInstance(Effects.NAUSEA, 100, 5));
				player.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 100, 5));
				player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 100, 5));
				player.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 100, 5));
			}
		}
		
		@Override
		public void tick(PlayerEntity player)
		{
			if(this.hakiTimer > 0)
				this.hakiTimer--;
		}
	}
	
	public static class BusoshokuHaki extends Ability
	{
		private int hakiTimer = 0;
		
		public BusoshokuHaki() 
		{
			super(ModAttributes.BUSOSHOKU_HAKI); 			
		}
		
		@Override
		public void duringPassive(PlayerEntity player, int timer)
		{
			this.hakiTimer++;
			if(this.hakiTimer > 2400)
			{
				player.addPotionEffect(new EffectInstance(Effects.NAUSEA, 100, 0));
				player.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 100, 0));
			}
			else if(this.hakiTimer > 3600)
			{
				player.addPotionEffect(new EffectInstance(Effects.NAUSEA, 100, 5));
				player.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 100, 5));
				player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 100, 5));
				player.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 100, 5));
			}
		}
		
		@Override
		public void tick(PlayerEntity player)
		{
			if(this.hakiTimer > 0)
				this.hakiTimer--;
		}
	}
}
