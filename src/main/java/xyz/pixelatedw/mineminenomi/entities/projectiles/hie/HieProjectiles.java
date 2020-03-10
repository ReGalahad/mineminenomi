package xyz.pixelatedw.mineminenomi.entities.projectiles.hie;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityType;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.PheasantModel;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.TridentModel;
import xyz.pixelatedw.wypi.WyRegistry;
import xyz.pixelatedw.wypi.abilities.models.SphereModel;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity.Data;
import xyz.pixelatedw.wypi.abilities.renderers.AbilityProjectileRenderer;

public class HieProjectiles 
{
	public static List<AbilityProjectileEntity.Data> projectiles = new ArrayList<AbilityProjectileEntity.Data>();

	public static final EntityType ICE_BLOCK_PARTISAN = WyRegistry.registerEntityType("ice_block_partisan", IceBlockPartisanProjectile::new, 0.5F, 0.5F);
	public static final EntityType ICE_BALL = WyRegistry.registerEntityType("ice_ball", IceBallProjectile::new, 0.7F, 0.7F);
	public static final EntityType ICE_BLOCK_PHEASANT = WyRegistry.registerEntityType("ice_block_pheasant", IceBlockPheasantProjectile::new, 1.75F, 1.75F);

	private static final AbilityProjectileRenderer.Factory ICE_BLOCK_PARTISAN_FACTORY = new AbilityProjectileRenderer.Factory(new TridentModel()).setTexture("iceblockpartisan").setScale(1.5);
	private static final AbilityProjectileRenderer.Factory ICE_BALL_FACTORY = new AbilityProjectileRenderer.Factory(new SphereModel()).setColor("#36648B").setScale(5);
	private static final AbilityProjectileRenderer.Factory ICE_BLOCK_PHEASANT_FACTORY = new AbilityProjectileRenderer.Factory(new PheasantModel()).setTexture("iceblockpheasant").setScale(5);

	static
	{
		projectiles.add(new Data(ICE_BLOCK_PARTISAN, IceBlockPartisanProjectile.class, ICE_BLOCK_PARTISAN_FACTORY));
		projectiles.add(new Data(ICE_BALL, IceBallProjectile.class, ICE_BALL_FACTORY));
		projectiles.add(new Data(ICE_BLOCK_PHEASANT, IceBlockPheasantProjectile.class, ICE_BLOCK_PHEASANT_FACTORY));
	}
}
