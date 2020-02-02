package xyz.pixelatedw.mineminenomi.abilities.goro;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability.Category;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.goro.ElThorParticleEffect;

public class SparkStepAbility extends Ability
{
	public static final Ability INSTANCE = new SparkStepAbility();
	
	private static final ParticleEffect PARTICLES = new ElThorParticleEffect();
	
	public SparkStepAbility()
	{
		super("Spark Step", Category.DEVIL_FRUIT);
		this.setMaxCooldown(3);
		this.setDescription("Instantly teleport the user to their desired location.");

		this.onUseEvent = this::onUseEvent;
	}
	
	private boolean onUseEvent(PlayerEntity player)
	{
		if (WyHelper.rayTraceBlocks(player) != null)
		{
			RayTraceResult mop = WyHelper.rayTraceBlocks(player);
			
			double x = mop.getHitVec().x;
			double y = mop.getHitVec().y;
			double z = mop.getHitVec().z;
			
			if (player.getRidingEntity() != null)
				player.dismountEntity(player.getRidingEntity());
			
			EnderTeleportEvent event = new EnderTeleportEvent(player, x, y, z, 5.0F);
			PARTICLES.spawn(player.world, player.posX, player.posY, player.posZ, 0, 0, 0);
			player.setPositionAndUpdate(event.getTargetX(), event.getTargetY() + 1, event.getTargetZ());
			PARTICLES.spawn(player.world, player.posX, player.posY, player.posZ, 0, 0, 0);
			player.fallDistance = 0.0F;
		}
		
		return true;
	}
}