package xyz.pixelatedw.MineMineNoMi3.api.quests;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.lists.ListQuests;

public class QuestProperties implements IExtendedEntityProperties 
{

	public final static String EXT_QUESTPROP_NAME = ID.PROJECT_ID + "_QuestIEEP";
	private final EntityPlayer thePlayer;
	
	private boolean hasPrimaryActive = false;
	
	private Quest[] questsList = new Quest[4];
	
	
	public QuestProperties(EntityPlayer entity) 
	{
		this.thePlayer = entity;	
	}
	
	public static final void register(EntityPlayer entity) 
	{
		entity.registerExtendedProperties(QuestProperties.EXT_QUESTPROP_NAME, new QuestProperties(entity));
	}

	public static final QuestProperties get(EntityPlayer entity) 
	{
		return (QuestProperties) entity.getExtendedProperties(EXT_QUESTPROP_NAME);
	}

	public void saveNBTData(NBTTagCompound compound)
	{
		NBTTagCompound props = new NBTTagCompound();
		
		props.setBoolean("hasPrimaryActive", this.hasPrimaryActive);
			
		for(int i = 0; i < questsList.length; i++)
		{
			if(this.questsList[i] != null)
			{
				props.setString("inProgressQuest_" + i, this.questsList[i].getQuestID());
				props.setDouble("progressForQuest_" + i, this.questsList[i].getProgress());
			}
		}

		compound.setTag(EXT_QUESTPROP_NAME, props);
	}

	public void loadNBTData(NBTTagCompound compound)
	{
		NBTTagCompound props = (NBTTagCompound) compound.getTag(EXT_QUESTPROP_NAME);

		this.hasPrimaryActive = props.getBoolean("hasPrimaryActive");

		try
		{
			for(int i = 0; i < questsList.length; i++)
			{
				//System.out.println(props.getString("inProgressQuest_" + i));
				this.questsList[i] = (!props.getString("inProgressQuest_" + i).isEmpty() || QuestManager.instance().getQuestByNameFromList(ListQuests.allQuests, props.getString("inProgressQuest_" + i)) != null) ? QuestManager.instance().getQuestByNameFromList(ListQuests.allQuests, props.getString("inProgressQuest_" + i)).getClass().newInstance() : null;
				if(this.questsList[i] != null)
					this.questsList[i].setProgress(this.thePlayer, props.getDouble("progressForQuest_" + i));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	public void init(Entity entity, World world) {}


	public void addQuestInTracker(Quest quest)
	{
		if((quest.isPrimary() && !this.hasPrimaryActive) || !quest.isPrimary())
		{
			for(int i = 0; i < questsList.length; i++)
			{
				if(this.questsList[i] == null)
				{
					if(quest.isPrimary())
						this.hasPrimaryActive = true;
					this.questsList[i] = quest;
					break;
				}
			}
		}
	}
	
	public void removeQuestFromTracker(Quest questTemplate)
	{
		for(int i = 0; i < questsList.length; i++)
		{
			if(this.questsList[i] != null && this.questsList[i].getQuestID().toLowerCase().equals(questTemplate.getQuestID().toLowerCase()))
			{
				if(questTemplate.isPrimary())
					this.hasPrimaryActive = false;
				this.questsList[i] = null;
				break;
			}
		}
	}
	
	public boolean hasQuestInTracker(Quest questTemplate)
	{
		for(int i = 0; i < questsList.length; i++)
		{
			if(this.questsList[i] != null && this.questsList[i].getQuestID().toLowerCase().equals(questTemplate.getQuestID().toLowerCase()))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public Quest getQuestFromTracker(Quest questTemplate)
	{
		for(int i = 0; i < questsList.length; i++)
		{
			if(this.questsList[i] != null && this.questsList[i].getQuestID().toLowerCase().equals(questTemplate.getQuestID().toLowerCase()))
			{
				return this.questsList[i];
			}
		}
		
		return null;
	}
	
	public Quest getQuestIndexFromTracker(int index)
	{
		if(this.questsList[index] != null)
		{
			return this.questsList[index];
		}

		return null;
	}
	
	public Quest getPrimaryQuestFromTracker()
	{
		if(this.hasPrimaryActive)
		{
			for(int i = 0; i < questsList.length; i++)
			{
				if(this.questsList[i] != null && this.questsList[i].isPrimary())
				{
					return this.questsList[i];
				}
			}
		}
		
		return null;
	}
	
	public void alterQuestProgress(Quest questTemplate, double progress)
	{
		for(int i = 0; i < questsList.length; i++)
		{
			if(this.questsList[i] != null && this.questsList[i].getQuestID().toLowerCase().equals(questTemplate.getQuestID().toLowerCase()))
			{
				this.questsList[i].setProgress(this.thePlayer, progress);
				break;
			}
		}
	}
	
	public boolean hasPrimary()
	{
		return this.hasPrimaryActive;
	}
	
	public int questsInProgress()
	{
		int inProgress = 0;
		for(int i = 0; i < questsList.length; i++)
		{
			if(this.questsList[i] != null)
			{
				inProgress++;
			}
		}
		
		return inProgress;
	}
	
	public void clearQuestTracker()
	{
		for(int i = 0; i < questsList.length; i++)
		{
			if(this.questsList[i] != null)
			{
				this.questsList[i] = null;
			}
		}
	}

	

}
