package xyz.pixelatedw.mineminenomi.api;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.pixelatedw.mineminenomi.api.abilities.extra.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.math.ISphere;
import xyz.pixelatedw.mineminenomi.api.math.Sphere;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;

public class WyHelper
{

	public enum Direction
	{
		SOUTH, SOUTH_EAST, EAST, NORTH, NORTH_EAST, NORTH_WEST, WEST, SOUTH_WEST;
	}

	private static HashMap<String, List<Block>> blockRules = createBlockRulesMap();

	private static HashMap<String, List<Block>> createBlockRulesMap()
	{
		HashMap<String, List<Block>> map = new HashMap<String, List<Block>>();

		map.put("core", Arrays.asList(new Block[]
			{
					Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.GRASS_BLOCK, Blocks.PODZOL, Blocks.MYCELIUM, Blocks.GRAVEL, Blocks.SAND, Blocks.RED_SAND, Blocks.CLAY, Blocks.STONE, Blocks.COBBLESTONE, 
					Blocks.SMOOTH_STONE, Blocks.GRANITE, Blocks.POLISHED_GRANITE, Blocks.DIORITE, Blocks.POLISHED_DIORITE, Blocks.OBSIDIAN, Blocks.ANDESITE, Blocks.POLISHED_ANDESITE, Blocks.MOSSY_COBBLESTONE,
					Blocks.BRICKS, Blocks.BONE_BLOCK, Blocks.SANDSTONE, Blocks.TERRACOTTA, Blocks.SNOW, Blocks.SNOW_BLOCK, Blocks.ICE, Blocks.PACKED_ICE, Blocks.BLUE_ICE, Blocks.SEA_LANTERN, Blocks.PRISMARINE,
					Blocks.PRISMARINE_BRICKS, Blocks.DARK_PRISMARINE, Blocks.FARMLAND, Blocks.GRASS_PATH, Blocks.GLASS, Blocks.BOOKSHELF, Blocks.STONE_BRICKS, Blocks.MOSSY_STONE_BRICKS, Blocks.CRACKED_STONE_BRICKS,
					Blocks.CHISELED_STONE_BRICKS, Blocks.SPONGE, Blocks.SLIME_BLOCK, Blocks.CARVED_PUMPKIN, Blocks.HAY_BLOCK, Blocks.DRIED_KELP_BLOCK, Blocks.ACACIA_LOG, Blocks.BIRCH_LOG, Blocks.DARK_OAK_LOG,
					Blocks.JUNGLE_LOG, Blocks.OAK_LOG, Blocks.SPRUCE_LOG, Blocks.STRIPPED_ACACIA_LOG, Blocks.STRIPPED_ACACIA_WOOD, Blocks.STRIPPED_ACACIA_LOG, Blocks.STRIPPED_BIRCH_LOG, Blocks.STRIPPED_BIRCH_WOOD, 
					Blocks.STRIPPED_DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_WOOD, Blocks.STRIPPED_JUNGLE_LOG, Blocks.STRIPPED_JUNGLE_WOOD, Blocks.STRIPPED_OAK_LOG, Blocks.STRIPPED_OAK_WOOD, Blocks.STRIPPED_SPRUCE_LOG,
					Blocks.STRIPPED_SPRUCE_WOOD, Blocks.ACACIA_WOOD, Blocks.BIRCH_WOOD, Blocks.DARK_OAK_WOOD, Blocks.JUNGLE_WOOD, Blocks.OAK_WOOD, Blocks.SPRUCE_WOOD, Blocks.ACACIA_PLANKS, Blocks.BIRCH_PLANKS,
					Blocks.DARK_OAK_PLANKS, Blocks.JUNGLE_PLANKS, Blocks.OAK_PLANKS, Blocks.SPRUCE_PLANKS, Blocks.FROSTED_ICE, Blocks.ACACIA_SLAB, Blocks.ANDESITE_SLAB, Blocks.BIRCH_SLAB, Blocks.BIRCH_SLAB,
					Blocks.BRICK_SLAB, Blocks.COBBLESTONE_SLAB, Blocks.DARK_OAK_SLAB, Blocks.DIORITE_SLAB, Blocks.DARK_PRISMARINE_SLAB, Blocks.END_STONE_BRICK_SLAB, Blocks.GRANITE_SLAB, Blocks.JUNGLE_SLAB,
					Blocks.MOSSY_COBBLESTONE_SLAB, Blocks.MOSSY_STONE_BRICK_SLAB, Blocks.NETHER_BRICK_SLAB, Blocks.OAK_SLAB, Blocks.PETRIFIED_OAK_SLAB, Blocks.POLISHED_ANDESITE_SLAB, Blocks.POLISHED_DIORITE_SLAB,
					Blocks.POLISHED_GRANITE_SLAB, Blocks.PRISMARINE_BRICK_SLAB, Blocks.PRISMARINE_SLAB, Blocks.PURPUR_SLAB, Blocks.QUARTZ_SLAB, Blocks.RED_NETHER_BRICK_SLAB, Blocks.RED_SANDSTONE_SLAB,
					Blocks.SANDSTONE_SLAB, Blocks.SANDSTONE_SLAB, Blocks.SMOOTH_RED_SANDSTONE_SLAB, Blocks.SMOOTH_SANDSTONE_SLAB, Blocks.SMOOTH_STONE_SLAB, Blocks.SPRUCE_SLAB,
					Blocks.STONE_BRICK_SLAB, Blocks.STONE_SLAB, Blocks.ACACIA_STAIRS, Blocks.ANDESITE_STAIRS, Blocks.BIRCH_STAIRS, Blocks.BIRCH_STAIRS, Blocks.BRICK_STAIRS, Blocks.COBBLESTONE_STAIRS,
					Blocks.DARK_OAK_STAIRS, Blocks.DARK_PRISMARINE_STAIRS, Blocks.DIORITE_STAIRS, Blocks.END_STONE_BRICK_STAIRS, Blocks.GRANITE_STAIRS, Blocks.JUNGLE_STAIRS, Blocks.MOSSY_COBBLESTONE_STAIRS,
					Blocks.MOSSY_STONE_BRICK_STAIRS, Blocks.NETHER_BRICK_STAIRS, Blocks.OAK_STAIRS, Blocks.POLISHED_ANDESITE_STAIRS, Blocks.POLISHED_DIORITE_STAIRS, Blocks.POLISHED_GRANITE_STAIRS,
					Blocks.PRISMARINE_BRICK_STAIRS, Blocks.PRISMARINE_STAIRS, Blocks.PURPUR_STAIRS, Blocks.QUARTZ_STAIRS, Blocks.RED_NETHER_BRICK_STAIRS, Blocks.RED_SANDSTONE_STAIRS, Blocks.SANDSTONE_STAIRS,
					Blocks.SMOOTH_RED_SANDSTONE_STAIRS, Blocks.SMOOTH_RED_SANDSTONE_STAIRS, Blocks.SMOOTH_SANDSTONE_STAIRS, Blocks.SPRUCE_STAIRS, Blocks.STONE_BRICK_STAIRS,
					Blocks.STONE_STAIRS, Blocks.NETHERRACK, Blocks.SOUL_SAND, Blocks.GLOWSTONE, Blocks.MAGMA_BLOCK, Blocks.NETHER_BRICKS, Blocks.RED_NETHER_BRICKS, Blocks.NETHER_BRICK_FENCE, Blocks.END_STONE,
					Blocks.PURPUR_BLOCK, Blocks.PURPUR_PILLAR, Blocks.END_STONE_BRICKS, Blocks.END_ROD, Blocks.BLACK_WOOL, Blocks.BLUE_WOOL, Blocks.BROWN_WOOL, Blocks.CYAN_WOOL, Blocks.GRAY_WOOL, Blocks.GREEN_WOOL,
					Blocks.LIGHT_BLUE_WOOL, Blocks.LIGHT_GRAY_WOOL, Blocks.LIME_WOOL, Blocks.MAGENTA_WOOL, Blocks.ORANGE_WOOL, Blocks.PINK_WOOL, Blocks.PURPLE_WOOL, Blocks.RED_WOOL, Blocks.WHITE_WOOL,
					Blocks.YELLOW_WOOL, Blocks.CRAFTING_TABLE, Blocks.BLAST_FURNACE, Blocks.FURNACE, Blocks.CAMPFIRE, Blocks.SMOKER, Blocks.STONECUTTER, Blocks.BARREL, Blocks.ENCHANTING_TABLE, Blocks.LOOM,
					Blocks.FLETCHING_TABLE, Blocks.CARTOGRAPHY_TABLE, Blocks.ANVIL, Blocks.SMITHING_TABLE, Blocks.GRINDSTONE, Blocks.BEACON, Blocks.JUKEBOX, Blocks.SCAFFOLDING, Blocks.BELL, Blocks.LECTERN,
					Blocks.COMPOSTER, Blocks.BREWING_STAND, Blocks.CAULDRON, Blocks.NOTE_BLOCK, Blocks.HOPPER, Blocks.TNT, Blocks.DISPENSER, Blocks.DROPPER, Blocks.OBSERVER, Blocks.REDSTONE_LAMP,
					Blocks.PISTON, Blocks.STICKY_PISTON, Blocks.DAYLIGHT_DETECTOR, Blocks.REDSTONE_WIRE, Blocks.REPEATER, Blocks.COMPARATOR, Blocks.TRIPWIRE_HOOK, Blocks.REDSTONE_TORCH, Blocks.REDSTONE_WALL_TORCH,
					Blocks.TORCH, Blocks.WALL_TORCH, Blocks.ACACIA_TRAPDOOR, Blocks.BIRCH_TRAPDOOR, Blocks.DARK_OAK_TRAPDOOR, Blocks.IRON_TRAPDOOR, Blocks.JUNGLE_TRAPDOOR, Blocks.OAK_TRAPDOOR, Blocks.SPRUCE_TRAPDOOR,
					Blocks.ACACIA_DOOR, Blocks.BIRCH_DOOR, Blocks.DARK_OAK_DOOR, Blocks.IRON_DOOR, Blocks.JUNGLE_DOOR, Blocks.OAK_DOOR, Blocks.SPRUCE_DOOR, Blocks.RAIL, Blocks.POWERED_RAIL, Blocks.ACTIVATOR_RAIL,
					Blocks.DETECTOR_RAIL, Blocks.ACACIA_FENCE, Blocks.ACACIA_FENCE_GATE, Blocks.BIRCH_FENCE, Blocks.BIRCH_FENCE_GATE, Blocks.DARK_OAK_FENCE, Blocks.DARK_OAK_FENCE_GATE, Blocks.JUNGLE_FENCE,
					Blocks.JUNGLE_FENCE_GATE, Blocks.OAK_FENCE, Blocks.OAK_FENCE_GATE, Blocks.SPRUCE_FENCE, Blocks.SPRUCE_FENCE_GATE, Blocks.IRON_BARS, Blocks.GLASS_PANE, Blocks.LADDER, Blocks.COBWEB, Blocks.LANTERN,
					Blocks.SEA_PICKLE, Blocks.FLOWER_POT, Blocks.BRAIN_CORAL_BLOCK, Blocks.BUBBLE_CORAL_BLOCK, Blocks.DEAD_BRAIN_CORAL_BLOCK, Blocks.DEAD_BUBBLE_CORAL_BLOCK, Blocks.DEAD_HORN_CORAL_BLOCK,
					Blocks.DEAD_TUBE_CORAL_BLOCK, Blocks.FIRE_CORAL_BLOCK, Blocks.HORN_CORAL_BLOCK, Blocks.TUBE_CORAL_BLOCK
			}));

		map.put("air", Arrays.asList(new Block[]
			{
					Blocks.AIR
			}));

		map.put("foliage", Arrays.asList(new Block[]
			{
					Blocks.ACACIA_LEAVES, Blocks.BIRCH_LEAVES, Blocks.DARK_OAK_LEAVES, Blocks.JUNGLE_LEAVES, Blocks.OAK_LEAVES, Blocks.SPRUCE_LEAVES, Blocks.LILY_PAD, Blocks.TALL_GRASS, Blocks.CHORUS_FLOWER, 
					Blocks.LILAC, Blocks.PEONY, Blocks.ROSE_BUSH, Blocks.MUSHROOM_STEM, Blocks.BROWN_MUSHROOM, Blocks.BROWN_MUSHROOM_BLOCK, Blocks.RED_MUSHROOM, Blocks.RED_MUSHROOM_BLOCK, Blocks.POTATOES, 
					Blocks.CARROTS, Blocks.CACTUS, Blocks.DEAD_BUSH, Blocks.GRASS, Blocks.AZURE_BLUET, Blocks.DANDELION, Blocks.VINE, Blocks.PUMPKIN, Blocks.PUMPKIN_STEM, Blocks.MELON, Blocks.MELON_STEM, Blocks.NETHER_WART, 
					Blocks.NETHER_WART_BLOCK, Blocks.CHORUS_FLOWER, Blocks.CHORUS_PLANT, Blocks.BRAIN_CORAL, Blocks.BRAIN_CORAL_FAN, Blocks.BRAIN_CORAL_WALL_FAN, Blocks.DEAD_BRAIN_CORAL, Blocks.DEAD_BRAIN_CORAL_FAN,
					Blocks.DEAD_BRAIN_CORAL_WALL_FAN, Blocks.BUBBLE_CORAL, Blocks.BUBBLE_CORAL_FAN, Blocks.BUBBLE_CORAL_WALL_FAN, Blocks.DEAD_BUBBLE_CORAL, Blocks.DEAD_BUBBLE_CORAL_FAN, Blocks.DEAD_BUBBLE_CORAL_WALL_FAN,
					Blocks.FIRE_CORAL, Blocks.FIRE_CORAL_FAN, Blocks.FIRE_CORAL_WALL_FAN, Blocks.DEAD_FIRE_CORAL, Blocks.DEAD_FIRE_CORAL_FAN, Blocks.DEAD_FIRE_CORAL_WALL_FAN, Blocks.DEAD_TUBE_CORAL, Blocks.DEAD_TUBE_CORAL_FAN,
					Blocks.DEAD_TUBE_CORAL_WALL_FAN, Blocks.TUBE_CORAL, Blocks.TUBE_CORAL_FAN, Blocks.TUBE_CORAL_WALL_FAN, Blocks.DEAD_HORN_CORAL, Blocks.DEAD_HORN_CORAL_FAN, Blocks.DEAD_HORN_CORAL_WALL_FAN,
					Blocks.HORN_CORAL, Blocks.HORN_CORAL_FAN, Blocks.HORN_CORAL_WALL_FAN, Blocks.COCOA, Blocks.SUGAR_CANE, Blocks.BEETROOTS, Blocks.WHEAT, Blocks.FERN, Blocks.LARGE_FERN, Blocks.ACACIA_SAPLING,
					Blocks.BAMBOO_SAPLING, Blocks.BIRCH_SAPLING, Blocks.DARK_OAK_SAPLING, Blocks.JUNGLE_SAPLING, Blocks.OAK_SAPLING, Blocks.BAMBOO
			}));

		map.put("ore", Arrays.asList(new Block[]
			{
					Blocks.COAL_ORE, Blocks.IRON_ORE, Blocks.GOLD_ORE, Blocks.DIAMOND_ORE, Blocks.EMERALD_ORE, Blocks.REDSTONE_ORE, Blocks.LAPIS_ORE, Blocks.NETHER_QUARTZ_ORE
			}));

		map.put("valuable", Arrays.asList(new Block[]
			{
					Blocks.COAL_BLOCK, Blocks.IRON_BLOCK, Blocks.GOLD_BLOCK, Blocks.DIAMOND_BLOCK, Blocks.EMERALD_BLOCK, Blocks.REDSTONE_BLOCK, Blocks.LAPIS_BLOCK, Blocks.QUARTZ_BLOCK, Blocks.CHISELED_QUARTZ_BLOCK,
					Blocks.SMOOTH_QUARTZ, Blocks.SMOOTH_QUARTZ_SLAB, Blocks.SMOOTH_QUARTZ_STAIRS
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

	public static boolean isNullOrEmpty(String str)
	{
		if (str != null && !str.isEmpty() && !str.equalsIgnoreCase("n/a"))
			return false;
		return true;
	}

	public static boolean placeBlockIfAllowed(World world, BlockPos pos, Block toPlace, String... rules)
	{
		return placeBlockIfAllowed(world, pos.getX(), pos.getY(), pos.getZ(), toPlace, 3, rules);
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
				bannedBlocks.remove(Blocks.BEDROCK);
				bannedBlocks.remove(ModBlocks.OPE);
				bannedBlocks.remove(ModBlocks.OPE_MID);
				bannedBlocks.remove(ModBlocks.STRING_MID);
				bannedBlocks.remove(ModBlocks.STRING_WALL);
				bannedBlocks.remove(ModBlocks.DARKNESS);
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

	public static boolean afterDate(String date)
	{
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		Calendar target = null;
		try
		{
			target = Calendar.getInstance();
			target.setTime(df.parse(date));
		}
		catch (ParseException e)
		{
			e.printStackTrace();
			return false;
		}

		Calendar now = Calendar.getInstance();
		return now.after(target);
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

	public static boolean isBlockNearby(LivingEntity player, int radius, Block... blocks)
	{
		for (Block b : blocks)
		{
			for (int x = -radius; x < radius; x++)
				for (int y = -radius; y < radius; y++)
					for (int z = -radius; z < radius; z++)
					{
						if (player.world.getBlockState(new BlockPos((int) player.posX + x, (int) player.posY + y, (int) player.posZ + z)).getBlock() == b)
						{
							return true;
						}
					}
		}

		return false;
	}

	public static Block getBlockNearby(LivingEntity entity, int radius, Block block)
	{
		for (int x = -radius; x < radius; x++)
			for (int y = -radius; y < radius; y++)
				for (int z = -radius; z < radius; z++)
				{
					if (entity.world.getBlockState(new BlockPos((int) entity.posX + x, (int) entity.posY + y, (int) entity.posZ + z)).getBlock() == block)
					{
						return entity.world.getBlockState(new BlockPos((int) entity.posX + x, (int) entity.posY + y, (int) entity.posZ + z)).getBlock();
					}
				}

		return null;
	}

	public static List<int[]> getBlockLocationsNearby(LivingEntity entity, int[] radius)
	{
		List<int[]> nearbyBlocks = new ArrayList<int[]>();

		for (int x = -radius[0]; x < radius[0]; x++)
			for (int y = -radius[1]; y < radius[1]; y++)
				for (int z = -radius[2]; z < radius[2]; z++)
				{
					nearbyBlocks.add(new int[]
						{
								(int) entity.posX + x, (int) entity.posY + y, (int) entity.posZ + z
						});
				}

		return nearbyBlocks;
	}

	public static List<int[]> getBlockLocationsNearby(LivingEntity entity, int radius)
	{
		return getBlockLocationsNearby(entity, new int[]
			{
					radius, radius, radius
			});
	}

	public static <K extends Comparable, V extends Comparable> Map<K, V> sortAlphabetically(Map<K, V> map)
	{
		List<Map.Entry<K, V>> entries = new LinkedList<Map.Entry<K, V>>(map.entrySet());

		Collections.sort(entries, new Comparator<Map.Entry<K, V>>()
		{
			@Override
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2)
			{
				return o1.getKey().compareTo(o2.getKey());
			}
		});

		Map<K, V> sortedMap = new LinkedHashMap<K, V>();

		for (Map.Entry<K, V> entry : entries)
		{
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		return sortedMap;
	}

	public static Color hslToColor(float h, float s, float l)
	{
		float[] hsl = new float[]
			{
					h, s, l
			};

		if (s < 0.0f || s > 100.0f)
		{
			String message = "Color parameter outside of expected range - Saturation";
			throw new IllegalArgumentException(message);
		}

		if (l < 0.0f || l > 100.0f)
		{
			String message = "Color parameter outside of expected range - Luminance";
			throw new IllegalArgumentException(message);
		}

		h = h % 360.0f;
		h /= 360f;
		s /= 100f;
		l /= 100f;

		float q = 0;

		if (l < 0.5)
			q = l * (1 + s);
		else
			q = (l + s) - (s * l);

		float p = 2 * l - q;

		float r = Math.max(0, hueToRGB(p, q, h + (1.0f / 3.0f)));
		float g = Math.max(0, hueToRGB(p, q, h));
		float b = Math.max(0, hueToRGB(p, q, h - (1.0f / 3.0f)));

		r = Math.min(r, 1.0f);
		g = Math.min(g, 1.0f);
		b = Math.min(b, 1.0f);

		return new Color(r, g, b);
	}

	private static float hueToRGB(float p, float q, float h)
	{
		if (h < 0)
			h += 1;

		if (h > 1)
			h -= 1;

		if (6 * h < 1)
			return p + ((q - p) * 6 * h);

		if (2 * h < 1)
			return q;

		if (3 * h < 2)
			return p + ((q - p) * 6 * ((2.0f / 3.0f) - h));

		return p;
	}

	public static Color hexToRGB(String hexColor)
	{
		if (hexColor.startsWith("#"))
			return Color.decode(hexColor);
		else
			return Color.decode("#" + hexColor);
	}

	public static float colorTolerance(float tolerance)
	{
		return colorTolerance(tolerance, false);
	}

	public static float colorTolerance(float tolerance, boolean hasDisturbance)
	{
		float color = new Random().nextFloat();

		if (color <= tolerance || (!hasDisturbance && color >= tolerance + 0.3))
			return tolerance;

		return color;
	}

	public static String formatBytes(long bytes)
	{
		int unit = 1024;
		if (bytes < unit)
			return bytes + " B";
		int exp = (int) (Math.log(bytes) / Math.log(unit));
		String pre = ("KMGTPE").charAt(exp - 1) + "";
		return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
	}

	public static String upperCaseFirst(String text)
	{
		return Character.toUpperCase(text.charAt(0)) + text.substring(1) + " ";
	}

	public static void sendMsgToPlayer(PlayerEntity player, String text)
	{
		player.sendMessage(new StringTextComponent(text));
	}

	public static String getResourceName(String text)
	{
		return text.replaceAll("[ \\t]+$", "").replaceAll("\\s+", "_").replaceAll("[\\'\\:\\-\\,\\#]", "").toLowerCase();
	}

	public static <T extends Entity> List<T> getEntitiesNear(BlockPos pos, World world, double radius)
	{
		return (List<T>) getEntitiesNear(pos, world, radius, LivingEntity.class);
	}

	public static <T extends Entity> List<T> getEntitiesNear(BlockPos pos, World world, double radius, Class<? extends T>... classEntities)
	{
		AxisAlignedBB aabb = new AxisAlignedBB(pos.add(1, 1, 1)).grow(radius, radius, radius);
		List<T> list = new ArrayList<T>();
		for (Class<? extends T> clzz : classEntities)
		{
			list.addAll(world.getEntitiesWithinAABB(clzz, aabb));
		}
		return list;
	}

	public static Direction get4Directions(Entity e)
	{
		switch (MathHelper.floor(e.rotationYaw * 4.0F / 360.0F + 0.5D) & 3)
		{
			case 0:
				return Direction.SOUTH;
			case 1:
				return Direction.WEST;
			case 2:
				return Direction.NORTH;
			case 3:
				return Direction.EAST;
		}
		return null;
	}

	public static Direction get8Directions(Entity e)
	{
		switch (MathHelper.floor(e.rotationYaw * 8.0F / 360.0F + 0.5D) & 7)
		{
			case 0:
				return Direction.SOUTH;
			case 1:
				return Direction.SOUTH_WEST;
			case 2:
				return Direction.WEST;
			case 3:
				return Direction.NORTH_WEST;
			case 4:
				return Direction.NORTH;
			case 5:
				return Direction.NORTH_EAST;
			case 6:
				return Direction.EAST;
			case 7:
				return Direction.SOUTH_EAST;
		}
		return null;
	}

	public static RayTraceResult rayTraceBlocks(Entity e)
	{
		float f = 1.0F;
		float f1 = e.prevRotationPitch + (e.rotationPitch - e.prevRotationPitch) * f;
		float f2 = e.prevRotationYaw + (e.rotationYaw - e.prevRotationYaw) * f;
		double d = e.prevPosX + (e.posX - e.prevPosX) * f;
		double d1 = (e.prevPosY + (e.posY - e.prevPosY) * f + 1.6200000000000001D) - e.getYOffset();
		double d2 = e.prevPosZ + (e.posZ - e.prevPosZ) * f;
		Vec3d vec3d = new Vec3d(d, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.01745329F - 3.141593F);
		float f4 = MathHelper.sin(-f2 * 0.01745329F - 3.141593F);
		float f5 = -MathHelper.cos(-f1 * 0.01745329F);
		float f6 = MathHelper.sin(-f1 * 0.01745329F);
		float f7 = f4 * f5;
		float f9 = f3 * f5;
		double d3 = 5000D;

		Vec3d vec3 = vec3d.add(f7 * d3, f6 * d3, f9 * d3);
		RayTraceResult ray = e.world.rayTraceBlocks(new RayTraceContext(vec3d, vec3, RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.ANY, e));

		return ray;
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

	public static byte[] serialize(Object obj) throws IOException
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(out);
		os.writeObject(obj);
		return out.toByteArray();
	}

	public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException
	{
		try
		{
			ByteArrayInputStream in = new ByteArrayInputStream(data);
			ObjectInputStream is = new ObjectInputStream(in);
			is.close();
			return is.readObject();
		}
		catch (EOFException e)
		{
			/* EOF Catch */}
		return null;
	}

}
