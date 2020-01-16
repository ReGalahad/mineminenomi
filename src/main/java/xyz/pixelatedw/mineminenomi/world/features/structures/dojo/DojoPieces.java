package xyz.pixelatedw.mineminenomi.world.features.structures.dojo;

import java.util.Random;

import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
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

public class DojoPieces
{
	public static class Piece extends StructurePiece
	{
		private ResourceLocation resourceLocation;
		private Rotation rotation;
		
		public Piece(BlockPos pos)
		{
			super(ModFeatures.Pieces.DOJO_BODY, 0);
			this.boundingBox = new MutableBoundingBox(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 64, pos.getY() + 32, pos.getZ() + 64);
			
			WyDebug.debug("Dojo spawned at: /tp " + pos.getX() + " ~ " + pos.getZ());
		}

		public Piece(TemplateManager template, CompoundNBT nbt)
		{
			super(ModFeatures.Pieces.DOJO_BODY, nbt);
		}

		@Override
		protected void readAdditional(CompoundNBT tagCompound) {}

		@Override
		public boolean addComponentParts(IWorld world, Random rand, MutableBoundingBox bb, ChunkPos chunkPos)
		{
			int i = world.getHeight(Heightmap.Type.WORLD_SURFACE_WG, this.boundingBox.minX, this.boundingBox.minZ);
			BlockPos pos = new BlockPos.MutableBlockPos(this.boundingBox.minX, i, this.boundingBox.minZ);
			
			WySchematicHelper.build(WySchematicHelper.load("dojo"), world, pos, Blocks.BEDROCK);
			
			try
			{
				world.setBlockState(pos.add(4, 2, 10), ModBlocks.CUSTOM_SPAWNER.getDefaultState(), 2);
				((CustomSpawnerTileEntity) world.getTileEntity(pos.add(4, 2, 10))).setSpawnerMob(ModEntities.DOJO_SENSEI).setSpawnerLimit(1);
			}
			catch(Exception e) {}

			return true;
		}
	}
}
