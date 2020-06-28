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
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.CoolBallAbility;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.HeatBallAbility;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.ThunderBallAbility;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.FogTempo;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.MirageTempo;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.RainTempo;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.ThunderLanceTempo;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.ThunderboltTempo;
import xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos.WeatherCloudTempo;
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
import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiFullBodyHardeningAbility;
import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiHardeningAbility;
import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiImbuingAbility;
import xyz.pixelatedw.mineminenomi.abilities.haki.HaoshokuHakiAbility;
import xyz.pixelatedw.mineminenomi.abilities.haki.KenbunshokuHakiAuraAbility;
import xyz.pixelatedw.mineminenomi.abilities.haki.KenbunshokuHakiFutureSightAbility;
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
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModQuests;
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

		if (worldData.isInsideRestrictedArea(player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ()))
		{
			WyHelper.sendMsgToPlayer(player, new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_CANNOT_USE_HERE).getFormattedText());
			return true;
		}

		return false;
	}
	
	public static boolean checkForRestriction(World world, int posX, int posY, int posZ)
	{
		ExtendedWorldData worldData = ExtendedWorldData.get(world);

		if (worldData.isInsideRestrictedArea(posX, posY, posZ))
			return true;

		return false;
	}

	public static boolean isAffectedByWater(LivingEntity entity)
	{
		boolean isUnderWater = entity.areEyesInFluid(FluidTags.WATER, true);
		boolean waterAbove = entity.world.getBlockState(entity.getPosition().up()).getBlock() == Blocks.WATER;
		boolean inWater = entity.world.getBlockState(entity.getPosition()).getBlock() == Blocks.WATER;
		boolean waterUnder = entity.world.getBlockState(entity.getPosition().down()).getBlock() == Blocks.WATER;
		int total = 0;
		if(waterAbove)
			total++;
		if(inWater)
			total++;
		if(waterUnder)
			total++;
		boolean hasWaterUnder = entity.isInWater() && total >= 2;
		
		if (entity.getRidingEntity() == null && (isUnderWater || hasWaterUnder))
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

		List<Ability> tempAblList = new ArrayList<Ability>();

		List<Ability> rokushikiAbilities = Lists.newArrayList(SoruAbility.INSTANCE, TekkaiAbility.INSTANCE, ShiganAbility.INSTANCE, RankyakuAbility.INSTANCE, KamieAbility.INSTANCE, GeppoAbility.INSTANCE);
		List<Ability> fishmanKarateAbilities = Lists.newArrayList(UchimizuAbility.INSTANCE, SamehadaShoteiAbility.INSTANCE, MurasameAbility.INSTANCE, KarakusagawaraSeikenAbility.INSTANCE, KachiageHaisokuAbility.INSTANCE);
		List<Ability> cyborgAbilities = Lists.newArrayList(StrongRightAbility.INSTANCE, RadicalBeamAbility.INSTANCE, FreshFireAbility.INSTANCE, CoupDeVentAbility.INSTANCE, ColaOverdriveAbility.INSTANCE);
		
		List<Ability> hakiAbilities = Lists.newArrayList(BusoshokuHakiFullBodyHardeningAbility.INSTANCE, BusoshokuHakiHardeningAbility.INSTANCE, BusoshokuHakiImbuingAbility.INSTANCE, HaoshokuHakiAbility.INSTANCE, KenbunshokuHakiAuraAbility.INSTANCE, KenbunshokuHakiFutureSightAbility.INSTANCE);

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

		for (Ability a : hakiAbilities)
			if (abilityProps.hasUnlockedAbility(a) && !verifyIfAbilityIsBanned(a))
				tempAblList.add(a);
		
		abilityProps.clearUnlockedAbilities(AbilityCategory.HAKI);
		
		for (Ability a : tempAblList)
			abilityProps.addUnlockedAbility(a);
		tempAblList.clear();
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
		{
			validateQuestAbility(player, ModQuests.SWORDSMAN_TRIAL_01, HeatBallAbility.INSTANCE);
			validateQuestAbility(player, ModQuests.SWORDSMAN_TRIAL_01, CoolBallAbility.INSTANCE);
			validateQuestAbility(player, ModQuests.SWORDSMAN_TRIAL_01, WeatherCloudTempo.INSTANCE);
			validateQuestAbility(player, ModQuests.SWORDSMAN_TRIAL_02, ThunderBallAbility.INSTANCE);
			validateQuestAbility(player, ModQuests.SWORDSMAN_TRIAL_02, ThunderboltTempo.INSTANCE);
			validateQuestAbility(player, ModQuests.SWORDSMAN_TRIAL_02, ThunderLanceTempo.INSTANCE);
			validateQuestAbility(player, ModQuests.SWORDSMAN_TRIAL_02, FogTempo.INSTANCE);
			validateQuestAbility(player, ModQuests.SWORDSMAN_TRIAL_02, MirageTempo.INSTANCE);
			validateQuestAbility(player, ModQuests.SWORDSMAN_TRIAL_03, RainTempo.INSTANCE);

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
}
