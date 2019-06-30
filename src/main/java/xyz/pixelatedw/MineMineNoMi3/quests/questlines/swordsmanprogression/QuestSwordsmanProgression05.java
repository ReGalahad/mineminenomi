package xyz.pixelatedw.MineMineNoMi3.quests.questlines.swordsmanprogression;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import xyz.pixelatedw.MineMineNoMi3.api.WyHelper;
import xyz.pixelatedw.MineMineNoMi3.api.math.WyMathHelper;
import xyz.pixelatedw.MineMineNoMi3.api.network.PacketQuestSync;
import xyz.pixelatedw.MineMineNoMi3.api.network.WyNetworkHelper;
import xyz.pixelatedw.MineMineNoMi3.api.quests.Quest;
import xyz.pixelatedw.MineMineNoMi3.api.quests.QuestProperties;
import xyz.pixelatedw.MineMineNoMi3.data.ExtendedEntityData;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.bandits.BanditData;
import xyz.pixelatedw.MineMineNoMi3.entities.mobs.bandits.EntityBandit;
import xyz.pixelatedw.MineMineNoMi3.helpers.QuestLogicHelper;
import xyz.pixelatedw.MineMineNoMi3.lists.ListMisc;
import xyz.pixelatedw.MineMineNoMi3.lists.ListQuests;
import xyz.pixelatedw.MineMineNoMi3.packets.PacketQuestObjectiveSpawn;
import xyz.pixelatedw.MineMineNoMi3.quests.EnumQuestlines;
import xyz.pixelatedw.MineMineNoMi3.quests.IInteractQuest;
import xyz.pixelatedw.MineMineNoMi3.quests.IKillQuest;
import xyz.pixelatedw.MineMineNoMi3.quests.IProgressionQuest;

public class QuestSwordsmanProgression05 extends Quest implements IInteractQuest, IKillQuest, IProgressionQuest
{
	
	private int questPhase = 0;

	@Override
	public String getQuestID()
	{
		return "swordsmanprogression05";	
	}
	
	@Override
	public String getQuestName()
	{
		return "Mysterious Note";
	}
	
	@Override
	public String[] getQuestDescription()
	{
		return new String[] 
				{
					"",
					"",
					"",
					"",
					"",
					"",
					""
				};
	}
	
	@Override
	public void startQuest(EntityPlayer player)
	{
		WyHelper.sendMsgToPlayer(player, I18n.format("quest." + this.getQuestID() + ".started"));	

		for (int i = 0; i < 6; i++)
		{
			EntityBandit target = new EntityBandit(player.worldObj);
			double posX = player.posX + WyMathHelper.randomWithRange(-20, 20);
			double posZ = player.posZ + WyMathHelper.randomWithRange(-20, 20);

			target.setLocationAndAngles(posX, player.posY, posZ, 0, 0);
			target.setOwner(player);
			target.setActive(true);
			
			if (!player.worldObj.isRemote)
				player.worldObj.spawnEntityInWorld(target);
		}
		
		WyNetworkHelper.sendToAll(new PacketQuestObjectiveSpawn(player.getEntityId()));
	
		this.extraData = new NBTTagCompound();	
		this.extraData.setInteger("phase", this.questPhase);
		
		super.startQuest(player);
	}
	
	@Override
	public boolean canStart(EntityPlayer player)
	{
		ExtendedEntityData props = ExtendedEntityData.get(player);
		QuestProperties questProps = QuestProperties.get(player);
		
		boolean flagSwordsman = props.isSwordsman();
		boolean flagPrevQuest = questProps.hasQuestCompleted(ListQuests.swordsmanProgression04);
		
		if(flagSwordsman && flagPrevQuest)
			return true;
		
		return false;
	}
	
	@Override
	public void finishQuest(EntityPlayer player)
	{
		WyHelper.sendMsgToPlayer(player, I18n.format("quest." + this.getQuestID() + ".completed"));
		
		ItemStack itemStack = QuestLogicHelper.getQuestItemStack(player.inventory, this.getQuestID());
		
		WyHelper.removeStackFromInventory(player, itemStack);
		
		super.finishQuest(player);
	}

	@Override
	public boolean isFinished(EntityPlayer player)
	{
		this.questPhase = this.extraData.getInteger("phase");
		
		boolean flagQuestStateKill = this.questPhase == 0;
		boolean flagNearbyMobs = WyHelper.getEntitiesNear(player, 20, BanditData.class).size() > 0;
		boolean flagQuestComplete = this.getProgress() >= this.getMaxProgress();
		boolean flagQuestStateInteract = this.questPhase == 1;
		
		if(flagNearbyMobs)
		{
			if (!player.worldObj.isRemote)
				WyHelper.sendMsgToPlayer(player, "<Dojo Sensei> There are still some bandits nearby!");		
			
			return false;
		}
		
		if (flagQuestStateKill && !flagQuestComplete)
		{
			if (!player.worldObj.isRemote)
				WyHelper.sendMsgToPlayer(player, "<Dojo Sensei> No doubt they came here for this note. You must decipher it, a librarian will probably be able to crack this code.");
				
			ItemStack mysteriousNote = new ItemStack(ListMisc.Note);
			mysteriousNote.setStackDisplayName("Mysterious Note");
			
			mysteriousNote.getTagCompound().setString("ForQuest", this.getQuestID());
			
			// Adding lore
			NBTTagCompound questLore = new NBTTagCompound();
			questLore.setString("lore1", WyMathHelper.shuffleArray("Transport Content :"));
			questLore.setString("lore2", WyMathHelper.shuffleArray("* 100 TNT Blocks"));
			questLore.setString("lore3", WyMathHelper.shuffleArray("* 50 Gunpowder"));
			questLore.setString("lore4", "");
			questLore.setString("lore5", WyMathHelper.shuffleArray("Delivered to :"));					
			questLore.setString("lore6", WyMathHelper.shuffleArray("X:0 Y:0 Z:0"));	
			questLore.setString("lore7", WyMathHelper.shuffleArray("- Simple Text Name"));
			
			mysteriousNote.getTagCompound().setTag("QuestLore", questLore);
						
			player.inventory.addItemStackToInventory(mysteriousNote);
			
			this.extraData.setInteger("phase", 1);
		}
		else if(flagQuestStateInteract && flagQuestComplete)
		{
			if (!player.worldObj.isRemote)
			{
				ItemStack itemStack = QuestLogicHelper.getQuestItemStack(player.inventory, this.getQuestID());
				
				if(itemStack != null && itemStack.hasTagCompound())
				{
					NBTTagCompound questLore = (NBTTagCompound) itemStack.getTagCompound().getTag("QuestLore");
					
					if(questLore == null)
						return false;
					
					itemStack.setStackDisplayName("Deciphered Note");
					
					// Adding deciphered lore
					questLore.setString("lore1", "Transport Content :");
					questLore.setString("lore2", "* 100 TNT Blocks");
					questLore.setString("lore3", "* 50 Gunpowder");
					questLore.setString("lore4", "");
					questLore.setString("lore5", "Delivered to :");					
					questLore.setString("lore6", "X:0 Y:0 Z:0");	
					questLore.setString("lore7", "- Simple Text Name");
					
					itemStack.getTagCompound().setTag("QuestLore", questLore);
				}
			}
			return true;
		}

		return false;
	}

	@Override
	public double getMaxProgress()
	{
		return 6;
	}

	@Override
	public boolean isPrimary()
	{
		return true;
	}

	@Override
	public EnumQuestlines getQuestLine()
	{
		return EnumQuestlines.SWORDSMAN_PROGRESSION;
	}

	@Override
	public boolean isRepeatable()
	{
		return false;
	}

	@Override
	public boolean isTarget(EntityPlayer player, EntityLivingBase target)
	{
		this.questPhase = this.extraData.getInteger("phase");
		
		if(questPhase == 0)
		{
			boolean flagMob = target instanceof BanditData;
			boolean flagCompletion = this.getProgress() < this.getMaxProgress() - 1;
			
			if(flagMob && flagCompletion)
				return true;
		}
		else if(questPhase == 1)
		{
			boolean flagMob = target instanceof EntityVillager;
			boolean flagLibrarian = flagMob && ((EntityVillager) target).getProfession() == 1;
			boolean flagIncomplete = this.getProgress() < this.getMaxProgress();
			
			if(flagMob && flagLibrarian && flagIncomplete)
			{
				this.setProgress(player, this.getMaxProgress());
				WyNetworkHelper.sendTo(new PacketQuestSync(QuestProperties.get(player)), (EntityPlayerMP) player);
				target.playSound("mob.villager.yes", 1, 1);
				this.isFinished(player);
				return true;
			}
		}
		
		return false;
	}

}