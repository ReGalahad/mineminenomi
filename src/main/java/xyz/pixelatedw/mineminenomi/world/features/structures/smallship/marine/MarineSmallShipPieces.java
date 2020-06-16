package xyz.pixelatedw.mineminenomi.world.features.structures.smallship.marine;

import java.util.List;
import java.util.Map;
import java.util.Random;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.CustomSpawnerTileEntity;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.init.ModFeatures;
import xyz.pixelatedw.mineminenomi.init.ModLootTables;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;

public class MarineSmallShipPieces
{
	private static final ResourceLocation FRONT = new ResourceLocation(APIConfig.PROJECT_ID, "small_ship/front");
	private static final ResourceLocation MIDDLE = new ResourceLocation(APIConfig.PROJECT_ID, "small_ship/middle");
	private static final ResourceLocation BACK = new ResourceLocation(APIConfig.PROJECT_ID, "small_ship/back");
	private static final ResourceLocation MAST = new ResourceLocation(APIConfig.PROJECT_ID, "small_ship/marine/mast");
	private static final Map<ResourceLocation, BlockPos> CENTER_OFFSET = ImmutableMap.of(FRONT, new BlockPos(4, 4, 21), MIDDLE, new BlockPos(4, 4, 4), BACK, new BlockPos(4, 4, -10), MAST, new BlockPos(9, 0, 5));
	private static final Map<ResourceLocation, BlockPos> POSITION_OFFSET = ImmutableMap.of(FRONT, new BlockPos(0, 0, -17), MIDDLE, BlockPos.ZERO, BACK, new BlockPos(0, 0, 14), MAST, new BlockPos(-5, 8, -1));
	
	private static final List<EntityType> GRUNT_TYPES = Lists.newArrayList(ModEntities.MARINE_WITH_SWORD, ModEntities.MARINE_WITH_GUN);
	
	private static EntityType chooseGruntType()
	{
		return GRUNT_TYPES.get((int) WyHelper.randomWithRange(0, GRUNT_TYPES.size() - 1));
	}
	
	public static void addComponents(TemplateManager templateManager, BlockPos pos, Rotation rot, List<StructurePiece> components)
	{
		components.add(new Piece(templateManager, FRONT, pos, rot));
		components.add(new Piece(templateManager, MIDDLE, pos, rot));
		components.add(new Piece(templateManager, BACK, pos, rot));
		components.add(new Piece(templateManager, MAST, pos, rot));
	}

	public static class Piece extends TemplateStructurePiece
	{
		private ResourceLocation resourceLocation;
		private Rotation rotation;

		public Piece(TemplateManager template, CompoundNBT nbt)
		{
			super(ModFeatures.Pieces.MARINE_SMALL_SHIP_PIECE, nbt);
			this.resourceLocation = new ResourceLocation(nbt.getString("Template"));
			this.rotation = Rotation.valueOf(nbt.getString("Rot"));
			this.build(template);
		}

		public Piece(TemplateManager template, ResourceLocation res, BlockPos pos, Rotation rot)
		{
			super(ModFeatures.Pieces.MARINE_SMALL_SHIP_PIECE, 0);
			this.rotation = rot;
			this.resourceLocation = res;
			BlockPos blockpos = MarineSmallShipPieces.POSITION_OFFSET.get(this.resourceLocation);
			this.templatePosition = pos.add(blockpos.getX(), blockpos.getY(), blockpos.getZ());
			this.build(template);
		}

		@Override
		protected void readAdditional(CompoundNBT nbt)
		{
			super.readAdditional(nbt);
			nbt.putString("Template", this.resourceLocation.toString());
			nbt.putString("Rot", this.rotation.name());
		}

		private void build(TemplateManager templateManager)
		{
			Template template = templateManager.getTemplateDefaulted(this.resourceLocation);
			PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset(MarineSmallShipPieces.CENTER_OFFSET.get(this.resourceLocation)).addProcessor(BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK);
			this.setup(template, this.templatePosition, placementsettings);			
		}

		@Override
		protected void handleDataMarker(String function, BlockPos pos, IWorld world, Random rand, MutableBoundingBox sbb)
		{
			// Chests
			if (function.equals("weapons_chest"))
			{
				world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
				TileEntity tileentity = world.getTileEntity(pos.down());
				if (tileentity instanceof ChestTileEntity)
				{
					((ChestTileEntity) tileentity).setLootTable(ModLootTables.SMALL_SHIP_COMBAT_CHEST, rand.nextLong());
				}
			}
			if (function.equals("food_chest"))
			{
				world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
				TileEntity tileentity = world.getTileEntity(pos.down());
				if (tileentity instanceof ChestTileEntity)
				{
					((ChestTileEntity) tileentity).setLootTable(ModLootTables.SMALL_SHIP_FOOD_CHEST, rand.nextLong());
				}
			}

			// Spawners
			if (function.equals("grunt_x2_spawn"))
			{
				world.setBlockState(pos, ModBlocks.CUSTOM_SPAWNER.getDefaultState(), 3);
				TileEntity spawner = world.getTileEntity(pos);
				if (spawner instanceof CustomSpawnerTileEntity)
				{
					((CustomSpawnerTileEntity) spawner).setSpawnerLimit(2);
					((CustomSpawnerTileEntity) spawner).setSpawnerMob(MarineSmallShipPieces.chooseGruntType());
				}
			}
			if (function.equals("grunt_x3_spawn"))
			{
				world.setBlockState(pos, ModBlocks.CUSTOM_SPAWNER.getDefaultState(), 3);
				TileEntity spawner = world.getTileEntity(pos);
				if (spawner instanceof CustomSpawnerTileEntity)
				{
					((CustomSpawnerTileEntity) spawner).setSpawnerLimit(3);
					((CustomSpawnerTileEntity) spawner).setSpawnerMob(MarineSmallShipPieces.chooseGruntType());
				}
			}
			if (function.equals("grunt_x5_spawn"))
			{
				world.setBlockState(pos, ModBlocks.CUSTOM_SPAWNER.getDefaultState(), 3);
				TileEntity spawner = world.getTileEntity(pos);
				if (spawner instanceof CustomSpawnerTileEntity)
				{
					((CustomSpawnerTileEntity) spawner).setSpawnerLimit(5);
					((CustomSpawnerTileEntity) spawner).setSpawnerMob(MarineSmallShipPieces.chooseGruntType());
				}
			}
		}

		@Override
		public boolean addComponentParts(IWorld world, Random random, MutableBoundingBox bb, ChunkPos chunkPos)
		{
			PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset(MarineSmallShipPieces.CENTER_OFFSET.get(this.resourceLocation)).addProcessor(BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK);
			BlockPos offset = MarineSmallShipPieces.POSITION_OFFSET.get(this.resourceLocation);
			this.templatePosition.add(Template.transformedBlockPos(placementsettings, new BlockPos(offset.getX(), offset.getY(), offset.getZ())));
			return super.addComponentParts(world, random, bb, chunkPos);
		}
	}
}
