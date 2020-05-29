package xyz.pixelatedw.mineminenomi.entities.mobs.animals;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModEntities;

public class DenDenMushiEntity extends AnimalEntity
{
	public DenDenMushiEntity(World worldIn)
	{
		super(ModEntities.DEN_DEN_MUSHI, worldIn);

		this.goalSelector.addGoal(1, new PanicGoal(this, 0.75D));
		this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
	}

	@Override
	protected void registerAttributes()
	{
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(5.0);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.12);
	}

	@Override
	public boolean processInteract(PlayerEntity player, Hand hand)
	{
		ItemStack stack = player.getHeldItem(hand);
		if (stack.getItem() == Items.IRON_INGOT)
		{
			player.inventory.addItemStackToInventory(new ItemStack(ModBlocks.DEN_DEN_MUSHI));
			stack.shrink(1);
			this.remove();
			return true;
		}
		
		return false;
	}

	@Override
	public AgeableEntity createChild(AgeableEntity ageable)
	{
		return null;
	}

}
