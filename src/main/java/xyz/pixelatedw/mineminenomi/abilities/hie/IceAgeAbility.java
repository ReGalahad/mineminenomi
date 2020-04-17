package xyz.pixelatedw.mineminenomi.abilities.hie;

import java.util.List;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.hie.IceAgeParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;

public class IceAgeAbility extends Ability
{
	public static final Ability INSTANCE = new IceAgeAbility();

	public static final ParticleEffect PARTICLES = new IceAgeParticleEffect();

	public IceAgeAbility()
	{
		super("Ice Age", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(15);
		this.setDescription("Freezes a large area around the user and everyone inside of it.");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		for (int i = -15; i < 15; i++)
		{
			for (int j = -10; j < 10; j++)
			{
				for (int k = -15; k < 15; k++)
				{
					double posX = player.posX + i + (i < -WyHelper.randomWithRange(8, 12) || i > WyHelper.randomWithRange(8, 12) ? WyHelper.randomWithRange(-5, 5) : 0);
					double posY = player.posY + j;
					double posZ = player.posZ + k + (k < -WyHelper.randomWithRange(8, 12) || k > WyHelper.randomWithRange(8, 12) ? WyHelper.randomWithRange(-5, 5) : 0);

					AbilityHelper.placeBlockIfAllowed(player.world, posX, posY, posZ, Blocks.PACKED_ICE, "core", "foliage");
				}
			}
		}

		List<LivingEntity> list = WyHelper.<LivingEntity>getEntitiesNear(player.getPosition(), player.world, 15);
		list.remove(player);

		/*for (LivingEntity target : list)
		{
			new DFEffectHieSlowness(target, 200);
		}*/

		PARTICLES.spawn(player.world, player.posX, player.posY, player.posZ, 0, 0, 0);

		return true;
	}
}
