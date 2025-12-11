/*     */ package xyz.pixelatedw.mineminenomi.abilities.sniper;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.BowTriggerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RepeaterComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.sniper.RenpatsuNamariBoshiProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*     */ 
/*     */ public class RenpatsuNamariBoshiAbility extends Ability {
/*  30 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "renpatsu_namari_boshi", new Pair[] {
/*  31 */         (Pair)ImmutablePair.of("Fires a barrage of exploding pellets", null)
/*     */       });
/*     */   
/*     */   private static final float COOLDOWN = 120.0F;
/*  35 */   public static final AbilityCore<RenpatsuNamariBoshiAbility> INSTANCE = (new AbilityCore.Builder("Renpatsu Namari Boshi", AbilityCategory.STYLE, RenpatsuNamariBoshiAbility::new))
/*  36 */     .addDescriptionLine(DESCRIPTION)
/*  37 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(120.0F), ContinuousComponent.getTooltip()
/*  38 */       }).addAdvancedDescriptionLine(ProjectileComponent.getProjectileTooltips())
/*  39 */     .setSourceHakiNature(SourceHakiNature.IMBUING)
/*  40 */     .setSourceElement(SourceElement.EXPLOSION)
/*  41 */     .setSourceType(new SourceType[] { SourceType.BULLET
/*  42 */       }).setUnlockCheck(RenpatsuNamariBoshiAbility::canUnlock)
/*  43 */     .build();
/*     */   
/*  45 */   private final ContinuousComponent continuousComponent = new ContinuousComponent((IAbility)this);
/*  46 */   private final BowTriggerComponent bowTriggerComponent = (new BowTriggerComponent((IAbility)this)).addShootEvent(this::shoot);
/*  47 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*  48 */   private final RepeaterComponent repeaterComponent = (new RepeaterComponent((IAbility)this)).addTriggerEvent(100, this::triggerRepeaterEvent).addStopEvent(100, this::stopRepeaterEvent);
/*     */   
/*     */   public RenpatsuNamariBoshiAbility(AbilityCore<RenpatsuNamariBoshiAbility> core) {
/*  51 */     super(core);
/*     */     
/*  53 */     this.isNew = true;
/*  54 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.bowTriggerComponent, (AbilityComponent)this.projectileComponent, (AbilityComponent)this.repeaterComponent });
/*     */     
/*  56 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   public void onUseEvent(LivingEntity entity, IAbility ability) {
/*  60 */     if (this.continuousComponent.isContinuous()) {
/*  61 */       this.repeaterComponent.stop(entity);
/*     */       return;
/*     */     } 
/*  64 */     this.continuousComponent.triggerContinuity(entity);
/*     */   }
/*     */   
/*     */   public boolean shoot(LivingEntity entity, IAbility ability) {
/*  68 */     if (this.continuousComponent.isContinuous()) {
/*  69 */       this.repeaterComponent.start(entity, 10, 3);
/*  70 */       return true;
/*     */     } 
/*  72 */     return false;
/*     */   }
/*     */   
/*     */   private void triggerRepeaterEvent(LivingEntity entity, IAbility ability) {
/*  76 */     boolean isHitScan = HissatsuAbility.checkHitScan(entity);
/*  77 */     this.projectileComponent.setHitScan(isHitScan);
/*  78 */     this.projectileComponent.shoot(entity, 4.0F, 10.0F);
/*     */   }
/*     */   
/*     */   private void stopRepeaterEvent(LivingEntity entity, IAbility ability) {
/*  82 */     this.continuousComponent.stopContinuity(entity);
/*  83 */     this.cooldownComponent.startCooldown(entity, 120.0F);
/*     */   }
/*     */   
/*     */   public RenpatsuNamariBoshiProjectile createProjectile(LivingEntity entity) {
/*  87 */     RenpatsuNamariBoshiProjectile proj = new RenpatsuNamariBoshiProjectile(entity.field_70170_p, entity, this);
/*  88 */     return proj;
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity entity) {
/*  92 */     if (!(entity instanceof PlayerEntity)) {
/*  93 */       return false;
/*     */     }
/*     */     
/*  96 */     PlayerEntity player = (PlayerEntity)entity;
/*  97 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*  98 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/* 100 */     return (props.isSniper() && questProps.hasFinishedQuest(ModQuests.SNIPER_TRIAL_06));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\sniper\RenpatsuNamariBoshiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */