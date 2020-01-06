package xyz.pixelatedw.mineminenomi.events;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.Env;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.extra.AbilityExplosion;
import xyz.pixelatedw.mineminenomi.init.ModEnchantments;

@Mod.EventBusSubscriber(modid = Env.PROJECT_ID)
public class EventsEnchantments
{

	@SubscribeEvent
	public static void onLivingAttack(LivingAttackEvent event)
	{	
		if (event.getSource().getTrueSource() instanceof PlayerEntity && !event.getSource().isExplosion())
		{
			PlayerEntity player = (PlayerEntity) event.getSource().getTrueSource();
			ItemStack heldItem = player.getHeldItemMainhand();
			
			if(heldItem != null && heldItem.isEnchanted() && !player.world.isRemote)
			{
				int impactDialLevel = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.DIAL_IMPACT, heldItem);
				if(impactDialLevel > 0)
				{
					AbilityExplosion explosion = WyHelper.newExplosion(player, event.getEntityLiving().posX, event.getEntityLiving().posY, event.getEntityLiving().posZ, impactDialLevel);
					explosion.setDamageOwner(false);
					explosion.setDestroyBlocks(false);
					explosion.doExplosion();
				}
				
				int flashDialLevel = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.DIAL_FLASH, heldItem);
				if(flashDialLevel > 0)
				{
					event.getEntityLiving().addPotionEffect(new EffectInstance(Effects.BLINDNESS, 200 * flashDialLevel, flashDialLevel));
				}
			}
		}
	}
}
