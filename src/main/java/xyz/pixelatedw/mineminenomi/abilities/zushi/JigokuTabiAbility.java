package xyz.pixelatedw.mineminenomi.abilities.zushi;

import java.util.List;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SPlayEntityEffectPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.AllBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.LiquidBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.RestrictedBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.ContinuousAbility;

public class JigokuTabiAbility extends ContinuousAbility
{
	public static final JigokuTabiAbility INSTANCE = new JigokuTabiAbility();
	private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(AllBlockProtectionRule.INSTANCE, RestrictedBlockProtectionRule.INSTANCE, new BlockProtectionRule(LiquidBlockProtectionRule.INSTANCE).setBanListedBlocks()); 

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
			EffectInstance instance = new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 20, 0);
			entity.addPotionEffect(instance);
			((ServerPlayerEntity) player).connection.sendPacket(new SPlayEntityEffectPacket(entity.getEntityId(), instance));

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

						AbilityHelper.placeBlockIfAllowed(player.world, posX, posY, posZ, Blocks.AIR, GRIEF_RULE);
					}
				}
			}
		}
	}
}
