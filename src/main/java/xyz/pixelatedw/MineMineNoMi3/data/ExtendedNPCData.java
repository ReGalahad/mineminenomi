package xyz.pixelatedw.MineMineNoMi3.data;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.EntityNewMob;

public class ExtendedNPCData implements IExtendedEntityProperties 
{

	public final static String EXT_PROP_NAME = "_NPCIEEP";
	private final EntityLivingBase entity;
	
	private String texture = "";
	private int doriki, belly;
	private boolean hasBusoHaki = false;
	
	
	public ExtendedNPCData(EntityLivingBase entity) 
	{
		this.entity = entity;	
	}
	
	public static final void register(EntityLivingBase entity) 
	{
		entity.registerExtendedProperties(ExtendedNPCData.EXT_PROP_NAME, new ExtendedNPCData(entity));
	}

	public static final ExtendedNPCData get(EntityLivingBase entity) 
	{
		return (ExtendedNPCData) entity.getExtendedProperties(EXT_PROP_NAME);
	}

	public EntityLivingBase getEntity()
	{
		return this.entity;
	}
	
	public void saveNBTData(NBTTagCompound compound) 
	{
		NBTTagCompound props = new NBTTagCompound();

		props.setInteger("Doriki", this.doriki);
		props.setInteger("Belly", this.belly);
		
		props.setString("CTexture", this.texture);
		
		props.setBoolean("HasBuso", this.hasBusoHaki);
		
		compound.setTag(EXT_PROP_NAME, props);
	}

	public void loadNBTData(NBTTagCompound compound) 
	{
		NBTTagCompound props = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);

		this.doriki = props.getInteger("Doriki");
		this.belly = props.getInteger("Belly");
		
		this.texture = props.getString("CTexture");
		
		this.hasBusoHaki = props.getBoolean("HasBuso");
	}

	public void init(Entity entity, World world) {}
	
	public void setTexture(String texture) { this.texture = texture; }
	public String getTexture() { return this.texture; }
	
	public void setBusoHaki(boolean value) { this.hasBusoHaki = value; }
	public boolean getBusoHaki() { return this.hasBusoHaki; }
	
	public void setDoriki(int value) { this.doriki = value; }
	public int getDoriki() { return this.doriki; }
	
	public void setBelly(int value) { this.belly = value; }
	public int getBelly() { return this.belly; }
}
