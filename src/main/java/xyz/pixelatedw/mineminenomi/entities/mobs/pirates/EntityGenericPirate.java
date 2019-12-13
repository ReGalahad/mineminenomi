package xyz.pixelatedw.mineminenomi.entities.mobs.pirates;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.entities.mobs.GenericNewEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.bandits.EntityGenericBandit;
import xyz.pixelatedw.mineminenomi.entities.mobs.marines.EntityGenericMarine;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;

public class EntityGenericPirate extends GenericNewEntity
{

	protected Item[] pirateSwords = new Item[] {ModWeapons.pirateCutlass, Items.IRON_SWORD};
	
	protected EntityGenericPirate(EntityType<? extends MobEntity> type, World worldIn, String[] textures)
	{
		super(type, worldIn, textures);
	}
	
	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(1, new SwimGoal(this));
		this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
		this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(5, new LookRandomlyGoal(this));

		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, EntityGenericMarine.class, true));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, EntityGenericBandit.class, true));
		this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
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
}

