/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.pirates;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.ILivingEntityData;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.SpawnReason;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifierMap;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.IServerWorld;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.BigBombThrowGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class PirateBomberEntity extends AbstractPirateEntity {
/*  28 */   private static final DataParameter<Boolean> HAS_BOMB = EntityDataManager.func_187226_a(PirateBomberEntity.class, DataSerializers.field_187198_h);
/*  29 */   private static final DataParameter<Integer> BOMB_FUSE = EntityDataManager.func_187226_a(PirateBomberEntity.class, DataSerializers.field_187192_b);
/*     */ 
/*     */   
/*     */   public PirateBomberEntity(EntityType<? extends MobEntity> type, World world) {
/*  33 */     super(type, world, MobsHelper.PIRATE_TEXTURES);
/*  34 */     EntityStatsCapability.get((LivingEntity)this).setFightingStyle(ModValues.SWORDSMAN);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_184651_r() {
/*  40 */     super.func_184651_r();
/*  41 */     this.field_70714_bg.func_75776_a(1, (Goal)new ImprovedMeleeAttackGoal((CreatureEntity)this, 1.0D, true));
/*  42 */     this.field_70714_bg.func_75776_a(2, (Goal)new BigBombThrowGoal(this));
/*     */   }
/*     */ 
/*     */   
/*     */   public static AttributeModifierMap.MutableAttribute createAttributes() {
/*  47 */     return OPEntity.createAttributes().func_233815_a_(Attributes.field_233819_b_, 35.0D).func_233815_a_(Attributes.field_233821_d_, 0.22499999403953552D).func_233815_a_(Attributes.field_233823_f_, 1.0D).func_233815_a_(Attributes.field_233818_a_, 25.0D).func_233815_a_(Attributes.field_233826_i_, 2.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/*  53 */     super.func_70071_h_();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ILivingEntityData func_213386_a(IServerWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
/*  60 */     spawnData = super.func_213386_a(world, difficulty, reason, spawnData, dataTag);
/*  61 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)this);
/*     */     
/*  63 */     if (props.isFishman()) {
/*     */       
/*  65 */       this.textures = MobsHelper.PIRATE_FISHMEN_TEXTURES;
/*  66 */       chooseTexture();
/*     */     } 
/*     */     
/*  69 */     setDoriki(2000.0D + WyHelper.randomWithRange(0, 1000));
/*  70 */     setBelly(5.0D + WyHelper.randomWithRange(0, 10));
/*     */     
/*  72 */     return spawnData;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/*  78 */     super.func_70088_a();
/*  79 */     func_184212_Q().func_187214_a(HAS_BOMB, Boolean.valueOf(true));
/*  80 */     func_184212_Q().func_187214_a(BOMB_FUSE, Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_213281_b(CompoundNBT nbt) {
/*  86 */     super.func_213281_b(nbt);
/*  87 */     nbt.func_74757_a("hasBomb", hasBomb());
/*  88 */     nbt.func_74768_a("bombFuse", getBombFuseTime());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70037_a(CompoundNBT nbt) {
/*  94 */     super.func_70037_a(nbt);
/*  95 */     setHasBomb(nbt.func_74767_n("hasBomb"));
/*  96 */     setBombFuseTime(nbt.func_74762_e("bombFuse"));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBombFuseTime() {
/* 101 */     return ((Integer)func_184212_Q().func_187225_a(BOMB_FUSE)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBombFused() {
/* 106 */     return (((Integer)func_184212_Q().func_187225_a(BOMB_FUSE)).intValue() > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBombFuseTime(int time) {
/* 111 */     func_184212_Q().func_187227_b(BOMB_FUSE, Integer.valueOf(time));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasBomb() {
/* 116 */     return ((Boolean)func_184212_Q().func_187225_a(HAS_BOMB)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHasBomb(boolean flag) {
/* 121 */     func_184212_Q().func_187227_b(HAS_BOMB, Boolean.valueOf(flag));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\pirates\PirateBomberEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */