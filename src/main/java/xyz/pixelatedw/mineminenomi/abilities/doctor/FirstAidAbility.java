package xyz.pixelatedw.mineminenomi.abilities.doctor;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.init.ModArmors;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.doctor.FirstAidParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.PunchAbility;

public class FirstAidAbility extends PunchAbility
{
	public static final FirstAidAbility INSTANCE = new FirstAidAbility();

	private static final ParticleEffect PARTICLES = new FirstAidParticleEffect();
	
	public FirstAidAbility()
	{
		super("First Aid", AbilityCategory.RACIAL);
		this.setMaxCooldown(10);
		this.setDescription("Allows the user to heal its target.");
		
		this.onHitEntityEvent = this::onHitEntity;
	}
	
	private float onHitEntity(PlayerEntity player, LivingEntity target)
	{
		ItemStack medicBag = player.inventory.armorInventory.get(2);
		boolean hasMedicBag = medicBag != null && medicBag.getItem() == ModArmors.MEDIC_BAG;

		if(!hasMedicBag)
		{
			WyHelper.sendMsgToPlayer(player, new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_MEDIC_BAG, this.getName()).getFormattedText());
			return -1;
		}
		
		target.heal(10);

		if(target.getHealth() < target.getMaxHealth())
			PARTICLES.spawn(player.world, target.posX, target.posY, target.posZ, 0, 0, 0);

		int damage = medicBag.getDamage() + 10 <= medicBag.getMaxDamage() ? 10 : medicBag.getMaxDamage() - medicBag.getDamage();
		medicBag.damageItem(damage, player, (user) -> {});
		if(medicBag.getDamage() >= medicBag.getMaxDamage())
		{
			//WyNetworkHelper.sendTo(new PacketBrokenItemParticles(medicBag), (EntityPlayerMP) player);
			medicBag.shrink(1);
		}
		
		return 0;
	}
}
