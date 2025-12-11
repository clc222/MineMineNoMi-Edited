/*    */ package xyz.pixelatedw.mineminenomi.abilities.hana;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.RayTraceResult;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUseResult;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.hana.CampoDeFloresEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class CampoDeFloresAbility extends Ability {
/* 30 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "campo_de_flores", new Pair[] {
/* 31 */         (Pair)ImmutablePair.of("Sprouts multiple hands in front of the user stunning and sending all enemies in a radius flying", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 200;
/*    */   public static final int HOLD_TIME = 100;
/*    */   public static final int RANGE = 10;
/*    */   public static final int DAMAGE = 10;
/* 38 */   public static final AbilityCore<CampoDeFloresAbility> INSTANCE = (new AbilityCore.Builder("Campo de Flores", AbilityCategory.DEVIL_FRUITS, CampoDeFloresAbility::new))
/* 39 */     .addDescriptionLine(DESCRIPTION)
/* 40 */     .addDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, HanaHelper.REQUIRES_MIL_FLEUR
/* 41 */       }).addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F), ContinuousComponent.getTooltip(100.0F), RangeComponent.getTooltip(10.0F, RangeComponent.RangeType.AOE), DealDamageComponent.getTooltip(10.0F)
/* 42 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/* 43 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 44 */       }).build();
/*    */   
/* 46 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(this::startContinuityEvent).addEndEvent(this::endContinuityEvent);
/* 47 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/* 48 */   private final DealDamageComponent dealDamageComponent = new DealDamageComponent((IAbility)this);
/*    */   
/*    */   private CampoDeFloresEntity campoDeFlores;
/*    */   
/*    */   public CampoDeFloresAbility(AbilityCore<CampoDeFloresAbility> core) {
/* 53 */     super(core);
/*    */     
/* 55 */     this.isNew = true;
/* 56 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.rangeComponent, (AbilityComponent)this.dealDamageComponent });
/*    */     
/* 58 */     addCanUseCheck(this::requiresMilFleur);
/* 59 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 63 */     this.continuousComponent.triggerContinuity(entity, 100.0F);
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 67 */     RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)entity);
/* 68 */     this.campoDeFlores = new CampoDeFloresEntity(entity.field_70170_p, this);
/* 69 */     this.campoDeFlores.func_70107_b((mop.func_216347_e()).field_72450_a, (mop.func_216347_e()).field_72448_b, (mop.func_216347_e()).field_72449_c);
/* 70 */     this.campoDeFlores.setOwner(entity);
/* 71 */     entity.field_70170_p.func_217376_c((Entity)this.campoDeFlores);
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 75 */     if (this.campoDeFlores != null) {
/* 76 */       this.campoDeFlores.func_70106_y();
/*    */     }
/*    */     
/* 79 */     this.cooldownComponent.startCooldown(entity, 200.0F);
/*    */   }
/*    */   
/*    */   private AbilityUseResult requiresMilFleur(LivingEntity entity, IAbility ability) {
/* 83 */     MilFleurAbility milFleur = (MilFleurAbility)AbilityDataCapability.get(entity).getEquippedAbility(MilFleurAbility.INSTANCE);
/* 84 */     if (milFleur != null && milFleur.isContinuous()) {
/* 85 */       return AbilityUseResult.success();
/*    */     }
/* 87 */     return AbilityUseResult.fail((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_MISSING_DEPENDENCY_SINGLE, new Object[] { INSTANCE.getLocalizedName().getString(), MilFleurAbility.INSTANCE.getLocalizedName().getString() }));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\hana\CampoDeFloresAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */