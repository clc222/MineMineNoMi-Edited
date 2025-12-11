/*    */ package xyz.pixelatedw.mineminenomi.abilities.mera;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.mera.JujikaProjectile;
/*    */ 
/*    */ public class JujikaAbility extends Ability {
/* 21 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "jujika", new Pair[] {
/* 22 */         (Pair)ImmutablePair.of("Launches a cross-shaped column of fire at the opponent", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 240;
/* 26 */   public static final AbilityCore<JujikaAbility> INSTANCE = (new AbilityCore.Builder("Jujika", AbilityCategory.DEVIL_FRUITS, JujikaAbility::new))
/* 27 */     .addDescriptionLine(DESCRIPTION)
/* 28 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(240.0F)
/* 29 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 30 */     .setSourceHakiNature(SourceHakiNature.SPECIAL)
/* 31 */     .setSourceElement(SourceElement.FIRE)
/* 32 */     .build();
/*    */   
/* 34 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*    */   
/*    */   public JujikaAbility(AbilityCore<JujikaAbility> core) {
/* 37 */     super(core);
/*    */     
/* 39 */     this.isNew = true;
/*    */     
/* 41 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.projectileComponent });
/*    */     
/* 43 */     addCanUseCheck(MeraHelper::canUseMeraAbilities);
/*    */     
/* 45 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 49 */     this.projectileComponent.shoot(entity, 2.0F, 1.0F);
/* 50 */     this.cooldownComponent.startCooldown(entity, 240.0F);
/*    */   }
/*    */   
/*    */   private JujikaProjectile createProjectile(LivingEntity entity) {
/* 54 */     JujikaProjectile proj = new JujikaProjectile(entity.field_70170_p, entity, this);
/* 55 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\mera\JujikaAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */