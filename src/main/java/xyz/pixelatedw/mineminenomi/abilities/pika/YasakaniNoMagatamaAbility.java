package xyz.pixelatedw.mineminenomi.abilities.pika;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.pika.YasakaniNoMagatamaProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.RepeaterAbility;

public class YasakaniNoMagatamaAbility extends RepeaterAbility
{
	public static final Ability INSTANCE = new YasakaniNoMagatamaAbility();
	
	public YasakaniNoMagatamaAbility()
	{
		super("Yasakani no Magatama", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(2.5);
		this.setMaxRepearCount(7, 2);
		this.setDescription("Fires a torrent of deadly light particles, causing huge destruction.");

		this.onUseEvent = this::onUseEvent;
	}
	
	private boolean onUseEvent(PlayerEntity player)
	{
		YasakaniNoMagatamaProjectile proj = new YasakaniNoMagatamaProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 4f, 10);		
		
		return true;
	}
}