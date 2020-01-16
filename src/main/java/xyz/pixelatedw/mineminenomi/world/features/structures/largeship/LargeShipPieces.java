package xyz.pixelatedw.mineminenomi.world.features.structures.largeship;

import java.util.Random;

import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;
import xyz.pixelatedw.mineminenomi.api.debug.WyDebug;
import xyz.pixelatedw.mineminenomi.api.math.WyMathHelper;
import xyz.pixelatedw.mineminenomi.api.schematic.WySchematicHelper;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.CustomSpawnerTileEntity;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.init.ModFeatures;
import xyz.pixelatedw.mineminenomi.init.ModResources;

public class LargeShipPieces
{
	public static class Piece extends StructurePiece
	{
		private ResourceLocation resourceLocation;
		private Rotation rotation;
		
		public Piece(BlockPos pos)
		{
			super(ModFeatures.Pieces.LARGE_SHIP_BODY, 0);
			this.boundingBox = new MutableBoundingBox(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 64, pos.getY() + 32, pos.getZ() + 64);
			
			WyDebug.debug("Large ship spawned at: /tp " + pos.getX() + " ~ " + pos.getZ());
		}

		public Piece(TemplateManager template, CompoundNBT nbt)
		{
			super(ModFeatures.Pieces.LARGE_SHIP_BODY, nbt);
		}

		@Override
		protected void readAdditional(CompoundNBT tagCompound) {}

		@Override
		public boolean addComponentParts(IWorld world, Random rand, MutableBoundingBox bb, ChunkPos chunkPos)
		{
			int i = world.getHeight(Heightmap.Type.WORLD_SURFACE_WG, this.boundingBox.minX, this.boundingBox.minZ);
			BlockPos pos = new BlockPos.MutableBlockPos(this.boundingBox.minX, i, this.boundingBox.minZ).down(4);
			
			int spawnLimit = (int) WyMathHelper.randomWithRange(4, 6);

			try
			{
				if(rand.nextDouble() < 0.4)
				{
					WySchematicHelper.build(WySchematicHelper.load("marineLargeShip"), world, pos, Blocks.BEDROCK);
									
					world.setBlockState(pos.add(11, 6, 35), ModBlocks.CUSTOM_SPAWNER.getDefaultState(), 2);
					((CustomSpawnerTileEntity) world.getTileEntity(pos.add(11, 6, 35))).setSpawnerMob(ModEntities.MARINE_WITH_SWORD).setSpawnerLimit(spawnLimit);
					
					world.setBlockState(pos.add(11, 6, 26), ModBlocks.CUSTOM_SPAWNER.getDefaultState(), 2);
					((CustomSpawnerTileEntity) world.getTileEntity(pos.add(11, 6, 26))).setSpawnerMob(ModEntities.MARINE_WITH_GUN).setSpawnerLimit(spawnLimit);
					
					world.setBlockState(pos.add(11, 12, 20), ModBlocks.CUSTOM_SPAWNER.getDefaultState(), 2);
					((CustomSpawnerTileEntity) world.getTileEntity(pos.add(11, 12, 20))).setSpawnerMob(ModEntities.MARINE_WITH_SWORD).setSpawnerLimit(spawnLimit);
	
					world.setBlockState(pos.add(8, 12, 49), ModBlocks.CUSTOM_SPAWNER.getDefaultState(), 2);
					((CustomSpawnerTileEntity) world.getTileEntity(pos.add(8, 12, 49))).setSpawnerMob(ModEntities.MARINE_WITH_GUN).setSpawnerLimit(2);
					
					world.setBlockState(pos.add(10, 12, 49), ModBlocks.CUSTOM_SPAWNER.getDefaultState(), 2);
					((CustomSpawnerTileEntity) world.getTileEntity(pos.add(10, 12, 49))).setSpawnerMob(ModEntities.MARINE_CAPTAIN).setSpawnerLimit(1);
					
					world.setBlockState(pos.add(12, 12, 49), ModBlocks.CUSTOM_SPAWNER.getDefaultState(), 2);
					((CustomSpawnerTileEntity) world.getTileEntity(pos.add(12, 12, 49))).setSpawnerMob(ModEntities.MARINE_WITH_SWORD).setSpawnerLimit(2);
				}
				else
				{
					WySchematicHelper.build(WySchematicHelper.load("pyrateLargeShip"), world, pos, Blocks.BEDROCK);
					
					world.setBlockState(pos.add(11, 6, 35), ModBlocks.CUSTOM_SPAWNER.getDefaultState(), 2);
					((CustomSpawnerTileEntity) world.getTileEntity(pos.add(11, 6, 35))).setSpawnerMob(ModEntities.PIRATE_WITH_SWORD).setSpawnerLimit(spawnLimit);
					
					world.setBlockState(pos.add(11, 6, 26), ModBlocks.CUSTOM_SPAWNER.getDefaultState(), 2);
					((CustomSpawnerTileEntity) world.getTileEntity(pos.add(11, 6, 26))).setSpawnerMob(ModEntities.PIRATE_WITH_GUN).setSpawnerLimit(spawnLimit);
					
					world.setBlockState(pos.add(11, 12, 20), ModBlocks.CUSTOM_SPAWNER.getDefaultState(), 2);
					((CustomSpawnerTileEntity) world.getTileEntity(pos.add(11, 12, 20))).setSpawnerMob(ModEntities.PIRATE_WITH_SWORD).setSpawnerLimit(spawnLimit);
					
					world.setBlockState(pos.add(8, 12, 49), ModBlocks.CUSTOM_SPAWNER.getDefaultState(), 2);
					((CustomSpawnerTileEntity) world.getTileEntity(pos.add(8, 12, 49))).setSpawnerMob(ModEntities.PIRATE_WITH_GUN).setSpawnerLimit(2);
					
					world.setBlockState(pos.add(10, 12, 49), ModBlocks.CUSTOM_SPAWNER.getDefaultState(), 2);
					((CustomSpawnerTileEntity) world.getTileEntity(pos.add(10, 12, 49))).setSpawnerMob(ModEntities.PIRATE_CAPTAIN).setSpawnerLimit(1);
					
					world.setBlockState(pos.add(12, 12, 49), ModBlocks.CUSTOM_SPAWNER.getDefaultState(), 2);
					((CustomSpawnerTileEntity) world.getTileEntity(pos.add(12, 12, 49))).setSpawnerMob(ModEntities.PIRATE_WITH_SWORD).setSpawnerLimit(2);
				}
				
				LockableLootTileEntity.setLootTable(world, rand, pos.add(7, 6, 46), ModResources.LARGE_SHIP_COMBAT);
	
				LockableLootTileEntity.setLootTable(world, rand, pos.add(14, 6, 46), ModResources.LARGE_SHIP_FOOD);
				
				LockableLootTileEntity.setLootTable(world, rand, pos.add(11, 6, 49), ModResources.LARGE_SHIP_TREASURE);
			}
			catch(Exception e)
			{
				//e.printStackTrace();
				//WyDebug.error("\nShip spawned outside it's bounds most likely; useful info : \n Pos:" + pos.toString() + "\n Seed: " + world.getSeed() + "\n Is Chunk Loaded: " + (world.getWorld().getChunkProvider()).isChunkLoaded(chunkPos) + "\n Provider: " + (world.getWorld().getChunkProvider()));
			}
			return true;
		}
	}
}
