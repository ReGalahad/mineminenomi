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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.entities.ai.IHakiUser;
import xyz.pixelatedw.mineminenomi.api.entities.ai.ISwordsman;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.mobs.GenericNewEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.bandits.GenericBanditEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.GenericPirateEntity;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.init.ModQuests;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.quests.Quest;

public class DojoSenseiEntity extends GenericNewEntity implements IQuestGiver, IHakiUser, ISwordsman
{
	protected Item[] swords = new Item[] {ModWeapons.SANDAI_KITETSU, ModWeapons.NIDAI_KITESTU, ModWeapons.WADO_ICHIMONJI, Items.DIAMOND_SWORD};
	
	public DojoSenseiEntity(World worldIn)
	{
		super(ModEntities.DOJO_SENSEI, worldIn, new String[] {"dojosensei1", "dojosensei2", "dojosensei3"});
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
		
		this.addSwordsmanAbilities(this, 4);
		this.addBusoshokuHaki(this, 100);
	}
	
	@Override
	protected void registerAttributes()
	{
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20F);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(10.0D);
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(80.0D);
		this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
		
		this.setDoriki(20 + WyHelper.randomWithRange(0, 10));
		this.setBelly(20 + WyHelper.randomWithRange(0, 20));
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

		ItemStack randomSword = new ItemStack(this.swords[this.rand.nextInt(this.swords.length)]);
		this.setItemStackToSlot(EquipmentSlotType.MAINHAND, randomSword);

		return spawnData;
	}

	@Override
	public Quest[] getAvailableQuests(PlayerEntity player)
	{
		IEntityStats entityProps = EntityStatsCapability.get(player);		
		List<Quest> availableQuests = new ArrayList<Quest>();
		
		if(entityProps.isSwordsman())
			availableQuests.addAll(ImmutableList.of(ModQuests.SWORDSMAN_TRIAL_01, ModQuests.SWORDSMAN_TRIAL_02, ModQuests.SWORDSMAN_TRIAL_03, ModQuests.SWORDSMAN_TRIAL_04));
		
		Quest[] quests = new Quest[availableQuests.size()];	
		return availableQuests.toArray(quests);
	}
}
