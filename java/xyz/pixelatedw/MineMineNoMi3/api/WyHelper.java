package xyz.pixelatedw.MineMineNoMi3.api;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import xyz.pixelatedw.MineMineNoMi3.ID;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.Ability;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.extra.AbilityExplosion;
import xyz.pixelatedw.MineMineNoMi3.api.abilities.extra.AbilityManager;
import xyz.pixelatedw.MineMineNoMi3.api.math.ISphere;
import xyz.pixelatedw.MineMineNoMi3.api.math.Sphere;

public class WyHelper 
{

	public enum Direction {SOUTH, SOUTH_EAST, EAST, NORTH, NORTH_EAST, NORTH_WEST, WEST, SOUTH_WEST;}
	
	public static AxisAlignedBB NULL_AABB = AxisAlignedBB.getBoundingBox(0, 0, 0, 0, 0, 0);
	
    public static AbilityExplosion explosion(Entity entity, double posX, double posY, double posZ, float size)
    {
    	AbilityExplosion explosion = new AbilityExplosion(entity, posX, posY, posZ, size);
    	explosion.doExplosionA();
        return explosion;
    }
	
	public static boolean isBlockNearby(EntityLivingBase player, int radius, Block... blocks)
	{
		for(Block b : blocks)
		{
			for (int x = -radius; x < radius; x++) 
			for (int y = -radius; y < radius; y++) 
			for (int z = -radius; z < radius; z++) 
			{
				if(player.worldObj.getBlock((int)player.posX + x, (int)player.posY + y, (int)player.posZ + z) == b)
				{
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static Block getBlockNearby(EntityPlayer player, int radius, Block block)
	{
		for (int x = -radius; x < radius; x++) 
		for (int y = -radius; y < radius; y++) 
		for (int z = -radius; z < radius; z++) 
		{
			if(player.worldObj.getBlock((int)player.posX + x, (int)player.posY + y, (int)player.posZ + z) == block)
			{
				return player.worldObj.getBlock((int)player.posX + x, (int)player.posY + y, (int)player.posZ + z);
			}
		}
		
		return null;
	}
	
	public static <K extends Comparable,V extends Comparable> Map<K,V> sortAlphabetically(Map<K,V> map)
	{
	    List<Map.Entry<K,V>> entries = new LinkedList<Map.Entry<K,V>>(map.entrySet());

	    Collections.sort(entries, new Comparator<Map.Entry<K,V>>() 
	    {
	        public int compare(Entry<K, V> o1, Entry<K, V> o2) 
	        {
	            return o1.getKey().compareTo(o2.getKey());
	        }
	    });

	    Map<K,V> sortedMap = new LinkedHashMap<K,V>();

	    for(Map.Entry<K,V> entry: entries)
	    {
	        sortedMap.put(entry.getKey(), entry.getValue());
	    }

	    return sortedMap;
	}
	
	public static void generateLangFiles()
	{		
		Map<String, String> sorted = sortAlphabetically(WyRegistry.getLangMap());
		Set set = sorted.entrySet();
		Iterator i = set.iterator();
		
		File langFolder = new File(ID.PROJECT_SOURCEFOLDER + "/assets/" + ID.PROJECT_ID + "/lang/");
		langFolder.mkdirs();
		
		if(langFolder.exists())
		{			
			try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ID.PROJECT_SOURCEFOLDER + "/assets/" + ID.PROJECT_ID + "/lang/en_US.lang"), "UTF-8"))) 
			{
				while(i.hasNext())
				{
					Map.Entry entry = (Map.Entry)i.next();
					 
					writer.write(entry.getKey() + "=" + entry.getValue() + "\n");
				}
				writer.close();
			}
			catch(Exception e) {e.getStackTrace();}
		}
	}
	
	public static void generateExtraBotFiles()
	{		
		Map<String, Ability> sorted = AbilityManager.instance().getHashMap();
		Set set = sorted.entrySet();
		Iterator i = set.iterator();
		
		File folder = new File(ID.PROJECT_SOURCEFOLDER + "/assets/" + ID.PROJECT_ID + "/EXTRA_BOT_FIELS/");
		folder.mkdirs();
		
		if(folder.exists())
		{			
			try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ID.PROJECT_SOURCEFOLDER + "/assets/" + ID.PROJECT_ID + "/EXTRA_BOT_FIELS/fruits.data"), "UTF-8"))) 
			{
				while(i.hasNext())
				{
					Map.Entry entry = (Map.Entry)i.next();
					
					Ability abl = ((Ability)entry.getValue());
					
					//writer.write("[\"" + abl.getAttribute().getAttributeName() + "\", \"" + abl + "\", \"Hiken\", \"Higan\", \"Dai Enkai : Entei\", \"Hidaruma\", \"Jujika\", \"Enjomo\"]");
					
					//writer.write(entry.getKey() + "=" + entry.getValue() + "\n");
				}
				writer.close();
			}
			catch(Exception e) {e.getStackTrace();}
		}
	}
	
/*	LEGACY METHOD TO GENERATE 1.8+ JSON MODELS
	public void generateIngameModels()
	{
		Set set = WyPI.apiInstance.getItemsMap().entrySet();
		Iterator i = set.iterator();
		
		while(i.hasNext())
		{
			Map.Entry entry = (Map.Entry)i.next();
			if(entry.getKey() instanceof Item)
			{
				Item item = (Item) entry.getKey();
				Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(WyPI.apiInstance.getParentMod().getParentModID() + ":" + item.getUnlocalizedName().substring(5), "inventory"));
			}
			if(entry.getKey() instanceof Block)
			{
				Block block = (Block) entry.getKey();
				Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(WyPI.apiInstance.getParentMod().getParentModID() + ":" + block.getUnlocalizedName().substring(5), "inventory"));
			}
		}
	}
*/	
	
	/**
	 * 0) is burning; 1) is sneaking; 2) is riding something; 3) is sprinting; 4) is eating; 5) is invisible
	 */
    public static void setEntityFlag(Entity player, int id, boolean bool)
    {
        byte b0 = player.getDataWatcher().getWatchableObjectByte(0);

        if (bool)
        {
        	player.getDataWatcher().updateObject(0, Byte.valueOf((byte)(b0 | 1 << id)));
        }
        else
        {
        	player.getDataWatcher().updateObject(0, Byte.valueOf((byte)(b0 & ~(1 << id))));
        }
    }
	
	public static Color hexToRGB(String hexColor)
	{return Color.decode("#"+hexColor);} 
	
	public static void sendMsgToPlayer(EntityPlayer player, String text)
	{player.addChatComponentMessage(new ChatComponentText(text));}
    
	public static String getFancyName(String text)
	{return text.replaceAll("\\s+","").toLowerCase().replaceAll("'", "").replaceAll("-", "").replaceAll(":", "").replaceAll("#", "").replace(",", "");}

	
    public static List<EntityLivingBase> getEntitiesNear(Entity e, double radius)
    {
	    return getEntitiesNear(e, radius, EntityLivingBase.class);
    }

    public static List<EntityLivingBase> getEntitiesNear(Entity e, double radius, Class <? extends Entity> classEntity)
    { 
    	AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(e.posX, e.posY, e.posZ, e.posX + 1, e.posY + 1, e.posZ + 1).expand(radius, radius, radius);
    	List list = e.worldObj.getEntitiesWithinAABB(classEntity, aabb);
    	list.remove(e);
	    return list;
    }
    
    public static List<EntityLivingBase> getEntitiesNear(TileEntity e, double radius)
    {
		return getEntitiesNear(e, radius, EntityLivingBase.class);
    }

    public static List<EntityLivingBase> getEntitiesNear(TileEntity e, double radius, Class <? extends Entity> classEntity)
    {
    	AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox((double)e.xCoord, (double)e.yCoord, (double)e.zCoord, (double)(e.xCoord + 1), (double)(e.yCoord + 1), (double)(e.zCoord + 1)).expand(radius, radius, radius);
    	List list = e.getWorldObj().getEntitiesWithinAABB(classEntity, aabb);
		return list;
    }
    
	public static Direction get4Directions(Entity e)
	{
		switch(MathHelper.floor_double(e.rotationYaw * 4.0F / 360.0F + 0.5D) & 3)
		{case 0: return Direction.SOUTH; case 1: return Direction.WEST; case 2: return Direction.NORTH; case 3: return Direction.EAST;}
		return null;
	}
	
	public static Direction get8Directions(Entity e)
	{
		switch(MathHelper.floor_double(e.rotationYaw * 8.0F / 360.0F + 0.5D) & 7)
		{case 0: return Direction.SOUTH; case 1: return Direction.SOUTH_WEST; case 2: return Direction.WEST; case 3: return Direction.NORTH_WEST; case 4: return Direction.NORTH; case 5: return Direction.NORTH_EAST; case 6: return Direction.EAST; case 7: return Direction.SOUTH_EAST;}
		return null;
	}
	
	public static MovingObjectPosition rayTraceBlocks(Entity e)
	{
		float f = 1.0F;
		float f1 = e.prevRotationPitch + (e.rotationPitch - e.prevRotationPitch) * f;
		float f2 = e.prevRotationYaw + (e.rotationYaw - e.prevRotationYaw) * f;
		double d = e.prevPosX + (e.posX - e.prevPosX) * (double)f;
		double d1 = (e.prevPosY + (e.posY - e.prevPosY) * (double)f + 1.6200000000000001D) - (double)e.getYOffset();
		double d2 = e.prevPosZ + (e.posZ - e.prevPosZ) * (double)f;
		Vec3 vec3d = Vec3.createVectorHelper(d, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.01745329F - 3.141593F);
		float f4 = MathHelper.sin(-f2 * 0.01745329F - 3.141593F);	      
		float f5 = -MathHelper.cos(-f1 * 0.01745329F);
		float f6 = MathHelper.sin(-f1 * 0.01745329F);
		float f7 = f4 * f5;
		float f9 = f3 * f5;
		double d3 = 5000D;
		
		Vec3 vec3 = vec3d.addVector((double)f7 * d3, (double)f6 * d3, (double)f9 * d3);
		MovingObjectPosition ray = e.worldObj.rayTraceBlocks(vec3d, vec3, false);
		
		return ray;
	}
	
	public static void createCube(Entity e, int i, Block b)
	{createCube(e, new int[] {i, i, i}, b);}
	
	public static void createCube(Entity e, int[] s, Block b)
	{
		for (int x = (s[0] * 0) - s[0]; x < s[0]; x++) 
		for (int y = (s[1] * 0) - s[1]; y < s[1]; y++) 
		for (int z = (s[2] * 0) - s[2]; z < s[2]; z++) 
		{
			e.worldObj.setBlock((int)e.posX + x, (int)e.posY + y, (int)e.posZ + z, b);
		}
	}
	
	public static void createSphere(Object e, int size, final Block b)
	{
		int x = 0, y = 0, z = 0;
		final World world;
		if(e instanceof Entity)
		{
			x = (int) ((Entity)e).posX;
			y = (int) ((Entity)e).posY;
			z = (int) ((Entity)e).posZ;
			world = ((Entity)e).worldObj;
		}
		else if(e instanceof TileEntity)
		{
			x = (int) ((TileEntity)e).xCoord;
			y = (int) ((TileEntity)e).yCoord;
			z = (int) ((TileEntity)e).zCoord;
			world = ((TileEntity)e).getWorldObj();			
		}
		else
		{
			world = null;
		}
		
		Sphere.generate(x, y, z, size, new ISphere()
		{
		    public void call(int x, int y, int z)
		    {world.setBlock(x, y ,z, b);}
		});
	}
	
	public static void createSphere(Object e, int x, int y, int z, int size, final Block b)
	{
		final World world;
		if(e instanceof Entity)
			world = ((Entity)e).worldObj;
		else if(e instanceof TileEntity)
			world = ((TileEntity)e).getWorldObj();			
		else
			world = null;
		
		Sphere.generate(x, y, z, size, new ISphere()
		{
		    public void call(int x, int y, int z)
		    {world.setBlock(x, y ,z, b);}
		});
	}
	
    public static void removeStackFromInventory(EntityPlayer player, ItemStack stack)
    {
    	for(int i = 0; i < player.inventory.mainInventory.length; i++)
    	{
    		if(stack == player.inventory.getStackInSlot(i))
    		{
    			player.inventory.setInventorySlotContents(i, null);
    			break;
    		}
    	}
    }
    
    public static void removeStackFromSlot(EntityPlayer player, int index)
    {
        if (player.inventory.mainInventory[index] != null)
            player.inventory.mainInventory[index] = null;
    }
}
