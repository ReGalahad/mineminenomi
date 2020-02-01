package xyz.pixelatedw.mineminenomi.abilities.ope;

import java.util.List;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability.Category;
import xyz.pixelatedw.mineminenomi.helpers.DevilFruitsHelper;

public class TaktAbility extends ContinuousAbility
{
	public static final Ability INSTANCE = new TaktAbility();
	
	public TaktAbility()
	{
		super("Takt", Category.DEVIL_FRUIT);
		this.setMaxCooldown(10);
		this.setThreshold(5);
		this.setDescription("Lifts all entities inside ROOM, making them unable to move.");
		
		this.onStartContinuityEvent = this::onStartContinuityEvent;
		this.duringContinuity = this::duringContinuity; 
	}
	
	private boolean onStartContinuityEvent(PlayerEntity player)
	{
		boolean flag = true;
		
		if(!DevilFruitsHelper.isEntityInRoom(player))
		{
			flag = false;
			WyHelper.sendMsgToPlayer(player, "" + this.getName() + " can only be used inside ROOM !");
		}
		
		return flag;
	}
	
	private void duringContinuity(PlayerEntity player, int timer)
	{
		if (!DevilFruitsHelper.isEntityInRoom(player))
		{
			this.stopContinuity(player);
			return;
		}
		
		List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 40);
		list.remove(player);
		list.removeIf(entity -> !DevilFruitsHelper.isEntityInRoom(entity));
		
		list.parallelStream().forEach(entity -> 
		{
			if(entity.posY <= player.posY + 5)
				entity.setPositionAndUpdate(entity.posX, entity.posY + 0.5, entity.posZ);
		});
	}

}
