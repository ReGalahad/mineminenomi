package xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers;

import javax.annotation.Nullable;

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
import xyz.pixelatedw.mineminenomi.api.quests.extra.Questline;
import xyz.pixelatedw.mineminenomi.entities.mobs.GenericNewEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.bandits.EntityGenericBandit;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.EntityGenericPirate;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.init.ModQuestlines;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;

public class DojoSenseiEntity extends GenericNewEntity implements IQuestGiver
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
		
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, EntityGenericPirate.class, true));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, EntityGenericBandit.class, true));
		this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
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

		ItemStack randomSword = new ItemStack(this.swords[this.rand.nextInt(this.swords.length)]);
		this.setItemStackToSlot(EquipmentSlotType.MAINHAND, randomSword);

		return spawnData;
	}

	@Override
	public Questline getQuestline()
	{
		return ModQuestlines.SWORDSMAN_PROGRESSION;
	}
}
