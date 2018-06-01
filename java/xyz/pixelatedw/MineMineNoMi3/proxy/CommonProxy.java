package xyz.pixelatedw.MineMineNoMi3.proxy;

import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class CommonProxy 
{

	public void preInit(){}
	public void init(){}
	
	public EntityPlayer getPlayerEntity (MessageContext ctx) 
	{
        return ctx.getServerHandler().playerEntity;
	}
	
	public void spawnCustomParticles(Entity theEntity, String particleType, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) { }
}
