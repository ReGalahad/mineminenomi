package xyz.pixelatedw.mineminenomi.abilities.magu;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.magu.DaiFunkaProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.RepeaterAbility;

public class RyuseiKazanAbility extends RepeaterAbility
{
	public static final Ability INSTANCE = new RyuseiKazanAbility();

	public RyuseiKazanAbility()
	{
		super("Ryusei Kazan", AbilityCategory.DEVIL_FRUIT);
		this.setDescription("Functions like 'Dai Funka', but multiple fists are launched at the opponent.");
		this.setMaxCooldown(12);
		this.setMaxRepearCount(5, 3);

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		DaiFunkaProjectile proj = new DaiFunkaProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 5);

		return true;
	}
}