package xyz.pixelatedw.mineminenomi.blocks.tileentities;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.init.ModTileEntities;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.ChargeableAbility;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.server.SSyncAbilityDataPacket;

public class AblProtectionTileEntity extends TileEntity implements ITickableTileEntity
{
	private int protectedSize = 10;
	
	public AblProtectionTileEntity()
	{
		super(ModTileEntities.ABILITY_PROTECTION);
	}

	public void setupProtection(World world, BlockPos pos, int size)
	{
		int posX = pos.getX();
		int posY = pos.getY();
		int posZ = pos.getZ();
		
		ExtendedWorldData worldData = ExtendedWorldData.get(world);
		this.protectedSize = size;

		int minPosX = posX - size;
		int minPosY = posY - size;
		int minPosZ = posZ - size;
		int maxPosX = posX + size;
		int maxPosY = posY + size;
		int maxPosZ = posZ + size;

		worldData.addRestrictedArea(new int[] { minPosX, minPosY, minPosZ }, new int[] { maxPosX, maxPosY, maxPosZ });
	}
	
	public int getSize()
	{
		return this.protectedSize;
	}
	
	@Override
	public void tick()
	{
		if (!this.world.isRemote)
		{
			List<PlayerEntity> nearbyEntities = WyHelper.getEntitiesNear(this.getPos(), this.world, this.protectedSize, PlayerEntity.class);
			
			if (!nearbyEntities.isEmpty())
			{
				IAbilityData props;
				for(PlayerEntity entity : nearbyEntities) 
				{
					if(entity == null || !entity.isAlive())
						continue;
					
					props = AbilityDataCapability.get(entity);
					boolean abilityCheck = Arrays.stream(props.getEquippedAbilities()).anyMatch(ability -> {
						
						boolean continuousCheck = ability instanceof ContinuousAbility && ((ContinuousAbility)ability).isContinuous();
						boolean chargeableCheck = ability instanceof ChargeableAbility && ((ChargeableAbility)ability).isCharging();
						
						return continuousCheck || chargeableCheck;
					});
					
					if(!abilityCheck)
						continue;
						
					for(Ability abl : props.getEquippedAbilities())
					{
						if(abl == null)
							continue;
						
						if(abl instanceof ContinuousAbility && abl.isContinuous())
							((ContinuousAbility)abl).stopContinuity(entity);
						else if(abl instanceof ChargeableAbility && abl.isCharging())
						{
							ChargeableAbility cAbl = ((ChargeableAbility)abl);
							cAbl.setChargeTime(cAbl.getMaxChargeTime());
							cAbl.startCooldown(entity);	
						}
					}
					
					WyNetwork.sendTo(new SSyncAbilityDataPacket(entity.getEntityId(), props), entity);
				}
			}
		}
	}

	@Override
	public void read(CompoundNBT nbtTag)
	{
		super.read(nbtTag);
		
		this.protectedSize = nbtTag.getInt("Size");
	}

	@Override
	public CompoundNBT write(CompoundNBT nbtTag)
	{
		super.write(nbtTag);
		
		nbtTag.putInt("Size", this.protectedSize);
		
		return nbtTag;
	}
	
	@Override
	public CompoundNBT getUpdateTag()
	{
		return this.write(new CompoundNBT());
	}

	@Override
	@Nullable
	public SUpdateTileEntityPacket getUpdatePacket()
	{
		CompoundNBT nbttagcompound = new CompoundNBT();
		this.write(nbttagcompound);
		return new SUpdateTileEntityPacket(this.pos, 9, this.getUpdateTag());
	}
}
