/*    */ package xyz.pixelatedw.mineminenomi.abilities.brawler;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.MoverType;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTrackerComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*    */ 
/*    */ public class TackleAbility
/*    */   extends Ability
/*    */ {
/* 31 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "tackle", new Pair[] {
/* 32 */         (Pair)ImmutablePair.of("Tackles all enemies in front of the user.", null)
/*    */       });
/*    */   
/*    */   private static final float HOLD_TIME = 10.0F;
/*    */   private static final float COOLDOWN = 200.0F;
/*    */   private static final float RANGE = 1.2F;
/*    */   private static final float DAMAGE = 15.0F;
/* 39 */   public static final AbilityCore<TackleAbility> INSTANCE = (new AbilityCore.Builder("Tackle", AbilityCategory.STYLE, TackleAbility::new))
/* 40 */     .addDescriptionLine(DESCRIPTION)
/* 41 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F), RangeComponent.getTooltip(1.2F, RangeComponent.RangeType.LINE), DealDamageComponent.getTooltip(15.0F)
/* 42 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/* 43 */     .setSourceType(new SourceType[] { SourceType.PHYSICAL
/* 44 */       }).build();
/*    */   
/* 46 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/* 47 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/* 48 */   private final HitTrackerComponent hitTrackerComponent = new HitTrackerComponent((IAbility)this);
/* 49 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addStartEvent(100, this::startContinuityEvent).addTickEvent(100, this::tickContinuityEvent).addEndEvent(100, this::endContinuityEvent);
/* 50 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*    */   
/*    */   public TackleAbility(AbilityCore<TackleAbility> core) {
/* 53 */     super(core);
/*    */     
/* 55 */     this.isNew = true;
/* 56 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.hitTrackerComponent, (AbilityComponent)this.animationComponent });
/*    */     
/* 58 */     addCanUseCheck(AbilityHelper::canUseMomentumAbilities);
/* 59 */     addCanUseCheck(AbilityHelper::requiresOnGround);
/* 60 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 64 */     this.continuousComponent.startContinuity(entity, 10.0F);
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 68 */     this.hitTrackerComponent.clearHits();
/* 69 */     this.animationComponent.start(entity, ModAnimations.TACKLE);
/*    */   }
/*    */   
/*    */   private void tickContinuityEvent(LivingEntity entity, IAbility ability) {
/* 73 */     if (entity.func_70089_S()) {
/* 74 */       Vector3d look = entity.func_70040_Z();
/* 75 */       Vector3d speed = look.func_216372_d(2.3D, 0.0D, 2.3D);
/* 76 */       entity.func_213315_a(MoverType.SELF, speed);
/*    */       
/* 78 */       List<LivingEntity> targets = this.rangeComponent.getTargetsInLine(entity, 1.0F, 1.2F);
/*    */       
/* 80 */       for (LivingEntity target : targets) {
/* 81 */         if (this.hitTrackerComponent.canHit((Entity)target)) {
/* 82 */           this.dealDamageComponent.hurtTarget(entity, target, 15.0F);
/*    */         }
/*    */       } 
/*    */       
/* 86 */       for (Entity target : this.hitTrackerComponent.getHits()) {
/* 87 */         target.func_70634_a(entity.func_226277_ct_() + look.field_72450_a, entity.func_226278_cu_(), entity.func_226281_cx_() + look.field_72449_c);
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 93 */     this.animationComponent.stop(entity);
/* 94 */     this.cooldownComponent.startCooldown(entity, 200.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\brawler\TackleAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */