package xyz.pixelatedw.mineminenomi.entities.projectiles.pika;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityType;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.models.CubeModel;
import xyz.pixelatedw.mineminenomi.api.abilities.models.SphereModel;
import xyz.pixelatedw.mineminenomi.api.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.projectiles.AbilityProjectileEntity.Data;
import xyz.pixelatedw.mineminenomi.api.abilities.renderers.RendererAbility;

public class PikaProjectiles
{
	public static List<AbilityProjectileEntity.Data> projectiles = new ArrayList<AbilityProjectileEntity.Data>();

	public static final EntityType AMATERASU = WyRegistry.registerEntityType("amaterasu", AmaterasuProjectile::new, 0.5F, 0.5F);
	public static final EntityType YASAKANI_NO_MAGATAMA = WyRegistry.registerEntityType("yasakani_no_magatama", YasakaniNoMagatamaProjectile::new, 0.2F, 0.2F);

	private static final RendererAbility.Factory AMATERASU_FACTORY = new RendererAbility.Factory(new CubeModel()).setColor("#FFFF00").setScale(1, 1, 2);
	private static final RendererAbility.Factory YASAKANI_NO_MAGATAMA_FACTORY = new RendererAbility.Factory(new SphereModel()).setColor("#FFFF00").setScale(.5, .5, .5).setOffset(0, 0.3, 0);

	static
	{
		projectiles.add(new Data(AMATERASU, AmaterasuProjectile.class, AMATERASU_FACTORY));
		projectiles.add(new Data(YASAKANI_NO_MAGATAMA, YasakaniNoMagatamaProjectile.class, YASAKANI_NO_MAGATAMA_FACTORY));

	}
}
