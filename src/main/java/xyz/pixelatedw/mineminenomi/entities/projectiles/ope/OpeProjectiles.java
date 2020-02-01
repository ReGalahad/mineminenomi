package xyz.pixelatedw.mineminenomi.entities.projectiles.ope;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityType;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.models.CubeModel;
import xyz.pixelatedw.mineminenomi.api.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.projectiles.AbilityProjectileEntity.Data;
import xyz.pixelatedw.mineminenomi.api.abilities.renderers.AbilityProjectileRenderer;

public class OpeProjectiles 
{
	public static List<AbilityProjectileEntity.Data> projectiles = new ArrayList<AbilityProjectileEntity.Data>();
	
	public static final EntityType GAMMA_KNIFE = WyRegistry.registerEntityType("gamma_knife", GammaKnifeProjectile::new, 0.5F, 0.5F);
	
	private static final AbilityProjectileRenderer.Factory GAMMA_KNIFE_FACTORY = new AbilityProjectileRenderer.Factory(new CubeModel()).setColor("#00AB66").setScale(1, 1, 5);

	static
	{
		projectiles.add(new Data(GAMMA_KNIFE, GammaKnifeProjectile.class, GAMMA_KNIFE_FACTORY));
	}	
}
