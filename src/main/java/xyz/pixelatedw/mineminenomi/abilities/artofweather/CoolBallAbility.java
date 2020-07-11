package xyz.pixelatedw.mineminenomi.abilities.artofweather;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.helpers.ArtOfWeatherHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather.CoolBallProjectile;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.items.weapons.ClimaTactItem;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.artofweather.ChargedWeatherBallParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;

public class CoolBallAbility extends Ability
{
	public static final CoolBallAbility INSTANCE = new CoolBallAbility();

	private static final ParticleEffect CHARGE_PARTICLES = new ChargedWeatherBallParticleEffect(WyHelper.hexToRGB("#0055FF"), ModResources.HIE);

	public CoolBallAbility()
	{
		super("Cool Ball", AbilityCategory.RACIAL);
		this.setMaxCooldown(3);

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
			climaTact.chargeWeatherBall(player.getHeldItemMainhand(), "C");
			ArtOfWeatherHelper.checkForTempo(player, CHARGE_PARTICLES);
		}
		else
		{
			CoolBallProjectile proj = new CoolBallProjectile(player.world, player);
			proj.setLocationAndAngles(trace.getHitVec().getX(), player.posY + player.getEyeHeight() - 0.5, trace.getHitVec().getZ(), player.rotationYaw, player.rotationPitch);
			proj.setMotion(0, 0.30, 0);
			player.world.addEntity(proj);
		}

		return true;
	}
}
