/*     */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*     */ 
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ 
/*     */ public abstract class OutOfBodyAbility<T extends LivingEntity> extends Ability {
/*     */   private T body;
/*  18 */   protected final ContinuousComponent continuousComponent = (new ContinuousComponent(this, true)).addStartEvent(200, this::startContinuityEvent).addTickEvent(200, this::duringContinuityEvent).addEndEvent(200, this::stopContinuityEvent); private BlockPos pivotPoint;
/*     */   
/*     */   public OutOfBodyAbility(AbilityCore<? extends IAbility> core) {
/*  21 */     super(core);
/*     */     
/*  23 */     this.isNew = true;
/*  24 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent });
/*     */     
/*  26 */     addCanUseCheck((entity, ability) -> AbilityHelper.isInCreativeOrSpectator(entity) ? AbilityUseResult.fail((ITextComponent)ModI18n.ABILITY_MESSAGE_SUVIVAL_ONLY) : AbilityUseResult.success());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  33 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  37 */     this.continuousComponent.triggerContinuity(entity, getHoldTime());
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  41 */     setPivotPoint(this.body.func_233580_cy_());
/*  42 */     entity.func_230245_c_(false);
/*  43 */     if (entity instanceof PlayerEntity) {
/*  44 */       PlayerEntity player = (PlayerEntity)entity;
/*  45 */       player.field_71075_bZ.field_75100_b = true;
/*  46 */       if (player instanceof ServerPlayerEntity) {
/*  47 */         ((ServerPlayerEntity)player).func_71016_p();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void stopContinuityEvent(LivingEntity entity, IAbility ability) {
/*  53 */     if (this.body != null) {
/*  54 */       entity.func_70634_a(getPivotPoint().func_177958_n(), getPivotPoint().func_177956_o(), getPivotPoint().func_177952_p());
/*  55 */       this.body.func_70106_y();
/*  56 */       this.body = null;
/*     */     } 
/*     */     
/*  59 */     if (!isPhysical()) {
/*  60 */       entity.field_70145_X = false;
/*     */     }
/*     */     
/*  63 */     if (entity instanceof PlayerEntity) {
/*  64 */       PlayerEntity player = (PlayerEntity)entity;
/*  65 */       player.field_71075_bZ.field_75100_b = false;
/*  66 */       if (player instanceof ServerPlayerEntity) {
/*  67 */         ((ServerPlayerEntity)player).func_71016_p();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/*  74 */     if (entity.func_184218_aH()) {
/*  75 */       entity.func_184210_p();
/*     */     }
/*     */     
/*  78 */     if (!isPhysical()) {
/*  79 */       entity.func_230245_c_(false);
/*  80 */       entity.field_70145_X = true;
/*  81 */       entity.func_70066_B();
/*     */     } 
/*     */     
/*  84 */     if (entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/*  88 */     if (Math.sqrt(entity.func_70092_e(getPivotPoint().func_177958_n(), getPivotPoint().func_177956_o(), getPivotPoint().func_177952_p())) > getMaxRange() || 
/*  89 */       getOriginalBody() == null) {
/*  90 */       this.continuousComponent.stopContinuity(entity);
/*     */       
/*     */       return;
/*     */     } 
/*  94 */     if (getPivotPoint().func_177958_n() != (getOriginalBody().func_213303_ch()).field_72450_a || getPivotPoint().func_177952_p() != (getOriginalBody().func_213303_ch()).field_72449_c || 
/*  95 */       getPivotPoint().func_177956_o() != (getOriginalBody().func_213303_ch()).field_72448_b) {
/*  96 */       setPivotPoint(getOriginalBody().func_233580_cy_());
/*     */     }
/*  98 */     if (getOriginalBody() == null) {
/*  99 */       this.continuousComponent.stopContinuity(entity);
/*     */       
/*     */       return;
/*     */     } 
/* 103 */     if (!getOriginalBody().func_70089_S()) {
/* 104 */       entity.func_70097_a(ModDamageSource.OUT_OF_BODY, entity.func_110138_aP());
/*     */     }
/*     */   }
/*     */   
/*     */   public T getOriginalBody() {
/* 109 */     return this.body;
/*     */   }
/*     */   
/*     */   public double getDistanceFromPivot(Entity entity) {
/* 113 */     if (getPivotPoint() == null) {
/* 114 */       return -1.0D;
/*     */     }
/* 116 */     return Math.sqrt(entity.func_70092_e(getPivotPoint().func_177958_n(), getPivotPoint().func_177956_o(), getPivotPoint().func_177952_p()));
/*     */   }
/*     */   
/*     */   public void setOriginalBody(T body) {
/* 120 */     this.body = body;
/*     */   }
/*     */   
/*     */   public BlockPos getPivotPoint() {
/* 124 */     return this.pivotPoint;
/*     */   }
/*     */   
/*     */   public void setPivotPoint(BlockPos pos) {
/* 128 */     this.pivotPoint = pos;
/*     */   }
/*     */ 
/*     */   
/*     */   public CompoundNBT save(CompoundNBT nbt) {
/* 133 */     if (this.pivotPoint != null) {
/* 134 */       nbt.func_74780_a("x", this.pivotPoint.func_177958_n());
/* 135 */       nbt.func_74780_a("y", this.pivotPoint.func_177956_o());
/* 136 */       nbt.func_74780_a("z", this.pivotPoint.func_177952_p());
/*     */     } 
/* 138 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(CompoundNBT nbt) {
/* 143 */     double x = nbt.func_74769_h("x");
/* 144 */     double y = nbt.func_74769_h("y");
/* 145 */     double z = nbt.func_74769_h("z");
/* 146 */     this.pivotPoint = new BlockPos(x, y, z);
/*     */   }
/*     */   
/*     */   public int getHoldTime() {
/* 150 */     return -1;
/*     */   }
/*     */   
/*     */   public boolean isActive() {
/* 154 */     return this.continuousComponent.isContinuous();
/*     */   }
/*     */   
/*     */   public abstract float getMaxRange();
/*     */   
/*     */   public abstract boolean isPhysical();
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\OutOfBodyAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */