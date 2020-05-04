package xyz.pixelatedw.mineminenomi.abilities.goe;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.goe.TodorokiProjectile;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.RepeaterAbility;

public class TodorokiAbility extends RepeaterAbility
{
	public static final Ability INSTANCE = new TodorokiAbility();

	public TodorokiAbility()
	{
		super("Todoroki", AbilityCategory.DEVIL_FRUIT);
		this.setDescription("The user shouts and creates a powerful sound blast.");
		this.setMaxCooldown(8);
		this.setMaxRepearCount(5, 4);

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		TodorokiProjectile proj = new TodorokiProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 3.5f, 1);
		
		return true;
	}
}