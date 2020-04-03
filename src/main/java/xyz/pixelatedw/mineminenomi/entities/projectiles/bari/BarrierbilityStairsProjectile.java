package xyz.pixelatedw.mineminenomi.entities.projectiles.bari;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.abilities.bari.BarrierbilityStairsAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

public class BarrierbilityStairsProjectile extends AbilityProjectileEntity
{
	public BarrierbilityStairsProjectile(World world)
	{
		super(BariProjectiles.BARRIERBILITY_STAIRS, world);
	}

	public BarrierbilityStairsProjectile(EntityType type, World world)
	{
		super(type, world);
	}

	public BarrierbilityStairsProjectile(World world, double x, double y, double z)
	{
		super(BariProjectiles.BARRIERBILITY_STAIRS, world, x, y, z);
	}

	public BarrierbilityStairsProjectile(World world, LivingEntity player)
	{
		super(BariProjectiles.BARRIERBILITY_STAIRS, world, player);
		
		this.setMaxLife(40);
		this.setPassThroughEntities();
		
		this.onTickEvent = this::onTickEvent;
	}
	
	private void onTickEvent()
	{
		IAbilityData abilityProps = AbilityDataCapability.get(this.getThrower());
		BarrierbilityStairsAbility ability = abilityProps.getEquippedAbility(BarrierbilityStairsAbility.INSTANCE);

		if (ability != null && ability.isContinuous())
			ability.fillBlocksList(AbilityHelper.createFilledCube(this.world, this.posX, this.posY - 2, this.posZ, new int[] {1, 1, 1}, ModBlocks.BARRIER, "air", "nogrief"));
	}

}
