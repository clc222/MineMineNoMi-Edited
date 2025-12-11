/*    */ package xyz.pixelatedw.mineminenomi.abilities.mera;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.util.Interval;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.mera.HidarumaProjectile;
/*    */ 
/*    */ public class HidarumaAbility extends Ability {
/* 23 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "hidaruma", new Pair[] {
/* 24 */         (Pair)ImmutablePair.of("Creates small green fireballs that set the target on fire", null)
/*    */       });
/*    */   
/*    */   private static final int MAX_THRESHOLD = 60;
/*    */   private static final int MIN_COOLDOWN = 200;
/*    */   private static final int MAX_COOLDOWN = 500;
/*    */   private static final int MAX_FIREFLIES = 30;
/* 31 */   private final Interval fireflyInterval = new Interval(2);
/*    */   
/* 33 */   public static final AbilityCore<HidarumaAbility> INSTANCE = (new AbilityCore.Builder("Hidaruma", AbilityCategory.DEVIL_FRUITS, HidarumaAbility::new))
/* 34 */     .addDescriptionLine(DESCRIPTION)
/* 35 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F, 500.0F)
/* 36 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 37 */     .setSourceHakiNature(SourceHakiNature.SPECIAL)
/* 38 */     .setSourceElement(SourceElement.FIRE)
/* 39 */     .build();
/*    */   
/* 41 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addTickEvent(this::onContinuityTick).addEndEvent(this::onContinuityEnd);
/* 42 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*    */   
/*    */   public HidarumaAbility(AbilityCore<HidarumaAbility> core) {
/* 45 */     super(core);
/*    */     
/* 47 */     this.isNew = true;
/*    */     
/* 49 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.projectileComponent });
/*    */     
/* 51 */     addCanUseCheck(MeraHelper::canUseMeraAbilities);
/*    */     
/* 53 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity player, IAbility ability) {
/* 57 */     this.continuousComponent.triggerContinuity(player, 60.0F);
/*    */   }
/*    */   
/*    */   private void onContinuityTick(LivingEntity player, IAbility ability) {
/* 61 */     if (this.fireflyInterval.canTick()) {
/* 62 */       HidarumaProjectile proj = (HidarumaProjectile)this.projectileComponent.getNewProjectile(player);
/*    */       
/* 64 */       double offsetX = (this.random.nextDouble() - 0.5D) * 0.2D;
/* 65 */       double offsetY = (this.random.nextDouble() - 0.5D) * 0.2D;
/* 66 */       double offsetZ = (this.random.nextDouble() - 0.5D) * 0.2D;
/*    */       
/* 68 */       AbilityHelper.setDeltaMovement((Entity)proj, player.func_70040_Z().func_72432_b().func_186678_a(0.25D).func_72441_c(offsetX, offsetY, offsetZ));
/*    */       
/* 70 */       player.field_70170_p.func_217376_c((Entity)proj);
/*    */     } 
/*    */   }
/*    */   
/*    */   private void onContinuityEnd(LivingEntity player, IAbility ability) {
/* 75 */     this.fireflyInterval.restartIntervalToZero();
/*    */     
/* 77 */     this.cooldownComponent.startCooldown(player, 200.0F + this.continuousComponent.getContinueTime() * 5.0F);
/*    */   }
/*    */   
/*    */   private HidarumaProjectile createProjectile(LivingEntity entity) {
/* 81 */     HidarumaProjectile proj = new HidarumaProjectile(entity.field_70170_p, entity, this);
/*    */     
/* 83 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\mera\HidarumaAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */