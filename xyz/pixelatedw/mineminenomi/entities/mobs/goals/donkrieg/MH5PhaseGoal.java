/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals.donkrieg;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.TickedGoal;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.BruteEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.GruntEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.kriegpirates.DonKriegEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModNPCGroups;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class MH5PhaseGoal extends TickedGoal<DonKriegEntity> {
/*  21 */   private BlockPos[] spawnPositions = new BlockPos[4];
/*  22 */   private int findIter = 0;
/*  23 */   private int spawnIter = 0;
/*     */   
/*  25 */   private final ExtendedWorldData worldData = ExtendedWorldData.get();
/*     */   private final int spawnIterMax;
/*     */   private final int spawnRate;
/*     */   
/*     */   public MH5PhaseGoal(DonKriegEntity entity) {
/*  30 */     super((MobEntity)entity);
/*     */     
/*  32 */     this.spawnRate = entity.isDifficultyHardOrAbove() ? 40 : 60;
/*  33 */     this.spawnIterMax = entity.isDifficultyHardOrAbove() ? 7 : 5;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_75250_a() {
/*  38 */     if (hasSpawnArrayEmptyPos() && this.findIter < 100) {
/*  39 */       findSpawnPos();
/*  40 */       return false;
/*     */     } 
/*     */     
/*  43 */     if (!((DonKriegEntity)this.entity).hasMH5PhaseActive()) {
/*  44 */       return false;
/*     */     }
/*     */     
/*  47 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_75253_b() {
/*  52 */     if (!((DonKriegEntity)this.entity).hasMH5PhaseActive()) {
/*  53 */       return false;
/*     */     }
/*     */     
/*  56 */     if (this.spawnIter >= this.spawnIterMax) {
/*  57 */       return false;
/*     */     }
/*     */     
/*  60 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75249_e() {
/*  65 */     super.func_75249_e();
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75246_d() {
/*  70 */     super.func_75246_d();
/*     */     
/*  72 */     if (((DonKriegEntity)this.entity).field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  76 */     if (getTickCount() % this.spawnRate == 0L) {
/*  77 */       for (BlockPos pos : this.spawnPositions) {
/*  78 */         if (pos != null) {
/*     */           GruntEntity gruntEntity;
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*  84 */           boolean isBrute = (((DonKriegEntity)this.entity).isDifficultyHardOrAbove() && ((DonKriegEntity)this.entity).func_70681_au().nextInt(3) == 0);
/*  85 */           if (isBrute) {
/*  86 */             BruteEntity bruteEntity = BruteEntity.createPirateBrute((EntityType)ModEntities.PIRATE_GRUNT.get(), ((DonKriegEntity)this.entity).field_70170_p);
/*  87 */             bruteEntity.func_70107_b(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
/*  88 */             this.worldData.addTemporaryCrewMember(ModNPCGroups.KRIEG_PIRATES, (LivingEntity)bruteEntity);
/*     */           } else {
/*     */             
/*  91 */             gruntEntity = GruntEntity.createPirateGrunt((EntityType)ModEntities.PIRATE_GRUNT.get(), ((DonKriegEntity)this.entity).field_70170_p);
/*  92 */             gruntEntity.func_70107_b(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
/*  93 */             this.worldData.addTemporaryCrewMember(ModNPCGroups.KRIEG_PIRATES, (LivingEntity)gruntEntity);
/*     */           } 
/*     */           
/*  96 */           if (((DonKriegEntity)this.entity).getChallengeInfo().getChallengerGroup().size() > 1) {
/*  97 */             List<LivingEntity> targets = WyHelper.shuffle(((DonKriegEntity)this.entity).getChallengeInfo().getChallengerGroup());
/*  98 */             gruntEntity.func_70624_b(targets.get(0));
/*     */           } else {
/*     */             
/* 101 */             gruntEntity.func_70624_b(((DonKriegEntity)this.entity).func_70638_az());
/*     */           } 
/*     */           
/* 104 */           ((DonKriegEntity)this.entity).field_70170_p.func_217376_c((Entity)gruntEntity);
/*     */           
/* 106 */           if (((DonKriegEntity)this.entity).func_70681_au().nextInt(3) == 0) {
/* 107 */             gruntEntity.func_184201_a(EquipmentSlotType.HEAD, ((Item)ModArmors.MH5_GAS_MASK.get()).func_190903_i());
/*     */           }
/*     */         } 
/*     */       } 
/* 111 */       this.spawnIter++;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75251_c() {
/* 117 */     super.func_75251_c();
/*     */     
/* 119 */     cleanSpawnArray();
/* 120 */     this.findIter = 0;
/*     */   }
/*     */   
/*     */   private void findSpawnPos() {
/* 124 */     BlockPos pos = WyHelper.findValidGroundLocation(((DonKriegEntity)this.entity).field_70170_p, ((DonKriegEntity)this.entity).func_233580_cy_(), 20, 10);
/* 125 */     if (pos != null) {
/* 126 */       for (int i = 0; i < this.spawnPositions.length; i++) {
/* 127 */         if (this.spawnPositions[i] == null) {
/* 128 */           this.spawnPositions[i] = pos;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } else {
/* 134 */       this.findIter++;
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean hasSpawnArrayEmptyPos() {
/* 139 */     boolean flag = false;
/* 140 */     for (BlockPos pos : this.spawnPositions) {
/* 141 */       if (pos == null) {
/* 142 */         flag = true;
/*     */         break;
/*     */       } 
/*     */     } 
/* 146 */     return flag;
/*     */   }
/*     */   
/*     */   private void cleanSpawnArray() {
/* 150 */     for (int i = 0; i < this.spawnPositions.length; i++)
/* 151 */       this.spawnPositions[i] = null; 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\donkrieg\MH5PhaseGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */