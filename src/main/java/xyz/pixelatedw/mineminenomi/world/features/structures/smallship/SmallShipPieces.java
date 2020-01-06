package xyz.pixelatedw.mineminenomi.world.features.structures.smallship;

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
import xyz.pixelatedw.mineminenomi.api.schematic.WySchematicHelper;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.CustomSpawnerTileEntity;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.init.ModFeatures;
import xyz.pixelatedw.mineminenomi.init.ModResources;

public class SmallShipPieces
{
	public static class Piece extends StructurePiece
	{
		private ResourceLocation resourceLocation;
		private Rotation rotation;

		public Piece(BlockPos pos)
		{
			super(ModFeatures.Pieces.SMALL_SHIP_BODY, 0);
			this.boundingBox = new MutableBoundingBox(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 32, pos.getY() + 32, pos.getZ() + 32);
			
			WyDebug.debug("Small ship spawned at: /tp " + pos.getX() + " ~ " + pos.getZ());
		}

		public Piece(TemplateManager template, CompoundNBT nbt)
		{
			super(ModFeatures.Pieces.SMALL_SHIP_BODY, nbt);
		}

		@Override
		protected void readAdditional(CompoundNBT tagCompound)
		{
		}

		@Override
		public boolean addComponentParts(IWorld world, Random rand, MutableBoundingBox bb, ChunkPos chunkPos)
		{
			int i = world.getHeight(Heightmap.Type.WORLD_SURFACE_WG, this.boundingBox.minX, this.boundingBox.minZ);
			BlockPos pos = new BlockPos.MutableBlockPos(this.boundingBox.minX, i, this.boundingBox.minZ).down(2);
			
			if (rand.nextDouble() < 0.4)
			{
				WySchematicHelper.build(WySchematicHelper.load("marineSmallShip"), world, pos, Blocks.BEDROCK);
				
				world.setBlockState(pos.add(10, 2, 32), ModBlocks.customSpawner.getDefaultState(), 2);
				((CustomSpawnerTileEntity) world.getTileEntity(pos.add(10, 2, 32))).setSpawnerMob(ModEntities.MARINE_WITH_SWORD).setSpawnerLimit(5);
				
				world.setBlockState(pos.add(11, 2, 22), ModBlocks.customSpawner.getDefaultState(), 2);
				((CustomSpawnerTileEntity) world.getTileEntity(pos.add(11, 2, 22))).setSpawnerMob(ModEntities.MARINE_WITH_GUN).setSpawnerLimit(5);
				
				world.setBlockState(pos.add(11, 7, 36), ModBlocks.customSpawner.getDefaultState(), 2);
				((CustomSpawnerTileEntity) world.getTileEntity(pos.add(11, 7, 36))).setSpawnerMob(ModEntities.MARINE_WITH_GUN).setSpawnerLimit(2);
			}
			else
			{
				WySchematicHelper.build(WySchematicHelper.load("pyrateSmallShip"), world, pos, Blocks.BEDROCK);

				world.setBlockState(pos.add(10, 2, 32), ModBlocks.customSpawner.getDefaultState(), 2);
				((CustomSpawnerTileEntity) world.getTileEntity(pos.add(10, 2, 32))).setSpawnerMob(ModEntities.PIRATE_WITH_SWORD).setSpawnerLimit(5);
				
				world.setBlockState(pos.add(11, 2, 22), ModBlocks.customSpawner.getDefaultState(), 2);
				((CustomSpawnerTileEntity) world.getTileEntity(pos.add(11, 2, 22))).setSpawnerMob(ModEntities.PIRATE_WITH_GUN).setSpawnerLimit(5);
				
				world.setBlockState(pos.add(11, 7, 36), ModBlocks.customSpawner.getDefaultState(), 2);
				((CustomSpawnerTileEntity) world.getTileEntity(pos.add(11, 7, 36))).setSpawnerMob(ModEntities.PIRATE_WITH_GUN).setSpawnerLimit(2);
				
			}
			
			LockableLootTileEntity.setLootTable(world, rand, pos.add(12, 2, 16), ModResources.SMALL_SHIP_COMBAT);
			LockableLootTileEntity.setLootTable(world, rand, pos.add(12, 2, 15), ModResources.SMALL_SHIP_COMBAT);

			LockableLootTileEntity.setLootTable(world, rand, pos.add(9, 2, 16), ModResources.SMALL_SHIP_FOOD);
			LockableLootTileEntity.setLootTable(world, rand, pos.add(9, 2, 15), ModResources.SMALL_SHIP_FOOD);

			LockableLootTileEntity.setLootTable(world, rand, pos.add(11, 2, 12), ModResources.SMALL_SHIP_COMBAT);
			LockableLootTileEntity.setLootTable(world, rand, pos.add(10, 2, 12), ModResources.SMALL_SHIP_COMBAT);

			return true;
		}
	}
}
