package xyz.pixelatedw.MineMineNoMi3.events.customevents;

import cpw.mods.fml.common.eventhandler.Cancelable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent;
import xyz.pixelatedw.MineMineNoMi3.entities.zoan.EntityZoanMorph;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;

@Cancelable
public class MorphRenderEvent extends EntityEvent
{
	public EntityZoanMorph morph;
	
	public MorphRenderEvent(EntityZoanMorph morph) 
	{
		super(morph);
		this.morph = morph;
	}

}
