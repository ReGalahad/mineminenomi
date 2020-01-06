package xyz.pixelatedw.mineminenomi.abilities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.abilities.extra.ZoanAbility;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.abilitydata.IAbilityData;
import xyz.pixelatedw.mineminenomi.api.network.packets.server.SAbilityDataSyncPacket;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZoanInfoBisonHeavy;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZoanInfoBisonWalk;
import xyz.pixelatedw.mineminenomi.helpers.DevilFruitsHelper;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.init.ModValues;

public class UshiBisonAbilities
{

	static
	{
		ModValues.abilityWebAppExtraParams.put("bisonpowerpoint", new String[]
			{
					"desc", "The user transforms into a half-bison hybrid, which focuses on strength, Allows the user to use 'Kokutei Cross' and 'Fiddle Banff'"
			});
		ModValues.abilityWebAppExtraParams.put("bisonspeedpoint", new String[]
			{
					"desc", ""
			});
		ModValues.abilityWebAppExtraParams.put("kokuteicross", new String[]
			{
					"desc", "The transformed user crosses their hooves to hit the opponent."
			});
		ModValues.abilityWebAppExtraParams.put("fiddlebanff", new String[]
			{
					"desc", "The transformed user dashes towards the opponent to crash against them with great power."
			});
	}

	public static Ability[] abilitiesArray = new Ability[]
		{
				new PowerPoint(), new SpeedPoint(), new FiddleBanff(), new KokuteiCross()
		};

	public static class PowerPoint extends ZoanAbility
	{
		public PowerPoint()
		{
			super(ModAttributes.BISON_HEAVY_POINT, ZoanInfoBisonHeavy.FORM);
		}
	}

	public static class SpeedPoint extends ZoanAbility
	{
		public SpeedPoint()
		{
			super(ModAttributes.BISON_WALK_POINT, ZoanInfoBisonWalk.FORM);
		}

		@Override
		public void duringPassive(PlayerEntity player, int timer)
		{
			player.addPotionEffect(new EffectInstance(Effects.SPEED, 2 * 20, 1, false, false));

			if (player.getPosition().getX() - player.prevPosX < -0.6 || player.getPosition().getZ() - player.prevPosZ < -0.6)
			{
				double[] speed = DevilFruitsHelper.propulsion(player, 2, 2);

				for (LivingEntity e : WyHelper.getEntitiesNear(player, 0.5))
				{
					e.attackEntityFrom(DamageSource.causePlayerDamage(player), 2);
					e.setMotion(speed[0], e.getMotion().y, speed[1]);
				}
			}
		}
	}

	public static class FiddleBanff extends Ability
	{
		public FiddleBanff()
		{
			super(ModAttributes.FIDDLE_BANFF);
		}

		@Override
		public void use(PlayerEntity player)
		{
			IDevilFruit props = DevilFruitCapability.get(player);
			boolean canMorphFlag = DevilFruitsHelper.canMorph(player, ZoanInfoBisonHeavy.FORM, ZoanInfoBisonWalk.FORM);

			if (canMorphFlag && !this.isOnCooldown)
			{
				double[] speed = DevilFruitsHelper.propulsion(player, 5, 5);

				DevilFruitsHelper.changeMotion("=", speed[0], player.getMotion().y, speed[1], player);

				super.use(player);
			}
			else if (!canMorphFlag)
			{
				WyHelper.sendMsgToPlayer(player, "" + this.getAttribute().getAttributeName() + " can only be used while Heavy Point or Walk Point is active !");
			}
		}

		@Override
		public void duringCooldown(PlayerEntity player, int currentCooldown)
		{
			if (currentCooldown > 110)
				for (LivingEntity e : WyHelper.getEntitiesNear(player, 1.6))
					e.attackEntityFrom(DamageSource.causePlayerDamage(player), 6);
		}
	}

	public static class KokuteiCross extends Ability
	{
		public KokuteiCross()
		{
			super(ModAttributes.KOKUTEI_CROSS);
		}

		@Override
		public void startPassive(PlayerEntity player)
		{
			IDevilFruit props = DevilFruitCapability.get(player);
			IAbilityData abilityProps = AbilityDataCapability.get(player);
			boolean canMorphFlag = DevilFruitsHelper.canMorph(player, ZoanInfoBisonHeavy.FORM);

			if (!canMorphFlag)
			{
				this.setPassiveActive(false);
				ModNetwork.sendTo(new SAbilityDataSyncPacket(player.getEntityId(), abilityProps), (ServerPlayerEntity) player);
				WyHelper.sendMsgToPlayer(player, "" + this.getAttribute().getAttributeName() + " can only be used while Heavy Point is active !");
			}
		}

		@Override
		public void hitEntity(PlayerEntity player, LivingEntity target)
		{
			IDevilFruit props = DevilFruitCapability.get(player);
			boolean canMorphFlag = DevilFruitsHelper.canMorph(player, ZoanInfoBisonHeavy.FORM);

			if (canMorphFlag)
			{
				super.hitEntity(player, target);
				target.attackEntityFrom(DamageSource.causePlayerDamage(player), 20);
			//	ModNetwork.sendToAllAround(new SParticlesPacket(ID.PARTICLEFX_KOKUTEICROSS, target), player);

				double[] speed = DevilFruitsHelper.propulsion(player, -0.7, -0.7);

				DevilFruitsHelper.changeMotion("=", speed[0], player.getMotion().y, speed[1], player);
			}
			else
			{
				this.setPassiveActive(false);
				WyHelper.sendMsgToPlayer(player, "" + this.getAttribute().getAttributeName() + " can only be used while Heavy Point is active !");
			}
		}
	}

}
