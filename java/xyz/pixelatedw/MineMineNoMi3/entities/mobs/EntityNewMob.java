package xyz.pixelatedw.MineMineNoMi3.entities.mobs;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedNPCData;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketSyncNPCData;

public class EntityNewMob extends EntityMob implements IDynamicRenderer
{

	private int textureId, state;
	protected String[] textures;

	public EntityNewMob(World worldIn) 
	{
		this(worldIn, null);
	}
	
	public EntityNewMob(World worldIn, String[] textures) 
	{
		super(worldIn);
		addRandomArmor();
		this.textures = textures;
	}

	public String getTexture() { return textures[this.getDataWatcher().getWatchableObjectInt(28)]; }
	public int getTextureId() { return this.getDataWatcher().getWatchableObjectInt(28); }
	protected void setTexture(int texture) { this.getDataWatcher().updateObject(28, texture); }
	
    protected void addRandomArmor() {}
	
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data)
    {
        super.onSpawnWithEgg(data);
        addRandomArmor();
		if(textures != null && textures.length > 0)
			this.setTexture(this.rand.nextInt(textures.length));
        return data;
    }
	
    public void setState(int i) { this.getDataWatcher().updateObject(27, i); }
    public int getState() { return this.getDataWatcher().getWatchableObjectInt(27); }
    
	protected void entityInit()
	{
		this.getDataWatcher().addObject(27, state);
		this.getDataWatcher().addObject(28, textureId);
		super.entityInit();
	}
	
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);	
		nbt.setInteger("Texture", this.getTextureId());
	}
	
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
		this.setTexture(nbt.getInteger("Texture"));
	}
	
	protected boolean isValidLightLevel()
	{return true;} 
    
	protected boolean canDespawn()
	{return true;}
    
	public boolean isAIEnabled()
	{return true;}
	
	public boolean getCanSpawnHere()
	{return true;}

	public String getMobTexture()
	{ return this.getTexture(); }

	public double[] itemOffset() 
	{
		return new double[] {0, 0, 0};
	}

	public double[] itemScale() 
	{
		return new double[] {1, 1, 1};
	}

}
