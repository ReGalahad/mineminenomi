package xyz.pixelatedw.mineminenomi.items;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Foods;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.EnumFruitType;
import xyz.pixelatedw.mineminenomi.api.WyHelper;
import xyz.pixelatedw.mineminenomi.api.WyRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
import xyz.pixelatedw.mineminenomi.api.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.api.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.api.network.packets.client.CAbilityDataSyncPacket;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.helpers.DevilFruitsHelper;
import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
import xyz.pixelatedw.mineminenomi.init.ModNetwork;
import xyz.pixelatedw.mineminenomi.init.ModValues;
import xyz.pixelatedw.mineminenomi.packets.client.CDevilFruitSyncPacket;

public class AkumaNoMiItem extends Item
{
	private String name;
	public EnumFruitType type;
	public Ability[] abilities;

	public AkumaNoMiItem(String name, EnumFruitType type, Ability... abilitiesArray)
	{
		super(new Item.Properties().group(ModCreativeTabs.DEVIL_FRUITS).maxStackSize(1).food(Foods.APPLE));
		this.name = name;
		this.type = type;
		this.abilities = abilitiesArray;
		
		this.registerDevilFruit();
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
		if(!(livingEntity instanceof PlayerEntity))
			return itemStack;
		
		PlayerEntity player = (PlayerEntity) livingEntity;
	
		IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
		IEntityStats entityStatsProps = EntityStatsCapability.get(player);
		IAbilityData abilityDataProps = AbilityDataCapability.get(player);

		String eatenFruit = this.getDefaultTranslationKey().substring("item.mineminenomi.".length()).replace("_no_mi", "").replace(":", "").replace(".", "").replace(",", "").replace("model", "");

		boolean flag1 = !WyHelper.isNullOrEmpty(devilFruitProps.getDevilFruit()) && !devilFruitProps.hasYamiPower() && !eatenFruit.equalsIgnoreCase("yamiyami");
		boolean flag2 = devilFruitProps.hasYamiPower() && !eatenFruit.equalsIgnoreCase(devilFruitProps.getDevilFruit()) && !devilFruitProps.getDevilFruit().equalsIgnoreCase("yamidummy");
		boolean flag3 = !CommonConfig.instance.isYamiPowerEnabled() && !WyHelper.isNullOrEmpty(devilFruitProps.getDevilFruit()) && (eatenFruit.equalsIgnoreCase("yamiyami") || !eatenFruit.equalsIgnoreCase(devilFruitProps.getDevilFruit()));
		
		if (flag1 || flag2 || flag3)
		{
			player.attackEntityFrom(DamageSource.WITHER, Float.POSITIVE_INFINITY);
			itemStack.shrink(1);
			return itemStack;
		}

		if (this.type == EnumFruitType.LOGIA)
			devilFruitProps.setLogia(true);
		
		if (!eatenFruit.equalsIgnoreCase("yamiyami"))
			devilFruitProps.setDevilFruit(eatenFruit);
		else
		{
			devilFruitProps.setLogia(false);
			
			devilFruitProps.setYamiPower(true);
			
			if (WyHelper.isNullOrEmpty(devilFruitProps.getDevilFruit()))
				devilFruitProps.setDevilFruit("yamidummy");
		}

		if (eatenFruit.equalsIgnoreCase("hitohito") && !player.world.isRemote)
		{
			WyHelper.sendMsgToPlayer(player, "You've gained some enlightenment");
			if (entityStatsProps.isFishman())
			{
				entityStatsProps.setRace(ModValues.HUMAN);
				
				//abilityDataProps.clearHotbarFromList(player, FishKarateAbilities.abilitiesArray);
				DevilFruitsHelper.validateStyleMoves(player);
				DevilFruitsHelper.validateRacialMoves(player);
				//ModNetwork.sendTo(new PacketAbilityDataSync(abilityDataProps), (ServerPlayerEntity) player);
			}
		}

		if(!eatenFruit.equalsIgnoreCase("yomiyomi"))
		{
			for(Ability a : abilities)
				if(!DevilFruitsHelper.verifyIfAbilityIsBanned(a) && abilityDataProps.getAbility(a) == null)
					abilityDataProps.addAbility(a);
			if(player instanceof ServerPlayerEntity)
			{
				ModNetwork.sendTo(new CDevilFruitSyncPacket(devilFruitProps), (ServerPlayerEntity) player);
				ModNetwork.sendTo(new CAbilityDataSyncPacket(abilityDataProps), (ServerPlayerEntity)player);
			}		
		}
		
		itemStack.shrink(1);
		return itemStack;
	}
	
	@Override
	public void addInformation(ItemStack itemStack, @Nullable World world, List<ITextComponent> list, ITooltipFlag par4)
	{
		for (int i = 0; i < this.abilities.length; i++)
			if (!DevilFruitsHelper.verifyIfAbilityIsBanned(this.abilities[i]) && this.abilities[i] != null)
				list.add(new StringTextComponent(TextFormatting.GRAY + I18n.format("ability." + WyHelper.getResourceName(this.abilities[i].getName()) + ".name")));

		list.add(new StringTextComponent(""));
		list.add(new StringTextComponent(type.getColor() + type.getName()));
	}

	public EnumFruitType getType()
	{
		return type;
	}

	private void registerDevilFruit() 
	{
		if (this.type == EnumFruitType.LOGIA)
			ModValues.logias.add(this);
		ModValues.devilfruits.add(this);
		WyRegistry.registerItem(this, this.name);
	}
}
