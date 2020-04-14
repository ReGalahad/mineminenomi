package xyz.pixelatedw.mineminenomi.renderers.entities;

import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.entities.mobs.misc.BlackKnightEntity;
import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.SimpleHumanModel;

@OnlyIn(Dist.CLIENT)
public class BlackKnightRenderer extends GenericMobRenderer<BlackKnightEntity, BipedModel<BlackKnightEntity>>
{

	public BlackKnightRenderer(EntityRendererManager manager)
	{
		super(manager, new SimpleHumanModel());
	}
		
	@Override
	protected ResourceLocation getEntityTexture(BlackKnightEntity entity) 
	{
		AbstractClientPlayerEntity abstractOwner = (AbstractClientPlayerEntity) entity.getOwner();
		return abstractOwner.getLocationSkin();
	}
	
	public static class Factory implements IRenderFactory<BlackKnightEntity>
	{
		public Factory() {}
		
		@Override
		public EntityRenderer<? super BlackKnightEntity> createRenderFor(EntityRendererManager manager)
		{
			return new BlackKnightRenderer(manager);
		}
	}
}
