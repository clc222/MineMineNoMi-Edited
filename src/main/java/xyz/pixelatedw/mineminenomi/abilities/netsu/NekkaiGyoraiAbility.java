/*    */ package xyz.pixelatedw.mineminenomi.abilities.netsu;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.RepeaterAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AnimationComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.netsu.NekkaiGyoraiProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAnimations;
/*    */ 
/*    */ public class NekkaiGyoraiAbility extends RepeaterAbility2 {
/* 22 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "nekkai_gyorai", new Pair[] {
/* 23 */         (Pair)ImmutablePair.of("Shoots heat-torpedoes, exploding and setting the enemy on fire.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 100;
/*    */   private static final int TRIGGERS = 5;
/*    */   private static final int INTERVAL = 5;
/* 29 */   public static final AbilityCore<NekkaiGyoraiAbility> INSTANCE = (new AbilityCore.Builder("Nekkai Gyorai", AbilityCategory.DEVIL_FRUITS, NekkaiGyoraiAbility::new))
/* 30 */     .addDescriptionLine(DESCRIPTION)
/* 31 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F)
/* 32 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 33 */     .setSourceHakiNature(SourceHakiNature.SPECIAL)
/* 34 */     .build();
/*    */   
/* 36 */   private final AnimationComponent animationComponent = new AnimationComponent((IAbility)this);
/*    */   
/*    */   public NekkaiGyoraiAbility(AbilityCore<NekkaiGyoraiAbility> core) {
/* 39 */     super(core);
/*    */     
/* 41 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.animationComponent });
/*    */     
/* 43 */     this.continuousComponent.addStartEvent(this::startContinuityEvent);
/* 44 */     this.continuousComponent.addEndEvent(this::endContinuityEvent);
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 48 */     this.animationComponent.start(entity, ModAnimations.AIM_SNIPER);
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 52 */     this.animationComponent.stop(entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxTriggers() {
/* 57 */     return 5;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getTriggerInterval() {
/* 62 */     return 5;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getRepeaterCooldown() {
/* 67 */     return 100.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public NekkaiGyoraiProjectile getProjectileFactory(LivingEntity entity) {
/* 72 */     NekkaiGyoraiProjectile proj = new NekkaiGyoraiProjectile(entity.field_70170_p, entity, (Ability)this);
/* 73 */     return proj;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\netsu\NekkaiGyoraiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */