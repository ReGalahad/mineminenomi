package xyz.pixelatedw.mineminenomi.api;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class ZoanMorphModel<T extends LivingEntity> extends EntityModel<T>
{
	public abstract RendererModel getHandRenderer();

	public abstract RendererModel getArmRenderer();

}
