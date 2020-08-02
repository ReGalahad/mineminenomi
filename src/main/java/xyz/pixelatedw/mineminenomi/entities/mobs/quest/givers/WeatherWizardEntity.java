package xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.mobs.GenericNewEntity;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.init.ModQuests;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.quests.Quest;

public class WeatherWizardEntity extends GenericNewEntity implements IQuestGiver
{
	public WeatherWizardEntity(World world)
	{
		super(ModEntities.WEATHER_WIZARD, world, new String[] {"weather_wizard1"});
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

		this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
	}

	@Override
	protected void registerAttributes()
	{
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.22F);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0D);
		this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
		
		this.setDoriki(10 + WyHelper.randomWithRange(0, 10));
		this.setBelly(20 + WyHelper.randomWithRange(0, 10));
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

		if(this.rand.nextDouble() < 0.4)
		{
			ItemStack climaTact = new ItemStack(ModWeapons.CLIMA_TACT);
			this.setItemStackToSlot(EquipmentSlotType.MAINHAND, climaTact);
		}
		
		return spawnData;
	}
	
	@Override
	public Quest[] getAvailableQuests(PlayerEntity player)
	{
		IEntityStats entityProps = EntityStatsCapability.get(player);		
		List<Quest> availableQuests = new ArrayList<Quest>();

		if(entityProps.isWeatherWizard())
			availableQuests.addAll(Arrays.asList(ModQuests.ART_OF_WEATHER_TRIALS));
		
		Quest[] quests = new Quest[availableQuests.size()];	
		return availableQuests.toArray(quests);
	}
}
