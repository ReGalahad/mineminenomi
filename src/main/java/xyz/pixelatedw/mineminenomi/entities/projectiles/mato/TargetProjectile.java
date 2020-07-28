package xyz.pixelatedw.mineminenomi.entities.projectiles.mato;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class TargetProjectile extends AbilityProjectileEntity
{
    public TargetProjectile(World world)
    {
        super(MatoProjectiles.TARGET_PROJECTILE, world);
    }
    public TargetProjectile(EntityType entity, World world)
    {
        super(entity, world);
    }
    public TargetProjectile(World world, double x, double y, double z)
    {
        super(MatoProjectiles.TARGET_PROJECTILE, world, x, y, z);
    }
    public TargetProjectile(World world, LivingEntity p)
    {
        super(MatoProjectiles.TARGET_PROJECTILE, world, p);
        this.setMaxLife(128);
    }

}
