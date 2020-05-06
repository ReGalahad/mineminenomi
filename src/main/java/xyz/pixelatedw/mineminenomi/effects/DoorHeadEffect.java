package xyz.pixelatedw.mineminenomi.effects;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import xyz.pixelatedw.wypi.WyHelper;

public class DoorHeadEffect extends Effect
{
	public DoorHeadEffect()
	{
		super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
	}
}
