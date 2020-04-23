package xyz.pixelatedw.mineminenomi.screens;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.config.GuiUtils;
import xyz.pixelatedw.mineminenomi.entities.mobs.misc.TraderEntity;
import xyz.pixelatedw.mineminenomi.screens.extra.NoTextureButton;
import xyz.pixelatedw.wypi.APIConfig;
import xyz.pixelatedw.wypi.WyHelper;

@OnlyIn(Dist.CLIENT)
public class TraderScreen extends Screen{

	public static final ResourceLocation BASE = new ResourceLocation(APIConfig.PROJECT_ID, "textures/gui/blank.png");
	public static final ResourceLocation DEBUG_ICON = new ResourceLocation(APIConfig.PROJECT_ID, "textures/gui/icons/doctor.png");
	public int height = this.getMinecraft().mainWindow.getScaledHeight();
	public int width = this.getMinecraft().mainWindow.getScaledWidth();
	public int baseHeight = height / 2 - 128;
	public int baseWidth = width / 2 -128;
	public TraderEntity entity;
	public TraderScreen(TraderEntity e) {
		super(new StringTextComponent(""));
		// TODO Auto-generated constructor stub
		this.entity = e;
	}

	@Override
	public void render(int p_render_1_, int p_render_2_, float p_render_3_) {
		   this.renderBackground();
		this.getMinecraft().getTextureManager().bindTexture(BASE);
		
		GuiUtils.drawTexturedModalRect(width / 2 - 128, height / 2 - 128, 0, 0, 256, 256, 0);
		
		   }
	
	public void RenderButtons() {
		this.buttons.forEach((b) -> {
			WyHelper.drawIcon(DEBUG_ICON, b.x, b.y, 32, 32);
		});
	}

	@Override
	   public void init(Minecraft p_init_1_, int p_init_2_, int p_init_3_) {
		   super.init(p_init_1_, p_init_2_, p_init_3_);
		   for(int i = 0; i < entity.STACKS.size(); i ++) {
			   this.addButton(new NoTextureButton(baseWidth, (baseHeight - 128) + i * 32, 32, 32, entity.STACKS.get(i).getItem().getRegistryName().getPath(), new Button.IPressable() {
				
				@Override
				public void onPress(Button b) {
					// TODO Auto-generated method stub
					System.out.println(b.getMessage());
				}
			}));
		   }
	}

}
