package xyz.pixelatedw.mineminenomi.world.features.structures.smallbase.bandit;

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
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.CustomSpawnerTileEntity;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.init.ModFeatures;
import xyz.pixelatedw.mineminenomi.init.ModLootTables;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.debug.WyDebug;

public class BanditSmallBasePieces
{
	private static final ResourceLocation HOUSE = new ResourceLocation(APIConfig.PROJECT_ID, "small_base/bandit/house");
	private static final ResourceLocation LOGS = new ResourceLocation(APIConfig.PROJECT_ID, "small_base/bandit/logs");
	private static final ResourceLocation TUNNEL = new ResourceLocation(APIConfig.PROJECT_ID, "small_base/bandit/tunnel");
	private static final ResourceLocation HALLWAY = new ResourceLocation(APIConfig.PROJECT_ID, "small_base/bandit/hallway");
	private static final ResourceLocation LOUNGE = new ResourceLocation(APIConfig.PROJECT_ID, "small_base/bandit/lounge");
	private static final ResourceLocation LAB = new ResourceLocation(APIConfig.PROJECT_ID, "small_base/bandit/lab");
	private static final ResourceLocation MINE = new ResourceLocation(APIConfig.PROJECT_ID, "small_base/bandit/mine");
	private static final ResourceLocation BEDS_ROOM = new ResourceLocation(APIConfig.PROJECT_ID, "small_base/bandit/beds_room");
	private static final ResourceLocation TREASURE_ROOM = new ResourceLocation(APIConfig.PROJECT_ID, "small_base/bandit/treasure_room");

	private static final Map<ResourceLocation, BlockPos> POSITION_OFFSET = ImmutableMap.<ResourceLocation, BlockPos>builder()
		.put(HOUSE, new BlockPos(0, 0, 0))
		.put(LOGS, new BlockPos(0, 0, 0))
		.put(TUNNEL, new BlockPos(2, -19, 4))
		.put(HALLWAY, new BlockPos(-7, -22, 0))
		.put(LOUNGE, new BlockPos(-7, -24, -16))
		.put(LAB, new BlockPos(-18, -27, -23))
		.put(MINE, new BlockPos(-20, -28, -15))
		.put(BEDS_ROOM, new BlockPos(-6, -26, -25))
		.put(TREASURE_ROOM, new BlockPos(-19, -27, -5))
		.build();
	
	private static final Map<ResourceLocation, BlockPos> CENTER_OFFSET = ImmutableMap.<ResourceLocation, BlockPos>builder()
		.put(HOUSE, new BlockPos(5, 0, 6))
		.put(LOGS, new BlockPos(0, 0, 0))
		.put(TUNNEL, new BlockPos(3, 19, 2))
		.put(HALLWAY, new BlockPos(12, 17, 6))
		.put(LOUNGE, new BlockPos(0, 0, 0))
		.put(LAB, new BlockPos(0, 0, 0))
		.put(MINE, new BlockPos(0, 0, 0))
		.put(BEDS_ROOM, new BlockPos(0, 0, 0))
		.put(TREASURE_ROOM, new BlockPos(0, 0, 0))
		.build();

	private static final List<EntityType> GRUNT_TYPES = Lists.newArrayList(ModEntities.BANDIT_WITH_SWORD);

	private static EntityType chooseGruntType()
	{
		return GRUNT_TYPES.get((int) WyHelper.randomWithRange(0, GRUNT_TYPES.size() - 1));
	}

	public static void addComponents(TemplateManager templateManager, BlockPos pos, List<StructurePiece> components)
	{
		components.add(new Piece(templateManager, HOUSE, pos, BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK));
		components.add(new Piece(templateManager, TUNNEL, pos, BlockIgnoreStructureProcessor.STRUCTURE_BLOCK));
		components.add(new Piece(templateManager, HALLWAY, pos, BlockIgnoreStructureProcessor.STRUCTURE_BLOCK));
		components.add(new Piece(templateManager, LOUNGE, pos, BlockIgnoreStructureProcessor.STRUCTURE_BLOCK));
		components.add(new Piece(templateManager, TREASURE_ROOM, pos, BlockIgnoreStructureProcessor.STRUCTURE_BLOCK));
		components.add(new Piece(templateManager, MINE, pos, BlockIgnoreStructureProcessor.STRUCTURE_BLOCK));
		components.add(new Piece(templateManager, LAB, pos, BlockIgnoreStructureProcessor.STRUCTURE_BLOCK));
		components.add(new Piece(templateManager, BEDS_ROOM, pos, BlockIgnoreStructureProcessor.STRUCTURE_BLOCK));
	}

	public static class Piece extends TemplateStructurePiece
	{
		private ResourceLocation resourceLocation;
		private Rotation rotation;
		private StructureProcessor processor;
		private BlockPos centerPosition;

		public Piece(TemplateManager template, CompoundNBT nbt)
		{
			super(ModFeatures.Pieces.BANDIT_SMALL_BASE_PIECE, nbt);
			this.resourceLocation = new ResourceLocation(nbt.getString("Template"));
			this.rotation = Rotation.valueOf(nbt.getString("Rot"));
			int centerX = nbt.getShort("CenterX");
			int centerY = nbt.getShort("CenterY");
			int centerZ = nbt.getShort("CenterZ");
			this.centerPosition = new BlockPos(centerX, centerY, centerZ);
			this.processor = BlockIgnoreStructureProcessor.STRUCTURE_BLOCK;
			this.build(template);
		}

		public Piece(TemplateManager template, ResourceLocation res, BlockPos pos, StructureProcessor proc)
		{
			super(ModFeatures.Pieces.BANDIT_SMALL_BASE_PIECE, 0);
			this.rotation = Rotation.NONE;
			this.resourceLocation = res;
			BlockPos blockpos = POSITION_OFFSET.get(this.resourceLocation);
			this.centerPosition = pos;
			this.templatePosition = pos.add(blockpos.getX(), blockpos.getY(), blockpos.getZ());
			this.processor = proc;
			this.build(template);
		}

		@Override
		protected void readAdditional(CompoundNBT nbt)
		{
			super.readAdditional(nbt);
			nbt.putString("Template", this.resourceLocation.toString());
			nbt.putString("Rot", this.rotation.name());
			nbt.putInt("CenterX", this.centerPosition.getX());
			nbt.putInt("CenterY", this.centerPosition.getY());
			nbt.putInt("CenterZ", this.centerPosition.getZ());
		}

		private void build(TemplateManager templateManager)
		{
			Template template = templateManager.getTemplateDefaulted(this.resourceLocation);
			PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset(CENTER_OFFSET.get(this.resourceLocation)).addProcessor(this.processor);
			this.setup(template, this.templatePosition, placementsettings);
		}

		@Override
		protected void handleDataMarker(String function, BlockPos pos, IWorld world, Random rand, MutableBoundingBox sbb)
		{
			// Chests
			System.out.println(function);
			if (function.equals("gold_chest"))
			{
				world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
				TileEntity tileentity = world.getTileEntity(pos.down());
				if (tileentity instanceof ChestTileEntity)
				{
					((ChestTileEntity) tileentity).setLootTable(ModLootTables.BANDIT_SMALL_BASE_GOLD_CHEST, rand.nextLong());
				}
			}
			if (function.equals("lab_chest"))
			{
				world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
				TileEntity tileentity = world.getTileEntity(pos.down());
				if (tileentity instanceof ChestTileEntity)
				{
					((ChestTileEntity) tileentity).setLootTable(ModLootTables.BANDIT_SMALL_BASE_LAB_CHEST, rand.nextLong());
				}
			}
			if (function.equals("mine_chest"))
			{
				world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
				TileEntity tileentity = world.getTileEntity(pos.down());
				if (tileentity instanceof ChestTileEntity)
				{
					((ChestTileEntity) tileentity).setLootTable(ModLootTables.BANDIT_SMALL_BASE_MINE_CHEST, rand.nextLong());
				}
			}
			if (function.equals("ore_chest"))
			{
				world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
				TileEntity tileentity = world.getTileEntity(pos.down());
				if (tileentity instanceof ChestTileEntity)
				{
					((ChestTileEntity) tileentity).setLootTable(ModLootTables.BANDIT_SMALL_BASE_ORES_CHEST, rand.nextLong());
				}
			}
			
			// Spawners
			if (function.equals("captain_spawn"))
			{
				world.setBlockState(pos, ModBlocks.CUSTOM_SPAWNER.getDefaultState(), 3);
				TileEntity spawner = world.getTileEntity(pos);
				if (spawner instanceof CustomSpawnerTileEntity)
				{
					((CustomSpawnerTileEntity) spawner).setSpawnerLimit(1);
					((CustomSpawnerTileEntity) spawner).setSpawnerMob(BanditSmallBasePieces.chooseGruntType());
				}
			}
			if (function.equals("grunt_spawn"))
			{
				world.setBlockState(pos, ModBlocks.CUSTOM_SPAWNER.getDefaultState(), 3);
				TileEntity spawner = world.getTileEntity(pos);
				if (spawner instanceof CustomSpawnerTileEntity)
				{
					((CustomSpawnerTileEntity) spawner).setSpawnerLimit(5);
					((CustomSpawnerTileEntity) spawner).setSpawnerMob(BanditSmallBasePieces.chooseGruntType());
				}
			}
		}

		@Override
		public boolean addComponentParts(IWorld world, Random random, MutableBoundingBox bb, ChunkPos chunkPos)
		{
			if(this.centerPosition == null)
			{
				WyDebug.debug("Somehow the Center Position of this structure is null. Contact the owner!");
				return false;
			}
			
			PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset(CENTER_OFFSET.get(this.resourceLocation)).addProcessor(this.processor);
			BlockPos offset = POSITION_OFFSET.get(this.resourceLocation);
			this.templatePosition.add(Template.transformedBlockPos(placementsettings, new BlockPos(offset.getX(), offset.getY(), offset.getZ())));
			
			BlockPos blockpos2 = this.templatePosition;
			int i = world.getHeight(Heightmap.Type.WORLD_SURFACE_WG, this.centerPosition.getX(), this.centerPosition.getZ());
			this.templatePosition = this.templatePosition.add(0, i - 90 - 1, 0);

			boolean flag = super.addComponentParts(world, random, bb, chunkPos);

			this.templatePosition = blockpos2;
			return flag;
		}
	}
}
