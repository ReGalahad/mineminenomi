package xyz.pixelatedw.mineminenomi.screens.extra;

import java.awt.Color;
import java.util.List;

import net.minecraft.client.gui.screen.Screen;
import xyz.pixelatedw.wypi.WyHelper;

public class SequencedString
{

	public String string;
	public int posX;
	public int posY;
	public int maxLength;
	public int color = Color.WHITE.getRGB();
	public char[] chars;
	public int maxTicks;
	public int ticksExisted;
	public Screen parent;
	public int delayTicks = this.maxTicks;

	public SequencedString(String str, Screen parent, int posX, int posY, int maxLength, int maxTicks)
	{

		this.parent = parent;
		this.string = str;
		this.posX = posX;
		this.posY = posY;
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

	public void render()
	{
		String tempStr = "";
		for (int i = 0; i < this.chars.length; i++)
		{
			if (this.ticksExisted >= this.calculateTicksNeeded(i) && this.ticksExisted < this.delayTicks)
			{
				tempStr = tempStr + this.chars[i];
			}
		}
		List<String> strings = WyHelper.splitString(parent.getMinecraft().fontRenderer, tempStr, this.posX, 0, this.maxLength);
		for (int b = 0; b < strings.size(); b++)
		{
			this.parent.drawString(this.parent.getMinecraft().fontRenderer, strings.get(b), this.posX, this.posY + 10 * b, this.color);
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
