/*    */ package xyz.pixelatedw.mineminenomi.abilities.artofweather;
/*    */ 
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
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RepeaterComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather.GustSwordProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ 
/*    */ public class GustSwordAbility extends Ability {
/* 29 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "gust_sword", new Pair[] {
/* 30 */         (Pair)ImmutablePair.of("Fires a concentrated wind blast forward, knocking back all enemies that it comes in contact with", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 10.0F;
/* 34 */   public static final AbilityCore<GustSwordAbility> INSTANCE = (new AbilityCore.Builder("Gust Sword", AbilityCategory.STYLE, GustSwordAbility::new))
/* 35 */     .addDescriptionLine(DESCRIPTION)
/* 36 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(10.0F)
/* 37 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/* 38 */     .setSourceHakiNature(SourceHakiNature.SPECIAL)
/* 39 */     .setSourceElement(SourceElement.AIR)
/* 40 */     .setSourceHakiNature(SourceHakiNature.IMBUING)
/* 41 */     .setUnlockCheck(GustSwordAbility::canUnlock)
/* 42 */     .build();
/*    */   
/* 44 */   private final RepeaterComponent repeaterComponent = (new RepeaterComponent((IAbility)this)).addTriggerEvent(this::triggerRepeaterEvent).addStopEvent(this::endRepeaterEvent);
/* 45 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(100, this::startContinuousEvent).addEndEvent(100, this::endContinuousEvent);
/* 46 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*    */   
/*    */   public GustSwordAbility(AbilityCore<GustSwordAbility> core) {
/* 49 */     super(core);
/*    */     
/* 51 */     this.isNew = true;
/* 52 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.repeaterComponent, (AbilityComponent)this.projectileComponent, (AbilityComponent)this.continuousComponent });
/*    */     
/* 54 */     addCanUseCheck(ArtOfWeatherHelper::needsSorceryClimaTact);
/* 55 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 59 */     this.continuousComponent.triggerContinuity(entity);
/*    */   }
/*    */   
/*    */   private void startContinuousEvent(LivingEntity entity, IAbility ability) {
/* 63 */     this.repeaterComponent.start(entity, 6, 3);
/*    */   }
/*    */   
/*    */   private void endContinuousEvent(LivingEntity entity, IAbility ability) {
/* 67 */     this.repeaterComponent.stop(entity);
/* 68 */     this.cooldownComponent.startCooldown(entity, 10.0F);
/*    */   }
/*    */   
/*    */   private void triggerRepeaterEvent(LivingEntity entity, IAbility ability) {
/* 72 */     this.projectileComponent.shoot(entity, 2.0F, 3.0F);
/*    */   }
/*    */   
/*    */   private void endRepeaterEvent(LivingEntity entity, IAbility ability) {
/* 76 */     this.continuousComponent.stopContinuity(entity);
/*    */   }
/*    */   
/*    */   private GustSwordProjectile createProjectile(LivingEntity entity) {
/* 80 */     GustSwordProjectile proj = new GustSwordProjectile(entity.field_70170_p, entity);
/* 81 */     return proj;
/*    */   }
/*    */   
/*    */   private static boolean canUnlock(LivingEntity entity) {
/* 85 */     if (!(entity instanceof PlayerEntity)) {
/* 86 */       return false;
/*    */     }
/*    */     
/* 89 */     PlayerEntity player = (PlayerEntity)entity;
/* 90 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 91 */     IQuestData questProps = QuestDataCapability.get(player);
/*    */     
/* 93 */     return (props.isWeatherWizard() && questProps.hasFinishedQuest(ModQuests.ART_OF_WEATHER_TRIAL_04));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\artofweather\GustSwordAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */