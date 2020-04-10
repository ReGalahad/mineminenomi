package xyz.pixelatedw.wypi.quests.objectives;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.nbt.CompoundNBT;
import xyz.pixelatedw.wypi.WyHelper;

public abstract class Objective
{
	private String id;
	private String title;
	private String description;
	private boolean isHidden;
	private boolean isOptional;
	private double maxProgress = 1;
	private double progress;
	
	private List<Objective> requirements = new ArrayList<Objective>();

	public Objective(String title)
	{
		this.title = title;
		this.id = WyHelper.getResourceName(title);
	}
	
	
	
	/*
	 * 	Setters and Getters
	 */
	
	public void setProgress(double progress)
	{
		if(progress <= this.getMaxProgress())
			this.progress = progress;
		else
			this.progress = this.getMaxProgress();
	}
	
	public void alterProgress(double progress)
	{
		if(this.progress + progress <= this.getMaxProgress())
			this.progress += progress;
		else
			this.progress = this.getMaxProgress();
	}
	
	public double getProgress()
	{
		return this.progress;
	}
	
	public void setMaxProgress(double progress)
	{
		this.maxProgress = progress;
	}
	
	public double getMaxProgress()
	{
		return this.maxProgress;
	}
	
	public void addRequirement(Objective objective)
	{
		if(!this.requirements.contains(objective))
			this.requirements.add(objective);
	}
	
	public void setDescription(String desc)
	{
		this.description = desc;
	}

	public void markHidden()
	{
		this.isHidden = true;
	}
	
	public String getId()
	{
		return this.id;
	}
	
	public boolean isHidden()
	{
		return this.isHidden;
	}
	
	public String getTitle()
	{
		return this.title;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	public boolean isComplete()
	{
		return this.progress >= this.maxProgress;
	}
	
	public boolean isLocked()
	{
		if(this.requirements.size() <= 0)
			return false;
		
		if(this.requirements.stream().allMatch(objective -> objective.isComplete()))
			return false;
		
		return true;
	}
	
	
	
	/*
	 *  Save / Load data
	 */
	
	public CompoundNBT save()
	{
		CompoundNBT nbt = new CompoundNBT();

		nbt.putString("id", this.getId());
		nbt.putBoolean("isHidden", this.isHidden);
		nbt.putDouble("progress", this.progress);

		return nbt;
	}
}
