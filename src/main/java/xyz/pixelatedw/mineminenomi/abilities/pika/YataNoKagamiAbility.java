package xyz.pixelatedw.mineminenomi.abilities.pika;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability.Category;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.pika.ParticleEffectYataNoKagami;

public class YataNoKagamiAbility extends Ability
{
	public static final Ability INSTANCE = new YataNoKagamiAbility();
	
	private static final ParticleEffect PARTICLES = new ParticleEffectYataNoKagami();
	
	public YataNoKagamiAbility()
	{
		super("Yata no Kagami", Category.DEVIL_FRUIT);
		this.setMaxCooldown(4);
		this.setDescription("Uses light to instantly teleport the user to their desired location.");

		this.onUseEvent = this::onUseEvent;
	}
	
	private void onUseEvent(PlayerEntity player, Ability ability)
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
	}
}