package xyz.pixelatedw.mineminenomi.renderers.entities;

import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.entities.mobs.misc.WaxCloneEntity;
import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.SimpleHumanModel;
import xyz.pixelatedw.wypi.APIConfig;

public class WaxCloneRenderer extends GenericMobRenderer<WaxCloneEntity, BipedModel<WaxCloneEntity>>
{

	public static final ResourceLocation WAX_LOCATION = new ResourceLocation(APIConfig.PROJECT_ID, "textures/models/zoanmorph/candle_lock.png");

	public WaxCloneRenderer(EntityRendererManager manager)
	{
		super(manager, new SimpleHumanModel());
	}

	@Override
	protected ResourceLocation getEntityTexture(WaxCloneEntity entity)
	{
		AbstractClientPlayerEntity abstractOwner = (AbstractClientPlayerEntity) entity.getOwner();
		if(entity.isTextured())
			return abstractOwner.getLocationSkin();
		else
			return WAX_LOCATION;
	}

	public static class Factory implements IRenderFactory<WaxCloneEntity>
	{
		public Factory()
		{
		}

		@Override
		public EntityRenderer<? super WaxCloneEntity> createRenderFor(EntityRendererManager manager)
		{
			return new WaxCloneRenderer(manager);
		}
	}
}
