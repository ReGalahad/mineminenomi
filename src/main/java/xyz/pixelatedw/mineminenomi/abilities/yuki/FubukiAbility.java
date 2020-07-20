package xyz.pixelatedw.mineminenomi.abilities.yuki;

import java.util.List;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import xyz.pixelatedw.mineminenomi.api.helpers.CrewHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.yuki.FubukiParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.math.ISphere;
import xyz.pixelatedw.wypi.math.Sphere;

public class FubukiAbility extends Ability
{
	public static final FubukiAbility INSTANCE = new FubukiAbility();

	private static final ParticleEffect PARTICLES = new FubukiParticleEffect();
	
	public FubukiAbility()
	{
		super("Fubuki", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(10);
		this.setDescription("The user releases an extremely cold stream of snow that spreads into the air around them.");
		
		this.onUseEvent = this::onUseEvent;
	}
	
	private boolean onUseEvent(PlayerEntity player)
	{

		if (CommonConfig.instance.isGriefingEnabled())
		{
			Sphere.generate((int) player.posX, (int) player.posY, (int) player.posZ, 25, new ISphere()
			{
				@Override
				public void call(int x, int y, int z)
				{
					if (player.world.isAirBlock(new BlockPos(x, y, z)) && player.world.getBlockState(new BlockPos(x, y - 1, z)).isSolid() && player.world.getBlockState(new BlockPos(x, y - 1, z)).getBlock() != Blocks.SNOW)
						player.world.setBlockState(new BlockPos(x, y, z), Blocks.SNOW.getDefaultState());
				}
			});
		}
		
		List<LivingEntity> targets = WyHelper.<LivingEntity>getEntitiesNear(player.getPosition(), player.world, 25, CrewHelper.NOT_IN_CREW_PREDICATE, LivingEntity.class);
		targets.remove(player);
		
		for (LivingEntity entity : targets)
		{
			entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 12);
			entity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 200, 2));
		}
		
		PARTICLES.spawn(player.world, player.posX, player.posY, player.posZ, 0, 0, 0);
		
		return true;
	}
}
