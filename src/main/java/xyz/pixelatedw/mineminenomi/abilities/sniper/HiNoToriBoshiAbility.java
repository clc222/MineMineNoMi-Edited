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
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.sniper.HiNoToriBoshiProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ 
/*    */ public class HiNoToriBoshiAbility extends Ability {
/* 29 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "hi_no_tori_boshi", new Pair[] {
/* 30 */         (Pair)ImmutablePair.of("Fires a flaming pellet in the form of a phoenix, that sets the target on fire", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 100.0F;
/* 34 */   public static final AbilityCore<HiNoToriBoshiAbility> INSTANCE = (new AbilityCore.Builder("Hi no Tori Boshi", AbilityCategory.STYLE, HiNoToriBoshiAbility::new))
/* 35 */     .addDescriptionLine(DESCRIPTION)
/* 36 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F), ContinuousComponent.getTooltip()
/* 37 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 38 */     .setSourceHakiNature(SourceHakiNature.IMBUING)
/* 39 */     .setSourceElement(SourceElement.FIRE)
/* 40 */     .setSourceType(new SourceType[] { SourceType.BULLET
/* 41 */       }).setUnlockCheck(HiNoToriBoshiAbility::canUnlock)
/* 42 */     .build();
/*    */   
/* 44 */   private final ContinuousComponent continuousComponent = new ContinuousComponent((IAbility)this);
/* 45 */   private final BowTriggerComponent bowTriggerComponent = (new BowTriggerComponent((IAbility)this)).addShootEvent(this::shoot);
/* 46 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*    */   
/*    */   public HiNoToriBoshiAbility(AbilityCore<HiNoToriBoshiAbility> core) {
/* 49 */     super(core);
/*    */     
/* 51 */     this.isNew = true;
/* 52 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.bowTriggerComponent, (AbilityComponent)this.projectileComponent });
/*    */     
/* 54 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   public void onUseEvent(LivingEntity entity, IAbility ability) {
/* 58 */     this.continuousComponent.triggerContinuity(entity);
/*    */   }
/*    */   
/*    */   public boolean shoot(LivingEntity entity, IAbility ability) {
/* 62 */     if (this.continuousComponent.isContinuous()) {
/* 63 */       boolean isHitScan = HissatsuAbility.checkHitScan(entity);
/* 64 */       this.projectileComponent.setHitScan(isHitScan);
/* 65 */       this.projectileComponent.shoot(entity, 5.0F, 1.0F);
/* 66 */       this.continuousComponent.stopContinuity(entity);
/* 67 */       this.cooldownComponent.startCooldown(entity, 100.0F);
/* 68 */       return true;
/*    */     } 
/* 70 */     return false;
/*    */   }
/*    */   
/*    */   public HiNoToriBoshiProjectile createProjectile(LivingEntity entity) {
/* 74 */     HiNoToriBoshiProjectile proj = new HiNoToriBoshiProjectile(entity.field_70170_p, entity);
/* 75 */     return proj;
/*    */   }
/*    */   
/*    */   private static boolean canUnlock(LivingEntity entity) {
/* 79 */     if (!(entity instanceof PlayerEntity)) {
/* 80 */       return false;
/*    */     }
/*    */     
/* 83 */     PlayerEntity player = (PlayerEntity)entity;
/* 84 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 85 */     IQuestData questProps = QuestDataCapability.get(player);
/*    */     
/* 87 */     return (props.isSniper() && questProps.hasFinishedQuest(ModQuests.SNIPER_TRIAL_01));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\sniper\HiNoToriBoshiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */