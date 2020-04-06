package xyz.pixelatedw.mineminenomi.entities.mobs.misc;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.init.ModEntities;

public class WaxPlayerEntity extends CreatureEntity{

	protected WaxPlayerEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
		super(type, worldIn);
		// TODO Auto-generated constructor stub
	}
	private int tick = 0;
	
	public WaxPlayerEntity(World world)
	{
		super(ModEntities.WAX_PLAYER, world);
	}


	@Override
	 public void tick() {
	      super.tick();
	    
	      if(tick > 300) {
	    	  this.remove();
	      }
	      tick++;
	   }
	
}
