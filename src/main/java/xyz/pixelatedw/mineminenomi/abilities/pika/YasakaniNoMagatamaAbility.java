package xyz.pixelatedw.mineminenomi.abilities.pika;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.abilities.RepeaterAbility;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability.Category;
import xyz.pixelatedw.mineminenomi.entities.projectiles.pika.YasakaniNoMagatamaProjectile;

public class YasakaniNoMagatamaAbility extends RepeaterAbility
{
	public static final Ability INSTANCE = new YasakaniNoMagatamaAbility();
	
	public YasakaniNoMagatamaAbility()
	{
		super("Yasakani no Magatama", Category.DEVIL_FRUIT);
		this.setMaxCooldown(2.5);
		this.setMaxRepearCount(7, 2);
		this.setDescription("Fires a torrent of deadly light particles, causing huge destruction.");

		this.onUseEvent = this::onUseEvent;
	}
	
	private boolean onUseEvent(PlayerEntity player)
	{
		YasakaniNoMagatamaProjectile proj = new YasakaniNoMagatamaProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);		
		
		return true;
	}
}