package xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;

import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.mobs.GenericNewEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.bandits.GenericBanditEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.GenericPirateEntity;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.init.ModQuests;
import xyz.pixelatedw.wypi.quests.Quest;

public class BowMasterEntity extends GenericNewEntity implements IQuestGiver
{
	public BowMasterEntity(World worldIn)
	{
		super(ModEntities.BOW_MASTER, worldIn, new String[] {"bow_master1", "bow_master2", "bow_master3"});
	}

	@Override
	protected void registerGoals()
	{
		super.registerGoals();
		this.goalSelector.addGoal(1, new SwimGoal(this));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1, true));
		this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
		this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(5, new LookRandomlyGoal(this));	
		
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, GenericPirateEntity.class, true));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, GenericBanditEntity.class, true));
		this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
	}
	
	@Override
	protected void registerAttributes()
	{
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.26F);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(70.0D);
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
	public boolean canSpawn(IWorld world, SpawnReason reason)
	{
		return true;
	}
	
	@Override
	public boolean canDespawn(double distance)
	{
		if(distance > 1024)
			return true;
		
		return false;
	}
	
	@Override
	@Nullable
	public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) 
	{
		spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);

		ItemStack randomSword = new ItemStack(Items.BOW);
		this.setItemStackToSlot(EquipmentSlotType.MAINHAND, randomSword);

		return spawnData;
	}

	@Override
	public Quest[] getAvailableQuests(PlayerEntity player)
	{
		IEntityStats entityProps = EntityStatsCapability.get(player);		
		List<Quest> availableQuests = new ArrayList<Quest>();

		if(entityProps.isSniper())
			availableQuests.addAll(ImmutableList.of(ModQuests.SNIPER_TRIAL_01, ModQuests.SNIPER_TRIAL_02, ModQuests.SNIPER_TRIAL_03, ModQuests.SNIPER_TRIAL_04));
		
		Quest[] quests = new Quest[availableQuests.size()];	
		return availableQuests.toArray(quests);
	}
}
