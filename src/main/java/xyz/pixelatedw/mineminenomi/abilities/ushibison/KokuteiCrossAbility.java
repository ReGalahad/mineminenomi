package xyz.pixelatedw.mineminenomi.abilities.ushibison;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.entities.zoan.BisonHeavyZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.ushibison.KokuteiCrossParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.PunchAbility;

public class KokuteiCrossAbility extends PunchAbility
{
	public static final KokuteiCrossAbility INSTANCE = new KokuteiCrossAbility();

	private static final ParticleEffect PARTICLES = new KokuteiCrossParticleEffect();
	
	public KokuteiCrossAbility()
	{
		super("Kokutei Cross", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(7);
		this.setDescription("The transformed user crosses their hooves to hit the opponent.");
				
		this.onStartContinuityEvent = this::onStartContinuityEvent;
		this.onHitEntityEvent = this::onHitEntityEvent;
	}
	
	private boolean onStartContinuityEvent(PlayerEntity player)
	{
		IDevilFruit props = DevilFruitCapability.get(player);
		
		if (!props.getZoanPoint().equalsIgnoreCase(BisonHeavyZoanInfo.FORM))
		{
			WyHelper.sendMsgToPlayer(player, new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ZOAN_FORM_SINGLE, this.getName(), BisonHeavyPointAbility.INSTANCE.getName()).getFormattedText());
			return false;
		}
		
		return true;
	}
	
	private float onHitEntityEvent(PlayerEntity player, LivingEntity target)
	{
<<<<<<< HEAD
		Vec3d speed = WyHelper.propulsion(player, 1.2, 1.2);
=======
		Vec3d speed = WyHelper.propulsion(player, 2f, 2f);
>>>>>>> 4e39f997fbd9ca0e07fb845c19dbeb92dcc91dd9
		target.setMotion(speed.x, player.getMotion().getY(), speed.z);
		if(target instanceof PlayerEntity)
			((ServerPlayerEntity)target).connection.sendPacket(new SEntityVelocityPacket(target));
		
		PARTICLES.spawn(player.world, target.posX, target.posY, target.posZ, 0, 0, 0);
		
		return 20;
	}
}
