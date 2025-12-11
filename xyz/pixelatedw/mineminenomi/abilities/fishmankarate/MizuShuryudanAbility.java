/*    */ package xyz.pixelatedw.mineminenomi.abilities.fishmankarate;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RepeaterComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.fishmankarate.MizuShuryudanProjectile;
/*    */ 
/*    */ public class MizuShuryudanAbility extends Ability {
/* 22 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "mizu_shuryudan", new Pair[] {
/* 23 */         (Pair)ImmutablePair.of("Launches multiple bubbles in the direction the user is looking, these bubbles will linger around until an enemy comes close to them at which point they'll start targeting the enemy and move towards them.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 240;
/* 27 */   public static final AbilityCore<MizuShuryudanAbility> INSTANCE = (new AbilityCore.Builder("Mizu Shuryudan", AbilityCategory.RACIAL, MizuShuryudanAbility::new))
/* 28 */     .addDescriptionLine(DESCRIPTION)
/* 29 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(240.0F)
/* 30 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 31 */     .setSourceHakiNature(SourceHakiNature.HARDENING)
/* 32 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 33 */       }).build();
/*    */   
/* 35 */   private final RepeaterComponent repeaterComponent = (new RepeaterComponent((IAbility)this)).addTriggerEvent(100, this::triggerRepeaterEvent).addStopEvent(100, this::stopRepeaterEvent);
/* 36 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*    */   
/* 38 */   private int projectilesSpawned = 5;
/*    */   
/*    */   public MizuShuryudanAbility(AbilityCore<MizuShuryudanAbility> core) {
/* 41 */     super(core);
/*    */     
/* 43 */     this.isNew = true;
/* 44 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.projectileComponent, (AbilityComponent)this.repeaterComponent });
/*    */     
/* 46 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 50 */     this.repeaterComponent.start(entity, this.projectilesSpawned, 5);
/*    */   }
/*    */   
/*    */   private void triggerRepeaterEvent(LivingEntity entity, IAbility ability) {
/* 54 */     float speed = 1.0F + entity.func_70681_au().nextFloat();
/* 55 */     this.projectileComponent.shoot(entity, speed, 10.0F);
/*    */   }
/*    */   
/*    */   private void stopRepeaterEvent(LivingEntity entity, IAbility ability) {
/* 59 */     this.cooldownComponent.startCooldown(entity, 240.0F);
/*    */   }
/*    */   
/*    */   private MizuShuryudanProjectile createProjectile(LivingEntity entity) {
/* 63 */     MizuShuryudanProjectile projectile = new MizuShuryudanProjectile(entity.field_70170_p, entity);
/* 64 */     return projectile;
/*    */   }
/*    */   
/*    */   public void setProjectilesNumber(int projectiles) {
/* 68 */     this.projectilesSpawned = projectiles;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\fishmankarate\MizuShuryudanAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */