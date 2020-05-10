package xyz.pixelatedw.mineminenomi.renderers.entities;

import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.entities.mobs.misc.MirageCloneEntity;
import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.SimpleHumanModel;

@OnlyIn(Dist.CLIENT)
public class MirageCloneRenderer extends GenericMobRenderer<MirageCloneEntity, BipedModel<MirageCloneEntity>>
{

	public MirageCloneRenderer(EntityRendererManager manager)
	{
		super(manager, new SimpleHumanModel());
	}
		
	@Override
	protected ResourceLocation getEntityTexture(MirageCloneEntity entity) 
	{
		AbstractClientPlayerEntity abstractOwner = (AbstractClientPlayerEntity) entity.getOwner();
		return abstractOwner.getLocationSkin();
	}
	
	public static class Factory implements IRenderFactory<MirageCloneEntity>
	{
		public Factory() {}
		
		@Override
		public EntityRenderer<? super MirageCloneEntity> createRenderFor(EntityRendererManager manager)
		{
			return new MirageCloneRenderer(manager);
		}
	}
}
