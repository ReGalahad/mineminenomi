package xyz.pixelatedw.mineminenomi.abilities.artofweather;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather.HeatBallProjectile;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.items.weapons.ClimaTactItem;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.artofweather.ChargedWeatherBallParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.artofweather.FailedTempoParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;

public class HeatBallAbility extends Ability
{
	public static final HeatBallAbility INSTANCE = new HeatBallAbility();

	private static final ParticleEffect FAILED_TEMPO_PARTICLES = new FailedTempoParticleEffect();
	private static final ParticleEffect CHARGE_PARTICLES = new ChargedWeatherBallParticleEffect(WyHelper.hexToRGB("#FF0000"));

	public HeatBallAbility()
	{
		super("Heat Ball", AbilityCategory.RACIAL);
		this.setMaxCooldown(5);

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		if (!ItemsHelper.isClimaTact(player.getHeldItemMainhand()))
		{
			WyHelper.sendMsgToPlayer(player, new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_CLIMA_TACT).getFormattedText());
			return false;
		}

		ClimaTactItem climaTact = ((ClimaTactItem) player.getHeldItemMainhand().getItem());
		EntityRayTraceResult trace = WyHelper.rayTraceEntities(player, 1.5);

		if (player.isSneaking())
		{
			climaTact.chargeWeatherBall(player.getHeldItemMainhand(), "H");

			String tempo = climaTact.checkCharge(player.getHeldItemMainhand());

			if (tempo.length() == 3)
			{
				if (tempo.equalsIgnoreCase("CCH"))
				{

					climaTact.emptyCharge(player.getHeldItemMainhand());
				}
				else
				{
					FAILED_TEMPO_PARTICLES.spawn(player.world, player.posX, player.posY, player.posZ, 0, 0, 0);
					climaTact.emptyCharge(player.getHeldItemMainhand());
				}
			}
			else
				CHARGE_PARTICLES.spawn(player.world, trace.getHitVec().getX(), player.posY, trace.getHitVec().getZ(), 0, 0, 0);
		}
		else
		{
			HeatBallProjectile proj = new HeatBallProjectile(player.world, player);
			proj.setLocationAndAngles(trace.getHitVec().getX(), player.posY + player.getEyeHeight() - 0.5, trace.getHitVec().getZ(), player.rotationYaw, player.rotationPitch);
			proj.setMotion(0, 0.30, 0);
			player.world.addEntity(proj);
		}

		return true;
	}
}
