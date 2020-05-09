package xyz.pixelatedw.mineminenomi.abilities.artofweather;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather.WeatherEggProjectile;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.items.weapons.ClimaTactItem;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;

public class WeatherEggAbility extends Ability
{
	public static final WeatherEggAbility INSTANCE = new WeatherEggAbility();

	public WeatherEggAbility()
	{
		super("Weather Egg", AbilityCategory.RACIAL);
		this.setMaxCooldown(20);

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
		if(climaTact.getLevel() < 3)
		{
			WyHelper.sendMsgToPlayer(player, new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_SORCERY_CLIMA_TACT).getFormattedText());
			return false;
		}
		
		EntityRayTraceResult trace = WyHelper.rayTraceEntities(player, 1.5);
		WeatherEggProjectile proj = new WeatherEggProjectile(player.world, player);
		proj.setLocationAndAngles(trace.getHitVec().getX(), player.posY + player.getEyeHeight() - 0.5, trace.getHitVec().getZ(), player.rotationYaw, player.rotationPitch);
		proj.setMotion(0, 0.30, 0);
		player.world.addEntity(proj);

		return true;
	}
}
