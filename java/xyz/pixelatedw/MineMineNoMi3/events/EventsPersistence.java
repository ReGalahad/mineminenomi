package xyz.pixelatedw.MineMineNoMi3.events;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import com.google.common.collect.Iterables;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.event.ClickEvent;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import scala.Int;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.Values;
import xyz.pixelatedw.MineMineNoMi3.abilities.FishKarateAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.HakiAbilities;
import xyz.pixelatedw.MineMineNoMi3.abilities.HakiAbilities.BusoshokuHaki;
import xyz.pixelatedw.MineMineNoMi3.abilities.HakiAbilities.KenbunshokuHaki;
import xyz.pixelatedw.MineMineNoMi3.abilities.RokushikiAbilities;
import xyz.pixelatedw.MineMineNoMi3.api.EnumParticleTypes;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.extra.AbilityManager;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.api.telemetry.WyTelemetry;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.EntityNewMob;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.marines.MarineData;
import xyz.pixelatedw.MineMineNoMi3.events.customevents.DorikiEvent;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.items.AkumaNoMi;
import xyz.pixelatedw.MineMineNoMi3.lists.ListEffects;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSync;

public class EventsPersistence
{

	/*
	 * onEntityUpdate 
	 * > Job boosts 
	 * > Fall damage nullification for Gomu & Bane 
	 * > Poison placement for Doku 
	 * > Doku nullification of poison damage 
	 * > Extra HP 
	 * > Fishman swimming boost 
	 * > Nullification of swimming for DF users 
	 * > Kilo gliding & falling
	 * > Buso Haki Timer
	 * 
	 * onEntityDeath 
	 * > Removing abilities when the user dies 
	 * > Doriki, Bounty & Belly rewards for killing players/mobs
	 * 
	 * onEntityAttackEvent 
	 * > Lava/Fire damage nullification for Mera & Magu 
	 * > Logia protection
	 * 
	 * onEntityJoinWorld 
	 * > Syncing between dimensions 
	 * > Gives a creation book for users without job/race/faction
	 * 
	 * onDorikiGained 
	 * > Rewards the user with rokushiki/fishman karate based on doriki
	 */	
	
	/** XXX onEntityUpdate */
	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent event)
	{
		/*if(event.entityLiving instanceof Doppelman && !event.entityLiving.worldObj.isRemote)
		{
			Doppelman dopp = (Doppelman) event.entityLiving;
			EntityPlayer doppOwner = dopp.getOwner();

			if(doppOwner != null)
			{
				if(dopp != null)
				{
					ItemStack heldItem = doppOwner.getHeldItem();
					if(heldItem != null && heldItem.getItem() == ListAbilities.DOPPELMAN && dopp.getDistanceSqToEntity(doppOwner) > 6000)
					{
						dopp.setDead();
						heldItem.getTagCompound().setBoolean("doppelman", false);
					}
				}
			}
			else
				dopp.setDead();
		}*/
		
		if (event.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			ExtendedEntityStats props = ExtendedEntityStats.get(player);
			ItemStack heldItem = player.getHeldItem();				
			
			if(!player.worldObj.isRemote)
			{			
				for(int i = 0; i < props.getAbilitiesInHotbar(); i++)
				{
					if(props.getAbilityFromSlot(i) != null && !props.getAbilityFromSlot(i).equals("n/a"))
					{
						props.getAbilityFromSlot(i).update(player);
					}
				}
			}
			
			if (heldItem != null)
			{				
				if(heldItem.getItem() == ListMisc.Umbrella && player.worldObj.getBlock((int)player.posX, (int)player.posY - 4, (int)player.posZ) == Blocks.air && !player.capabilities.isCreativeMode)
					player.motionY = -0.05;
				
				/*if(props.getUsedFruit().equals("kilokilo") && heldItem.getItem() == ListAbilities.KILOPRESS)
				{
					if (props.getKilo())
					{
						player.motionY -= 3;
						player.fallDistance = 0;
					}
					if ( !props.getKilo() && !heldItem.getTagCompound().getBoolean("use") )
						player.addPotionEffect(new PotionEffect(Potion.jump.id, 10, 20, true));
				}*/
			}
			
			if (props.getUsedFruit().equals("gomugomu") || props.getUsedFruit().equals("banebane") || props.isLogia() || (player.getHeldItem() != null 
					|| (player.getHeldItem() != null)) )
				player.fallDistance = 0;

			if (props.getUsedFruit().equals("dokudoku"))
			{
				if (player.worldObj.getBlock((int)player.posX, (int)player.posY - 1, (int)player.posZ) != Blocks.air
						&& player.worldObj.getBlock((int)player.posX, (int)player.posY - 1, (int)player.posZ) != ListMisc.Poison
						&& player.worldObj.getBlock((int)player.posX, (int)player.posY - 1, (int)player.posZ) != ListMisc.Ope
						&& player.worldObj.getBlock((int)player.posX, (int)player.posY - 1, (int)player.posZ) != ListMisc.OpeMid)
					player.worldObj.setBlock((int)player.posX, (int)player.posY, (int)player.posZ, ListMisc.Poison);

				if (player.isPotionActive(Potion.poison.id))
					player.removePotionEffect(Potion.poison.id);
			}
			
			if ((player.isInsideOfMaterial(Material.water) || (player.isWet() && player.worldObj.getBlock((int) player.posX, (int) player.posY - 3, (int) player.posZ) == Blocks.water))
					&& !player.capabilities.isCreativeMode)
			{
				if (!props.getUsedFruit().equals("N/A"))
					player.motionY -= 5;

				if (props.getRace().equals(ID.RACE_FISHMAN) && props.getUsedFruit().equals("N/A"))
				{
					player.setAir(300);
					player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 300, 1));

					if ((player.motionX >= 5.0D) || (player.motionZ >= 5.0D))
					{
						player.motionX /= 1.2D;	
						player.motionZ /= 1.2D;
					}
					else
					{
						player.motionX *= 1.2D;
						player.motionZ *= 1.2D;
					}
				}
			}
			
			if(player.isInsideOfMaterial(Material.lava) && !player.capabilities.isCreativeMode)
			{
				if (props.getUsedFruit().equals("magumagu"))
				{
					if ((player.motionX >= 5.0D) || (player.motionZ >= 5.0D))
					{
						player.motionX /= 1.9D;
						player.motionZ /= 1.9D;
					}
					else
					{
						player.motionX *= 1.9D;
						player.motionZ *= 1.9D;
					}
				}
			}
			
			//System.out.println("" + props.hasHakiActive() + "; " + props.getHakiTimer());
			
			if(props.hasHakiActive())
				props.addHakiTimer();
			else
			{
				if(props.getHakiTimer() > 0)
					props.decHakiTimer();
			}
			
			if(props.getHakiTimer() > 1000)
			{
				player.addPotionEffect(new PotionEffect(Potion.confusion.id, 100, 0));
				player.addPotionEffect(new PotionEffect(Potion.weakness.id, 100, 0));
				if(props.getHakiTimer() > 1500 + (props.getDoriki() / 15))
				{
					player.attackEntityFrom(DamageSource.generic, Integer.MAX_VALUE);
				}
			}
			
		}
	}	

	/** XXX onLivingDeath */
	@SubscribeEvent
	public void onEntityDeath(LivingDeathEvent event)
	{
		if (event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entity;
			ExtendedEntityStats props = ExtendedEntityStats.get(player);

			props.setYamiPower(false);

			for(int i = 0; i < 8; i++)
			{
				if(props.getAbilityFromSlot(i) != null)
					props.getAbilityFromSlot(i).reset();
			}
			
			WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
		}

		if (event.source.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.source.getEntity();
			ExtendedEntityStats props = ExtendedEntityStats.get(player);
			EntityLivingBase target = event.entityLiving;

			IAttributeInstance attrAtk = target.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.attackDamage);
			IAttributeInstance attrHP = target.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.maxHealth);

			int rng = player.worldObj.rand.nextInt(3) + 1;
			int plusDoriki = 0, plusBounty = 0, plusBelly = 0;		
			
			if (target instanceof EntityPlayer)
			{
				ExtendedEntityStats targetprops = ExtendedEntityStats.get(player);

				plusDoriki = (targetprops.getDoriki() / 4) + rng;
				plusBounty = (targetprops.getBounty() / 2) + rng;
				plusBelly = targetprops.getBelly();
			}
			else
			{
				if (props.getFaction().equals(ID.FACTION_MARINE) && target instanceof MarineData)
					return;
				
				if(target instanceof EntityNewMob)
				{				
					plusDoriki = ((EntityNewMob) target).getDorikiPower() + rng;
					plusBounty = (((EntityNewMob) target).getDorikiPower() * 2) + rng;	
					plusBelly = ((EntityNewMob) target).getBellyInPockets() + rng;
					
			    	if(!ID.DEV_EARLYACCESS && !player.worldObj.isRemote && !player.capabilities.isCreativeMode)
			    		WyTelemetry.addDefeatedEntityStat("defeated_" + WyHelper.getFancyName(target.getCommandSenderName()), 1);
				}
				else
				{
					if (attrAtk != null && attrHP != null)
					{	
						double i = attrAtk.getAttributeValue();
						double j = attrHP.getAttributeValue();		

						plusDoriki = (int) Math.round(((i + j) / 10) / Math.PI) + rng;
						plusBounty = (int) Math.round((i + j) / 10) + rng;
						plusBelly = 1;
					
					}
					else
					{
						plusDoriki = 0;
						plusBounty = 0;
						plusBelly = 1;
					}
				}
					
				if (plusDoriki > 0)
				{
					if (props.getDoriki() + plusDoriki < Values.MAX_DORIKI && plusDoriki > props.getDoriki() / 100)
					{
						props.alterDoriki(plusDoriki);
						DorikiEvent e = new DorikiEvent(player);
						if (MinecraftForge.EVENT_BUS.post(e))
							return;
					}
				}
					
				if (props.getFaction().equals(ID.FACTION_PIRATE) || props.getFaction().equals(ID.FACTION_REVOLUTIONARY))
					if (plusBounty > 0)
						if (props.getBounty() + plusBounty < Values.MAX_GENERAL)
							props.alterBounty(plusBounty);
				
				if(props.getBelly() + plusBelly < Values.MAX_GENERAL)
					props.alterBelly(plusBelly);
						
			}
			
	    	if(!ID.DEV_EARLYACCESS && !player.worldObj.isRemote && !player.capabilities.isCreativeMode)
	    	{
	    		WyTelemetry.addGeneralStat("dorikiEarnedFromKills", plusDoriki);
	    		WyTelemetry.addGeneralStat("bellyEarnedFromKills", plusBelly);
	    		WyTelemetry.addGeneralStat("bountyEarnedFromKills", plusBounty);
	    	}
			
			WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
		}
	}

	/** XXX onEntityAttackEvent */
	@SubscribeEvent
	public void onEntityAttackEvent(LivingAttackEvent event)
	{
		EntityLivingBase entity = event.entityLiving;
		Entity sourceOfDamage = event.source.getSourceOfDamage();
		ExtendedEntityStats props = ExtendedEntityStats.get(entity);

		for (int i = -2; i <= 2; i++)
			for (int j = -2; j <= 2; j++)
				for (int k = -2; k <= 2; k++)
					if (entity.worldObj.getBlock((int)entity.posX + i, (int)entity.posY + j, (int)entity.posZ + k) == ListMisc.KairosekiOre || entity.worldObj.getBlock((int)entity.posX + i, (int)entity.posY + j, (int)entity.posZ + k) == ListMisc.KairosekiBlock)
						return;
		
		if (sourceOfDamage instanceof EntityPlayer)
		{			
			ExtendedEntityStats propz = ExtendedEntityStats.get((EntityLivingBase) sourceOfDamage);
			ItemStack heldItem = ((EntityLivingBase) sourceOfDamage).getHeldItem();

			if(heldItem != null)
			{
				boolean hasKairosekiWeapon = heldItem.isItemEnchanted() ? EnchantmentHelper.getEnchantmentLevel(ListEffects.kairoseki.effectId, heldItem) > 0 : false;
				boolean hasHaki = propz.hasBusoHakiActive();
	
				if (entity instanceof EntityPlayer)
				{
					if (props.isLogia())
						if (!hasHaki && !hasKairosekiWeapon)
							event.setCanceled(true);
				}
				else
				{
					if (entity instanceof EntityNewMob)
					{
						if (((EntityNewMob) entity).isLogia())
							if (!hasHaki && !hasKairosekiWeapon)
								event.setCanceled(true);
					}
					else
					{
						// Possible mods/plugins support ?
					}					
				}
			}
		}

		if (sourceOfDamage instanceof EntityLivingBase && !(sourceOfDamage instanceof EntityPlayer))
		{
			boolean hasKairosekiWeapon;
			boolean hasHaki;

			if (sourceOfDamage instanceof EntityNewMob)
				hasHaki = ((EntityNewMob) sourceOfDamage).hasBusoHaki();
			else
				hasHaki = false;

			if (props.isLogia())
				if (!hasHaki)
					event.setCanceled(true);

		}
 
		if (sourceOfDamage instanceof EntityArrow)
		{
			if (entity instanceof EntityPlayer && props.isLogia())
				event.setCanceled(true);

		}

		if(event.source.isExplosion())
			event.setCanceled(true);
		
		if (event.entityLiving instanceof EntityPlayer)
		{
			if (props.getUsedFruit().equals("meramera") && (event.source.equals(DamageSource.inFire) || event.source.equals(DamageSource.onFire)))
			{
				entity.extinguish();
				event.setCanceled(true);
			}
			if (props.getUsedFruit().equals("magumagu")
					&& (event.source.equals(DamageSource.inFire) || event.source.equals(DamageSource.onFire) || event.source.equals(DamageSource.lava)))
			{
				entity.extinguish();
				event.setCanceled(true);
			}
		}
	}
	
	/** XXX onEntityJoinWorld */
	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event)
	{
		if (event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entity;
			ExtendedEntityStats props = ExtendedEntityStats.get(player);			
			
			if (!player.worldObj.isRemote)
			{				
				if(ID.DEV_EARLYACCESS)
				{					
					try 
					{
						URL url = new URL("https://dl.dropboxusercontent.com/s/a1v3m3eaqz5o185/earlyaccess.txt?dl=0");
						Scanner scanner = new Scanner(url.openStream());
						boolean flag = false;
						
						while(scanner.hasNextLine())
						{
							String uuid = scanner.nextLine();
							
							if(player.getUniqueID().toString().equals(uuid) || player.getDisplayName().equals(uuid))
							{
								flag = true;
								break;
							}													
						}
						
						if(!flag)
							((EntityPlayerMP)player).playerNetServerHandler.kickPlayerFromServer(EnumChatFormatting.BOLD + "" + EnumChatFormatting.RED + "WARNING! \n\n " + EnumChatFormatting.RESET + "You don't have access to this version yet!");														
						
						scanner.close();
					} 
					catch (IOException e) 
					{
						((EntityPlayerMP)player).playerNetServerHandler.kickPlayerFromServer(EnumChatFormatting.BOLD + "" + EnumChatFormatting.RED + "WARNING! \n\n " + EnumChatFormatting.RESET + "You don't have access to this version yet!");						
						e.printStackTrace();
					}				
				}
				
				if (props.getRace().equals("N/A") && props.getFaction().equals("N/A") && props.getFightStyle().equals("N/A") && !player.inventory.hasItemStack(new ItemStack(ListMisc.CharacterCreator)))
					player.inventory.addItemStackToInventory(new ItemStack(ListMisc.CharacterCreator, 1));
				
				if(props.getUsedFruit() != null && !props.getUsedFruit().equals("N/A"))
				{
					String model = "";
					if(props.getUsedFruit().equals("ushiushibison"))
						model = "bison";
					
					ItemStack yamiFruit = new ItemStack(GameRegistry.findItem(ID.PROJECT_ID, "yamiyaminomi"));
					ItemStack df = new ItemStack(GameRegistry.findItem(ID.PROJECT_ID, props.getUsedFruit().replace(model, "") + "nomi" + model));
					
					props.clearDevilFruitAbilities();
					
					if(props.hasYamiPower())
					{
						for(Ability a : ((AkumaNoMi)yamiFruit.getItem()).abilities)
						{
							props.addDevilFruitAbility(a);
						}
					}
					
					for(Ability a : ((AkumaNoMi)df.getItem()).abilities)
					{
						props.addDevilFruitAbility(a);
					}
					
					for(int i = 0; i < props.getAbilitiesInHotbar(); i++)
					{
						if(props.getAbilityFromSlot(i) != null)
						{
							if(props.getAbilityFromSlot(i) == null)
								props.setAbilityInSlot(i, null);
						}
					}
				}
					
				WyNetworkHelper.sendTo(new PacketSync(props), (EntityPlayerMP) player);
				
				try 
				{
					URL url = new URL("https://dl.dropboxusercontent.com/s/3io0vaqiqaoabnh/version.txt?dl=0");
					Scanner scanner = new Scanner(url.openStream());
					
					while(scanner.hasNextLine())
					{
						String[] parts = scanner.nextLine().split("\\-");

						if(ID.PROJECT_MCVERSION.equals(parts[0]))
						{
							if(!ID.PROJECT_VERSION.equals(parts[1]))
							{
								ChatStyle updateStyle = new ChatStyle().setColor(EnumChatFormatting.GOLD).setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "http://pixelatedw.xyz/builds.php"));
								
								player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + "" + EnumChatFormatting.BOLD + "[UPDATE]" + EnumChatFormatting.RED + " Mine Mine no Mi " + parts[1] + " is now available !").setChatStyle(updateStyle) );
								player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + "Download it from the official website : [http://pixelatedw.xyz/builds.php]").setChatStyle(updateStyle) );
							}
						}					
					}
					
					scanner.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				
			}		
		}
	}
	

	/** XXX onDorikiGained */
	@SubscribeEvent
	public void onDorikiGained(DorikiEvent event)
	{
		if (event.props.getRace().equals(ID.RACE_HUMAN))
		{
			ability(event.player, 500, RokushikiAbilities.SORU);
			ability(event.player, 1500, RokushikiAbilities.TEKKAI);
			ability(event.player, 3000, RokushikiAbilities.SHIGAN);
			ability(event.player, 4500, RokushikiAbilities.GEPPO);
			ability(event.player, 5000, HakiAbilities.KENBUNSHOKUHAKI);
			ability(event.player, 6000, RokushikiAbilities.KAMIE);
			ability(event.player, 8500, RokushikiAbilities.RANKYAKU);
			ability(event.player, 9000, HakiAbilities.BUSOSHOKUHAKI);
			//HAOSHOKU - 9000 + other			
		}
		else if (event.props.getRace().equals(ID.RACE_FISHMAN))
		{
			ability(event.player, 800, FishKarateAbilities.UCHIMIZU);
			ability(event.player, 2000, FishKarateAbilities.SOSHARK);
			//ability(event.player, 2500, FishKarateAbilities.KACHIAGEHAISOKU);
			ability(event.player, 3000, FishKarateAbilities.SAMEHADASHOTEI);
			ability(event.player, 4000, HakiAbilities.KENBUNSHOKUHAKI);
			ability(event.player, 7500, FishKarateAbilities.KARAKUSAGAWARASEIKEN);
			ability(event.player, 9000, HakiAbilities.BUSOSHOKUHAKI);
		}
		else if(event.props.getRace().equals(ID.RACE_CYBORG))
		{
			ability(event.player, 5500, HakiAbilities.KENBUNSHOKUHAKI);
			ability(event.player, 8500, HakiAbilities.BUSOSHOKUHAKI);
		}
		
		if(event.player != null)		
		{
			ExtendedEntityStats props = ExtendedEntityStats.get(event.player);
			IAttributeInstance maxHp = event.player.getEntityAttribute(SharedMonsterAttributes.maxHealth);
						
			if(props.getDoriki() < 70)
			{
				event.player.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20);
			}
			else
			{
				if(props.getDoriki() % 70 == 0)
				{
					event.player.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(maxHp.getAttributeValue() + 2);
				}
			}
		}
	}	

	private void ability(EntityPlayer player, int doriki, Ability ability)
	{
		ExtendedEntityStats props = ExtendedEntityStats.get(player);	
			
		if(ability instanceof KenbunshokuHaki || ability instanceof BusoshokuHaki)
		{
			if (props.getDoriki() >= doriki && !props.hasHakiAbility(ability))
				props.addHakiAbility(ability);
			if (props.getDoriki() < doriki && props.hasHakiAbility(ability))
				props.removeHakiAbility(ability);
		}
		else
		{
			if (props.getDoriki() >= doriki && !props.hasRacialAbility(ability))
				props.addRacialAbility(ability);
			if (props.getDoriki() < doriki && props.hasRacialAbility(ability))
				props.removeRacialAbility(ability);		
		}
	}
	
}
