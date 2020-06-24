package xyz.pixelatedw.mineminenomi.models.entities.mobs.animals;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.entities.mobs.animals.YagaraBullEntity;

@OnlyIn(Dist.CLIENT)
public class YagaraBullModel<T extends YagaraBullEntity> extends BipedModel<T>
{
	public RendererModel body1;
	public RendererModel body2;
	public RendererModel tail1;
	public RendererModel neck1;
	public RendererModel leftfin1;
	public RendererModel leftfin2;
	public RendererModel rightfin1;
	public RendererModel rightfin2;
	public RendererModel saddle;
	public RendererModel belt1;
	public RendererModel belt2;
	public RendererModel tail2;
	public RendererModel tail3;
	public RendererModel tail4;
	public RendererModel tail5;
	public RendererModel tail6;
	public RendererModel neck2;
	public RendererModel neck3;
	public RendererModel neck4;
	public RendererModel head1;
	public RendererModel mane;
	public RendererModel head2;
	public RendererModel head3;
	public RendererModel righteye;
	public RendererModel lefteye;
	public RendererModel leftgill;
	public RendererModel rightgill;
	public RendererModel mout;
	public RendererModel saddleside1;
	public RendererModel saddleside2;
	public RendererModel saddlefront;
	public RendererModel saddleside3;
	public RendererModel saddleside4;
	public RendererModel saddleback;
	public RendererModel saddlemiddle;

	public YagaraBullModel()
	{
		this.textureWidth = 180;
		this.textureHeight = 90;
		
		this.bipedBody.isHidden = true;
		this.bipedHead.isHidden = true;
		this.bipedHeadwear.isHidden = true;
		this.bipedLeftArm.isHidden = true;
		this.bipedLeftLeg.isHidden = true;
		this.bipedRightArm.isHidden = true;
		this.bipedRightLeg.isHidden = true;
		
		this.saddleside2 = new RendererModel(this, 25, 64);
		this.saddleside2.setRotationPoint(-4.8F, -1.2F, 10.1F);
		this.saddleside2.addBox(0.0F, -5.0F, -5.5F, 1, 5, 11, 0.0F);
		this.setRotateAngle(saddleside2, 0.017453292519943295F, 0.10471975511965977F, -0.13962634015954636F);
		this.rightfin1 = new RendererModel(this, 99, 41);
		this.rightfin1.setRotationPoint(-3.9000000953674316F, 20.299999237060547F, -6.0F);
		this.rightfin1.addBox(-8.0F, -0.5F, -6.0F, 8, 1, 6, 0.0F);
		this.setRotateAngle(rightfin1, -0.1922112707456648F, 0.6485390995515854F, -0.44355388853834565F);
		this.tail2 = new RendererModel(this, 47, 15);
		this.tail2.setRotationPoint(0.0F, 0.0F, 4.0F);
		this.tail2.addBox(-4.0F, -3.5F, 0.0F, 8, 7, 5, 0.0F);
		this.setRotateAngle(tail2, -0.03490658503988659F, -0.0F, 0.0F);
		this.saddleside3 = new RendererModel(this, 50, 64);
		this.saddleside3.setRotationPoint(5.2F, -0.8F, -0.6F);
		this.saddleside3.addBox(-1.0F, -5.0F, -5.5F, 1, 5, 11, 0.0F);
		this.setRotateAngle(saddleside3, 0.05235987755982988F, 0.10471975511965977F, 0.13962634015954636F);
		this.saddle = new RendererModel(this, 115, 0);
		this.saddle.setRotationPoint(0.0F, 12.7F, -5.0F);
		this.saddle.addBox(-5.5F, -1.0F, -5.5F, 11, 1, 21, 0.0F);
		this.setRotateAngle(saddle, -0.05235987755982988F, -0.0F, 0.0F);
		this.neck3 = new RendererModel(this, 78, 30);
		this.neck3.setRotationPoint(0.0F, -1.6F, -3.0F);
		this.neck3.addBox(-3.5F, -2.5F, 0.0F, 7, 5, 5, 0.0F);
		this.setRotateAngle(neck3, -0.4886921905584123F, 0.0F, 0.0F);
		this.body2 = new RendererModel(this, 0, 23);
		this.body2.setRotationPoint(0.0F, 18.399999618530273F, 5.300000190734863F);
		this.body2.addBox(-6.0F, -5.0F, -4.5F, 12, 10, 9, 0.0F);
		this.setRotateAngle(body2, 0.03490658476948738F, -0.0F, 0.0F);
		this.rightgill = new RendererModel(this, 0, 61);
		this.rightgill.setRotationPoint(-2.2F, -1.0F, 0.0F);
		this.rightgill.addBox(-3.0F, 0.0F, -2.0F, 3, 0, 2, 0.0F);
		this.setRotateAngle(rightgill, -0.17732545200262387F, 0.30647981665020424F, -0.933227551041368F);
		this.tail6 = new RendererModel(this, 47, 42);
		this.tail6.setRotationPoint(0.0F, 0.0F, 2.5F);
		this.tail6.addBox(0.0F, -2.5F, -2.5F, 0, 7, 7, 0.0F);
		this.setRotateAngle(tail6, 0.6981317007977318F, -0.0F, 0.0F);
		this.body1 = new RendererModel(this, 0, 0);
		this.body1.setRotationPoint(0.0F, 18.299999237060547F, -4.0F);
		this.body1.addBox(-6.0F, -5.5F, -5.5F, 12, 11, 11, 0.0F);
		this.setRotateAngle(body1, -0.06981316953897475F, -0.0F, 0.0F);
		this.tail4 = new RendererModel(this, 47, 37);
		this.tail4.setRotationPoint(0.0F, 0.0F, 3.0F);
		this.tail4.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 3, 0.0F);
		this.setRotateAngle(tail4, -0.03490658503988659F, -0.0F, 0.0F);
		this.neck2 = new RendererModel(this, 78, 0);
		this.neck2.setRotationPoint(0.0F, -3.0F, -3.5F);
		this.neck2.addBox(-4.0F, -3.0F, 0.0F, 8, 6, 7, 0.0F);
		this.setRotateAngle(neck2, -0.6981317007977318F, -0.0F, 0.0F);
		this.head2 = new RendererModel(this, 0, 52);
		this.head2.setRotationPoint(0.0F, 0.0F, -0.5F);
		this.head2.addBox(-3.0F, -5.0F, -3.0F, 6, 5, 3, 0.0F);
		this.setRotateAngle(head2, 0.03490658503988659F, -0.0F, 0.0F);
		this.rightfin2 = new RendererModel(this, 99, 49);
		this.rightfin2.setRotationPoint(-4.400000095367432F, 21.299999237060547F, 8.0F);
		this.rightfin2.addBox(-6.0F, -0.5F, -5.0F, 6, 1, 5, 0.0F);
		this.setRotateAngle(rightfin2, -0.24228174870360314F, 0.7267750770383524F, -0.47536334228374844F);
		this.leftfin2 = new RendererModel(this, 99, 49);
		this.leftfin2.setRotationPoint(4.400000095367432F, 21.299999237060547F, 8.0F);
		this.leftfin2.addBox(0.0F, -0.5F, -5.0F, 6, 1, 5, 0.0F);
		this.setRotateAngle(leftfin2, -0.24228174870360314F, -0.7267750770383524F, 0.47536334228374844F);
		this.mout = new RendererModel(this, 19, 52);
		this.mout.setRotationPoint(0.0F, 0.0F, -4.0F);
		this.mout.addBox(-3.0F, -2.5F, -4.0F, 6, 5, 4, 0.0F);
		this.saddlemiddle = new RendererModel(this, 100, 78);
		this.saddlemiddle.setRotationPoint(-0.2F, -0.9F, 4.5F);
		this.saddlemiddle.addBox(-5.0F, -5.0F, -1.0F, 11, 5, 1, 0.0F);
		this.setRotateAngle(saddlemiddle, -0.08726646259971647F, -0.0F, 0.0F);
		this.saddleside4 = new RendererModel(this, 75, 64);
		this.saddleside4.setRotationPoint(5.2F, -1.1F, 9.9F);
		this.saddleside4.addBox(-1.0F, -5.0F, -5.5F, 1, 5, 11, 0.0F);
		this.setRotateAngle(saddleside4, 0.017453292519943295F, -0.10367255756846318F, 0.14032447186034408F);
		this.head1 = new RendererModel(this, 0, 43);
		this.head1.setRotationPoint(0.0F, -0.5F, 0.5F);
		this.head1.addBox(-3.0F, -5.0F, -1.0F, 6, 5, 3, 0.0F);
		this.setRotateAngle(head1, 1.7976891295541593F, -0.0F, 0.0F);
		this.lefteye = new RendererModel(this, 40, 57);
		this.lefteye.setRotationPoint(2.5F, -4.5F, -1.5F);
		this.lefteye.addBox(-1.0F, -1.0F, -2.0F, 2, 2, 2, 0.0F);
		this.setRotateAngle(lefteye, -0.08726646259971647F, -0.0F, 0.0F);
		this.saddleback = new RendererModel(this, 100, 71);
		this.saddleback.setRotationPoint(0.4F, -1.3F, 15.6F);
		this.saddleback.addBox(-4.5F, -5.0F, -1.0F, 9, 5, 1, 0.0F);
		this.setRotateAngle(saddleback, 0.017453292519943295F, -0.0F, 0.0F);
		this.righteye = new RendererModel(this, 40, 57);
		this.righteye.setRotationPoint(-2.5F, -4.5F, -1.5F);
		this.righteye.addBox(-1.0F, -1.0F, -2.0F, 2, 2, 2, 0.0F);
		this.setRotateAngle(righteye, -0.08726646259971647F, -0.0F, 0.0F);
		this.neck1 = new RendererModel(this, 78, 14);
		this.neck1.setRotationPoint(0.0F, 16.0F, -13.8F);
		this.neck1.addBox(-5.5F, -4.5F, 0.0F, 11, 9, 6, 0.0F);
		this.setRotateAngle(neck1, -0.3490658503988659F, -0.0F, 0.0F);
		this.saddleside1 = new RendererModel(this, 0, 64);
		this.saddleside1.setRotationPoint(-5.0F, -0.9F, -0.7F);
		this.saddleside1.addBox(0.0F, -5.0F, -5.5F, 1, 5, 11, 0.0F);
		this.setRotateAngle(saddleside1, 0.05235987755982988F, -0.05235987755982988F, -0.13962634015954636F);
		this.tail3 = new RendererModel(this, 47, 28);
		this.tail3.setRotationPoint(0.0F, 0.0F, 4.5F);
		this.tail3.addBox(-2.5F, -2.5F, 0.0F, 5, 5, 3, 0.0F);
		this.setRotateAngle(tail3, -0.06981317007977318F, -0.0F, 0.0F);
		this.belt1 = new RendererModel(this, 152, 64);
		this.belt1.setRotationPoint(0.0F, 12.800000190734863F, 6.900000095367432F);
		this.belt1.addBox(-6.5F, 0.0F, -1.0F, 13, 11, 1, 0.0F);
		this.setRotateAngle(belt1, -0.08726646006107329F, -0.0F, 0.0F);
		this.head3 = new RendererModel(this, 19, 43);
		this.head3.setRotationPoint(0.0F, -2.5F, -2.5F);
		this.head3.addBox(-2.5F, -2.0F, -4.0F, 5, 4, 4, 0.0F);
		this.setRotateAngle(head3, 0.13962634015954636F, -0.0F, 0.0F);
		this.tail5 = new RendererModel(this, 47, 44);
		this.tail5.setRotationPoint(0.0F, 0.0F, 3.0F);
		this.tail5.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F);
		this.setRotateAngle(tail5, -0.08726646259971647F, -0.0F, 0.0F);
		this.belt2 = new RendererModel(this, 152, 77);
		this.belt2.setRotationPoint(0.0F, 12.300000190734863F, -4.099999904632568F);
		this.belt2.addBox(-6.5F, 0.0F, -1.0F, 13, 12, 1, 0.0F);
		this.setRotateAngle(belt2, -0.08726646006107329F, -0.0F, 0.0F);
		this.saddlefront = new RendererModel(this, 100, 64);
		this.saddlefront.setRotationPoint(0.0F, -0.7F, -5.0F);
		this.saddlefront.addBox(-5.0F, -5.0F, -1.0F, 10, 5, 1, 0.0F);
		this.setRotateAngle(saddlefront, 0.08726646259971647F, -0.0F, 0.0F);
		this.leftfin1 = new RendererModel(this, 99, 41);
		this.leftfin1.setRotationPoint(3.9000000953674316F, 20.299999237060547F, -6.0F);
		this.leftfin1.addBox(0.0F, -0.5F, -6.0F, 8, 1, 6, 0.0F);
		this.setRotateAngle(leftfin1, -0.1922112707456648F, -0.6485390995515854F, 0.44355388853834565F);
		this.mane = new RendererModel(this, 67, 29);
		this.mane.setRotationPoint(0.0F, -7.5F, 1.0F);
		this.mane.addBox(0.0F, 0.0F, 0.0F, 0, 10, 4, 0.0F);
		this.setRotateAngle(mane, -0.17453292519943295F, -0.0F, 0.0F);
		this.neck4 = new RendererModel(this, 78, 41);
		this.neck4.setRotationPoint(0.0F, -0.8F, -3.0F);
		this.neck4.addBox(-3.0F, -2.5F, 0.0F, 6, 5, 4, 0.0F);
		this.setRotateAngle(neck4, -0.20943951023931953F, -0.0F, 0.0F);
		this.tail1 = new RendererModel(this, 47, 0);
		this.tail1.setRotationPoint(0.0F, 18.0F, 9.5F);
		this.tail1.addBox(-5.0F, -4.5F, 0.0F, 10, 9, 5, 0.0F);
		this.setRotateAngle(tail1, 0.08726646006107329F, -0.0F, 0.0F);
		this.leftgill = new RendererModel(this, 0, 61);
		this.leftgill.setRotationPoint(2.2F, -1.0F, 0.0F);
		this.leftgill.addBox(0.0F, 0.0F, -2.0F, 3, 0, 2, 0.0F);
		this.setRotateAngle(leftgill, -0.17732545200262387F, -0.30647981665020424F, 0.933227551041368F);
		this.saddle.addChild(this.saddleside2);
		this.tail1.addChild(this.tail2);
		this.saddle.addChild(this.saddleside3);
		this.neck2.addChild(this.neck3);
		this.head2.addChild(this.rightgill);
		this.tail5.addChild(this.tail6);
		this.tail3.addChild(this.tail4);
		this.neck1.addChild(this.neck2);
		this.head1.addChild(this.head2);
		this.head3.addChild(this.mout);
		this.saddle.addChild(this.saddlemiddle);
		this.saddle.addChild(this.saddleside4);
		this.neck4.addChild(this.head1);
		this.head2.addChild(this.lefteye);
		this.saddle.addChild(this.saddleback);
		this.head2.addChild(this.righteye);
		this.saddle.addChild(this.saddleside1);
		this.tail2.addChild(this.tail3);
		this.head2.addChild(this.head3);
		this.tail4.addChild(this.tail5);
		this.saddle.addChild(this.saddlefront);
		this.head1.addChild(this.mane);
		this.neck3.addChild(this.neck4);
		this.head2.addChild(this.leftgill);
	}

	@Override
	public void render(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		this.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

		this.body1.render(scale);
		this.rightfin1.render(scale);
		this.leftfin1.render(scale);
		this.tail1.render(scale);
		this.neck1.render(scale);
		this.leftfin2.render(scale);
		this.rightfin2.render(scale);
		this.body2.render(scale);
		if (entity.isSaddled())
		{
			this.saddle.render(scale);
			this.belt1.render(scale);
			this.belt2.render(scale);
		}
	}

	@Override
	public void setRotationAngles(YagaraBullEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch, float scaleFactor)
	{
		this.tail1.rotateAngleY = 0.1F * (-0.2F + MathHelper.cos(ageInTicks * 0.15F));
		this.tail2.rotateAngleY = 0.2F * (-0.2F + MathHelper.cos(ageInTicks * 0.15F));
		this.tail3.rotateAngleY = 0.1F * (-0.2F + MathHelper.cos(ageInTicks * 0.15F));
		this.tail4.rotateAngleY = 0.2F * (-0.2F + MathHelper.cos(ageInTicks * 0.15F));
		this.tail5.rotateAngleY = 0.1F * (-0.2F + MathHelper.cos(ageInTicks * 0.15F));

		this.leftfin1.rotateAngleY = 0.2F * -MathHelper.cos(ageInTicks * 0.15F);
		this.leftfin1.rotateAngleX = 0.2F * -MathHelper.cos(ageInTicks * 0.15F);
		this.leftfin2.rotateAngleY = 0.2F * MathHelper.cos(ageInTicks * 0.15F);
		this.leftfin2.rotateAngleX = 0.2F * MathHelper.cos(ageInTicks * 0.15F);

		this.rightfin1.rotateAngleY = 0.2F * -MathHelper.cos(ageInTicks * 0.15F);
		this.rightfin1.rotateAngleX = 0.2F * -MathHelper.cos(ageInTicks * 0.15F);
		this.rightfin2.rotateAngleY = 0.2F * MathHelper.cos(ageInTicks * 0.15F);
		this.rightfin2.rotateAngleX = 0.2F * MathHelper.cos(ageInTicks * 0.15F);

		// Neck animation
		if (ageInTicks % 300 > 0 && ageInTicks % 300 < 50)
			this.neck4.rotateAngleZ = 0.4F * MathHelper.cos(ageInTicks * 0.25F);
		else
			this.neck4.rotateAngleZ = (float) Math.toDegrees(0);
	}

	public void setRotateAngle(RendererModel modelRenderer, float x, float y, float z)
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
