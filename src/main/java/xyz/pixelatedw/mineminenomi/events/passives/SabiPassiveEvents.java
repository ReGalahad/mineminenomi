package xyz.pixelatedw.mineminenomi.events.passives;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.model.IHasArm;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderSpecificHandEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.abilities.sabi.RustTouchAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.MorphHelper;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.init.ModEnchantments;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.LogiaParticleEffect;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class SabiPassiveEvents
{
	public static final List<Item> IRON_ITEMS = Lists.newArrayList(ModWeapons.MARINE_SWORD, ModWeapons.PIRATE_CUTLASS, ModWeapons.BANDIT_KNIFE, Items.IRON_AXE, Items.IRON_BARS, Items.IRON_BLOCK, Items.IRON_BOOTS, Items.IRON_CHESTPLATE, Items.IRON_DOOR, Items.IRON_HELMET, Items.IRON_HOE, Items.IRON_HORSE_ARMOR, Items.IRON_INGOT, Items.IRON_LEGGINGS, Items.IRON_NUGGET, Items.IRON_ORE, Items.IRON_PICKAXE, Items.IRON_SHOVEL, Items.IRON_SWORD, Items.IRON_TRAPDOOR);
	
	@SubscribeEvent
	public static void onEntityAttack(LivingAttackEvent event)
	{
		if (!(event.getSource().getTrueSource() instanceof LivingEntity) || !(event.getEntityLiving() instanceof PlayerEntity))
			return;

		LivingEntity attacker = (LivingEntity) event.getSource().getTrueSource();
		PlayerEntity attacked = (PlayerEntity) event.getEntityLiving();
		IDevilFruit devilFruitProps = DevilFruitCapability.get(attacked);
		
		if (!devilFruitProps.getDevilFruit().equalsIgnoreCase("sabi_sabi"))
			return;

		ItemStack mainhandGear = attacker.getItemStackFromSlot(EquipmentSlotType.MAINHAND);	
		ItemStack offhandGear = attacker.getItemStackFromSlot(EquipmentSlotType.OFFHAND);	
		
		ItemStack toDamage = null;
		
		for(Item item : SabiPassiveEvents.IRON_ITEMS)
		{
			boolean isMainhand = mainhandGear.getItem() == item && EnchantmentHelper.getEnchantmentLevel(ModEnchantments.KAIROSEKI, mainhandGear) <= 0;
			boolean isOffhand = offhandGear.getItem() == item && EnchantmentHelper.getEnchantmentLevel(ModEnchantments.KAIROSEKI, offhandGear) <= 0;
			if(isMainhand || isOffhand)
			{
				toDamage = mainhandGear;
				break;
			}
		}
		
		if(toDamage != null)
		{
			toDamage.damageItem((toDamage.getMaxDamage() / 4) + 1, attacked, (entity) -> {});
			event.setCanceled(true);
			ParticleEffect effect = new LogiaParticleEffect(ModResources.RUST);	
			effect.spawn(attacked.world, attacked.posX, attacked.posY, attacked.posZ, 0, 0, 0);
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onHandRendering(RenderSpecificHandEvent event)
	{
		if (event.getHand() != Hand.MAIN_HAND)
			return;

		if (!event.getItemStack().isEmpty())
			return;

		PlayerEntity player = Minecraft.getInstance().player;

		IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
		IAbilityData abilityProps = AbilityDataCapability.get(player);

		if (!devilFruitProps.getDevilFruit().equalsIgnoreCase("sabi_sabi"))
			return;

		RustTouchAbility ability = abilityProps.getEquippedAbility(RustTouchAbility.INSTANCE);

		if (ability == null || !ability.isContinuous())
			return;

		event.setCanceled(true);
		MorphHelper.renderArmFirstPerson(event.getEquipProgress(), event.getSwingProgress(), HandSide.RIGHT, ModResources.RUST_TOUCH_ARM);
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public static void onEntityRendered(RenderLivingEvent.Post event)
	{
		if (!(event.getEntity() instanceof PlayerEntity))
			return;

		PlayerEntity player = (PlayerEntity) event.getEntity();
		LivingRenderer renderer = event.getRenderer();

		IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
		IAbilityData abilityProps = AbilityDataCapability.get(player);
			
		if (!devilFruitProps.getDevilFruit().equals("sabi_sabi"))
			return;
		
		RustTouchAbility ability = abilityProps.getEquippedAbility(RustTouchAbility.INSTANCE);
		
		if (ability == null || !ability.isContinuous())
			return;

		if (!(renderer.getEntityModel() instanceof IHasArm))
			return;

		MorphHelper.renderArmThirdPerson(event, player, ModResources.RUST_TOUCH_ARM);
	}
}
