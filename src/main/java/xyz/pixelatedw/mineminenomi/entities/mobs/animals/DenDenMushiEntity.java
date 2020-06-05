package xyz.pixelatedw.mineminenomi.entities.mobs.animals;

import javax.annotation.Nullable;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.Hand;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.entities.mobs.IDynamicRenderer;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModEntities;

public class DenDenMushiEntity extends AnimalEntity implements IDynamicRenderer
{
	private static final DataParameter<Integer> TEXTURE_ID = EntityDataManager.createKey(DenDenMushiEntity.class, DataSerializers.VARINT);
	private String[] textures = new String[] {"den_den_mushi1", "den_den_mushi2", "den_den_mushi3"};
	
	public DenDenMushiEntity(World worldIn)
	{
		super(ModEntities.DEN_DEN_MUSHI, worldIn);

		this.goalSelector.addGoal(1, new PanicGoal(this, 0.75D));
		this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
	}
	
	@Override
	protected void registerData()
	{
		super.registerData();
		this.getDataManager().register(TEXTURE_ID, 0);
	}

	@Override
	protected void registerAttributes()
	{
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(5.0);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.12);
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
	public boolean processInteract(PlayerEntity player, Hand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		if (stack.getItem() == Items.IRON_INGOT)
		{
			ItemStack denStack = new ItemStack(ModBlocks.DEN_DEN_MUSHI);
			denStack.getOrCreateTag().putInt("type", this.getTextureId());
			player.inventory.addItemStackToInventory(denStack);
			stack.shrink(1);
			this.remove();
			return true;
		}
		
		return false;
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
	
	@Override
	public AgeableEntity createChild(AgeableEntity ageable)
	{
		return null;
	}

	public int getTextureId()
	{
		return this.getDataManager().get(TEXTURE_ID);
	}

	protected void setTexture(int texture)
	{
		this.getDataManager().set(TEXTURE_ID, texture);
	}
	
	@Override
	public String getMobTexture()
	{
		return this.textures[this.getTextureId()];
	}

}
