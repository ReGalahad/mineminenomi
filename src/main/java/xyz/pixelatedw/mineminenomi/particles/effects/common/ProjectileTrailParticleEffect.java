package xyz.pixelatedw.mineminenomi.particles.effects.common;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.particles.SimpleParticle;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.wypi.WyHelper;

@Deprecated
public class ProjectileTrailParticleEffect extends ParticleEffect
{
	private ResourceLocation particleTexture;
	private int age;
	private int density;
	private float scale;

	public ProjectileTrailParticleEffect(ResourceLocation texture)
	{
		this.particleTexture = texture;
		this.age = 5;
		this.scale = 1;
		this.density = 20;
	}
	
	public ProjectileTrailParticleEffect(ResourceLocation texture, int age, float scale, int density)
	{
		this.particleTexture = texture;
		this.age = age;
		this.scale = scale;
		this.density = density;
	}
	
	@Override
	public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{	
		for (int i = 0; i < this.density; i++)
		{
			posX +=  WyHelper.randomDouble() / 3;
			posY +=  WyHelper.randomDouble() / 3;
			posZ +=  WyHelper.randomDouble() / 3;
			
			SimpleParticle cp = new SimpleParticle(world, this.particleTexture,
					posX, 
					posY,
					posZ, 
					0,
					0,
					0)
					.setParticleAge(this.age).setParticleScale(this.scale);
			
			Minecraft.getInstance().particles.addEffect(cp);
		}
	}
	
}
