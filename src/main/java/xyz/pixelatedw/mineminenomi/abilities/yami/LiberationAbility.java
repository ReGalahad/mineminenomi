package xyz.pixelatedw.mineminenomi.abilities.yami;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import xyz.pixelatedw.mineminenomi.entities.projectiles.yami.LiberationProjectile;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;

public class LiberationAbility extends Ability
{
	public static final LiberationAbility INSTANCE = new LiberationAbility();

	private int liberationCount = 0;

	public LiberationAbility()
	{
		super("Liberation", AbilityCategory.DEVIL_FRUIT);
		this.setDescription("The user expells everything sucked up by the 'Black Hole' at the target location.");
		this.setMaxCooldown(2);

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		if (this.liberationCount > 0)
		{
			BlockRayTraceResult mop = WyHelper.rayTraceBlocks(player);

			if (mop != null)
			{
				double x = mop.getHitVec().x;
				double y = mop.getHitVec().y;
				double z = mop.getHitVec().z;

				while(this.liberationCount > 0)
				{
					LiberationProjectile proj = new LiberationProjectile(player.world, player);	
					proj.setLocationAndAngles(x + WyHelper.randomWithRange(-3, 3), (y + 14) + WyHelper.randomWithRange(0, 4), z + WyHelper.randomWithRange(-3, 3), 0, 0);
					proj.setMotion(0, -0.7 - player.world.rand.nextDouble(), 0);
					player.world.addEntity(proj);
					this.liberationCount--;	
				}
			}
		}
		else
		{
			int libCount = 0;
			BlockPos pos;
			for (int x = -40; x < 40; x++)
			{
				for (int y = -40; y < 40; y++)
				{
					for (int z = -40; z < 40; z++)
					{
						pos = new BlockPos((int) player.posX + x, (int) player.posY + y, (int) player.posZ + z);
						if (player.world.getBlockState(pos).getBlock() == ModBlocks.DARKNESS)
						{
							player.world.setBlockState(pos, Blocks.AIR.getDefaultState());
							libCount++;
							if (libCount % 10 == 0)
								this.liberationCount++;
						}
					}
				}
			}
		}

		return true;
	}
}
