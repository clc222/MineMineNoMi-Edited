/*    */ package xyz.pixelatedw.mineminenomi.abilities.brawler;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.GroundParticlesEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ public class JishinHoAbility
/*    */   extends Ability {
/* 35 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "jishin_ho", new Pair[] {
/* 36 */         (Pair)ImmutablePair.of("Punches the ground to cause a quake that damages everyone around", null)
/*    */       });
/* 38 */   public static final ParticleEffect PARTICLES = (ParticleEffect)new GroundParticlesEffect(7, 100);
/*    */   
/*    */   private static final float COOLDOWN = 300.0F;
/*    */   
/*    */   private static final float DAMAGE = 25.0F;
/*    */   private static final float RANGE = 7.0F;
/* 44 */   public static final AbilityCore<JishinHoAbility> INSTANCE = (new AbilityCore.Builder("Jishin Ho", AbilityCategory.STYLE, JishinHoAbility::new))
/* 45 */     .addDescriptionLine(DESCRIPTION)
/* 46 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(300.0F), DealDamageComponent.getTooltip(25.0F), RangeComponent.getTooltip(7.0F, RangeComponent.RangeType.AOE)
/* 47 */       }).setSourceType(new SourceType[] { SourceType.FIST, SourceType.INDIRECT
/* 48 */       }).setSourceElement(SourceElement.SHOCKWAVE)
/* 49 */     .setSourceHakiNature(SourceHakiNature.HARDENING)
/* 50 */     .setUnlockCheck(JishinHoAbility::canUnlock)
/* 51 */     .build();
/*    */   
/* 53 */   private final ContinuousComponent continuousComponent = new ContinuousComponent((IAbility)this);
/* 54 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/* 55 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*    */   
/*    */   public JishinHoAbility(AbilityCore<JishinHoAbility> core) {
/* 58 */     super(core);
/*    */     
/* 60 */     this.isNew = true;
/* 61 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.dealDamageComponent, (AbilityComponent)this.rangeComponent });
/*    */     
/* 63 */     addCanUseCheck(AbilityHelper::canUseBrawlerAbilities);
/* 64 */     addUseEvent(this::onUseEvent);
/*    */   }
/*    */   
/*    */   private void onUseEvent(LivingEntity player, IAbility ability) {
/* 68 */     PARTICLES.spawn(player.field_70170_p, player.func_226277_ct_(), player.func_226278_cu_(), player.func_226281_cx_(), 0.0D, 0.0D, 0.0D);
/*    */     
/* 70 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(player, 7.0F);
/* 71 */     targets.remove(player);
/* 72 */     targets.removeIf(entity -> (!entity.func_233570_aj_() && DevilFruitHelper.getDifferenceToFloor((Entity)player) > 1.5D));
/*    */     
/* 74 */     for (LivingEntity target : targets) {
/* 75 */       boolean flag = this.dealDamageComponent.hurtTarget(player, target, 25.0F);
/* 76 */       if (flag) {
/* 77 */         AbilityHelper.setDeltaMovement((Entity)target, 0.0D, 0.75D, 0.0D);
/*    */       }
/*    */     } 
/*    */     
/* 81 */     this.cooldownComponent.startCooldown(player, 300.0F);
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
/* 93 */     return (props.isBrawler() && questProps.hasFinishedQuest(ModQuests.BRAWLER_TRIAL_05));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\brawler\JishinHoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */