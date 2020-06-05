package xyz.pixelatedw.mineminenomi.api.crew;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import net.minecraft.entity.player.PlayerEntity;

public class Crew
{
	private String name;
	private List<Member> members = new ArrayList<Member>();
	
	public Crew(String name, PlayerEntity captain)
	{
		this.name = name;
		this.addMember(captain).setIsCaptain();
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public Member addMember(PlayerEntity player)
	{
		Member member = new Member(player.getUniqueID());	
		this.members.add(member);
		return member;
	}
	
	@Nullable
	public Member getCaptain()
	{
		return this.members.stream().filter(member -> member.isCaptain()).findFirst().orElse(null);
	}
	
	public class Member
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
