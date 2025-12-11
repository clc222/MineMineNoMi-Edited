/*     */ package xyz.pixelatedw.mineminenomi.abilities.doctor;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HealComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.TargetsPredicate;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class MedicBagExplosionAbility extends Ability {
/*  37 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "medic_bag_explosion", new Pair[] {
/*  38 */         (Pair)ImmutablePair.of("By sacrificing the medic bag's durability the user can heal themselves with regeneration while applying debuffs to nearby enemies.", null)
/*     */       });
/*  40 */   public static final TargetsPredicate ENEMY_AREA_CHECK = (new TargetsPredicate()).testEnemyFaction();
/*  41 */   public static final TargetsPredicate FRIENDLY_AREA_CHECK = (new TargetsPredicate()).testFriendlyFaction();
/*     */   
/*     */   private static final int COOLDOWN = 800;
/*     */   
/*     */   private static final int RANGE = 10;
/*     */   private static final int MIN_HEAL = 5;
/*     */   private static final int MAX_HEAL = 50;
/*  48 */   public static final AbilityCore<MedicBagExplosionAbility> INSTANCE = (new AbilityCore.Builder("Medic Bag Explosion", AbilityCategory.STYLE, MedicBagExplosionAbility::new))
/*  49 */     .addDescriptionLine(DESCRIPTION)
/*  50 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(800.0F), RangeComponent.getTooltip(10.0F, RangeComponent.RangeType.AOE), HealComponent.getTooltip(5.0F, 50.0F)
/*  51 */       }).setUnlockCheck(MedicBagExplosionAbility::canUnlock)
/*  52 */     .build();
/*     */   
/*  54 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*  55 */   private final HealComponent healComponent = new HealComponent((IAbility)this);
/*     */   
/*     */   public MedicBagExplosionAbility(AbilityCore<MedicBagExplosionAbility> core) {
/*  58 */     super(core);
/*     */     
/*  60 */     this.isNew = true;
/*  61 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.rangeComponent, (AbilityComponent)this.healComponent });
/*     */     
/*  63 */     addCanUseCheck(AbilityHelper::requiresMedicBag);
/*  64 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  68 */     boolean isHandEmpty = entity.func_184614_ca().func_190926_b();
/*     */     
/*  70 */     Optional<ItemStack> medicBag = !isHandEmpty ? Optional.<ItemStack>ofNullable(entity.func_184614_ca()) : ItemsHelper.findItemInSlot(entity, EquipmentSlotType.CHEST, (Item)ModArmors.MEDIC_BAG.get());
/*     */     
/*  72 */     if (!medicBag.isPresent()) {
/*     */       return;
/*     */     }
/*     */     
/*  76 */     float heal = (float)MathHelper.func_151237_a(WyHelper.percentage(20.0D, entity.func_110138_aP()), 5.0D, 50.0D);
/*  77 */     this.healComponent.healTarget(entity, entity, heal);
/*     */     
/*  79 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 10.0F, ENEMY_AREA_CHECK);
/*  80 */     for (LivingEntity target : targets) {
/*  81 */       int effect = (int)WyHelper.randomWithRange(0, 6);
/*     */       
/*  83 */       switch (effect) {
/*     */         case 0:
/*  85 */           target.func_195064_c(new EffectInstance(Effects.field_76440_q, 200, 1));
/*     */         
/*     */         case 1:
/*  88 */           target.func_195064_c(new EffectInstance(Effects.field_76431_k, 200, 1));
/*     */         
/*     */         case 2:
/*  91 */           target.func_195064_c(new EffectInstance(Effects.field_76419_f, 200, 1));
/*     */         
/*     */         case 3:
/*  94 */           target.func_195064_c(new EffectInstance(Effects.field_76421_d, 200, 1));
/*     */         
/*     */         case 4:
/*  97 */           target.func_195064_c(new EffectInstance(Effects.field_76436_u, 200, 1));
/*     */         
/*     */         case 5:
/* 100 */           target.func_195064_c(new EffectInstance(Effects.field_82731_v, 200, 1));
/*     */         
/*     */         case 6:
/* 103 */           target.func_195064_c(new EffectInstance(Effects.field_76437_t, 200, 1));
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     } 
/* 110 */     List<LivingEntity> friendlies = this.rangeComponent.getTargetsInArea(entity, 10.0F, FRIENDLY_AREA_CHECK);
/* 111 */     for (LivingEntity target : friendlies) {
/* 112 */       target.func_195064_c(new EffectInstance(Effects.field_76428_l, 100, 2));
/*     */     }
/*     */     
/* 115 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.MEDIC_BAG_EXPLOSION.get(), (Entity)entity, (entity.func_213303_ch()).field_72450_a, (entity.func_213303_ch()).field_72448_b, (entity.func_213303_ch()).field_72449_c);
/*     */     
/* 117 */     this.cooldownComponent.startCooldown(entity, 800.0F);
/*     */     
/* 119 */     ((ItemStack)medicBag.get()).func_222118_a(250, entity, user -> user.func_213361_c(EquipmentSlotType.MAINHAND));
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity entity) {
/* 123 */     if (!(entity instanceof PlayerEntity)) {
/* 124 */       return false;
/*     */     }
/*     */     
/* 127 */     PlayerEntity player = (PlayerEntity)entity;
/* 128 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 129 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/* 131 */     return (props.isDoctor() && questProps.hasFinishedQuest(ModQuests.DOCTOR_TRIAL_03));
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\doctor\MedicBagExplosionAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */