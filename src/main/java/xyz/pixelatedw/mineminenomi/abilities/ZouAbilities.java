package xyz.pixelatedw.mineminenomi.abilities;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.ID;
import xyz.pixelatedw.mineminenomi.abilities.extra.ZoanAbility;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.abilities.extra.AbilityExplosion;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.ZouProjectiles;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZoanInfoZouGuard;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZoanInfoZouHeavy;
import xyz.pixelatedw.mineminenomi.helpers.DevilFruitsHelper;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.packets.server.SParticlesPacket;

public class ZouAbilities
{

	public static Ability[] abilitiesArray = new Ability[] {new ZouPoint(), new HybridPoint(), new IvoryDart(), new IvoryStomp(), new GreatStomp(), new TrunkShot()};
	
	public static class TrunkShot extends Ability
	{
		public TrunkShot()
		{
			super(ModAttributes.TRUNK_SHOT);
		}
		
		@Override
		public void use(PlayerEntity player)
		{	
			IDevilFruit props = DevilFruitCapability.get(player);
			boolean canMorphFlag = DevilFruitsHelper.canMorph(player, ZoanInfoZouGuard.FORM, ZoanInfoZouHeavy.FORM);

			if(!canMorphFlag)
			{
				WyHelper.sendMsgToPlayer(player, "" + this.getAttribute().getAttributeName() + " can only be used while Guard Point or Heavy Point are active !");	
				return;
			}
			
			this.projectile = new ZouProjectiles.TrunkShot(player.world, player, ModAttributes.TRUNK_SHOT);			
			super.use(player);	
		}
	}
	
	public static class GreatStomp extends Ability
	{
		public GreatStomp() 
		{
			super(ModAttributes.GREAT_STOMP); 
		}	
		
		@Override
		public void use(PlayerEntity player) 
		{
			IDevilFruit props = DevilFruitCapability.get(player);
			boolean canMorphFlag = DevilFruitsHelper.canMorph(player, ZoanInfoZouGuard.FORM);

			if(!canMorphFlag)
			{
				WyHelper.sendMsgToPlayer(player, "" + this.getAttribute().getAttributeName() + " can only be used while Guard Point is active !");	
				return;
			}
			
			if(!this.isOnCooldown)
			{
				ModNetwork.sendToAllAround(new SParticlesPacket(ID.PARTICLEFX_GREATSTOMP, player.posX, player.posY, player.posZ), player);

				for(LivingEntity entity : WyHelper.getEntitiesNear(player, 10))
				{
					entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 10);
					entity.setPositionAndUpdate(entity.posX, entity.posY + 3, entity.posZ);
				}
				
				super.use(player);
			}
		}
	}
	
	public static class IvoryStomp extends Ability
	{
		public IvoryStomp() 
		{
			super(ModAttributes.IVORY_STOMP); 
		}	
		
		@Override
		public void passive(PlayerEntity player)
		{
			IDevilFruit props = DevilFruitCapability.get(player);
			boolean canMorphFlag = DevilFruitsHelper.canMorph(player, ZoanInfoZouHeavy.FORM);

			if(!canMorphFlag)
				WyHelper.sendMsgToPlayer(player, "" + this.getAttribute().getAttributeName() + " can only be used while Heavy Point is active !");	
			else
				super.passive(player);
		}
		
		@Override
		public void hitEntity(PlayerEntity player, LivingEntity target) 
		{
			IDevilFruit props = DevilFruitCapability.get(player);
			boolean canMorphFlag = DevilFruitsHelper.canMorph(player, ZoanInfoZouHeavy.FORM);

			if(canMorphFlag)
			{
				super.hitEntity(player, target);
				target.attackEntityFrom(DamageSource.causePlayerDamage(player), 20);
				AbilityExplosion explosion = WyHelper.newExplosion(target, target.posX, target.posY, target.posZ, 1);
				explosion.setExplosionSound(false);
				explosion.setDamageOwner(false);
				explosion.setDestroyBlocks(false);
				explosion.doExplosion();
				
				double[] speed = DevilFruitsHelper.propulsion(player, -2.7, -2.7);
				
				target.setMotion(speed[0], 0.1, speed[1]);
			}
			else
			{
				this.setPassiveActive(false);
				WyHelper.sendMsgToPlayer(player, "" + this.getAttribute().getAttributeName() + " can only be used while Heavy Point is active !");
			}
		}
	}
	
	public static class IvoryDart extends Ability
	{
		private int initialY;
		
		public IvoryDart() 
		{
			super(ModAttributes.IVORY_DART); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{
			IDevilFruit props = DevilFruitCapability.get(player);
			boolean canMorphFlag = DevilFruitsHelper.canMorph(player, ZoanInfoZouGuard.FORM);

			if(canMorphFlag && !this.isOnCooldown)
			{
				this.initialY = (int) player.posY;
				
				double[] speed = DevilFruitsHelper.propulsion(player, 2.7, 2.7);

				DevilFruitsHelper.changeMotion("=", speed[0], player.getMotion().y, speed[1], player);			
			}
			else if(!canMorphFlag )
			{
				WyHelper.sendMsgToPlayer(player, "" + this.getAttribute().getAttributeName() + " can only be used while Guard Point is active !");
			}
			
			super.use(player);
		}
		
	    @Override
		public void duringCooldown(PlayerEntity player, int currentCooldown)
	    {
			if(currentCooldown > 180 && player.posY >= this.initialY)
			{
				for(LivingEntity e : WyHelper.getEntitiesNear(player, 1.6))
					e.attackEntityFrom(DamageSource.causePlayerDamage(player), 12);
				
				for(int[] location : WyHelper.getBlockLocationsNearby(player, 3))
				{
					if(location[1] >= player.posY)
					{
						if(WyHelper.placeBlockIfAllowed(player.world, location[0], location[1], location[2], Blocks.AIR, "core", "foliage"))
						{
							ModNetwork.sendToAllAround(new SParticlesPacket(ID.PARTICLEFX_BAKUMUNCH, location[0], location[1], location[2]), player);
						}
					}
				}
			}
	    }
	}
	
	public static class ZouPoint extends ZoanAbility
	{
		public ZouPoint()
		{
			super(ModAttributes.ZOU_GUARD_POINT, ZoanInfoZouGuard.FORM);
		}
		
		@Override
		public void duringPassive(PlayerEntity player, int timer)
		{
			player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 2 * 20, 2, false, false));
			player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 2 * 20, 0, false, false));
		}
	}
	
	public static class HybridPoint extends ZoanAbility
	{
		public HybridPoint()
		{
			super(ModAttributes.ZOU_HEAVY_POINT, ZoanInfoZouHeavy.FORM);
		}
		
		@Override
		public void duringPassive(PlayerEntity player, int timer)
		{
			player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 2 * 20, 0, false, false));
		}
	}
	
}
