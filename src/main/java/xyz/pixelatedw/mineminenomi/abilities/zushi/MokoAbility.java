package xyz.pixelatedw.mineminenomi.abilities.zushi;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.zushi.MokoProjectile;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.RepeaterAbility;

public class MokoAbility extends RepeaterAbility
{
	public static final MokoAbility INSTANCE = new MokoAbility();

	public MokoAbility()
	{
		super("Moko", AbilityCategory.DEVIL_FRUIT);
		this.setDescription("Sends a wave of gravitational waves towards the enemies, slowing them down");
		this.setMaxCooldown(12);
		this.setMaxRepearCount(3, 5);

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		if (!ItemsHelper.isSword(player.getHeldItemMainhand()))
		{
			WyHelper.sendMsgToPlayer(player, new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_SWORD).getFormattedText());
			return false;
		}
		
		MokoProjectile proj = new MokoProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2.25f, 2);

		return true;
	}
}