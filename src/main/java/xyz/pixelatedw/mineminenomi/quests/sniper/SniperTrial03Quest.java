package xyz.pixelatedw.mineminenomi.quests.sniper;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.abilities.sniper.RenpatsuNamariBoshiAbility;
import xyz.pixelatedw.mineminenomi.entities.mobs.quest.objectives.SniperTargetEntity;
import xyz.pixelatedw.mineminenomi.packets.server.SDespawnQuestObjectivePacket;
import xyz.pixelatedw.mineminenomi.quests.sniper.objectives.ArrowKillSniperTargetObjective;
import xyz.pixelatedw.mineminenomi.quests.sniper.objectives.HoldBowObjective;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.WyRegistry;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.client.CSyncAbilityDataPacket;
import xyz.pixelatedw.wypi.quests.Quest;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

public class SniperTrial03Quest extends Quest
{
	private Objective objective01 = new HoldBowObjective();
	private Objective objective02 = new ArrowKillSniperTargetObjective("Hit all 10 targets before they hit the ground", 10).addRequirement(this.objective01);

	private List<SniperTargetEntity> targets = new ArrayList<SniperTargetEntity>();
	private static final String RESET_DIALOGUE = WyRegistry.registerName("quest.sniper_trial_03.reset_dialogue", "<Bow Master> Look out for those targets!");

	public SniperTrial03Quest()
	{
		super("sniper_trial_03", "Trial: Renpatsu Namari Boshi");
		//this.addRequirements(ModQuests.SNIPER_TRIAL_01, ModQuests.SNIPER_TRIAL_02);
		this.addObjectives(this.objective01, this.objective02);
		
		this.onStartEvent = this::spawnTargets;
		this.onCompleteEvent = this::giveReward;
		this.shouldRestartEvent = this::shouldRestartEvent;
	}

	public boolean shouldRestartEvent(PlayerEntity player)
	{
		if(this.isComplete())
			return false;
				
		if(this.targets.size() <= 0)
			return true;
		
		boolean restart = false;
		
		for(SniperTargetEntity target : this.targets)
		{
			if(target == null || !target.isAlive())
			{			
				restart = true;
			}
		}
		
		if(restart)
		{
			for(SniperTargetEntity target : this.targets)
			{
				target.remove();
			}
			this.targets = new ArrayList<SniperTargetEntity>();
		}
		
		return restart;
	}
	
	private void spawnTargets(PlayerEntity player)
	{
		for (int i = 0; i < 10; i++)
		{
			SniperTargetEntity target = new SniperTargetEntity(player.world);
			double posX = player.posX + WyHelper.randomWithRange(-10, 10);
			double posY = player.posY + 30;
			double posZ = player.posZ + WyHelper.randomWithRange(-10, 10);
			target.setLocationAndAngles(posX, posY, posZ, 0, 0);
			
			player.world.addEntity(target);			
			this.targets.add(target);
			
			WyNetwork.sendToAll(new SDespawnQuestObjectivePacket(player.getUniqueID(), target.getEntityId()));
		}
		WyHelper.sendMsgToPlayer(player, new TranslationTextComponent(RESET_DIALOGUE).getFormattedText());
	}
	
	private void giveReward(PlayerEntity player)
	{
		IAbilityData props = AbilityDataCapability.get(player);
		
		props.addUnlockedAbility(RenpatsuNamariBoshiAbility.INSTANCE);
		WyNetwork.sendToServer(new CSyncAbilityDataPacket(props));
	}
}
