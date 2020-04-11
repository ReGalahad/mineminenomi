package xyz.pixelatedw.wypi.quests;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.registries.ForgeRegistryEntry;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

public abstract class Quest extends ForgeRegistryEntry<Quest>
{
	private String title;
	private String description;
	
	private List<Objective> objectives = new ArrayList<Objective>();
	
	public Quest(String id, String title)
	{
		this.title = title;
	}
	
	
	
	/*
	 *  Abstract methods
	 */
	
	public abstract boolean canStart(PlayerEntity player);
	
	
	/*
	 *  Methods
	 */
	
	@Nullable
	public Quest create()
	{
		try
		{
			return this.getClass().getConstructor().newInstance();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return null;
	}
	
	
	/*
	 *  Setters and Getters
	 */
	public void addObjectives(Objective... objectives)
	{
		for(Objective obj : objectives)
		{
			if(!this.objectives.contains(obj))
				this.objectives.add(obj);
		}
	}
	
	public void addObjective(Objective objective)
	{
		if(!this.objectives.contains(objective))
			this.objectives.add(objective);
	}
	
	public List<Objective> getObjectives()
	{
		return this.objectives;
	}
	
	public boolean isCompleted()
	{
		return this.objectives.stream().allMatch(objective -> objective.isComplete());
	}
	
	public double getProgress()
	{
		int maxProgress = this.objectives.size();
		int completed = this.objectives.stream().filter(objective -> objective.isComplete()).collect(Collectors.toList()).size();

		double progress = completed / (double) maxProgress;
		
		return progress;
	}
	
	public void setDescription(String desc)
	{
		this.description = desc;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	public String getId()
	{
		return WyHelper.getResourceName(this.title);
	}
	
	public String getTitle()
	{
		return this.title;
	}
	
	
	/*
	 *  Save / Load data
	 */
	
	public CompoundNBT save()
	{
		CompoundNBT nbt = new CompoundNBT();
		
		nbt.putString("id", this.getId());
		ListNBT objectivesData = new ListNBT();
		for(Objective obj : this.getObjectives())
		{
			objectivesData.add(obj.save());
		}
		nbt.put("objectives", objectivesData);
		
		return nbt;
	}
	
	public void load(CompoundNBT nbt)
	{
		ListNBT objectivesData = nbt.getList("objectives", Constants.NBT.TAG_COMPOUND);
		for (int i = 0; i < objectivesData.size(); i++)
		{
			CompoundNBT questData = objectivesData.getCompound(i);
			this.getObjectives().get(i).load(questData);
		}
	}
}
