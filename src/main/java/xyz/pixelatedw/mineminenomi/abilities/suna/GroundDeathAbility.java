package xyz.pixelatedw.mineminenomi.abilities.suna;

import java.util.List;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.suna.GroundDeathParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;

public class GroundDeathAbility extends Ability
{
	public static final Ability INSTANCE = new GroundDeathAbility();

	private static final ParticleEffect PARTICLES = new GroundDeathParticleEffect();
	
	public GroundDeathAbility()
	{
		super("Ground Death", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(15);
		this.setDescription("Dries out the surroundings and suffucates all nearby opponents in sand.");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		List<LivingEntity> targets = WyHelper.<LivingEntity>getEntitiesNear(player.getPosition(), player.world, 25);
		targets.remove(player);
		
		for (LivingEntity target : targets)
		{
			AbilityHelper.createFilledCube(target, new int[] { 2, 2, 2 }, Blocks.SAND, "air");
			PARTICLES.spawn(player.world, target.posX, target.posY, target.posZ, 0, 0, 0);
		}
		
		return true;
	}
}
