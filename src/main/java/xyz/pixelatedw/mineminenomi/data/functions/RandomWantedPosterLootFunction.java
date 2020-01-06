package xyz.pixelatedw.mineminenomi.data.functions;

import java.util.HashMap;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootFunction;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import xyz.pixelatedw.mineminenomi.Env;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.helpers.ItemsHelper;

public class RandomWantedPosterLootFunction extends LootFunction
{
	private HashMap<String, ItemStack> wantedPosters = new HashMap<String, ItemStack>();
	private long lastPackageOpened = 0;
	
	protected RandomWantedPosterLootFunction(ILootCondition[] conditionsIn)
	{
		super(conditionsIn);
	}

	@Override
	protected ItemStack doApply(ItemStack stack, LootContext context)
	{
    	ExtendedWorldData worldData = ExtendedWorldData.get(context.getWorld());

		if( this.wantedPosters.size() >= 5 || Math.abs(context.getWorld().getGameTime() - this.lastPackageOpened) > 1000 )
			this.wantedPosters.clear();
		
		Object[] randomBounty = worldData.getRandomBounty();
		
		if(randomBounty == null)
			return ItemStack.EMPTY;
			
		String uuid = (String) randomBounty[0];
		long bounty = (long) randomBounty[1];
		
		if(this.wantedPosters.containsKey(uuid))
			return ItemStack.EMPTY;
		
		stack.getOrCreateTag().merge(ItemsHelper.setWantedData(context.getWorld(), uuid, bounty));

		this.wantedPosters.put(uuid, stack);
		this.lastPackageOpened = context.getWorld().getGameTime();
		
		return stack;
	}

	public static class Serializer extends LootFunction.Serializer<RandomWantedPosterLootFunction>
	{
		public Serializer()
		{
			super(new ResourceLocation(Env.PROJECT_ID + ":random_wanted_poster"), RandomWantedPosterLootFunction.class);
		}

		@Override
		public void serialize(JsonObject object, RandomWantedPosterLootFunction functionClazz, JsonSerializationContext serializationContext)
		{
			super.serialize(object, functionClazz, serializationContext);
		}

		@Override
		public RandomWantedPosterLootFunction deserialize(JsonObject object, JsonDeserializationContext deserializationContext, ILootCondition[] conditionsIn)
		{
			return new RandomWantedPosterLootFunction(conditionsIn);
		}
	}
}
