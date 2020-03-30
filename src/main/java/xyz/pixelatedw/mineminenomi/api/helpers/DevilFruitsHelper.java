package xyz.pixelatedw.mineminenomi.api.helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
import xyz.pixelatedw.mineminenomi.abilities.swordsman.OTatsumakiAbility;
import xyz.pixelatedw.mineminenomi.abilities.swordsman.SanbyakurokujuPoundHoAbility;
import xyz.pixelatedw.mineminenomi.abilities.swordsman.ShiShishiSonsonAbility;
import xyz.pixelatedw.mineminenomi.abilities.swordsman.YakkodoriAbility;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.events.custom.DorikiEvent;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.APIConfig.AbilityCategory;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.wypi.math.ISphere;
import xyz.pixelatedw.wypi.math.Sphere;

public class DevilFruitsHelper
{

	private static HashMap<String, List<Block>> blockRules = createBlockRulesMap();

	private static HashMap<String, List<Block>> createBlockRulesMap()
	{
		HashMap<String, List<Block>> map = new HashMap<String, List<Block>>();

		map.put("core", Arrays.asList(new Block[]
			{
					Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.GRASS_BLOCK, Blocks.PODZOL, Blocks.MYCELIUM, Blocks.GRAVEL, Blocks.SAND, Blocks.RED_SAND, Blocks.CLAY, Blocks.STONE, Blocks.COBBLESTONE, Blocks.SMOOTH_STONE, Blocks.GRANITE, Blocks.POLISHED_GRANITE, Blocks.DIORITE, Blocks.POLISHED_DIORITE, Blocks.OBSIDIAN, Blocks.ANDESITE, Blocks.POLISHED_ANDESITE, Blocks.MOSSY_COBBLESTONE, Blocks.BRICKS, Blocks.BONE_BLOCK, Blocks.SANDSTONE, Blocks.TERRACOTTA, Blocks.SNOW, Blocks.SNOW_BLOCK, Blocks.ICE, Blocks.PACKED_ICE, Blocks.BLUE_ICE, Blocks.SEA_LANTERN, Blocks.PRISMARINE, Blocks.PRISMARINE_BRICKS, Blocks.DARK_PRISMARINE, Blocks.FARMLAND, Blocks.GRASS_PATH, Blocks.GLASS, Blocks.BOOKSHELF, Blocks.STONE_BRICKS, Blocks.MOSSY_STONE_BRICKS, Blocks.CRACKED_STONE_BRICKS, Blocks.CHISELED_STONE_BRICKS, Blocks.SPONGE, Blocks.SLIME_BLOCK, Blocks.CARVED_PUMPKIN, Blocks.HAY_BLOCK, Blocks.DRIED_KELP_BLOCK, Blocks.ACACIA_LOG, Blocks.BIRCH_LOG, Blocks.DARK_OAK_LOG, Blocks.JUNGLE_LOG, Blocks.OAK_LOG, Blocks.SPRUCE_LOG, Blocks.STRIPPED_ACACIA_LOG, Blocks.STRIPPED_ACACIA_WOOD, Blocks.STRIPPED_ACACIA_LOG, Blocks.STRIPPED_BIRCH_LOG, Blocks.STRIPPED_BIRCH_WOOD, Blocks.STRIPPED_DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_WOOD, Blocks.STRIPPED_JUNGLE_LOG, Blocks.STRIPPED_JUNGLE_WOOD, Blocks.STRIPPED_OAK_LOG, Blocks.STRIPPED_OAK_WOOD, Blocks.STRIPPED_SPRUCE_LOG, Blocks.STRIPPED_SPRUCE_WOOD, Blocks.ACACIA_WOOD, Blocks.BIRCH_WOOD, Blocks.DARK_OAK_WOOD, Blocks.JUNGLE_WOOD, Blocks.OAK_WOOD, Blocks.SPRUCE_WOOD, Blocks.ACACIA_PLANKS, Blocks.BIRCH_PLANKS, Blocks.DARK_OAK_PLANKS, Blocks.JUNGLE_PLANKS, Blocks.OAK_PLANKS, Blocks.SPRUCE_PLANKS, Blocks.FROSTED_ICE, Blocks.ACACIA_SLAB, Blocks.ANDESITE_SLAB, Blocks.BIRCH_SLAB, Blocks.BIRCH_SLAB, Blocks.BRICK_SLAB, Blocks.COBBLESTONE_SLAB, Blocks.DARK_OAK_SLAB, Blocks.DIORITE_SLAB, Blocks.DARK_PRISMARINE_SLAB, Blocks.END_STONE_BRICK_SLAB, Blocks.GRANITE_SLAB, Blocks.JUNGLE_SLAB, Blocks.MOSSY_COBBLESTONE_SLAB, Blocks.MOSSY_STONE_BRICK_SLAB, Blocks.NETHER_BRICK_SLAB, Blocks.OAK_SLAB, Blocks.PETRIFIED_OAK_SLAB, Blocks.POLISHED_ANDESITE_SLAB, Blocks.POLISHED_DIORITE_SLAB, Blocks.POLISHED_GRANITE_SLAB, Blocks.PRISMARINE_BRICK_SLAB, Blocks.PRISMARINE_SLAB, Blocks.PURPUR_SLAB, Blocks.QUARTZ_SLAB, Blocks.RED_NETHER_BRICK_SLAB, Blocks.RED_SANDSTONE_SLAB, Blocks.SANDSTONE_SLAB, Blocks.SANDSTONE_SLAB, Blocks.SMOOTH_RED_SANDSTONE_SLAB, Blocks.SMOOTH_SANDSTONE_SLAB, Blocks.SMOOTH_STONE_SLAB, Blocks.SPRUCE_SLAB, Blocks.STONE_BRICK_SLAB, Blocks.STONE_SLAB, Blocks.ACACIA_STAIRS, Blocks.ANDESITE_STAIRS, Blocks.BIRCH_STAIRS, Blocks.BIRCH_STAIRS, Blocks.BRICK_STAIRS, Blocks.COBBLESTONE_STAIRS, Blocks.DARK_OAK_STAIRS, Blocks.DARK_PRISMARINE_STAIRS, Blocks.DIORITE_STAIRS, Blocks.END_STONE_BRICK_STAIRS, Blocks.GRANITE_STAIRS, Blocks.JUNGLE_STAIRS, Blocks.MOSSY_COBBLESTONE_STAIRS, Blocks.MOSSY_STONE_BRICK_STAIRS, Blocks.NETHER_BRICK_STAIRS, Blocks.OAK_STAIRS, Blocks.POLISHED_ANDESITE_STAIRS, Blocks.POLISHED_DIORITE_STAIRS, Blocks.POLISHED_GRANITE_STAIRS, Blocks.PRISMARINE_BRICK_STAIRS, Blocks.PRISMARINE_STAIRS, Blocks.PURPUR_STAIRS, Blocks.QUARTZ_STAIRS, Blocks.RED_NETHER_BRICK_STAIRS, Blocks.RED_SANDSTONE_STAIRS, Blocks.SANDSTONE_STAIRS, Blocks.SMOOTH_RED_SANDSTONE_STAIRS, Blocks.SMOOTH_RED_SANDSTONE_STAIRS, Blocks.SMOOTH_SANDSTONE_STAIRS, Blocks.SPRUCE_STAIRS, Blocks.STONE_BRICK_STAIRS, Blocks.STONE_STAIRS, Blocks.NETHERRACK, Blocks.SOUL_SAND, Blocks.GLOWSTONE, Blocks.MAGMA_BLOCK, Blocks.NETHER_BRICKS, Blocks.RED_NETHER_BRICKS, Blocks.NETHER_BRICK_FENCE, Blocks.END_STONE, Blocks.PURPUR_BLOCK, Blocks.PURPUR_PILLAR, Blocks.END_STONE_BRICKS, Blocks.END_ROD, Blocks.BLACK_WOOL, Blocks.BLUE_WOOL, Blocks.BROWN_WOOL, Blocks.CYAN_WOOL, Blocks.GRAY_WOOL, Blocks.GREEN_WOOL, Blocks.LIGHT_BLUE_WOOL, Blocks.LIGHT_GRAY_WOOL, Blocks.LIME_WOOL, Blocks.MAGENTA_WOOL, Blocks.ORANGE_WOOL, Blocks.PINK_WOOL, Blocks.PURPLE_WOOL, Blocks.RED_WOOL, Blocks.WHITE_WOOL, Blocks.YELLOW_WOOL, Blocks.CRAFTING_TABLE, Blocks.BLAST_FURNACE, Blocks.FURNACE, Blocks.CAMPFIRE, Blocks.SMOKER, Blocks.STONECUTTER, Blocks.BARREL, Blocks.ENCHANTING_TABLE, Blocks.LOOM, Blocks.FLETCHING_TABLE, Blocks.CARTOGRAPHY_TABLE, Blocks.ANVIL, Blocks.SMITHING_TABLE, Blocks.GRINDSTONE, Blocks.BEACON, Blocks.JUKEBOX, Blocks.SCAFFOLDING, Blocks.BELL, Blocks.LECTERN, Blocks.COMPOSTER, Blocks.BREWING_STAND, Blocks.CAULDRON, Blocks.NOTE_BLOCK, Blocks.HOPPER, Blocks.TNT, Blocks.DISPENSER, Blocks.DROPPER, Blocks.OBSERVER, Blocks.REDSTONE_LAMP, Blocks.PISTON, Blocks.STICKY_PISTON, Blocks.DAYLIGHT_DETECTOR, Blocks.REDSTONE_WIRE, Blocks.REPEATER, Blocks.COMPARATOR, Blocks.TRIPWIRE_HOOK, Blocks.REDSTONE_TORCH, Blocks.REDSTONE_WALL_TORCH, Blocks.TORCH, Blocks.WALL_TORCH, Blocks.ACACIA_TRAPDOOR, Blocks.BIRCH_TRAPDOOR, Blocks.DARK_OAK_TRAPDOOR, Blocks.IRON_TRAPDOOR, Blocks.JUNGLE_TRAPDOOR, Blocks.OAK_TRAPDOOR, Blocks.SPRUCE_TRAPDOOR, Blocks.ACACIA_DOOR, Blocks.BIRCH_DOOR, Blocks.DARK_OAK_DOOR, Blocks.IRON_DOOR, Blocks.JUNGLE_DOOR, Blocks.OAK_DOOR, Blocks.SPRUCE_DOOR, Blocks.RAIL, Blocks.POWERED_RAIL, Blocks.ACTIVATOR_RAIL, Blocks.DETECTOR_RAIL, Blocks.ACACIA_FENCE, Blocks.ACACIA_FENCE_GATE, Blocks.BIRCH_FENCE, Blocks.BIRCH_FENCE_GATE, Blocks.DARK_OAK_FENCE, Blocks.DARK_OAK_FENCE_GATE, Blocks.JUNGLE_FENCE, Blocks.JUNGLE_FENCE_GATE, Blocks.OAK_FENCE, Blocks.OAK_FENCE_GATE, Blocks.SPRUCE_FENCE, Blocks.SPRUCE_FENCE_GATE, Blocks.IRON_BARS, Blocks.GLASS_PANE, Blocks.LADDER, Blocks.COBWEB, Blocks.LANTERN, Blocks.SEA_PICKLE, Blocks.FLOWER_POT, Blocks.BRAIN_CORAL_BLOCK, Blocks.BUBBLE_CORAL_BLOCK, Blocks.DEAD_BRAIN_CORAL_BLOCK, Blocks.DEAD_BUBBLE_CORAL_BLOCK, Blocks.DEAD_HORN_CORAL_BLOCK, Blocks.DEAD_TUBE_CORAL_BLOCK, Blocks.FIRE_CORAL_BLOCK, Blocks.HORN_CORAL_BLOCK, Blocks.TUBE_CORAL_BLOCK
			}));

		map.put("air", Arrays.asList(new Block[]
			{
					Blocks.AIR
			}));

		map.put("foliage", Arrays.asList(new Block[]
			{
					Blocks.ACACIA_LEAVES, Blocks.BIRCH_LEAVES, Blocks.DARK_OAK_LEAVES, Blocks.JUNGLE_LEAVES, Blocks.OAK_LEAVES, Blocks.SPRUCE_LEAVES, Blocks.LILY_PAD, Blocks.TALL_GRASS, Blocks.CHORUS_FLOWER, Blocks.LILAC, Blocks.PEONY, Blocks.ROSE_BUSH, Blocks.MUSHROOM_STEM, Blocks.BROWN_MUSHROOM, Blocks.BROWN_MUSHROOM_BLOCK, Blocks.RED_MUSHROOM, Blocks.RED_MUSHROOM_BLOCK, Blocks.POTATOES, Blocks.CARROTS, Blocks.CACTUS, Blocks.DEAD_BUSH, Blocks.GRASS, Blocks.AZURE_BLUET, Blocks.DANDELION, Blocks.VINE, Blocks.PUMPKIN, Blocks.PUMPKIN_STEM, Blocks.MELON, Blocks.MELON_STEM, Blocks.NETHER_WART, Blocks.NETHER_WART_BLOCK, Blocks.CHORUS_FLOWER, Blocks.CHORUS_PLANT, Blocks.BRAIN_CORAL, Blocks.BRAIN_CORAL_FAN, Blocks.BRAIN_CORAL_WALL_FAN, Blocks.DEAD_BRAIN_CORAL, Blocks.DEAD_BRAIN_CORAL_FAN, Blocks.DEAD_BRAIN_CORAL_WALL_FAN, Blocks.BUBBLE_CORAL, Blocks.BUBBLE_CORAL_FAN, Blocks.BUBBLE_CORAL_WALL_FAN, Blocks.DEAD_BUBBLE_CORAL, Blocks.DEAD_BUBBLE_CORAL_FAN, Blocks.DEAD_BUBBLE_CORAL_WALL_FAN, Blocks.FIRE_CORAL, Blocks.FIRE_CORAL_FAN, Blocks.FIRE_CORAL_WALL_FAN, Blocks.DEAD_FIRE_CORAL, Blocks.DEAD_FIRE_CORAL_FAN, Blocks.DEAD_FIRE_CORAL_WALL_FAN, Blocks.DEAD_TUBE_CORAL, Blocks.DEAD_TUBE_CORAL_FAN, Blocks.DEAD_TUBE_CORAL_WALL_FAN, Blocks.TUBE_CORAL, Blocks.TUBE_CORAL_FAN, Blocks.TUBE_CORAL_WALL_FAN, Blocks.DEAD_HORN_CORAL, Blocks.DEAD_HORN_CORAL_FAN, Blocks.DEAD_HORN_CORAL_WALL_FAN, Blocks.HORN_CORAL, Blocks.HORN_CORAL_FAN, Blocks.HORN_CORAL_WALL_FAN, Blocks.COCOA, Blocks.SUGAR_CANE, Blocks.BEETROOTS, Blocks.WHEAT, Blocks.FERN, Blocks.LARGE_FERN, Blocks.ACACIA_SAPLING, Blocks.BAMBOO_SAPLING, Blocks.BIRCH_SAPLING, Blocks.DARK_OAK_SAPLING, Blocks.JUNGLE_SAPLING, Blocks.OAK_SAPLING, Blocks.BAMBOO
			}));

		map.put("ore", Arrays.asList(new Block[]
			{
					Blocks.COAL_ORE, Blocks.IRON_ORE, Blocks.GOLD_ORE, Blocks.DIAMOND_ORE, Blocks.EMERALD_ORE, Blocks.REDSTONE_ORE, Blocks.LAPIS_ORE, Blocks.NETHER_QUARTZ_ORE
			}));

		map.put("valuable", Arrays.asList(new Block[]
			{
					Blocks.COAL_BLOCK, Blocks.IRON_BLOCK, Blocks.GOLD_BLOCK, Blocks.DIAMOND_BLOCK, Blocks.EMERALD_BLOCK, Blocks.REDSTONE_BLOCK, Blocks.LAPIS_BLOCK, Blocks.QUARTZ_BLOCK, Blocks.CHISELED_QUARTZ_BLOCK, Blocks.SMOOTH_QUARTZ, Blocks.SMOOTH_QUARTZ_SLAB, Blocks.SMOOTH_QUARTZ_STAIRS
			}));

		map.put("liquid", Arrays.asList(new Block[]
			{
					Blocks.WATER, Blocks.LAVA
			}));

		map.put("protection", Arrays.asList(new Block[]
			{
					Blocks.BLUE_STAINED_GLASS, Blocks.RED_STAINED_GLASS
			}));

		map.put("portal", Arrays.asList(new Block[]
			{
					Blocks.END_PORTAL, Blocks.NETHER_PORTAL, Blocks.END_GATEWAY
			}));

		return map;
	}

	private static String[][] zoanModels = new String[][]
		{
				{
						"ushiushibison", "bison"
				},
				{
						"toritoriphoenix", "phoenix"
				},
				{
						"ushiushigiraffe", "giraffe"
				},
		};

	public static String[] flyingFruits = new String[]
		{
				"gasugasu", "sunasuna", "mokumoku"
		};

	public static boolean placeBlockIfAllowed(World world, BlockPos pos, Block toPlace, String... rules)
	{
		return placeBlockIfAllowed(world, pos.getX(), pos.getY(), pos.getZ(), toPlace, 3, rules);
	}
	
	public static boolean placeBlockIfAllowed(World world, BlockPos pos, Block toPlace, int flag, String... rules)
	{
		return placeBlockIfAllowed(world, pos.getX(), pos.getY(), pos.getZ(), toPlace, flag, rules);
	}

	public static boolean placeBlockIfAllowed(World world, double posX, double posY, double posZ, Block toPlace, String... rules)
	{
		return placeBlockIfAllowed(world, posX, posY, posZ, toPlace, 2, rules);
	}

	public static boolean placeBlockIfAllowed(World world, double posX, double posY, double posZ, Block toPlace, int flag, String... rules)
	{
		Block b = world.getBlockState(new BlockPos(posX, posY, posZ)).getBlock();
		List<Block> bannedBlocks = new ArrayList<Block>();
		boolean noGriefFlag = Arrays.toString(rules).contains("nogrief");

		ExtendedWorldData worldData = ExtendedWorldData.get(world);
		if (worldData.isInsideRestrictedArea((int) posX, (int) posY, (int) posZ) || !CommonConfig.instance.isGriefingEnabled())
			return false;

		Arrays.stream(rules).forEach(rule ->
		{
			String formula;
			if (rule.split(" ").length > 1)
			{
				formula = rule.split(" ")[0];
				rule = rule.split(" ")[1];
			}
			else
				formula = "add";

			if (blockRules.containsKey(rule))
			{
				if (formula.equalsIgnoreCase("add"))
					bannedBlocks.addAll(blockRules.get(rule));
				else if (formula.equalsIgnoreCase("ignore"))
					bannedBlocks.removeAll(blockRules.get(rule));
			}

			if (rule.equalsIgnoreCase("all"))
			{
				if (formula.equalsIgnoreCase("add"))
				{
					ForgeRegistries.BLOCKS.forEach(block ->
					{
						bannedBlocks.add(block);
					});
				}
				else if (formula.equalsIgnoreCase("ignore"))
				{
					ForgeRegistries.BLOCKS.forEach(block ->
					{
						bannedBlocks.remove(block);
					});
				}
			}
			else if (rule.equalsIgnoreCase("restricted"))
			{
				bannedBlocks.removeAll(Arrays.asList(ItemsHelper.RESTRICTED_BLOCKS));
			}

		});

		if (CommonConfig.instance.isGriefingEnabled() || noGriefFlag)
		{
			for (Block blk : bannedBlocks)
			{
				if (b == blk)
				{
					BlockPos pos = new BlockPos(posX, posY, posZ);
					BlockState state = toPlace.getDefaultState();
					world.setBlockState(pos, state, flag);
					return true;
				}
			}
		}

		return false;
	}

	public static List<int[]> createEmptyCube(Entity entity, int[] sizes, Block blockToPlace, String... blockRules)
	{
		return createEmptyCube(entity.world, (int) entity.posX, (int) entity.posY, (int) entity.posZ, sizes, blockToPlace, blockRules);
	}

	public static List<int[]> createEmptyCube(World world, double posX, double posY, double posZ, int[] sizes, Block blockToPlace, String... blockRules)
	{
		List<int[]> blocks = new ArrayList<int[]>();
		for (int x = (sizes[0] * 0) - sizes[0]; x <= sizes[0]; x++)
			for (int y = (sizes[1] * 0) - sizes[1]; y <= sizes[1]; y++)
				for (int z = (sizes[2] * 0) - sizes[2]; z <= sizes[2]; z++)
				{
					if (x == -sizes[0] || x == sizes[0] || y == -sizes[1] || y == sizes[1] || z == -sizes[2] || z == sizes[2])
					{
						placeBlockIfAllowed(world, (int) posX + x, (int) posY + y, (int) posZ + z, blockToPlace, blockRules);
						blocks.add(new int[]
							{
									(int) posX + x, (int) posY + y, (int) posZ + z
							});
					}
				}

		return blocks;
	}

	public static List<int[]> createFilledCube(Entity entity, int[] sizes, Block blockToPlace, String... blockRules)
	{
		return createFilledCube(entity.world, (int) entity.posX, (int) entity.posY, (int) entity.posZ, sizes, blockToPlace, blockRules);
	}

	public static List<int[]> createFilledCube(World world, double posX, double posY, double posZ, int[] sizes, Block blockToPlace, String... blockRules)
	{
		List<int[]> blocks = new ArrayList<int[]>();
		for (int x = (sizes[0] * 0) - sizes[0]; x <= sizes[0]; x++)
			for (int y = (sizes[1] * 0) - sizes[1]; y <= sizes[1]; y++)
				for (int z = (sizes[2] * 0) - sizes[2]; z <= sizes[2]; z++)
				{
					placeBlockIfAllowed(world, (int) posX + x, (int) posY + y, (int) posZ + z, blockToPlace, blockRules);
					blocks.add(new int[]
						{
								(int) posX + x, (int) posY + y, (int) posZ + z
						});
				}

		return blocks;
	}

	public static List<int[]> createEmptySphere(World world, int posX, int posY, int posZ, int size, final Block block, String... blockRules)
	{
		List<int[]> blocks = new ArrayList<int[]>();

		Sphere.generate(posX, posY, posZ, size, new ISphere()
		{
			@Override
			public void call(int x, int y, int z)
			{
				placeBlockIfAllowed(world, x, y, z, block, blockRules);
				blocks.add(new int[]
					{
							x, y, z
					});
			}
		});

		return blocks;
	}

	public static List<int[]> createFilledSphere(World world, int posX, int posY, int posZ, int size, final Block block, String... blockRules)
	{
		List<int[]> blocks = new ArrayList<int[]>();

		Sphere.generateFilled(posX, posY, posZ, size, new ISphere()
		{
			@Override
			public void call(int x, int y, int z)
			{
				placeBlockIfAllowed(world, x, y, z, block, blockRules);
				blocks.add(new int[]
					{
							x, y, z
					});
			}
		});

		return blocks;
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
		Ability sparClaw = null;// abilityProps.getHotbarAbilityFromName(ModAttributes.SPAR_CLAW.getAttributeName());

		boolean hasSwordInHand = ItemsHelper.isSword(player.getHeldItemMainhand());
		boolean hasSparClaw = devilFruitProps.getDevilFruit().equalsIgnoreCase("supa_supa") && sparClaw != null && sparClaw.isContinuous();
		
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
		// QuestProperties questProps = QuestProperties.get(player);

		IEntityStats props = EntityStatsCapability.get(player);
		IAbilityData abilityProps = AbilityDataCapability.get(player);
		if (props.isSwordsman())
		{
			if (!verifyIfAbilityIsBanned(ShiShishiSonsonAbility.INSTANCE))
				abilityProps.addUnlockedAbility(ShiShishiSonsonAbility.INSTANCE);
			if (CommonConfig.instance.isQuestProgressionEnabled())
			{
				// if (questProps.hasQuestCompleted(ListQuests.swordsmanProgression04) && !verifyIfAbilityIsBanned(SwordsmanAbilities.SANBYAKUROKUJUPOUNDHO))
				// abilityProps.addRacialAbility(SwordsmanAbilities.SANBYAKUROKUJUPOUNDHO);
			}
			else
			{
				if (!verifyIfAbilityIsBanned(SanbyakurokujuPoundHoAbility.INSTANCE))
					abilityProps.addUnlockedAbility(SanbyakurokujuPoundHoAbility.INSTANCE);
				if (!verifyIfAbilityIsBanned(YakkodoriAbility.INSTANCE))
					abilityProps.addUnlockedAbility(YakkodoriAbility.INSTANCE);
				if (!verifyIfAbilityIsBanned(OTatsumakiAbility.INSTANCE))
					abilityProps.addUnlockedAbility(OTatsumakiAbility.INSTANCE);
			}
		}
/*		else if (props.isSniper())
		{
			if (!verifyIfAbilityIsBanned(SniperAbilities.KAENBOSHI))
				abilityProps.addRacialAbility(SniperAbilities.KAENBOSHI);
			if (CommonConfig.instance.isQuestProgressionEnabled())
			{
			}
			else
			{
				if (!verifyIfAbilityIsBanned(SniperAbilities.KEMURIBOSHI))
					abilityProps.addRacialAbility(SniperAbilities.KEMURIBOSHI);
				if (!verifyIfAbilityIsBanned(SniperAbilities.RENPATSUNAMARIBOSHI))
					abilityProps.addRacialAbility(SniperAbilities.RENPATSUNAMARIBOSHI);
				if (!verifyIfAbilityIsBanned(SniperAbilities.SAKURETSUSABOTENBOSHI))
					abilityProps.addRacialAbility(SniperAbilities.SAKURETSUSABOTENBOSHI);
			}
		}
*/
	}

	public static boolean isSniperAbility(Ability abl)
	{
		/*
		 * for (Ability a : SniperAbilities.abilitiesArray)
		 * {
		 * if (abl.getAttribute().getAttributeName().equalsIgnoreCase(a.getAttribute().getAttributeName()))
		 * return true;
		 * }
		 * return false;
		 */
		return false;
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
				fullModel = "model" + model;
				break;
			}
		}

		if (fullName.equals("yamidummy"))
			fullName = "yamiyami";

		return new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(APIConfig.PROJECT_ID, fullName.replace(model, "") + "_no_mi" + fullModel)));
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
