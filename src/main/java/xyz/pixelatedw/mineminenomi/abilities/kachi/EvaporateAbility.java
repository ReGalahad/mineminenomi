package xyz.pixelatedw.mineminenomi.abilities.kachi;

import java.util.List;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.LiquidBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.kachi.EvaporateParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class EvaporateAbility extends Ability
{
	public static final EvaporateAbility INSTANCE = new EvaporateAbility();

	private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(LiquidBlockProtectionRule.INSTANCE, FoliageBlockProtectionRule.INSTANCE);
	private static final ParticleEffect PARTICLES = new EvaporateParticleEffect();
	
	public EvaporateAbility()
	{
		super("Evaporate", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(15);
		
		this.onUseEvent = this::onUseEvent;
	}
	
	private boolean onUseEvent(PlayerEntity player)
	{
		List<BlockPos> coords = AbilityHelper.createFilledSphere(player.getEntityWorld(), (int) player.posX, (int) player.posY, (int) player.posZ, 6, Blocks.AIR, GRIEF_RULE);
		for (int count = 0; count < coords.size(); count++)
		{
			BlockPos pos = coords.get(count);
			if (player.getEntityWorld().getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ())).getBlock().equals(Blocks.AIR))
			{
				PARTICLES.spawn(player.world, pos.getX(), pos.getY(), pos.getZ(), 0, 0, 0);
			}
		}
		
		return true;
	}
}
