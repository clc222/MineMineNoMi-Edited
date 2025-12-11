/*     */ package xyz.pixelatedw.mineminenomi.abilities.kage;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.StackComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.ability.DoppelmanEntity;
/*     */ 
/*     */ public class DoppelmanAbility
/*     */   extends Ability {
/*     */   private static final int HOLD_TIME = 12000;
/*     */   private static final int MIN_COOLDOWN = 40;
/*     */   private static final int MAX_COOLDOWN = 6000;
/*  28 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "doppelman", new Pair[] {
/*  29 */         (Pair)ImmutablePair.of("Creates a living version of the user's shadow to help them fight", null)
/*     */       });
/*  31 */   public static final AbilityCore<DoppelmanAbility> INSTANCE = (new AbilityCore.Builder("Doppelman", AbilityCategory.DEVIL_FRUITS, DoppelmanAbility::new))
/*  32 */     .addDescriptionLine(DESCRIPTION)
/*  33 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(40.0F, 6000.0F), ContinuousComponent.getTooltip()
/*  34 */       }).build();
/*     */   
/*  36 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addStartEvent(100, this::startContinuityEvent).addTickEvent(100, this::onTickEvent).addEndEvent(100, this::stopContinuityEvent);
/*  37 */   private final StackComponent stackComponent = new StackComponent((IAbility)this);
/*     */   
/*  39 */   private DoppelmanEntity doppelman = null;
/*  40 */   private int shadowsUsed = 0;
/*  41 */   private int prevShadowsUsed = 0;
/*     */   
/*     */   public DoppelmanAbility(AbilityCore<DoppelmanAbility> core) {
/*  44 */     super(core);
/*     */     
/*  46 */     this.isNew = true;
/*  47 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.stackComponent });
/*     */     
/*  49 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  53 */     this.continuousComponent.triggerContinuity(entity);
/*     */   }
/*     */   
/*     */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/*  57 */     this.doppelman = new DoppelmanEntity(entity.field_70170_p, entity);
/*  58 */     this.doppelman.func_70012_b(entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), entity.field_70177_z, entity.field_70125_A);
/*     */     
/*  60 */     if (!entity.field_70170_p.field_72995_K) {
/*  61 */       this.doppelman.setShadow(this.shadowsUsed);
/*  62 */       this.stackComponent.setStacks(entity, (IAbility)this, this.shadowsUsed);
/*     */     } 
/*     */     
/*  65 */     entity.field_70170_p.func_217376_c((Entity)this.doppelman);
/*     */   }
/*     */   
/*     */   private void onTickEvent(LivingEntity entity, IAbility ability) {
/*  69 */     if (this.doppelman == null || !this.doppelman.func_70089_S()) {
/*  70 */       this.continuousComponent.stopContinuity(entity);
/*     */       
/*     */       return;
/*     */     } 
/*  74 */     this.shadowsUsed = this.doppelman.getShadows();
/*  75 */     if (this.shadowsUsed != this.prevShadowsUsed) {
/*  76 */       this.stackComponent.setStacks(entity, (IAbility)this, this.shadowsUsed);
/*  77 */       this.prevShadowsUsed = this.shadowsUsed;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void stopContinuityEvent(LivingEntity entity, IAbility ability) {
/*  82 */     if (this.doppelman != null) {
/*  83 */       this.doppelman.func_70106_y();
/*     */     }
/*     */     
/*  86 */     this.prevShadowsUsed = 0;
/*     */     
/*  88 */     float cooldown = MathHelper.func_76131_a(this.continuousComponent.getContinueTime(), 40.0F, 6000.0F);
/*  89 */     this.cooldownComponent.startCooldown(entity, cooldown);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public DoppelmanEntity getDoppelman() {
/*  94 */     return this.doppelman;
/*     */   }
/*     */   
/*     */   public void doppelmanDeathTrigger(LivingEntity owner) {
/*  98 */     this.shadowsUsed = 0;
/*  99 */     this.stackComponent.setStacks(owner, (IAbility)this, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public CompoundNBT save(CompoundNBT nbt) {
/* 104 */     nbt = super.save(nbt);
/* 105 */     nbt.func_74768_a("shadows", this.shadowsUsed);
/* 106 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(CompoundNBT nbt) {
/* 111 */     super.load(nbt);
/* 112 */     this.shadowsUsed = nbt.func_74762_e("shadows");
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\kage\DoppelmanAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */