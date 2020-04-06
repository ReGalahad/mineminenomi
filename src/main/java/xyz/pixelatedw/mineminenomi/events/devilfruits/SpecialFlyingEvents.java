package xyz.pixelatedw.mineminenomi.events.devilfruits;

import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.wypi.APIConfig;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class SpecialFlyingEvents
{
	@SubscribeEvent
	public static void onEntityUpdate(LivingUpdateEvent event)
	{
		/*if (event.getEntityLiving() instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) event.getEntityLiving();
			IDevilFruit props = DevilFruitCapability.get(player);
			IAbilityData abilityProps = AbilityDataCapability.get(player);

			Ability abareHimatsuri = null;//abilityProps.hasAbilityInHotbar(ModAttributes.ABARE_HIMATSURI.getAttributeName());
			boolean hasAbareHimatsuri = props.getDevilFruit().equalsIgnoreCase("zushizushi") && abareHimatsuri != null && abareHimatsuri.isContinuous();
			
			boolean hasToriFruit = props.getDevilFruit().equalsIgnoreCase("toritoriphoenix") && !props.getZoanPoint().toLowerCase().equals("n/a");
			
			boolean hasFlyingFruit = Arrays.stream(AbilityHelper.flyingFruits).anyMatch(p ->
			{				
				return props.getDevilFruit().equalsIgnoreCase(p);
			});	
			
			if(!player.isCreative() && !player.isSpectator())
			{
				if(!player.world.isRemote)
				{
					if((CommonConfig.instance.isSpecialFlyingEnabled() && hasFlyingFruit) || hasToriFruit || hasAbareHimatsuri)		
					{
						WyNetwork.sendTo(new SSpecialFlyingPacket(true), (ServerPlayerEntity) player);
						player.fallDistance = 0;
					}
					else
						WyNetwork.sendTo(new SSpecialFlyingPacket(false), (ServerPlayerEntity) player);
				}
			
				if(player.abilities.isFlying && player.world.isRemote)
				{
					double extraOffset = 0;
					
					ResourceLocation particleToUse = null;
					if(props.getDevilFruit().equalsIgnoreCase("mokumoku") )
						particleToUse = ModResources.MOKU;
					else if(props.getDevilFruit().equalsIgnoreCase("gasugasu") )
						particleToUse = ModResources.GASU;
					else if(props.getDevilFruit().equalsIgnoreCase("sunasuna") )
						particleToUse = ModResources.SUNA2;
					else if(props.getDevilFruit().equalsIgnoreCase("toritoriphoenix") )
					{
						particleToUse = ModResources.BLUE_FLAME;
						extraOffset = 1;
					}
					
					if(particleToUse != null)
					{
						for (int i = 0; i < 5; i++)
						{							
							double offsetX = 0.5 - player.world.rand.nextDouble();
							double offsetY = player.world.rand.nextDouble();
							double offsetZ = 0.5 - player.world.rand.nextDouble();

							CustomParticleData data = new CustomParticleData();
							data.setTexture(particleToUse);
							data.setPosX(player.posX + offsetX);
							data.setPosY(player.posY - 0.2 + offsetY + extraOffset);
							data.setPosZ(player.posZ + offsetZ);
							
							data.setMaxAge(5);
							data.setGravity(-0.05F);
							data.setScale(1.3F);
							
							//ModMain.proxy.spawnParticles(player.world, data);
						}
					}
				}
			}
		}*/
	}
}
