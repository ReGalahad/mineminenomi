package xyz.pixelatedw.mineminenomi.models.armors;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.abilities.ZoomAbility;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

@OnlyIn(Dist.CLIENT)
public class SniperGogglesModel extends BipedModel
{
	public RendererModel base;
	public RendererModel frame1;
	public RendererModel frame2;
	public RendererModel frame3;
	public RendererModel frame4;
	public RendererModel frame5;
	public RendererModel frame6;
	public RendererModel frame7;
	public RendererModel frame8;
	public RendererModel frame9;
	public RendererModel right_ear_support;
	public RendererModel left_ear_support;
	public RendererModel right_eye;
	public RendererModel right_eye_extension;
	public RendererModel right_eye_circle_1;
	public RendererModel right_eye_circle_2;
	public RendererModel left_eye;
	public RendererModel left_eye_extension;
	public RendererModel left_eye_circle_1;
	public RendererModel left_eye_circle_2;

	public SniperGogglesModel()
	{
		this.textureWidth = 32;
		this.textureHeight = 16;
		this.right_eye = new RendererModel(this, 6, 8);
		this.right_eye.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.right_eye.addBox(-3.0F, 3.5F, -5.0F, 2, 2, 1, 0.0F);
		this.right_eye_extension = new RendererModel(this, 6, 12);
		this.right_eye_extension.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.right_eye_extension.addBox(-2.5F, 4.0F, -5.3F, 1, 1, 1, 0.0F);
		this.left_eye_circle_1 = new RendererModel(this, 6, 8);
		this.left_eye_circle_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.left_eye_circle_1.addBox(0.5F, 4.0F, -5.0F, 3, 1, 1, 0.0F);
		this.base = new RendererModel(this, 0, 0);
		this.base.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.base.addBox(-4.0F, -8.0F, -4.0F, 0, 0, 0, 0.0F);
		this.left_ear_support = new RendererModel(this, 10, 3);
		this.left_ear_support.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.left_ear_support.addBox(4.0F, 4.0F, -0.5F, 1, 2, 2, 0.0F);
		this.right_ear_support = new RendererModel(this, 10, 3);
		this.right_ear_support.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.right_ear_support.addBox(-5.0F, 4.0F, -0.5F, 1, 2, 2, 0.0F);
		this.frame7 = new RendererModel(this, 5, 3);
		this.frame7.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.frame7.addBox(1.5F, 1.0F, -4.5F, 1, 3, 1, 0.0F);
		this.left_eye = new RendererModel(this, 6, 8);
		this.left_eye.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.left_eye.addBox(1.0F, 3.5F, -5.0F, 2, 2, 1, 0.0F);
		this.frame2 = new RendererModel(this, 0, 3);
		this.frame2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.frame2.addBox(-4.5F, 0.9F, 0.0F, 1, 5, 1, 0.0F);
		this.frame4 = new RendererModel(this, 12, 6);
		this.frame4.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.frame4.addBox(-2.5F, 0.0F, -4.5F, 1, 1, 9, 0.0F);
		this.left_eye_extension = new RendererModel(this, 6, 12);
		this.left_eye_extension.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.left_eye_extension.addBox(1.5F, 4.0F, -5.3F, 1, 1, 1, 0.0F);
		this.frame3 = new RendererModel(this, 0, 3);
		this.frame3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.frame3.addBox(3.5F, 0.9F, 0.0F, 1, 5, 1, 0.0F);
		this.frame5 = new RendererModel(this, 12, 6);
		this.frame5.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.frame5.addBox(1.5F, 0.0F, -4.5F, 1, 1, 9, 0.0F);
		this.left_eye_circle_2 = new RendererModel(this, 6, 8);
		this.left_eye_circle_2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.left_eye_circle_2.addBox(1.5F, 3.0F, -5.0F, 1, 3, 1, 0.0F);
		this.frame9 = new RendererModel(this, 5, 3);
		this.frame9.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.frame9.addBox(-2.5F, 1.0F, 3.5F, 1, 4, 1, 0.0F);
		this.frame6 = new RendererModel(this, 5, 3);
		this.frame6.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.frame6.addBox(-2.5F, 1.0F, -4.5F, 1, 3, 1, 0.0F);
		this.right_eye_circle_1 = new RendererModel(this, 6, 8);
		this.right_eye_circle_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.right_eye_circle_1.addBox(-3.5F, 4.0F, -5.0F, 3, 1, 1, 0.0F);
		this.right_eye_circle_2 = new RendererModel(this, 6, 8);
		this.right_eye_circle_2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.right_eye_circle_2.addBox(-2.5F, 3.0F, -5.0F, 1, 3, 1, 0.0F);
		this.frame1 = new RendererModel(this, 0, 0);
		this.frame1.setRotationPoint(0.0F, -8.5F, 0.0F);
		this.frame1.addBox(-4.5F, -0.1F, 0.0F, 9, 1, 1, 0.0F);
		this.frame8 = new RendererModel(this, 5, 3);
		this.frame8.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.frame8.addBox(1.5F, 1.0F, 3.5F, 1, 4, 1, 0.0F);
		this.frame6.addChild(this.right_eye);
		this.right_eye.addChild(this.right_eye_extension);
		this.left_eye.addChild(this.left_eye_circle_1);
		this.frame3.addChild(this.left_ear_support);
		this.frame2.addChild(this.right_ear_support);
		this.frame1.addChild(this.frame7);
		this.frame7.addChild(this.left_eye);
		this.frame1.addChild(this.frame2);
		this.frame1.addChild(this.frame4);
		this.left_eye.addChild(this.left_eye_extension);
		this.frame1.addChild(this.frame3);
		this.frame1.addChild(this.frame5);
		this.left_eye.addChild(this.left_eye_circle_2);
		this.frame1.addChild(this.frame9);
		this.frame1.addChild(this.frame6);
		this.right_eye.addChild(this.right_eye_circle_1);
		this.right_eye.addChild(this.right_eye_circle_2);
		this.base.addChild(this.frame1);
		this.frame1.addChild(this.frame8);
	}

	@Override
	public void render(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		this.bipedHeadwear = this.base;
		
		this.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

		if(entity instanceof PlayerEntity)
		{
			PlayerEntity player = Minecraft.getInstance().player;		
			IAbilityData aprops = AbilityDataCapability.get(player);
			
			if(aprops.getEquippedAbility(ZoomAbility.INSTANCE) != null)
			{
				Ability ability = aprops.getEquippedAbility(ZoomAbility.INSTANCE);
				
				if(!ability.isContinuous())
				{
					this.left_eye.offsetY = -0.1F;
					this.right_eye.offsetY = -0.1F;
				}
			}
		}
				
		if(this.isSneak)
			this.base.offsetY = 0.2F;
		
		this.base.render(scale);
	}

	@Override
	public void setRotationAngles(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor)
	{
		super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(RendererModel model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}