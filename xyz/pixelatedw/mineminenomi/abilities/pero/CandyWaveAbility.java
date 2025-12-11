/*    */ package xyz.pixelatedw.mineminenomi.abilities.pero;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.pero.CandyWaveProjectile;
/*    */ 
/*    */ public class CandyWaveAbility extends Ability {
/* 20 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "candy_wave", new Pair[] {
/* 21 */         (Pair)ImmutablePair.of("Launches a wave of candy and traps enemies in hardened candy.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 240;
/* 25 */   public static final AbilityCore<CandyWaveAbility> INSTANCE = (new AbilityCore.Builder("Candy Wave", AbilityCategory.DEVIL_FRUITS, CandyWaveAbility::new))
/* 26 */     .addDescriptionLine(DESCRIPTION)
/* 27 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(240.0F)
/* 28 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 29 */     .setSourceHakiNature(SourceHakiNature.IMBUING)
/* 30 */     .build();
/*    */   
/* 32 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*    */   
/*    */   public CandyWaveAbility(AbilityCore<CandyWaveAbility> core) {
/* 35 */     super(core);
/*    */     
/* 37 */     this.isNew = true;
/* 38 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.projectileComponent });
/*    */     
/* 40 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 44 */     this.projectileComponent.shoot(entity, 2.0F, 1.0F);
/* 45 */     this.cooldownComponent.startCooldown(entity, 240.0F);
/*    */   }
/*    */   
/*    */   private CandyWaveProjectile createProjectile(LivingEntity entity) {
/* 49 */     CandyWaveProjectile proj = new CandyWaveProjectile(entity.field_70170_p, entity, this);
/* 50 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\pero\CandyWaveAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */