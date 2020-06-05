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
				if(baseElement != null && baseElement.getTexture() != null)
				{
					CompoundNBT baseNBT = new CompoundNBT();
					baseNBT.putString("id", baseElement.getTexture().toString());
					baseNBT.putBoolean("canBeColored", baseElement.canBeColored());
					baseNBT.putString("color", baseElement.getColor());
					props.put("base", baseNBT);
				}
				else
				{
					CompoundNBT baseNBT = new CompoundNBT();
					baseNBT.putString("id", "empty");
					props.put("base", baseNBT);
				}
					
				try
				{
					ListNBT backgrounds = new ListNBT();
					for (int i = 0; i < instance.getBackgrounds().length; i++)
					{
						JollyRogerElement bgElement = instance.getBackgrounds()[i];				
						if(bgElement != null && bgElement.getTexture() != null)
						{
							CompoundNBT backgroundNBT = new CompoundNBT();
							backgroundNBT.putInt("slot", i);
							backgroundNBT.putString("id", bgElement.getTexture().toString());
							backgroundNBT.putBoolean("canBeColored", bgElement.canBeColored());
							backgroundNBT.putString("color", bgElement.getColor());
							backgrounds.add(backgroundNBT);
						}
						else
						{
							CompoundNBT backgroundNBT = new CompoundNBT();
							backgroundNBT.putInt("slot", i);
							backgroundNBT.putString("id", "empty");
							backgrounds.add(backgroundNBT);
						}
					}
					props.put("backgrounds", backgrounds);
					
					ListNBT details = new ListNBT();
					for (int i = 0; i < instance.getDetails().length; i++)
					{
						JollyRogerElement detElement = instance.getDetails()[i];				
						if(detElement != null && detElement.getTexture() != null)
						{
							CompoundNBT detailNBT = new CompoundNBT();
							detailNBT.putInt("slot", i);
							detailNBT.putString("id", detElement.getTexture().toString());
							detailNBT.putBoolean("canBeColored", detElement.canBeColored());
							detailNBT.putString("color", detElement.getColor());			
							details.add(detailNBT);
						}
						else
						{
							CompoundNBT detailNBT = new CompoundNBT();
							detailNBT.putInt("slot", i);
							detailNBT.putString("id", "empty");
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
					JollyRogerElement baseElement = GameRegistry.findRegistry(JollyRogerElement.class).getValue(new ResourceLocation(APIConfig.PROJECT_ID, baseNBT.getString("id").replace(APIConfig.PROJECT_ID + ":", "")));
					
					if(baseElement != null)
					{
						if(baseNBT.getBoolean("canBeColored"))
							baseElement.setCanBeColored();
						
						String color = baseNBT.getString("color");
						baseElement.setColor(color);
						
						instance.setBase(baseElement);
					}
					else
					{
						instance.setBase(null);
					}
					
					ListNBT backgroundsNBT = props.getList("backgrounds", Constants.NBT.TAG_COMPOUND);
					for (int i = 0; i < backgroundsNBT.size(); i++)
					{
						CompoundNBT backgroundNBT = backgroundsNBT.getCompound(i);
						ResourceLocation res = new ResourceLocation(APIConfig.PROJECT_ID, backgroundNBT.getString("id").replace(APIConfig.PROJECT_ID + ":", ""));
						JollyRogerElement bgElement = GameRegistry.findRegistry(JollyRogerElement.class).getValue(res);
						
						int slot = backgroundNBT.getInt("slot");
						if(bgElement != null)
						{			
							if(backgroundNBT.getBoolean("canBeColored"))
								bgElement.setCanBeColored();
							
							String color = backgroundNBT.getString("color");
							bgElement.setColor(color);
							
							instance.setBackground(slot, bgElement);
						}
						else
						{
							instance.setBackground(slot, null);
						}
					}
					
					ListNBT detailsNBT = props.getList("details", Constants.NBT.TAG_COMPOUND);
					for (int i = 0; i < detailsNBT.size(); i++)
					{
						CompoundNBT detailNBT = detailsNBT.getCompound(i);
						JollyRogerElement detElement = GameRegistry.findRegistry(JollyRogerElement.class).getValue(new ResourceLocation(APIConfig.PROJECT_ID, detailNBT.getString("id").replace(APIConfig.PROJECT_ID + ":", "")));
						
						int slot = detailNBT.getInt("slot");
						if(detElement != null)
						{			
							if(detailNBT.getBoolean("canBeColored"))
								detElement.setCanBeColored();
							
							String color = detailNBT.getString("color");
							detElement.setColor(color);
							
							instance.setDetail(slot, detElement);
						}
						else
						{
							instance.setDetail(slot, null);
						}
					}
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}

		}, JollyRogerBase::new);
	}
	
	public static IJollyRoger get(final LivingEntity entity)
	{
		return entity.getCapability(INSTANCE, null).orElse(null);
	}
}
