package xyz.pixelatedw.wypi;

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
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SSpawnParticlePacket;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.ResourceLocationException;
import net.minecraft.util.Rotation;
import net.minecraft.util.Util;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.template.IntegrityProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.spawner.WorldEntitySpawner;

public class WyHelper
{

	/*
	 * String Helpers
	 */
	
	public static boolean isNullOrEmpty(String str)
	{
		if (str != null && !str.isEmpty() && !str.equalsIgnoreCase("n/a"))
			return false;
		return true;
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

	public static String getResourceName(String text)
	{
		return text.replaceAll("[ \\t]+$", "").replaceAll("\\s+", "_").replaceAll("[\\'\\:\\-\\,\\#]", "").replaceAll("\\&", "and").toLowerCase();
	}

	/*
	 * Color Helpers
	 */

	public static Color hslToColor(float h, float s, float l)
	{
		float[] hsl = new float[] { h, s, l };

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

	public static String rgbToHex(int red, int green, int blue)
	{
		return String.format("#%02X%02X%02X", red, green, blue);
	}

	public static Color hexToRGB(String hexColor)
	{
		if (hexColor.startsWith("#"))
			hexColor = hexColor.substring(1);

		if (hexColor.length() == 8)
		{
			int r = Integer.parseInt(hexColor.substring(0, 2), 16);
			int g = Integer.parseInt(hexColor.substring(2, 4), 16);
			int b = Integer.parseInt(hexColor.substring(4, 6), 16);
			int a = Integer.parseInt(hexColor.substring(6, 8), 16);
			return new Color(r, g, b, a);
		}
		else
			return Color.decode("#" + hexColor);
	}

	public static Color getComplementaryColor(Color color)
	{
		return new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue());
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

	/*
	 * Player/World Helpers
	 */

	public static void spawnParticles(IParticleData data, ServerWorld world, double posX, double posY, double posZ)
	{
		IPacket<?> ipacket = new SSpawnParticlePacket(data, true, (float) posX, (float) posY, (float) posZ, 0, 0, 0, 0, 1);

		for (int j = 0; j < world.getPlayers().size(); ++j)
		{
			ServerPlayerEntity player = world.getPlayers().get(j);
			BlockPos blockpos = new BlockPos(player.posX, player.posY, player.posZ);
			if (blockpos.withinDistance(new Vec3d(posX, posY, posZ), 512))
			{
				player.connection.sendPacket(ipacket);
			}
		}
	}

	public static Vec3d propulsion(LivingEntity entity, double extraVelX, double extraVelZ)
	{
		return propulsion(entity, extraVelX, 0, extraVelZ);
	}

	public static Vec3d propulsion(LivingEntity entity, double extraVelX, double extraVelY, double extraVelZ)
	{
		return entity.getLook(1).mul(extraVelX, extraVelY, extraVelZ);
	}

	public static <T extends Entity> List<T> getEntitiesNear(BlockPos pos, World world, double radius)
	{
		return (List<T>) getEntitiesNear(pos, world, radius, LivingEntity.class);
	}

	public static <T extends Entity> List<T> getEntitiesNear(BlockPos pos, World world, double radius, Class<? extends T>... classEntities)
	{
		return getEntitiesNear(pos, world, radius, null, classEntities);
	}
	
	public static <T extends Entity> List<T> getEntitiesNear(BlockPos pos, World world, double radius, Predicate<Entity> predicate, Class<? extends T>... classEntities)
	{
		if(predicate == null)
			predicate = EntityPredicates.NOT_SPECTATING;
		
		AxisAlignedBB aabb = new AxisAlignedBB(pos).grow(radius, radius, radius);
		List<T> list = new ArrayList<T>();
		for (Class<? extends T> clzz : classEntities)
		{
			list.addAll(world.getEntitiesWithinAABB(clzz, aabb, predicate));
		}
		return list;
	}

	public static BlockRayTraceResult rayTraceBlocks(Entity source)
	{
		float f = 1.0F;
		float f1 = source.prevRotationPitch + (source.rotationPitch - source.prevRotationPitch) * f;
		float f2 = source.prevRotationYaw + (source.rotationYaw - source.prevRotationYaw) * f;
		double d = source.prevPosX + (source.posX - source.prevPosX) * f;
		double d1 = (source.prevPosY + (source.posY - source.prevPosY) * f + 1.6200000000000001D) - source.getYOffset();
		double d2 = source.prevPosZ + (source.posZ - source.prevPosZ) * f;
		Vec3d vec3d = new Vec3d(d, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.01745329F - 3.141593F);
		float f4 = MathHelper.sin(-f2 * 0.01745329F - 3.141593F);
		float f5 = -MathHelper.cos(-f1 * 0.01745329F);
		float f6 = MathHelper.sin(-f1 * 0.01745329F);
		float f7 = f4 * f5;
		float f9 = f3 * f5;
		double d3 = 5000D;

		Vec3d vec3 = vec3d.add(f7 * d3, f6 * d3, f9 * d3);
		BlockRayTraceResult ray = source.world.rayTraceBlocks(new RayTraceContext(vec3d, vec3, RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.ANY, source));

		return ray;
	}

	public static BlockRayTraceResult rayTraceBlocksWithDistance(Entity source, double distance)
	{

		Vec3d lookVec = source.getLook(1.0F);
		Vec3d startVec = source.getEyePosition(1.0F);
		Vec3d endVec = startVec.add(lookVec.x * distance, lookVec.y * distance, lookVec.z * distance);

		BlockRayTraceResult ray = source.world.rayTraceBlocks(new RayTraceContext(startVec, endVec, RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.ANY, source));

		return ray;
	}

	public static EntityRayTraceResult rayTraceEntities(Entity source, double distance)
	{
		Vec3d lookVec = source.getLook(1.0F);
		Vec3d startVec = source.getEyePosition(1.0F);
		Vec3d endVec = startVec.add(lookVec.x * distance, lookVec.y * distance, lookVec.z * distance);
		AxisAlignedBB boundingBox = source.getBoundingBox().grow(distance);

		for (Entity entity : source.world.getEntitiesInAABBexcluding(source, boundingBox, (entity) ->
		{
			return entity != source;
		}))
		{
			AxisAlignedBB entityBB = entity.getBoundingBox().grow(1);
			Optional<Vec3d> optional = entityBB.rayTrace(startVec, endVec);

			if (optional.isPresent())
			{
				Vec3d targetVec = optional.get();
				double distFromSource = MathHelper.sqrt(startVec.squareDistanceTo(targetVec));

				if (distFromSource < distance)
				{
					List<Entity> targets = WyHelper.getEntitiesNear(new BlockPos(targetVec), source.world, 1.25);
					targets.remove(source);
					Optional<Entity> target = targets.stream().findFirst();

					if (target.isPresent())
					{
						return new EntityRayTraceResult(target.get(), endVec);
					}
				}
			}
		}

		return new EntityRayTraceResult(null, endVec);
	}

	public static void sendMsgToPlayer(PlayerEntity player, String text)
	{
		player.sendMessage(new StringTextComponent(text));
	}

	public static boolean isBlockNearby(LivingEntity player, int radius, Block... blocks)
	{
		for (Block b : blocks)
		{
			for (int x = -radius; x <= radius; x++)
				for (int y = -radius; y <= radius; y++)
					for (int z = -radius; z <= radius; z++)
					{
						if (player.world.getBlockState(new BlockPos((int) player.posX + x, (int) player.posY + y, (int) player.posZ + z)).getBlock() == b)
						{
							return true;
						}
					}
		}

		return false;
	}

	public static List<BlockPos> getNearbyBlocks(LivingEntity player, int radius)
	{
		return getNearbyBlocks(player.getPosition(), player.world, radius);
	}

	public static List<BlockPos> getNearbyBlocks(BlockPos pos, World world, int radius)
	{
		List<BlockPos> blockLocations = new ArrayList<BlockPos>();
		for (int x = -radius; x <= radius; x++)
		{
			for (int y = -radius; y <= radius; y++)
			{
				for (int z = -radius; z <= radius; z++)
				{
					BlockPos newPos = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
					if (world.getBlockState(newPos).getBlock() != Blocks.AIR)
					{
						blockLocations.add(newPos);
					}
				}
			}
		}

		return blockLocations;
	}
	
	public static List<BlockPos> getNearbyTileEntities(LivingEntity player, int radius)
	{
		return getNearbyTileEntities(player.getPosition(), player.world, radius);
	}
	
	public static List<BlockPos> getNearbyTileEntities(BlockPos pos, World world, int radius)
	{
		List<BlockPos> blockLocations = new ArrayList<BlockPos>();
		for (int x = -radius; x <= radius; x++)
		{
			for (int y = -radius; y <= radius; y++)
			{
				for (int z = -radius; z <= radius; z++)
				{
					BlockPos newPos = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
					if (world.getBlockState(newPos).getBlock() != Blocks.AIR && world.getTileEntity(newPos) != null)
					{
						blockLocations.add(newPos);
					}
				}
			}
		}

		return blockLocations;
	}
	
	@Nullable
	public static BlockPos findOnGroundSpawnLocation(World world, EntityType type, BlockPos spawnLocation, int radius)
	{
		BlockPos blockpos = null;
		Random random = new Random();

		for (int i = 0; i < 10; ++i)
		{
			int j = spawnLocation.getX() + random.nextInt(radius * 2) - radius;
			int k = spawnLocation.getZ() + random.nextInt(radius * 2) - radius;
			int l = world.getHeight(Heightmap.Type.WORLD_SURFACE, j, k);
			BlockPos blockpos1 = new BlockPos(j, l, k);
			if (WorldEntitySpawner.canCreatureTypeSpawnAtLocation(EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, world, blockpos1, type))
			{
				blockpos = blockpos1;
				break;
			}
		}

		return blockpos;
	}

	/*
	 * Date Helpers
	 */

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

	/*
	 * Math Helpers
	 */

	public static double percentage(double percent, double value)
	{
		return (percent / 100) * value;
	}

	public static double randomWithRange(int min, int max)
	{
		return new Random().nextInt(max + 1 - min) + min;
	}

	public static double randomDouble()
	{
		return new Random().nextDouble() * 2 - 1;
	}

	public static int round(int value)
	{
		String valueString = "" + value;
		
		if(valueString.length() < 1)
			return value;
		
		return round(value, valueString.length() - 1);
	}
	
	public static int round(int value, int nth)
	{
		String valueString = "" + value;
		
		if(valueString.length() < 1 || nth < 0)
			return value;
		
		if(nth == 0)
			nth = 1;
		
		int n = (int) Math.pow(10, nth - 1);
		int r = 5 * (n / 10);

		return ((value + r) / n ) * n;
	}
	
	/*
	 * Rendering Helpers
	 */

	public static void drawColourOnScreen(int colour, int alpha, double posX, double posY, double width, double height, double zLevel)
	{
		int r = (colour >> 16 & 0xff);
		int g = (colour >> 8 & 0xff);
		int b = (colour & 0xff);
		drawColourOnScreen(r, g, b, alpha, posX, posY, width, height, zLevel);
	}

	public static void drawColourOnScreen(int red, int green, int blue, int alpha, double posX, double posY, double width, double height, double zLevel)
	{
		if (width <= 0 || height <= 0)
			return;
		GlStateManager.enableBlend();
		GlStateManager.disableTexture();
		BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
		bufferbuilder.pos(posX, posY + height, zLevel).color(red, green, blue, alpha).endVertex();
		bufferbuilder.pos(posX + width, posY + height, zLevel).color(red, green, blue, alpha).endVertex();
		bufferbuilder.pos(posX + width, posY, zLevel).color(red, green, blue, alpha).endVertex();
		bufferbuilder.pos(posX, posY, zLevel).color(red, green, blue, alpha).endVertex();
		Tessellator.getInstance().draw();
		GlStateManager.enableTexture();
		GlStateManager.disableBlend();
	}

	public static void drawIcon(ResourceLocation rs, int x, int y, int u, int v)
	{
		Minecraft.getInstance().getTextureManager().bindTexture(rs);
		BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(x, y + v, 1).tex(0.0, 1.0).endVertex();
		bufferbuilder.pos(x + u, y + v, 1).tex(1.0, 1.0).endVertex();
		bufferbuilder.pos(x + u, y, 1).tex(1.0, 0.0).endVertex();
		bufferbuilder.pos(x, y, 1).tex(0.0, 0.0).endVertex();
		Tessellator.getInstance().draw();
	}

	public static void drawEntityOnScreen(int posX, int posY, int scale, float mouseX, float mouseY, LivingEntity entity)
	{
		GlStateManager.enableColorMaterial();
		GlStateManager.pushMatrix();
		GlStateManager.translatef(posX, posY, 50.0F);
		GlStateManager.scalef((-scale), scale, scale);
		GlStateManager.rotatef(180.0F, 0.0F, 0.0F, 1.0F);
		float f = entity.renderYawOffset;
		float f1 = entity.rotationYaw;
		float f2 = entity.rotationPitch;
		float f3 = entity.prevRotationYawHead;
		float f4 = entity.rotationYawHead;
		GlStateManager.rotatef(135.0F, 0.0F, 1.0F, 0.0F);
		RenderHelper.enableStandardItemLighting();
		GlStateManager.rotatef(-135.0F, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotatef(-((float) Math.atan(mouseY / 40.0F)) * 20.0F, 1.0F, 0.0F, 0.0F);
		entity.renderYawOffset = (float) Math.atan(mouseX / 40.0F) * 20.0F;
		entity.rotationYaw = (float) Math.atan(mouseX / 40.0F) * 40.0F;
		entity.rotationPitch = -((float) Math.atan(mouseY / 40.0F)) * 20.0F;
		entity.rotationYawHead = entity.rotationYaw;
		entity.prevRotationYawHead = entity.rotationYaw;
		GlStateManager.translatef(0.0F, 0.0F, 0.0F);
		EntityRendererManager entityrenderermanager = Minecraft.getInstance().getRenderManager();
		entityrenderermanager.setPlayerViewY(180.0F);
		entityrenderermanager.setRenderShadow(false);
		entityrenderermanager.renderEntity(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, false);
		entityrenderermanager.setRenderShadow(true);
		entity.renderYawOffset = f;
		entity.rotationYaw = f1;
		entity.rotationPitch = f2;
		entity.prevRotationYawHead = f3;
		entity.rotationYawHead = f4;
		GlStateManager.popMatrix();
		RenderHelper.disableStandardItemLighting();
		GlStateManager.disableRescaleNormal();
		GlStateManager.activeTexture(GLX.GL_TEXTURE1);
		GlStateManager.disableTexture();
		GlStateManager.activeTexture(GLX.GL_TEXTURE0);
	}

	public static void drawStringWithBorder(FontRenderer font, String text, int posX, int posY, int color)
	{
		font.drawStringWithShadow(text, posX, posY - 1, 1);
		font.drawStringWithShadow(text, posX, posY + 1, 1);
		font.drawStringWithShadow(text, posX + 1, posY, 1);
		font.drawStringWithShadow(text, posX - 1, posY, 1);
		font.drawStringWithShadow(text, posX, posY, color);
	}

	public static List<String> splitString(FontRenderer font, String text, int posX, int wrapWidth)
	{
		while (text != null && text.endsWith("\n"))
		{
			text = text.substring(0, text.length() - 1);
		}

		List<String> newText = new ArrayList<String>();
		for (String s : font.listFormattedStringToWidth(text, wrapWidth))
		{
			if (font.getBidiFlag())
			{
				int i = font.getStringWidth(font.bidiReorder(s));
				posX += wrapWidth - i;
			}

			newText.add(s);
		}
		return newText;
	}

	public static float handleRotationFloat(LivingEntity entity, float partialTicks)
	{
		return entity.ticksExisted + partialTicks;
	}

	public static void rotateCorpse(LivingEntity entityLiving, float ageInTicks, float headYawOffset, float v)
	{
		GlStateManager.rotated(180.0F + headYawOffset, 0.0F, 1.0F, 0.0F);

		if (entityLiving.deathTime > 0)
		{
			float f3 = (entityLiving.deathTime + v - 1.0F) / 20.0F * 1.6F;
			f3 = MathHelper.sqrt(f3);

			if (f3 > 1.0F)
			{
				f3 = 1.0F;
			}

			GL11.glRotatef(f3 * 90, 0.0F, 0.0F, 1.0F);
		}
	}

	public static float interpolateRotation(float lowerLimit, float upperLimit, float range)
	{
		float f3;

		for (f3 = upperLimit - lowerLimit; f3 < -180.0F; f3 += 360.0F)
		{
			;
		}

		while (f3 >= 180.0F)
		{
			f3 -= 360.0F;
		}

		return lowerLimit + range * f3;
	}

	/*
	 * Misc Helpers
	 */

	public static <T> List<T> shuffle(List<T> ar)
	{
		Random rnd = new Random();

		for (int i = ar.size() - 1; i > 0; i--)
		{
			int index = rnd.nextInt(i + 1);
			// Simple swap
			T a = ar.get(index);
			ar.set(index, ar.get(i));
			ar.set(i, a);
		}

		return ar;
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

	public static final int getIndexOfItemStack(ItemStack stack, IInventory inven)
	{
		for (int i = 0; i < inven.getSizeInventory(); i++)
		{
			if (inven.getStackInSlot(i).getItem() == stack.getItem())
			{
				return i;
			}
		}
		return -1;
	}

	public static boolean saveNBTStructure(ServerWorld world, String name, BlockPos pos, BlockPos size)
	{
		if (!world.isRemote)
		{
			ServerWorld serverworld = world;
			TemplateManager templatemanager = serverworld.getStructureTemplateManager();
			ResourceLocation res = new ResourceLocation(APIConfig.PROJECT_ID, name);

			Template template;
			try
			{
				template = templatemanager.getTemplateDefaulted(res);
			}
			catch (ResourceLocationException ex)
			{
				ex.printStackTrace();
				return false;
			}

			template.takeBlocksFromWorld(world, pos, size, false, Blocks.STRUCTURE_VOID);
			template.setAuthor("?");
			try
			{
				return templatemanager.writeToFile(res);
			}
			catch (ResourceLocationException var7)
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}

	public static boolean loadNBTStructure(ServerWorld world, String name, BlockPos pos)
	{
		if (!world.isRemote)
		{
			BlockPos blockpos = pos;
			ServerWorld serverworld = world;
			TemplateManager templatemanager = serverworld.getStructureTemplateManager();
			ResourceLocation res = new ResourceLocation(APIConfig.PROJECT_ID, name);

			Template template;
			try
			{
				template = templatemanager.getTemplate(res);
			}
			catch (ResourceLocationException ex)
			{
				ex.printStackTrace();
				return false;
			}

			if (template == null)
			{
				return false;
			}
			else
			{
				BlockState blockstate = world.getBlockState(blockpos);
				world.notifyBlockUpdate(blockpos, blockstate, blockstate, 3);
			}

			PlacementSettings placementsettings = (new PlacementSettings()).setMirror(Mirror.NONE).setRotation(Rotation.NONE).setIgnoreEntities(true).setChunk((ChunkPos) null);
			placementsettings.clearProcessors().addProcessor(new IntegrityProcessor(MathHelper.clamp(1, 0.0F, 1.0F))).setRandom(new Random(Util.milliTime()));

			template.addBlocksToWorldChunk(world, pos, placementsettings);
			return true;
		}
		else
		{
			return false;
		}
	}

	public static boolean isSurfaceFlat(ChunkGenerator<?> chunkGen, int chunkPosX, int chunkPosZ, int difference)
	{
		int offset = 16;

		int xStart = (chunkPosX << 4) + (7 - (offset / 2));
		int zStart = (chunkPosZ << 4) + (7 - (offset / 2));

		int i1 = chunkGen.func_222531_c(xStart, zStart, Heightmap.Type.WORLD_SURFACE_WG);
		int j1 = chunkGen.func_222531_c(xStart, zStart + offset, Heightmap.Type.WORLD_SURFACE_WG);
		int k1 = chunkGen.func_222531_c(xStart + offset, zStart, Heightmap.Type.WORLD_SURFACE_WG);
		int l1 = chunkGen.func_222531_c(xStart + offset, zStart + offset, Heightmap.Type.WORLD_SURFACE_WG);
		int minHeight = Math.min(Math.min(i1, j1), Math.min(k1, l1));
		int maxHeight = Math.max(Math.max(i1, j1), Math.max(k1, l1));

		return Math.abs(maxHeight - minHeight) <= difference;
	}

	public static String getTextureName(String t) {
		for(String s : t.split("/")) {
			if(s.contains(".png")) {
				return s.replace(".png", "");
			}
		}
		return null;
	}
}
