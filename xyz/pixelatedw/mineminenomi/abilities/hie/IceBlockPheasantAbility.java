/*    */ package xyz.pixelatedw.mineminenomi.abilities.hie;
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
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.hie.IceBlockPheasantProjectile;
/*    */ 
/*    */ public class IceBlockPheasantAbility extends Ability {
/* 21 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "ice_block_pheasant", new Pair[] {
/* 22 */         (Pair)ImmutablePair.of("Releases a massive wave of ice in the shape of a pheasant", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 500;
/* 26 */   public static final AbilityCore<IceBlockPheasantAbility> INSTANCE = (new AbilityCore.Builder("Ice Block: Pheasant", AbilityCategory.DEVIL_FRUITS, IceBlockPheasantAbility::new))
/* 27 */     .addDescriptionLine(DESCRIPTION)
/* 28 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(500.0F)
/* 29 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 30 */     .setSourceHakiNature(SourceHakiNature.IMBUING)
/* 31 */     .setSourceElement(SourceElement.ICE)
/* 32 */     .build();
/*    */   
/* 34 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*    */   
/*    */   public IceBlockPheasantAbility(AbilityCore<IceBlockPheasantAbility> core) {
/* 37 */     super(core);
/*    */     
/* 39 */     this.isNew = true;
/*    */     
/* 41 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.projectileComponent });
/*    */     
/* 43 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 47 */     this.projectileComponent.shoot(entity, 2.0F, 0.0F);
/*    */     
/* 49 */     this.cooldownComponent.startCooldown(entity, 500.0F);
/*    */   }
/*    */   
/*    */   private IceBlockPheasantProjectile createProjectile(LivingEntity entity) {
/* 53 */     IceBlockPheasantProjectile proj = new IceBlockPheasantProjectile(entity.field_70170_p, entity, this);
/* 54 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\hie\IceBlockPheasantAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */