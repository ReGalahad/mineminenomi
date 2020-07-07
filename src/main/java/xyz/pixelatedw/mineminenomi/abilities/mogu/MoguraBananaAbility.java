package xyz.pixelatedw.mineminenomi.abilities.mogu;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.entities.zoan.MoguHeavyZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.PunchAbility;

public class MoguraBananaAbility extends PunchAbility
{
	public static final MoguraBananaAbility INSTANCE = new MoguraBananaAbility();
	
	public MoguraBananaAbility()
	{
		super("Mogura Banana", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(6);
				
		this.onStartContinuityEvent = this::onStartContinuityEvent;
		this.onHitEntityEvent = this::onHitEntityEvent;
	}
	
	private boolean onStartContinuityEvent(PlayerEntity player)
	{
		IDevilFruit props = DevilFruitCapability.get(player);
		
		if (!props.getZoanPoint().equalsIgnoreCase(MoguHeavyZoanInfo.FORM))
		{
			WyHelper.sendMsgToPlayer(player, new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ZOAN_FORM_SINGLE, this.getName(), MoguHeavyPointAbility.INSTANCE.getName()).getFormattedText());
			return false;
		}
		
		return true;
	}
	
	private float onHitEntityEvent(PlayerEntity player, LivingEntity target)
	{
		Vec3d speed = WyHelper.propulsion(player, 3, 3);
		target.setMotion(speed.x, player.getMotion().getY() + 0.1, speed.z);
		if(target instanceof PlayerEntity)
			((ServerPlayerEntity)target).connection.sendPacket(new SEntityVelocityPacket(target));
				
		return 12;
	}
}
