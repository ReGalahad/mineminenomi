package xyz.pixelatedw.mineminenomi.entities.projectiles.gomu;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class GomuGomuNoRocketProjectile extends AbilityProjectileEntity
{
	public GomuGomuNoRocketProjectile(World world)
	{
		super(GomuProjectiles.GOMU_GOMU_NO_PISTOL, world);
	}

	public GomuGomuNoRocketProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public GomuGomuNoRocketProjectile(World world, double x, double y, double z)
	{
		super(GomuProjectiles.GOMU_GOMU_NO_PISTOL, world, x, y, z);
	}

	public GomuGomuNoRocketProjectile(World world, LivingEntity player)
	{
		super(GomuProjectiles.GOMU_GOMU_NO_PISTOL, world, player);

		this.setDamage(4);
		this.setPhysical();
		
		this.onBlockImpactEvent = this::onBlockImpactEvent;
	}
	
	private void onBlockImpactEvent(BlockPos hit)
	{
		PlayerEntity player = (PlayerEntity) this.getThrower();
		
		double powerX = Math.abs(hit.getX() - player.posX) / 3;
		double powerY = (hit.getY() - player.posY) / 4;
		double powerZ = Math.abs(hit.getZ() - player.posZ) / 3;
				
		Vec3d speed = WyHelper.propulsion(player, powerX, powerZ);
		player.setMotion(speed.x, powerY, speed.z);
		((ServerPlayerEntity)player).connection.sendPacket(new SEntityVelocityPacket(player));
	}
}
