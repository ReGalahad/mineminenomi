package xyz.pixelatedw.mineminenomi.screens.extra;

import java.awt.Color;
import java.util.List;

import net.minecraft.client.Minecraft;
import xyz.pixelatedw.wypi.WyHelper;

public class SequencedString
{
	public String string;
	public int maxLength;
	public int color = Color.WHITE.getRGB();
	public char[] chars;
	public int maxTicks;
	public int ticksExisted;
	public Minecraft mc;
	public int delayTicks = this.maxTicks;

	public SequencedString(String str, int maxLength, int maxTicks)
	{
		this.mc = Minecraft.getInstance();
		this.string = str;
		this.maxLength = maxLength;
		this.chars = new char[this.string.length()];
		for (int i = 0; i < this.string.length(); i++)
		{
			this.chars[i] = this.string.charAt(i);
		}
		this.maxTicks = maxTicks;
		this.ticksExisted = 0;
		this.delayTicks = this.maxTicks + 5 * 20;
	}

	public void render(int posX, int posY)
	{
		String tempStr = "";
		for (int i = 0; i < this.chars.length; i++)
		{
			if (this.ticksExisted >= this.calculateTicksNeeded(i) && this.ticksExisted < this.delayTicks)
			{
				tempStr = tempStr + this.chars[i];
			}
		}
		List<String> strings = WyHelper.splitString(this.mc.fontRenderer, tempStr, posX, this.maxLength);
		for (int b = 0; b < strings.size(); b++)
		{
			WyHelper.drawStringWithBorder(this.mc.fontRenderer, strings.get(b), posX, posY + 10 * b, this.color);
		}

		this.ticksExisted++;
	}

	public int calculateTicksNeeded(int index)
	{
		int oldRange = this.string.length();
		int newRange = this.maxTicks;
		int newValue = (((index) * newRange) / oldRange);

		return newValue;
	}
}
