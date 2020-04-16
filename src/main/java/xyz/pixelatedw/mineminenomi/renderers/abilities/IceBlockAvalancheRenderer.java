package xyz.pixelatedw.mineminenomi.renderers.abilities;

import java.awt.Color;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import xyz.pixelatedw.mineminenomi.entities.projectiles.hie.IceBlockAvalancheProjectile;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.models.CubeModel;
import xyz.pixelatedw.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.wypi.abilities.renderers.AbilityProjectileRenderer;

public class IceBlockAvalancheRenderer extends AbilityProjectileRenderer {

	public IceBlockAvalancheRenderer(EntityRendererManager renderManager, EntityModel model) {
		super(renderManager, model);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doRender(AbilityProjectileEntity entity, double x, double y, double z, float entityYaw,
			float partialTicks) {

		if (entity instanceof IceBlockAvalancheProjectile) {
			IceBlockAvalancheProjectile e = (IceBlockAvalancheProjectile) entity;
			if (e.getFinalized() == false) {
				if ((this.getScale().x + e.ticksExisted / 5) < 30) {
					this.setScale(8 + e.ticksExisted / 5, 8 + e.ticksExisted / 5, 8 + e.ticksExisted / 5);
				} 
			}
		}
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	public static class Factory implements IRenderFactory<AbilityProjectileEntity> {
		private EntityModel model = new CubeModel();
		private double scaleX = 1, scaleY = 1, scaleZ = 1;
		private double offsetX = 0, offsetY = 0, offsetZ = 0;
		private double red = 255, green = 255, blue = 255, alpha = 255;
		private ResourceLocation texture;

		public Factory(EntityModel model) {
			this.model = model;
		}

		public Factory setTexture(String textureName) {
			this.texture = new ResourceLocation(APIConfig.PROJECT_ID,
					"textures/models/projectiles/" + textureName + ".png");
			return this;
		}

		public Factory setColor(double red, double green, double blue, double alpha) {
			this.red = red;
			this.green = green;
			this.blue = blue;
			this.alpha = alpha;
			return this;
		}

		public Factory setColor(String hex) {
			Color color = WyHelper.hexToRGB(hex);
			this.red = color.getRed();
			this.green = color.getGreen();
			this.blue = color.getBlue();
			this.alpha = color.getAlpha();
			return this;
		}

		public Factory setAlpha(double alpha) {
			this.alpha = alpha;
			return this;
		}

		public Factory setScale(double scale) {
			this.scaleX = this.scaleY = this.scaleZ = scale;
			return this;
		}

		public Factory setScale(double scaleX, double scaleY, double scaleZ) {
			this.scaleX = scaleX;
			this.scaleY = scaleY;
			this.scaleZ = scaleZ;
			return this;
		}

		public Factory setOffset(double offsetX, double offsetY, double offsetZ) {
			this.offsetX = offsetX;
			this.offsetY = offsetY;
			this.offsetZ = offsetZ;
			return this;
		}

		@Override
		public EntityRenderer<? super AbilityProjectileEntity> createRenderFor(EntityRendererManager manager) {
			IceBlockAvalancheRenderer renderer = new IceBlockAvalancheRenderer(manager, this.model);
			renderer.setTexture(this.texture);
			renderer.setScale(this.scaleX, this.scaleY, this.scaleZ);
			renderer.setOffset(this.offsetX, this.offsetY, this.offsetZ);
			renderer.setColor(this.red, this.green, this.blue, this.alpha);
			return renderer;
		}
	}

}
