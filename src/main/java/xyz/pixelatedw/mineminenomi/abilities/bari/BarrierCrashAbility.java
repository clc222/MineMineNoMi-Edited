/*    */ package xyz.pixelatedw.mineminenomi.abilities.bari;
/*    */ 
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
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.bari.BarrierCrashProjectile;
/*    */ 
/*    */ public class BarrierCrashAbility extends Ability {
/* 21 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "barrier_crash", new Pair[] {
/* 22 */         (Pair)ImmutablePair.of("Launches a barrier towards the opponent, smashing it against them.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 160;
/* 26 */   public static final AbilityCore<BarrierCrashAbility> INSTANCE = (new AbilityCore.Builder("Barrier Crash", AbilityCategory.DEVIL_FRUITS, BarrierCrashAbility::new))
/* 27 */     .addDescriptionLine(DESCRIPTION)
/* 28 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(160.0F)
/* 29 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 30 */     .setSourceHakiNature(SourceHakiNature.IMBUING)
/* 31 */     .setSourceType(new SourceType[] { SourceType.BLUNT
/* 32 */       }).build();
/*    */   
/* 34 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*    */   
/*    */   public BarrierCrashAbility(AbilityCore<BarrierCrashAbility> core) {
/* 37 */     super(core);
/*    */     
/* 39 */     this.isNew = true;
/* 40 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.projectileComponent });
/*    */     
/* 42 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 46 */     this.projectileComponent.shoot(entity, 2.0F, 1.0F);
/* 47 */     this.cooldownComponent.startCooldown(entity, 160.0F);
/*    */   }
/*    */   
/*    */   private BarrierCrashProjectile createProjectile(LivingEntity entity) {
/* 51 */     BarrierCrashProjectile proj = new BarrierCrashProjectile(entity.field_70170_p, entity);
/* 52 */     proj.func_70107_b(proj.func_226277_ct_(), proj.func_226278_cu_() + 1.5D, proj.func_226281_cx_());
/* 53 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\bari\BarrierCrashAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */