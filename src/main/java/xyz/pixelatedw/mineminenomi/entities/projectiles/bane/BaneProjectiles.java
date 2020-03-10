package xyz.pixelatedw.mineminenomi.entities.projectiles.bane;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityType;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.FistModel;
import xyz.pixelatedw.wypi.WyRegistry;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity.Data;
import xyz.pixelatedw.wypi.abilities.renderers.AbilityProjectileRenderer;

public class BaneProjectiles 
{
	public static List<AbilityProjectileEntity.Data> projectiles = new ArrayList<AbilityProjectileEntity.Data>();
	
	public static final EntityType SPRING_DEATH_KNOCK = WyRegistry.registerEntityType("spring_death_knock", SpringDeathKnockProjectile::new, 1.5F, 1.5F);
	
	private static final AbilityProjectileRenderer.Factory SPRING_DEATH_KNOCK_FACTORY = new AbilityProjectileRenderer.Factory(new FistModel()).setTexture("springdeathknock").setScale(5, 5, 7).setOffset(0.15, -0.5, 0);

	static
	{
		projectiles.add(new Data(SPRING_DEATH_KNOCK, SpringDeathKnockProjectile.class, SPRING_DEATH_KNOCK_FACTORY));
	}	
}
