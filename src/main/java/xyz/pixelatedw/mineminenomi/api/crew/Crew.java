package xyz.pixelatedw.mineminenomi.api.crew;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

public class Crew
{
	private String name;
	private boolean isTemporary;
	private List<Member> members = new ArrayList<Member>();
	private JollyRoger jollyRoger = new JollyRoger();
	
	public Crew()
	{
	}

	public Crew(String name, LivingEntity entity)
	{
		this(name, entity.getUniqueID(), entity.getDisplayName().getFormattedText());
	}
	
	public Crew(String name, UUID capId, String username)
	{
		this.name = name;
		this.isTemporary = true;
		this.addMember(capId, username).setIsCaptain();
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return this.name;
	}

	public boolean isTemporary()
	{
		return this.isTemporary;
	}

	public Member addMember(LivingEntity entity)
	{
		return this.addMember(entity.getUniqueID(), entity.getDisplayName().getFormattedText());
	}

	public Member addMember(UUID uuid, String username)
	{
		Member member = new Member(uuid, username);
		this.members.add(member);
		return member;	
	}
	
	public void removeMember(UUID id)
	{
		Member member = this.getMember(id);
		if(member.isCaptain())
			this.members.removeAll(this.members);
		else
			this.members.remove(member);
	}
	
	public Member getMember(UUID id)
	{
		return this.members.stream().filter(member -> member.getUUID().equals(id)).findFirst().orElse(null);
	}
	
	public boolean hasMember(UUID id)
	{
		return this.getMember(id) != null;
	}

	public void create(World world)
	{
		this.isTemporary = false;
	}

	@Nullable
	public Member getCaptain()
	{
		return this.members.stream().filter(member -> member.isCaptain()).findFirst().orElse(null);
	}

	public List<Member> getMembers()
	{
		return this.members;
	}

	public JollyRoger getJollyRoger()
	{
		return this.jollyRoger;
	}
	
	public void setJollyRoger(JollyRoger jr)
	{
		this.jollyRoger = jr;
	}
	
	public CompoundNBT write()
	{
		CompoundNBT crewNBT = new CompoundNBT();
		crewNBT.putString("name", this.getName());

		ListNBT members = new ListNBT();
		for (Crew.Member member : this.getMembers())
		{
			CompoundNBT memberNBT = new CompoundNBT();
			memberNBT.putUniqueId("id", member.getUUID());
			memberNBT.putString("username", member.getUsername());
			memberNBT.putBoolean("isCaptain", member.isCaptain());
			members.add(memberNBT);
		}
		crewNBT.put("members", members);

		CompoundNBT jollyRogerData = this.jollyRoger.write();
		crewNBT.put("jollyRoger", jollyRogerData);
		
		return crewNBT;
	}

	public void read(CompoundNBT nbt)
	{
		String name = nbt.getString("name");
		this.setName(name);

		ListNBT members = nbt.getList("members", Constants.NBT.TAG_COMPOUND);
		for (int j = 0; j < members.size(); j++)
		{
			CompoundNBT memberNBT = members.getCompound(j);
			Crew.Member member = this.addMember(memberNBT.getUniqueId("id"), memberNBT.getString("username"));
			if (memberNBT.getBoolean("isCaptain"))
				member.setIsCaptain();
		}
		
		CompoundNBT jollyRogerData = nbt.getCompound("jollyRoger");
		this.jollyRoger.read(jollyRogerData);
	}

	public static class Member
	{
		private UUID uuid;
		private String username;
		private boolean isCaptain;

		public Member(LivingEntity entity)
		{
			this(entity.getUniqueID(), entity.getDisplayName().getFormattedText());
		}
		
		public Member(UUID uuid, String username)
		{
			this.uuid = uuid;
			this.username = username;
		}

		public Member setIsCaptain()
		{
			this.isCaptain = true;
			return this;
		}

		public boolean isCaptain()
		{
			return this.isCaptain;
		}

		public UUID getUUID()
		{
			return this.uuid;
		}
		
		public String getUsername()
		{
			return this.username;
		}
	}
}
