package xyz.pixelatedw.mineminenomi.api.crew;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

public class Crew
{
	private String name;
	private boolean isTemporary;
	private List<Member> members = new ArrayList<Member>();

	public Crew()
	{
	}

	public Crew(String name, UUID capId)
	{
		this.name = name;
		this.isTemporary = true;
		this.addMember(capId).setIsCaptain();
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

	public Member addMember(UUID id)
	{
		Member member = new Member(id);
		this.members.add(member);
		return member;
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

	public CompoundNBT write()
	{
		CompoundNBT crewNBT = new CompoundNBT();
		crewNBT.putString("name", this.getName());

		ListNBT members = new ListNBT();
		for (Crew.Member member : this.getMembers())
		{
			CompoundNBT memberNBT = new CompoundNBT();
			memberNBT.putUniqueId("id", member.getUUID());
			memberNBT.putBoolean("isCaptain", member.isCaptain());
			members.add(memberNBT);
		}
		crewNBT.put("members", members);

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
			Crew.Member member = this.addMember(memberNBT.getUniqueId("id"));
			if(memberNBT.getBoolean("isCaptain"))
				member.setIsCaptain();		
		}
	}

	public static class Member
	{
		private UUID uuid;
		private boolean isCaptain;

		public Member(UUID uuid)
		{
			this.uuid = uuid;
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
	}
}
