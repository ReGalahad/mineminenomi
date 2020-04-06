package xyz.pixelatedw.mineminenomi.events.passives;

import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import xyz.pixelatedw.mineminenomi.abilities.zushi.AbareHimatsuriAbility;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.models.effects.AbareHimatsuriModel;
import xyz.pixelatedw.mineminenomi.renderers.effects.AbareHimatsuriRenderer;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.abilities.Ability;
import xyz.pixelatedw.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.wypi.data.ability.IAbilityData;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID, value = Dist.CLIENT)
public class ZushiPassiveEvents
{
	private static final AbareHimatsuriRenderer ABARE_HIMATSURI = new AbareHimatsuriRenderer(new AbareHimatsuriModel());

	@SubscribeEvent
	public static void onEntityRendered(RenderLivingEvent.Pre event)
	{
		if (!(event.getEntity() instanceof PlayerEntity))
			return;

		LivingEntity entity = event.getEntity();
		IDevilFruit props = DevilFruitCapability.get(entity);

		if (!props.getDevilFruit().equalsIgnoreCase("zushi_zushi"))
			return;

		IAbilityData abilityProps = AbilityDataCapability.get(entity);
		LivingRenderer renderer = event.getRenderer();

		Ability abareAbility = abilityProps.getEquippedAbility(AbareHimatsuriAbility.INSTANCE);
		boolean isAbareActive = abareAbility != null && abareAbility.isContinuous();

		if (!isAbareActive)
			return;

		if (event.getEntity().onGround)
		{
			/*
			 * BlockState state = event.getEntity().world.getBlockState(new BlockPos(event.getEntity().posX, event.getEntity().posY - 1, event.getEntity().posZ));
			 * String textureName = state.getBlock().getTranslationKey().replace("block.", "").replace("minecraft.", "");
			 */
			ABARE_HIMATSURI.setTextureAndTint("dirt", WyHelper.hexToRGB("#FFFFFF").getRGB());
		}

		if (!event.getEntity().onGround)
			ABARE_HIMATSURI.doRender(event.getEntity(), event.getX(), event.getY(), event.getZ(), 0F, 0.0625F);
	}

}
