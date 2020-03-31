package xyz.pixelatedw.mineminenomi.abilities.ope;

import java.util.List;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitsHelper;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;

public class TaktAbility extends ContinuousAbility
{
	public static final Ability INSTANCE = new TaktAbility();
	
	public TaktAbility()
	{
		super("Takt", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(10);
		this.setThreshold(5);
		this.setDescription("Lifts all entities inside ROOM, making them unable to move.");
		
		this.onStartContinuityEvent = this::onStartContinuityEvent;
		this.duringContinuity = this::duringContinuity; 
	}
	
	private boolean onStartContinuityEvent(PlayerEntity player)
	{
		if(!DevilFruitsHelper.isEntityInRoom(player))
		{
			WyHelper.sendMsgToPlayer(player, new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_ONLY_IN_ROOM, this.getName()).getFormattedText());
			return false;
		}
		
		return true;
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
		
		list.stream().forEach(entity -> 
		{
			if(entity.posY <= player.posY + 5)
				entity.setPositionAndUpdate(entity.posX, entity.posY + 0.5, entity.posZ);
		});
	}

}
