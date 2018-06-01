package xyz.pixelatedw.MineMineNoMi3.api;

public enum EnumCustomParticleTypes 
{
	
	GASROBE("gasrobe");
	
	
	private String fxName;
	
	private EnumCustomParticleTypes(String fxName)
	{
		this.fxName = fxName;
	}
	
	public String getParticleName()
	{
		return fxName;
	}
}
