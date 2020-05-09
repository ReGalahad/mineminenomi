package xyz.pixelatedw.mineminenomi.api.helpers;

import java.util.List;
import java.util.stream.Collectors;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.EntityRayTraceResult;
import xyz.pixelatedw.mineminenomi.api.abilities.TempoAbility;
import xyz.pixelatedw.mineminenomi.items.weapons.ClimaTactItem;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.artofweather.FailedTempoParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

public class ArtOfWeatherHelper
{
	private static final ParticleEffect FAILED_TEMPO_PARTICLES = new FailedTempoParticleEffect();

	public static void checkForTempo(PlayerEntity player, ParticleEffect chargeParticles)
	{
		ClimaTactItem climaTact = ((ClimaTactItem) player.getHeldItemMainhand().getItem());
		String tempoCombo = climaTact.checkCharge(player.getHeldItemMainhand());
		IAbilityData props = AbilityDataCapability.get(player);
		EntityRayTraceResult trace = WyHelper.rayTraceEntities(player, 1.5);
				
		if (tempoCombo.length() == 3)
		{
			List availableTempos = props.getUnlockedAbilities(AbilityCategory.RACIAL).stream().filter(ability -> ability instanceof TempoAbility).collect(Collectors.toList());		
			boolean hasTempo = false;
			
			for(TempoAbility tempo : (List<TempoAbility>) availableTempos)
			{
				if(tempo.canUseTempo(player, null))
				{
					tempo.use(player);
					climaTact.emptyCharge(player.getHeldItemMainhand());
					hasTempo = true;
					break;
				}
			}
			
			if(!hasTempo)
			{
				FAILED_TEMPO_PARTICLES.spawn(player.world, player.posX, player.posY, player.posZ, 0, 0, 0);
				climaTact.emptyCharge(player.getHeldItemMainhand());
			}
		}
		else
			chargeParticles.spawn(player.world, trace.getHitVec().getX(), player.posY, trace.getHitVec().getZ(), 0, 0, 0);
	}
	
}
