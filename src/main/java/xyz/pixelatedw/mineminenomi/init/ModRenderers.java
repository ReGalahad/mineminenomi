package xyz.pixelatedw.mineminenomi.init;

import java.util.List;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityProjectileEntity;
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
import xyz.pixelatedw.mineminenomi.entities.WantedPosterPackageEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.bandits.EntityBanditWithSword;
import xyz.pixelatedw.mineminenomi.entities.mobs.marines.EntityMarineCaptain;
import xyz.pixelatedw.mineminenomi.entities.mobs.marines.EntityMarineWithGun;
import xyz.pixelatedw.mineminenomi.entities.mobs.marines.EntityMarineWithSword;
import xyz.pixelatedw.mineminenomi.entities.mobs.misc.EntityBlackKnight;
import xyz.pixelatedw.mineminenomi.entities.mobs.misc.EntityDoppelman;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.EntityPirateCaptain;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.EntityPirateWithGun;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.EntityPirateWithSword;
import xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers.DojoSenseiEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.ExtraProjectiles.EntityCloud;
import xyz.pixelatedw.mineminenomi.models.blocks.Dial01Model;
import xyz.pixelatedw.mineminenomi.models.blocks.Dial02Model;
import xyz.pixelatedw.mineminenomi.models.blocks.Dial03Model;
import xyz.pixelatedw.mineminenomi.models.blocks.Dial04Model;
import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.DojoSenseiModel;
import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.SimpleHumanModel;
import xyz.pixelatedw.mineminenomi.renderers.blocks.DialTileEntityRenderer;
import xyz.pixelatedw.mineminenomi.renderers.blocks.WantedPosterTileEntityRenderer;
import xyz.pixelatedw.mineminenomi.renderers.blocks.WantedPostersPackageTileEntityRenderer;
import xyz.pixelatedw.mineminenomi.renderers.entities.BlackKnightRenderer;
import xyz.pixelatedw.mineminenomi.renderers.entities.CloudRenderer;
import xyz.pixelatedw.mineminenomi.renderers.entities.DoppelmanRenderer;
import xyz.pixelatedw.mineminenomi.renderers.entities.GenericMobRenderer;
import xyz.pixelatedw.mineminenomi.renderers.entities.WantedPosterPackageRenderer;

public class ModRenderers
{
    public static void registerRenderers() 
    {
    	// Projectiles
    	for(List<AbilityProjectileEntity.Data> list : ModDevilFruits.ALL_PROJECTILES)
    	{
    		list.forEach((value) -> 
    		{
    			RenderingRegistry.registerEntityRenderingHandler(value.getEntityClass(), value.getFactory() );			
    		});
    	}
    	
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

    	// Mobs
    	// Marines
		RenderingRegistry.registerEntityRenderingHandler(EntityMarineWithSword.class, new GenericMobRenderer.Factory(new SimpleHumanModel(), 1, null));
		RenderingRegistry.registerEntityRenderingHandler(EntityMarineWithGun.class, new GenericMobRenderer.Factory(new SimpleHumanModel(), 1, null));
		RenderingRegistry.registerEntityRenderingHandler(EntityMarineCaptain.class, new GenericMobRenderer.Factory(new SimpleHumanModel(), 1, null));

		// Pirates
		RenderingRegistry.registerEntityRenderingHandler(EntityPirateWithSword.class, new GenericMobRenderer.Factory(new SimpleHumanModel(), 1, null));
		RenderingRegistry.registerEntityRenderingHandler(EntityPirateWithGun.class, new GenericMobRenderer.Factory(new SimpleHumanModel(), 1, null));
		RenderingRegistry.registerEntityRenderingHandler(EntityPirateCaptain.class, new GenericMobRenderer.Factory(new SimpleHumanModel(), 1, null));

		// Bandits
		RenderingRegistry.registerEntityRenderingHandler(EntityBanditWithSword.class, new GenericMobRenderer.Factory(new SimpleHumanModel(), 1, null));

		// Factionless
		RenderingRegistry.registerEntityRenderingHandler(DojoSenseiEntity.class, new GenericMobRenderer.Factory(new DojoSenseiModel(), 1, null));

		// Others
		RenderingRegistry.registerEntityRenderingHandler(EntityDoppelman.class, new DoppelmanRenderer.Factory());
		RenderingRegistry.registerEntityRenderingHandler(EntityBlackKnight.class, new BlackKnightRenderer.Factory());
		RenderingRegistry.registerEntityRenderingHandler(EntityCloud.class, new CloudRenderer.Factory());
		RenderingRegistry.registerEntityRenderingHandler(WantedPosterPackageEntity.class, new WantedPosterPackageRenderer.Factory());
    }
}
