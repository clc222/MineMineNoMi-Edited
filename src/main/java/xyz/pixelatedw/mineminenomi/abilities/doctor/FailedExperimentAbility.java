/*     */ package xyz.pixelatedw.mineminenomi.abilities.doctor;
/*     */ import com.google.common.collect.Lists;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.projectile.PotionEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.potion.PotionUtils;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AltModeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*     */ 
/*     */ public class FailedExperimentAbility extends Ability {
/*  33 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "failed_experiment", new Pair[] {
/*  34 */         (Pair)ImmutablePair.of("Throws a random splash potion with a debuff effect at the enemy.", null)
/*     */       });
/*     */   
/*     */   private static final int COOLDOWN = 240;
/*  38 */   public static final AbilityCore<FailedExperimentAbility> INSTANCE = (new AbilityCore.Builder("Failed Experiment", AbilityCategory.STYLE, FailedExperimentAbility::new))
/*  39 */     .addDescriptionLine(DESCRIPTION)
/*  40 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(240.0F)
/*  41 */       }).setUnlockCheck(FailedExperimentAbility::canUnlock)
/*  42 */     .build();
/*     */   
/*  44 */   private final AltModeComponent<Mode> altModeComponent = (new AltModeComponent((IAbility)this, Mode.class, Mode.ATTACK_SPEED)).addChangeModeEvent(this::onAltModeChange);
/*  45 */   private ItemStack stack = new ItemStack((IItemProvider)Items.field_185155_bH);
/*     */   
/*     */   public FailedExperimentAbility(AbilityCore<FailedExperimentAbility> core) {
/*  48 */     super(core);
/*  49 */     this.isNew = true;
/*     */     
/*  51 */     addCanUseCheck(AbilityHelper::requiresMedicBag);
/*     */     
/*  53 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.altModeComponent });
/*  54 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity livingEntity, IAbility ability) {
/*  58 */     if (this.altModeComponent.getCurrentMode() == Mode.ATTACK_SPEED) {
/*  59 */       this.stack = PotionUtils.func_185184_a(this.stack, Lists.newArrayList((Object[])new EffectInstance[] { new EffectInstance(Effects.field_76419_f, 200, 1) }));
/*     */     }
/*     */     
/*  62 */     PotionEntity potion = new PotionEntity(livingEntity.field_70170_p, livingEntity);
/*     */     
/*  64 */     potion.func_213884_b(this.stack);
/*  65 */     potion.field_70125_A -= -20.0F;
/*  66 */     potion.func_234612_a_((Entity)livingEntity, livingEntity.field_70125_A, livingEntity.field_70177_z, -20.0F, 1.0F, 0.0F);
/*  67 */     livingEntity.field_70170_p.func_217376_c((Entity)potion);
/*     */     
/*  69 */     if (livingEntity instanceof PlayerEntity) {
/*  70 */       PlayerEntity player = (PlayerEntity)livingEntity;
/*  71 */       ItemStack medicBag = (ItemStack)player.field_71071_by.field_70460_b.get(2);
/*  72 */       medicBag.func_222118_a(10, (LivingEntity)player, user -> user.func_213361_c(EquipmentSlotType.MAINHAND));
/*     */     } 
/*     */     
/*  75 */     this.cooldownComponent.startCooldown(livingEntity, 240.0F);
/*     */   }
/*     */   
/*     */   private void onAltModeChange(LivingEntity entity, IAbility ability, Mode mode) {
/*  79 */     switch (mode) {
/*     */       case POISON:
/*  81 */         this.stack = PotionUtils.func_185184_a(this.stack, Lists.newArrayList((Object[])new EffectInstance[] { new EffectInstance(Effects.field_76436_u, 200, 1) }));
/*     */         break;
/*     */       
/*     */       case HUNGER:
/*  85 */         this.stack = PotionUtils.func_185184_a(this.stack, Lists.newArrayList((Object[])new EffectInstance[] { new EffectInstance(Effects.field_76438_s, 200, 1) }));
/*     */         break;
/*     */       
/*     */       case ATTACK_SPEED:
/*  89 */         this.stack = PotionUtils.func_185184_a(this.stack, Lists.newArrayList((Object[])new EffectInstance[] { new EffectInstance(Effects.field_76419_f, 200, 1) }));
/*     */         break;
/*     */       
/*     */       case CONFUSION:
/*  93 */         this.stack = PotionUtils.func_185184_a(this.stack, Lists.newArrayList((Object[])new EffectInstance[] { new EffectInstance(Effects.field_76431_k, 200, 1) }));
/*     */         break;
/*     */       
/*     */       case SLOWNESS:
/*  97 */         this.stack = PotionUtils.func_185184_a(this.stack, Lists.newArrayList((Object[])new EffectInstance[] { new EffectInstance(Effects.field_76421_d, 200, 1) }));
/*     */         break;
/*     */       
/*     */       case BLINDNESS:
/* 101 */         this.stack = PotionUtils.func_185184_a(this.stack, Lists.newArrayList((Object[])new EffectInstance[] { new EffectInstance(Effects.field_76440_q, 200, 1) }));
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean canUnlock(LivingEntity entity) {
/* 108 */     if (!(entity instanceof PlayerEntity)) {
/* 109 */       return false;
/*     */     }
/*     */     
/* 112 */     PlayerEntity player = (PlayerEntity)entity;
/* 113 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 114 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/* 116 */     return (props.isDoctor() && questProps.hasFinishedQuest(ModQuests.DOCTOR_TRIAL_02));
/*     */   }
/*     */   
/*     */   public enum Mode {
/* 120 */     CONFUSION,
/* 121 */     ATTACK_SPEED,
/* 122 */     POISON,
/* 123 */     HUNGER,
/* 124 */     SLOWNESS,
/* 125 */     BLINDNESS;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\doctor\FailedExperimentAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */