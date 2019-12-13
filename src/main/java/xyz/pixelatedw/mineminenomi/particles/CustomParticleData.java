package xyz.pixelatedw.mineminenomi.particles;

import net.minecraft.util.ResourceLocation;

public class CustomParticleData
{

	private ResourceLocation texture;
	private double posX, posY, posZ, motionX, motionY, motionZ;
	private float gravity, scale;
	private int maxAge;
	private float redColor = 1, greenColor = 1, blueColor = 1;
	public boolean hasCustomScale, hasCustomGravity, hasCustomMaxAge;
	
	public float getBlueColor()
	{
		return this.blueColor;
	}
	
	public float getGreenColor()
	{
		return this.greenColor;
	}
	
	public float getRedColor()
	{
		return this.redColor;
	}
	
	public void setColor(float red, float green, float blue)
	{
		this.redColor = red;
		this.greenColor = green;
		this.blueColor = blue;
	}
	
	public boolean hasCustomScale()
	{
		return hasCustomScale;
	}

	public boolean hasCustomGravity()
	{
		return hasCustomGravity;
	}

	public boolean hasCustomMaxAge()
	{
		return hasCustomMaxAge;
	}

	public ResourceLocation getTexture()
	{
		return texture;
	}

	public void setTexture(ResourceLocation texture)
	{
		this.texture = texture;
	}

	public double getPosX()
	{
		return posX;
	}

	public void setPosX(double posX)
	{
		this.posX = posX;
	}

	public double getPosY()
	{
		return posY;
	}

	public void setPosY(double posY)
	{
		this.posY = posY;
	}

	public double getPosZ()
	{
		return posZ;
	}

	public void setPosZ(double posZ)
	{
		this.posZ = posZ;
	}

	public double getMotionX()
	{
		return motionX;
	}

	public void setMotionX(double motionX)
	{
		this.motionX = motionX;
	}

	public double getMotionY()
	{
		return motionY;
	}

	public void setMotionY(double motionY)
	{
		this.motionY = motionY;
	}

	public double getMotionZ()
	{
		return motionZ;
	}

	public void setMotionZ(double motionZ)
	{
		this.motionZ = motionZ;
	}

	public float getGravity()
	{
		return gravity;
	}

	public void setGravity(float gravity)
	{
		this.hasCustomGravity = true;
		this.gravity = gravity;
	}

	public float getScale()
	{
		return scale;
	}

	public void setScale(float scale)
	{
		this.hasCustomScale = true;
		this.scale = scale;
	}

	public int getMaxAge()
	{
		return maxAge;
	}

	public void setMaxAge(int maxAge)
	{
		this.hasCustomMaxAge = true;
		this.maxAge = maxAge;
	}
}
