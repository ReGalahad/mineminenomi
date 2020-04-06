package xyz.pixelatedw.mineminenomi.abilities.sabi;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.events.passives.SabiPassiveEvents;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.sabi.RustTouchParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.PunchAbility;

public class RustTouchAbility extends PunchAbility
{
	public static final Ability INSTANCE = new RustTouchAbility();

	private static final ParticleEffect PARTICLES = new RustTouchParticleEffect();
	
	public RustTouchAbility()
	{
		super("Rust Touch", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(15);
		this.setDescription("Rusts the enemy and the items equipped on them");
		
		this.onHitEntityEvent = this::onHitEntity;
	}
	
	private float onHitEntity(PlayerEntity player, LivingEntity target)
	{
		target.addPotionEffect(new EffectInstance(Effects.WITHER, 200, 1));
		target.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 200, 1));
		target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 200, 1));

		ItemStack gear = target.getItemStackFromSlot(EquipmentSlotType.HEAD);
		if(gear.getItem() == Items.IRON_HELMET)
			gear.damageItem(((gear.getMaxDamage() / 3) + 1) + 1, target, (entity) -> {});
		
		gear = target.getItemStackFromSlot(EquipmentSlotType.CHEST);
		if(gear.getItem() == Items.IRON_CHESTPLATE)
			gear.damageItem((gear.getMaxDamage() / 3) + 1, target, (entity) -> {});
		
		gear = target.getItemStackFromSlot(EquipmentSlotType.LEGS);
		if(gear.getItem() == Items.IRON_LEGGINGS)
			gear.damageItem((gear.getMaxDamage() / 3) + 1, target, (entity) -> {});
		
		gear = target.getItemStackFromSlot(EquipmentSlotType.FEET);
		if(gear.getItem() == Items.IRON_BOOTS)
			gear.damageItem((gear.getMaxDamage() / 3) + 1, target, (entity) -> {});
				
		gear = target.getItemStackFromSlot(EquipmentSlotType.MAINHAND);	
		ItemStack offhandGear = target.getItemStackFromSlot(EquipmentSlotType.OFFHAND);	
		for(Item item : SabiPassiveEvents.IRON_ITEMS)
		{
			if(gear.getItem() == item)
				gear.damageItem((gear.getMaxDamage() / 3) + 1, target, (entity) -> {});
			else if(offhandGear.getItem() == item)
				offhandGear.damageItem((offhandGear.getMaxDamage() / 3) + 1, target, (entity) -> {});
		}
		
		PARTICLES.spawn(player.world, target.posX, target.posY, target.posZ, 0, 0, 0);
		
		return 8;
	}
}
