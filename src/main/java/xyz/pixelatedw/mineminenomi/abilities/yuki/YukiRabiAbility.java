/*    */ package xyz.pixelatedw.mineminenomi.abilities.yuki;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RepeaterComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.yuki.YukiRabiProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ public class YukiRabiAbility extends Ability {
/* 22 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "yuki_rabi", new Pair[] {
/* 23 */         (Pair)ImmutablePair.of("Launches numerous hardened snowballs, shaped like a rabbit's head, that can inflict %s on their enemies.", new Object[] { ModEffects.FROSTBITE })
/*    */       });
/* 25 */   public static final AbilityCore<YukiRabiAbility> INSTANCE = (new AbilityCore.Builder("Yuki Rabi", AbilityCategory.DEVIL_FRUITS, YukiRabiAbility::new))
/* 26 */     .addDescriptionLine(DESCRIPTION)
/* 27 */     .setSourceHakiNature(SourceHakiNature.IMBUING)
/* 28 */     .setSourceElement(SourceElement.ICE)
/* 29 */     .build();
/*    */   
/* 31 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addStartEvent(this::onContinuityStart);
/* 32 */   private final RepeaterComponent repeaterComponent = (new RepeaterComponent((IAbility)this)).addTriggerEvent(this::onRepeaterTrigger).addStopEvent(this::onRepeaterStop);
/* 33 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*    */   
/*    */   private static final float COOLDOWN = 120.0F;
/*    */   
/*    */   public YukiRabiAbility(AbilityCore<YukiRabiAbility> core) {
/* 38 */     super(core);
/*    */     
/* 40 */     this.isNew = true;
/*    */     
/* 42 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.repeaterComponent, (AbilityComponent)this.projectileComponent });
/*    */     
/* 44 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 48 */     if (this.continuousComponent.isContinuous()) {
/* 49 */       this.repeaterComponent.stop(entity);
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/* 54 */     this.continuousComponent.triggerContinuity(entity);
/*    */   }
/*    */   
/*    */   private void onContinuityStart(LivingEntity entity, IAbility ability) {
/* 58 */     if (entity.field_70170_p.field_72995_K) {
/*    */       return;
/*    */     }
/*    */     
/* 62 */     this.repeaterComponent.start(entity, 6, 3);
/*    */   }
/*    */   
/*    */   private void onRepeaterTrigger(LivingEntity entity, IAbility ability) {
/* 66 */     this.projectileComponent.shoot(entity, 2.0F, 2.0F);
/*    */   }
/*    */   
/*    */   private void onRepeaterStop(LivingEntity entity, IAbility ability) {
/* 70 */     this.continuousComponent.stopContinuity(entity);
/*    */     
/* 72 */     this.cooldownComponent.startCooldown(entity, 120.0F);
/*    */   }
/*    */   
/*    */   private YukiRabiProjectile createProjectile(LivingEntity entity) {
/* 76 */     return new YukiRabiProjectile(entity.field_70170_p, entity, this);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\yuki\YukiRabiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */