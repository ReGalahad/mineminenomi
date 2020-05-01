package xyz.pixelatedw.mineminenomi.entities.mobs.marines;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.mobs.misc.TraderEntity;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModResources;

public class MarineTraderEntity extends TraderEntity
{
	public MarineTraderEntity(World world)
	{
		super(ModEntities.MARINE_TRADER, world);
		
		this.textures = new String[] { "trader1", "trader1", "trader1" };
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
		return ModResources.TRADER_WEAPONS;
	}

	@Override
	public String getTradeFailMessage()
	{
		return new TranslationTextComponent(ModI18n.TRADER_NO_PIRATE).getFormattedText();
	}
}
