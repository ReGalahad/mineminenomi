package xyz.pixelatedw.mineminenomi.abilities.kage;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.misc.DoppelmanEntity;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.wypi.abilities.IParallelContinuousAbility;

public class DoppelmanAbility extends ContinuousAbility implements IParallelContinuousAbility
{
	public static final DoppelmanAbility INSTANCE = new DoppelmanAbility();
	
	private DoppelmanEntity doppelman = null;
	
	public DoppelmanAbility()
	{
		super("Doppelman", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(1);
		this.setThreshold(120);
		this.setDescription("Creates a living version of the user's shadow to help them fight.");

		this.onStartContinuityEvent = this::onStartContinuityEvent;
		this.onEndContinuityEvent = this::onEndContinuityEvent;
	}

	private boolean onStartContinuityEvent(PlayerEntity player)
	{
		this.setMaxCooldown(1);
		this.doppelman = new DoppelmanEntity(player.world);
		this.doppelman.setPositionAndRotation(player.posX, player.posY, player.posZ, player.rotationYaw, player.rotationPitch);
		this.doppelman.setOwner(player.getUniqueID());
		player.world.addEntity(this.doppelman);
		
		return true;
	}
	
	private boolean onEndContinuityEvent(PlayerEntity player)
	{
		if(this.doppelman != null)
		{
			if(player.onGround && player.isSneaking())
			{
				this.doppelman.isAggressive = !this.doppelman.isAggressive;
				WyHelper.sendMsgToPlayer(player, "Your Doppelman is now " + (this.doppelman.isAggressive ? "Agressive" : "Defensive"));
				return false;
			}
			
			this.doppelman.remove();
		}
				
		return true;
	}
	
	public DoppelmanEntity getDoppelman()
	{
		return this.doppelman;
	}
}
