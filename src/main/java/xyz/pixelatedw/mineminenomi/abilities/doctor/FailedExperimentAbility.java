package xyz.pixelatedw.mineminenomi.abilities.doctor;

import com.google.common.collect.Lists;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PotionEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.init.ModArmors;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.ChargeableAbility;

public class FailedExperimentAbility extends ChargeableAbility
{
	public static final Ability INSTANCE = new FailedExperimentAbility();

	public FailedExperimentAbility()
	{
		super("Failed Experiment", AbilityCategory.RACIAL);
		this.setMaxCooldown(7);
		this.setMaxChargeTime(2);
		this.setDescription("Throws a random splash potion with a debuff effect at the enemy");
		
		this.onEndChargingEvent = this::onEndChargingEvent;
	}
	
	private boolean onEndChargingEvent(PlayerEntity player)
	{		
		ItemStack medicBag = player.inventory.armorInventory.get(2);
		boolean hasMedicBag = medicBag != null && medicBag.getItem() == ModArmors.MEDIC_BAG;

		if(!hasMedicBag)
		{
			WyHelper.sendMsgToPlayer(player, new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_MEDIC_BAG, this.getName()).getFormattedText());
			return false;
		}
		
		PotionEntity potion = new PotionEntity(player.world, player);
		
		int potionType = (int) WyHelper.randomWithRange(0, 3);
		ItemStack stack = new ItemStack(Items.SPLASH_POTION);
		
		switch(potionType)
		{
			case 0:
				stack = PotionUtils.appendEffects(stack, Lists.newArrayList(new EffectInstance(Effects.NAUSEA, 200, 1)));
				break;
			case 1:
				stack = PotionUtils.appendEffects(stack, Lists.newArrayList(new EffectInstance(Effects.MINING_FATIGUE, 200, 1)));
				break;
			case 2:
				stack = PotionUtils.appendEffects(stack, Lists.newArrayList(new EffectInstance(Effects.POISON, 200, 1)));
				break;
			case 3:
				stack = PotionUtils.appendEffects(stack, Lists.newArrayList(new EffectInstance(Effects.HUNGER, 200, 1)));
				break;
		}
		potion.setItem(stack);
		
		potion.shoot(player, player.rotationPitch, player.rotationYaw, -20.0F, 0.8F, 1.0F);
		player.world.addEntity(potion);

		int damage = medicBag.getDamage() + 10 <= medicBag.getMaxDamage() ? 10 : medicBag.getMaxDamage() - medicBag.getDamage();
		medicBag.damageItem(damage, player, (user) -> {
			user.sendBreakAnimation(EquipmentSlotType.MAINHAND);
		});
		if(medicBag.getDamage() >= medicBag.getMaxDamage())
		{
			//WyNetworkHelper.sendTo(new PacketBrokenItemParticles(medicBag), (EntityPlayerMP) player);
			medicBag.shrink(1);
		}
		
		return true;
	}
}
