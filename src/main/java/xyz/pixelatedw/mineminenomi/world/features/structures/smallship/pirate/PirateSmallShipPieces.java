package xyz.pixelatedw.mineminenomi.world.features.structures.smallship.pirate;

import java.util.List;
import java.util.Map;
import java.util.Random;

import com.google.common.collect.ImmutableMap;

import net.minecraft.block.Blocks;
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
import xyz.pixelatedw.mineminenomi.api.helpers.StructuresHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.StructuresHelper.StructureFaction;
import xyz.pixelatedw.mineminenomi.init.ModFeatures;
import xyz.pixelatedw.mineminenomi.init.ModLootTables;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;

public class PirateSmallShipPieces
{
	private static final ResourceLocation FRONT = new ResourceLocation(APIConfig.PROJECT_ID, "small_ship/front");
	private static final ResourceLocation MIDDLE = new ResourceLocation(APIConfig.PROJECT_ID, "small_ship/middle");
	private static final ResourceLocation BACK = new ResourceLocation(APIConfig.PROJECT_ID, "small_ship/back");
	private static final ResourceLocation MAST0 = new ResourceLocation(APIConfig.PROJECT_ID, "small_ship/pirate/mast0");
	private static final ResourceLocation MAST1 = new ResourceLocation(APIConfig.PROJECT_ID, "small_ship/pirate/mast1");
	private static final ResourceLocation MAST2 = new ResourceLocation(APIConfig.PROJECT_ID, "small_ship/pirate/mast2");
	private static final ResourceLocation MAST3 = new ResourceLocation(APIConfig.PROJECT_ID, "small_ship/pirate/mast3");
	private static final ResourceLocation MAST4 = new ResourceLocation(APIConfig.PROJECT_ID, "small_ship/pirate/mast4");

	private static final ResourceLocation[] MASTS = new ResourceLocation[] { MAST0, MAST1, MAST2, MAST3, MAST4 };

	private static final Map<ResourceLocation, BlockPos> POSITION_OFFSET = ImmutableMap.<ResourceLocation, BlockPos>builder()
		.put(FRONT, new BlockPos(0, 0, -17))
		.put(MIDDLE, BlockPos.ZERO)
		.put(BACK, new BlockPos(0, 0, 14))
		.put(MAST0, new BlockPos(-5, 8, -1))
		.put(MAST1, new BlockPos(-5, 8, -1))
		.put(MAST2, new BlockPos(-5, 8, -1))
		.put(MAST3, new BlockPos(-5, 8, -1))
		.put(MAST4, new BlockPos(-5, 8, -1))
		.build();
	
	private static final Map<ResourceLocation, BlockPos> CENTER_OFFSET = ImmutableMap.<ResourceLocation, BlockPos>builder()
		.put(FRONT, new BlockPos(4, 4, 21))
		.put(MIDDLE, new BlockPos(4, 4, 4))
		.put(BACK, new BlockPos(4, 4, -10))
		.put(MAST0, new BlockPos(9, 0, 5))
		.put(MAST1, new BlockPos(9, 0, 5))
		.put(MAST2, new BlockPos(9, 0, 5))
		.put(MAST3, new BlockPos(9, 0, 5))
		.put(MAST4, new BlockPos(9, 0, 5))
		.build();
	
	public static void addComponents(TemplateManager templateManager, BlockPos pos, Rotation rot, List<StructurePiece> components)
	{
		components.add(new Piece(templateManager, FRONT, pos, rot));
		components.add(new Piece(templateManager, MIDDLE, pos, rot));
		components.add(new Piece(templateManager, BACK, pos, rot));
		components.add(new Piece(templateManager, MAST0, pos, rot));
	}

	public static class Piece extends TemplateStructurePiece
	{
		private ResourceLocation resourceLocation;
		private Rotation rotation;

		public Piece(TemplateManager template, CompoundNBT nbt)
		{
			super(ModFeatures.Pieces.PIRATE_SMALL_SHIP_PIECE, nbt);
			this.resourceLocation = new ResourceLocation(nbt.getString("Template"));
			this.rotation = Rotation.valueOf(nbt.getString("Rot"));
			this.build(template);
		}

		public Piece(TemplateManager template, ResourceLocation res, BlockPos pos, Rotation rot)
		{
			super(ModFeatures.Pieces.PIRATE_SMALL_SHIP_PIECE, 0);
			this.rotation = rot;
			this.resourceLocation = res;
			BlockPos blockpos = PirateSmallShipPieces.POSITION_OFFSET.get(this.resourceLocation);
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
			if(this.resourceLocation.equals(MAST0))
				this.resourceLocation = MASTS[(int) WyHelper.randomWithRange(0, MASTS.length - 1)];
			Template template = templateManager.getTemplateDefaulted(this.resourceLocation);
			PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset(PirateSmallShipPieces.CENTER_OFFSET.get(this.resourceLocation)).addProcessor(BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK);
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
			// 2 x5 Grunt Spawners
			// 1 x3 Grunt Spawner
			// 1 x2 Grunt Spawner
			StructuresHelper.setupSpawners(function, world, pos, StructureFaction.PIRATE);
		}

		@Override
		public boolean addComponentParts(IWorld world, Random random, MutableBoundingBox bb, ChunkPos chunkPos)
		{
			PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset(PirateSmallShipPieces.CENTER_OFFSET.get(this.resourceLocation)).addProcessor(BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK);
			BlockPos offset = PirateSmallShipPieces.POSITION_OFFSET.get(this.resourceLocation);
			this.templatePosition.add(Template.transformedBlockPos(placementsettings, new BlockPos(offset.getX(), offset.getY(), offset.getZ())));
			return super.addComponentParts(world, random, bb, chunkPos);
		}
	}
}
