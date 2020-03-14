package xyz.pixelatedw.mineminenomi.entities.projectiles.pika;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityType;
import xyz.pixelatedw.wypi.WyRegistry;
import xyz.pixelatedw.wypi.abilities.models.CubeModel;
import xyz.pixelatedw.wypi.abilities.models.SphereModel;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity.Data;
import xyz.pixelatedw.wypi.abilities.renderers.AbilityProjectileRenderer;

public class PikaProjectiles
{
	public static List<AbilityProjectileEntity.Data> projectiles = new ArrayList<AbilityProjectileEntity.Data>();

	public static final EntityType AMATERASU = WyRegistry.registerEntityType("amaterasu", AmaterasuProjectile::new, 0.5F, 0.5F);
	public static final EntityType YASAKANI_NO_MAGATAMA = WyRegistry.registerEntityType("yasakani_no_magatama", YasakaniNoMagatamaProjectile::new, 0.2F, 0.2F);

	private static final AbilityProjectileRenderer.Factory AMATERASU_FACTORY = new AbilityProjectileRenderer.Factory(new CubeModel()).setColor("#FFFF00").setScale(1, 1, 2);
	private static final AbilityProjectileRenderer.Factory YASAKANI_NO_MAGATAMA_FACTORY = new AbilityProjectileRenderer.Factory(new SphereModel()).setColor("#FFFF00").setScale(.5, .5, .5);

	static
	{
		projectiles.add(new Data(AMATERASU, AmaterasuProjectile.class, AMATERASU_FACTORY));
		projectiles.add(new Data(YASAKANI_NO_MAGATAMA, YasakaniNoMagatamaProjectile.class, YASAKANI_NO_MAGATAMA_FACTORY));

	}
}
