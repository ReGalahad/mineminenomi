package xyz.pixelatedw.mineminenomi.abilities.gura;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.gura.TenchiMeidoParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.ChargeableAbility;

public class ShimaYurashiAbility extends ChargeableAbility
{
	public static final Ability INSTANCE = new ShimaYurashiAbility();

	private static final ParticleEffect PARTICLES = new TenchiMeidoParticleEffect();
	private List<BlockPos> blocks = new ArrayList<BlockPos>();

	public ShimaYurashiAbility()
	{
		super("Shima Yurashi", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(40);
		this.setMaxChargeTime(10);
		this.setDescription("The user grabs the air and pulls it downwards after which the nearby land and entities are sent flying.");

		this.onStartChargingEvent = this::onStartChargingEvent;
		this.duringChargingEvent = this::duringChargingEvent;
		this.onEndChargingEvent = this::onEndChargingEvent;
	}
	
	private boolean onStartChargingEvent(PlayerEntity player)
	{
		int areaSize = 20;
		for(int i = -areaSize; i <= areaSize; i++)
		{
			for(int j = -areaSize; j <= areaSize; j++)
			{
				for(int k = -areaSize; k <= areaSize; k++)
				{
					double posX = player.posX + i + (i < -WyHelper.randomWithRange(15, 20) || i > WyHelper.randomWithRange(15, 20) ? WyHelper.randomWithRange(-10, 10) : 0);
					double posY = player.posY + j;
					double posZ = player.posZ + k + (k < -WyHelper.randomWithRange(15, 20) || k > WyHelper.randomWithRange(15, 20) ? WyHelper.randomWithRange(-10, 10) : 0);
					
					BlockPos pos = new BlockPos(posX, posY, posZ);
					BlockState state = player.world.getBlockState(pos);
					
					boolean airBlocksAbove = player.world.getBlockState(pos.up()).getBlock() == Blocks.AIR && player.world.getBlockState(pos.up(2)).getBlock() == Blocks.AIR;
					boolean isAllowedToMove = !Arrays.<Block>asList(ItemsHelper.RESTRICTED_BLOCKS).contains(state.getBlock());
					
					if(airBlocksAbove && isAllowedToMove && state.isSolid() && !this.blocks.contains(pos) && CommonConfig.instance.isGriefingEnabled())
					{
						this.blocks.add(pos);
					}
				}
			}
		}

		return true;
	}

	private void duringChargingEvent(PlayerEntity player, int chargeTime)
	{
		if(chargeTime % 2 == 0)
			PARTICLES.spawn(player.world, player.posX, player.posY, player.posZ, 0, 0, 0);
		player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 10, 2000));
		player.setMotion(0, 0, 0);
		player.velocityChanged = true;
	}
	
	private boolean onEndChargingEvent(PlayerEntity player)
	{
		int i = 0;
		
		for(BlockPos pos : this.blocks)
		{
			i += WyHelper.randomWithRange(-1, 2);
			if(i % 2 != 0)
				continue;
			
			BlockState state = player.world.getBlockState(pos);
			if (player.world.getChunkProvider().isChunkLoaded(new ChunkPos(pos))) 
			{
				FallingBlockEntity fallingBlock = new FallingBlockEntity(player.world, pos.getX(), pos.getY(), pos.getZ(), state);
				fallingBlock.setMotion(0, 0.5 + (WyHelper.randomDouble() / 2), 0);
				fallingBlock.velocityChanged = true;
				player.world.addEntity(fallingBlock);			
			}
		}
		
		this.blocks.clear();
		
		List<LivingEntity> targets = WyHelper.<LivingEntity>getEntitiesNear(player.getPosition(), player.world, 20, FactionHelper.getOutsideGroupPredicate(player), LivingEntity.class);
		targets.removeIf(entity -> !entity.onGround);
		targets.remove(player);

		targets.stream().filter(target -> target != null && target.isAlive()).forEach(target ->
		{
			target.attackEntityFrom(DamageSource.MAGIC, 10);
			target.setMotion(0, 2, 0);
			target.velocityChanged = true;			
		});

		return true;
	}
}
