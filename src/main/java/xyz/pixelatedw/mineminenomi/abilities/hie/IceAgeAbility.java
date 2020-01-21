package xyz.pixelatedw.mineminenomi.abilities.hie;

import java.util.List;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.abilities.effects.DFEffectHieSlowness;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability.Category;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;

public class IceAgeAbility extends Ability
{
	public static final Ability INSTANCE = new IceAgeAbility();

	public IceAgeAbility()
	{
		super("Ice Age", Category.DEVIL_FRUIT);
		this.setMaxCooldown(15);
		this.setDescription("Freezes a large area around the user and everyone inside of it.");

		this.onUseEvent = this::onUseEvent;
	}

	private void onUseEvent(PlayerEntity player, Ability ability)
	{
		for (int i = -15; i < 15; i++)
		{
			for (int j = -10; j < 10; j++)
			{
				for (int k = -15; k < 15; k++)
				{
					double posX = player.posX + i + (i < -WyMathHelper.randomWithRange(8, 12) || i > WyMathHelper.randomWithRange(8, 12) ? WyMathHelper.randomWithRange(-5, 5) : 0);
					double posY = player.posY + j;
					double posZ = player.posZ + k + (k < -WyMathHelper.randomWithRange(8, 12) || k > WyMathHelper.randomWithRange(8, 12) ? WyMathHelper.randomWithRange(-5, 5) : 0);
					
					WyHelper.placeBlockIfAllowed(player.world, posX, posY, posZ, Blocks.PACKED_ICE, "core", "foliage");
				}
			}
		}
		
		List<LivingEntity> list = WyHelper.<LivingEntity>getEntitiesNear(player.getPosition(), player.world, 15);
		list.remove(player);
		
		for (LivingEntity target : list)
		{
			new DFEffectHieSlowness(target, 200);
		}
	}
}
