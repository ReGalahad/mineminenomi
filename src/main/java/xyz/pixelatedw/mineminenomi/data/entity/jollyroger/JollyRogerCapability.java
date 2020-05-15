package xyz.pixelatedw.mineminenomi.data.entity.jollyroger;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xyz.pixelatedw.mineminenomi.api.jollyroger.JollyRogerElement;
import xyz.pixelatedw.wypi.APIConfig;

public class JollyRogerCapability
{
	@CapabilityInject(IJollyRoger.class)
	public static final Capability<IJollyRoger> INSTANCE = null;

	public static void register()
	{
		CapabilityManager.INSTANCE.register(IJollyRoger.class, new Capability.IStorage<IJollyRoger>()
		{
			@Override
			public INBT writeNBT(Capability<IJollyRoger> capability, IJollyRoger instance, Direction side)
			{
				CompoundNBT props = new CompoundNBT();

				JollyRogerElement baseElement = instance.getBase();
				if(baseElement != null)
				{
					CompoundNBT baseNBT = new CompoundNBT();
					baseNBT.putString("id", baseElement.getTexture().toString());
					baseNBT.putString("color", baseElement.getColor());
					props.put("base", baseNBT);
				}
					
				try
				{
					ListNBT backgrounds = new ListNBT();
					for (int i = 0; i < instance.getBackgrounds().length; i++)
					{
						JollyRogerElement bgElement = instance.getBackgrounds()[i];				
						if(bgElement != null)
						{
							CompoundNBT backgroundNBT = new CompoundNBT();
							backgroundNBT.putInt("slot", i);
							backgroundNBT.putString("id", bgElement.getTexture().toString());
							backgroundNBT.putString("color", bgElement.getColor());
							backgrounds.add(backgroundNBT);
						}
					}
					props.put("backgrounds", backgrounds);
					
					ListNBT details = new ListNBT();
					for (int i = 0; i < instance.getDetails().length; i++)
					{
						JollyRogerElement detElement = instance.getDetails()[i];				
						if(detElement != null)
						{
							CompoundNBT detailNBT = new CompoundNBT();
							detailNBT.putInt("slot", i);
							detailNBT.putString("id", detElement.getTexture().toString());
							detailNBT.putString("color", detElement.getColor());			
							details.add(detailNBT);
						}
					}
					props.put("details", details);
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}

				return props;
			}

			@Override
			public void readNBT(Capability<IJollyRoger> capability, IJollyRoger instance, Direction side, INBT nbt)
			{
				CompoundNBT props = (CompoundNBT) nbt;

				try
				{
					CompoundNBT baseNBT = props.getCompound("base");
					JollyRogerElement baseElement = GameRegistry.findRegistry(JollyRogerElement.class).getValue(new ResourceLocation(APIConfig.PROJECT_ID, baseNBT.getString("id")));
					instance.setBase(baseElement);
					
					ListNBT backgroundsNBT = props.getList("backgrounds", Constants.NBT.TAG_COMPOUND);
					for (int i = 0; i < backgroundsNBT.size(); i++)
					{
						CompoundNBT backgroundNBT = backgroundsNBT.getCompound(i);
						JollyRogerElement bgElement = GameRegistry.findRegistry(JollyRogerElement.class).getValue(new ResourceLocation(APIConfig.PROJECT_ID, backgroundNBT.getString("id")));
						
						int slot = backgroundNBT.getInt("slot");
						
						String color = backgroundNBT.getString("color");
						bgElement.setColor(color);
						
						instance.setBackground(slot, bgElement);
					}
					
					ListNBT detailsNBT = props.getList("details", Constants.NBT.TAG_COMPOUND);
					for (int i = 0; i < detailsNBT.size(); i++)
					{
						CompoundNBT detailNBT = detailsNBT.getCompound(i);
						JollyRogerElement detElement = GameRegistry.findRegistry(JollyRogerElement.class).getValue(new ResourceLocation(APIConfig.PROJECT_ID, detailNBT.getString("id")));
						
						int slot = detailNBT.getInt("slot");
						
						String color = detailNBT.getString("color");
						detElement.setColor(color);
						
						instance.setDetail(slot, detElement);
					}
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}

		}, () -> new JollyRogerBase());
	}
	
	public static IJollyRoger get(final LivingEntity entity)
	{
		return entity.getCapability(INSTANCE, null).orElse(new JollyRogerBase());
	}
}
