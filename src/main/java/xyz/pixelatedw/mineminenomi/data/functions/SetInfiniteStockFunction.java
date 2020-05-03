package xyz.pixelatedw.mineminenomi.data.functions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootFunction;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import xyz.pixelatedw.wypi.APIConfig;

public class SetInfiniteStockFunction extends LootFunction
{
	protected SetInfiniteStockFunction(ILootCondition[] conditionsIn)
	{
		super(conditionsIn);
	}

	@Override
	protected ItemStack doApply(ItemStack stack, LootContext context)
	{
		stack.getOrCreateTag().putBoolean("isInfinite", true);
		stack.setCount(1);
		return stack;
	}

	public static class Serializer extends LootFunction.Serializer<SetInfiniteStockFunction>
	{
		public Serializer()
		{
			super(new ResourceLocation(APIConfig.PROJECT_ID + ":infinite_stock"), SetInfiniteStockFunction.class);
		}

		@Override
		public void serialize(JsonObject object, SetInfiniteStockFunction functionClazz, JsonSerializationContext serializationContext)
		{
			super.serialize(object, functionClazz, serializationContext);
		}

		@Override
		public SetInfiniteStockFunction deserialize(JsonObject object, JsonDeserializationContext deserializationContext, ILootCondition[] conditionsIn)
		{
			return new SetInfiniteStockFunction(conditionsIn);
		}
	}

}
