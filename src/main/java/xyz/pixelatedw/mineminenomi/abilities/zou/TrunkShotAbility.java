package xyz.pixelatedw.mineminenomi.abilities.zou;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.entities.projectiles.zou.TrunkShotProjectile;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZouGuardZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.ZouHeavyZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;

public class TrunkShotAbility extends Ability
{
	public static final TrunkShotAbility INSTANCE = new TrunkShotAbility();

	public TrunkShotAbility()
	{
		super("Trunk Shot", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(5);

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		IDevilFruit props = DevilFruitCapability.get(player);
		
		if (!props.getZoanPoint().equalsIgnoreCase(ZouGuardZoanInfo.FORM) && !props.getZoanPoint().equalsIgnoreCase(ZouHeavyZoanInfo.FORM))
		{
			WyHelper.sendMsgToPlayer(player, new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ZOAN_FORM_DOUBLE, this.getName(), ZouGuardPointAbility.INSTANCE.getName(), ZouHeavyPointAbility.INSTANCE.getName()).getFormattedText());
			return false;
		}
		
		TrunkShotProjectile proj = new TrunkShotProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2.5f, 0);
		
		return true;
	}
}