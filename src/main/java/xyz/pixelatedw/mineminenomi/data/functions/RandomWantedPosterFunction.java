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
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.wypi.APIConfig;

public class RandomWantedPosterFunction extends LootFunction
{
	private HashMap<String, ItemStack> wantedPosters = new HashMap<String, ItemStack>();
	private long lastPackageOpened = 0;
	
	protected RandomWantedPosterFunction(ILootCondition[] conditionsIn)
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

	public static class Serializer extends LootFunction.Serializer<RandomWantedPosterFunction>
	{
		public Serializer()
		{
			super(new ResourceLocation(APIConfig.PROJECT_ID + ":random_wanted_poster"), RandomWantedPosterFunction.class);
		}

		@Override
		public void serialize(JsonObject object, RandomWantedPosterFunction functionClazz, JsonSerializationContext serializationContext)
		{
			super.serialize(object, functionClazz, serializationContext);
		}

		@Override
		public RandomWantedPosterFunction deserialize(JsonObject object, JsonDeserializationContext deserializationContext, ILootCondition[] conditionsIn)
		{
			return new RandomWantedPosterFunction(conditionsIn);
		}
	}
}
