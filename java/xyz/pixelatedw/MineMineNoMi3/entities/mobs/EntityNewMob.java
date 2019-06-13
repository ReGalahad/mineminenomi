package xyz.pixelatedw.MineMineNoMi3.entities.mobs;

import java.util.LinkedList;
import java.util.Queue;

import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityNewMob extends EntityMob implements IDynamicRenderer
{

	private int textureId, state;
	protected String[] textures;
	private EntityAIBase currentAI, previousAI;

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
        this.addRandomArmor();
		if(this.textures != null && this.textures.length > 0)
			this.setTexture(this.rand.nextInt(this.textures.length));
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

	public EntityAIBase getCurrentAI()
	{
		return this.currentAI;
	}
	
	public EntityAIBase getPreviousAI()
	{
		return this.previousAI;
	}
	
	public void setCurrentAI(EntityAIBase ai)
	{
		this.currentAI = ai;
	}
	
	public void setPreviousAI(EntityAIBase ai)
	{
		this.previousAI = ai;
	}
	
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
