/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.goals;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.fluid.FluidState;
/*     */ import net.minecraft.tags.FluidTags;
/*     */ import net.minecraft.tags.ITag;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.vector.Vector3i;
/*     */ import net.minecraft.world.IBlockReader;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.TickedGoal;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ 
/*     */ public class SwimUpOnDrowningGoal
/*     */   extends TickedGoal<CreatureEntity> {
/*     */   private static final int MAX_AIR_ATTEMPTS = 20;
/*     */   private static final int NEXT_AIR_ATTEMPT_COOLDOWN = 300;
/*  22 */   private int airAttempts = 0; private IEntityStats entityStats; private boolean isDrowining; private BlockPos surfacePosition;
/*  23 */   private long lastAirAttempt = 0L;
/*     */   
/*     */   public SwimUpOnDrowningGoal(CreatureEntity entity) {
/*  26 */     super((MobEntity)entity);
/*  27 */     this.entityStats = EntityStatsCapability.get((LivingEntity)entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_75250_a() {
/*  32 */     if (this.entityStats.isFishman()) {
/*  33 */       return false;
/*     */     }
/*     */     
/*  36 */     if (!((CreatureEntity)this.entity).func_208600_a((ITag)FluidTags.field_206959_a)) {
/*  37 */       return false;
/*     */     }
/*     */     
/*  40 */     if (((CreatureEntity)this.entity).func_70086_ai() >= ((CreatureEntity)this.entity).func_205010_bg() / 2 && ((CreatureEntity)this.entity).func_189748_bU() != DamageSource.field_76369_e) {
/*  41 */       return false;
/*     */     }
/*     */     
/*  44 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_75253_b() {
/*  49 */     if (!((CreatureEntity)this.entity).func_208600_a((ITag)FluidTags.field_206959_a)) {
/*  50 */       return false;
/*     */     }
/*     */     
/*  53 */     if (((CreatureEntity)this.entity).func_70086_ai() >= ((CreatureEntity)this.entity).func_205010_bg() / 2) {
/*  54 */       return false;
/*     */     }
/*     */     
/*  57 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75249_e() {
/*  62 */     super.func_75249_e();
/*     */     
/*  64 */     this.isDrowining = true;
/*     */     
/*  66 */     BlockPos.Mutable mutpos = new BlockPos.Mutable();
/*  67 */     mutpos.func_189533_g((Vector3i)((CreatureEntity)this.entity).func_233580_cy_());
/*  68 */     if (((CreatureEntity)this.entity).field_70170_p.func_226660_f_(((CreatureEntity)this.entity).func_233580_cy_())) {
/*  69 */       for (int i = 0; i < 20; i++) {
/*  70 */         FluidState fluidState = ((CreatureEntity)this.entity).field_70170_p.func_204610_c(mutpos.func_177981_b(i));
/*  71 */         if (fluidState.func_206888_e()) {
/*  72 */           this.surfacePosition = mutpos.func_185334_h();
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } else {
/*  78 */       tryFindingAir();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_75246_d() {
/*  84 */     super.func_75246_d();
/*     */     
/*  86 */     if (this.surfacePosition != null) {
/*  87 */       ((CreatureEntity)this.entity).func_70661_as().func_75499_g();
/*  88 */       ((CreatureEntity)this.entity).func_70661_as().func_75492_a(this.surfacePosition.func_177958_n(), this.surfacePosition.func_177956_o(), this.surfacePosition.func_177952_p(), 1.5499999523162842D);
/*     */     
/*     */     }
/*  91 */     else if (((CreatureEntity)this.entity).field_70170_p.func_82737_E() >= this.lastAirAttempt + 300L) {
/*  92 */       tryFindingAir();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75251_c() {
/*  99 */     super.func_75251_c();
/*     */     
/* 101 */     this.isDrowining = false;
/* 102 */     this.surfacePosition = null;
/* 103 */     this.airAttempts = 0;
/*     */   }
/*     */   
/*     */   private void tryFindingAir() {
/* 107 */     while (this.airAttempts < 20) {
/* 108 */       this.surfacePosition = lookForAir((IBlockReader)((CreatureEntity)this.entity).field_70170_p, 40, 40);
/* 109 */       if (this.surfacePosition == null) {
/* 110 */         this.airAttempts++;
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 117 */     this.lastAirAttempt = ((CreatureEntity)this.entity).field_70170_p.func_82737_E();
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   protected BlockPos lookForAir(IBlockReader level, int horizontalRange, int verticalRange) {
/* 122 */     BlockPos blockpos = ((CreatureEntity)this.entity).func_233580_cy_();
/* 123 */     int x0 = blockpos.func_177958_n();
/* 124 */     int y0 = blockpos.func_177956_o();
/* 125 */     int z0 = blockpos.func_177952_p();
/* 126 */     float f = (horizontalRange * horizontalRange * verticalRange * 2);
/* 127 */     BlockPos targetPosition = null;
/* 128 */     BlockPos.Mutable mutpos = new BlockPos.Mutable();
/*     */     
/* 130 */     for (int x = x0 - horizontalRange; x <= x0 + horizontalRange; x++) {
/* 131 */       for (int y = y0 - verticalRange; y <= y0 + verticalRange; y++) {
/* 132 */         for (int z = z0 - horizontalRange; z <= z0 + horizontalRange; z++) {
/* 133 */           mutpos.func_181079_c(x, y, z);
/* 134 */           if (level.func_204610_c((BlockPos)mutpos).func_206888_e()) {
/* 135 */             float dist = ((x - x0) * (x - x0) + (y - y0) * (y - y0) + (z - z0) * (z - z0));
/* 136 */             if (dist < f) {
/* 137 */               f = dist;
/* 138 */               targetPosition = mutpos.func_185334_h();
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 145 */     return targetPosition;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\goals\SwimUpOnDrowningGoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */