package xyz.pixelatedw.mineminenomi.abilities.kachi;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.abilities.PunchAbility;
import xyz.pixelatedw.wypi.abilities.events.SetOnFireEvent;

public class HotBoilingSpecialAbility extends PunchAbility
{
	public static final HotBoilingSpecialAbility INSTANCE = new HotBoilingSpecialAbility();

	public HotBoilingSpecialAbility()
	{
		super("Hot Boiling Special", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(5);

		this.onHitEntityEvent = this::onHitEntityEvent;
	}

	private float onHitEntityEvent(PlayerEntity player, LivingEntity target)
	{
		SetOnFireEvent event = new SetOnFireEvent(player, target, 20);
		if (!MinecraftForge.EVENT_BUS.post(event))
			target.setFire(20);

		return 10;
	}
}
