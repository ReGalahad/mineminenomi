package xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import scala.actors.threadpool.Arrays;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityAttribute;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityProjectile;
import xyz.pixelatedw.MineMineNoMi3.api.math.WyMathHelper;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.lists.ListExtraAttributes;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketPlayer;

public class WeatherProjectiles
{
	public static ArrayList<Object[]> abilitiesClassesArray = new ArrayList();

	static
	{
		abilitiesClassesArray.add(new Object[] {HeatBall.class, ListAttributes.HEATBALL});
		abilitiesClassesArray.add(new Object[] {CoolBall.class, ListAttributes.COOLBALL});
		abilitiesClassesArray.add(new Object[] {ThunderBall.class, ListAttributes.THUNDERBALL});
		
		abilitiesClassesArray.add(new Object[] {EntityWeatherCloud.class, ListExtraAttributes.WEATHERCLOUD});
	}
	
	public static class ThunderBall extends WeatherBall
	{
		public ThunderBall(World world)
		{super(world);}
		
		public ThunderBall(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public ThunderBall(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);
			this.type = 3;
		}
	}
	
	public static class CoolBall extends WeatherBall
	{
		public CoolBall(World world)
		{super(world);}
		
		public CoolBall(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public CoolBall(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);
			this.type = 2;
		}
		
		public void onUpdate()
		{
			super.onUpdate();
			if(!this.worldObj.isRemote)
			{
				List weatherBallsNear = WyHelper.getEntitiesNear(this, 2, WeatherBall.class);
				if(weatherBallsNear.size() > 0 && this.ticksExisted > 100)
				{
					List<HeatBall> heatBalls = (List<HeatBall>) weatherBallsNear.stream().filter(x ->
					{
						WeatherBall ball = (WeatherBall)x;
						
						return ball.getType() == 1;
					}).collect(Collectors.toList());

					if(heatBalls.size() > 0)
					{
						EntityWeatherCloud weatherCloud = new EntityWeatherCloud(worldObj);
						weatherCloud.setThrower((EntityPlayer) this.getThrower());
						weatherCloud.setPositionAndRotation(this.posX, this.posY, this.posZ, 0, 0);
						weatherCloud.addWeatherBall(this);
						
						for(HeatBall hb : heatBalls)
						{
							weatherCloud.addWeatherBall(hb);
							hb.setDead();
						}
						
						this.worldObj.spawnEntityInWorld(weatherCloud);
						
						this.setDead();
					}
				}
			}
		}
	}
	
	public static class HeatBall extends WeatherBall
	{		
		public HeatBall(World world)
		{super(world);}
		
		public HeatBall(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public HeatBall(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
			this.type = 1;
		}
	}
	
	
	
	public static class WeatherBall extends AbilityProjectile
	{
		protected int type;
		
		public WeatherBall(World world)
		{super(world);}
		
		public WeatherBall(World world, double x, double y, double z)
		{super(world, x, y, z);}
		
		public WeatherBall(World world, EntityLivingBase player, AbilityAttribute attr) 
		{		
			super(world, player, attr);		
		}
		
		public void onUpdate()
		{
			super.onUpdate();

			this.motionX /= 1.5;
			this.motionZ /= 1.5;
			if(this.ticksExisted < 100)
				this.motionY = 0.25;
			else
				this.motionY = 0;
		}
		
		public int getType()
		{
			return type;
		}
	}
	
	public static class EntityWeatherCloud extends Entity
	{
		private int life = 300;
		private EntityPlayer thrower;
		private List<WeatherBall> weatherBalls = new ArrayList<WeatherBall>();
		private boolean charged = false;
		
		public EntityWeatherCloud(World world)
		{
			super(world);
		}
		
		public void onUpdate()
		{
			super.onUpdate();
			if(!this.worldObj.isRemote)
			{
				if(life <= 0)
					this.setDead();

				life--;
				
				if(this.charged)
				{
					List<EntityLivingBase> targets = WyHelper.getEntitiesNear(this, 30);
					
					System.out.println(Arrays.toString(targets.toArray()));
					System.out.println(Arrays.toString(WyMathHelper.shuffle(targets).toArray()));
					
					targets.remove(this.getThrower());
					
					for(EntityLivingBase entity : targets)
					{
						if(entity.posY <= this.posY)
						{
							if(this.ticksExisted % 50 == 0)
							{
								WyNetworkHelper.sendTo(new PacketPlayer("ElThorThunder", entity.posX, entity.posY, entity.posZ), (EntityPlayerMP) this.getThrower());
								EntityLightningBolt thunder = new EntityLightningBolt(worldObj, entity.posX, entity.posY, entity.posZ);
								
								this.worldObj.spawnEntityInWorld(thunder);
								break;
							}
						}
					}
				}
				else
				{			
					List weatherBallsNear = WyHelper.getEntitiesNear(this, 2, WeatherBall.class);
					if(weatherBallsNear.size() > 0 && this.weatherBalls.size() < 8)
					{
						List<HeatBall> heatBalls = (List<HeatBall>) weatherBallsNear.stream().filter(x ->
						{
							WeatherBall ball = (WeatherBall)x;
							
							return ball.getType() == 1;
						}).collect(Collectors.toList());
	
						List<CoolBall> coolBalls = (List<CoolBall>) weatherBallsNear.stream().filter(x ->
						{
							WeatherBall ball = (WeatherBall)x;
							
							return ball.getType() == 2;
						}).collect(Collectors.toList());
						
						List<ThunderBall> thunderBalls = (List<ThunderBall>) weatherBallsNear.stream().filter(x ->
						{
							WeatherBall ball = (WeatherBall)x;
							
							return ball.getType() == 3;
						}).collect(Collectors.toList());
						
						if(heatBalls.size() > 0)
						{
							for(HeatBall hb : heatBalls)
							{
								if(this.weatherBalls.get(this.weatherBalls.size() - 1) instanceof CoolBall)
								{
									this.addWeatherBall(hb);
									this.life += 50;
								}
								else
								{
									WyHelper.doExplosion(this, this.posX, this.posY, this.posZ, 10);
									this.setDead();
								}
								
								hb.setDead();
							}
						}
						
						if(coolBalls.size() > 0)
						{
							for(CoolBall cb : coolBalls)
							{
								if(this.weatherBalls.get(this.weatherBalls.size() - 1) instanceof HeatBall)
								{
									this.addWeatherBall(cb);
									this.life += 50;
								}
								else
								{
									WyHelper.doExplosion(this, this.posX, this.posY, this.posZ, 10);
									this.setDead();
								}
								
								cb.setDead();
							}
						}
						
						if(thunderBalls.size() > 0)
						{
							for(ThunderBall tb : thunderBalls)
							{		
								this.charged = true;
								tb.setDead();
							}
						}
					}
					else if(this.weatherBalls.size() >= 8)
					{
						
					}
				}
			}	
		}
		
		public void addWeatherBall(WeatherBall ball)
		{
			this.weatherBalls.add(ball);
		}
		
		public EntityPlayer getThrower()
		{
			return this.thrower;
		}
		
		public void setThrower(EntityPlayer player)
		{
			this.thrower = player;
		}
		
		public int getLife()
		{
			return this.life;
		}
		
		public void setLife(int life)
		{
			this.life = life;
		}
		
		protected void entityInit() {}
		protected void readEntityFromNBT(NBTTagCompound nbt) {}
		protected void writeEntityToNBT(NBTTagCompound nbt) {}
	}	
}
