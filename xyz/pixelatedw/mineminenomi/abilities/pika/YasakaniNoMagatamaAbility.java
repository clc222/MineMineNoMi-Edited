/*    */ package xyz.pixelatedw.mineminenomi.abilities.pika;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.SoundEvent;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RepeaterComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.pika.YasakaniNoMagatamaProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ 
/*    */ public class YasakaniNoMagatamaAbility extends Ability {
/* 28 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "yasakani_no_magatama", new Pair[] {
/* 29 */         (Pair)ImmutablePair.of("Fires a torrent of deadly light particles, causing huge destruction", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 280;
/* 33 */   public static final AbilityCore<YasakaniNoMagatamaAbility> INSTANCE = (new AbilityCore.Builder("Yasakani no Magatama", AbilityCategory.DEVIL_FRUITS, YasakaniNoMagatamaAbility::new))
/* 34 */     .addDescriptionLine(DESCRIPTION)
/* 35 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(280.0F)
/* 36 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 37 */     .setSourceHakiNature(SourceHakiNature.SPECIAL)
/* 38 */     .setSourceElement(SourceElement.LIGHT)
/* 39 */     .build();
/*    */   
/* 41 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addStartEvent(this::startContinuityEvent).addTickEvent(this::duringContinuityEvent);
/* 42 */   private final RepeaterComponent repeaterComponent = (new RepeaterComponent((IAbility)this)).addTriggerEvent(this::repeaterTriggerEvent).addStopEvent(this::repeaterStopEvent);
/* 43 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/* 44 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*    */   
/*    */   public YasakaniNoMagatamaAbility(AbilityCore<YasakaniNoMagatamaAbility> core) {
/* 47 */     super(core);
/*    */     
/* 49 */     this.isNew = true;
/*    */     
/* 51 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.repeaterComponent, (AbilityComponent)this.animationComponent, (AbilityComponent)this.projectileComponent });
/*    */     
/* 53 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 57 */     if (this.continuousComponent.isContinuous()) {
/* 58 */       this.repeaterComponent.stop(entity);
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/* 63 */     this.continuousComponent.triggerContinuity(entity);
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 67 */     if (entity.field_70170_p.field_72995_K) {
/*    */       return;
/*    */     }
/*    */     
/* 71 */     this.animationComponent.start(entity, ModAnimations.POINT_RIGHT_ARM);
/* 72 */     this.repeaterComponent.start(entity, 25, 3);
/*    */   }
/*    */   
/*    */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/* 76 */     entity.func_195064_c(new EffectInstance((Effect)ModEffects.MOVEMENT_BLOCKED.get(), 5, 1, false, false));
/* 77 */     AbilityHelper.slowEntityFall(entity);
/*    */   }
/*    */   
/*    */   private void repeaterTriggerEvent(LivingEntity entity, IAbility ability) {
/* 81 */     if (canUse(entity).isFail()) {
/* 82 */       this.repeaterComponent.stop(entity);
/*    */     }
/*    */     
/* 85 */     this.projectileComponent.shoot(entity, 10.5F, 20.0F);
/* 86 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.PIKA_SFX.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/*    */   }
/*    */   
/*    */   private void repeaterStopEvent(LivingEntity entity, IAbility ability) {
/* 90 */     this.continuousComponent.stopContinuity(entity);
/*    */     
/* 92 */     this.animationComponent.stop(entity);
/* 93 */     this.cooldownComponent.startCooldown(entity, 280.0F);
/*    */   }
/*    */   
/*    */   private YasakaniNoMagatamaProjectile createProjectile(LivingEntity entity) {
/* 97 */     YasakaniNoMagatamaProjectile proj = new YasakaniNoMagatamaProjectile(entity.field_70170_p, entity, this);
/* 98 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\pika\YasakaniNoMagatamaAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */