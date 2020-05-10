package xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.TempoAbility;
import xyz.pixelatedw.mineminenomi.entities.mobs.misc.MirageCloneEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather.MirageTempoCloudEntity;
import xyz.pixelatedw.mineminenomi.items.weapons.ClimaTactItem;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;

public class MirageTempo extends TempoAbility
{
	public static final MirageTempo INSTANCE = new MirageTempo();

	public MirageTempo()
	{
		super("Mirage Tempo", AbilityCategory.RACIAL);
		
		this.onUseEvent = this::onUseEvent;
		this.canUseCheck = this::canUseCheck;
	}
	
	public boolean canUseCheck(PlayerEntity player, ICanUse check)
	{
		if (player.getHeldItemMainhand().getItem() instanceof ClimaTactItem)
		{
			ClimaTactItem climaTact = ((ClimaTactItem) player.getHeldItemMainhand().getItem());
			String tempoCombo = climaTact.checkCharge(player.getHeldItemMainhand());
			return tempoCombo.equalsIgnoreCase("CCH");
		}

		return false;
	}
	
	private boolean onUseEvent(PlayerEntity player)
	{
		MirageTempoCloudEntity smokeCloud = new MirageTempoCloudEntity(player.world);
		smokeCloud.setLife(50);
		smokeCloud.setLocationAndAngles(player.posX, (player.posY + 1), player.posZ, 0, 0);
		smokeCloud.setMotion(0, 0, 0);
		smokeCloud.setThrower(player);
		player.world.addEntity(smokeCloud);

		for(int i = 0; i < 5; i++)
		{
			MirageCloneEntity mirageClone = new MirageCloneEntity(player.world);
			mirageClone.setPositionAndRotation(player.posX, player.posY, player.posZ, 180, 0);
			mirageClone.setOwner(player.getUniqueID());
			player.world.addEntity(mirageClone);		
		}
		
		return true;
	}
}
