package xyz.pixelatedw.mineminenomi.abilities.artofweather;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather.GustSwordProjectile;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.items.weapons.ClimaTactItem;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.RepeaterAbility;

public class GustSwordAbility extends RepeaterAbility
{
	public static final GustSwordAbility INSTANCE = new GustSwordAbility();

	public GustSwordAbility()
	{
		super("Gust Sword", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(10);
		this.setMaxRepearCount(5, 3);

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
		
		GustSwordProjectile proj = new GustSwordProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 3);
		
		return true;
	}
}