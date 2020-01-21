package xyz.pixelatedw.mineminenomi.abilities.bane;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability.Category;
import xyz.pixelatedw.mineminenomi.entities.projectiles.bane.SpringDeathKnockProjectile;

public class SpringDeathKnockAbility extends Ability
{
	public static final Ability INSTANCE = new SpringDeathKnockAbility();
	
	public SpringDeathKnockAbility()
	{
		super("Spring Death Knock", Category.DEVIL_FRUIT);
		this.setMaxCooldown(6);
		this.setDescription("By turning the user's arm into a spring and compressing it, they can launch a powerful punch.");

		this.onUseEvent = this::onUseEvent;
	}
	
	private void onUseEvent(PlayerEntity player, Ability ability)
	{
		SpringDeathKnockProjectile proj = new SpringDeathKnockProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);		
	}
}
