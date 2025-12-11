/*    */ package xyz.pixelatedw.mineminenomi.abilities.fishmankarate;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.fishmankarate.YarinamiProjectile;
/*    */ 
/*    */ public class YarinamiAbility extends Ability {
/* 23 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "yarinami", new Pair[] {
/* 24 */         (Pair)ImmutablePair.of("The user fires a densely compressed spear-shaped waterbullet at the opponent", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 240.0F;
/*    */   private static final float CHARGE_TIME = 60.0F;
/*    */   private static final float WATER_DAMAGE_MUL = 1.2F;
/* 30 */   public static final AbilityCore<YarinamiAbility> INSTANCE = (new AbilityCore.Builder("Yarinami", AbilityCategory.RACIAL, YarinamiAbility::new))
/* 31 */     .addDescriptionLine(DESCRIPTION)
/* 32 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(240.0F), ChargeComponent.getTooltip(60.0F), FishmanKarateHelper.getWaterBuffedProjectileDamageStat(1.2F)
/* 33 */       }).setSourceHakiNature(SourceHakiNature.IMBUING)
/* 34 */     .setSourceElement(SourceElement.WATER)
/* 35 */     .setUnlockCheck(YarinamiAbility::canUnlock)
/* 36 */     .build();
/*    */   
/* 38 */   private final ChargeComponent chargeComponent = (new ChargeComponent((IAbility)this)).addEndEvent(100, this::onEndChargeEvent);
/* 39 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*    */   
/*    */   public YarinamiAbility(AbilityCore<YarinamiAbility> core) {
/* 42 */     super(core);
/*    */     
/* 44 */     this.isNew = true;
/* 45 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent, (AbilityComponent)this.projectileComponent });
/*    */     
/* 47 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/* 51 */     this.chargeComponent.startCharging(entity, 60.0F);
/*    */   }
/*    */   
/*    */   private void onEndChargeEvent(LivingEntity entity, IAbility ability) {
/* 55 */     this.projectileComponent.shoot(entity, 3.5F, 0.0F);
/* 56 */     this.cooldownComponent.startCooldown(entity, 240.0F);
/*    */   }
/*    */   
/*    */   private YarinamiProjectile createProjectile(LivingEntity entity) {
/* 60 */     YarinamiProjectile proj = new YarinamiProjectile(entity.field_70170_p, entity);
/* 61 */     if (FishmanKarateHelper.isInWater(entity)) {
/* 62 */       proj.setDamage(proj.getDamage() * 1.2F);
/*    */     }
/* 64 */     return proj;
/*    */   }
/*    */   
/*    */   private static boolean canUnlock(LivingEntity user) {
/* 68 */     IEntityStats props = EntityStatsCapability.get(user);
/* 69 */     return (props.isFishman() && props.getDoriki() >= 5500.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\fishmankarate\YarinamiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */