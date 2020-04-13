package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.Effect;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.effects.BubblyCoralEffect;
import xyz.pixelatedw.mineminenomi.effects.CandleLockEffect;
import xyz.pixelatedw.mineminenomi.effects.ChiyuHormoneEffect;
import xyz.pixelatedw.mineminenomi.effects.DrunkEffect;
import xyz.pixelatedw.mineminenomi.effects.GanmenSeichoHormoneEffect;
import xyz.pixelatedw.mineminenomi.effects.LovestruckEffect;
import xyz.pixelatedw.mineminenomi.effects.NegativeEffect;
import xyz.pixelatedw.mineminenomi.effects.TensionHormoneEffect;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyRegistry;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEffects
{
	public static final Effect BUBBLY_CORAL = new BubblyCoralEffect();
	public static final Effect CANDLE_LOCK = new CandleLockEffect()
		.addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160890", -100, AttributeModifier.Operation.MULTIPLY_TOTAL);
	public static final Effect LOVESTRUCK = new LovestruckEffect()
		.addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160890", -100, AttributeModifier.Operation.MULTIPLY_TOTAL)
		.addAttributesModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, "7d355019-7ef9-4beb-bcba-8b2608a73380", 100, AttributeModifier.Operation.ADDITION);
	public static final Effect NEGATIVE = new NegativeEffect()
		.addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160890", -0.01, AttributeModifier.Operation.ADDITION)
		.addAttributesModifier(SharedMonsterAttributes.ATTACK_SPEED, "7d355019-7ef9-4beb-bcba-8b2608a73380", -1.5, AttributeModifier.Operation.MULTIPLY_TOTAL);
	public static final Effect CHIYU_HORMONE = new ChiyuHormoneEffect();
	public static final Effect TENSION_HORMONE = new TensionHormoneEffect()
		.addAttributesModifier(SharedMonsterAttributes.ATTACK_DAMAGE, "646707c2-479e-40fc-8df2-622582948a9d", 2, AttributeModifier.Operation.MULTIPLY_TOTAL)
		.addAttributesModifier(SharedMonsterAttributes.KNOCKBACK_RESISTANCE, "7d355019-7ef9-4beb-bcba-8b2608a73380", 2, AttributeModifier.Operation.MULTIPLY_TOTAL);
	public static final Effect GANMEN_SEICHO_HORMONE = new GanmenSeichoHormoneEffect()
		.addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160890", -0.01, AttributeModifier.Operation.ADDITION);
	public static final Effect DRUNK = new DrunkEffect()
		.addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "7107DE5E-7CE8-4030-940E-514C1F160890", -0.005, AttributeModifier.Operation.ADDITION);

	static
	{
		WyRegistry.registerEffect(BUBBLY_CORAL, "Bubbly Coral");
		WyRegistry.registerEffect(CANDLE_LOCK, "Candle Lock");
		WyRegistry.registerEffect(LOVESTRUCK, "Lovestruck");
		WyRegistry.registerEffect(NEGATIVE, "Negative");
		WyRegistry.registerEffect(CHIYU_HORMONE, "Chiyu Hormone");
		WyRegistry.registerEffect(TENSION_HORMONE, "Tension Hormone");
		WyRegistry.registerEffect(GANMEN_SEICHO_HORMONE, "Genmen Seicho Hormone");
		WyRegistry.registerEffect(DRUNK, "Drunk");
	}
}