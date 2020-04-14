package xyz.pixelatedw.mineminenomi.abilities.ito;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.misc.BlackKnightEntity;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.wypi.abilities.IParallelContinuousAbility;

public class BlackKnightAbility extends ContinuousAbility implements IParallelContinuousAbility
{
	public static final BlackKnightAbility INSTANCE = new BlackKnightAbility();
	
	private BlackKnightEntity knight = null;
	
	public BlackKnightAbility()
	{
		super("Black Knight", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(1);
		this.setThreshold(120);
		this.setDescription("Creates a clone of himself made entirely out of compressed strings.");

		this.onStartContinuityEvent = this::onStartContinuityEvent;
		this.onEndContinuityEvent = this::onEndContinuityEvent;
	}
	
	
	private boolean onStartContinuityEvent(PlayerEntity player)
	{
		this.knight = new BlackKnightEntity(player.world);
		this.knight.setPositionAndRotation(player.posX, player.posY, player.posZ, player.rotationYaw, player.rotationPitch);
		this.knight.setOwner(player.getUniqueID());
		player.world.addEntity(this.knight);
		
		return true;
	}
	
	private boolean onEndContinuityEvent(PlayerEntity player)
	{
		if(this.knight != null)
			this.knight.remove();
		
		return true;
	}
}
