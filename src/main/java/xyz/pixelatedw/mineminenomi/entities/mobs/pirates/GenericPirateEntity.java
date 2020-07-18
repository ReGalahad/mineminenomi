package xyz.pixelatedw.mineminenomi.entities.mobs.pirates;

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
import xyz.pixelatedw.mineminenomi.entities.mobs.GenericNewEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.bandits.GenericBanditEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.marines.GenericMarineEntity;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;

public class GenericPirateEntity extends GenericNewEntity
{
	protected Item[] pirateSwords = new Item[] { ModWeapons.PIRATE_CUTLASS, Items.IRON_SWORD };

	private static final Predicate<LivingEntity> NON_PIRATE = (target) ->
	{
		if (target instanceof PlayerEntity)
		{
			//IEntityStats props = EntityStatsCapability.get(target);
			return true;//!props.isPirate();
		}
		else
		{
			return target instanceof GenericMarineEntity || target instanceof GenericBanditEntity;
		}
	};
	
	protected GenericPirateEntity(EntityType<? extends MobEntity> type, World worldIn, String[] textures)
	{
		super(type, worldIn, textures);
	}

	@Override
	protected void registerGoals()
	{
		((GroundPathNavigator) this.getNavigator()).setBreakDoors(true);

		this.goalSelector.addGoal(1, new SwimGoal(this));
		this.goalSelector.addGoal(2, new OpenDoorGoal(this, false));
		this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
		this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(5, new LookRandomlyGoal(this));

		this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, LivingEntity.class, 10, true, true, NON_PIRATE));
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
