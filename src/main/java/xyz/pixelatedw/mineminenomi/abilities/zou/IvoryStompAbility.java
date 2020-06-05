package xyz.pixelatedw.mineminenomi.abilities.zou;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZouHeavyZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.PunchAbility;

public class IvoryStompAbility extends PunchAbility
{
	public static final IvoryStompAbility INSTANCE = new IvoryStompAbility();

	public IvoryStompAbility()
	{
		super("Ivory Stomp", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(10);
		
		this.onStartContinuityEvent = this::onStartContinuityEvent;
		this.onHitEntityEvent = this::onHitEntityEvent;
	}
	
	private boolean onStartContinuityEvent(PlayerEntity player)
	{
		IDevilFruit props = DevilFruitCapability.get(player);
		
		if (!props.getZoanPoint().equalsIgnoreCase(ZouHeavyZoanInfo.FORM))
		{
			WyHelper.sendMsgToPlayer(player, new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ZOAN_FORM_SINGLE, this.getName(), ZouHeavyPointAbility.INSTANCE.getName()).getFormattedText());
			return false;
		}
		
		return true;
	}
	
	private float onHitEntityEvent(PlayerEntity player, LivingEntity target)
	{
		Vec3d speed = WyHelper.propulsion(player, 2.5, 2.5);
		target.setMotion(speed.x, player.getMotion().getY() + 0.1, speed.z);
		if(target instanceof PlayerEntity)
			((ServerPlayerEntity)target).connection.sendPacket(new SEntityVelocityPacket(target));
				
		return 12;
	}
}
