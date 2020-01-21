package xyz.pixelatedw.mineminenomi.abilities.mera;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.abilities.RepeaterAbility;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability.Category;
import xyz.pixelatedw.mineminenomi.entities.projectiles.mera.HiganProjectile;

public class HiganAbility extends RepeaterAbility
{
	public static final Ability INSTANCE = new HiganAbility();
	
	public HiganAbility() 
	{
		super("Higan", Category.DEVIL_FRUIT);
		this.setDescription("Turns the user's fingertips into flames and shoots bullets made of fire from them.");
		this.setMaxCooldown(4);
		this.setMaxRepearCount(5, 2);
		
		this.onUseEvent = this::onUseEvent;
	}
	
	private void onUseEvent(PlayerEntity player, Ability ability)
	{
		HiganProjectile proj = new HiganProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);		
	}
}