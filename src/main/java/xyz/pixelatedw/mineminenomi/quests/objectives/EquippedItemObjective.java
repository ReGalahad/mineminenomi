package xyz.pixelatedw.mineminenomi.quests.objectives;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.quests.objectives.IEquipItemObjective;
import xyz.pixelatedw.wypi.quests.objectives.Objective;

public class EquippedItemObjective extends Objective implements IEquipItemObjective
{
	private Item itemTarget;
	private EquipmentSlotType slotTarget;

	public EquippedItemObjective(String title, int count, Item item, EquipmentSlotType slot)
	{
		super(title);
		this.setMaxProgress(count);
		this.itemTarget = item;
		this.slotTarget = slot;
	}

	@Override
	public boolean checkEquippedItem(PlayerEntity player, ItemStack stack)
	{
		return player.getItemStackFromSlot(this.slotTarget).getItem() == this.itemTarget;
	}

	@Override
	public String getLocalizedTitle() 
	{
		String objectiveKey = new TranslationTextComponent(String.format("quest.objective." + APIConfig.PROJECT_ID + ".%s", this.getId())).getKey();
		return new TranslationTextComponent(objectiveKey, new ItemStack(this.itemTarget).getDisplayName()).getFormattedText(); 
	}
}
