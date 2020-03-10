package xyz.pixelatedw.mineminenomi.entities.projectiles.mera;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityType;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.FistModel;
import xyz.pixelatedw.wypi.WyRegistry;
import xyz.pixelatedw.wypi.abilities.models.CubeModel;
import xyz.pixelatedw.wypi.abilities.models.SphereModel;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity.Data;
import xyz.pixelatedw.wypi.abilities.renderers.AbilityProjectileRenderer;

public class MeraProjectiles
{
	public static List<AbilityProjectileEntity.Data> projectiles = new ArrayList<AbilityProjectileEntity.Data>();
	
	public static final EntityType HIKEN = WyRegistry.registerEntityType("hiken", HikenProjectile::new, 0.5F, 0.5F);
	public static final EntityType HIGAN = WyRegistry.registerEntityType("higan", HiganProjectile::new, 0.5F, 0.5F);
	public static final EntityType DAI_ENKAI_ENTEI = WyRegistry.registerEntityType("dai_enkai_entei", DaiEnkaiEnteiProjectile::new, 1.75F, 1.75F);
	public static final EntityType HIDARUMA = WyRegistry.registerEntityType("hidaruma", HidarumaProjectile::new, 0.5F, 0.5F);
	public static final EntityType JUJIKA = WyRegistry.registerEntityType("jujika", JujikaProjectile::new, 0.5F, 0.5F);
	
	private static final AbilityProjectileRenderer.Factory HIKEN_FACTORY = new AbilityProjectileRenderer.Factory(new FistModel()).setTexture("hiken").setScale(1.5);
	private static final AbilityProjectileRenderer.Factory HIGAN_FACTORY = new AbilityProjectileRenderer.Factory(new CubeModel()).setColor(255, 0, 0, 100).setScale(0.5).setOffset(0, 0.6, 0);
	private static final AbilityProjectileRenderer.Factory DAI_ENKAI_ENTEI_FACTORY = new AbilityProjectileRenderer.Factory(new SphereModel()).setColor(255, 0, 0, 100).setScale(9);
	private static final AbilityProjectileRenderer.Factory HIDARUMA_FACTORY = new AbilityProjectileRenderer.Factory(new SphereModel()).setScale(0.01);
	private static final AbilityProjectileRenderer.Factory JUJIKA_FACTORY = new AbilityProjectileRenderer.Factory(new SphereModel()).setColor(255, 0, 0, 100).setScale(0.5).setOffset(0, 0.6, 0);

	static
	{
		projectiles.add(new Data(HIKEN, HikenProjectile.class, HIKEN_FACTORY));
		projectiles.add(new Data(HIGAN, HiganProjectile.class, HIGAN_FACTORY));
		projectiles.add(new Data(DAI_ENKAI_ENTEI, DaiEnkaiEnteiProjectile.class, DAI_ENKAI_ENTEI_FACTORY));
		projectiles.add(new Data(HIDARUMA, HidarumaProjectile.class, HIDARUMA_FACTORY));
		projectiles.add(new Data(JUJIKA, JujikaProjectile.class, JUJIKA_FACTORY));
	}
	
}

