package xyz.pixelatedw.mineminenomi.abilities.sniper;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.ISniperAbility;
import xyz.pixelatedw.mineminenomi.entities.projectiles.sniper.RenpatsuNamariBoshiProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;

public class RenpatsuNamariBoshiAbility extends ContinuousAbility implements ISniperAbility
{
	public static final RenpatsuNamariBoshiAbility INSTANCE = new RenpatsuNamariBoshiAbility();

	public RenpatsuNamariBoshiAbility()
	{
		super("Renpatsu Namari Boshi", AbilityCategory.RACIAL);
		this.setMaxCooldown(6);
		this.setDescription("Lets the user fire a barrage of exploding shots.");
	}

	@Override
	public void shoot(PlayerEntity player)
	{
		int current = player.ticksExisted;
		int limit = (int) (current - this.maxCooldown);
		while(current > limit)
		{
			if(current % 20 == 0)
			{
				RenpatsuNamariBoshiProjectile proj = new RenpatsuNamariBoshiProjectile(player.world, player);
				player.world.addEntity(proj);
				proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 5);
			}
			current--;
		}
	}
}
