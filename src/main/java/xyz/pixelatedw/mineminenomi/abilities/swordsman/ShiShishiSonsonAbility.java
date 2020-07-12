package xyz.pixelatedw.mineminenomi.abilities.swordsman;

import java.util.List;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiImbuingAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

public class ShiShishiSonsonAbility extends Ability
{
	public static final ShiShishiSonsonAbility INSTANCE = new ShiShishiSonsonAbility();

	public ShiShishiSonsonAbility()
	{
		super("Shi Shishi Sonson", AbilityCategory.RACIAL);
		this.setMaxCooldown(8);
		this.setDescription("The user dashes forward and rapidly slashes the opponent.");

		this.onUseEvent = this::onUseEvent;
		this.duringCooldownEvent = this::duringCooldown;
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
		
		Vec3d speed = WyHelper.propulsion(player, 3, 3);
		player.setMotion(speed.x, 0.2, speed.z);
		player.velocityChanged = true;
		((ServerWorld) player.world).getChunkProvider().sendToTrackingAndSelf(player, new SAnimateHandPacket(player, 0));
		
		return true;
	}
	
	private void duringCooldown(PlayerEntity player, int cooldownTimer)
	{
		if (this.canDealDamage())
		{
			List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 1.6);
			list.remove(player);

			list.stream().forEach(entity ->
			{
				entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 10);
			});
		}
	}
	
	// Cooldown = 8*20 = 160 
	// Damage Frame = 6*20 = 120
	// 160-120=40 ticks or 2 seconds of damage frames
	public boolean canDealDamage()
	{
		if(this.cooldown > 6 * 20) 
			return true;
		return false;
	}
}