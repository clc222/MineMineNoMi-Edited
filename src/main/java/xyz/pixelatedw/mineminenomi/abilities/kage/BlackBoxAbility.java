/*    */ package xyz.pixelatedw.mineminenomi.abilities.kage;
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
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.kage.BlackBoxProjectile;
/*    */ 
/*    */ public class BlackBoxAbility
/*    */   extends Ability
/*    */ {
/*    */   private static final float COOLDOWN = 320.0F;
/* 23 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "black_box", new Pair[] {
/* 24 */         (Pair)ImmutablePair.of("Encases and suffocates the opponent in a box made of shadows.", null)
/*    */       });
/* 26 */   public static final AbilityCore<BlackBoxAbility> INSTANCE = (new AbilityCore.Builder("Black Box", AbilityCategory.DEVIL_FRUITS, BlackBoxAbility::new))
/* 27 */     .addDescriptionLine(DESCRIPTION)
/* 28 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(320.0F)
/* 29 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 30 */     .setSourceHakiNature(SourceHakiNature.IMBUING)
/* 31 */     .build();
/*    */   
/* 33 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*    */   
/*    */   public BlackBoxAbility(AbilityCore<BlackBoxAbility> core) {
/* 36 */     super(core);
/*    */     
/* 38 */     this.isNew = true;
/* 39 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.projectileComponent });
/*    */     
/* 41 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 45 */     this.projectileComponent.shoot(entity, 2.0F, 0.5F);
/* 46 */     this.cooldownComponent.startCooldown(entity, 320.0F);
/*    */   }
/*    */   
/*    */   private BlackBoxProjectile createProjectile(LivingEntity entity) {
/* 50 */     BlackBoxProjectile proj = new BlackBoxProjectile(entity.field_70170_p, entity, this);
/* 51 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\kage\BlackBoxAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */