/*    */ package xyz.pixelatedw.mineminenomi.abilities.chiyu;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.PunchAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HealComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTriggerComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class HealingTouchAbility extends PunchAbility2 {
/* 25 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "healing_touch", new Pair[] {
/* 26 */         (Pair)ImmutablePair.of("Touch the target to heal them", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 200.0F;
/*    */   private static final float HEAL_AMOUNT = 20.0F;
/* 31 */   public static final AbilityCore<HealingTouchAbility> INSTANCE = (new AbilityCore.Builder("Healing Touch", AbilityCategory.DEVIL_FRUITS, HealingTouchAbility::new))
/* 32 */     .addDescriptionLine(DESCRIPTION)
/* 33 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F), HealComponent.getTooltip(20.0F)
/* 34 */       }).setSourceType(new SourceType[] { SourceType.FIST
/* 35 */       }).build();
/*    */   
/* 37 */   private final HealComponent healComponent = new HealComponent((IAbility)this);
/*    */   
/*    */   public HealingTouchAbility(AbilityCore<HealingTouchAbility> core) {
/* 40 */     super(core);
/*    */     
/* 42 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.healComponent });
/*    */     
/* 44 */     this.hitTriggerComponent.setBypassSameGroupProtection();
/* 45 */     this.hitTriggerComponent.addTryHitEvent(80, this::hitEvent);
/*    */   }
/*    */   
/*    */   private HitTriggerComponent.HitResult hitEvent(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/* 49 */     if (canActivate().test(entity)) {
/* 50 */       this.healComponent.healTarget(entity, target, 20.0F);
/*    */       
/* 52 */       target.func_195064_c(new EffectInstance(Effects.field_76428_l, 400, 1));
/*    */       
/* 54 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.HEALING_TOUCH.get(), (Entity)entity, target.func_226277_ct_(), target.func_226278_cu_(), target.func_226281_cx_());
/*    */       
/* 56 */       increaseUses();
/*    */       
/* 58 */       return HitTriggerComponent.HitResult.FAIL;
/*    */     } 
/*    */     
/* 61 */     return HitTriggerComponent.HitResult.PASS;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onHitEffect(LivingEntity entity, LivingEntity target, ModDamageSource source) {
/* 66 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public Predicate<LivingEntity> canActivate() {
/* 71 */     return entity -> this.continuousComponent.isContinuous();
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchCooldown() {
/* 76 */     return 200.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getUseLimit() {
/* 81 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isParallel() {
/* 86 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\chiyu\HealingTouchAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */