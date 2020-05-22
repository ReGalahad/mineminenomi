package xyz.pixelatedw.mineminenomi.renderers.entities;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.models.entities.zoans.YomiModel;

@OnlyIn(Dist.CLIENT)
public class YomiMorphRenderer extends ZoanMorphRenderer
{

	private YomiModel model;
	
	public YomiMorphRenderer(EntityRendererManager renderManager, YomiModel model, String texture)
	{
		super(renderManager, model, texture);
		this.model = model;
	}

	@Override
	public void doRender(LivingEntity entity, double x, double y, double z, float u, float v)
	{
		super.doRender(entity, x, y, z, u, v);
		
		this.model.afro.isHidden = true;
		//if(entity instanceof PlayerEntity)
		//{
			/*int age = ((PlayerEntity) entity).ttick;
			if(age > 2000)
			{
				this.model.afro.isHidden = false;
			}*/
		//}
	}
	
	public static class Factory<T extends LivingEntity> implements IRenderFactory<T>
	{
		private YomiModel model;
		private String texture;

		
		public Factory(YomiModel model, String texture)
		{
			this.model = model;
			this.texture = texture;
		}
		
		@Override
		public EntityRenderer<? super T> createRenderFor(EntityRendererManager manager)
		{
			return new YomiMorphRenderer(manager, this.model, this.texture);
		}
	}
}
