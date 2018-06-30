package xyz.pixelatedw.MineMineNoMi3.abilities;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import scala.reflect.api.Internals.ReificationSupportApi.SyntacitcSingletonTypeExtractor;
import scala.tools.nsc.backend.icode.BasicBlocks.BasicBlock.SuccessorList;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.AbilityProjectile;
import xyz.pixelatedw.MineMineNoMi3.api.math.WyMathHelper;
import xyz.pixelatedw.MineMineNoMi3.entities.abilityprojectiles.YamiProjectiles;
import xyz.pixelatedw.MineMineNoMi3.lists.ListAttributes;
import xyz.pixelatedw.MineMineNoMi3.lists.ListExtraAttributes;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;

public class YamiAbilities 
{

	public static Ability[] abilitiesArray = new Ability[] {new DarkMatter(), new Liberation(), new BlackHole()};	
	
	public static class BlackHole extends Ability
	{
		public BlackHole() 
		{
			super(ListAttributes.BLACKHOLE); 
		}
		
		public void use(final EntityPlayer player)
		{				
			if(!this.isOnCooldown)
			{
				for(int i = -7; i < 7; i++)
				for(int j = -5; j < 5; j++)
				for(int k = -7; k < 7; k++)
				{
					if(!player.worldObj.isAirBlock((int) player.posX + i, (int) player.posY + j, (int) player.posZ + k) && player.worldObj.getBlock((int) player.posX + i, (int) player.posY + j, (int) player.posZ + k) != ListMisc.Ope
							&& player.worldObj.getBlock((int) player.posX + i, (int) player.posY + j, (int) player.posZ + k) != ListMisc.OpeMid && player.worldObj.getBlock((int) player.posX + i, (int) player.posY + j, (int) player.posZ + k) != Blocks.bedrock)
						player.worldObj.setBlock((int) player.posX + i, (int) player.posY + j, (int) player.posZ + k, ListMisc.Darkness);
				}
				super.use(player);
			}		
		}
	}
	
	public static class Liberation extends Ability
	{
		public int liberationCount = 0;
		
		public Liberation() 
		{
			super(ListAttributes.LIBERATION); 
		}
		
		public void use(final EntityPlayer player)
		{				
			int libCount = 0;
			
			if(!this.isOnCooldown)
			{
				if(liberationCount > 0)
				{
					MovingObjectPosition mop = WyHelper.rayTraceBlocks(player);	
					
					if(mop != null)
					{
						double x = mop.blockX;
						double y = mop.blockY;
						double z = mop.blockZ;

						while(liberationCount > 0)
						{
							AbilityProjectile proj = new YamiProjectiles.Liberation(player.worldObj, player, ListExtraAttributes.LIBERATIONBLOCK);	
							proj.setLocationAndAngles(x + WyMathHelper.randomWithRange(-3, 3), (y + 14) + WyMathHelper.randomWithRange(0, 4), z + WyMathHelper.randomWithRange(-3, 3), 0, 0);
							proj.motionX = 0;
							proj.motionZ = 0;
							proj.motionY = -0.7 - player.worldObj.rand.nextDouble();
							player.worldObj.spawnEntityInWorld(proj);
							liberationCount--;	
						}					
					}					
				}
				else
				{
					for(int x = -20; x < 20; x++)
					for(int y = -20; y < 20; y++)
					for(int z = -20; z < 20; z++)
					{
						if( player.worldObj.getBlock((int) player.posX + x, (int) player.posY + y, (int) player.posZ + z) == ListMisc.Darkness)
						{
							player.worldObj.setBlockToAir((int)(int) player.posX + x, (int)(int) player.posY + y, (int)(int) player.posZ + z);
							libCount++;
							if(libCount % 10 == 0)
								liberationCount++;
						}
					}
				}
				
				super.use(player);
			}		
		}
	}
	
	public static class Kurouzu extends Ability
	{
		public Kurouzu() 
		{
			super(ListAttributes.KUROUZU); 
		}
		
		public void use(final EntityPlayer player)
		{				
			
			//super.use(player);
		}
	}
	
	public static class DarkMatter extends Ability
	{
		public DarkMatter() 
		{
			super(ListAttributes.DARKMATTER);
		}
		
		public void use(final EntityPlayer player)
		{				
			this.projectile = new YamiProjectiles.DarkMatter(player.worldObj, player, attr);
			super.use(player);
		}
	}
	
}
