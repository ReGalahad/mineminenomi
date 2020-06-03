package xyz.pixelatedw.mineminenomi.abilities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import xyz.pixelatedw.mineminenomi.init.ModArmors;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.wypi.abilities.IParallelContinuousAbility;

public class ZoomAbility extends ContinuousAbility implements IParallelContinuousAbility
{
	public static final ZoomAbility INSTANCE = new ZoomAbility();
	
	public ZoomAbility()
	{
		super("Zoom", AbilityCategory.RACIAL);

		this.onStartContinuityEvent = this::onStartContinuityEvent;
	}
	
	private boolean onStartContinuityEvent(PlayerEntity player)
	{
		if(player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() != ModArmors.SNIPER_GOGGLES)
			return false;
					
		return true;
	}
}
