package xyz.pixelatedw.mineminenomi.api.abilities;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncDevilFruitPacket;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.server.SSyncAbilityDataPacket;

public class ZoanAbility extends ContinuousAbility
{
	private String zoanForm;
	
	public ZoanAbility(String name, AbilityCategory category, String form)
	{
		super(name, category);
		
		this.zoanForm = form;
		
		this.onStartContinuityEvent = this::onStartContinuityEvent;
		this.onEndContinuityEvent = this::onEndContinuityEvent;
	}
	
	private boolean onStartContinuityEvent(PlayerEntity player)
	{
		IDevilFruit props = DevilFruitCapability.get(player);
		IAbilityData abilityProps = AbilityDataCapability.get(player);

		if (props.getZoanPoint().isEmpty())
			props.setZoanPoint("");
		
		props.setZoanPoint(this.zoanForm);
		WyNetwork.sendToAll(new SSyncDevilFruitPacket(player.getEntityId(), props));
		WyNetwork.sendToAll(new SSyncAbilityDataPacket(abilityProps));
//		ModMain.proxy.updateEyeHeight(player);
//		ModNetwork.sendTo(new SRecalculateEyeHeightPacket(), (ServerPlayerEntity) player);
		
		return true;
	}

	private boolean onEndContinuityEvent(PlayerEntity player)
	{
		IDevilFruit props = DevilFruitCapability.get(player);

		props.setZoanPoint("");
		
		WyNetwork.sendToAll(new SSyncDevilFruitPacket(player.getEntityId(), props));
		
		return true;
	}
	
	protected boolean canMorphIn(PlayerEntity player, String form)
	{
		IDevilFruit props = DevilFruitCapability.get(player);

		return WyHelper.isNullOrEmpty(props.getZoanPoint()) || props.getZoanPoint().equalsIgnoreCase(form);
	}
}
