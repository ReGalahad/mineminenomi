package xyz.pixelatedw.MineMineNoMi3.quests.questlines.swordsmanprogression;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.biome.BiomeGenBase;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.abilities.SwordsmanAbilities;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.quests.Quest;
import xyz.pixelatedw.MineMineNoMi3.api.quests.QuestProperties;
import xyz.pixelatedw.MineMineNoMi3.ieep.ExtendedEntityStats;
import xyz.pixelatedw.MineMineNoMi3.items.weapons.ItemCoreWeapon;
import xyz.pixelatedw.MineMineNoMi3.lists.ListQuests;
import xyz.pixelatedw.MineMineNoMi3.quests.EnumQuestlines;
import xyz.pixelatedw.MineMineNoMi3.quests.IHitCounterQuest;
import xyz.pixelatedw.MineMineNoMi3.quests.IKillQuest;
import xyz.pixelatedw.MineMineNoMi3.quests.IProgressionQuest;

public class QuestSwordsmanProgression04 extends Quest implements IHitCounterQuest, IProgressionQuest
{

	public String getQuestID()
	{
		return "swordsmanprogression04";	
	}
	
	public String getQuestName()
	{
		return "The Fruits of Training";
	}
	
	public String[] getQuestDescription()
	{
		return new String[] 
				{
					" For my last test I need to deal 25 critical hits.",
					"I need to focus on my movement and the enemy to",
					"successfully deal them.",
					"",
					"",
					"",
					""
				};
	}
	
	public void startQuest(EntityPlayer player)
	{
		WyHelper.sendMsgToPlayer(player, "<Swordsman Master> Now for the last test we must train your movement during combat. Deal 25 critical hits.");
		
		super.startQuest(player);
	}

	public void finishQuest(EntityPlayer player)
	{
		//WyHelper.sendMsgToPlayer(player, "<Swordsman Master> That's a really nice blade you have there, really strong indeed. Fine, I will train you, when you're ready come and talk with me again !");
		
		ExtendedEntityStats props = ExtendedEntityStats.get(player);
		
		props.addRacialAbility(SwordsmanAbilities.SANBYAKUROKUJUPOUNDHO);
		
		super.finishQuest(player);
	}

	public boolean canStart(EntityPlayer player)
	{
		ExtendedEntityStats props = ExtendedEntityStats.get(player);
		QuestProperties questProps = QuestProperties.get(player);
		
		boolean flag1 = !props.getFightStyle().equals(ID.FSTYLE_SWORDSMAN) || !questProps.hasQuestCompleted(ListQuests.swordsmanProgression03);
		
		if(flag1)
			return false;

		return true;
	}

	public double getMaxProgress()
	{
		return 25;
	}

	public boolean isPrimary()
	{
		return true;
	}

	public EnumQuestlines getQuestLine()
	{
		return EnumQuestlines.SWORDSMANPROGRESSION;
	}

	public boolean isRepeatable()
	{
		return false;
	}

	public boolean checkHit(EntityPlayer player, EntityLivingBase target, DamageSource source)
	{
		ItemStack heldItem = player.getHeldItem();
		
		boolean flag = player.fallDistance > 0.0F && !player.onGround && !player.isOnLadder() && !player.isInWater() && !player.isRiding() && heldItem != null && (heldItem.getItem() instanceof ItemCoreWeapon || heldItem.getItem() instanceof ItemSword);

        if (flag) 
        	return true;

		return false;
	}

}
