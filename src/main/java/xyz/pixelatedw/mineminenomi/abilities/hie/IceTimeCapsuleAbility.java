package xyz.pixelatedw.mineminenomi.abilities.hie;

import java.util.List;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability.Category;

public class IceTimeCapsuleAbility extends Ability
{
	public static final Ability INSTANCE = new IceTimeCapsuleAbility();

	public IceTimeCapsuleAbility()
	{
		super("Ice Time Capsule", Category.DEVIL_FRUIT);
		this.setMaxCooldown(15);
		this.setDescription("A wave of ice is sent along the ground and freezes every enemy it hits, locking them in an ice capsule.");

		this.onUseEvent = this::onUseEvent;
	}

	private void onUseEvent(PlayerEntity player, Ability ability)
	{
		List<LivingEntity> list = WyHelper.<LivingEntity>getEntitiesNear(player.getPosition(), player.world, 25);
		list.remove(player);
		
		for (LivingEntity target : list)
		{
			WyHelper.createFilledCube(target, new int[] { 2, 4, 2 }, Blocks.PACKED_ICE, "air", "foliage");
		}
	}
}
