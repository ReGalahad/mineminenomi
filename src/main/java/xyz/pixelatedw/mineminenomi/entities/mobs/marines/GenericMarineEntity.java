package xyz.pixelatedw.mineminenomi.entities.mobs.marines;

import java.util.function.Predicate;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.OpenDoorGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.mobs.GenericNewEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.bandits.GenericBanditEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.GenericPirateEntity;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;

public class GenericMarineEntity extends GenericNewEntity
{
	protected Item[] marineSwords = new Item[] { ModWeapons.MARINE_SWORD, Items.IRON_SWORD };

	private static final Predicate<LivingEntity> NON_MARINE = (target) ->
	{
		if (target instanceof PlayerEntity)
		{
			IEntityStats props = EntityStatsCapability.get(target);
			return !props.isMarine() && !props.isBountyHunter();
		}
		else
		{
			return target instanceof GenericPirateEntity || target instanceof GenericBanditEntity;
		}
	};
	
	protected GenericMarineEntity(EntityType<? extends MobEntity> type, World worldIn, String[] textures)
	{
		super(type, worldIn, textures);
	}

	@Override
	protected void registerGoals()
	{
		((GroundPathNavigator) this.getNavigator()).setBreakDoors(true);

		this.goalSelector.addGoal(1, new SwimGoal(this));
		this.goalSelector.addGoal(2, new OpenDoorGoal(this, true));
		this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
		this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(5, new LookRandomlyGoal(this));

		this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, LivingEntity.class, 10, true, true, NON_MARINE));
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
		if (distance > 1024)
			return true;

		return false;
	}
}
