package xyz.pixelatedw.mineminenomi.abilities.kage;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.RayTraceResult;
import xyz.pixelatedw.mineminenomi.entities.projectiles.kage.TsunoTokagePillarEntity;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;

public class TsunoTokageAbility extends Ability
{
	public static final TsunoTokageAbility INSTANCE = new TsunoTokageAbility();

	public TsunoTokageAbility()
	{
		super("Tsuno Tokage", AbilityCategory.DEVIL_FRUIT);
		this.setDescription("The user creates a lizard-like shadow under his opponent, which pierces them from below.");
		this.setMaxCooldown(13);

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		RayTraceResult mop = WyHelper.rayTraceBlocks(player);
		if (mop != null)
		{
			double i = mop.getHitVec().x;
			double j = mop.getHitVec().y;
			double k = mop.getHitVec().z;
			TsunoTokagePillarEntity pillar = new TsunoTokagePillarEntity(player.world, player);
			pillar.rotationPitch = 90;
			pillar.setPosition(i, j - 3, k);
			pillar.setMotion(0, 0.7, 0);
			player.world.addEntity(pillar);
		}

		return true;
	}
}