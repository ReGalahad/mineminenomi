package xyz.pixelatedw.mineminenomi.entities.mobs.pirates;

import javax.annotation.Nullable;

import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.entities.ai.IBrawler;
import xyz.pixelatedw.mineminenomi.api.entities.ai.IRokushikiUser;
import xyz.pixelatedw.mineminenomi.api.entities.ai.ISwordsman;
import xyz.pixelatedw.mineminenomi.entities.mobs.ai.abilities.GapCloserGoal;
import xyz.pixelatedw.mineminenomi.init.ModEntities;

public class PirateCaptainEntity extends GenericPirateEntity implements IRokushikiUser, ISwordsman, IBrawler
{
	public PirateCaptainEntity(World world)
	{
		super(ModEntities.PIRATE_CAPTAIN, world, new String[] {"pirate_captain1", "pirate_captain2", "pirate_captain3", "pirate_captain4", "pirate_captain5"});
	}
	
	@Override
	protected void registerGoals()
	{
		super.registerGoals();
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1, true));
		this.goalSelector.addGoal(2, new GapCloserGoal(this));
		this.addRokushikiAbilities(this, 2);
		this.addSwordsmanAbilities(this, 2);
		this.addBrawlerAbilities(this, 2);
	}
	
	@Override
	protected void registerAttributes()
	{
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23F);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0D);
		this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
		
		this.setDoriki(20);
		this.setBelly(20);
	}

	@Override
	protected void registerData()
	{
		super.registerData();
	}

	@Override
	@Nullable
	public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) 
	{
		spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);

		if(this.rand.nextDouble() < 0.8)
		{
			ItemStack randomSword = new ItemStack(this.pirateSwords[this.rand.nextInt(this.pirateSwords.length)]);
			this.setItemStackToSlot(EquipmentSlotType.MAINHAND, randomSword);

			if(this.rand.nextDouble() < 0.2)
				this.setItemStackToSlot(EquipmentSlotType.OFFHAND, randomSword);
		}
			
		return spawnData;
	}
}
