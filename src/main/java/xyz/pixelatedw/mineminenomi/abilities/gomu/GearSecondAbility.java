package xyz.pixelatedw.mineminenomi.abilities.gomu;

import java.util.UUID;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.gomu.GearSecondParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;

public class GearSecondAbility extends ContinuousAbility
{
	public static final GearSecondAbility INSTANCE = new GearSecondAbility();
	
	private static final AttributeModifier MODIFIER = new AttributeModifier(UUID.fromString("a44a9644-369a-4e18-88d9-323727d3d85b"), "Gear Second Modifier", 1.02, Operation.MULTIPLY_BASE);
	private static final ParticleEffect PARTICLES = new GearSecondParticleEffect();
	
	public GearSecondAbility()
	{
		super("Gear Second", AbilityCategory.DEVIL_FRUIT);
		this.setThreshold(60);
		this.setDescription("By speding up their blood flow, the user gains strength, speed and mobility.");

		this.duringContinuityEvent = this::duringContinuity;
		this.onEndContinuityEvent = this::onEndContinuityEvent;
	}

	private void duringContinuity(PlayerEntity player, int passiveTimer)
	{
		IAttributeInstance attr = player.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
		if(!attr.hasModifier(MODIFIER))
			attr.applyModifier(MODIFIER);
		
		if(passiveTimer % 10 == 0)
			PARTICLES.spawn(player.world, player.posX, player.posY, player.posZ, 0, 0, 0);
	}

	private boolean onEndContinuityEvent(PlayerEntity player)
	{
		int cooldown = (int) Math.round(this.continueTime / 25.0);
		this.setMaxCooldown(cooldown);
		
		IAttributeInstance attr = player.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
		if(attr.hasModifier(MODIFIER))
			attr.removeModifier(MODIFIER);
		
		return true;
	}
}
