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
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.pero.CandyEscalatorProjectile;
/*    */ 
/*    */ public class CandyEscalatorAbility extends Ability {
/* 19 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "candy_escalator", new Pair[] {
/* 20 */         (Pair)ImmutablePair.of("Creates a line made out of candy in front of the user.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 160;
/* 24 */   public static final AbilityCore<CandyEscalatorAbility> INSTANCE = (new AbilityCore.Builder("Candy Escalator", AbilityCategory.DEVIL_FRUITS, CandyEscalatorAbility::new))
/* 25 */     .addDescriptionLine(DESCRIPTION)
/* 26 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(160.0F)
/* 27 */       }).build();
/*    */   
/* 29 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*    */   
/*    */   public CandyEscalatorAbility(AbilityCore<CandyEscalatorAbility> core) {
/* 32 */     super(core);
/*    */     
/* 34 */     this.isNew = true;
/* 35 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.projectileComponent });
/*    */     
/* 37 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 41 */     this.projectileComponent.shoot(entity, 2.0F, 1.0F);
/* 42 */     this.cooldownComponent.startCooldown(entity, 160.0F);
/*    */   }
/*    */   
/*    */   private CandyEscalatorProjectile createProjectile(LivingEntity entity) {
/* 46 */     CandyEscalatorProjectile proj = new CandyEscalatorProjectile(entity.field_70170_p, entity);
/* 47 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\pero\CandyEscalatorAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */