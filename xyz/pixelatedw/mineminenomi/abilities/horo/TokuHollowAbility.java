/*    */ package xyz.pixelatedw.mineminenomi.abilities.horo;
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
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.horo.TokuHollowProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class TokuHollowAbility extends Ability {
/* 21 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "toku_hollow", new Pair[] {
/* 22 */         (Pair)ImmutablePair.of("Creates a huge ghost that causes a massive explosion upon impact.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 300;
/* 26 */   public static final AbilityCore<TokuHollowAbility> INSTANCE = (new AbilityCore.Builder("Toku Hollow", AbilityCategory.DEVIL_FRUITS, TokuHollowAbility::new))
/* 27 */     .addDescriptionLine(DESCRIPTION)
/* 28 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(300.0F)
/* 29 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 30 */     .setSourceHakiNature(SourceHakiNature.IMBUING)
/* 31 */     .build();
/*    */   
/* 33 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*    */   
/*    */   public TokuHollowAbility(AbilityCore<TokuHollowAbility> core) {
/* 36 */     super(core);
/*    */     
/* 38 */     this.isNew = true;
/*    */     
/* 40 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.projectileComponent });
/*    */     
/* 42 */     addUseEvent(this::onUse);
/*    */   }
/*    */   
/*    */   private void onUse(LivingEntity entity, IAbility ability) {
/* 46 */     this.projectileComponent.shoot(entity);
/*    */     
/* 48 */     this.cooldownComponent.startCooldown(entity, WyHelper.secondsToTicks(15.0F));
/*    */   }
/*    */   
/*    */   private TokuHollowProjectile createProjectile(LivingEntity entity) {
/* 52 */     TokuHollowProjectile proj = new TokuHollowProjectile(entity.field_70170_p, entity, this);
/*    */     
/* 54 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\horo\TokuHollowAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */