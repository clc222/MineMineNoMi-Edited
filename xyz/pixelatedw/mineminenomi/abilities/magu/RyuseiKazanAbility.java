/*    */ package xyz.pixelatedw.mineminenomi.abilities.magu;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.SoundEvent;
/*    */ import net.minecraft.util.math.vector.Vector3d;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RepeaterComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.magu.RyuseiKazanProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ 
/*    */ public class RyuseiKazanAbility extends Ability {
/* 26 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "ryusei_kazan", new Pair[] {
/* 27 */         (Pair)ImmutablePair.of("Functions like 'Dai Funka', but multiple fists are launched at the opponent", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 400.0F;
/* 31 */   public static final AbilityCore<RyuseiKazanAbility> INSTANCE = (new AbilityCore.Builder("Ryusei Kazan", AbilityCategory.DEVIL_FRUITS, RyuseiKazanAbility::new))
/* 32 */     .addDescriptionLine(DESCRIPTION)
/* 33 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(400.0F)
/* 34 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 35 */     .setSourceHakiNature(SourceHakiNature.SPECIAL)
/* 36 */     .setSourceElement(SourceElement.MAGMA)
/* 37 */     .build();
/*    */   
/* 39 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(this::onContinuityStart);
/* 40 */   private final RepeaterComponent repeaterComponent = (new RepeaterComponent((IAbility)this)).addTriggerEvent(this::onRepeaterTrigger).addStopEvent(this::onRepeaterStop);
/* 41 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*    */   
/*    */   public RyuseiKazanAbility(AbilityCore<RyuseiKazanAbility> core) {
/* 44 */     super(core);
/*    */     
/* 46 */     this.isNew = true;
/*    */     
/* 48 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.repeaterComponent, (AbilityComponent)this.projectileComponent });
/*    */     
/* 50 */     addUseEvent(this::onUse);
/*    */   }
/*    */   
/*    */   private void onUse(LivingEntity entity, IAbility ability) {
/* 54 */     if (this.continuousComponent.isContinuous()) {
/* 55 */       this.repeaterComponent.stop(entity);
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/* 60 */     this.continuousComponent.triggerContinuity(entity);
/*    */   }
/*    */   
/*    */   private void onContinuityStart(LivingEntity entity, IAbility ability) {
/* 64 */     this.repeaterComponent.start(entity, 10, 5);
/*    */   }
/*    */   
/*    */   private void onRepeaterTrigger(LivingEntity entity, IAbility ability) {
/* 68 */     Vector3d lookVec = entity.func_70040_Z().func_72432_b();
/*    */     
/* 70 */     RyuseiKazanProjectile proj = (RyuseiKazanProjectile)this.projectileComponent.getNewProjectile(entity);
/*    */     
/* 72 */     if (lookVec.field_72448_b > 0.7D) {
/* 73 */       proj.func_234612_a_((Entity)entity, entity.field_70125_A, entity.field_70177_z, 0.0F, 3.0F, 8.0F);
/* 74 */       proj.setMaxLife(300);
/* 75 */       proj.setGravity(0.05F);
/*    */     } else {
/* 77 */       proj.func_234612_a_((Entity)entity, entity.field_70125_A, entity.field_70177_z, 0.0F, 2.5F, 4.0F);
/*    */     } 
/*    */     
/* 80 */     entity.field_70170_p.func_217376_c((Entity)proj);
/* 81 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.MAGU_SFX.get(), SoundCategory.PLAYERS, 3.0F, 1.0F);
/*    */   }
/*    */   
/*    */   private void onRepeaterStop(LivingEntity entity, IAbility ability) {
/* 85 */     this.continuousComponent.stopContinuity(entity);
/*    */     
/* 87 */     this.cooldownComponent.startCooldown(entity, 400.0F);
/*    */   }
/*    */   
/*    */   private RyuseiKazanProjectile createProjectile(LivingEntity entity) {
/* 91 */     return new RyuseiKazanProjectile(entity.field_70170_p, entity, (IAbility)this);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\magu\RyuseiKazanAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */