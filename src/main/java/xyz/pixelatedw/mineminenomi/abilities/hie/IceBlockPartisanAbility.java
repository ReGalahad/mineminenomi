package xyz.pixelatedw.mineminenomi.abilities.hie;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.abilities.RepeaterAbility;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability.Category;
import xyz.pixelatedw.mineminenomi.entities.projectiles.hie.IceBlockPartisanProjectile;

public class IceBlockPartisanAbility extends RepeaterAbility
{
	public static final Ability INSTANCE = new IceBlockPartisanAbility();
	
	public IceBlockPartisanAbility()
	{
		super("Ice Block: Partisan", Category.DEVIL_FRUIT);
		this.setMaxCooldown(7);
		this.setMaxRepearCount(5, 1);
		this.setDescription("Creates several spears of ice that the user hurls at the enemy.");

		this.onUseEvent = this::onUseEvent;
	}
	
	private void onUseEvent(PlayerEntity player, Ability ability)
	{
		IceBlockPartisanProjectile proj = new IceBlockPartisanProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);		
	}
}
