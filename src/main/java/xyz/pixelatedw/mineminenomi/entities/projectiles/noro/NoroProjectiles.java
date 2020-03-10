package xyz.pixelatedw.mineminenomi.entities.projectiles.noro;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityType;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.NoroNoroBeamModel;
import xyz.pixelatedw.wypi.WyRegistry;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity.Data;
import xyz.pixelatedw.wypi.abilities.renderers.AbilityProjectileRenderer;

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
