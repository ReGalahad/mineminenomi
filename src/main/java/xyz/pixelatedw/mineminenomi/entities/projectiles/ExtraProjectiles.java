package xyz.pixelatedw.mineminenomi.entities.projectiles;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.AxeDialProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.KairosekiBulletProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.KujaArrowProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.MilkyDialProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.NormalBulletProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.PopGreenProjectile;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.ArrowModel;
import xyz.pixelatedw.wypi.WyRegistry;
import xyz.pixelatedw.wypi.abilities.models.CubeModel;
import xyz.pixelatedw.wypi.abilities.models.SphereModel;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity.Data;
import xyz.pixelatedw.wypi.abilities.renderers.AbilityProjectileRenderer;

public class ExtraProjectiles 
{
	public static List<AbilityProjectileEntity.Data> projectiles = new ArrayList<AbilityProjectileEntity.Data>();
	
	public static final EntityType NORMAL_BULLET = WyRegistry.registerEntityType("normal_bullet", NormalBulletProjectile::new, 0.3F, 0.3F);
	public static final EntityType KAIROSEKI_BULLET = WyRegistry.registerEntityType("kairoseki_bullet", KairosekiBulletProjectile::new, 0.3F, 0.3F);
	public static final EntityType AXE_DIAL_PROJECTILE = WyRegistry.registerEntityType("axe_dial_projectile", AxeDialProjectile::new, 0.3F, 0.3F);
	public static final EntityType MILKY_DIAL_PROJECTILE = WyRegistry.registerEntityType("milky_dial_projectile", MilkyDialProjectile::new, 0.3F, 0.3F);
	public static final EntityType POP_GREEN = WyRegistry.registerEntityType("pop_green", PopGreenProjectile::new, 0.3F, 0.3F);
	public static final EntityType KUJA_ARROW = WyRegistry.registerEntityType("kuja_arrow", KujaArrowProjectile::new, 0.5F, 0.5F);
	public static final EntityType CLOUD = WyRegistry.registerEntityType("cloud", EntityCloud::new);

	private static final AbilityProjectileRenderer.Factory NORMAL_BULLET_FACTORY = new AbilityProjectileRenderer.Factory(new SphereModel()).setScale(0.5).setColor("#878787");
	private static final AbilityProjectileRenderer.Factory KAIROSEKI_BULLET_FACTORY = new AbilityProjectileRenderer.Factory(new SphereModel()).setScale(0.5).setColor("#F3F3F3");
	private static final AbilityProjectileRenderer.Factory AXE_DIAL_PROJECTILE_FACTORY = new AbilityProjectileRenderer.Factory(new CubeModel()).setScale(6, 0.4, 1.5).setColor("#69E3FF");
	private static final AbilityProjectileRenderer.Factory MILKY_DIAL_PROJECTILE_FACTORY = new AbilityProjectileRenderer.Factory(new SphereModel()).setScale(.1).setColor("#69E3FF");
	private static final AbilityProjectileRenderer.Factory POP_GREEN_FACTORY = new AbilityProjectileRenderer.Factory(new SphereModel()).setScale(0.5).setColor("#7ccc6a");
	private static final AbilityProjectileRenderer.Factory KUJA_ARROW_FACTORY = new AbilityProjectileRenderer.Factory(new ArrowModel()).setTexture("kujaarrow").setScale(1.25);

	static
	{
		projectiles.add(new Data(NORMAL_BULLET, NormalBulletProjectile.class, NORMAL_BULLET_FACTORY));
		projectiles.add(new Data(KAIROSEKI_BULLET, KairosekiBulletProjectile.class, KAIROSEKI_BULLET_FACTORY));
		projectiles.add(new Data(AXE_DIAL_PROJECTILE, AxeDialProjectile.class, AXE_DIAL_PROJECTILE_FACTORY));
		projectiles.add(new Data(MILKY_DIAL_PROJECTILE, MilkyDialProjectile.class, MILKY_DIAL_PROJECTILE_FACTORY));
		projectiles.add(new Data(MILKY_DIAL_PROJECTILE, MilkyDialProjectile.class, MILKY_DIAL_PROJECTILE_FACTORY));
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
}
