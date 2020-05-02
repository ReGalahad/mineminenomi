package xyz.pixelatedw.mineminenomi.api.helpers;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.pixelatedw.mineminenomi.abilities.cyborg.ColaOverdriveAbility;
import xyz.pixelatedw.mineminenomi.abilities.cyborg.CoupDeVentAbility;
import xyz.pixelatedw.mineminenomi.abilities.cyborg.FreshFireAbility;
import xyz.pixelatedw.mineminenomi.abilities.cyborg.RadicalBeamAbility;
import xyz.pixelatedw.mineminenomi.abilities.cyborg.StrongRightAbility;
import xyz.pixelatedw.mineminenomi.abilities.doctor.FailedExperimentAbility;
import xyz.pixelatedw.mineminenomi.abilities.doctor.FirstAidAbility;
import xyz.pixelatedw.mineminenomi.abilities.doctor.MedicBagExplosionAbility;
import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.KachiageHaisokuAbility;
import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.KarakusagawaraSeikenAbility;
import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.MurasameAbility;
import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.SamehadaShoteiAbility;
import xyz.pixelatedw.mineminenomi.abilities.fishmankarate.UchimizuAbility;
import xyz.pixelatedw.mineminenomi.abilities.rokushiki.GeppoAbility;
import xyz.pixelatedw.mineminenomi.abilities.rokushiki.KamieAbility;
import xyz.pixelatedw.mineminenomi.abilities.rokushiki.RankyakuAbility;
import xyz.pixelatedw.mineminenomi.abilities.rokushiki.ShiganAbility;
import xyz.pixelatedw.mineminenomi.abilities.rokushiki.SoruAbility;
import xyz.pixelatedw.mineminenomi.abilities.rokushiki.TekkaiAbility;
import xyz.pixelatedw.mineminenomi.abilities.sniper.KaenBoshiAbility;
import xyz.pixelatedw.mineminenomi.abilities.sniper.KemuriBoshiAbility;
import xyz.pixelatedw.mineminenomi.abilities.sniper.RenpatsuNamariBoshiAbility;
import xyz.pixelatedw.mineminenomi.abilities.sniper.SakuretsuSabotenBoshiAbility;
import xyz.pixelatedw.mineminenomi.abilities.supa.SparClawAbility;
import xyz.pixelatedw.mineminenomi.abilities.swordsman.OTatsumakiAbility;
import xyz.pixelatedw.mineminenomi.abilities.swordsman.SanbyakurokujuPoundHoAbility;
import xyz.pixelatedw.mineminenomi.abilities.swordsman.ShiShishiSonsonAbility;
import xyz.pixelatedw.mineminenomi.abilities.swordsman.YakkodoriAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.events.custom.DorikiEvent;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModQuests;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.data.quest.IQuestData;
import xyz.pixelatedw.wypi.data.quest.QuestDataCapability;
import xyz.pixelatedw.wypi.math.ISphere;
import xyz.pixelatedw.wypi.math.Sphere;
import xyz.pixelatedw.wypi.quests.Quest;

public class AbilityHelper
{
	private static String[][] zoanModels = new String[][]
		{
				{
						"ushi_ushi_bison", "bison"
				},
				{
						"tori_tori_phoenix", "phoenix"
				},
				{
						"ushi_ushi_giraffe", "giraffe"
				},
		};

	public static String[] flyingFruits = new String[]
		{
				"gasugasu", "sunasuna", "mokumoku"
		};

	
	public static boolean placeBlockIfAllowed(World world, double posX, double posY, double posZ, Block toPlace, BlockProtectionRule rule)
	{
		return placeBlockIfAllowed(world, posX, posY, posZ, toPlace, 2, rule);
	}
	
	public static boolean placeBlockIfAllowed(World world, double posX, double posY, double posZ, Block toPlace, int flag, BlockProtectionRule rule)
	{
		Block currentBlock = world.getBlockState(new BlockPos(posX, posY, posZ)).getBlock();

		ExtendedWorldData worldData = ExtendedWorldData.get(world);
		boolean inProtectedAreaFlag = worldData.isInsideRestrictedArea((int) posX, (int) posY, (int) posZ);
		boolean isGriefDisabled = !CommonConfig.instance.isGriefingEnabled();
		boolean isGriefBypass = rule.getBypassGriefRule();
		
		if (inProtectedAreaFlag || (isGriefDisabled && !isGriefBypass))
			return false;

		for (Block block : rule.getApprovedBlocks())
		{
			if (currentBlock == block)
			{
				BlockPos pos = new BlockPos(posX, posY, posZ);
				BlockState state = toPlace.getDefaultState();
				world.setBlockState(pos, state, flag);
				return true;
			}
		}
	
		return false;
	}

	public static List<BlockPos> createEmptyCube(World world, double posX, double posY, double posZ, int sizeX, int sizeY, int sizeZ, Block blockToPlace, BlockProtectionRule rule)
	{
		List<BlockPos> blockPositions = new ArrayList<BlockPos>();
		for (int x = -sizeX; x <= sizeX; x++)
			for (int y = -sizeY; y <= sizeY; y++)
				for (int z = -sizeZ; z <= sizeZ; z++)
				{
					if (x == -sizeX || x == sizeX || y == -sizeY || y == sizeY || z == -sizeZ || z == sizeZ)
					{
						placeBlockIfAllowed(world, posX + x, posY + y, posZ + z, blockToPlace, rule);
						blockPositions.add(new BlockPos(posX + x, posY + y, posZ + z));
					}
				}

		return blockPositions;
	}

	public static List<BlockPos> createFilledCube(World world, double posX, double posY, double posZ, int sizeX, int sizeY, int sizeZ, Block blockToPlace, BlockProtectionRule rule)
	{
		List<BlockPos> blockPositions = new ArrayList<BlockPos>();
		for (int x = -sizeX; x <= sizeX; x++)
			for (int y = -sizeY; y <= sizeY; y++)
				for (int z = -sizeZ; z <= sizeZ; z++)
				{
					placeBlockIfAllowed(world, posX + x, posY + y, posZ + z, blockToPlace, rule);
					blockPositions.add(new BlockPos(posX + x, posY + y, posZ + z));
				}

		return blockPositions;
	}

	public static List<BlockPos> createEmptySphere(World world, int posX, int posY, int posZ, int size, final Block block, BlockProtectionRule rule)
	{
		List<BlockPos> blockPositions = new ArrayList<BlockPos>();

		Sphere.generate(posX, posY, posZ, size, new ISphere()
		{
			@Override
			public void call(int x, int y, int z)
			{
				placeBlockIfAllowed(world, x, y, z, block, rule);
				blockPositions.add(new BlockPos(x, y, z));
			}
		});

		return blockPositions;
	}

	public static List<BlockPos> createFilledSphere(World world, int posX, int posY, int posZ, int size, final Block block, BlockProtectionRule rule)
	{
		List<BlockPos> blockPositions = new ArrayList<BlockPos>();

		Sphere.generateFilled(posX, posY, posZ, size, new ISphere()
		{
			@Override
			public void call(int x, int y, int z)
			{
				placeBlockIfAllowed(world, x, y, z, block, rule);
				blockPositions.add(new BlockPos(x, y, z));
			}
		});

		return blockPositions;
	}

	public static double[] propulsion(LivingEntity entity, double extraMX, double extraMZ)
	{
		double mX = -MathHelper.sin(entity.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(entity.rotationPitch / 180.0F * (float) Math.PI) * 0.4;
		double mZ = MathHelper.cos(entity.rotationYaw / 180.0F * (float) Math.PI) * MathHelper.cos(entity.rotationPitch / 180.0F * (float) Math.PI) * 0.4;

		double f2 = MathHelper.sqrt(mX * mX + entity.getMotion().y * entity.getMotion().y + mZ * mZ);
		mX /= f2;
		mZ /= f2;
		mX += entity.world.rand.nextGaussian() * 0.007499999832361937D * 1.0;
		mZ += entity.world.rand.nextGaussian() * 0.007499999832361937D * 1.0;
		mX *= extraMX;
		mZ *= extraMZ;

		return new double[]
			{
					mX, mZ
			};
	}

	public static ExplosionAbility newExplosion(Entity entity, double posX, double posY, double posZ, float size)
	{
		ExplosionAbility explosion = new ExplosionAbility(entity, posX, posY, posZ, size);
		return explosion;
	}

	@Deprecated
	public static void doExplosion(Entity entity, double posX, double posY, double posZ, float size)
	{
		ExplosionAbility explosion = new ExplosionAbility(entity, posX, posY, posZ, size);
		explosion.doExplosion();
	}

	public static boolean canUseSwordsmanAbilities(PlayerEntity player)
	{
		IAbilityData abilityProps = AbilityDataCapability.get(player);
		IDevilFruit devilFruitProps = DevilFruitCapability.get(player);
		Ability sparClawAbility = abilityProps.getEquippedAbility(SparClawAbility.INSTANCE);
		
		boolean hasSwordInHand = ItemsHelper.isSword(player.getHeldItemMainhand());
		boolean hasSparClaw = devilFruitProps.getDevilFruit().equalsIgnoreCase("supa_supa") && sparClawAbility != null && sparClawAbility.isContinuous();
		
		if (hasSwordInHand || hasSparClaw)
		{
			return true;
		}

		return false;
	}

	public static boolean checkForRestriction(PlayerEntity player)
	{
		ExtendedWorldData worldData = ExtendedWorldData.get(player.world);

		if (worldData.isInsideRestrictedArea((int) player.posX, (int) player.posY, (int) player.posZ))
		{
			WyHelper.sendMsgToPlayer(player, "Cannot use abilites in a restricted area !");
			return true;
		}

		return false;
	}

	public static boolean isDevilFruitInWorld(World world, String devilFruitName)
	{
		ExtendedWorldData worldData = ExtendedWorldData.get(world);

		if (worldData.isDevilFruitInWorld(devilFruitName))
			return true;

		return false;
	}

	public static boolean isAffectedByWater(LivingEntity entity)
	{
		boolean isUnderWater = entity.areEyesInFluid(FluidTags.WATER, true) && entity.getRidingEntity() == null;
		boolean hasWaterUnder = entity.isInWater() && entity.world.getBlockState(entity.getPosition().down()).getBlock() == Blocks.WATER;

		if (isUnderWater || hasWaterUnder)
		{
			return true;
		}

		return false;
	}

	public static boolean isNearbyKairoseki(PlayerEntity player)
	{
		if (WyHelper.isBlockNearby(player, 4, ModBlocks.KAIROSEKI, ModBlocks.KAIROSEKI_ORE, ModBlocks.KAIROSEKI_BARS) || ItemsHelper.hasKairosekiItem(player) || isAffectedByWater(player))
		{
			return true;
		}

		return false;
	}

	public static boolean verifyIfAbilityIsBanned(Ability a)
	{
		for (String str : CommonConfig.instance.getBannedAbilities())
		{
			if (WyHelper.getResourceName(str).contains(WyHelper.getResourceName(a.getName())))
				return true;
		}

		return false;
	}

	public static void validateRacialMoves(PlayerEntity player)
	{
		IEntityStats props = EntityStatsCapability.get(player);
		IAbilityData abilityProps = AbilityDataCapability.get(player);

		DorikiEvent e = new DorikiEvent(player);
		if (MinecraftForge.EVENT_BUS.post(e))
			return;

		List<Ability> tempAblList = new ArrayList<Ability>();

		List<Ability> rokushikiAbilities = Lists.newArrayList(SoruAbility.INSTANCE, TekkaiAbility.INSTANCE, ShiganAbility.INSTANCE, RankyakuAbility.INSTANCE, KamieAbility.INSTANCE, GeppoAbility.INSTANCE);
		List<Ability> fishmanKarateAbilities = Lists.newArrayList(UchimizuAbility.INSTANCE, SamehadaShoteiAbility.INSTANCE, MurasameAbility.INSTANCE, KarakusagawaraSeikenAbility.INSTANCE, KachiageHaisokuAbility.INSTANCE);
		List<Ability> cyborgAbilities = Lists.newArrayList(StrongRightAbility.INSTANCE, RadicalBeamAbility.INSTANCE, FreshFireAbility.INSTANCE, CoupDeVentAbility.INSTANCE, ColaOverdriveAbility.INSTANCE);

		if (props.isHuman())
			for (Ability a : rokushikiAbilities)
				if (abilityProps.hasUnlockedAbility(a) && !verifyIfAbilityIsBanned(a))
					tempAblList.add(a);
		if (props.isFishman())
			for (Ability a : fishmanKarateAbilities)
				if (abilityProps.hasUnlockedAbility(a) && !verifyIfAbilityIsBanned(a))
					tempAblList.add(a);
		if (props.isCyborg())
			for (Ability a : cyborgAbilities)
				if (abilityProps.hasUnlockedAbility(a) && !verifyIfAbilityIsBanned(a))
					tempAblList.add(a);
		
		abilityProps.clearUnlockedAbilities(AbilityCategory.RACIAL);
		
		for (Ability a : tempAblList)
			abilityProps.addUnlockedAbility(a);
		tempAblList.clear();
		/*
		for (Ability a : HakiAbilities.abilitiesArray)
			if (abilityProps.hasHakiAbility(a) && !verifyIfAbilityIsBanned(a))
				tempAblList.add(a);
		abilityProps.clearHakiAbilities();
		for (Ability a : tempAblList)
			abilityProps.addHakiAbility(a);*/

	}

	public static void validateStyleMoves(PlayerEntity player)
	{
		IEntityStats props = EntityStatsCapability.get(player);
		
		if (props.isSwordsman())
		{
			validateQuestAbility(player, ModQuests.SWORDSMAN_TRIAL_01, ShiShishiSonsonAbility.INSTANCE);
			validateQuestAbility(player, ModQuests.SWORDSMAN_TRIAL_02, YakkodoriAbility.INSTANCE);
			validateQuestAbility(player, ModQuests.SWORDSMAN_TRIAL_03, SanbyakurokujuPoundHoAbility.INSTANCE);
			validateQuestAbility(player, ModQuests.SWORDSMAN_TRIAL_04, OTatsumakiAbility.INSTANCE);
		}
		else if (props.isSniper())
		{
			validateQuestAbility(player, ModQuests.SNIPER_TRIAL_01, KaenBoshiAbility.INSTANCE);
			validateQuestAbility(player, ModQuests.SNIPER_TRIAL_02, KemuriBoshiAbility.INSTANCE);
			validateQuestAbility(player, ModQuests.SNIPER_TRIAL_03, RenpatsuNamariBoshiAbility.INSTANCE);
			validateQuestAbility(player, ModQuests.SNIPER_TRIAL_04, SakuretsuSabotenBoshiAbility.INSTANCE);
		}
		else if (props.isDoctor())
		{
			validateQuestAbility(player, ModQuests.SWORDSMAN_TRIAL_01, FirstAidAbility.INSTANCE);
			validateQuestAbility(player, ModQuests.SWORDSMAN_TRIAL_02, MedicBagExplosionAbility.INSTANCE);
			validateQuestAbility(player, ModQuests.SWORDSMAN_TRIAL_03, FailedExperimentAbility.INSTANCE);
		}
	}

	private static void validateQuestAbility(PlayerEntity player, Quest quest, Ability ability)
	{
		IAbilityData abilityProps = AbilityDataCapability.get(player);
		IQuestData questProps = QuestDataCapability.get(player);
		
		if(CommonConfig.instance.isQuestProgressionEnabled())
		{
			if (questProps.hasFinishedQuest(quest) && !verifyIfAbilityIsBanned(ability))
				abilityProps.addUnlockedAbility(ability);
		}
		else
		{
			if(!verifyIfAbilityIsBanned(ability))
				abilityProps.addUnlockedAbility(ability);
		}
	}
	
	public static ItemStack getDevilFruitItem(String fullName)
	{
		String model = "";
		String fullModel = "";

		for (String[] s : zoanModels)
		{
			if (fullName.equals(s[0]))
			{
				model = s[1];
				fullModel = "_model_" + model;
				break;
			}
		}

		if (fullName.equals("yamidummy"))
			fullName = "yamiyami";

		String finalName = (!WyHelper.isNullOrEmpty(model) ? fullName.replace("_" + model, "") : fullName) + "_no_mi" + fullModel;
				
		return new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(APIConfig.PROJECT_ID, finalName)));
	}

	public static boolean isEntityInRoom(LivingEntity entity)
	{
		for (int i = -20; i < 20; i++)
			for (int j = -20; j < 20; j++)
				for (int k = -20; k < 20; k++)
				{
					if (entity.world.getBlockState(new BlockPos(entity.posX + i, entity.posY + j, entity.posZ + k)).getBlock() == ModBlocks.OPE_MID)
						return true;
				}

		return false;
	}

	public static int getRegenFromPoision(LivingEntity entity)
	{
		return entity.getActivePotionEffect(Effects.POISON).getAmplifier() / 5;
	}

}
