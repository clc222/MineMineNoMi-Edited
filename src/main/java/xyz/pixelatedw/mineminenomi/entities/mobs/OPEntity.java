/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs;
/*     */ import com.google.common.base.Strings;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.Random;
/*     */ import java.util.function.Supplier;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.ILivingEntityData;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.SpawnReason;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifierMap;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.world.Difficulty;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.IBlockReader;
/*     */ import net.minecraft.world.IServerWorld;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.IExtensibleEnum;
/*     */ import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
/*     */ import net.minecraftforge.fml.network.NetworkHooks;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.GoalMemories;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ICommandReceiver;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.IGoalMemoriesEntity;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.IWithHome;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.NPCCommand;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public abstract class OPEntity extends CreatureEntity implements IRandomTexture, IEntityAdditionalSpawnData, IGoalMemoriesEntity, IWithHome {
/*  51 */   private static final DataParameter<Boolean> HAS_BUSOSHOKU_HAKI_ACTIVE = EntityDataManager.func_187226_a(OPEntity.class, DataSerializers.field_187198_h); private static final float SPAWNER_DESPAWN_DISTANCE = 40000.0F;
/*  52 */   private static final DataParameter<Boolean> HAS_FULLBODY_HAKI_ACTIVE = EntityDataManager.func_187226_a(OPEntity.class, DataSerializers.field_187198_h);
/*  53 */   private static final DataParameter<Integer> ANIMATION = EntityDataManager.func_187226_a(OPEntity.class, DataSerializers.field_187192_b);
/*     */   
/*  55 */   private final GoalMemories goalMemories = new GoalMemories();
/*     */   
/*  57 */   private ResourceLocation currentTexture = null;
/*     */   protected ResourceLocation[] textures;
/*     */   protected boolean needsEntityDataUpdate;
/*     */   private boolean isSpawnerChild = false;
/*  61 */   private Optional<Vector3d> homePosition = Optional.empty(); private boolean hasFear = true;
/*     */   @Deprecated
/*  63 */   private String crew = "";
/*     */ 
/*     */   
/*     */   public OPEntity(EntityType type, World world) {
/*  67 */     this(type, world, (ResourceLocation[])null);
/*     */   }
/*     */   
/*     */   public OPEntity(EntityType type, World world, ResourceLocation[] textures) {
/*  71 */     super(type, world);
/*     */     
/*  73 */     this.textures = textures;
/*     */     
/*  75 */     if (world != null && !world.field_72995_K) {
/*  76 */       chooseTexture();
/*     */       
/*  78 */       if (getCurrentTexture() == null) {
/*  79 */         setTexture(getDefaultTexture());
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/*  85 */     return MobEntity.func_233666_p_()
/*  86 */       .func_233814_a_(Attributes.field_233823_f_)
/*  87 */       .func_233814_a_(Attributes.field_233825_h_);
/*     */   }
/*     */   
/*     */   public IEntityStats getEntityStats() {
/*  91 */     return EntityStatsCapability.get((LivingEntity)this);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/*  96 */     super.func_70088_a();
/*  97 */     func_184212_Q().func_187214_a(ANIMATION, Integer.valueOf(-1));
/*  98 */     func_184212_Q().func_187214_a(HAS_BUSOSHOKU_HAKI_ACTIVE, Boolean.valueOf(false));
/*  99 */     func_184212_Q().func_187214_a(HAS_FULLBODY_HAKI_ACTIVE, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_213281_b(CompoundNBT nbt) {
/* 104 */     super.func_213281_b(nbt);
/*     */     
/* 106 */     nbt.func_74778_a("texture", getCurrentTexture().toString());
/*     */     
/* 108 */     nbt.func_74757_a("hasBusoHaki", hasBusoHaki());
/* 109 */     nbt.func_74757_a("hasFullbodyHaki", hasFullbodyHaki());
/* 110 */     nbt.func_74757_a("hasFear", this.hasFear);
/* 111 */     nbt.func_74778_a("crew", getCrew());
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70037_a(CompoundNBT nbt) {
/* 116 */     super.func_70037_a(nbt);
/*     */     
/* 118 */     setTexture(new ResourceLocation(nbt.func_74779_i("texture")));
/*     */ 
/*     */     
/* 121 */     if (Strings.isNullOrEmpty(getCurrentTexture().func_110623_a())) {
/* 122 */       chooseTexture();
/*     */     }
/* 124 */     else if (!getCurrentTexture().func_110623_a().contains("/")) {
/* 125 */       chooseTexture();
/*     */     } 
/*     */     
/* 128 */     setBusoHaki(nbt.func_74767_n("hasBusoHaki"));
/* 129 */     setFullbodyHaki(nbt.func_74767_n("hasFullbodyHaki"));
/* 130 */     this.hasFear = nbt.func_74767_n("hasFear");
/* 131 */     setCrew(nbt.func_74779_i("crew"));
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getDefaultTexture() {
/* 136 */     if (this.textures != null) {
/* 137 */       return this.textures[0];
/*     */     }
/* 139 */     return ModResources.NULL_ENTITY_TEXTURE;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ILivingEntityData func_213386_a(IServerWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
/* 145 */     spawnData = super.func_213386_a(world, difficulty, reason, spawnData, dataTag);
/*     */     
/* 147 */     func_184641_n(false);
/*     */     
/* 149 */     if (dataTag != null && dataTag.func_74764_b("isSpawned")) {
/* 150 */       this.isSpawnerChild = true;
/*     */     }
/* 152 */     this.homePosition = Optional.of(func_213303_ch());
/*     */     
/* 154 */     return spawnData;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70636_d() {
/* 159 */     func_82168_bl();
/* 160 */     super.func_70636_d();
/*     */     
/* 162 */     if (this instanceof ICommandReceiver) {
/* 163 */       ICommandReceiver receiver = (ICommandReceiver)this;
/* 164 */       if (!receiver.canMaintainCommand()) {
/* 165 */         receiver.setCurrentCommand(null, NPCCommand.IDLE);
/*     */       }
/*     */     } 
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
/*     */   protected void func_213333_a(DamageSource source, int looting, boolean recentlyHitIn) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean checkSpawnRules(EntityType<? extends OPEntity> type, IServerWorld world, SpawnReason reason, BlockPos pos, Random random) {
/* 188 */     if (reason == SpawnReason.SPAWNER) {
/* 189 */       return true;
/*     */     }
/*     */     
/* 192 */     if (world.func_175659_aa() == Difficulty.PEACEFUL) {
/* 193 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 197 */     if (world.func_242415_f(0.0F) > 0.25F && world.func_242415_f(0.0F) < 0.8F) {
/* 198 */       return false;
/*     */     }
/*     */     
/* 201 */     if (!world.func_226660_f_(pos)) {
/* 202 */       return false;
/*     */     }
/*     */     
/* 205 */     if (pos.func_177956_o() > 150) {
/* 206 */       return false;
/*     */     }
/*     */     
/* 209 */     boolean isValidSpawn = world.func_180495_p(pos.func_177977_b()).func_215688_a((IBlockReader)world, pos.func_177977_b(), type);
/* 210 */     if (!isValidSpawn) {
/* 211 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 220 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_213380_a(IWorld world, SpawnReason reason) {
/* 225 */     return super.func_213380_a(world, reason);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_213397_c(double distance) {
/* 230 */     if (isSpawnedViaSpawner() && distance > 40000.0D) {
/* 231 */       return true;
/*     */     }
/*     */     
/* 234 */     return false;
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
/*     */   public boolean func_70648_aU() {
/* 246 */     return EntityStatsCapability.get((LivingEntity)this).isFishman();
/*     */   }
/*     */   
/*     */   public boolean isSpawnedViaSpawner() {
/* 250 */     return this.isSpawnerChild;
/*     */   }
/*     */ 
/*     */   
/*     */   public Optional<Vector3d> getHomePosition() {
/* 255 */     return this.homePosition;
/*     */   }
/*     */ 
/*     */   
/*     */   public ResourceLocation getCurrentTexture() {
/* 260 */     return this.currentTexture;
/*     */   }
/*     */   
/*     */   protected void setTexture(ResourceLocation id) {
/* 264 */     this.currentTexture = id;
/*     */   }
/*     */   
/*     */   public void setTextures(ResourceLocation[] textures) {
/* 268 */     this.textures = textures;
/*     */   }
/*     */   
/*     */   public void chooseTexture() {
/* 272 */     chooseTexture(this.field_70146_Z);
/*     */   }
/*     */   
/*     */   public void chooseTexture(Random random) {
/* 276 */     if (this.textures != null && this.textures.length > 0) {
/* 277 */       int id = random.nextInt(this.textures.length);
/* 278 */       setTexture(this.textures[id]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setFear(boolean flag) {
/* 283 */     this.hasFear = flag;
/*     */   }
/*     */   
/*     */   public boolean hasFear() {
/* 287 */     return this.hasFear;
/*     */   }
/*     */   
/*     */   public double getDoriki() {
/* 291 */     return getEntityStats().getDoriki();
/*     */   }
/*     */   
/*     */   public void setDoriki(double value) {
/* 295 */     getEntityStats().setDoriki((int)Math.floor(value));
/*     */     
/* 297 */     this.field_70728_aV = (int)(getDoriki() / 100.0D);
/*     */   }
/*     */   
/*     */   public long getBelly() {
/* 301 */     return getEntityStats().getBelly();
/*     */   }
/*     */   
/*     */   public void setBelly(double value) {
/* 305 */     getEntityStats().setBelly((long)value);
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public int getAnimation() {
/* 311 */     return ((Integer)this.field_70180_af.func_187225_a(ANIMATION)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setAnimation(int value) {
/* 317 */     this.field_70180_af.func_187227_b(ANIMATION, Integer.valueOf(value));
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public boolean hasBusoHaki() {
/* 323 */     return ((Boolean)this.field_70180_af.func_187225_a(HAS_BUSOSHOKU_HAKI_ACTIVE)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setBusoHaki(boolean value) {
/* 329 */     this.field_70180_af.func_187227_b(HAS_BUSOSHOKU_HAKI_ACTIVE, Boolean.valueOf(value));
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public boolean hasFullbodyHaki() {
/* 335 */     return ((Boolean)func_184212_Q().func_187225_a(HAS_FULLBODY_HAKI_ACTIVE)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setFullbodyHaki(boolean value) {
/* 341 */     func_184212_Q().func_187227_b(HAS_FULLBODY_HAKI_ACTIVE, Boolean.valueOf(value));
/*     */   }
/*     */ 
/*     */   
/*     */   public void queueEntityDataUpdate() {
/* 346 */     this.needsEntityDataUpdate = true;
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public String getCrew() {
/* 352 */     return this.crew;
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setCrew(String crew) {
/* 358 */     this.crew = crew;
/*     */   }
/*     */   
/*     */   public ItemStack getRandomSword(List<Supplier<? extends Item>> list) {
/* 362 */     return getRandomSword(this.field_70146_Z, list);
/*     */   }
/*     */   
/*     */   public ItemStack getRandomSword(Random random, List<Supplier<? extends Item>> list) {
/* 366 */     return new ItemStack(((Supplier<IItemProvider>)list.get(random.nextInt(list.size()))).get());
/*     */   }
/*     */   
/*     */   public boolean isAboveNormalDifficulty() {
/* 370 */     return (this.field_70170_p.func_175659_aa().func_151525_a() > Difficulty.NORMAL.func_151525_a());
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 375 */     super.func_70071_h_();
/*     */     
/* 377 */     if (this.field_70725_aQ > 0 && func_110143_aJ() > 0.0F) {
/* 378 */       this.field_70725_aQ = 0;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 386 */     if (!this.field_70170_p.field_72995_K) {
/* 387 */       if (func_184187_bx() instanceof net.minecraft.entity.item.BoatEntity && func_70638_az() != null) {
/* 388 */         func_184210_p();
/*     */       }
/*     */       
/* 391 */       if (this.needsEntityDataUpdate) {
/* 392 */         WyNetwork.sendToAllTrackingAndSelf(new SSyncEntityStatsPacket(func_145782_y(), getEntityStats()), (Entity)this);
/* 393 */         this.needsEntityDataUpdate = false;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70619_bc() {
/* 400 */     this.goalMemories.tick();
/*     */   }
/*     */ 
/*     */   
/*     */   public double func_70033_W() {
/* 405 */     return -0.35D;
/*     */   }
/*     */ 
/*     */   
/*     */   public IPacket<?> func_213297_N() {
/* 410 */     return NetworkHooks.getEntitySpawningPacket((Entity)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeSpawnData(PacketBuffer buffer) {
/* 415 */     int len = this.currentTexture.toString().length();
/* 416 */     buffer.writeInt(len);
/* 417 */     buffer.func_211400_a(this.currentTexture.toString(), len);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readSpawnData(PacketBuffer buffer) {
/* 422 */     int len = buffer.readInt();
/* 423 */     this.currentTexture = new ResourceLocation(buffer.func_150789_c(len));
/*     */   }
/*     */ 
/*     */   
/*     */   public GoalMemories getGoalMemories() {
/* 428 */     return this.goalMemories;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public enum Animation
/*     */     implements IExtensibleEnum {
/* 434 */     NONE(0), FLINTLOCK_POINTING(1), CLEAVE_ATTACK(2), SHOCKWAVE(3), BLOCK(4);
/*     */     
/*     */     private int id;
/*     */ 
/*     */     
/*     */     Animation(int id) {
/* 440 */       this.id = id;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getId() {
/* 445 */       return this.id;
/*     */     }
/*     */ 
/*     */     
/*     */     public static Animation create(String name, int id) {
/* 450 */       throw new IllegalStateException("Enum not extended");
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\OPEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */