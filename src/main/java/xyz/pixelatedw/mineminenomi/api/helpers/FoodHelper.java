package xyz.pixelatedw.mineminenomi.api.helpers;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class FoodHelper {
    public static boolean canBeCooked(ItemStack stack)
    {
        if(stack.getItem().equals(Items.CHICKEN))
            return true;
        else if (stack.getItem().equals(Items.PORKCHOP))
            return true;
        else if (stack.getItem().equals(Items.SALMON))
            return true;
        else if (stack.getItem().equals(Items.COD))
            return true;
        else if (stack.getItem().equals(Items.RABBIT))
            return true;
        else if (stack.getItem().equals(Items.BEEF))
            return true;
        else if (stack.getItem().equals(Items.MUTTON))
            return true;
        else if (stack.getItem().equals(Items.POTATO))
            return true;


        return false;
    }

    public static ItemStack cookStack(ItemStack stack)
    {
        Item cookedItem = null;

        if(stack.getItem().equals(Items.CHICKEN))
            cookedItem = Items.COOKED_CHICKEN;
        else if (stack.getItem().equals(Items.PORKCHOP))
            cookedItem = Items.COOKED_PORKCHOP;
        else if (stack.getItem().equals(Items.SALMON))
            cookedItem = Items.COOKED_SALMON;
        else if (stack.getItem().equals(Items.COD))
            cookedItem = Items.COOKED_COD;
        else if (stack.getItem().equals(Items.RABBIT))
            cookedItem = Items.COOKED_RABBIT;
        else if (stack.getItem().equals(Items.BEEF))
            cookedItem = Items.COOKED_BEEF;
        else if (stack.getItem().equals(Items.MUTTON))
            cookedItem = Items.COOKED_MUTTON;
        else if (stack.getItem().equals(Items.POTATO))
            cookedItem = Items.BAKED_POTATO;

        return new ItemStack(cookedItem, stack.getCount());
    }
}
