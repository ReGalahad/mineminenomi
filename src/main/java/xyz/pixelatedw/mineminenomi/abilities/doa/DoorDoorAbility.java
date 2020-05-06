package xyz.pixelatedw.mineminenomi.abilities.doa;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;

public class DoorDoorAbility extends Ability
{
	public static final DoorDoorAbility INSTANCE = new DoorDoorAbility();

	public DoorDoorAbility()
	{
		super("Door Door", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(5);

		this.onUseEvent = this::onUseEvent;
	}
	
	private boolean onUseEvent(PlayerEntity player)
	{
		BlockRayTraceResult hitBlock = WyHelper.rayTraceBlocks(player);		
		if(hitBlock == null || Math.sqrt(player.getDistanceSq(hitBlock.getHitVec())) > 2.5)
		{
			return false;
		}
		
		Vec3d dir = WyHelper.propulsion(player, 2, 2);
		int x = (int) Math.floor(dir.x), z = (int) Math.floor(dir.z);
		if((dir.x > -0.3 && dir.x < 0) || (dir.x > 0 && dir.x < 0.3))
			x = 0;
		if((dir.z > -0.3 && dir.z < 0) || (dir.z > 0 && dir.z < 0.3))
			z = 0;
		Vec3i iDir = new Vec3i(x, dir.y, z);
		BlockPos pos = player.getPosition().add(iDir);
		boolean firstSolid = false;
		int airBlocks = 0;
		for(int i = 0; i < 40; i++)
		{
			if(player.world.getBlockState(pos) == Blocks.AIR.getDefaultState() && (firstSolid || airBlocks > 1))
			{
				player.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
				break;
			}
			
			dir = WyHelper.propulsion(player, 2, 2);
			if((dir.x > -0.3 && dir.x < 0) || (dir.x > 0 && dir.x < 0.3))
				x = 0;
			if((dir.z > -0.3 && dir.z < 0) || (dir.z > 0 && dir.z < 0.3))
				z = 0;
			iDir = new Vec3i(x, dir.y, z);
			pos = pos.add(iDir);
			
			if(player.world.getBlockState(pos).isSolid())
				firstSolid = true;
			
			if(player.world.getBlockState(pos) == Blocks.AIR.getDefaultState())
				airBlocks++;
		}
		
		return true;
	}
}
