/*    */ package xyz.pixelatedw.mineminenomi.abilities.sniper;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.BowTriggerComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.sniper.SakuretsuSabotenBoshiProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ 
/*    */ public class SakuretsuSabotenBoshiAbility extends Ability {
/* 28 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "sakuretsu_saboten_boshi", new Pair[] {
/* 29 */         (Pair)ImmutablePair.of("The fired projectile explodes and attaches several cacti to the target, dealing damage over time", null)
/*    */       });
/*    */   
/*    */   private static final float DISTANCE = 40.0F;
/*    */   private static final float COOLDOWN = 200.0F;
/* 34 */   public static final AbilityCore<SakuretsuSabotenBoshiAbility> INSTANCE = (new AbilityCore.Builder("Sakuretsu Saboten Boshi", AbilityCategory.STYLE, SakuretsuSabotenBoshiAbility::new))
/* 35 */     .addDescriptionLine(DESCRIPTION)
/* 36 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F), ContinuousComponent.getTooltip()
/* 37 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 38 */     .setSourceHakiNature(SourceHakiNature.IMBUING)
/* 39 */     .setSourceType(new SourceType[] { SourceType.BULLET
/* 40 */       }).setUnlockCheck(SakuretsuSabotenBoshiAbility::canUnlock)
/* 41 */     .build();
/*    */   
/* 43 */   private final ContinuousComponent continuousComponent = new ContinuousComponent((IAbility)this);
/* 44 */   private final BowTriggerComponent bowTriggerComponent = (new BowTriggerComponent((IAbility)this)).addShootEvent(this::shoot);
/* 45 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*    */   
/*    */   public SakuretsuSabotenBoshiAbility(AbilityCore<SakuretsuSabotenBoshiAbility> core) {
/* 48 */     super(core);
/*    */     
/* 50 */     this.isNew = true;
/* 51 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.bowTriggerComponent, (AbilityComponent)this.projectileComponent });
/*    */     
/* 53 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   public void onUseEvent(LivingEntity entity, IAbility ability) {
/* 57 */     this.continuousComponent.triggerContinuity(entity);
/*    */   }
/*    */   
/*    */   public boolean shoot(LivingEntity entity, IAbility ability) {
/* 61 */     if (this.continuousComponent.isContinuous()) {
/* 62 */       boolean isHitScan = HissatsuAbility.checkHitScan(entity);
/* 63 */       this.projectileComponent.setHitScan(isHitScan);
/* 64 */       this.projectileComponent.shoot(entity, 4.0F, 1.0F);
/* 65 */       this.continuousComponent.stopContinuity(entity);
/* 66 */       this.cooldownComponent.startCooldown(entity, 200.0F);
/* 67 */       return true;
/*    */     } 
/* 69 */     return false;
/*    */   }
/*    */   
/*    */   public SakuretsuSabotenBoshiProjectile createProjectile(LivingEntity entity) {
/* 73 */     SakuretsuSabotenBoshiProjectile proj = new SakuretsuSabotenBoshiProjectile(entity.field_70170_p, entity);
/* 74 */     return proj;
/*    */   }
/*    */   
/*    */   private static boolean canUnlock(LivingEntity entity) {
/* 78 */     if (!(entity instanceof PlayerEntity)) {
/* 79 */       return false;
/*    */     }
/*    */     
/* 82 */     PlayerEntity player = (PlayerEntity)entity;
/* 83 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 84 */     IQuestData questProps = QuestDataCapability.get(player);
/*    */     
/* 86 */     return (props.isSniper() && questProps.hasFinishedQuest(ModQuests.SNIPER_TRIAL_04));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\sniper\SakuretsuSabotenBoshiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */