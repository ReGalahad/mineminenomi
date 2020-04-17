package xyz.pixelatedw.mineminenomi.abilities.doku;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.entities.zoan.VenomDemonZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.doku.VenomDemonParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;

public class VenomDemonAbility extends ZoanAbility
{
	public static final Ability INSTANCE = new VenomDemonAbility();

	private static final ParticleEffect PARTICLES = new VenomDemonParticleEffect();
	private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(AirBlockProtectionRule.INSTANCE, FoliageBlockProtectionRule.INSTANCE); 

	public VenomDemonAbility()
	{
		super("Venom Demon", AbilityCategory.DEVIL_FRUIT, VenomDemonZoanInfo.FORM);
		this.setMaxCooldown(3);
		this.setThreshold(60);
		this.setDescription("The user coats himself in layers of strong corrosive venom, becoming a Venom Demon and leaving a highly poisonous trail.");

		this.duringContinuityEvent = this::duringContinuity;
	}
	
	private void duringContinuity(PlayerEntity player, int timer)
	{
		if(player.world.getBlockState(player.getPosition().down()).isSolid() && !AbilityHelper.isNearbyKairoseki(player))
		{
			for(int x = -1; x < 1; x++)
			for(int z = -1; z < 1; z++)
			{
				AbilityHelper.placeBlockIfAllowed(player.world, player.posX + x, player.posY, player.posZ + z, ModBlocks.DEMON_POISON, GRIEF_RULE);
			}
		}
		
		PARTICLES.spawn(player.world, player.posX, player.posY, player.posZ, 0, 0, 0);
	}
}
