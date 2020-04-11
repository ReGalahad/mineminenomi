package xyz.pixelatedw.mineminenomi.entities.mobs.misc;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.entities.mobs.GenericNewEntity;
import xyz.pixelatedw.mineminenomi.init.ModEntities;

public class WaxPlayerEntity extends CreatureEntity{

	private static final DataParameter<Integer> TEXTURE_ID = EntityDataManager.createKey(WaxPlayerEntity.class, DataSerializers.VARINT);
	protected WaxPlayerEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		// TODO Auto-generated constructor stub
	}
	private int tick = 0;
	
	
	public WaxPlayerEntity(World world, int id)
	{
		super(ModEntities.WAX_PLAYER, world);
		this.setTexture(id);
	}


	public WaxPlayerEntity(World world)
	{
		super(ModEntities.WAX_PLAYER, world);
		this.setTexture(0);
	}


	@Override
	protected void registerData()
	{
		super.registerData();
		this.getDataManager().register(TEXTURE_ID, 0);
	}

	@Override
	 public void tick() {
	      super.tick();
	    
	      if(tick > 300) {
	    	  this.remove();
	      }
	      tick++;
	   }
	
	@Override
	public void writeAdditional(CompoundNBT nbt)
	{
		super.writeAdditional(nbt);
		nbt.putInt("Texture", this.getTextureId());
	}

	@Override
	public void readAdditional(CompoundNBT nbt)
	{
		super.readAdditional(nbt);
		this.setTexture(nbt.getInt("Texture"));
	}

	public int getTextureId()
	{
		return this.getDataManager().get(TEXTURE_ID);
	}

	protected void setTexture(int texture)
	{
		this.getDataManager().set(TEXTURE_ID, texture);
	}
}
