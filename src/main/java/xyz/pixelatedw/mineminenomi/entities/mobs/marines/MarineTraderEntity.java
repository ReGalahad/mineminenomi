package xyz.pixelatedw.mineminenomi.entities.mobs.marines;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.entities.TraderEntity;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModLootTables;
import xyz.pixelatedw.wypi.WyHelper;

public class MarineTraderEntity extends TraderEntity
{
	public MarineTraderEntity(World world)
	{
		super(ModEntities.MARINE_TRADER, world);
		
		this.textures = new String[] { "marine_trader1", "marine_trader2" };
	}

	@Override
	public boolean canTrade(PlayerEntity player)
	{
		IEntityStats props = EntityStatsCapability.get(player);
		return props.isMarine() || props.isBountyHunter();
	}

	@Override
	public ResourceLocation getTradeTable()
	{		
		if(WyHelper.randomDouble() < 0.1)
			return ModLootTables.GILGAMESH_TRADER_POOL;
		
		return ModLootTables.JACK_OF_ALL_TRADES_TRADER_POOL;
	}

	@Override
	public String getTradeFailMessage()
	{
		return new TranslationTextComponent(ModI18n.TRADER_NO_PIRATE).getFormattedText();
	}
}
