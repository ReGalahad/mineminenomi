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

public class FakeWeaponFunction extends LootFunction
{
	protected FakeWeaponFunction(ILootCondition[] conditionsIn)
	{
		super(conditionsIn);
	}

	@Override
	protected ItemStack doApply(ItemStack stack, LootContext context)
	{
		stack.getOrCreateTag().putBoolean("isClone", true);
		return stack;
	}

	public static class Serializer extends LootFunction.Serializer<FakeWeaponFunction>
	{
		public Serializer()
		{
			super(new ResourceLocation(APIConfig.PROJECT_ID + ":fake_weapon"), FakeWeaponFunction.class);
		}

		@Override
		public void serialize(JsonObject object, FakeWeaponFunction functionClazz, JsonSerializationContext serializationContext)
		{
			super.serialize(object, functionClazz, serializationContext);
		}

		@Override
		public FakeWeaponFunction deserialize(JsonObject object, JsonDeserializationContext deserializationContext, ILootCondition[] conditionsIn)
		{
			return new FakeWeaponFunction(conditionsIn);
		}
	}

}
