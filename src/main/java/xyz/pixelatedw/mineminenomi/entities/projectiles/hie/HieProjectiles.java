package xyz.pixelatedw.mineminenomi.entities.projectiles.hie;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityType;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectileEntity.Data;
import xyz.pixelatedw.mineminenomi.api.abilities.RendererAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.SphereModel;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.PheasantModel;
import xyz.pixelatedw.mineminenomi.models.entities.projectiles.TridentModel;

public class HieProjectiles 
{
	public static List<AbilityProjectileEntity.Data> projectiles = new ArrayList<AbilityProjectileEntity.Data>();

	public static final EntityType ICE_BLOCK_PARTISAN = WyRegistry.registerEntityType("ice_block_partisan", IceBlockPartisanProjectile::new);
	public static final EntityType ICE_BALL = WyRegistry.registerEntityType("ice_ball", IceBallProjectile::new);
	public static final EntityType ICE_BLOCK_PHEASANT = WyRegistry.registerEntityType("ice_block_pheasant", IceBlockPheasantProjectile::new);

	private static final RendererAbility.Factory ICE_BLOCK_PARTISAN_FACTORY = new RendererAbility.Factory(new TridentModel()).setTexture("iceblockpartisan").setScale(1.5);
	private static final RendererAbility.Factory ICE_BALL_FACTORY = new RendererAbility.Factory(new SphereModel()).setColor("#0055FF").setScale(5);
	private static final RendererAbility.Factory ICE_BLOCK_PHEASANT_FACTORY = new RendererAbility.Factory(new PheasantModel()).setTexture("iceblockpheasant").setScale(5);

	static
	{
		projectiles.add(new Data(ICE_BLOCK_PARTISAN, IceBlockPartisanProjectile.class, ICE_BLOCK_PARTISAN_FACTORY));
		projectiles.add(new Data(ICE_BALL, IceBallProjectile.class, ICE_BALL_FACTORY));
		projectiles.add(new Data(ICE_BLOCK_PHEASANT, IceBlockPheasantProjectile.class, ICE_BLOCK_PHEASANT_FACTORY));
	}
}
