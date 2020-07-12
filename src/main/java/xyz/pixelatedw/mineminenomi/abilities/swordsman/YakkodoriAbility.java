package xyz.pixelatedw.mineminenomi.abilities.swordsman;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiImbuingAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.swordsman.YakkodoriProjectile;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

public class YakkodoriAbility extends Ability
{
	public static final Ability INSTANCE = new YakkodoriAbility();

	public YakkodoriAbility()
	{
		super("Yakkodori", AbilityCategory.RACIAL);
		this.setMaxCooldown(6);
		this.setDescription("Launches a crescent moon-shaped slash, which destroys everything in its path");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		if (!AbilityHelper.canUseSwordsmanAbilities(player))
		{
			WyHelper.sendMsgToPlayer(player, new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_SWORD).getFormattedText());
			return false;
		}

		ItemStack stack = player.getHeldItemMainhand();		
		IAbilityData abilityProps = AbilityDataCapability.get(player);
		BusoshokuHakiImbuingAbility ability = abilityProps.getEquippedAbility(BusoshokuHakiImbuingAbility.INSTANCE);
		boolean hakiActiveFlag = ability != null && ability.isContinuous();
		if(!hakiActiveFlag)
		{
			stack.damageItem(1, player, (user) ->
			{
				user.sendBreakAnimation(EquipmentSlotType.MAINHAND);
			});
		}

		YakkodoriProjectile proj = new YakkodoriProjectile(player.world, player);
		player.world.addEntity(proj);
		proj.shoot(player, player.rotationPitch, player.rotationYaw, 0, 2f, 1);
		((ServerWorld) player.world).getChunkProvider().sendToTrackingAndSelf(player, new SAnimateHandPacket(player, 0));

		return true;
	}
}