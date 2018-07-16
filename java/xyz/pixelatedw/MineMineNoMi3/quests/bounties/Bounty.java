package xyz.pixelatedw.MineMineNoMi3.quests.bounties;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.quests.Quest;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;

public abstract class Bounty extends Quest
{

	public abstract boolean isTarget(EntityLivingBase target);
	
}
