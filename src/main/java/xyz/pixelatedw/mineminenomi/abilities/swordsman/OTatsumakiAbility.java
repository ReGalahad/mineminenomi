package xyz.pixelatedw.mineminenomi.abilities.swordsman;

import java.util.List;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiImbuingAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.swordsman.OTatsumakiParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

public class OTatsumakiAbility extends Ability
{
	public static final Ability INSTANCE = new OTatsumakiAbility();
	public static final ParticleEffect PARTICLES = new OTatsumakiParticleEffect();
	
	public OTatsumakiAbility()
	{
		super("O Tatsumaki", AbilityCategory.RACIAL);
		this.setMaxCooldown(8);
		this.setDescription("By spinning, the user creates a small tornado, which slashes and weakens nearby opponents.");

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
		
		List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 2.5);
		list.remove(player);

		list.stream().forEach(entity ->
		{
			entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 15);
		});
		
		PARTICLES.spawn(player.world, player.posX, player.posY, player.posZ, 0, 0, 0);

		return true;
	}
}