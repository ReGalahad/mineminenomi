package xyz.pixelatedw.mineminenomi.entities.mobs.misc;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameterSets;
import net.minecraft.world.storage.loot.LootTable;
import xyz.pixelatedw.mineminenomi.api.TradeEntry;
import xyz.pixelatedw.mineminenomi.containers.TraderContainer;
import xyz.pixelatedw.mineminenomi.entities.mobs.GenericNewEntity;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncTraderOffersPacket;
import xyz.pixelatedw.wypi.network.WyNetwork;

public class TraderEntity extends GenericNewEntity
{
	private static final DataParameter<CompoundNBT> ITEMS = EntityDataManager.createKey(TraderEntity.class, DataSerializers.COMPOUND_NBT);
	private List<TradeEntry> tradeEntries = new ArrayList<TradeEntry>();

	public TraderEntity(World world)
	{
		super(ModEntities.TRADER, world, null);

		this.textures = new String[] { "trader1", "trader1", "trader1" };
	}

	@Override
	protected void registerGoals()
	{
		super.registerGoals();
		this.goalSelector.addGoal(1, new SwimGoal(this));
		this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
		this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
	}

	@Override
	protected void registerAttributes()
	{
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(25.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20F);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
		this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
	}

	@Override
	protected void registerData()
	{
		super.registerData();
		this.dataManager.register(ITEMS, new CompoundNBT());
	}

	@Override
	public void writeAdditional(CompoundNBT nbt)
	{
		super.writeAdditional(nbt);

		nbt.put("Items", this.getItems());
	}

	@Override
	public void readAdditional(CompoundNBT nbt)
	{
		super.readAdditional(nbt);

		this.setItems((CompoundNBT) nbt.get("Items"));
	}

	public CompoundNBT getItems()
	{
		return this.getDataManager().get(ITEMS);
	}

	protected void setItems(CompoundNBT nbt)
	{
		this.getDataManager().set(ITEMS, nbt);
	}

	public void generate()
	{
		if (!this.world.isRemote)
		{
			LootTable lootTable = this.world.getServer().getLootTableManager().getLootTableFromLocation(ModResources.TRADER_WEAPONS);
			LootContext.Builder builder = (new LootContext.Builder((ServerWorld) this.world));

			LootContext context = builder.build(LootParameterSets.EMPTY);
			List<ItemStack> stacks = lootTable.generate(context);

			for (ItemStack stack : stacks)
			{
				this.tradeEntries.add(new TradeEntry(stack));
			}
		}
	}

	@Override
	@Nullable
	public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag)
	{
		spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);

		// Generates the trade entries from a loot table json file
		LootTable lootTable = this.world.getServer().getLootTableManager().getLootTableFromLocation(ModResources.TRADER_WEAPONS);
		LootContext.Builder builder = (new LootContext.Builder((ServerWorld) this.world));

		LootContext context = builder.build(LootParameterSets.EMPTY);
		List<ItemStack> stacks = lootTable.generate(context);

		for (ItemStack stack : stacks)
		{
			this.tradeEntries.add(new TradeEntry(stack));
		}

		return spawnData;
	}

	@Override
	protected boolean processInteract(PlayerEntity player, Hand hand)
	{
		if (!player.world.isRemote)
		{
			player.openContainer(new SimpleNamedContainerProvider((i, inventory, playerUser) ->
			{
				return new TraderContainer(i, inventory, playerUser, this, this.tradeEntries);
			}, new StringTextComponent("")));
			WyNetwork.sendTo(new SSyncTraderOffersPacket(this.getEntityId(), this.tradeEntries), player);
			return true;
		}
		return false;
	}
}
