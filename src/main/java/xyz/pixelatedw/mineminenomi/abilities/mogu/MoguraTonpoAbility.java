package xyz.pixelatedw.mineminenomi.abilities.mogu;

import java.util.List;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.OreBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.entities.zoan.MoguHeavyZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.baku.BakuMunchParticleEffect;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;

public class MoguraTonpoAbility extends Ability
{
	public static final MoguraTonpoAbility INSTANCE = new MoguraTonpoAbility();

	private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(CoreBlockProtectionRule.INSTANCE, OreBlockProtectionRule.INSTANCE, FoliageBlockProtectionRule.INSTANCE); 
	private static final ParticleEffect PARTICLES = new BakuMunchParticleEffect();
	private int initialY;

	public MoguraTonpoAbility()
	{
		super("Mogura Tonpo", AbilityCategory.DEVIL_FRUIT);
		this.setMaxCooldown(15);

		this.onUseEvent = this::onUseEvent;
		this.duringCooldownEvent = this::duringCooldown;
	}

	private boolean onUseEvent(PlayerEntity player)
	{
		IDevilFruit props = DevilFruitCapability.get(player);

		if (!props.getZoanPoint().equalsIgnoreCase(MoguHeavyZoanInfo.FORM))
		{
			WyHelper.sendMsgToPlayer(player, new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NOT_ZOAN_FORM_SINGLE, this.getName(), MoguHeavyPointAbility.INSTANCE.getName()).getFormattedText());
			return false;
		}

		this.initialY = (int) player.posY;

		if (player.isSneaking())
		{
			for (int x = -1; x < 1; x++)
			{
				for (int y = 0; y < 10; y++)
				{
					for (int z = -1; z < 1; z++)
					{
						int posX = (int) player.posX + x;
						int posY = (int) player.posY - y;
						int posZ = (int) player.posZ + z;
						BlockPos pos = new BlockPos(posX, posY, posZ);
						
						player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 50, 100, false, false));
						player.addPotionEffect(new EffectInstance(Effects.HASTE, 400, 2, false, false));

						BlockState tempBlock = player.world.getBlockState(pos);
						if (AbilityHelper.placeBlockIfAllowed(player.world, posX, posY, posZ, Blocks.AIR, GRIEF_RULE))
						{
							player.inventory.addItemStackToInventory(new ItemStack(tempBlock.getBlock()));
							PARTICLES.spawn(player.world, posX, posY, posZ, 0, 0, 0);
						}
					}
				}
			}
		}
		else
		{
			Vec3d speed = WyHelper.propulsion(player, 4, 4);
			player.setMotion(speed.x, 0.3, speed.z);
			((ServerPlayerEntity) player).connection.sendPacket(new SEntityVelocityPacket(player));
		}

		return true;
	}

	private void duringCooldown(PlayerEntity player, int cooldownTimer)
	{
		if (this.canDealDamage() && player.posY >= this.initialY)
		{
			List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 1.6);
			list.remove(player);
			for (LivingEntity target : list)
				target.attackEntityFrom(DamageSource.causePlayerDamage(player), 8);
			for (BlockPos location : WyHelper.getNearbyBlocks(player, 3)) 
			{
				if (location.getY() >= player.posY) 
				{
					BlockState tempBlock = player.world.getBlockState(new BlockPos(location.getX(), location.getY(), location.getZ()));
					if (AbilityHelper.placeBlockIfAllowed(player.world, location.getX(), location.getY(), location.getZ(), Blocks.AIR, GRIEF_RULE)) 
					{
						player.inventory.addItemStackToInventory(new ItemStack(tempBlock.getBlock()));
						PARTICLES.spawn(player.world, location.getX(), location.getY(), location.getZ(), 0, 0, 0);
					}
				}
			}
		}
	}

	// Cooldown = 15*20 = 300
	// Damage Frame = 10*20 = 200
	// 300-200=100 ticks or 5 seconds of damage frames
	public boolean canDealDamage()
	{
		if (this.cooldown > 10 * 20)
			return true;
		return false;
	}
}
