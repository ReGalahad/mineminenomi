package xyz.pixelatedw.mineminenomi.abilities.baku;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.AllBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.LiquidBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.RestrictedBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.particles.effects.baku.BakuMunchParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;

public class BakuMunchAbility extends Ability
{
	public static final BakuMunchAbility INSTANCE = new BakuMunchAbility();

	private static final BakuMunchParticleEffect PARTICLES = new BakuMunchParticleEffect();
	private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(AllBlockProtectionRule.INSTANCE, RestrictedBlockProtectionRule.INSTANCE, new BlockProtectionRule(LiquidBlockProtectionRule.INSTANCE).setBanListedBlocks()); 

	public BakuMunchAbility()
	{
		super("Baku Munch", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(5);
		this.setDescription("Allows the user to eat a big chunk of blocks in front of him, obtaining all of them as blocks in their inventory.");

		this.onUseEvent = this::onUseEvent;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		RayTraceResult mop = WyHelper.rayTraceBlocks(player);
		if (mop != null && MathHelper.sqrt(player.getDistanceSq(mop.getHitVec().x, mop.getHitVec().y, mop.getHitVec().z)) < 5)
		{
			for (int x = -2; x < 2; x++)
			{
				for (int y = 0; y < 3; y++)
				{
					for (int z = -2; z < 2; z++)
					{
						int posX = (int) mop.getHitVec().x + x;
						int posY = (int) mop.getHitVec().y - y;
						int posZ = (int) mop.getHitVec().z + z;
						Block tempBlock = player.world.getBlockState(new BlockPos(posX, posY, posZ)).getBlock();
						if (AbilityHelper.placeBlockIfAllowed(player.world, posX, posY, posZ, Blocks.AIR, GRIEF_RULE))
						{
							player.inventory.addItemStackToInventory(new ItemStack(tempBlock));
							PARTICLES.spawn(player.world, posX, posY, posZ, 0, 0, 0);
						}
					}
				}
			}
		}

		return true;
	}
}
