package xyz.pixelatedw.mineminenomi.entities.projectiles.goro;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityType;
import xyz.pixelatedw.wypi.WyRegistry;
import xyz.pixelatedw.wypi.abilities.models.CubeModel;
import xyz.pixelatedw.wypi.abilities.models.SphereModel;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity.Data;
import xyz.pixelatedw.wypi.abilities.renderers.AbilityProjectileRenderer;

public class GoroProjectiles 
{

	public static List<AbilityProjectileEntity.Data> projectiles = new ArrayList<AbilityProjectileEntity.Data>();
	
	public static final EntityType SANGO = WyRegistry.registerEntityType("sango", SangoProjectile::new, 0.5F, 0.5F);
	public static final EntityType RAIGO = WyRegistry.registerEntityType("raigo", RaigoProjectile::new, 5, 5);
	public static final EntityType VOLT_VARI_5_MILLION = WyRegistry.registerEntityType("volt_vari_5_million", VoltVari5MillionProjectile::new, 0.5F, 0.5F);
	public static final EntityType VOLT_VARI_20_MILLION = WyRegistry.registerEntityType("volt_vari_20_million", VoltVari20MillionProjectile::new, 0.5F, 0.5F);
	public static final EntityType VOLT_VARI_60_MILLION = WyRegistry.registerEntityType("volt_vari_60_million", VoltVari60MillionProjectile::new, 0.5F, 0.5F);
	public static final EntityType VOLT_VARI_200_MILLION = WyRegistry.registerEntityType("volt_vari_200_million", VoltVari200MillionProjectile::new, 1, 1);

	private static final AbilityProjectileRenderer.Factory SANGO_FACTORY = new AbilityProjectileRenderer.Factory(new CubeModel()).setScale(0);
	private static final AbilityProjectileRenderer.Factory RAIGO_FACTORY = new AbilityProjectileRenderer.Factory(new SphereModel()).setColor("#5D8AA8").setScale(50);
	private static final AbilityProjectileRenderer.Factory VOLT_VARI_5_MILLION_FACTORY = new AbilityProjectileRenderer.Factory(new SphereModel()).setColor("#92C1E5").setScale(1);
	private static final AbilityProjectileRenderer.Factory VOLT_VARI_20_MILLION_FACTORY = new AbilityProjectileRenderer.Factory(new SphereModel()).setColor("#7CB9E8").setScale(3);
	private static final AbilityProjectileRenderer.Factory VOLT_VARI_60_MILLION_FACTORY = new AbilityProjectileRenderer.Factory(new SphereModel()).setColor("#6BB0E5").setScale(5);
	private static final AbilityProjectileRenderer.Factory VOLT_VARI_200_MILLION_FACTORY = new AbilityProjectileRenderer.Factory(new SphereModel()).setColor("#3170A0").setScale(7);
	
	static
	{
		projectiles.add(new Data(SANGO, SangoProjectile.class, SANGO_FACTORY));
		projectiles.add(new Data(RAIGO, RaigoProjectile.class, RAIGO_FACTORY));
		projectiles.add(new Data(VOLT_VARI_5_MILLION, VoltVari5MillionProjectile.class, VOLT_VARI_5_MILLION_FACTORY));
		projectiles.add(new Data(VOLT_VARI_20_MILLION, VoltVari20MillionProjectile.class, VOLT_VARI_20_MILLION_FACTORY));
		projectiles.add(new Data(VOLT_VARI_60_MILLION, VoltVari60MillionProjectile.class, VOLT_VARI_60_MILLION_FACTORY));
		projectiles.add(new Data(VOLT_VARI_200_MILLION, VoltVari200MillionProjectile.class, VOLT_VARI_200_MILLION_FACTORY));
	}	
}
