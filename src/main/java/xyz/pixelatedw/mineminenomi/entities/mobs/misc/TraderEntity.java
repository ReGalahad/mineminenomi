package xyz.pixelatedw.mineminenomi.entities.mobs.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.Hand;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameterSets;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.pixelatedw.mineminenomi.entities.mobs.GenericNewEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.ai.TradeGoal;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.screens.TraderScreen;
import xyz.pixelatedw.wypi.WyHelper;

public class TraderEntity extends GenericNewEntity {

	private static final DataParameter<CompoundNBT> ITEMS = EntityDataManager.createKey(TraderEntity.class,
			DataSerializers.COMPOUND_NBT);
	public List<ItemStack> STACKS = new ArrayList<ItemStack>();
	public static List<Item> DISPOSABLES = new ArrayList<Item>(Arrays.asList(ModItems.BULLET, ModItems.KAIROSEKI_BULLET,
			ModItems.BLACK_METAL, ModItems.COLA, ModItems.DENSE_KAIROSEKI, ModItems.KAIROSEKI, ModItems.KUJA_ARROW,
			ModItems.ULTRA_COLA, ModItems.BUBBLY_CORAL));
    public static String[] FACTIONS = new String[] {"pirate","marine", "bountyhunter"};
	public TraderEntity(World worldIn) {
		super(ModEntities.TRADER, worldIn, null);

		this.textures = new String[] { "weapontrader", "dialtrader", "clothingtrader", "resourcetrader" };

	}

	@Override
	protected void registerGoals()
	{
		super.registerGoals();
		this.goalSelector.addGoal(1, new SwimGoal(this));
		this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
		this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(5, new LookRandomlyGoal(this));	
		this.goalSelector.addGoal(0, new TradeGoal(this));
		
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
	protected void registerData() {
		super.registerData();
		this.dataManager.register(ITEMS, new CompoundNBT());
	}

	@Override
	public void writeAdditional(CompoundNBT nbt) {
		super.writeAdditional(nbt);
		this.updateNbtFromStacks(false);
		nbt.put("itemstacks", this.getNBT());

	}

	@Override
	public void readAdditional(CompoundNBT nbt) {
		super.readAdditional(nbt);

		this.setNBT(nbt.getCompound("itemstacks"));
	}

	public CompoundNBT clear(CompoundNBT nbt) {

		Iterator<String> iterator = nbt.keySet().iterator();
		while (iterator.hasNext()) {
			String str = iterator.next();
			if (!str.contains("price") && !str.contains("isopened") && !str.contains("faction")) {
				iterator.remove();
			}
		}
		return nbt;
	}

	public CompoundNBT getNBT() {
		CompoundNBT nbt =  this.getDataManager().get(ITEMS);

		return nbt;
	}
	
	public void setIsOpened(CompoundNBT nbt, boolean bool) {
		nbt.remove("isopened");
		nbt.putBoolean("isopened", bool);
	}

	public boolean getIsOpened() {
		CompoundNBT nbt = this.getNBT();
		return nbt.getBoolean("isopened");
	}
	public void setFaction(CompoundNBT nbt, String fac) {
		nbt.remove("faction");
		nbt.putString("faction", fac);
	}
	
	public String getFaction() {
		CompoundNBT nbt = this.getNBT();
	return	nbt.getString("faction");
	}
	public void setNBT(CompoundNBT val) {
		this.getDataManager().set(ITEMS, val);
		this.setStacksFromNBT(this.getNBT());
	}

	public void setPrice(ItemStack stack, int price) {
		this.getNBT().remove(stack.getItem().getRegistryName().getPath() + "price");
		this.getNBT().putInt(stack.getItem().getRegistryName().getPath() + "price", price);
	}

	public int getPrice(ItemStack stack) {
		return this.getNBT().getInt(stack.getItem().getRegistryName().getPath() + "price");
	}

	public void setStacksFromNBT(CompoundNBT nbt) {

		this.STACKS.clear();
		for (Item item : ForgeRegistries.ITEMS.getValues()) {
			if (nbt.contains(item.getRegistryName().getPath()) && nbt.getInt(item.getRegistryName().getPath()) > 0) {
				ItemStack stack = new ItemStack(item);
				stack.setCount(nbt.getInt(item.getRegistryName().getPath()));
				this.STACKS.add(stack);
			}
		}
	}

	public void changeStackCount(ItemStack stack, int newCount) {
		for (ItemStack tempStack : this.STACKS) {
			if (tempStack.getItem() == stack.getItem()) {
				tempStack.setCount(newCount);
				break;
			}
		}
	}

	public void generate() {
		if (!this.world.isRemote) {
			LootTable loottable = this.world.getServer().getLootTableManager()
					.getLootTableFromLocation(ModResources.TRADER_WEAPON);
			LootContext.Builder lootcontext$builder = (new LootContext.Builder((ServerWorld) this.world));

			LootContext context = lootcontext$builder.build(LootParameterSets.EMPTY);
			List<ItemStack> stacks = loottable.generate(context);

			if (this.checkForDuplicate(stacks)) {
				this.generate();

			} else {
				this.STACKS = stacks;
				this.updateNbtFromStacks(true);
			}

		}
	}

	public void updateNbtFromStacks(boolean initialUpdate) {
		CompoundNBT nbt = this.getNBT();
		this.clear(nbt);
		for (ItemStack stack : this.STACKS) {

			nbt.putInt(stack.getItem().getRegistryName().getPath(), stack.getCount());

			if (initialUpdate) {
				this.setPrice(stack, stack.getTag().getInt("price"));
			}

		}
	}

	public boolean checkForDuplicate(List<ItemStack> stacks) {
		for (ItemStack stack : stacks) {
			List<ItemStack> stacks2 = new ArrayList<ItemStack>(stacks);
			stacks2.remove(stack);
			for (ItemStack stack2 : stacks2) {
				if (stack.getItem() == stack2.getItem()) {
					return true;
				}
			}
		}
		return false;
	}

	// Gui Helper Method
	public ItemStack getStackFromPath(String path) {
		for (ItemStack stack : this.STACKS) {
			if (stack.getItem().getRegistryName().getPath() == path) {
				return stack;
			}
		}
		return ItemStack.EMPTY;
	}

	@Override
	@Nullable
	public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason,
			@Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
		spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);

		if (this.STACKS.isEmpty()) {
			this.generate();
		}
		int rand = (int) WyHelper.randomWithRange(1, 10);
		if(rand < 4) {
			this.setFaction(this.getNBT(), FACTIONS[rand - 1]);
		}

		return spawnData;
	}

	@Override
	protected boolean processInteract(PlayerEntity player, Hand hand) {
		if (world.isRemote()) {
			if (!this.getIsOpened()) {
				if (Minecraft.getInstance().currentScreen == null) {
					Minecraft.getInstance().displayGuiScreen(new TraderScreen(this, player));
					this.setIsOpened(this.getNBT(), true);
					return true;
				}
			}
		}
		return false;
	}

	public void removeStackFromList(ItemStack stack) {
		for (int i = 0; i < this.STACKS.size(); i++) {
			if (this.STACKS.get(i).getItem() == stack.getItem()) {
				this.STACKS.remove(i);
			}
		}
	}


}
