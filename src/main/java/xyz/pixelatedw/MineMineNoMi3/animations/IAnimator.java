package xyz.pixelatedw.MineMineNoMi3.animations;

import java.util.HashMap;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import xyz.pixelatedw.MineMineNoMi3.api.debug.WyDebug;

public interface IAnimator
{

	void runAnimator(Entity entity, ModelBase model);
	HashMap<String, IAnimation> getAnimations();

	default IAnimation getAnimation(String name)
	{
		if(this.getAnimations().containsKey(name))
			return this.getAnimations().get(name);
		
		WyDebug.warn("Animation named " + name + " for Animator " + this.getClass() + " does not exist!");
		return null;
	}
	
}
