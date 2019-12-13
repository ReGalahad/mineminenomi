package xyz.pixelatedw.mineminenomi.events.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraftforge.event.entity.EntityEvent;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;

public class YomiTriggerEvent extends EntityEvent
{
	public LivingEntity entity;
	public IDevilFruit oldPlayerData, newPlayerData;
	
	public YomiTriggerEvent(LivingEntity entity, IDevilFruit oldPlayerData, IDevilFruit newPlayerData) 
	{
		super(entity);
		this.entity = entity;
		this.oldPlayerData = oldPlayerData;
		this.newPlayerData = newPlayerData;
	}
}
