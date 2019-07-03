package xyz.pixelatedw.MineMineNoMi3.animations.kungfudugong;

import net.minecraft.client.model.ModelBase;
import xyz.pixelatedw.MineMineNoMi3.animations.IAnimation;
import xyz.pixelatedw.MineMineNoMi3.api.math.WyMathHelper;
import xyz.pixelatedw.MineMineNoMi3.models.entities.mobs.animals.ModelKungFuDugong;

public class AnimationDugongHappy implements IAnimation
{
	private double frame = 0;

	public void runAnimation(ModelKungFuDugong model)
	{
		double[] animationHappyTail = new double[]
			{
					35, 30, 25, 20, 15, 10, 5, 0, -5, -10, -15, -20, -15, -10, -5, 0, 5, 10, 15, 20, 25, 30, 35
			};

		this.frame += 1;
		if (((int) frame) < animationHappyTail.length)
		{
			model.tail2.rotateAngleX = WyMathHelper.degToRad(animationHappyTail[(int) this.frame]);
			model.tail3.rotateAngleX = WyMathHelper.degToRad(animationHappyTail[(int) this.frame]);
		}
		else
			this.frame = 0;
	}

	@Override
	public void runAnimation(ModelBase model)
	{
		this.runAnimation((ModelKungFuDugong) model);
	}

}
