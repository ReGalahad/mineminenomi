package xyz.pixelatedw.mineminenomi.abilities.toriphoenix;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.entities.projectiles.toriphoenix.PhoenixGoenProjectile;
import xyz.pixelatedw.mineminenomi.entities.zoan.PhoenixAssaultZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.PhoenixFlyZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.RepeaterAbility;

public class PhoenixGoenAbility extends RepeaterAbility
{
	public static final PhoenixGoenAbility INSTANCE = new PhoenixGoenAbility();

	public PhoenixGoenAbility()
	{
		super("Phoenix Goen", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(8);
		this.setMaxRepearCount(10, 3);
		this.setDescription("Launches a powerful fiery shockwave made of blue flames at the target.");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		IDevilFruit props = DevilFruitCapability.get(player);		
		if (!props.getZoanPoint().equalsIgnoreCase(PhoenixFlyZoanInfo.FORM) && !props.getZoanPoint().equalsIgnoreCase(PhoenixAssaultZoanInfo.FORM))
		{
			WyHelper.sendMsgToPlayer(player, new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ZOAN_FORM_DOUBLE, this.getName(), PhoenixFlyPointAbility.INSTANCE.getName(), PhoenixAssaultPointAbility.INSTANCE.getName()).getFormattedText());
			return false;
		}
		
		PhoenixGoenProjectile proj = new PhoenixGoenProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 4);

		return true;
	}
}