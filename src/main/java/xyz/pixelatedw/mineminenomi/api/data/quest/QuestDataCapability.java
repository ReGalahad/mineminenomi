package xyz.pixelatedw.mineminenomi.api.data.quest;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.data.CapabilityProviderSerializable;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.init.ModQuests;

public class QuestDataCapability
{
	@CapabilityInject(IQuestData.class)
	public static final Capability<IQuestData> INSTANCE = null;

	public static void register()
	{
		CapabilityManager.INSTANCE.register(IQuestData.class, new Capability.IStorage<IQuestData>()
		{
			@Override
			public INBT writeNBT(Capability<IQuestData> capability, IQuestData instance, Direction side)
			{
				CompoundNBT props = new CompoundNBT();

				int i = 0;
				CompoundNBT inprogressQuestNBT = new CompoundNBT();
				for (Quest quest : instance.getInProgressQuests())
				{
					CompoundNBT questNBT = new CompoundNBT();
					questNBT.putString("quest_id_" + i, quest.getQuestId());
					questNBT.putDouble("progress_" + i, quest.getProgress());
					if (quest.getExtraData() != null)
						questNBT.put("extra_data_" + i, quest.getExtraData());

					inprogressQuestNBT.put("inprogress_quest_" + i, questNBT);
					i++;
				} 
				props.put("inprogress_quests", inprogressQuestNBT);

				i = 0;
				CompoundNBT completedQuestNBT = new CompoundNBT();
				for (String questId : instance.getCompletedQuests())
				{
					completedQuestNBT.putString("quest_id_" + i, questId);
					i++;
				}
				props.put("completed_quests", completedQuestNBT);

				return props;
			}

			@Override
			public void readNBT(Capability<IQuestData> capability, IQuestData instance, Direction side, INBT nbt)
			{
				CompoundNBT props = (CompoundNBT) nbt;

				instance.clearInProgressQuests();
				instance.clearCompletedQuests();
				
				if (props.getCompound("inprogress_quests") != null)
				{
					CompoundNBT inprogressQuestsNBT = props.getCompound("inprogress_quests");

					for (int i = 0; i < inprogressQuestsNBT.keySet().size(); i++)
					{
						if (inprogressQuestsNBT.getCompound("inprogress_quest_" + i) != null)
						{
							CompoundNBT questNBT = inprogressQuestsNBT.getCompound("inprogress_quest_" + i);
							String questId = questNBT.getString("quest_id_" + i);
							double questProgress = questNBT.getDouble("progress_" + i);
							CompoundNBT questExtraDataNBT = null;
							if (questNBT.getCompound("extra_data_" + i) != null)
								questExtraDataNBT = questNBT.getCompound("extra_data_" + i);

							Quest quest = ModQuests.getAllQuests().get(questId);

							if (quest != null)
							{
								quest.setProgress(questProgress);
								quest.setExtraData(questExtraDataNBT);
								instance.addInProgressQuest(quest);
							}
						}
					}
				}

				if (props.getCompound("completed_quests") != null)
				{
					CompoundNBT completedQuestsNBT = props.getCompound("completed_quests");

					for (int i = 0; i < completedQuestsNBT.keySet().size(); i++)
					{
						String questId = completedQuestsNBT.getString("quest_id_" + i);

						if (WyHelper.isNullOrEmpty(questId))
							continue;

						instance.addCompletedQuest(questId);
					}
				}
			}

		}, QuestDataBase::new);
	}

	public static IQuestData get(final LivingEntity entity)
	{
		return entity.getCapability(INSTANCE, null).orElse(new QuestDataBase());
	}

	public static ICapabilityProvider createProvider(final IQuestData data)
	{
		return new CapabilityProviderSerializable<>(INSTANCE, null, data);
	}
}
