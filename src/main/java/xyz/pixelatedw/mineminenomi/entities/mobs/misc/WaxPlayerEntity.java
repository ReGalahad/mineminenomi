package xyz.pixelatedw.mineminenomi.entities.mobs.misc;

import java.util.Optional;
import java.util.UUID;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.init.ModEntities;

public class WaxPlayerEntity extends CreatureEntity{

	private static final DataParameter<Optional<UUID>> TEXTURE_ID = EntityDataManager.createKey(WaxPlayerEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID);
	protected WaxPlayerEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		// TODO Auto-generated constructor stub
	}
	private int tick = 0;
	
	private UUID owner;
	
	public WaxPlayerEntity(World world, int id, UUID p)
	{
		super(ModEntities.WAX_PLAYER, world);
		this.setTexture(Optional.ofNullable(p));
		this.owner = p;
	}


	public WaxPlayerEntity(World world)
	{
		super(ModEntities.WAX_PLAYER, world);
		this.setTexture(null);
	}


	@Override
	protected void registerData()
	{
		super.registerData();
		this.getDataManager().register(TEXTURE_ID, null);
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
		if(this.getTextureId().isPresent()) {
		nbt.putUniqueId("Texture", this.getTextureId().get());
		}
	}

	@Override
	public void readAdditional(CompoundNBT nbt)
	{
		super.readAdditional(nbt);
		this.setTexture(Optional.ofNullable(nbt.getUniqueId("Texture")));
	}

	public Optional<UUID> getTextureId()
	{
		return this.getDataManager().get(TEXTURE_ID).isPresent() ? this.getDataManager().get(TEXTURE_ID) : null;
	}

	protected void setTexture(Optional<UUID> uuid)
	{
		this.getDataManager().set(TEXTURE_ID, uuid);
	}
}
