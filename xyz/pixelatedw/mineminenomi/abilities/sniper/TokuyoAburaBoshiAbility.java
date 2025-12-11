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
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.sniper.TokuyoAburaBoshiProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ 
/*    */ public class TokuyoAburaBoshiAbility extends Ability {
/* 28 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "tokuyo_abura_boshi", new Pair[] {
/* 29 */         (Pair)ImmutablePair.of("Fires a pellet that upon impact releases oil coating the target in it and making the ground slippery", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 140.0F;
/* 33 */   public static final AbilityCore<TokuyoAburaBoshiAbility> INSTANCE = (new AbilityCore.Builder("Tokuyo Abura Boshi", AbilityCategory.STYLE, TokuyoAburaBoshiAbility::new))
/* 34 */     .addDescriptionLine(DESCRIPTION)
/* 35 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(140.0F), ContinuousComponent.getTooltip()
/* 36 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 37 */     .setSourceHakiNature(SourceHakiNature.IMBUING)
/* 38 */     .setSourceType(new SourceType[] { SourceType.BULLET
/* 39 */       }).setUnlockCheck(TokuyoAburaBoshiAbility::canUnlock)
/* 40 */     .build();
/*    */   
/* 42 */   private final ContinuousComponent continuousComponent = new ContinuousComponent((IAbility)this);
/* 43 */   private final BowTriggerComponent bowTriggerComponent = (new BowTriggerComponent((IAbility)this)).addShootEvent(this::shoot);
/* 44 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*    */   
/*    */   public TokuyoAburaBoshiAbility(AbilityCore<TokuyoAburaBoshiAbility> core) {
/* 47 */     super(core);
/*    */     
/* 49 */     this.isNew = true;
/* 50 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.bowTriggerComponent, (AbilityComponent)this.projectileComponent });
/*    */     
/* 52 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   public void onUseEvent(LivingEntity entity, IAbility ability) {
/* 56 */     this.continuousComponent.triggerContinuity(entity);
/*    */   }
/*    */   
/*    */   public boolean shoot(LivingEntity entity, IAbility ability) {
/* 60 */     if (this.continuousComponent.isContinuous()) {
/* 61 */       boolean isHitScan = HissatsuAbility.checkHitScan(entity);
/* 62 */       this.projectileComponent.setHitScan(isHitScan);
/* 63 */       this.projectileComponent.shoot(entity, 4.0F, 1.0F);
/* 64 */       this.continuousComponent.stopContinuity(entity);
/* 65 */       this.cooldownComponent.startCooldown(entity, 140.0F);
/* 66 */       return true;
/*    */     } 
/* 68 */     return false;
/*    */   }
/*    */   
/*    */   public TokuyoAburaBoshiProjectile createProjectile(LivingEntity entity) {
/* 72 */     TokuyoAburaBoshiProjectile proj = new TokuyoAburaBoshiProjectile(entity.field_70170_p, entity);
/* 73 */     return proj;
/*    */   }
/*    */   
/*    */   private static boolean canUnlock(LivingEntity entity) {
/* 77 */     if (!(entity instanceof PlayerEntity)) {
/* 78 */       return false;
/*    */     }
/*    */     
/* 81 */     PlayerEntity player = (PlayerEntity)entity;
/* 82 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 83 */     IQuestData questProps = QuestDataCapability.get(player);
/*    */     
/* 85 */     return (props.isSniper() && questProps.hasFinishedQuest(ModQuests.SNIPER_TRIAL_03));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\sniper\TokuyoAburaBoshiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */