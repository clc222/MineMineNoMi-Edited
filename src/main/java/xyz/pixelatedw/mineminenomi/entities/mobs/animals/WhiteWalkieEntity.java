/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.animals;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.AgeableEntity;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.ILivingEntityData;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.SpawnReason;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.goal.BreedGoal;
/*     */ import net.minecraft.entity.ai.goal.FollowParentGoal;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.ai.goal.HurtByTargetGoal;
/*     */ import net.minecraft.entity.ai.goal.LookAtGoal;
/*     */ import net.minecraft.entity.ai.goal.LookRandomlyGoal;
/*     */ import net.minecraft.entity.ai.goal.OwnerHurtByTargetGoal;
/*     */ import net.minecraft.entity.ai.goal.OwnerHurtTargetGoal;
/*     */ import net.minecraft.entity.ai.goal.RandomWalkingGoal;
/*     */ import net.minecraft.entity.ai.goal.SwimGoal;
/*     */ import net.minecraft.entity.passive.AnimalEntity;
/*     */ import net.minecraft.entity.passive.TameableEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.PlayerInventory;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.IInventoryChangedListener;
/*     */ import net.minecraft.inventory.Inventory;
/*     */ import net.minecraft.inventory.container.Container;
/*     */ import net.minecraft.inventory.container.INamedContainerProvider;
/*     */ import net.minecraft.inventory.container.SimpleNamedContainerProvider;
/*     */ import net.minecraft.item.Food;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.nbt.ListNBT;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.util.ActionResultType;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.SoundEvents;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.IServerWorld;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.network.NetworkHooks;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.GoalMemories;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.IGoalMemoriesEntity;
/*     */ import xyz.pixelatedw.mineminenomi.containers.WhiteWalkieStorageContainer;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class WhiteWalkieEntity extends TameableEntity implements IInventoryChangedListener, IGoalMemoriesEntity {
/*  76 */   private static final TranslationTextComponent CONTAINER_TITLE = new TranslationTextComponent(ModI18n.CONTAINER_WHITE_WALKIE_STORAGE);
/*  77 */   private static final DataParameter<Byte> FLAGS = EntityDataManager.func_187226_a(WhiteWalkieEntity.class, DataSerializers.field_187191_a);
/*  78 */   private static final DataParameter<Integer> CHESTS = EntityDataManager.func_187226_a(WhiteWalkieEntity.class, DataSerializers.field_187192_b);
/*     */   private static final double TAME_CHANCE = 0.35D;
/*  80 */   private static final Item[] SADDLES = new Item[] { Items.field_151141_av };
/*     */   
/*     */   private static final byte BITE_EVENT = 100;
/*     */   private static final byte SHAKE_EVENT = 101;
/*  84 */   private final GoalMemories goalMemories = new GoalMemories();
/*     */   
/*     */   private Inventory inventory;
/*     */   private int currentInvPage;
/*     */   private long lastMountTime;
/*     */   private long lastBiteTime;
/*     */   private long lastShakeTime;
/*     */   private int clientBiteTime;
/*     */   private int clientShakeTime;
/*     */   
/*     */   public WhiteWalkieEntity(EntityType type, World world) {
/*  95 */     super(type, world);
/*  96 */     createInventory();
/*     */     
/*  98 */     if (world != null && !world.field_72995_K) {
/*  99 */       func_110148_a((Attribute)ModAttributes.STEP_HEIGHT.get()).func_111128_a(1.0D);
/*     */     }
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/* 104 */     return OPEntity.createAttributes()
/* 105 */       .func_233815_a_(Attributes.field_233819_b_, 40.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_184651_r() {
/* 110 */     this.field_70714_bg.func_75776_a(0, (Goal)new SwimGoal((MobEntity)this));
/* 111 */     this.field_70714_bg.func_75776_a(0, (Goal)new WhiteWalkieSleepGoal(this));
/*     */     
/* 113 */     this.field_70714_bg.func_75776_a(2, (Goal)new BreedGoal((AnimalEntity)this, 1.0D));
/* 114 */     this.field_70714_bg.func_75776_a(3, (Goal)new SitGoal(this));
/* 115 */     this.field_70714_bg.func_75776_a(3, (Goal)new FollowParentGoal((AnimalEntity)this, 1.15D));
/* 116 */     this.field_70714_bg.func_75776_a(4, (Goal)new MeleeAttackGoal((CreatureEntity)this, 1.0D, false));
/* 117 */     this.field_70714_bg.func_75776_a(5, (Goal)new RandomWalkingGoal((CreatureEntity)this, 0.8D));
/* 118 */     this.field_70714_bg.func_75776_a(6, (Goal)new LookAtGoal((MobEntity)this, PlayerEntity.class, 8.0F));
/* 119 */     this.field_70714_bg.func_75776_a(6, (Goal)new LookRandomlyGoal((MobEntity)this));
/*     */     
/* 121 */     this.field_70715_bh.func_75776_a(1, (Goal)new OwnerHurtByTargetGoal(this));
/* 122 */     this.field_70715_bh.func_75776_a(2, (Goal)new OwnerHurtTargetGoal(this));
/* 123 */     this.field_70715_bh.func_75776_a(3, (Goal)new HurtByTargetGoal((CreatureEntity)this, new Class[0]));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/* 128 */     super.func_70088_a();
/* 129 */     func_184212_Q().func_187214_a(FLAGS, Byte.valueOf((byte)0));
/* 130 */     func_184212_Q().func_187214_a(CHESTS, Integer.valueOf(0));
/*     */   }
/*     */   
/*     */   protected void createInventory() {
/* 134 */     Inventory inventory = this.inventory;
/* 135 */     this.inventory = new Inventory(getInventorySize());
/* 136 */     if (inventory != null) {
/* 137 */       inventory.func_110132_b(this);
/* 138 */       int i = Math.min(inventory.func_70302_i_(), this.inventory.func_70302_i_());
/*     */       
/* 140 */       for (int j = 0; j < i; j++) {
/* 141 */         ItemStack itemstack = inventory.func_70301_a(j);
/* 142 */         if (!itemstack.func_190926_b()) {
/* 143 */           this.inventory.func_70299_a(j, itemstack.func_77946_l());
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 148 */     this.inventory.func_110134_a(this);
/* 149 */     updateContainerEquipment();
/*     */   }
/*     */   
/*     */   protected void updateContainerEquipment() {
/* 153 */     if (!this.field_70170_p.field_72995_K) {
/* 154 */       setFlag(4, !this.inventory.func_70301_a(0).func_190926_b());
/*     */     }
/*     */   }
/*     */   
/*     */   public void openInventory(PlayerEntity player) {
/* 159 */     if (!this.field_70170_p.field_72995_K && (!func_184207_aI() || func_184196_w((Entity)player)) && func_70909_n()) {
/* 160 */       SimpleNamedContainerProvider simpleNamedContainerProvider = new SimpleNamedContainerProvider((id, inv, playerEntity) -> new WhiteWalkieStorageContainer(id, inv, (IInventory)this.inventory, this), (ITextComponent)CONTAINER_TITLE);
/* 161 */       NetworkHooks.openGui((ServerPlayerEntity)player, (INamedContainerProvider)simpleNamedContainerProvider, buf -> {
/*     */             CompoundNBT nbt = new CompoundNBT();
/*     */             nbt.func_74768_a("entity", func_145782_y());
/*     */             nbt.func_74768_a("inventorySize", this.inventory.func_70302_i_());
/*     */             nbt.func_74768_a("pageSize", getInventoryPageSize());
/*     */             buf.func_150786_a(nbt);
/*     */           });
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_213281_b(CompoundNBT nbt) {
/* 173 */     super.func_213281_b(nbt);
/* 174 */     nbt.func_74757_a("IsSleeping", func_70608_bn());
/* 175 */     nbt.func_74768_a("Chests", getChests());
/*     */     
/* 177 */     if (!this.inventory.func_70301_a(0).func_190926_b()) {
/* 178 */       nbt.func_218657_a("SaddleItem", (INBT)this.inventory.func_70301_a(0).func_77955_b(new CompoundNBT()));
/*     */     }
/*     */     
/* 181 */     if (hasChest()) {
/* 182 */       ListNBT listnbt = new ListNBT();
/*     */       
/* 184 */       for (int i = 1; i < this.inventory.func_70302_i_(); i++) {
/* 185 */         ItemStack itemstack = this.inventory.func_70301_a(i);
/* 186 */         if (!itemstack.func_190926_b()) {
/* 187 */           CompoundNBT compoundnbt = new CompoundNBT();
/* 188 */           compoundnbt.func_74774_a("Slot", (byte)i);
/* 189 */           itemstack.func_77955_b(compoundnbt);
/* 190 */           listnbt.add(compoundnbt);
/*     */         } 
/*     */       } 
/*     */       
/* 194 */       nbt.func_218657_a("Items", (INBT)listnbt);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70037_a(CompoundNBT nbt) {
/* 200 */     super.func_70037_a(nbt);
/* 201 */     setSleeping(nbt.func_74767_n("IsSleeping"));
/* 202 */     setChest(nbt.func_74762_e("Chests"));
/*     */     
/* 204 */     if (nbt.func_150297_b("SaddleItem", 10)) {
/* 205 */       ItemStack itemstack = ItemStack.func_199557_a(nbt.func_74775_l("SaddleItem"));
/* 206 */       if (itemstack != null && !itemstack.func_190926_b() && itemstack.func_77973_b() == Items.field_151141_av) {
/* 207 */         setSaddled(itemstack);
/*     */       }
/*     */     } 
/*     */     
/* 211 */     if (hasChest()) {
/* 212 */       ListNBT listnbt = nbt.func_150295_c("Items", 10);
/* 213 */       createInventory();
/*     */       
/* 215 */       for (int i = 0; i < listnbt.size(); i++) {
/* 216 */         CompoundNBT compoundnbt = listnbt.func_150305_b(i);
/* 217 */         int j = compoundnbt.func_74771_c("Slot") & 0xFF;
/* 218 */         if (j >= 1 && j < this.inventory.func_70302_i_()) {
/* 219 */           this.inventory.func_70299_a(j, ItemStack.func_199557_a(compoundnbt));
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 224 */     updateContainerEquipment();
/*     */   }
/*     */ 
/*     */   
/*     */   public ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
/* 229 */     if (!player.field_70170_p.field_72995_K && hand == Hand.MAIN_HAND) {
/* 230 */       ItemStack stack = player.func_184586_b((player.func_184600_cs() != null) ? player.func_184600_cs() : Hand.MAIN_HAND);
/* 231 */       boolean isSaddle = Arrays.<Item>stream(SADDLES).anyMatch(stack.func_77973_b()::equals);
/*     */       
/* 233 */       if (!func_70909_n()) {
/* 234 */         if (!stack.func_190926_b() && func_70877_b(stack)) {
/* 235 */           func_175505_a(player, stack);
/* 236 */           double chance = this.field_70146_Z.nextDouble();
/* 237 */           if (chance < 0.35D && !ForgeEventFactory.onAnimalTame((AnimalEntity)this, player)) {
/* 238 */             this.field_70170_p.func_72960_a((Entity)this, (byte)7);
/* 239 */             func_193101_c(player);
/*     */           } 
/* 241 */           return ActionResultType.SUCCESS;
/*     */         } 
/*     */       } else {
/*     */         
/* 245 */         if (player.func_226563_dT_()) {
/* 246 */           openInventory(player);
/* 247 */           return ActionResultType.func_233537_a_(this.field_70170_p.field_72995_K);
/*     */         } 
/* 249 */         if (func_70877_b(stack) && func_110143_aJ() < func_110138_aP()) {
/* 250 */           func_175505_a(player, stack);
/* 251 */           func_70691_i(4.0F);
/* 252 */           this.field_70170_p.func_72960_a((Entity)this, (byte)7);
/* 253 */           return ActionResultType.SUCCESS;
/*     */         } 
/* 255 */         if (isSaddle && !isSaddled() && !func_70631_g_()) {
/* 256 */           setSaddled(stack.func_77946_l());
/* 257 */           func_175505_a(player, stack);
/* 258 */           this.field_70170_p.func_217384_a((PlayerEntity)null, (Entity)this, SoundEvents.field_187726_cu, SoundCategory.PLAYERS, 0.5F, 1.0F);
/* 259 */           return ActionResultType.SUCCESS;
/*     */         } 
/* 261 */         if (stack.func_77973_b() == Blocks.field_150486_ae.func_199767_j() && !func_70631_g_() && getChests() < 2) {
/* 262 */           setChest(getChests() + 1);
/* 263 */           createInventory();
/* 264 */           func_175505_a(player, stack);
/* 265 */           return ActionResultType.SUCCESS;
/*     */         } 
/*     */         
/* 268 */         if ((!func_70877_b(stack) || (func_70877_b(stack) && !func_204701_dC())) && player == func_70902_q() && !func_70631_g_()) {
/* 269 */           player.func_184220_m((Entity)this);
/* 270 */           this.lastMountTime = this.field_70170_p.func_82737_E();
/* 271 */           return ActionResultType.SUCCESS;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 277 */     return super.func_230254_b_(player, hand);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_213352_e(Vector3d pTravelVector) {
/* 282 */     if (func_70089_S()) {
/* 283 */       if (func_184207_aI() && func_82171_bF() && isSaddled()) {
/* 284 */         LivingEntity livingentity = (LivingEntity)func_184179_bs();
/* 285 */         this.field_70177_z = livingentity.field_70177_z;
/* 286 */         this.field_70126_B = this.field_70177_z;
/* 287 */         this.field_70125_A = livingentity.field_70125_A * 0.5F;
/* 288 */         func_70101_b(this.field_70177_z, this.field_70125_A);
/* 289 */         this.field_70761_aq = this.field_70177_z;
/* 290 */         this.field_70759_as = this.field_70761_aq;
/* 291 */         float f = livingentity.field_70702_br * 0.3F;
/* 292 */         float f1 = livingentity.field_191988_bg;
/* 293 */         if (f1 <= 0.0F) {
/* 294 */           f1 *= 0.25F;
/*     */         }
/* 296 */         if (func_184186_bw()) {
/* 297 */           func_70659_e((float)func_233637_b_(Attributes.field_233821_d_) * 0.7F);
/* 298 */           super.func_213352_e(new Vector3d(f, pTravelVector.field_72448_b, f1));
/*     */         }
/* 300 */         else if (livingentity instanceof PlayerEntity) {
/* 301 */           AbilityHelper.setDeltaMovement((Entity)this, Vector3d.field_186680_a);
/*     */         } 
/*     */         
/* 304 */         func_233629_a_((LivingEntity)this, false);
/*     */       } else {
/*     */         
/* 307 */         super.func_213352_e(pTravelVector);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70636_d() {
/* 314 */     super.func_70636_d();
/* 315 */     if (this.clientShakeTime > 0) {
/* 316 */       this.clientShakeTime--;
/*     */     }
/* 318 */     if (this.clientBiteTime > 0) {
/* 319 */       this.clientBiteTime--;
/*     */     }
/* 321 */     if (!this.field_70170_p.field_72995_K) {
/* 322 */       if (func_70909_n() && func_184207_aI() && func_184188_bt().contains(func_70902_q()) && func_70902_q() != null && 
/* 323 */         (func_70902_q()).field_82175_bq && this.field_70170_p.func_82737_E() >= this.lastMountTime + 20L && this.field_70170_p.func_82737_E() >= this.lastBiteTime + 20L) {
/* 324 */         func_184609_a(Hand.MAIN_HAND);
/* 325 */         Vector3d look = func_213303_ch().func_178787_e(func_70902_q().func_70040_Z().func_216372_d(4.0D, 1.0D, 4.0D));
/* 326 */         Vector3d ogPos = new Vector3d(look.func_82615_a(), func_226278_cu_(), look.func_82616_c());
/* 327 */         List<LivingEntity> targets = WyHelper.getNearbyLiving(ogPos, (IWorld)this.field_70170_p, 1.0D, ModEntityPredicates.getEnemyFactions(func_70902_q()));
/* 328 */         targets.remove(this);
/* 329 */         targets.remove(func_70902_q());
/* 330 */         this.lastBiteTime = this.field_70170_p.func_82737_E();
/* 331 */         this.field_70170_p.func_72960_a((Entity)this, (byte)100);
/* 332 */         for (Entity e : targets) {
/* 333 */           e.func_70097_a(DamageSource.func_76358_a((LivingEntity)this), (float)func_233637_b_(Attributes.field_233823_f_));
/*     */         }
/*     */       } 
/* 336 */       if (this.lastShakeTime <= 0L) {
/* 337 */         this.lastShakeTime = this.field_70170_p.func_82737_E();
/*     */       }
/* 339 */       if (this.field_70170_p.func_82737_E() >= this.lastShakeTime + 1200L && !func_184207_aI()) {
/* 340 */         this.field_70170_p.func_72960_a((Entity)this, (byte)101);
/* 341 */         this.lastShakeTime = this.field_70170_p.func_82737_E();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 348 */     super.func_70071_h_();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70619_bc() {
/* 353 */     this.goalMemories.tick();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_226292_a_(Hand hand, boolean updateSelf) {
/* 358 */     if (func_70638_az() != null) {
/* 359 */       this.field_70170_p.func_72960_a((Entity)this, (byte)100);
/*     */     }
/* 361 */     super.func_226292_a_(hand, updateSelf);
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent func_184639_G() {
/* 366 */     return SoundEvents.field_190026_er;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent func_184601_bQ(DamageSource pDamageSource) {
/* 371 */     return SoundEvents.field_190029_eu;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEvent func_184615_bR() {
/* 376 */     return SoundEvents.field_190028_et;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_180429_a(BlockPos pos, BlockState block) {
/* 381 */     func_184185_a(SoundEvents.field_190030_ev, 0.15F, 1.5F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected float func_70599_aP() {
/* 386 */     return 0.4F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float func_70647_i() {
/* 391 */     return func_70631_g_() ? (1.5F + this.field_70146_Z.nextFloat() / 2.0F) : (1.25F + this.field_70146_Z.nextFloat() / 2.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ILivingEntityData func_213386_a(IServerWorld pLevel, DifficultyInstance pDifficulty, SpawnReason pReason, @Nullable ILivingEntityData pSpawnData, @Nullable CompoundNBT pDataTag) {
/*     */     AgeableEntity.AgeableData ageableData;
/* 398 */     if (pSpawnData == null) {
/* 399 */       ageableData = new AgeableEntity.AgeableData(0.2F);
/*     */     }
/*     */ 
/*     */     
/* 403 */     func_110148_a(Attributes.field_233818_a_).func_111128_a(generateRandomHealth());
/* 404 */     func_110148_a(Attributes.field_233826_i_).func_111128_a(generateRandomArmor());
/* 405 */     func_110148_a(Attributes.field_233823_f_).func_111128_a(generateRandomAttack());
/* 406 */     func_110148_a(Attributes.field_233821_d_).func_111128_a(generateRandomSpeed());
/*     */     
/* 408 */     func_70606_j((float)func_110148_a(Attributes.field_233818_a_).func_111126_e());
/*     */     
/* 410 */     return super.func_213386_a(pLevel, pDifficulty, pReason, (ILivingEntityData)ageableData, pDataTag);
/*     */   }
/*     */ 
/*     */   
/*     */   public AgeableEntity func_241840_a(ServerWorld world, AgeableEntity mate) {
/* 415 */     WhiteWalkieEntity offspring = (WhiteWalkieEntity)((EntityType)ModEntities.WHITE_WALKIE.get()).func_200721_a((World)world);
/* 416 */     setOffspringAttributes(mate, offspring);
/* 417 */     return (AgeableEntity)offspring;
/*     */   }
/*     */   
/*     */   protected void setOffspringAttributes(AgeableEntity mate, WhiteWalkieEntity offspring) {
/* 421 */     double hp = func_233638_c_(Attributes.field_233818_a_) + mate.func_233638_c_(Attributes.field_233818_a_) + generateRandomHealth();
/* 422 */     offspring.func_110148_a(Attributes.field_233818_a_).func_111128_a(hp / 3.0D);
/*     */     
/* 424 */     double armor = func_233638_c_(Attributes.field_233826_i_) + mate.func_233638_c_(Attributes.field_233826_i_) + generateRandomArmor();
/* 425 */     offspring.func_110148_a(Attributes.field_233826_i_).func_111128_a(armor / 3.0D);
/*     */     
/* 427 */     double speed = func_233638_c_(Attributes.field_233821_d_) + mate.func_233638_c_(Attributes.field_233821_d_) + generateRandomSpeed();
/* 428 */     offspring.func_110148_a(Attributes.field_233821_d_).func_111128_a(speed / 3.0D);
/*     */     
/* 430 */     double damage = func_233638_c_(Attributes.field_233823_f_) + mate.func_233638_c_(Attributes.field_233823_f_) + generateRandomAttack();
/* 431 */     offspring.func_110148_a(Attributes.field_233823_f_).func_111128_a(damage / 3.0D);
/*     */   }
/*     */   
/*     */   protected double generateRandomArmor() {
/* 435 */     return 1.0D + this.field_70146_Z.nextDouble() * 2.5D;
/*     */   }
/*     */   
/*     */   protected double generateRandomAttack() {
/* 439 */     return 5.0D + this.field_70146_Z.nextDouble() * 2.0D;
/*     */   }
/*     */   
/*     */   protected double generateRandomHealth() {
/* 443 */     return 50.0D + this.field_70146_Z.nextDouble() * 10.0D;
/*     */   }
/*     */   
/*     */   protected double generateRandomSpeed() {
/* 447 */     return 0.23000000417232513D + this.field_70146_Z.nextDouble() * 0.05D;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_70877_b(ItemStack stack) {
/* 452 */     if (stack.func_190926_b()) {
/* 453 */       return false;
/*     */     }
/* 455 */     Food food = stack.func_77973_b().func_219967_s();
/* 456 */     return (food != null && food.func_221467_c());
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean func_70610_aX() {
/* 461 */     return (super.func_70610_aX() && func_184207_aI() && isSaddled());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_82171_bF() {
/* 466 */     return func_184179_bs() instanceof LivingEntity;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_96092_aw() {
/* 471 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public void func_70103_a(byte id) {
/* 477 */     switch (id) {
/*     */       case 100:
/* 479 */         this.clientBiteTime = 20;
/*     */         break;
/*     */       case 101:
/* 482 */         this.clientShakeTime = 40;
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 487 */     super.func_70103_a(id);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Entity func_184179_bs() {
/* 493 */     List<Entity> list = func_184188_bt();
/* 494 */     return list.isEmpty() ? null : list.get(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public double func_70042_X() {
/* 499 */     return 1.7D;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_76316_a(IInventory inv) {
/* 504 */     ItemStack stack = this.inventory.func_70301_a(0);
/* 505 */     boolean flag = (stack != null && !stack.func_190926_b() && stack.func_77973_b() == Items.field_151141_av);
/* 506 */     setFlag(0, flag);
/* 507 */     updateContainerEquipment();
/* 508 */     if (this.field_70173_aa > 20 && !flag && isSaddled()) {
/* 509 */       func_184185_a(SoundEvents.field_187726_cu, 0.5F, 1.0F);
/*     */     }
/*     */   }
/*     */   
/*     */   protected int getInventorySize() {
/* 514 */     return 3 + (hasChest() ? (getInventoryPageSize() * getInventoryMaxPage()) : 0);
/*     */   }
/*     */   
/*     */   public int getInventoryColumns() {
/* 518 */     return 5;
/*     */   }
/*     */   
/*     */   public int getInventoryPageSize() {
/* 522 */     return 15;
/*     */   }
/*     */   
/*     */   public void setInventoryPage(int page) {
/* 526 */     this.currentInvPage = page;
/*     */   }
/*     */   
/*     */   public int getInventoryPage() {
/* 530 */     return this.currentInvPage;
/*     */   }
/*     */   
/*     */   public int getInventoryMaxPage() {
/* 534 */     return 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_174820_d(int pSlotIndex, ItemStack pStack) {
/* 553 */     return super.func_174820_d(pSlotIndex, pStack);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_213337_cE() {
/* 558 */     super.func_213337_cE();
/* 559 */     if (this.inventory != null) {
/* 560 */       for (int i = 0; i < this.inventory.func_70302_i_(); i++) {
/* 561 */         ItemStack itemstack = this.inventory.func_70301_a(i);
/* 562 */         if (!itemstack.func_190926_b() && !EnchantmentHelper.func_190939_c(itemstack)) {
/* 563 */           func_199701_a_(itemstack);
/*     */         }
/*     */       } 
/*     */     }
/* 567 */     if (hasChest()) {
/* 568 */       if (!this.field_70170_p.field_72995_K) {
/* 569 */         func_199703_a((IItemProvider)Blocks.field_150486_ae);
/*     */       }
/* 571 */       setChest(0);
/*     */     } 
/*     */   }
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public float getBiteAnimationProgress(float partialTicks) {
/* 577 */     return ((20 - this.clientBiteTime) + partialTicks) / 20.0F;
/*     */   }
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   public float getShakeAnimationTime() {
/* 582 */     return this.clientShakeTime;
/*     */   }
/*     */   
/*     */   public void setSaddled(@Nullable ItemStack stack) {
/* 586 */     boolean flag = true;
/* 587 */     if (stack == null) {
/* 588 */       stack = ItemStack.field_190927_a;
/* 589 */       flag = false;
/*     */     } 
/* 591 */     this.inventory.func_70299_a(0, stack);
/* 592 */     setFlag(0, flag);
/*     */   }
/*     */   
/*     */   public void setSleeping(boolean flag) {
/* 596 */     setFlag(1, flag);
/*     */   }
/*     */   
/*     */   public void setChest(int chests) {
/* 600 */     func_184212_Q().func_187227_b(CHESTS, Integer.valueOf(chests));
/*     */   }
/*     */   
/*     */   private void setFlag(int flag, boolean set) {
/* 604 */     byte b0 = ((Byte)this.field_70180_af.func_187225_a(FLAGS)).byteValue();
/* 605 */     if (set) {
/* 606 */       this.field_70180_af.func_187227_b(FLAGS, Byte.valueOf((byte)(b0 | 1 << flag)));
/*     */     } else {
/*     */       
/* 609 */       this.field_70180_af.func_187227_b(FLAGS, Byte.valueOf((byte)(b0 & (1 << flag ^ 0xFFFFFFFF))));
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean getFlag(int id) {
/* 614 */     return ((((Byte)this.field_70180_af.func_187225_a(FLAGS)).byteValue() & 1 << id) != 0);
/*     */   }
/*     */   
/*     */   public boolean isIdling() {
/* 618 */     return (!func_70608_bn() && !func_184207_aI());
/*     */   }
/*     */   
/*     */   public boolean isSaddled() {
/* 622 */     ItemStack stack = this.inventory.func_70301_a(0);
/* 623 */     return ((stack != null && !stack.func_190926_b() && stack.func_77973_b() == Items.field_151141_av) || getFlag(0));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_70608_bn() {
/* 628 */     return getFlag(1);
/*     */   }
/*     */   
/*     */   public boolean hasChest() {
/* 632 */     return (getChests() > 0);
/*     */   }
/*     */   
/*     */   public int getChests() {
/* 636 */     return ((Integer)func_184212_Q().func_187225_a(CHESTS)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public GoalMemories getGoalMemories() {
/* 641 */     return this.goalMemories;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\animals\WhiteWalkieEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */