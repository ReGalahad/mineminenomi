package xyz.pixelatedw.mineminenomi.world.features.structures.largeship.pirate;

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

public class PirateLargeShipPieces
{
	private static final ResourceLocation FRONT = new ResourceLocation(APIConfig.PROJECT_ID, "large_ship/front");
	private static final ResourceLocation MIDDLE = new ResourceLocation(APIConfig.PROJECT_ID, "large_ship/middle");
	private static final ResourceLocation BACK = new ResourceLocation(APIConfig.PROJECT_ID, "large_ship/back");
	private static final ResourceLocation SMALL_MAST = new ResourceLocation(APIConfig.PROJECT_ID, "large_ship/pirate/small_mast");
	private static final ResourceLocation LARGE_MAST0 = new ResourceLocation(APIConfig.PROJECT_ID, "large_ship/pirate/large_mast0");
	private static final ResourceLocation LARGE_MAST1 = new ResourceLocation(APIConfig.PROJECT_ID, "large_ship/pirate/large_mast1");
	private static final ResourceLocation LARGE_MAST2 = new ResourceLocation(APIConfig.PROJECT_ID, "large_ship/pirate/large_mast2");
	private static final ResourceLocation LARGE_MAST3 = new ResourceLocation(APIConfig.PROJECT_ID, "large_ship/pirate/large_mast3");
	private static final ResourceLocation LARGE_MAST4 = new ResourceLocation(APIConfig.PROJECT_ID, "large_ship/pirate/large_mast4");
	
	private static final ResourceLocation[] MASTS = new ResourceLocation[] { LARGE_MAST0, LARGE_MAST1, LARGE_MAST2, LARGE_MAST3, LARGE_MAST4 };

	private static final Map<ResourceLocation, BlockPos> POSITION_OFFSET = ImmutableMap.<ResourceLocation, BlockPos>builder()
		.put(FRONT, new BlockPos(0, 0, -22))
		.put(MIDDLE, new BlockPos(0, 0, 0))
		.put(BACK, new BlockPos(0, 0, 15))
		.put(LARGE_MAST0, new BlockPos(-2, 13, 0))
		.put(LARGE_MAST1, new BlockPos(-2, 13, 0))
		.put(LARGE_MAST2, new BlockPos(-2, 13, 0))
		.put(LARGE_MAST3, new BlockPos(-2, 13, 0))
		.put(LARGE_MAST4, new BlockPos(-2, 13, 0))
		.put(SMALL_MAST, new BlockPos(2, 18, 19))
		.build();
	
	private static final Map<ResourceLocation, BlockPos> CENTER_OFFSET = ImmutableMap.<ResourceLocation, BlockPos>builder()
		.put(FRONT, new BlockPos(7, 12, 26))
		.put(MIDDLE, new BlockPos(7, 12, 4))
		.put(BACK, new BlockPos(7, 12, -11))
		.put(LARGE_MAST0, new BlockPos(9, 0, 4))
		.put(LARGE_MAST1, new BlockPos(9, 0, 4))
		.put(LARGE_MAST2, new BlockPos(9, 0, 4))
		.put(LARGE_MAST3, new BlockPos(9, 0, 4))
		.put(LARGE_MAST4, new BlockPos(9, 0, 4))
		.put(SMALL_MAST, new BlockPos(5, 0, -15))
		.build();
	
	public static void addComponents(TemplateManager templateManager, BlockPos pos, Rotation rot, List<StructurePiece> components)
	{
		components.add(new Piece(templateManager, FRONT, pos, rot));
		components.add(new Piece(templateManager, MIDDLE, pos, rot));
		components.add(new Piece(templateManager, BACK, pos, rot));
		components.add(new Piece(templateManager, LARGE_MAST0, pos, rot));
		components.add(new Piece(templateManager, SMALL_MAST, pos, rot));
	}

	public static class Piece extends TemplateStructurePiece
	{
		private ResourceLocation resourceLocation;
		private Rotation rotation;

		public Piece(TemplateManager template, CompoundNBT nbt)
		{
			super(ModFeatures.Pieces.PIRATE_LARGE_SHIP_PIECE, nbt);
			this.resourceLocation = new ResourceLocation(nbt.getString("Template"));
			this.rotation = Rotation.valueOf(nbt.getString("Rot"));
			this.build(template);
		}

		public Piece(TemplateManager template, ResourceLocation res, BlockPos pos, Rotation rot)
		{
			super(ModFeatures.Pieces.PIRATE_LARGE_SHIP_PIECE, 0);
			this.rotation = rot;
			this.resourceLocation = res;
			BlockPos blockpos = PirateLargeShipPieces.POSITION_OFFSET.get(this.resourceLocation);
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
			if(this.resourceLocation.equals(LARGE_MAST0))
				this.resourceLocation = MASTS[(int) WyHelper.randomWithRange(0, MASTS.length - 1)];
			Template template = templateManager.getTemplateDefaulted(this.resourceLocation);
			PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset(PirateLargeShipPieces.CENTER_OFFSET.get(this.resourceLocation)).addProcessor(BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK);
			this.setup(template, this.templatePosition, placementsettings);			
		}

		@Override
		protected void handleDataMarker(String function, BlockPos pos, IWorld world, Random rand, MutableBoundingBox sbb)
		{
			// Chests
			if (function.equals("combat_chest"))
			{
				world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
				TileEntity tileentity = world.getTileEntity(pos.down());
				if (tileentity instanceof ChestTileEntity)
				{
					((ChestTileEntity) tileentity).setLootTable(ModLootTables.LARGE_SHIP_COMBAT_CHEST, rand.nextLong());
				}
			}
			else if (function.equals("food_chest"))
			{
				world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
				TileEntity tileentity = world.getTileEntity(pos.down());
				if (tileentity instanceof ChestTileEntity)
				{
					((ChestTileEntity) tileentity).setLootTable(ModLootTables.LARGE_SHIP_FOOD_CHEST, rand.nextLong());
				}
			}
			else if (function.equals("treasure_chest"))
			{
				world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
				TileEntity tileentity = world.getTileEntity(pos.down());
				if (tileentity instanceof ChestTileEntity)
				{
					((ChestTileEntity) tileentity).setLootTable(ModLootTables.LARGE_SHIP_TREASURE_CHEST, rand.nextLong());
				}
			}

			// Spawners
			// 3 x5 Grunt Spawners
			// 1 x3 Grunt Spawner
			// 1 Captain Spawner
			StructuresHelper.setupSpawners(function, world, pos, StructureFaction.PIRATE);
		}

		@Override
		public boolean addComponentParts(IWorld world, Random random, MutableBoundingBox bb, ChunkPos chunkPos)
		{
			PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset(PirateLargeShipPieces.CENTER_OFFSET.get(this.resourceLocation)).addProcessor(BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK);
			BlockPos offset = PirateLargeShipPieces.POSITION_OFFSET.get(this.resourceLocation);
			this.templatePosition.add(Template.transformedBlockPos(placementsettings, new BlockPos(offset.getX(), offset.getY(), offset.getZ())));
			
			boolean flag = super.addComponentParts(world, random, bb, chunkPos);
			
			return flag;
		}
	}
}
