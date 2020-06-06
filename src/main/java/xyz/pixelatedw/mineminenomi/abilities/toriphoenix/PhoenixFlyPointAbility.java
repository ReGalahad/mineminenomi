package xyz.pixelatedw.mineminenomi.abilities.toriphoenix;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
import xyz.pixelatedw.mineminenomi.entities.zoan.PhoenixFlyZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;

import java.util.UUID;

public class PhoenixFlyPointAbility extends ZoanAbility
{
	public static final PhoenixFlyPointAbility INSTANCE = new PhoenixFlyPointAbility();
	private static final AttributeModifier REGEN_RATE_MODIFIER = new AttributeModifier(UUID.fromString("eb43ce22-3c5a-45a0-810c-03c0f552efe7"), "Health Regeneration Speed Multiplier", 1.5f, AttributeModifier.Operation.ADDITION).setSaved(false);
	public float speed = 0;

	public PhoenixFlyPointAbility()
	{
		super("Phoenix Fly Point", AbilityCategory.DEVIL_FRUIT, PhoenixFlyZoanInfo.FORM);
		this.setDescription("Allows the user to transforms into a phoenix, which focuses on speed and healing, Allows the user to use 'Phoenix Goen', 'Tensei no Soen' and 'Blue Flames of Resurrection'");
	
		this.onStartContinuityEvent = this::onStartContinuityEvent;
		this.addZoanModifier(ModAttributes.REGEN_RATE, REGEN_RATE_MODIFIER);
	}
	
	@Override
	protected boolean onStartContinuityEvent(PlayerEntity player)
	{
		if(player.onGround) {
			WyHelper.sendMsgToPlayer(player, new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_ONLY_IN_AIR, this.getName()).getFormattedText());
			return false;
		}

		return super.onStartContinuityEvent(player);
	}
}
