package xyz.pixelatedw.mineminenomi.abilities.zushi;

import java.util.List;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;

public class JigokuTabiAbility extends ContinuousAbility
{
	public static final JigokuTabiAbility INSTANCE = new JigokuTabiAbility();

	public JigokuTabiAbility()
	{
		super("Jigoku Tabi", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(10);
		this.setThreshold(20);
		this.setDescription("Causes a powerful downward force of gravity sending the enemies down in a crater.");

		this.duringContinuityEvent = this::duringContinuityEvent;
	}

	private void duringContinuityEvent(PlayerEntity player, int activeTimer)
	{
		List<LivingEntity> targets = WyHelper.<LivingEntity>getEntitiesNear(player.getPosition(), player.world, 10);
		targets.remove(player);

		for (LivingEntity entity : targets)
		{
			entity.setMotion(0, entity.getMotion().y - 5, 0);
			entity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 100, 10));

			if (++activeTimer % 100 == 0)
			{
				entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 10);
				for (int x = -2; x < 2; x++)
				{
					for (int z = -2; z < 2; z++)
					{
						int posX = (int) entity.posX + x;
						int posY = (int) entity.posY - 1;
						int posZ = (int) entity.posZ + z;

						AbilityHelper.placeBlockIfAllowed(player.world, posX, posY, posZ, Blocks.AIR, "all", "restricted", "ignore liquid");
					}
				}
			}
		}
	}
}