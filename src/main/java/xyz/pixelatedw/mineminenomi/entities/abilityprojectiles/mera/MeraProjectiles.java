package xyz.pixelatedw.mineminenomi.entities.abilityprojectiles.mera;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityType;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.CubeModel;
import xyz.pixelatedw.mineminenomi.api.abilities.ProjectileAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.ProjectileAbility.Data;
import xyz.pixelatedw.mineminenomi.api.abilities.RendererAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.SphereModel;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.FistModel;

public class MeraProjectiles
{
	public static List<ProjectileAbility.Data> projectiles = new ArrayList<ProjectileAbility.Data>();
	
	public static final EntityType HIKEN = WyRegistry.registerEntityType("hiken", HikenProjectile::new);
	public static final EntityType HIGAN = WyRegistry.registerEntityType("higan", HiganProjectile::new);
	public static final EntityType DAI_ENKAI_ENTEI = WyRegistry.registerEntityType("dai_enkai_entei", DaiEnkaiEnteiProjectile::new);
	public static final EntityType HIDARUMA = WyRegistry.registerEntityType("hidaruma", HidarumaProjectile::new);
	public static final EntityType JUJIKA = WyRegistry.registerEntityType("jujika", JujikaProjectile::new);
	
	private static final RendererAbility.Factory HIKEN_FACTORY = new RendererAbility.Factory(new FistModel()).setTexture("hiken").setScale(1.5);
	private static final RendererAbility.Factory HIGAN_FACTORY = new RendererAbility.Factory(new CubeModel()).setColor(255, 0, 0, 100).setScale(.5);
	private static final RendererAbility.Factory DAI_ENKAI_ENTEI_FACTORY = new RendererAbility.Factory(new SphereModel()).setColor(255, 0, 0, 100).setScale(9);
	private static final RendererAbility.Factory HIDARUMA_FACTORY = new RendererAbility.Factory(new SphereModel()).setScale(0.01);
	private static final RendererAbility.Factory JUJIKA_FACTORY = new RendererAbility.Factory(new SphereModel()).setColor(255, 0, 0, 100).setScale(0.5);

	static
	{
		projectiles.add(new Data(HIKEN, HikenProjectile.class, HIKEN_FACTORY));
		projectiles.add(new Data(HIGAN, HiganProjectile.class, HIGAN_FACTORY));
		projectiles.add(new Data(DAI_ENKAI_ENTEI, DaiEnkaiEnteiProjectile.class, DAI_ENKAI_ENTEI_FACTORY));
		projectiles.add(new Data(HIDARUMA, HidarumaProjectile.class, HIDARUMA_FACTORY));
		projectiles.add(new Data(JUJIKA, JujikaProjectile.class, JUJIKA_FACTORY));
	}
	
}

