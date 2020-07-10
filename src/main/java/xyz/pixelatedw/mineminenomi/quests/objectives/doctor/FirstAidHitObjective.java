package xyz.pixelatedw.mineminenomi.quests.objectives.doctor;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.abilities.doctor.FirstAidAbility;
import xyz.pixelatedw.mineminenomi.quests.objectives.HitEntityObjective;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

public class FirstAidHitObjective extends HitEntityObjective
{
	public FirstAidHitObjective(String title, int count, EntityType type)
	{
		super(title, count, type);
	}

	@Override
	public boolean checkHit(PlayerEntity player, LivingEntity target, DamageSource source)
	{
		IAbilityData props = AbilityDataCapability.get(player);
		FirstAidAbility ability = props.getEquippedAbility(FirstAidAbility.INSTANCE);
		boolean hasAbility = ability != null && ability.isContinuous();
		
		boolean hasHealNeed = target.getHealth() < target.getMaxHealth();
		boolean hasEmptyHand = player.getHeldItem(player.getActiveHand()).isEmpty();
		
		return super.checkHit(player, target, source) && hasHealNeed && hasEmptyHand && hasAbility;
	}
	
	@Override
	public String getLocalizedTitle() 
	{
		String objectiveKey = new TranslationTextComponent(String.format("quest.objective." + APIConfig.PROJECT_ID + ".%s", this.getId())).getKey();
		return new TranslationTextComponent(objectiveKey, (int)this.getMaxProgress(), this.target.getName().getFormattedText()).getFormattedText(); 
	}
}
