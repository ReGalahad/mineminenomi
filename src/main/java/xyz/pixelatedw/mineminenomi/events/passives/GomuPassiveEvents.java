package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.NormalBulletProjectile;
import xyz.pixelatedw.mineminenomi.items.weapons.ClimaTactItem;
import xyz.pixelatedw.mineminenomi.items.weapons.CoreSwordItem;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID)
public class GomuPassiveEvents
{

	@SubscribeEvent
	public static void onEntityUpdate(LivingUpdateEvent event)
	{
		if (!(event.getEntityLiving() instanceof PlayerEntity))
			return;	
		
		PlayerEntity player = (PlayerEntity) event.getEntityLiving();
		IDevilFruit devilFruitProps = DevilFruitCapability.get(player);

		if (!devilFruitProps.getDevilFruit().equals("gomu_gomu"))
			return;
		
		player.fallDistance = 0;
	}

	@SubscribeEvent
	public static void onEntityHurt(LivingHurtEvent event) {
		if (!(event.getEntityLiving() instanceof PlayerEntity))
			return;

		PlayerEntity attacked = (PlayerEntity) event.getEntityLiving();
		IDevilFruit devilFruitProps = DevilFruitCapability.get(attacked);
		
		if (!devilFruitProps.getDevilFruit().equals("gomu_gomu"))
			return;

		if(!AbilityHelper.isNearbyKairoseki(attacked)) {
			DamageSource source = event.getSource();
			Entity trueSource = source.getTrueSource();
			Entity instantSource = source.getImmediateSource();

			float reduction = 0;

			if(trueSource != null) {
				if(trueSource instanceof LivingEntity) {
					ItemStack mainhandGear = ((LivingEntity) trueSource).getItemStackFromSlot(EquipmentSlotType.MAINHAND);
					if(getGomuDamagingItems(mainhandGear.getItem()) && !ItemsHelper.isKairosekiWeapon(mainhandGear))
						reduction = 0.8f;
				}
			}
			if(instantSource != null) {
				if(instantSource instanceof AbilityProjectileEntity) {
					boolean isPhysical = ((AbilityProjectileEntity) instantSource).getPhysical();
					if(isPhysical)
						reduction = 0.8f;
				}
			}

			if(source.equals(DamageSource.LIGHTNING_BOLT)) {
				reduction = 1f;
				event.setCanceled(true);
			}

			event.setAmount(event.getAmount() * (1 - reduction));
		}
	}

	@SubscribeEvent
	public static void onEntityAttackEvent(LivingAttackEvent event) {
		if (!(event.getEntityLiving() instanceof PlayerEntity))
			return;

		PlayerEntity attacked = (PlayerEntity) event.getEntityLiving();
		IDevilFruit devilFruitProps = DevilFruitCapability.get(attacked);

		if (!devilFruitProps.getDevilFruit().equals("gomu_gomu"))
			return;

		if(!AbilityHelper.isNearbyKairoseki(attacked)) {
			DamageSource source = event.getSource();
			Entity instantSource = source.getImmediateSource();

			if(instantSource instanceof NormalBulletProjectile)
			{
				event.setCanceled(true);
				((NormalBulletProjectile) instantSource).setThrower(attacked);
				((NormalBulletProjectile) instantSource).shoot(-instantSource.getMotion().x, -instantSource.getMotion().y, -instantSource.getMotion().z,0.8f, 20);
			}
		}
	}

	private static boolean getGomuDamagingItems(Item item) {
		if(item instanceof SwordItem || item instanceof PickaxeItem || item instanceof AxeItem || item instanceof TridentItem || item instanceof ClimaTactItem)
			return false;

		if(item instanceof CoreSwordItem)
			return ((CoreSwordItem) item).isBlunt();

		return true;
	}
}
