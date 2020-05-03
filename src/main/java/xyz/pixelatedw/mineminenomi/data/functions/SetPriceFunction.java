package xyz.pixelatedw.mineminenomi.data.functions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.IRandomRange;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootFunction;
import net.minecraft.world.storage.loot.RandomRanges;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import xyz.pixelatedw.wypi.APIConfig;

public class SetPriceFunction extends LootFunction
{
	private IRandomRange range;

	protected SetPriceFunction(ILootCondition[] conditionsIn, IRandomRange rang)
	{
		super(conditionsIn);
		this.range = rang;
	}

	@Override
	protected ItemStack doApply(ItemStack stack, LootContext context)
	{
		stack.getOrCreateTag().putInt("price", this.range.generateInt(context.getRandom()));
		return stack;
	}

	public static class Serializer extends LootFunction.Serializer<SetPriceFunction>
	{
		public Serializer()
		{
			super(new ResourceLocation(APIConfig.PROJECT_ID + ":set_price"), SetPriceFunction.class);
		}

		@Override
		public void serialize(JsonObject object, SetPriceFunction functionClazz, JsonSerializationContext serializationContext)
		{
			super.serialize(object, functionClazz, serializationContext);
			object.add("price_range", RandomRanges.serialize(functionClazz.range, serializationContext));
		}

		@Override
		public SetPriceFunction deserialize(JsonObject object, JsonDeserializationContext deserializationContext, ILootCondition[] conditionsIn)
		{
			IRandomRange range = RandomRanges.deserialize(object.get("price_range"), deserializationContext);
			return new SetPriceFunction(conditionsIn, range);
		}
	}

}
