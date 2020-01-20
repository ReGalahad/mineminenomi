package xyz.pixelatedw.mineminenomi.entities.projectiles;

import java.util.HashMap;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttribute;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectileEntity.Data;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModExtraAttributes;

public class ExtraProjectiles 
{
	public static HashMap<AbilityAttribute, AbilityProjectileEntity.Data> projectiles = new HashMap<AbilityAttribute, AbilityProjectileEntity.Data>();
	
	public static final EntityType NORMAL_BULLET = WyRegistry.registerEntityType("normal_bullet", NormalBullet::new);
	public static final EntityType KAIROSEKI_BULLET = WyRegistry.registerEntityType("kairoseki_bullet", KairosekiBullet::new);
	public static final EntityType AXE_DIAL_PROJECTILE = WyRegistry.registerEntityType("axe_dial_projectile", AxeDialProjectile::new);
	public static final EntityType MILKY_DIAL_PROJECTILE = WyRegistry.registerEntityType("milky_dial_projectile", MilkyDialProjectile::new);
	public static final EntityType POP_GREEN = WyRegistry.registerEntityType("pop_green", PopGreen::new);
	public static final EntityType KUJA_ARROW = WyRegistry.registerEntityType("kuja_arrow", KujaArrow::new);
	public static final EntityType CLOUD = WyRegistry.registerEntityType("cloud", EntityCloud::new);

	static
	{
		projectiles.put(ModExtraAttributes.NORMAL_BULLET, new Data(NORMAL_BULLET, NormalBullet.class));
		projectiles.put(ModExtraAttributes.KAIROSEKI_BULLET, new Data(KAIROSEKI_BULLET, KairosekiBullet.class));
		projectiles.put(ModExtraAttributes.DIAL_AXE, new Data(AXE_DIAL_PROJECTILE, AxeDialProjectile.class));
		projectiles.put(ModExtraAttributes.DIAL_MILKY, new Data(MILKY_DIAL_PROJECTILE, MilkyDialProjectile.class));
		projectiles.put(ModExtraAttributes.POP_GREEN, new Data(POP_GREEN, PopGreen.class));
		projectiles.put(ModExtraAttributes.KUJA_ARROW, new Data(KUJA_ARROW, KujaArrow.class));		
	}
	
	public static class EntityCloud extends Entity
	{
		private int life = 100;
		private PlayerEntity thrower;
		
		public EntityCloud(World world)
		{
			super(CLOUD, world);
		}
		
		public EntityCloud(EntityType type, World world)
		{super(type, world);}
		
		@Override
		public void tick()
		{
			super.tick();
			if(!this.world.isRemote)
			{
				if(life <= 0)
					this.remove();

				life--;
			}		
		}
		
		public PlayerEntity getThrower()
		{
			return this.thrower;
		}
		
		public void setThrower(PlayerEntity player)
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

		@Override
		protected void registerData() {}
		@Override
		protected void readAdditional(CompoundNBT compound) {}
		@Override
		protected void writeAdditional(CompoundNBT compound) {}
		@Override
		public IPacket<?> createSpawnPacket() { return NetworkHooks.getEntitySpawningPacket(this); }
	}
	
	public static class MilkyDialProjectile extends AbilityProjectileEntity
	{
		public MilkyDialProjectile(World world)
		{super(MILKY_DIAL_PROJECTILE, world);}
		
		public MilkyDialProjectile(EntityType type, World world)
		{super(type, world);}
		
		public MilkyDialProjectile(World world, double x, double y, double z)
		{super(MILKY_DIAL_PROJECTILE, world, x, y, z);}
		
		public MilkyDialProjectile(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(MILKY_DIAL_PROJECTILE, world, player, attr);		
		}
		
		@Override
		public void tick()
		{
			WyHelper.placeBlockIfAllowed(this.world, (int)this.posX, (int)this.posY - 1, (int)this.posZ, ModBlocks.SKY_BLOCK, "air");
			WyHelper.placeBlockIfAllowed(this.world, (int)this.posX + 1, (int)this.posY - 1, (int)this.posZ, ModBlocks.SKY_BLOCK, "air");
			WyHelper.placeBlockIfAllowed(this.world, (int)this.posX - 1, (int)this.posY - 1, (int)this.posZ, ModBlocks.SKY_BLOCK, "air");
			WyHelper.placeBlockIfAllowed(this.world, (int)this.posX + 1, (int)this.posY - 1, (int)this.posZ + 1, ModBlocks.SKY_BLOCK, "air");
			WyHelper.placeBlockIfAllowed(this.world, (int)this.posX + 1, (int)this.posY - 1, (int)this.posZ - 1, ModBlocks.SKY_BLOCK, "air");
			WyHelper.placeBlockIfAllowed(this.world, (int)this.posX - 1, (int)this.posY - 1, (int)this.posZ + 1, ModBlocks.SKY_BLOCK, "air");
			WyHelper.placeBlockIfAllowed(this.world, (int)this.posX, (int)this.posY - 1, (int)this.posZ + 1, ModBlocks.SKY_BLOCK, "air");
			WyHelper.placeBlockIfAllowed(this.world, (int)this.posX, (int)this.posY - 1, (int)this.posZ - 1, ModBlocks.SKY_BLOCK, "air");
			WyHelper.placeBlockIfAllowed(this.world, (int)this.posX - 1, (int)this.posY - 1, (int)this.posZ - 1, ModBlocks.SKY_BLOCK, "air");
			WyHelper.placeBlockIfAllowed(this.world, (int)this.posX, (int)this.posY - 2, (int)this.posZ, ModBlocks.SKY_BLOCK, "air");
			
			super.tick();
		}
	}	
	
	public static class AxeDialProjectile extends AbilityProjectileEntity
	{
		public AxeDialProjectile(World world)
		{super(AXE_DIAL_PROJECTILE, world);}
		
		public AxeDialProjectile(EntityType type, World world)
		{super(type, world);}
		
		public AxeDialProjectile(World world, double x, double y, double z)
		{super(AXE_DIAL_PROJECTILE, world, x, y, z);}
		
		public AxeDialProjectile(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(AXE_DIAL_PROJECTILE, world, player, attr);		
		}
		
		@Override
		public void tasksImapct(RayTraceResult hit)
		{
			WyHelper.doExplosion(this.getThrower(), this.posX, this.posY, this.posZ, 4);
		}	
	}	
	
	public static class PopGreen extends AbilityProjectileEntity
	{
		public PopGreen(World world)
		{super(POP_GREEN, world);}
		
		public PopGreen(EntityType type, World world)
		{super(type, world);}
		
		public PopGreen(World world, double x, double y, double z)
		{super(POP_GREEN, world, x, y, z);}
		
		public PopGreen(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(POP_GREEN, world, player, attr);		
		}
	}	
	
	public static class KujaArrow extends AbilityProjectileEntity
	{
		public KujaArrow(World world)
		{super(KUJA_ARROW, world);}
		
		public KujaArrow(EntityType type, World world)
		{super(type, world);}
		
		public KujaArrow(World world, double x, double y, double z)
		{super(KUJA_ARROW, world, x, y, z);}
		
		public KujaArrow(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(KUJA_ARROW, world, player, attr);		
		}
	}	
	
	public static class NormalBullet extends AbilityProjectileEntity
	{
		public NormalBullet(World world)
		{super(NORMAL_BULLET, world);}
		
		public NormalBullet(EntityType type, World world)
		{super(type, world);}
		
		public NormalBullet(World world, double x, double y, double z)
		{super(NORMAL_BULLET, world, x, y, z);}
		
		public NormalBullet(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(NORMAL_BULLET, world, player, attr);		
		}
	}	
	
	public static class KairosekiBullet extends AbilityProjectileEntity
	{
		public KairosekiBullet(World world)
		{super(KAIROSEKI_BULLET, world);}
		
		public KairosekiBullet(EntityType type, World world)
		{super(type, world);}
		
		public KairosekiBullet(World world, double x, double y, double z)
		{super(KAIROSEKI_BULLET, world, x, y, z);}
		
		public KairosekiBullet(World world, LivingEntity player, AbilityAttribute attr) 
		{		
			super(KAIROSEKI_BULLET, world, player, attr);		
		}
	}	
}
