package xyz.pixelatedw.mineminenomi.abilities.goro;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.RayTraceResult;
import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.RaigoProjectile;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.goro.RaigoParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

public class RaigoAbility extends Ability
{
	public static final Ability INSTANCE = new RaigoAbility();
	
	private static final ParticleEffect PARTICLES = new RaigoParticleEffect();
	
	private double posX;
	private double posY;
	private double posZ;
	
	public RaigoAbility()
	{
		super("Raigo", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(45);
		this.setDescription("Creates a huge cloud filled with electricity, which causes massive damage");

		this.onUseEvent = this::onUseEvent;
		this.duringCooldownEvent = this::duringCooldownEvent;
	}
	
	private boolean onUseEvent(PlayerEntity player)
	{
		RayTraceResult mop = WyHelper.rayTraceBlocks(player);
		
		if (mop != null)
		{
			double x = mop.getHitVec().x;
			double y = mop.getHitVec().y;
			double z = mop.getHitVec().z;
			
			this.posX = x;
			this.posY = y;
			this.posZ = z;
			
			AbilityProjectileEntity proj = new RaigoProjectile(player.world, player);
			proj.setLocationAndAngles(x, (y + 90), z, 0, 0);
			proj.setMotion(0, -1.4, 0);
			player.world.addEntity(proj);
		}
		
		return true;
	}
	
	private void duringCooldownEvent(PlayerEntity player, int cooldown)
	{
		if(cooldown > (35 * 20) && cooldown % 10 == 0)
			PARTICLES.spawn(player.world, this.posX, this.posY + 40, this.posZ, 0, 0, 0);
	}
}