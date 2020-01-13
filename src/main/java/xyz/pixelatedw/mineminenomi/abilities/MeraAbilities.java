package xyz.pixelatedw.mineminenomi.abilities;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.MeraProjectiles;
import xyz.pixelatedw.mineminenomi.init.ModValues;

public class MeraAbilities
{
	static
	{
		ModValues.abilityWebAppExtraParams.put("hiken", new String[] {"desc", "Turns the user's fist into flames and launches it towards the target."});
		ModValues.abilityWebAppExtraParams.put("higan", new String[] {"desc", "Turns the user's fingertips into flames and shoots bullets made of fire from them."});
		ModValues.abilityWebAppExtraParams.put("hidaruma", new String[] {"desc", "Creates small green fireballs that set the target on fire."});
		ModValues.abilityWebAppExtraParams.put("jujika", new String[] {"desc", "Launches a cross-shaped column of fire at the opponent, leaving a cross of fire."});
		ModValues.abilityWebAppExtraParams.put("enjomo", new String[] {"desc", "Creates a circle of fire around the user, burning everyone inside of it."});
		ModValues.abilityWebAppExtraParams.put("daienkaientei", new String[] {"desc", "Amasses the user's flames into a gigantic fireball that the user hurls at the opponent."});
	}

	public static class Higan extends Ability
	{
		public Higan() 
		{
			super("Higan", Category.DEVIL_FRUIT);
			this.setMaxCooldown(4);
			this.setDescription("Turns the user's fingertips into flames and shoots bullets made of fire from them.");

			this.onUseEvent = this::onUseEvent;
		}
		
		private void onUseEvent(PlayerEntity player, Ability ability)
		{
			MeraProjectiles.Higan proj = new MeraProjectiles.Higan(player.world, player);
			player.world.addEntity(proj);
			proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);		
		}
	}

/*	public static class DaiEnkaiEntei extends Ability
	{
		public DaiEnkaiEntei() 
		{
			super(ModAttributes.DAI_ENKAI_ENTEI); 
		}
		
		@Override
		public void startCharging(PlayerEntity player)
		{
			super.startCharging(player);
		}
		
		@Override
		public void duringCharging(PlayerEntity player, int currentCharge)
		{
			//ModNetwork.sendToAllAround(new SParticlesPacket(ID.PARTICLEFX_DAIENKAI2, player), (ServerPlayerEntity) player);
		}
		
		@Override
		public void endCharging(PlayerEntity player)
		{						
			this.projectile = new MeraProjectiles.DaiEnkaiEntei(player.world, player, ModAttributes.DAI_ENKAI_ENTEI);
			super.endCharging(player);
		}
	
	}

	public static class Hidaruma extends Ability
	{
		public Hidaruma() 
		{
			super(ModAttributes.HIDARUMA); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{
			this.projectile = new MeraProjectiles.Hidaruma(player.world, player, ModAttributes.HIDARUMA);
			super.use(player);
		};			
	}

	public static class Jujika extends Ability
	{
		public Jujika() 
		{
			super(ModAttributes.JUJIKA); 
		}
		
		@Override
		public void use(PlayerEntity player)
		{
			this.projectile = new MeraProjectiles.Jujika(player.world, player, ModAttributes.JUJIKA);
			super.use(player);
		};			
	}

	public static class Enjomo extends Ability
	{
		public Enjomo() 
		{
			super(ModAttributes.ENJOMO); 
		}
		
		@Override
		public void use(final PlayerEntity player)
		{
			if(!isOnCooldown)
			{
				WyHelper.createEmptySphere(player.world, (int)player.posX, (int)player.posY, (int)player.posZ, 13, Blocks.FIRE, "air", "foliage");
					
				for(LivingEntity l : WyHelper.getEntitiesNear(player, 12))
				{
					l.setFire(20);
				}
											
				super.use(player);
			}
		}	
	}
*/
}

