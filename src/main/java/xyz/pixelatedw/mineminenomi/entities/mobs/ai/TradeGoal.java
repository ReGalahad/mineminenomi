package xyz.pixelatedw.mineminenomi.entities.mobs.ai;

import net.minecraft.entity.ai.goal.Goal;
import xyz.pixelatedw.mineminenomi.entities.mobs.misc.TraderEntity;

public class TradeGoal extends Goal{

	public TraderEntity trader;
	public TradeGoal(TraderEntity entity) {
		super();
		this.trader = entity;
	}
	@Override
	public boolean shouldExecute() {
/*		if(this.trader.getIsOpened()) {
			this.trader.setMotion(0, this.trader.getMotion().y, 0);
		return true;
		}
		*/
		return false;
		
	}

}
