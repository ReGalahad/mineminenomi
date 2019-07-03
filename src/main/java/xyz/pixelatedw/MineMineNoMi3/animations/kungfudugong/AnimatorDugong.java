package xyz.pixelatedw.MineMineNoMi3.animations.kungfudugong;

import java.util.HashMap;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import xyz.pixelatedw.MineMineNoMi3.animations.AnimatorBase;
import xyz.pixelatedw.MineMineNoMi3.animations.IAnimation;

public class AnimatorDugong extends AnimatorBase
{
	IAnimation animationHappy = new AnimationDugongHappy();
	
	
	@Override
	public HashMap<String, IAnimation> getAnimations()
	{
		HashMap<String, IAnimation> components = new HashMap<String, IAnimation>();
		
		components.put("happy", animationHappy);
		
		return components;
	}
	
	@Override
	public void runAnimator(Entity entity, ModelBase model)
	{
		//this.getAnimation("happy").runAnimation(model);
	}
}
