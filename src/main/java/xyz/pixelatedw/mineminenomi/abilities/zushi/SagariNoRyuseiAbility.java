package xyz.pixelatedw.mineminenomi.abilities.zushi;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.RayTraceResult;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.mineminenomi.entities.projectiles.zushi.SagariNoRyuseiProjectile;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class SagariNoRyuseiAbility extends Ability
{
	public static final SagariNoRyuseiAbility INSTANCE = new SagariNoRyuseiAbility();

	public SagariNoRyuseiAbility()
	{
		super("Sagari no Ryusei", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(25);
		this.setDescription("Using the gravity the user pulls a nearby meteorite down on their enemies.");

		this.onUseEvent = this::onUseEvent;
	}
	
	private boolean onUseEvent(PlayerEntity player)
	{
		RayTraceResult mop = WyHelper.rayTraceBlocks(player);
		
		if (mop != null)
		{
			double x = mop.getHitVec().x;
			double y = mop.getHitVec().y;
			double z = mop.getHitVec().z;

			AbilityProjectileEntity proj = new SagariNoRyuseiProjectile(player.world, player);
			proj.setLocationAndAngles(x, (y + 90), z, 0, 0);
			proj.setMotion(0, -1.4, 0);
			player.world.addEntity(proj);
		}
		
		return true;
	}
}