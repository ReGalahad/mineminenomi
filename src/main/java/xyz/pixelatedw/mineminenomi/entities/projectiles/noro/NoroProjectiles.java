package xyz.pixelatedw.mineminenomi.entities.projectiles.noro;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityType;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.projectiles.AbilityProjectileEntity.Data;
import xyz.pixelatedw.mineminenomi.api.abilities.renderers.AbilityProjectileRenderer;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.NoroNoroBeamModel;

public class NoroProjectiles 
{

	public static List<AbilityProjectileEntity.Data> projectiles = new ArrayList<AbilityProjectileEntity.Data>();
	
	public static final EntityType NORO_NORO_BEAM = WyRegistry.registerEntityType("noro_noro_beam", NoroNoroBeamProjectile::new, 1.5F, 1.5F);
	
	private static final AbilityProjectileRenderer.Factory NORO_NORO_BEAM_FACTORY = new AbilityProjectileRenderer.Factory(new NoroNoroBeamModel()).setTexture("noronorobeam").setScale(5);

	static
	{
		projectiles.add(new Data(NORO_NORO_BEAM, NoroNoroBeamProjectile.class, NORO_NORO_BEAM_FACTORY));
	}
}
