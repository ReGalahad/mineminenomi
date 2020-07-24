package xyz.pixelatedw.mineminenomi.abilities;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.SpecialFlyingParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.PassiveAbility;

public class SpecialFlyAbility extends PassiveAbility
{
	public static final SpecialFlyAbility INSTANCE = new SpecialFlyAbility();

	private static final ParticleEffect PARTICLES_SUNA = new SpecialFlyingParticleEffect(ModResources.SUNA2);
	private static final ParticleEffect PARTICLES_GASU = new SpecialFlyingParticleEffect(ModResources.GASU);
	private static final ParticleEffect PARTICLES_MOKU = new SpecialFlyingParticleEffect(ModResources.MOKU);
	
	public SpecialFlyAbility()
	{
		super("Special Fly", AbilityCategory.DEVIL_FRUIT);
		
		this.duringPassiveEvent = this::duringPassiveEvent;
	}

	private void duringPassiveEvent(PlayerEntity player)
	{
		if(CommonConfig.instance.isSpecialFlyingEnabled())
		{
			player.abilities.allowFlying = true;

			if(!player.world.isRemote && !player.abilities.isCreativeMode && player.abilities.isFlying)
			{
				if(DevilFruitHelper.hasDevilFruit(player, ModAbilities.SUNA_SUNA_NO_MI))
					PARTICLES_SUNA.spawn(player.world, player.posX, player.posY, player.posZ, 0, 0, 0);
				else if(DevilFruitHelper.hasDevilFruit(player, ModAbilities.GASU_GASU_NO_MI))
					PARTICLES_GASU.spawn(player.world, player.posX, player.posY, player.posZ, 0, 0, 0);
				else if(DevilFruitHelper.hasDevilFruit(player, ModAbilities.MOKU_MOKU_NO_MI))
					PARTICLES_MOKU.spawn(player.world, player.posX, player.posY, player.posZ, 0, 0, 0);
			}
		}
	}
}
