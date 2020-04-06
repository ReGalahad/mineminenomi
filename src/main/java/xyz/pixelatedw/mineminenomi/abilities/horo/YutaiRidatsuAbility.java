package xyz.pixelatedw.mineminenomi.abilities.horo;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.entities.PhysicalBodyEntity;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;

public class YutaiRidatsuAbility extends ContinuousAbility
{
	public static final YutaiRidatsuAbility INSTANCE = new YutaiRidatsuAbility();

	private int posX, posY, posZ;
	private PhysicalBodyEntity body;
	
	public YutaiRidatsuAbility()
	{
		super("Yutai Ridatsu", AbilityCategory.DEVIL_FRUIT);
		this.setDescription("The user's spirit leaves her body. Allowing them to freely explore the nearby areas.");
		this.setThreshold(60);
		
		this.onStartContinuityEvent = this::onStartContinuityEvent;
		this.duringContinuityEvent = this::duringContinuityEvent;
		this.onEndContinuityEvent = this::onEndContinuityEvent;
	}

	private boolean onStartContinuityEvent(PlayerEntity player)
	{
		if(player.isCreative() || player.isSpectator())
			return true;
		
		player.setMotion(0, 5, 0);
		player.velocityChanged = true;
		
		this.posX = player.getPosition().getX();
		this.posY = player.getPosition().getY();
		this.posZ = player.getPosition().getZ();
		
		this.body = new PhysicalBodyEntity(player.world);
		this.body.setOwner(player.getUniqueID());
		player.world.addEntity(this.body);
		
		return true;
	}
	
	private void duringContinuityEvent(PlayerEntity player, int continueTime)
	{
		if(Math.sqrt(player.getDistanceSq(this.posX, this.posY, this.posZ)) > 40)
		{
			this.stopContinuity(player);
		}
	}
	
	private boolean onEndContinuityEvent(PlayerEntity player)
	{
		double cooldown = this.continueTime / 20;
		
		this.setMaxCooldown(cooldown);
		
		if(player.isCreative() || player.isSpectator())
			return true;
		
		if(this.body != null)
			this.body.remove();
		player.setPositionAndUpdate(this.posX, this.posY, this.posZ);
		
		return true;
	}
}
