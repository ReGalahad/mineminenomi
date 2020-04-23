package xyz.pixelatedw.mineminenomi.api.protection;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.block.Block;

public class BlockProtectionRule
{

	private boolean bypassGriefRule = false;
	private boolean banListedBlocks = false;
	private List<Block> blocks = new ArrayList<Block>();
	private List<Block> bannedBlocks = new ArrayList<Block>();

	public BlockProtectionRule(BlockProtectionRule... rules)
	{
		for(BlockProtectionRule rule : rules)
		{
			this.blocks.addAll(rule.getApprovedBlocks());
			this.bannedBlocks.addAll(rule.getBannedBlocks());
		}
		
		for(Block block : this.bannedBlocks)
		{
			if(this.blocks.contains(block))
				this.blocks.remove(block);
		}
	}
	
	public List<Block> getBannedBlocks()
	{
		return this.bannedBlocks;
	}
	
	public BlockProtectionRule addBannedBlocks(Block... blocks)
	{
		this.bannedBlocks.addAll(Lists.newArrayList(blocks));
		return this;
	}
	
	public List<Block> getApprovedBlocks()
	{
		return this.blocks;
	}
	
	public BlockProtectionRule addApprovedBlocks(Block... blocks)
	{
		this.blocks.addAll(Lists.newArrayList(blocks));
		return this;
	}
	
	public boolean getBypassGriefRule()
	{
		return this.bypassGriefRule;
	}
	
	public BlockProtectionRule setBypassGriefRule()
	{
		this.bypassGriefRule = true;
		return this;
	}
	
	public boolean getBanListedBlocks()
	{
		return this.banListedBlocks;
	}
	
	public BlockProtectionRule setBanListedBlocks()
	{
		this.banListedBlocks = true;
		return this;
	}
}
