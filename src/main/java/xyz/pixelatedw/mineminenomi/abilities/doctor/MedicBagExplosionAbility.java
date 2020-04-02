package xyz.pixelatedw.mineminenomi.abilities.doctor;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.init.ModArmors;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.doctor.MedicBagExplosionParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;

public class MedicBagExplosionAbility extends Ability
{
	public static final Ability INSTANCE = new MedicBagExplosionAbility();

	private static final ParticleEffect PARTICLES = new MedicBagExplosionParticleEffect();
	
	public MedicBagExplosionAbility()
	{
		super("Medic Bag Explosion", AbilityCategory.RACIAL);
		this.setMaxCooldown(30);
		this.setDescription("By sacrificing the medic bag's durability the user can fully heal himself while applying debuffs to nearby enemies");
		
		this.onUseEvent = this::onUseEvent;
	}
	
	private boolean onUseEvent(PlayerEntity player)
	{
		ItemStack medicBag = player.inventory.armorInventory.get(2);
		boolean hasMedicBag = medicBag != null && medicBag.getItem() == ModArmors.MEDIC_BAG;

		if(!hasMedicBag)
		{
			WyHelper.sendMsgToPlayer(player, new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_MEDIC_BAG, this.getName()).getFormattedText());
			return false;
		}
		
		player.heal(player.getMaxHealth());

		for(LivingEntity entity : WyHelper.<LivingEntity>getEntitiesNear(player.getPosition(), player.world, 10))
		{
			int effect = (int) WyHelper.randomWithRange(0, 6);
			
			switch(effect)
			{
				case 0:
					entity.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 200, 1)); break;
				case 1:
					entity.addPotionEffect(new EffectInstance(Effects.NAUSEA, 200, 1)); break;
				case 2:
					entity.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 200, 1)); break;
				case 3:
					entity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 200, 1)); break;
				case 4:
					entity.addPotionEffect(new EffectInstance(Effects.POISON, 200, 1)); break;
				case 5:
					entity.addPotionEffect(new EffectInstance(Effects.WITHER, 200, 1)); break;
				case 6:
					entity.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 200, 1)); break;
			}
		}
		
		PARTICLES.spawn(player.world, player.posX, player.posY, player.posZ, 0, 0, 0);

		int damage = medicBag.getDamage() + 100 <= medicBag.getMaxDamage() ? 100 : medicBag.getMaxDamage() - medicBag.getDamage();
		medicBag.damageItem(damage, player, (user) -> {});
		if(medicBag.getDamage() >= medicBag.getMaxDamage())
		{
			//WyNetworkHelper.sendTo(new PacketBrokenItemParticles(medicBag), (EntityPlayerMP) player);
			medicBag.shrink(1);
		}
		
		return true;
	}
}
