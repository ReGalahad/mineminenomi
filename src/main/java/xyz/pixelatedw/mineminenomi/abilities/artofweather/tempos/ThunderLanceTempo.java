package xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos;

import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.TempoAbility;
import xyz.pixelatedw.mineminenomi.items.weapons.ClimaTactItem;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;

public class ThunderLanceTempo extends TempoAbility
{
	public static final ThunderLanceTempo INSTANCE = new ThunderLanceTempo();

	public ThunderLanceTempo()
	{
		super("Thunder Lance Tempo", AbilityCategory.RACIAL);
		
		this.onUseEvent = this::onUseEvent;
		this.canUseCheck = this::canUseCheck;
		this.duringCooldownEvent = this::duringCooldownEvent;
	}
	
	public boolean canUseCheck(PlayerEntity player, ICanUse check)
	{
		if(player.getHeldItemMainhand().getItem() instanceof ClimaTactItem)
		{
			ClimaTactItem climaTact = ((ClimaTactItem) player.getHeldItemMainhand().getItem());
			String tempoCombo = climaTact.checkCharge(player.getHeldItemMainhand());
			return tempoCombo.equalsIgnoreCase("TTT");
		}
		
		return false;
	}
	
	private boolean onUseEvent(PlayerEntity player)
	{
		if(player.getHeldItemMainhand().getItem() instanceof ClimaTactItem)
		{
			ClimaTactItem climaTact = ((ClimaTactItem) player.getHeldItemMainhand().getItem());
			climaTact.setDamageModifier(player.getHeldItemMainhand(), 1.5);
			this.setMaxCooldown(10);
		}
			
		return true;
	}
	
	private void duringCooldownEvent(PlayerEntity player, int cooldown)
	{
		if(cooldown <= 1)
		{
			if(player.getHeldItemMainhand().getItem() instanceof ClimaTactItem)
			{
				ClimaTactItem climaTact = ((ClimaTactItem) player.getHeldItemMainhand().getItem());
				climaTact.setDamageModifier(player.getHeldItemMainhand(), 1);
			}
		}
	}
}
