package xyz.pixelatedw.mineminenomi.entities.mobs;

import javax.annotation.Nullable;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class GenericNewEntity extends CreatureEntity implements IDynamicRenderer
{

	protected String[] textures;
	private static final DataParameter<Integer> TEXTURE_ID = EntityDataManager.createKey(GenericNewEntity.class, DataSerializers.VARINT);
	private int doriki, belly, state;
	private boolean hasBusoHaki;
	// private EntityAIBase currentAI, previousAI;

	public GenericNewEntity(EntityType type, World worldIn, String[] textures)
	{
		super(type, worldIn);
		this.textures = textures;
		this.experienceValue = 5;
	}

	@Override
	protected void registerAttributes()
	{
		super.registerAttributes();
		this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
	}
	
	@Override
	protected void registerData()
	{
		super.registerData();
		this.getDataManager().register(TEXTURE_ID, 0);
	}

	@Override
	public void writeAdditional(CompoundNBT nbt)
	{
		super.writeAdditional(nbt);
		nbt.putInt("Texture", this.getTextureId());
		nbt.putInt("Doriki", this.doriki);
		nbt.putInt("Belly", this.belly);

		nbt.putBoolean("HasBusoHaki", this.hasBusoHaki);
	}

	@Override
	public void readAdditional(CompoundNBT nbt)
	{
		super.readAdditional(nbt);
		this.setTexture(nbt.getInt("Texture"));
		this.doriki = nbt.getInt("Doriki");
		this.belly = nbt.getInt("Belly");

		this.hasBusoHaki = nbt.getBoolean("HasBusoHaki");
	}

	@Override
	@Nullable
	public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) 
	{
		spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);
		
		if(this.textures != null && this.textures.length > 0)
			this.setTexture(this.rand.nextInt(this.textures.length));
		
		return spawnData;
	}
	
	@Override
	public String getMobTexture()
	{
		return textures[this.getTextureId()];
	}

	public int getTextureId()
	{
		return this.getDataManager().get(TEXTURE_ID);
	}

	protected void setTexture(int texture)
	{
		this.getDataManager().set(TEXTURE_ID, texture);
	}

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

}
