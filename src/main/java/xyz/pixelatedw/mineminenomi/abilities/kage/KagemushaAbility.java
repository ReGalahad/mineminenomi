package xyz.pixelatedw.mineminenomi.abilities.kage;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

public class KagemushaAbility extends Ability
{
	public static final KagemushaAbility INSTANCE = new KagemushaAbility();

	public KagemushaAbility()
	{
		super("Kagemusha", AbilityCategory.DEVIL_FRUIT);
		this.setDescription("Allows the user to change its position with that of the Doppelman.");
		this.setMaxCooldown(5);

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		IAbilityData props = AbilityDataCapability.get(player);

		DoppelmanAbility ability = props.getEquippedAbility(DoppelmanAbility.INSTANCE);
		boolean isActive = ability != null && ability.isContinuous();
		
		if(isActive && ability.getDoppelman() != null)
		{
			BlockPos temp = player.getPosition();
			player.setPositionAndUpdate(ability.getDoppelman().posX, ability.getDoppelman().posY, ability.getDoppelman().posZ);
			ability.getDoppelman().setPositionAndUpdate(temp.getX(), temp.getY(), temp.getZ());
			return true;
		}
		
		return false;
	}
}
