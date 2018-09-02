package xyz.pixelatedw.MineMineNoMi3.blocks.tileentities;

import java.util.stream.Collectors;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.abilities.ItoAbilities.Torikago;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.extra.AbilityManager;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.extra.AbilityProperties;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketWorld;

public class TileEntityString extends TileEntity
{
	
	public TileEntityString()
	{

	}

    public void updateEntity()
    {
		for(EntityLivingBase elb : WyHelper.getEntitiesNear(this, 28))
		{
			if(elb instanceof EntityPlayer)
			{
				EntityPlayer user = (EntityPlayer) elb;
				ExtendedEntityStats props = ExtendedEntityStats.get(user);
				AbilityProperties abilityProps = AbilityProperties.get(user);

				if(props.getUsedFruit().equals("itoito"))
				{
					if(user.getDistanceSq(this.xCoord, this.yCoord, this.zCoord) > 810)
					{
						clearRoom();
						
						for(Ability abl : abilityProps.getDevilFruitAbilities())
						{
							if(abl.getAttribute().getAttributeName().equalsIgnoreCase("torikago"))
							{
								((Torikago)abl).alterSpawnFlag(true);
							}
						}
					}		
				}
			}
			else if(WyHelper.getEntitiesNear(this, 28).stream().filter(x -> { return x instanceof EntityPlayer; } ).collect(Collectors.toList()).isEmpty())
			{
				clearRoom();
			}
		}
	}
    
    public void clearRoom()
    {
    	World world = this.worldObj;

		for(int i = -22; i < 22; i++)
		for(int k = -21; k < 21; k++)
		for(int j = -22; j < 22; j++)
			if(world.getBlock((int) this.xCoord + i, (int) this.yCoord + k, (int) this.zCoord + j) == ListMisc.StringWall)
				WyNetworkHelper.sendToServer(new PacketWorld((int) this.xCoord + i, (int) this.yCoord + k, (int) this.zCoord + j, 0));
		WyNetworkHelper.sendToServer(new PacketWorld((int) this.xCoord, (int) this.yCoord, (int) this.zCoord, 0));
    }
	
}

