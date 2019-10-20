package xyz.pixelatedw.MineMineNoMi3.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import xyz.pixelatedw.MineMineNoMi3.abilities.HakiAbilities;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.extra.AbilityProperties;
import xyz.pixelatedw.MineMineNoMi3.api.math.WyMathHelper;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedEntityData;
import xyz.pixelatedw.MineMineNoMi3.helpers.DevilFruitsHelper;
import xyz.pixelatedw.MineMineNoMi3.helpers.ItemsHelper;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;

public class EventsHakiGain
{
	@SubscribeEvent
	public void onEntityDeath(LivingDeathEvent event)
	{
		if (event.source.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.source.getEntity();
			ExtendedEntityData props = ExtendedEntityData.get(player);
			AbilityProperties abilityProps = AbilityProperties.get(player);
			EntityLivingBase target = event.entityLiving;
			
			ItemStack heldItem = player.getHeldItem();
			
			if(heldItem != null)
			{
				Ability imbuingBuso = abilityProps.getAbilityFromName(ListAttributes.BUSOSHOKU_HAKI_IMBUING.getAttributeName());
				boolean hasImbuingBusoActive = imbuingBuso != null && imbuingBuso.isPassiveActive();
				
				if(props.getImbuingHakiExp() <= 600 || hasImbuingBusoActive)
				{
					if(ItemsHelper.isSword(heldItem))
						props.addImbuingHakiExp((int) (3 + WyMathHelper.randomWithRange(0, 3)));
					else
						props.addImbuingHakiExp(1);
				}
			}
			else
			{
				Ability hardeningBuso = abilityProps.getAbilityFromName(ListAttributes.BUSOSHOKU_HAKI_HARDENING.getAttributeName());
				boolean hasHardeningBusoActive = hardeningBuso != null && hardeningBuso.isPassiveActive();
				
				if(props.getHardeningHakiExp() <= 600 || hasHardeningBusoActive)
				{
					props.addHardeningHakiExp((int) (6 + WyMathHelper.randomWithRange(0, 3)));
				}
			}
			
			if(props.getDoriki() > 4000 && props.getImbuingHakiExp() > 400 + WyMathHelper.randomWithRange(10, 50))
			{
				this.giveHakiAbility(abilityProps, HakiAbilities.BUSOSHOKU_HAKI_IMBUING);
			}
			
			if(props.getDoriki() > 3000 && props.getHardeningHakiExp() > 500 + WyMathHelper.randomWithRange(10, 100))
			{
				this.giveHakiAbility(abilityProps, HakiAbilities.BUSOSHOKU_HAKI_HARDENING);
				if(props.getHardeningHakiExp() > 800 + WyMathHelper.randomWithRange(10, 100))
				{
					this.giveHakiAbility(abilityProps, HakiAbilities.BUSOSHOKU_HAKI_FULL_BODY_HARDENING);
				}
			}
			
			System.out.println("Imbuing : " + props.getImbuingHakiExp());
			System.out.println("Hardening : " + props.getHardeningHakiExp());
			System.out.println("Observation : " + props.getObservationHakiExp());
			System.out.println("King : " + props.getKingHakiExp());
		}
	}
	
	private void giveHakiAbility(AbilityProperties abilityProps, Ability ability)
	{
		System.out.println(" " + (abilityProps.hasHakiAbility(ability)));
		if(!abilityProps.hasHakiAbility(ability) && !DevilFruitsHelper.verifyIfAbilityIsBanned(ability))
			abilityProps.addHakiAbility(ability);
	}
}
