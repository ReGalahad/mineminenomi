package xyz.pixelatedw.mineminenomi.entities.projectiles.bane;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityType;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.projectiles.AbilityProjectileEntity.Data;
import xyz.pixelatedw.mineminenomi.api.abilities.renderers.RendererAbility;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.FistModel;

public class BaneProjectiles 
{
	public static List<AbilityProjectileEntity.Data> projectiles = new ArrayList<AbilityProjectileEntity.Data>();
	
	public static final EntityType SPRING_DEATH_KNOCK = WyRegistry.registerEntityType("spring_death_knock", SpringDeathKnockProjectile::new);
	
	private static final RendererAbility.Factory SPRING_DEATH_KNOCK_FACTORY = new RendererAbility.Factory(new FistModel()).setTexture("springdeathknock").setScale(7, 5, 5);

	static
	{
		projectiles.add(new Data(SPRING_DEATH_KNOCK, SpringDeathKnockProjectile.class, SPRING_DEATH_KNOCK_FACTORY));
	}	
}
