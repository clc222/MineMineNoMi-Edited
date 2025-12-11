/*     */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.Direction8;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*     */ 
/*     */ public abstract class WallAbility
/*     */   extends Ability {
/*  18 */   protected final ContinuousComponent continuousComponent = (new ContinuousComponent(this, true)).addStartEvent(90, this::startContinuityEvent).addEndEvent(90, this::endContinuityEvent);
/*     */   
/*  20 */   private List<BlockPos> posList = new ArrayList<>();
/*     */   
/*     */   public WallAbility(AbilityCore<? extends IAbility> core) {
/*  23 */     super(core);
/*     */     
/*  25 */     this.isNew = true;
/*  26 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent });
/*     */     
/*  28 */     addUseEvent(90, this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  32 */     this.continuousComponent.triggerContinuity(entity, getHoldTime());
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  36 */     float f = (MathHelper.func_76128_c(-entity.field_70177_z / 45.0D + 0.5D) & 0x7);
/*  37 */     Direction8 dir = Direction8.values()[(int)MathHelper.func_76135_e(f % (Direction8.values()).length)];
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  42 */     if (this.posList.isEmpty()) {
/*  43 */       if (dir == Direction8.SOUTH) {
/*  44 */         this.posList.addAll(AbilityHelper.createFilledCube(entity.field_70170_p, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_() - 4.0D, 
/*  45 */               getLength(), getHeight(), getThickness(), getWallBlock(), getGriefingRule()));
/*     */       }
/*  47 */       else if (dir == Direction8.SOUTH_EAST) {
/*  48 */         this.posList.addAll(AbilityHelper.createFilledCube(entity.field_70170_p, entity.func_226277_ct_() + 2.0D, entity.func_226278_cu_(), entity.func_226281_cx_() - 4.0D, 
/*  49 */               getLength(), getHeight(), getThickness(), getWallBlock(), getGriefingRule()));
/*  50 */         this.posList.addAll(AbilityHelper.createFilledCube(entity.field_70170_p, entity.func_226277_ct_() + 4.0D, entity.func_226278_cu_(), entity.func_226281_cx_() - 2.0D, 
/*  51 */               getThickness(), getHeight(), getLength(), getWallBlock(), getGriefingRule()));
/*     */       }
/*  53 */       else if (dir == Direction8.SOUTH_WEST) {
/*  54 */         this.posList.addAll(AbilityHelper.createFilledCube(entity.field_70170_p, entity.func_226277_ct_() - 2.0D, entity.func_226278_cu_(), entity.func_226281_cx_() - 4.0D, 
/*  55 */               getLength(), getHeight(), getThickness(), getWallBlock(), getGriefingRule()));
/*  56 */         this.posList.addAll(AbilityHelper.createFilledCube(entity.field_70170_p, entity.func_226277_ct_() - 4.0D, entity.func_226278_cu_(), entity.func_226281_cx_() - 2.0D, 
/*  57 */               getThickness(), getHeight(), getLength(), getWallBlock(), getGriefingRule()));
/*     */       }
/*  59 */       else if (dir == Direction8.NORTH) {
/*  60 */         this.posList.addAll(AbilityHelper.createFilledCube(entity.field_70170_p, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_() + 4.0D, 
/*  61 */               getLength(), getHeight(), getThickness(), getWallBlock(), getGriefingRule()));
/*     */       }
/*  63 */       else if (dir == Direction8.NORTH_EAST) {
/*  64 */         this.posList.addAll(AbilityHelper.createFilledCube(entity.field_70170_p, entity.func_226277_ct_() + 2.0D, entity.func_226278_cu_(), entity.func_226281_cx_() + 4.0D, 
/*  65 */               getLength(), getHeight(), getThickness(), getWallBlock(), getGriefingRule()));
/*  66 */         this.posList.addAll(AbilityHelper.createFilledCube(entity.field_70170_p, entity.func_226277_ct_() + 4.0D, entity.func_226278_cu_(), entity.func_226281_cx_() + 2.0D, 
/*  67 */               getThickness(), getHeight(), getLength(), getWallBlock(), getGriefingRule()));
/*     */       }
/*  69 */       else if (dir == Direction8.NORTH_WEST) {
/*  70 */         this.posList.addAll(AbilityHelper.createFilledCube(entity.field_70170_p, entity.func_226277_ct_() - 2.0D, entity.func_226278_cu_(), entity.func_226281_cx_() + 4.0D, 
/*  71 */               getLength(), getHeight(), getThickness(), getWallBlock(), getGriefingRule()));
/*  72 */         this.posList.addAll(AbilityHelper.createFilledCube(entity.field_70170_p, entity.func_226277_ct_() - 4.0D, entity.func_226278_cu_(), entity.func_226281_cx_() + 2.0D, 
/*  73 */               getThickness(), getHeight(), getLength(), getWallBlock(), getGriefingRule()));
/*     */       }
/*  75 */       else if (dir == Direction8.EAST) {
/*  76 */         this.posList.addAll(AbilityHelper.createFilledCube(entity.field_70170_p, entity.func_226277_ct_() + 4.0D, entity
/*  77 */               .func_226278_cu_(), entity.func_226281_cx_(), getThickness(), getHeight(), getLength(), getWallBlock(), getGriefingRule()));
/*     */       }
/*  79 */       else if (dir == Direction8.WEST) {
/*  80 */         this.posList.addAll(AbilityHelper.createFilledCube(entity.field_70170_p, entity.func_226277_ct_() - 4.0D, entity
/*  81 */               .func_226278_cu_(), entity.func_226281_cx_(), getThickness(), getHeight(), getLength(), getWallBlock(), getGriefingRule()));
/*     */       } 
/*     */     }
/*     */     
/*  85 */     if (stopAfterUse()) {
/*  86 */       this.continuousComponent.stopContinuity(entity);
/*     */     }
/*     */   }
/*     */   
/*     */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/*  91 */     if (!stopAfterUse()) {
/*  92 */       for (BlockPos pos : this.posList) {
/*  93 */         Block currentBlock = entity.field_70170_p.func_180495_p(pos).func_177230_c();
/*  94 */         if (currentBlock == getWallBlock()) {
/*  95 */           entity.field_70170_p.func_175656_a(pos, Blocks.field_150350_a.func_176223_P());
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 100 */     this.posList.clear();
/*     */   }
/*     */   
/*     */   protected int getHoldTime() {
/* 104 */     return -1;
/*     */   }
/*     */   
/*     */   public abstract int getThickness();
/*     */   
/*     */   public abstract int getHeight();
/*     */   
/*     */   public abstract int getLength();
/*     */   
/*     */   public abstract Block getWallBlock();
/*     */   
/*     */   public abstract BlockProtectionRule getGriefingRule();
/*     */   
/*     */   public abstract boolean stopAfterUse();
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\WallAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */