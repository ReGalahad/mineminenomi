package xyz.pixelatedw.mineminenomi.api.entities;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameterSets;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraftforge.common.util.Constants;
import xyz.pixelatedw.mineminenomi.api.TradeEntry;
import xyz.pixelatedw.mineminenomi.entities.mobs.GenericNewEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.ai.TradeGoal;
import xyz.pixelatedw.mineminenomi.packets.server.SOpenTraderScreenPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SUpdateTraderOffersPacket;
import xyz.pixelatedw.wypi.network.WyNetwork;

public abstract class TraderEntity extends GenericNewEntity {
	private List<TradeEntry> tradeEntries = new ArrayList<TradeEntry>();

	public TraderEntity(EntityType type, World world) {
		super(type, world, null);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(1, new SwimGoal(this));
		this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
		this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
		this.goalSelector.addGoal(0, new TradeGoal(this));
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(25.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20F);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
		this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
	}

	@Override
	protected void registerData() {
		super.registerData();
	}

	public abstract boolean canTrade(PlayerEntity player);

	public abstract String getTradeFailMessage();

	public abstract ResourceLocation getTradeTable();

	@Override
	public void writeAdditional(CompoundNBT nbt) {
		super.writeAdditional(nbt);

		ListNBT items = new ListNBT();
		for (TradeEntry stack : this.tradeEntries) {
			CompoundNBT nbtTrade = new CompoundNBT();
			nbtTrade = stack.getItemStack().write(nbtTrade);
			items.add(nbtTrade);
		}
		nbt.put("Items", items);
	}

	@Override
	public void readAdditional(CompoundNBT nbt) {
		super.readAdditional(nbt);

		ListNBT tradeList = nbt.getList("Items", Constants.NBT.TAG_COMPOUND);
		for (int i = 0; i < tradeList.size(); i++) {
			CompoundNBT nbtTrade = tradeList.getCompound(i);
			ItemStack stack = ItemStack.read(nbtTrade);
			this.tradeEntries.add(new TradeEntry(stack));
		}
	}

	public void setStacksFromNBT(CompoundNBT nbt) {

		ListNBT tradeList = nbt.getList("Items", Constants.NBT.TAG_COMPOUND);
		for (int i = 0; i < tradeList.size(); i++) {
			CompoundNBT nbtTrade = tradeList.getCompound(i);
			ItemStack stack = ItemStack.read(nbtTrade);
			this.tradeEntries.add(new TradeEntry(stack));
		}
	}

	@Override
	@Nullable
	public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason,
			@Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
		spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);

		// Generates the trade entries from a loot table json file
		LootTable lootTable = this.world.getServer().getLootTableManager()
				.getLootTableFromLocation(this.getTradeTable());
		LootContext.Builder builder = (new LootContext.Builder((ServerWorld) this.world));

		LootContext context = builder.build(LootParameterSets.EMPTY);
		List<ItemStack> stacks = lootTable.generate(context);

		for (ItemStack stack : stacks) {
			this.tradeEntries.add(new TradeEntry(stack));
		}

		return spawnData;
	}

	public List<TradeEntry> getTradingItems() {
		return this.tradeEntries;
	}

	public void setTradingItems(List<TradeEntry> entries) {
		this.tradeEntries = entries;
	}

	@Override
	protected boolean processInteract(PlayerEntity player, Hand hand) {
		if (!player.world.isRemote) {
			WyNetwork.sendTo(new SOpenTraderScreenPacket(this.getEntityId()), player);
			WyNetwork.sendTo(new SUpdateTraderOffersPacket(this.getEntityId(), this.tradeEntries), player);
			return true;
		}
		return false;
	}
}
