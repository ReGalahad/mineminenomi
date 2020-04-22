package xyz.pixelatedw.mineminenomi.entities.mobs.quest.objectives;

import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.init.ModEntities;

public class SniperTargetEntity extends MobEntity
{
	private PlayerEntity owner;
	private boolean active = false;

	public SniperTargetEntity(World world)
	{
		super(ModEntities.SNIPER_TARGET, world);
		this.experienceValue = 0;
		this.setAIMoveSpeed(0);
	}

	@Override
	protected void registerAttributes()
	{
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1);
	}

	@Override
	public void tick()
	{
		this.setMotion(0, -0.1, 0);
		this.fallDistance = 0;

		if ((this.onGround || this.isInWater() || this.isInLava()) && !this.world.isRemote)
			this.remove();

		super.tick();
	}
}
