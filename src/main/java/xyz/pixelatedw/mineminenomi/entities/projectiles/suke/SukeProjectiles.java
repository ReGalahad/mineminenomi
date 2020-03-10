package xyz.pixelatedw.mineminenomi.entities.projectiles.suke;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityType;
import xyz.pixelatedw.wypi.WyRegistry;
import xyz.pixelatedw.wypi.abilities.models.SphereModel;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity.Data;
import xyz.pixelatedw.wypi.abilities.renderers.AbilityProjectileRenderer;

public class SukeProjectiles 
{

	public static List<AbilityProjectileEntity.Data> projectiles = new ArrayList<AbilityProjectileEntity.Data>();

	public static final EntityType SHISHA_NO_TE = WyRegistry.registerEntityType("shisha_no_te", ShishaNoTeProjectile::new, 0.5F, 0.5F);
	
	private static final AbilityProjectileRenderer.Factory SHISHA_NO_TE_FACTORY = new AbilityProjectileRenderer.Factory(new SphereModel()).setScale(0);

	static
	{
		projectiles.add(new Data(SHISHA_NO_TE, ShishaNoTeProjectile.class, SHISHA_NO_TE_FACTORY));
	}
}
