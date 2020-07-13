package xyz.pixelatedw.mineminenomi.api.crew;

import java.util.Arrays;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xyz.pixelatedw.mineminenomi.init.ModJollyRogers;
import xyz.pixelatedw.wypi.APIConfig;

public class JollyRoger
{
	private JollyRogerElement base = ModJollyRogers.BASE_0;
	private JollyRogerElement[] backgrounds = new JollyRogerElement[2];
	private JollyRogerElement[] details = new JollyRogerElement[5];

	public CompoundNBT write()
	{
		CompoundNBT nbt = new CompoundNBT();
		
		JollyRogerElement baseElement = this.getBase();
		if(baseElement != null && baseElement.getTexture() != null)
		{
			CompoundNBT baseNBT = new CompoundNBT();
			baseNBT.putString("id", baseElement.getTexture().toString());
			baseNBT.putBoolean("canBeColored", baseElement.canBeColored());
			baseNBT.putString("color", baseElement.getColor());
			nbt.put("base", baseNBT);
		}
		else
		{
			CompoundNBT baseNBT = new CompoundNBT();
			baseNBT.putString("id", "empty");
			nbt.put("base", baseNBT);
		}
			
		try
		{
			ListNBT backgrounds = new ListNBT();
			for (int i = 0; i < this.getBackgrounds().length; i++)
			{
				JollyRogerElement bgElement = this.getBackgrounds()[i];				
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
			nbt.put("backgrounds", backgrounds);
			
			ListNBT details = new ListNBT();
			for (int i = 0; i < this.getDetails().length; i++)
			{
				JollyRogerElement detElement = this.getDetails()[i];				
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
			nbt.put("details", details);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return nbt;
	}
	
	public void read(CompoundNBT nbt)
	{
		try
		{
			CompoundNBT baseNBT = nbt.getCompound("base");
			JollyRogerElement baseElement = GameRegistry.findRegistry(JollyRogerElement.class).getValue(new ResourceLocation(APIConfig.PROJECT_ID, baseNBT.getString("id").replace(APIConfig.PROJECT_ID + ":", "")));
			
			if(baseElement != null)
			{
				if(baseNBT.getBoolean("canBeColored"))
					baseElement.setCanBeColored();
				
				String color = baseNBT.getString("color");
				baseElement.setColor(color);
				
				this.setBase(baseElement);
			}
			else
			{
				this.setBase(null);
			}
			
			ListNBT backgroundsNBT = nbt.getList("backgrounds", Constants.NBT.TAG_COMPOUND);
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
					
					this.setBackground(slot, bgElement);
				}
				else
				{
					this.setBackground(slot, null);
				}
			}
			
			ListNBT detailsNBT = nbt.getList("details", Constants.NBT.TAG_COMPOUND);
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
					
					this.setDetail(slot, detElement);
				}
				else
				{
					this.setDetail(slot, null);
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}		
	}
	
	public JollyRogerElement getBase()
	{
		return this.base;
	}

	public void setBase(JollyRogerElement base)
	{
		this.base = base;
	}

	public JollyRogerElement[] getBackgrounds()
	{
		return this.backgrounds;
	}

	public void setBackgrounds(JollyRogerElement[] elements)
	{
		this.backgrounds = elements;
	}

	public boolean addBackground(JollyRogerElement bg)
	{
		for (int i = 0; i < this.backgrounds.length; i++)
		{
			JollyRogerElement background = this.backgrounds[i];
			if (background == null)
			{
				this.backgrounds[i] = bg;
				return true;
			}
		}
		return false;
	}

	public boolean setBackground(int slot, JollyRogerElement bg)
	{
		if (!this.hasBackground(bg) && slot <= 2)
		{
			this.backgrounds[slot] = bg;
			return true;
		}
		return false;
	}

	public boolean hasBackground(JollyRogerElement bg)
	{
		return Arrays.stream(this.backgrounds).parallel().anyMatch(background -> background != null && background.equals(bg));
	}

	public JollyRogerElement[] getDetails()
	{
		return this.details;
	}

	public void setDetails(JollyRogerElement[] elements)
	{
		this.details = elements;
	}
	
	public boolean addDetail(JollyRogerElement det)
	{
		for (int i = 0; i < this.details.length; i++)
		{
			JollyRogerElement detail = this.details[i];
			if (detail == null)
			{
				this.details[i] = det;
				return true;
			}
		}
		return false;
	}

	public boolean setDetail(int slot, JollyRogerElement det)
	{
		if (!this.hasDetail(det) && slot <= 5)
		{
			this.details[slot] = det;
			return true;
		}
		return false;
	}

	public boolean hasDetail(JollyRogerElement det)
	{
		return Arrays.stream(this.details).parallel().anyMatch(detail -> detail != null && detail.equals(det));
	}
}
