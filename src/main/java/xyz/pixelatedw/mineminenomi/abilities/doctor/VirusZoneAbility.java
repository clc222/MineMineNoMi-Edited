/*     */ package xyz.pixelatedw.mineminenomi.abilities.doctor;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Optional;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.projectile.PotionEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.PotionUtils;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AltModeComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*     */ 
/*     */ public class VirusZoneAbility extends Ability {
/*  37 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "virus_zone", new Pair[] {
/*  38 */         (Pair)ImmutablePair.of("Throws a lingering splash potion of the mummy virus or ice oni virus.", null)
/*     */       });
/*  40 */   private static final TranslationTextComponent MUMMY_NAME = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.virus_zone_mummy", "Virus Zone: Mummy"));
/*  41 */   private static final TranslationTextComponent ICE_ONI_NAME = new TranslationTextComponent(WyRegistry.registerName("ability.mineminenomi.virus_zone_ice_oni", "Virus Zone: Ice Oni"));
/*     */   
/*  43 */   private static final ResourceLocation MUMMY_ICON = new ResourceLocation("mineminenomi", "textures/abilities/virus_zone_mummy.png");
/*  44 */   private static final ResourceLocation ICE_ONI_ICON = new ResourceLocation("mineminenomi", "textures/abilities/virus_zone_ice_oni.png");
/*     */   
/*     */   private static final int COOLDOWN = 400;
/*     */   
/*     */   private static final int EFFECT_TIME = 200;
/*  49 */   public static final AbilityCore<VirusZoneAbility> INSTANCE = (new AbilityCore.Builder("Virus Zone", AbilityCategory.STYLE, VirusZoneAbility::new))
/*  50 */     .addDescriptionLine(DESCRIPTION)
/*  51 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(400.0F)
/*  52 */       }).setUnlockCheck(VirusZoneAbility::canUnlock)
/*  53 */     .build();
/*     */   
/*  55 */   private final AltModeComponent<Mode> altModeComponent = (new AltModeComponent((IAbility)this, Mode.class, Mode.MUMMY)).addChangeModeEvent(this::onAltModeChange);
/*     */   
/*     */   public VirusZoneAbility(AbilityCore<VirusZoneAbility> core) {
/*  58 */     super(core);
/*     */     
/*  60 */     setDisplayName((ITextComponent)MUMMY_NAME);
/*  61 */     setDisplayIcon(MUMMY_ICON);
/*     */     
/*  63 */     this.isNew = true;
/*  64 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.altModeComponent });
/*     */     
/*  66 */     addCanUseCheck(AbilityHelper::requiresMedicBag);
/*     */     
/*  68 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity livingEntity, IAbility ability) {
/*  72 */     ItemStack stack = new ItemStack((IItemProvider)Items.field_185156_bI);
/*  73 */     if (this.altModeComponent.getCurrentMode() == Mode.MUMMY) {
/*  74 */       stack = PotionUtils.func_185184_a(stack, Lists.newArrayList((Object[])new EffectInstance[] { new EffectInstance((Effect)ModEffects.MUMMY_VIRUS.get(), 200, 1) }));
/*     */     }
/*  76 */     else if (this.altModeComponent.getCurrentMode() == Mode.ICE_ONI) {
/*  77 */       stack = PotionUtils.func_185184_a(stack, Lists.newArrayList((Object[])new EffectInstance[] { new EffectInstance((Effect)ModEffects.ICE_ONI.get(), 200, 1) }));
/*     */     } 
/*     */     
/*  80 */     PotionEntity potion = new PotionEntity(livingEntity.field_70170_p, livingEntity);
/*     */     
/*  82 */     potion.func_213884_b(stack);
/*  83 */     potion.field_70125_A -= -20.0F;
/*  84 */     potion.func_234612_a_((Entity)livingEntity, livingEntity.field_70125_A, livingEntity.field_70177_z, -20.0F, 1.0F, 0.0F);
/*     */     
/*  86 */     livingEntity.field_70170_p.func_217376_c((Entity)potion);
/*     */     
/*  88 */     if (livingEntity instanceof PlayerEntity) {
/*  89 */       PlayerEntity player = (PlayerEntity)livingEntity;
/*     */       
/*  91 */       Optional<ItemStack> medicBag = ItemsHelper.findItemInSlot((LivingEntity)player, EquipmentSlotType.CHEST, (Item)ModArmors.MEDIC_BAG.get());
/*     */       
/*  93 */       if (medicBag.isPresent()) {
/*  94 */         ((ItemStack)medicBag.get()).func_222118_a(10, (LivingEntity)player, user -> user.func_213361_c(EquipmentSlotType.MAINHAND));
/*     */       }
/*     */     } 
/*     */     
/*  98 */     this.cooldownComponent.startCooldown(livingEntity, 400.0F);
/*     */   }
/*     */   
/*     */   private void onAltModeChange(LivingEntity entity, IAbility ability, Mode mode) {
/* 102 */     if (mode == Mode.MUMMY) {
/* 103 */       setDisplayName((ITextComponent)MUMMY_NAME);
/* 104 */       setDisplayIcon(MUMMY_ICON);
/*     */     }
/* 106 */     else if (mode == Mode.ICE_ONI) {
/* 107 */       setDisplayName((ITextComponent)ICE_ONI_NAME);
/* 108 */       setDisplayIcon(ICE_ONI_ICON);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity entity) {
/* 113 */     if (!(entity instanceof PlayerEntity)) {
/* 114 */       return false;
/*     */     }
/*     */     
/* 117 */     PlayerEntity player = (PlayerEntity)entity;
/* 118 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 119 */     IQuestData questProps = QuestDataCapability.get(player);
/*     */     
/* 121 */     return (props.isDoctor() && questProps.hasFinishedQuest(ModQuests.DOCTOR_TRIAL_04));
/*     */   }
/*     */   
/*     */   public enum Mode {
/* 125 */     MUMMY,
/* 126 */     ICE_ONI;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\doctor\VirusZoneAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */