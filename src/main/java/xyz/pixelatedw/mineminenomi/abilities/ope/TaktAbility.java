package xyz.pixelatedw.mineminenomi.abilities.ope;

import java.util.List;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.CrewHelper;
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
		this.setMaxCooldown(12);
		this.setThreshold(10);
		this.setDescription("Lifts all entities inside ROOM, making them unable to move.");
		
		this.onStartContinuityEvent = this::onStartContinuityEvent;
		this.duringContinuityEvent = this::duringContinuity; 
	}
	
	private boolean onStartContinuityEvent(PlayerEntity player)
	{
		if(!AbilityHelper.isEntityInRoom(player))
		{
			WyHelper.sendMsgToPlayer(player, new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_ONLY_IN_ROOM, this.getName()).getFormattedText());
			return false;
		}
		
		return true;
	}
	
	private void duringContinuity(PlayerEntity player, int timer)
	{
		if (!AbilityHelper.isEntityInRoom(player))
		{
			this.stopContinuity(player);
			return;
		}
		
		List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 40, CrewHelper.NOT_IN_CREW_PREDICATE, LivingEntity.class);
		list.remove(player);
		list.removeIf(entity -> !AbilityHelper.isEntityInRoom(entity));
		
		list.stream().forEach(entity -> 
		{
			entity.setMotion(0,0,0);
			if(entity.posY <= player.posY + 3)
				entity.setPositionAndUpdate(entity.posX, entity.posY + 0.5, entity.posZ);
		});
	}

}
