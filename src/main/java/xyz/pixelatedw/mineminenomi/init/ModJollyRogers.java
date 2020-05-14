package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import xyz.pixelatedw.mineminenomi.api.ModRegistries;
import xyz.pixelatedw.mineminenomi.api.jollyroger.JollyRogerElement;
import xyz.pixelatedw.mineminenomi.api.jollyroger.JollyRogerElement.LayerType;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;
import xyz.pixelatedw.wypi.WyPatreon;

@Mod.EventBusSubscriber(modid = APIConfig.PROJECT_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModJollyRogers
{
	public static final DeferredRegister<JollyRogerElement> JOLLY_ROGER_ELEMENTS = new DeferredRegister<>(ModRegistries.JOLLY_ROGER_ELEMENTS, APIConfig.PROJECT_ID);

	public static JollyRogerElement registerElement(JollyRogerElement element)
	{
		String resourceName = WyHelper.getResourceName(element.getTexture().toString());

		JOLLY_ROGER_ELEMENTS.register(resourceName, () -> element);

		return element;
	}

	// Bases
	public static final JollyRogerElement BASE_0 = new JollyRogerElement(LayerType.BASE).setTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/jolly_rogers/bases/base_0.png"));
	public static final JollyRogerElement BASE_1 = new JollyRogerElement(LayerType.BASE).setCanBeColored().setTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/jolly_rogers/bases/base_1.png"));

	// Details
	public static final JollyRogerElement DETAIL_0 = new JollyRogerElement(LayerType.DETAIL).setCanBeColored().setTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/jolly_rogers/details/detail_0.png"));
	public static final JollyRogerElement DETAIL_1 = new JollyRogerElement(LayerType.DETAIL).setCanBeColored().setTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/jolly_rogers/details/detail_1.png"));
	public static final JollyRogerElement DETAIL_2 = new JollyRogerElement(LayerType.DETAIL).setCanBeColored().setTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/jolly_rogers/details/detail_2.png"));
	public static final JollyRogerElement DETAIL_3 = new JollyRogerElement(LayerType.DETAIL).setCanBeColored().setTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/jolly_rogers/details/detail_3.png"));
	public static final JollyRogerElement DETAIL_4 = new JollyRogerElement(LayerType.DETAIL).setCanBeColored().setTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/jolly_rogers/details/detail_4.png"));
	public static final JollyRogerElement DETAIL_5 = new JollyRogerElement(LayerType.DETAIL).setTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/jolly_rogers/details/detail_5.png"));
	public static final JollyRogerElement DETAIL_6 = new JollyRogerElement(LayerType.DETAIL).setUseCheck(WyPatreon::isSupernova).setTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/jolly_rogers/details/detail_6.png"));
	public static final JollyRogerElement DETAIL_7 = new JollyRogerElement(LayerType.DETAIL).setCanBeColored().setTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/jolly_rogers/details/detail_7.png"));
	public static final JollyRogerElement DETAIL_8 = new JollyRogerElement(LayerType.DETAIL).setCanBeColored().setTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/jolly_rogers/details/detail_8.png"));
	public static final JollyRogerElement DETAIL_9 = new JollyRogerElement(LayerType.DETAIL).setCanBeColored().setTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/jolly_rogers/details/detail_9.png"));
	public static final JollyRogerElement DETAIL_10 = new JollyRogerElement(LayerType.DETAIL).setCanBeColored().setTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/jolly_rogers/details/detail_10.png"));
	public static final JollyRogerElement DETAIL_11 = new JollyRogerElement(LayerType.DETAIL).setTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/jolly_rogers/details/detail_11.png"));
	public static final JollyRogerElement DETAIL_12 = new JollyRogerElement(LayerType.DETAIL).setCanBeColored().setTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/jolly_rogers/details/detail_12.png"));
	public static final JollyRogerElement DETAIL_13 = new JollyRogerElement(LayerType.DETAIL).setCanBeColored().setTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/jolly_rogers/details/detail_13.png"));
	public static final JollyRogerElement DETAIL_14 = new JollyRogerElement(LayerType.DETAIL).setCanBeColored().setTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/jolly_rogers/details/detail_14.png"));
	public static final JollyRogerElement DETAIL_15 = new JollyRogerElement(LayerType.DETAIL).setCanBeColored().setTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/jolly_rogers/details/detail_15.png"));
	public static final JollyRogerElement DETAIL_16 = new JollyRogerElement(LayerType.DETAIL).setCanBeColored().setTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/jolly_rogers/details/detail_16.png"));
	public static final JollyRogerElement DETAIL_17 = new JollyRogerElement(LayerType.DETAIL).setUseCheck(WyPatreon::isSupernova).setTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/jolly_rogers/details/detail_17.png"));
	public static final JollyRogerElement DETAIL_18 = new JollyRogerElement(LayerType.DETAIL).setUseCheck(WyPatreon::isSupernova).setCanBeColored().setTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/jolly_rogers/details/detail_18.png"));
	public static final JollyRogerElement DETAIL_19 = new JollyRogerElement(LayerType.DETAIL).setCanBeColored().setTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/jolly_rogers/details/detail_19.png"));
	public static final JollyRogerElement DETAIL_20 = new JollyRogerElement(LayerType.DETAIL).setCanBeColored().setTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/jolly_rogers/details/detail_20.png"));
	public static final JollyRogerElement DETAIL_21 = new JollyRogerElement(LayerType.DETAIL).setCanBeColored().setTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/jolly_rogers/details/detail_21.png"));
	public static final JollyRogerElement DETAIL_22 = new JollyRogerElement(LayerType.DETAIL).setCanBeColored().setTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/jolly_rogers/details/detail_22.png"));
	public static final JollyRogerElement DETAIL_23 = new JollyRogerElement(LayerType.DETAIL).setCanBeColored().setTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/jolly_rogers/details/detail_23.png"));
	public static final JollyRogerElement DETAIL_24 = new JollyRogerElement(LayerType.DETAIL).setTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/jolly_rogers/details/detail_24.png"));
	public static final JollyRogerElement DETAIL_25 = new JollyRogerElement(LayerType.DETAIL).setCanBeColored().setTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/jolly_rogers/details/detail_25.png"));
	public static final JollyRogerElement DETAIL_26 = new JollyRogerElement(LayerType.DETAIL).setCanBeColored().setTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/jolly_rogers/details/detail_26.png"));
	public static final JollyRogerElement DETAIL_27 = new JollyRogerElement(LayerType.DETAIL).setCanBeColored().setTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/jolly_rogers/details/detail_27.png"));

	// Backgrounds
	public static final JollyRogerElement BACKGROUND_0 = new JollyRogerElement(LayerType.BACKGROUND).setTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/jolly_rogers/details/bg_0.png"));
	public static final JollyRogerElement BACKGROUND_1 = new JollyRogerElement(LayerType.BACKGROUND).setTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/jolly_rogers/details/bg_1.png"));
	public static final JollyRogerElement BACKGROUND_2 = new JollyRogerElement(LayerType.BACKGROUND).setTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/jolly_rogers/details/bg_2.png"));
	public static final JollyRogerElement BACKGROUND_3 = new JollyRogerElement(LayerType.BACKGROUND).setTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/jolly_rogers/details/bg_3.png"));
	public static final JollyRogerElement BACKGROUND_4 = new JollyRogerElement(LayerType.BACKGROUND).setCanBeColored().setTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/jolly_rogers/details/bg_4.png"));
	public static final JollyRogerElement BACKGROUND_5 = new JollyRogerElement(LayerType.BACKGROUND).setCanBeColored().setTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/jolly_rogers/details/bg_5.png"));
	public static final JollyRogerElement BACKGROUND_6 = new JollyRogerElement(LayerType.BACKGROUND).setCanBeColored().setTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/jolly_rogers/details/bg_6.png"));
	public static final JollyRogerElement BACKGROUND_7 = new JollyRogerElement(LayerType.BACKGROUND).setCanBeColored().setTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/jolly_rogers/details/bg_7.png"));
	public static final JollyRogerElement BACKGROUND_8 = new JollyRogerElement(LayerType.BACKGROUND).setCanBeColored().setTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/jolly_rogers/details/bg_8.png"));
	public static final JollyRogerElement BACKGROUND_9 = new JollyRogerElement(LayerType.BACKGROUND).setTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/jolly_rogers/details/bg_9.png"));
	public static final JollyRogerElement BACKGROUND_10 = new JollyRogerElement(LayerType.BACKGROUND).setTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/jolly_rogers/details/bg_10.png"));
	public static final JollyRogerElement BACKGROUND_11 = new JollyRogerElement(LayerType.BACKGROUND).setCanBeColored().setTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/jolly_rogers/details/bg_11.png"));
	public static final JollyRogerElement BACKGROUND_12 = new JollyRogerElement(LayerType.BACKGROUND).setTexture(new ResourceLocation(APIConfig.PROJECT_ID, "textures/jolly_rogers/details/bg_12.png"));

	static
	{
		ModJollyRogers.registerElement(BASE_0);
		ModJollyRogers.registerElement(BASE_1);
		
		ModJollyRogers.registerElement(DETAIL_0);
		ModJollyRogers.registerElement(DETAIL_1);
		ModJollyRogers.registerElement(DETAIL_2);
		ModJollyRogers.registerElement(DETAIL_3);
		ModJollyRogers.registerElement(DETAIL_4);
		ModJollyRogers.registerElement(DETAIL_5);
		ModJollyRogers.registerElement(DETAIL_6);	
		ModJollyRogers.registerElement(DETAIL_7);
		ModJollyRogers.registerElement(DETAIL_8);
		ModJollyRogers.registerElement(DETAIL_9);	
		ModJollyRogers.registerElement(DETAIL_10);
		ModJollyRogers.registerElement(DETAIL_11);
		ModJollyRogers.registerElement(DETAIL_12);
		ModJollyRogers.registerElement(DETAIL_13);
		ModJollyRogers.registerElement(DETAIL_14);
		ModJollyRogers.registerElement(DETAIL_15);
		ModJollyRogers.registerElement(DETAIL_16);
		ModJollyRogers.registerElement(DETAIL_17);
		ModJollyRogers.registerElement(DETAIL_18);	
		ModJollyRogers.registerElement(DETAIL_19);
		ModJollyRogers.registerElement(DETAIL_20);
		ModJollyRogers.registerElement(DETAIL_21);	
		ModJollyRogers.registerElement(DETAIL_22);
		ModJollyRogers.registerElement(DETAIL_23);
		ModJollyRogers.registerElement(DETAIL_24);
		ModJollyRogers.registerElement(DETAIL_25);
		ModJollyRogers.registerElement(DETAIL_26);	
		ModJollyRogers.registerElement(DETAIL_27);
		
		ModJollyRogers.registerElement(BACKGROUND_0);
		ModJollyRogers.registerElement(BACKGROUND_1);
		ModJollyRogers.registerElement(BACKGROUND_2);
		ModJollyRogers.registerElement(BACKGROUND_3);
		ModJollyRogers.registerElement(BACKGROUND_4);
		ModJollyRogers.registerElement(BACKGROUND_5);
		ModJollyRogers.registerElement(BACKGROUND_6);
		ModJollyRogers.registerElement(BACKGROUND_7);
		ModJollyRogers.registerElement(BACKGROUND_8);
		ModJollyRogers.registerElement(BACKGROUND_9);
		ModJollyRogers.registerElement(BACKGROUND_10);
		ModJollyRogers.registerElement(BACKGROUND_11);
		ModJollyRogers.registerElement(BACKGROUND_12);
	}

}