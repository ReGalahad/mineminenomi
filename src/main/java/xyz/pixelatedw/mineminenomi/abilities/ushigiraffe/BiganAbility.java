package xyz.pixelatedw.mineminenomi.abilities.ushigiraffe;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.entities.projectiles.ushigiraffe.BiganProjectile;
import xyz.pixelatedw.mineminenomi.entities.zoan.GiraffeHeavyZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.GiraffeWalkZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;

public class BiganAbility extends Ability
{
	public static final BiganAbility INSTANCE = new BiganAbility();

	public BiganAbility()
	{
		super("Bigan", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(5);
		
		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		IDevilFruit props = DevilFruitCapability.get(player);		
		if (!props.getZoanPoint().equalsIgnoreCase(GiraffeHeavyZoanInfo.FORM) && !props.getZoanPoint().equalsIgnoreCase(GiraffeWalkZoanInfo.FORM))
		{
			WyHelper.sendMsgToPlayer(player, new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ZOAN_FORM_DOUBLE, this.getName(), GiraffeHeavyPointAbility.INSTANCE.getName(), GiraffeWalkPointAbility.INSTANCE.getName()).getFormattedText());
			return false;
		}
		
		BiganProjectile proj = new BiganProjectile(player.world, player);
		proj.setLocationAndAngles(player.posX, player.posY + 3, player.posZ, 0, 0);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 0);
		
		return true;
	}
}
