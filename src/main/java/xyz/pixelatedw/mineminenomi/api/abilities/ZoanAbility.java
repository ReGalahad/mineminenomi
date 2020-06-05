package xyz.pixelatedw.mineminenomi.api.abilities;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.packets.server.SRecalculateEyeHeightPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncDevilFruitPacket;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.wypi.abilities.IParallelContinuousAbility;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.server.SSyncAbilityDataPacket;

import java.util.HashMap;
import java.util.Map.Entry;

public class ZoanAbility extends ContinuousAbility implements IParallelContinuousAbility
{
	private String zoanForm;
	private HashMap<IAttribute, AttributeModifier> zoanModifiers = new HashMap<IAttribute, AttributeModifier>();
	
	public ZoanAbility(String name, AbilityCategory category, String form)
	{
		super(name, category);
		
		this.zoanForm = form;
		
		this.onStartContinuityEvent = this::onStartContinuityEvent;
		this.onEndContinuityEvent = this::onEndContinuityEvent;
	}
	
	protected boolean onStartContinuityEvent(PlayerEntity player)
	{
		IDevilFruit props = DevilFruitCapability.get(player);
		IAbilityData abilityProps = AbilityDataCapability.get(player);

		if (props.getZoanPoint().isEmpty())
			props.setZoanPoint("");
		
		for(Entry<IAttribute, AttributeModifier> entry : this.zoanModifiers.entrySet())
		{
			player.getAttribute(entry.getKey()).removeModifier(entry.getValue().setSaved(false));
			player.getAttribute(entry.getKey()).applyModifier(entry.getValue().setSaved(false));
		}
		
		// Need to set this before the updateEyeView method is called to ensure the ability is active
		this.setState(State.CONTINUOUS);
		
		props.setZoanPoint(this.zoanForm);
		WyNetwork.sendToAll(new SSyncDevilFruitPacket(player.getEntityId(), props));
		WyNetwork.sendToAll(new SSyncAbilityDataPacket(player.getEntityId(), abilityProps));

		// Updating the eye height
		MinecraftForge.EVENT_BUS.post(new EntityEvent.EyeHeight(player, player.getPose(), player.getSize(player.getPose()), player.getHeight()));
		WyNetwork.sendTo(new SRecalculateEyeHeightPacket(), player);
		
		return true;
	}

	protected boolean onEndContinuityEvent(PlayerEntity player)
	{
		IDevilFruit props = DevilFruitCapability.get(player);
		IAbilityData abilityProps = AbilityDataCapability.get(player);

		props.setZoanPoint("");
		
		for(Entry<IAttribute, AttributeModifier> entry : this.zoanModifiers.entrySet())
		{
			player.getAttribute(entry.getKey()).removeModifier(entry.getValue().setSaved(false));
		}
		
		WyNetwork.sendToAll(new SSyncDevilFruitPacket(player.getEntityId(), props));
		WyNetwork.sendToAll(new SSyncAbilityDataPacket(player.getEntityId(),abilityProps));

		// Updating the eye height
		MinecraftForge.EVENT_BUS.post(new EntityEvent.EyeHeight(player, player.getPose(), player.getSize(player.getPose()), player.getHeight()));
		WyNetwork.sendTo(new SRecalculateEyeHeightPacket(), player);
		
		return true;
	}
	
	protected boolean canMorphIn(PlayerEntity player, String form)
	{
		IDevilFruit props = DevilFruitCapability.get(player);

		return WyHelper.isNullOrEmpty(props.getZoanPoint()) || props.getZoanPoint().equalsIgnoreCase(form);
	}

	public HashMap<IAttribute, AttributeModifier> getZoanModifiers()
	{
		return this.zoanModifiers;
	}

	public void addZoanModifier(IAttribute attr, AttributeModifier modifier)
	{
		this.zoanModifiers.put(attr, modifier);
	}
}
