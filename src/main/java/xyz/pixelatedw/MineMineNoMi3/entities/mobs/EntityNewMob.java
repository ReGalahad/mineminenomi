package xyz.pixelatedw.MineMineNoMi3.entities.mobs;

import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketEntityNBTSync;

public class EntityNewMob extends EntityMob implements IDynamicRenderer, INBTEntity
{
	protected String[] textures;
	private int doriki, belly, textureId, state;
	private boolean hasBusoHaki;
	private EntityAIBase currentAI, previousAI;

	public EntityNewMob(World worldIn) 
	{
		this(worldIn, null);
	}
	
	public EntityNewMob(World worldIn, String[] textures) 
	{
		super(worldIn);
		this.addRandomArmor();
		this.textures = textures;
		
		if(this.textures != null && this.textures.length > 0)
			this.setTexture(this.rand.nextInt(this.textures.length));
	}

    @Override
	protected void addRandomArmor() {}
	
    @Override
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData data)
    {
        super.onSpawnWithEgg(data);
        this.addRandomArmor();
		if(this.textures != null && this.textures.length > 0)
			this.setTexture(this.rand.nextInt(this.textures.length));

		return data;
    }

	@Override
	protected void entityInit()
	{
		super.entityInit();
	}
	
	@Override
	public ItemStack[] getLastActiveItems()
	{
		return new ItemStack[] {};
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);
		nbt.setInteger("Texture", this.textureId);		
		nbt.setInteger("Doriki", this.doriki);
		nbt.setInteger("Belly", this.belly);
		
		nbt.setBoolean("HasBusoHaki", this.hasBusoHaki);
	}
	
	@Override
	public void readEntityFromExtraNBT(NBTTagCompound nbt)
	{
		this.readEntityFromNBT(nbt);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
		this.textureId = nbt.getInteger("Texture");
		this.doriki = nbt.getInteger("Doriki");
		this.belly = nbt.getInteger("Belly");

		this.hasBusoHaki = nbt.getBoolean("HasBusoHaki");
		
		System.out.println("" + this.textureId);
	}
	
	public void updateNBT()
	{
		NBTTagCompound nbtClone = new NBTTagCompound();
		this.writeEntityToNBT(nbtClone);

		WyNetworkHelper.sendToAll(new PacketEntityNBTSync(this.getEntityId(), nbtClone));
	}
	
	// Used mostly for debugging
	@Override
	public void onEntityUpdate()
	{
		super.onEntityUpdate();
		
		//if (this.ticksExisted % 100 == 0)
		//	this.updateNBT();
		
		//System.out.println(this.getEntityId() + " " + this.textureId);
	}
	
	@Override
	protected boolean isValidLightLevel()
	{return true;} 
    
	@Override
	protected boolean canDespawn()
	{return true;}
    
	@Override
	public boolean isAIEnabled()
	{return true;}
	
	@Override
	public boolean getCanSpawnHere()
	{return true;}

	public String getTexture() { return textures[this.textureId]; }
	public int getTextureId() { return this.textureId; }
	protected void setTexture(int texture) { this.textureId = texture; }	
	
	public int getDoriki()
	{
		return this.doriki;
	}
	
	public void setDoriki(int value)
	{
		this.doriki = value;
	}
	
	public int getBelly()
	{
		return this.belly;
	}
	
	public void setBelly(int value)
	{
		this.belly = value;
	}
	
	public boolean hasBusoHaki()
	{
		return this.hasBusoHaki;
	}
	
	public void setBusoHaki(boolean value)
	{
		this.hasBusoHaki = value;
	}
	
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
	
	@Override
	public String getMobTexture()
	{ return this.getTexture(); }

	@Override
	public double[] itemOffset() 
	{
		return new double[] {0, 0, 0};
	}

	@Override
	public double[] itemScale() 
	{
		return new double[] {1, 1, 1};
	}

}
