package xyz.pixelatedw.MineMineNoMi3.entities.mobs.quest.objectives;

import net.minecraft.block.material.Material;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.api.quests.Quest;

public class EntitySniperTarget extends EntityMob implements IQuestObjective
{
	private Quest quest;
	private EntityPlayer owner;
	
	public EntitySniperTarget(World world)
	{
		super(world);
	}
	
	public EntitySniperTarget(World world, Quest quest)
	{
		super(world);
		this.quest = quest;
		this.experienceValue = 0;
	}
	
	@Override
	public void applyEntityAttributes()
	{ 
		super.applyEntityAttributes(); 
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1);
	}
	
	@Override
	protected void entityInit() 
	{
		super.entityInit();
	}

    @Override
	protected boolean isAIEnabled()
    {
        return false;
    }

	@Override
	public void onEntityUpdate()
	{
		this.motionY /= 1.5 + this.worldObj.rand.nextDouble();
		this.fallDistance = 0;
		
		if(this.onGround && !this.worldObj.isRemote)
			this.setDead();
		
		if(this.isInWater() || this.isInsideOfMaterial(Material.lava))
			this.setDead();
		
		super.onEntityUpdate();
	}
	
	@Override
	public void setOwner(EntityPlayer player)
	{
		this.owner = player;
	}
	
	@Override
	public EntityPlayer getOwner()
	{
		return this.owner;
	}
}
