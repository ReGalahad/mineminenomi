package xyz.pixelatedw.mineminenomi.world.features.structures.dojo;

import java.util.Random;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.CustomSpawnerTileEntity;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.init.ModFeatures;
import xyz.pixelatedw.wypi.APIConfig;

public class DojoPiece extends TemplateStructurePiece
{
	private ResourceLocation resourceLocation;
	private Rotation rotation;

	public DojoPiece(TemplateManager template, CompoundNBT nbt)
	{
		super(ModFeatures.Pieces.DOJO_PIECE, nbt);
        this.resourceLocation = new ResourceLocation(nbt.getString("Template"));
        this.rotation = Rotation.valueOf(nbt.getString("Rot"));
        this.build(template);
	}
	
	public DojoPiece(TemplateManager template, BlockPos pos, Rotation rot)
	{
		super(ModFeatures.Pieces.DOJO_PIECE, 0);
		this.templatePosition = pos;
		this.rotation = rot;
		this.resourceLocation = new ResourceLocation(APIConfig.PROJECT_ID, "dojo");
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
		PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
		this.setup(template, this.templatePosition, placementsettings);		
	}

	@Override
	protected void handleDataMarker(String function, BlockPos pos, IWorld world, Random rand, MutableBoundingBox sbb)
	{
		if (function.equals("sensei_spawn"))
		{
			world.setBlockState(pos, ModBlocks.CUSTOM_SPAWNER.getDefaultState(), 3);
			TileEntity spawner = world.getTileEntity(pos);
			if (spawner instanceof CustomSpawnerTileEntity)
			{
				((CustomSpawnerTileEntity) spawner).setSpawnerLimit(1);
				((CustomSpawnerTileEntity) spawner).setSpawnerMob(ModEntities.DOJO_SENSEI);
			}
		}
	}
	
	@Override
	public boolean addComponentParts(IWorld worldIn, Random randomIn, MutableBoundingBox structureBoundingBoxIn, ChunkPos chunkPosIn)
	{
		PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
		BlockPos blockpos = BlockPos.ZERO;
		BlockPos blockpos1 = this.templatePosition.add(Template.transformedBlockPos(placementsettings, new BlockPos(3 - blockpos.getX(), 0, 0 - blockpos.getZ())));
		int i = worldIn.getHeight(Heightmap.Type.WORLD_SURFACE_WG, blockpos1.getX(), blockpos1.getZ());
		BlockPos blockpos2 = this.templatePosition;
		this.templatePosition = this.templatePosition.add(0, i - 90, 0);
		boolean flag = super.addComponentParts(worldIn, randomIn, structureBoundingBoxIn, chunkPosIn);

		this.templatePosition = blockpos2;
		return flag;
	}
}
