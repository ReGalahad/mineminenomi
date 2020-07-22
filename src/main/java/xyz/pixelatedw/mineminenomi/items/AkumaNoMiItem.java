package xyz.pixelatedw.mineminenomi.items;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.item.minecart.HopperMinecartEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Foods;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.HopperTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.EnumFruitType;
import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.mineminenomi.init.ModValues;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncDevilFruitPacket;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.abilities.PassiveAbility;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.network.WyNetwork;
import xyz.pixelatedw.wypi.network.packets.server.SSyncAbilityDataPacket;

public class AkumaNoMiItem extends Item
{
	private String name;
	public EnumFruitType type;
	public Ability[] abilities;

	public AkumaNoMiItem(String name, int tier, EnumFruitType type, Ability... abilitiesArray)
	{
		super(new Item.Properties().group(ModCreativeTabs.DEVIL_FRUITS).maxStackSize(1).food(Foods.APPLE));
		this.name = name;
		this.type = type;
		this.abilities = abilitiesArray;

		if (tier == 1)
			DevilFruitHelper.tier1Fruits.add(this);
		else if (tier == 2)
			DevilFruitHelper.tier2Fruits.add(this);
		else if (tier == 3)
			DevilFruitHelper.tier3Fruits.add(this);

		if (this.type == EnumFruitType.LOGIA)
			ModValues.logias.add(this);
		ModValues.devilfruits.add(this);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
	{
		player.setActiveHand(hand);
		return new ActionResult<>(ActionResultType.SUCCESS, player.getHeldItem(hand));
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack itemStack, World world, LivingEntity livingEntity)
	{
		if (!(livingEntity instanceof PlayerEntity))
			return itemStack;

		PlayerEntity player = (PlayerEntity) livingEntity;

		IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
		IEntityStats entityStatsProps = EntityStatsCapability.get(player);
		IAbilityData abilityDataProps = AbilityDataCapability.get(player);
		ExtendedWorldData worldData = ExtendedWorldData.get(world);
		
		String eatenFruit = DevilFruitHelper.getDevilFruitKey(this);

		boolean hasYami = DevilFruitHelper.hasDevilFruit(player, ModAbilities.YAMI_YAMI_NO_MI);

		boolean flag1 = !WyHelper.isNullOrEmpty(devilFruitProps.getDevilFruit()) && !devilFruitProps.hasYamiPower() && !hasYami;
		boolean flag2 = devilFruitProps.hasYamiPower() && !eatenFruit.equalsIgnoreCase(devilFruitProps.getDevilFruit()) && !devilFruitProps.getDevilFruit().equalsIgnoreCase("yamidummy");
		boolean flag3 = !CommonConfig.instance.isYamiPowerEnabled() && !WyHelper.isNullOrEmpty(devilFruitProps.getDevilFruit()) && (hasYami || !eatenFruit.equalsIgnoreCase(devilFruitProps.getDevilFruit()));
		
		if (flag1 || flag2 || flag3)
		{
			player.attackEntityFrom(DamageSource.WITHER, player.getMaxHealth());
			itemStack.shrink(1);
			return itemStack;
		}

		if (this.type == EnumFruitType.LOGIA)
			devilFruitProps.setLogia(true);

		if (!hasYami)
		{
			if(!WyHelper.isNullOrEmpty(devilFruitProps.getDevilFruit()) && eatenFruit.equalsIgnoreCase("yami_yami"))
				devilFruitProps.setYamiPower(true);
			devilFruitProps.setDevilFruit(eatenFruit);
		}
		else
		{
			devilFruitProps.setLogia(false);
			devilFruitProps.setYamiPower(true);

			if (WyHelper.isNullOrEmpty(devilFruitProps.getDevilFruit()))
				devilFruitProps.setDevilFruit("yamidummy");
			else if(devilFruitProps.getDevilFruit().equalsIgnoreCase("yamidummy") || hasYami)
				devilFruitProps.setDevilFruit(eatenFruit);
		}

		if (DevilFruitHelper.hasDevilFruit(player, ModAbilities.HITO_HITO_NO_MI) && !player.world.isRemote)
		{
			WyHelper.sendMsgToPlayer(player, "You've gained some enlightenment");
			if (entityStatsProps.isFishman())
			{
				entityStatsProps.setRace(ModValues.HUMAN);

				// abilityDataProps.clearHotbarFromList(player, FishKarateAbilities.abilitiesArray);
				AbilityHelper.validateStyleMoves(player);
				AbilityHelper.validateRacialMoves(player);
				// ModNetwork.sendTo(new PacketAbilityDataSync(abilityDataProps), (ServerPlayerEntity) player);
			}
		}

		if (!DevilFruitHelper.hasDevilFruit(player, ModAbilities.YOMI_YOMI_NO_MI))
		{
			for (Ability a : abilities)
				if (!AbilityHelper.verifyIfAbilityIsBanned(a) && abilityDataProps.getUnlockedAbility(a) == null)
					abilityDataProps.addUnlockedAbility(a);
			if (!player.world.isRemote)
			{
				WyNetwork.sendTo(new SSyncDevilFruitPacket(player.getEntityId(), devilFruitProps), player);
				WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), abilityDataProps), player);
			}
		}

		if(CommonConfig.instance.hasOneFruitPerWorldSimpleLogic())
			worldData.addAteDevilFruit(player, this);
		itemStack.shrink(1);
		return itemStack;
	}

	@Override
	public void addInformation(ItemStack itemStack, @Nullable World world, List<ITextComponent> list, ITooltipFlag par4)
	{
		for (int i = 0; i < this.abilities.length; i++)
			if (!AbilityHelper.verifyIfAbilityIsBanned(this.abilities[i]) && this.abilities[i] != null && !(this.abilities[i] instanceof PassiveAbility))
				list.add(new StringTextComponent(TextFormatting.GRAY + I18n.format("ability." + APIConfig.PROJECT_ID + "." + WyHelper.getResourceName(this.abilities[i].getName()))));

		list.add(new StringTextComponent(""));
		list.add(new StringTextComponent(this.type.getColor() + this.type.getName()));
	}

	@Override
	public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity)
	{
		if(entity.world.isRemote)
			return false;

		ExtendedWorldData worldData = ExtendedWorldData.get(entity.world);

		if (entity.isBurning())
		{
			entity.remove();
			worldData.removeDevilFruitInWorld(this);
		}

		if (entity.getPosition().getY() < -1)
		{
			entity.remove();
			worldData.removeDevilFruitInWorld(this);
		}

		boolean nearHopper = false;
		
		List<BlockPos> blockPosList = WyHelper.getNearbyTileEntities(entity.getPosition(), entity.world, 1);
		
		for (BlockPos pos : blockPosList)
		{
			TileEntity te = entity.world.getTileEntity(pos);

			if (te instanceof HopperTileEntity)
			{
				nearHopper = true;
				break;
			}
		}
		
		List<Entity> hopperMinecarts = WyHelper.getEntitiesNear(entity.getPosition(), entity.world, 0.5, HopperMinecartEntity.class);

		if(hopperMinecarts.size() > 0)
			nearHopper = true;
		
		if(nearHopper) 
		{
			entity.remove();
			PlayerEntity thrower = entity.world.getPlayerByUuid(entity.getThrowerId());
			if(thrower != null)
				thrower.inventory.addItemStackToInventory(stack);
			else
				worldData.removeDevilFruitInWorld(this);
		}

		return false;
	}

	public EnumFruitType getType()
	{
		return this.type;
	}

	public String getDevilFruitName()
	{
		return this.name;
	}
}
