package xyz.pixelatedw.MineMineNoMi3.models;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import xyz.pixelatedw.MineMineNoMi3.animations.IAnimator;

public class AnimatedModel extends ModelBiped
{
	protected long lastTime = System.nanoTime();
	protected final double ticks = 60D;
	protected double ns = 1e9 / ticks;
	protected double frame = 0;
	
	private IAnimator animator;
	
	public AnimatedModel(IAnimator animator)
	{
		this.animator = animator;
	}
	
	public IAnimator getAnimator()
	{
		return this.animator;
	}
	
	public void runAnimator(Entity entity, ModelBase model)
	{
		if(Minecraft.getMinecraft().isGamePaused())
			return;
		
		long now = System.nanoTime();
		double delta = (now - this.lastTime) / ns;
		this.lastTime = now;
		if (delta >= 1)
		{
			this.animator.runAnimator(entity, model);		
			delta--;
		}
	}
}
