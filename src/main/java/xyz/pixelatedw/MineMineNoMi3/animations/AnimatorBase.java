package xyz.pixelatedw.MineMineNoMi3.animations;

import java.util.HashMap;

public abstract class AnimatorBase implements IAnimator
{	
	private IAnimation animationToRun;
	
	@Override
	public abstract HashMap<String, IAnimation> getAnimations();

	public void startAnimation(IAnimation anim)
	{
		if(this.animationToRun == null)
			this.animationToRun = anim;
	}
}
