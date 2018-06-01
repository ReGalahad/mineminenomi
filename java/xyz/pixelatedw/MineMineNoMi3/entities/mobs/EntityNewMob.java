package xyz.pixelatedw.MineMineNoMi3.entities.mobs;

import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityNewMob extends EntityMob implements IDynamicRenderer
{

	private boolean hasHaki = false, isLogia = false;
	private String texture = "N/A", model = "N/A";
	private int state;

	public EntityNewMob(World worldIn) 
	{
		super(worldIn);
		addRandomArmor();
	}
		
	public String getTexture() {return texture;}
	protected void setTexture(String texture) {this.texture = texture;}
	
	public boolean hasBusoHaki() { return false; }
	public boolean hasHaoHaki() { return false; }
	public boolean isLogia() { return false; }	
	public int getDorikiPower() { return 1; }
	public int getBellyInPockets() { return 1; }
	
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);
		nbt.setString("Texture", getTexture());
	}
	
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
		setTexture(nbt.getString("Texture"));	
	}
	
    protected void addRandomArmor() {}
	
    public IEntityLivingData onSpawnWithEgg(IEntityLivingData data)
    {
        super.onSpawnWithEgg(data);
        addRandomArmor();
        return data;
    }
	
    public void setState(int i) { this.getDataWatcher().updateObject(27, i); }
    public int getState() { return this.getDataWatcher().getWatchableObjectInt(27); }
    
	protected void entityInit()
	{
		this.getDataWatcher().addObject(27, state);
		super.entityInit();
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
