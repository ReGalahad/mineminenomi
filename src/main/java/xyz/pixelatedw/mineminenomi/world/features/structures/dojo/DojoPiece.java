package xyz.pixelatedw.mineminenomi.world.features.structures.dojo;

import java.util.Random;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import xyz.pixelatedw.mineminenomi.init.ModFeatures;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.debug.WyDebug;

public class DojoPiece extends TemplateStructurePiece
{
	private static final BlockPos STRUCTURE_OFFSET = new BlockPos(4, 0, 15);
	private ResourceLocation resourceLocation;
	private Rotation rotation;

	public DojoPiece(TemplateManager template, CompoundNBT nbt)
	{
		super(ModFeatures.Pieces.DOJO_BODY, nbt);
		this.boundingBox = new MutableBoundingBox();
		this.rotation = Rotation.CLOCKWISE_90;
		this.resourceLocation = new ResourceLocation(APIConfig.PROJECT_ID, "dojo");
		this.build(template);

		WyDebug.debug("Dojo spawned at: /tp " + this.templatePosition.getX() + " ~ " + this.templatePosition.getZ());
	}

	@Override
	protected void readAdditional(CompoundNBT nbt)
	{
		nbt.putInt("TPX", this.templatePosition.getX());
		nbt.putInt("TPY", this.templatePosition.getY());
		nbt.putInt("TPZ", this.templatePosition.getZ());
	}

	private void build(TemplateManager templateManager)
	{
		Template template = templateManager.getTemplateDefaulted(this.resourceLocation);
		PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setCenterOffset(DojoPiece.STRUCTURE_OFFSET).addProcessor(BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK);
		this.setup(template, this.templatePosition.add(0, 40, 0), placementsettings);
	}

	@Override
	protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand, MutableBoundingBox sbb)
	{

	}
}
