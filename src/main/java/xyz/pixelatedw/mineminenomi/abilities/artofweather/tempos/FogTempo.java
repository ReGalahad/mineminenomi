package xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos;

import java.util.List;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import xyz.pixelatedw.mineminenomi.api.abilities.TempoAbility;
import xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather.MirageTempoCloudEntity;
import xyz.pixelatedw.mineminenomi.items.weapons.ClimaTactItem;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;

public class FogTempo extends TempoAbility
{
	public static final FogTempo INSTANCE = new FogTempo();

	public FogTempo()
	{
		super("Fog Tempo", AbilityCategory.RACIAL);

		this.onUseEvent = this::onUseEvent;
		this.canUseCheck = this::canUseCheck;
	}

	public boolean canUseCheck(PlayerEntity player, ICanUse check)
	{
		if (player.getHeldItemMainhand().getItem() instanceof ClimaTactItem)
		{
			ClimaTactItem climaTact = ((ClimaTactItem) player.getHeldItemMainhand().getItem());
			String tempoCombo = climaTact.checkCharge(player.getHeldItemMainhand());
			return tempoCombo.equalsIgnoreCase("HHC");
		}

		return false;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		MirageTempoCloudEntity smokeCloud = new MirageTempoCloudEntity(player.world);
		smokeCloud.setLife(100);
		smokeCloud.setLocationAndAngles(player.posX, (player.posY + 1), player.posZ, 0, 0);
		smokeCloud.setMotion(0, 0, 0);
		smokeCloud.setThrower(player);
		player.world.addEntity(smokeCloud);

		List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 10);
		targets.remove(player);

		for (LivingEntity entity : targets)
		{
			entity.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 200, 1));
		}

		return true;
	}
}
