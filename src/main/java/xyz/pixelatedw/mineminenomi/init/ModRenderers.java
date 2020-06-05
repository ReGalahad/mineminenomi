package xyz.pixelatedw.mineminenomi.init;

import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.CannonTileEntity;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.DenDenMushiTileEntity;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.WantedPosterPackageTileEntity;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.WantedPosterTileEntity;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.dials.AxeDialTileEntity;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.dials.BreathDialTileEntity;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.dials.EisenDialTileEntity;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.dials.FlameDialTileEntity;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.dials.FlashDialTileEntity;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.dials.ImpactDialTileEntity;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.dials.MilkyDialTileEntity;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.dials.RejectDialTileEntity;
import xyz.pixelatedw.mineminenomi.entities.PhysicalBodyEntity;
import xyz.pixelatedw.mineminenomi.entities.VivreCardEntity;
import xyz.pixelatedw.mineminenomi.entities.WantedPosterPackageEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.animals.DenDenMushiEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.animals.KungFuDugongEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.animals.LapahnEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.bandits.BanditWithSwordEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.marines.MarineCaptainEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.marines.MarineTraderEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.marines.MarineWithGunEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.marines.MarineWithSwordEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.misc.BlackKnightEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.misc.DoppelmanEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.misc.MirageCloneEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.misc.WaxCloneEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.PirateBruteEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.PirateCaptainEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.PirateTraderEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.PirateWithGunEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.PirateWithSwordEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers.BowMasterEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers.DojoSenseiEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.quest.objectives.SniperTargetEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.EntityCloud;
import xyz.pixelatedw.mineminenomi.entities.projectiles.nikyu.ChargingUrsusShockEntity;
import xyz.pixelatedw.mineminenomi.models.blocks.Dial01Model;
import xyz.pixelatedw.mineminenomi.models.blocks.Dial02Model;
import xyz.pixelatedw.mineminenomi.models.blocks.Dial03Model;
import xyz.pixelatedw.mineminenomi.models.blocks.Dial04Model;
import xyz.pixelatedw.mineminenomi.models.entities.mobs.SniperTargetModel;
import xyz.pixelatedw.mineminenomi.models.entities.mobs.animals.DenDenMushiModel;
import xyz.pixelatedw.mineminenomi.models.entities.mobs.animals.KungFuDugongModel;
import xyz.pixelatedw.mineminenomi.models.entities.mobs.animals.LapahnModel;
import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.DojoSenseiModel;
import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.FatHumanModel;
import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.SimpleHumanModel;
import xyz.pixelatedw.mineminenomi.renderers.abilities.ChargingUrsusShockRenderer;
import xyz.pixelatedw.mineminenomi.renderers.abilities.PhysicalBodyRenderer;
import xyz.pixelatedw.mineminenomi.renderers.blocks.CannonTileEntityRenderer;
import xyz.pixelatedw.mineminenomi.renderers.blocks.DenDenMushiTileEntityRenderer;
import xyz.pixelatedw.mineminenomi.renderers.blocks.DialTileEntityRenderer;
import xyz.pixelatedw.mineminenomi.renderers.blocks.WantedPosterTileEntityRenderer;
import xyz.pixelatedw.mineminenomi.renderers.blocks.WantedPostersPackageTileEntityRenderer;
import xyz.pixelatedw.mineminenomi.renderers.entities.BlackKnightRenderer;
import xyz.pixelatedw.mineminenomi.renderers.entities.CloudRenderer;
import xyz.pixelatedw.mineminenomi.renderers.entities.DoppelmanRenderer;
import xyz.pixelatedw.mineminenomi.renderers.entities.GenericMobRenderer;
import xyz.pixelatedw.mineminenomi.renderers.entities.MirageCloneRenderer;
import xyz.pixelatedw.mineminenomi.renderers.entities.VivreCardRenderer;
import xyz.pixelatedw.mineminenomi.renderers.entities.WantedPosterPackageRenderer;
import xyz.pixelatedw.mineminenomi.renderers.entities.WaxCloneRenderer;
import xyz.pixelatedw.mineminenomi.renderers.layers.PotionLayer;

public class ModRenderers
{
    public static void registerRenderers() 
    {
    	//TESRs
    	// Dials
    	ClientRegistry.bindTileEntitySpecialRenderer(AxeDialTileEntity.class, new DialTileEntityRenderer(new Dial01Model(), "axe"));
    	ClientRegistry.bindTileEntitySpecialRenderer(ImpactDialTileEntity.class, new DialTileEntityRenderer(new Dial01Model(), "impact"));
    	ClientRegistry.bindTileEntitySpecialRenderer(FlashDialTileEntity.class, new DialTileEntityRenderer(new Dial01Model(), "flash"));
    	ClientRegistry.bindTileEntitySpecialRenderer(BreathDialTileEntity.class, new DialTileEntityRenderer(new Dial02Model(), "breath"));
    	ClientRegistry.bindTileEntitySpecialRenderer(EisenDialTileEntity.class, new DialTileEntityRenderer(new Dial02Model(), "eisen"));
    	ClientRegistry.bindTileEntitySpecialRenderer(MilkyDialTileEntity.class, new DialTileEntityRenderer(new Dial02Model(), "milky"));
    	ClientRegistry.bindTileEntitySpecialRenderer(FlameDialTileEntity.class, new DialTileEntityRenderer(new Dial03Model(), "flame"));
    	ClientRegistry.bindTileEntitySpecialRenderer(RejectDialTileEntity.class, new DialTileEntityRenderer(new Dial04Model(), "reject"));

    	// Other
    	ClientRegistry.bindTileEntitySpecialRenderer(WantedPosterPackageTileEntity.class, new WantedPostersPackageTileEntityRenderer());
    	ClientRegistry.bindTileEntitySpecialRenderer(WantedPosterTileEntity.class, new WantedPosterTileEntityRenderer());
    	ClientRegistry.bindTileEntitySpecialRenderer(DenDenMushiTileEntity.class, new DenDenMushiTileEntityRenderer());
    	ClientRegistry.bindTileEntitySpecialRenderer(CannonTileEntity.class, new CannonTileEntityRenderer());

    	// Entities
    	// Marines
		RenderingRegistry.registerEntityRenderingHandler(MarineWithSwordEntity.class, new GenericMobRenderer.Factory(new SimpleHumanModel(), 1, null));
		RenderingRegistry.registerEntityRenderingHandler(MarineWithGunEntity.class, new GenericMobRenderer.Factory(new SimpleHumanModel(), 1, null));
		RenderingRegistry.registerEntityRenderingHandler(MarineCaptainEntity.class, new GenericMobRenderer.Factory(new SimpleHumanModel(), 1, null));
		RenderingRegistry.registerEntityRenderingHandler(MarineTraderEntity.class, new GenericMobRenderer.Factory(new SimpleHumanModel(), 1, null));

		// Pirates
		RenderingRegistry.registerEntityRenderingHandler(PirateWithSwordEntity.class, new GenericMobRenderer.Factory(new SimpleHumanModel(), 1, null));
		RenderingRegistry.registerEntityRenderingHandler(PirateWithGunEntity.class, new GenericMobRenderer.Factory(new SimpleHumanModel(), 1, null));
		RenderingRegistry.registerEntityRenderingHandler(PirateCaptainEntity.class, new GenericMobRenderer.Factory(new SimpleHumanModel(), 1, null));
		RenderingRegistry.registerEntityRenderingHandler(PirateTraderEntity.class, new GenericMobRenderer.Factory(new SimpleHumanModel(), 1, null));
		RenderingRegistry.registerEntityRenderingHandler(PirateBruteEntity.class, new GenericMobRenderer.Factory(new FatHumanModel(), 1.25F, null));

		// Bandits
		RenderingRegistry.registerEntityRenderingHandler(BanditWithSwordEntity.class, new GenericMobRenderer.Factory(new SimpleHumanModel(), 1, null));

		// Factionless
		RenderingRegistry.registerEntityRenderingHandler(DojoSenseiEntity.class, new GenericMobRenderer.Factory(new DojoSenseiModel(), 1, null));
		RenderingRegistry.registerEntityRenderingHandler(BowMasterEntity.class, new GenericMobRenderer.Factory(new SimpleHumanModel(), 1, null));

		// Animals
		RenderingRegistry.registerEntityRenderingHandler(DenDenMushiEntity.class, new GenericMobRenderer.Factory(new DenDenMushiModel(), 1, null));
		RenderingRegistry.registerEntityRenderingHandler(LapahnEntity.class, new GenericMobRenderer.Factory(new LapahnModel(), 1, "lapahn"));
		RenderingRegistry.registerEntityRenderingHandler(KungFuDugongEntity.class, new GenericMobRenderer.Factory(new KungFuDugongModel(), 1, "kung_fu_dugong"));

		// Others
		RenderingRegistry.registerEntityRenderingHandler(DoppelmanEntity.class, new DoppelmanRenderer.Factory());
	    RenderingRegistry.registerEntityRenderingHandler(WaxCloneEntity.class, new WaxCloneRenderer.Factory());
		RenderingRegistry.registerEntityRenderingHandler(BlackKnightEntity.class, new BlackKnightRenderer.Factory());
		RenderingRegistry.registerEntityRenderingHandler(EntityCloud.class, new CloudRenderer.Factory());
		RenderingRegistry.registerEntityRenderingHandler(WantedPosterPackageEntity.class, new WantedPosterPackageRenderer.Factory());
		RenderingRegistry.registerEntityRenderingHandler(VivreCardEntity.class, new VivreCardRenderer.Factory());
		RenderingRegistry.registerEntityRenderingHandler(ChargingUrsusShockEntity.class, new ChargingUrsusShockRenderer.Factory());
		RenderingRegistry.registerEntityRenderingHandler(SniperTargetEntity.class, new GenericMobRenderer.Factory(new SniperTargetModel(), 1, "sniper_target"));
		RenderingRegistry.registerEntityRenderingHandler(PhysicalBodyEntity.class, new PhysicalBodyRenderer.Factory());
	    RenderingRegistry.registerEntityRenderingHandler(MirageCloneEntity.class, new MirageCloneRenderer.Factory());

		/*Pretty simple system, you just create a new layer, and add the layer to the living entities or the player */

		for (Map.Entry<Class<? extends Entity>, EntityRenderer<? extends Entity>> entry : Minecraft.getInstance().getRenderManager().renderers.entrySet()) {
			EntityRenderer render = entry.getValue();
			if (render instanceof LivingRenderer) {
				((LivingRenderer) render).addLayer(new PotionLayer((IEntityRenderer) render));
			}
		}

		for (Map.Entry<String, PlayerRenderer> entry : Minecraft.getInstance().getRenderManager().getSkinMap().entrySet()) {
			PlayerRenderer render = entry.getValue();
			render.addLayer(new PotionLayer(render));
		}

    }
}
