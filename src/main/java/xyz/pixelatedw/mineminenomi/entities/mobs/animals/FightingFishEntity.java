/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.animals;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityPredicate;
/*     */ import net.minecraft.entity.EntitySize;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.ILivingEntityData;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.MoverType;
/*     */ import net.minecraft.entity.SpawnReason;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.controller.LookController;
/*     */ import net.minecraft.entity.ai.controller.MovementController;
/*     */ import net.minecraft.entity.ai.goal.FindWaterGoal;
/*     */ import net.minecraft.entity.ai.goal.FollowBoatGoal;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.ai.goal.HurtByTargetGoal;
/*     */ import net.minecraft.entity.ai.goal.LookAtGoal;
/*     */ import net.minecraft.entity.ai.goal.LookRandomlyGoal;
/*     */ import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
/*     */ import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
/*     */ import net.minecraft.entity.monster.IMob;
/*     */ import net.minecraft.entity.passive.WaterMobEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.pathfinding.PathNavigator;
/*     */ import net.minecraft.pathfinding.SwimmerPathNavigator;
/*     */ import net.minecraft.tags.FluidTags;
/*     */ import net.minecraft.tags.ITag;
/*     */ import net.minecraft.util.RegistryKey;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.IServerWorld;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.Biome;
/*     */ import net.minecraft.world.biome.Biomes;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.ai.controllers.FishSwimMoveController;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.ai.controllers.LandFishLookController;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModTags;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class FightingFishEntity extends WaterMobEntity implements IMob {
/*  60 */   private static final DataParameter<Float> SIZE = EntityDataManager.func_187226_a(FightingFishEntity.class, DataSerializers.field_187193_c);
/*  61 */   public static final EntityPredicate TARGET_PREDICATE = (new EntityPredicate()).func_221013_a(20.0D).func_221011_b().func_221008_a();
/*  62 */   private static final List<RegistryKey<Biome>> SPAWN_BIOMES = new ArrayList<>(Arrays.asList((RegistryKey<Biome>[])new RegistryKey[] { Biomes.field_76771_b, Biomes.field_150575_M, Biomes.field_203615_U, Biomes.field_203618_X, Biomes.field_203614_T, Biomes.field_203617_W }));
/*     */ 
/*     */   
/*     */   public FightingFishEntity(EntityType type, World world) {
/*  66 */     super(type, world);
/*  67 */     this.field_70765_h = (MovementController)new FishSwimMoveController((MobEntity)this);
/*  68 */     this.field_70749_g = (LookController)new LandFishLookController((MobEntity)this, 10);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_184651_r() {
/*  74 */     this.field_70714_bg.func_75776_a(0, (Goal)new FindWaterGoal((CreatureEntity)this));
/*  75 */     this.field_70714_bg.func_75776_a(0, (Goal)new AvoidBlockTagGoal((CreatureEntity)this, (ITag.INamedTag)ModTags.Blocks.KAIROSEKI));
/*  76 */     this.field_70714_bg.func_75776_a(0, (Goal)new AvoidCoatedBoatGoal((CreatureEntity)this));
/*  77 */     this.field_70714_bg.func_75776_a(1, (Goal)new ImprovedMeleeAttackGoal((CreatureEntity)this, 1.7999999523162842D, true));
/*  78 */     this.field_70714_bg.func_75776_a(1, (Goal)new RandomSwimmingGoal((CreatureEntity)this, 1.0D, 10));
/*  79 */     this.field_70714_bg.func_75776_a(1, (Goal)new LookRandomlyGoal((MobEntity)this));
/*  80 */     this.field_70714_bg.func_75776_a(2, (Goal)new LookAtGoal((MobEntity)this, PlayerEntity.class, 12.0F));
/*  81 */     this.field_70714_bg.func_75776_a(4, (Goal)new FollowBoatGoal((CreatureEntity)this));
/*  82 */     this.field_70714_bg.func_75776_a(5, (Goal)new BreakBoatGoal((CreatureEntity)this));
/*     */     
/*  84 */     this.field_70715_bh.func_75776_a(1, (Goal)new HurtByTargetGoal((CreatureEntity)this, new Class[0]));
/*  85 */     this.field_70715_bh.func_75776_a(2, (Goal)new NearestAttackableTargetGoal((MobEntity)this, PlayerEntity.class, true));
/*  86 */     this.field_70715_bh.func_75776_a(3, (Goal)new NearestAttackableTargetGoal((MobEntity)this, YagaraBullEntity.class, false));
/*     */   }
/*     */ 
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/*  91 */     return MobEntity.func_233666_p_()
/*  92 */       .func_233815_a_(Attributes.field_233821_d_, 1.2000000476837158D)
/*  93 */       .func_233815_a_(Attributes.field_233819_b_, 55.0D)
/*  94 */       .func_233815_a_(Attributes.field_233823_f_, 7.0D)
/*  95 */       .func_233815_a_(Attributes.field_233818_a_, 40.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/* 101 */     super.func_70088_a();
/* 102 */     float size = 0.2F + this.field_70146_Z.nextFloat();
/* 103 */     this.field_70180_af.func_187214_a(SIZE, Float.valueOf(Math.min(size, 1.0F)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ILivingEntityData func_213386_a(IServerWorld pLevel, DifficultyInstance pDifficulty, SpawnReason pReason, @Nullable ILivingEntityData pSpawnData, @Nullable CompoundNBT pDataTag) {
/* 110 */     this.field_70125_A = 0.0F;
/*     */     
/* 112 */     double r = WyHelper.randomWithRange(this.field_70146_Z, 0, 64);
/* 113 */     if (r == 0.0D) {
/* 114 */       func_200203_b((ITextComponent)new StringTextComponent("Timmy"));
/*     */     }
/* 116 */     double reach = ((1.0F - getSize()) * 30.0F);
/* 117 */     func_110148_a((Attribute)ModAttributes.ATTACK_RANGE.get()).func_111128_a(-reach);
/* 118 */     func_110148_a(Attributes.field_233818_a_).func_111128_a(func_233637_b_(Attributes.field_233818_a_) * Math.max(getSize(), 0.5D));
/*     */     
/* 120 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)this);
/* 121 */     props.setDoriki((1500.0F + getSize() * 1000.0F));
/*     */     
/* 123 */     return super.func_213386_a(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_209207_l(int air) {
/* 129 */     super.func_209207_l(air);
/* 130 */     if (WyHelper.isAprilFirst()) {
/* 131 */       func_70050_g(300);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_213281_b(CompoundNBT nbt) {
/* 137 */     super.func_213281_b(nbt);
/* 138 */     nbt.func_74776_a("size", ((Float)func_184212_Q().func_187225_a(SIZE)).floatValue());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70037_a(CompoundNBT nbt) {
/* 144 */     super.func_70037_a(nbt);
/* 145 */     func_184212_Q().func_187227_b(SIZE, Float.valueOf(nbt.func_74760_g("size")));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_184206_a(DataParameter<?> key) {
/* 151 */     if (SIZE.equals(key)) {
/* 152 */       func_213323_x_();
/*     */     }
/* 154 */     super.func_184206_a(key);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EntitySize func_213305_a(Pose pose) {
/* 160 */     float fishScale = getSize();
/* 161 */     EntitySize newSize = func_200600_R().func_220334_j().func_220313_a(fishScale);
/* 162 */     return newSize;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getSize() {
/* 167 */     return ((Float)func_184212_Q().func_187225_a(SIZE)).floatValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70646_bf() {
/* 173 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_184649_cE() {
/* 179 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected PathNavigator func_175447_b(World level) {
/* 185 */     if (WyHelper.isAprilFirst())
/*     */     {
/* 187 */       return super.func_175447_b(level);
/*     */     }
/* 189 */     return (PathNavigator)new SwimmerPathNavigator((MobEntity)this, level);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 194 */     super.func_70071_h_();
/*     */     
/* 196 */     if (!(func_130014_f_()).field_72995_K && !func_208600_a((ITag)FluidTags.field_206959_a)) {
/* 197 */       AbilityHelper.setDeltaMovement((Entity)this, func_213322_ci().func_72441_c(0.0D, -0.01D, 0.0D));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_213352_e(Vector3d pTravelVector) {
/* 203 */     if (func_70613_aW() && func_70090_H()) {
/* 204 */       func_213309_a(func_70689_ay(), pTravelVector);
/* 205 */       func_213315_a(MoverType.SELF, func_213322_ci());
/*     */       
/* 207 */       AbilityHelper.setDeltaMovement((Entity)this, func_213322_ci().func_186678_a(0.9D));
/*     */       
/* 209 */       if (func_70638_az() == null) {
/* 210 */         AbilityHelper.setDeltaMovement((Entity)this, func_213322_ci().func_72441_c(0.0D, -0.005D, 0.0D));
/*     */       }
/*     */     } else {
/* 213 */       super.func_213352_e(pTravelVector);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean checkSpawnRules(EntityType<FightingFishEntity> entity, IWorld world, SpawnReason spawnType, BlockPos pos, Random random) {
/* 218 */     if (WyHelper.isAprilFirst()) {
/* 219 */       return MobEntity.func_223315_a(entity, world, spawnType, pos, random);
/*     */     }
/*     */     
/* 222 */     if (pos.func_177956_o() >= world.func_181545_F() - 10) {
/* 223 */       return false;
/*     */     }
/*     */     
/* 226 */     return world.func_204610_c(pos).func_206884_a((ITag)FluidTags.field_206959_a);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70648_aU() {
/* 232 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_184652_a(PlayerEntity pPlayer) {
/* 238 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\animals\FightingFishEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */