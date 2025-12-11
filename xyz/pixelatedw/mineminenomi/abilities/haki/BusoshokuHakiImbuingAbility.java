/*     */ package xyz.pixelatedw.mineminenomi.abilities.haki;
/*     */ 
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.SoundCategory;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTriggerComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.PoolComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityPools;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*     */ 
/*     */ public class BusoshokuHakiImbuingAbility
/*     */   extends Ability
/*     */ {
/*  37 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "busoshoku_haki_imbuing", new Pair[] {
/*  38 */         (Pair)ImmutablePair.of("Covers the weapon of the user in Armament haki, making their weapon attacks stronger and being able to damage Logia users.", null)
/*     */       });
/*  40 */   public static final AbilityCore<BusoshokuHakiImbuingAbility> INSTANCE = (new AbilityCore.Builder("Busoshoku Haki: Imbuing", AbilityCategory.HAKI, BusoshokuHakiImbuingAbility::new))
/*  41 */     .addDescriptionLine(DESCRIPTION)
/*  42 */     .setUnlockCheck(BusoshokuHakiImbuingAbility::canUnlock)
/*  43 */     .setSourceType(new SourceType[] { SourceType.SLASH
/*  44 */       }).setSourceHakiNature(SourceHakiNature.IMBUING)
/*  45 */     .build();
/*     */   
/*  47 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true))
/*  48 */     .addStartEvent(this::onContinuityStart)
/*  49 */     .addTickEvent(this::onContinuityTick)
/*  50 */     .addEndEvent(this::onContinuityEnd);
/*     */   
/*  52 */   private final HitTriggerComponent hitTriggerComponent = (new HitTriggerComponent((IAbility)this)).addTryHitEvent(this::tryHitEvent).addOnHitEvent(this::onHitEvent);
/*     */   
/*  54 */   private final PoolComponent poolComponent = new PoolComponent((IAbility)this, ModAbilityPools.IMBUING_BUSOSHOKU_HAKI, new xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool2[0]);
/*     */   
/*     */   public BusoshokuHakiImbuingAbility(AbilityCore<BusoshokuHakiImbuingAbility> core) {
/*  57 */     super(core);
/*     */     
/*  59 */     this.isNew = true;
/*  60 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.hitTriggerComponent, (AbilityComponent)this.poolComponent });
/*     */     
/*  62 */     addCanUseCheck(HakiHelper::canEnableHaki);
/*  63 */     addUseEvent(this::onUseEvent);
/*     */   }
/*     */   
/*     */   private void onUseEvent(LivingEntity entity, IAbility ability) {
/*  67 */     if (entity.func_184614_ca().func_77973_b() == ModWeapons.ENMA.get() && this.continuousComponent.isContinuous()) {
/*     */       return;
/*     */     }
/*     */     
/*  71 */     this.continuousComponent.triggerContinuity(entity);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void onContinuityStart(LivingEntity entity, IAbility ability) {
/*  77 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.BUSOSHOKU_HAKI_SFX.get(), SoundCategory.PLAYERS, 2.0F, 1.0F);
/*     */   }
/*     */   
/*     */   private void onContinuityTick(LivingEntity entity, IAbility ability) {
/*  81 */     boolean isOnMaxOveruse = HakiHelper.checkForHakiOveruse(entity, 1);
/*     */     
/*  83 */     if (isOnMaxOveruse) {
/*  84 */       this.continuousComponent.stopContinuity(entity);
/*     */     }
/*     */   }
/*     */   
/*     */   private void onContinuityEnd(LivingEntity entity, IAbility ability) {
/*  89 */     Iterable<ItemStack> inventories = ItemsHelper.getAllInventoryItems(entity);
/*     */     
/*  91 */     for (ItemStack stack : inventories) {
/*  92 */       boolean hasDefaultImbuing = false;
/*  93 */       if (stack.func_77973_b() instanceof xyz.pixelatedw.mineminenomi.items.weapons.ModSwordItem && stack.func_77973_b() instanceof xyz.pixelatedw.mineminenomi.items.weapons.AbilitySwordItem) {
/*  94 */         hasDefaultImbuing = true;
/*  95 */       } else if (!(stack.func_77973_b() instanceof xyz.pixelatedw.mineminenomi.items.weapons.ModSwordItem)) {
/*  96 */         hasDefaultImbuing = stack.func_77984_f();
/*     */       } 
/*     */       
/*  99 */       if (!stack.func_190926_b() && stack.func_77942_o() && stack.func_77978_p().func_74767_n("imbuingHakiActive") && hasDefaultImbuing) {
/* 100 */         stack.func_196082_o().func_82580_o("imbuingHakiActive");
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private HitTriggerComponent.HitResult tryHitEvent(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/* 106 */     if (!this.continuousComponent.isContinuous()) {
/* 107 */       return HitTriggerComponent.HitResult.PASS;
/*     */     }
/*     */     
/* 110 */     return HitTriggerComponent.HitResult.HIT;
/*     */   }
/*     */   
/*     */   private boolean onHitEvent(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/* 114 */     source.bypassLogia();
/*     */     
/* 116 */     return true;
/*     */   }
/*     */   
/*     */   private static boolean canUnlock(LivingEntity user) {
/* 120 */     IAbilityData abilityProps = AbilityDataCapability.get(user);
/* 121 */     IHakiData props = HakiDataCapability.get(user);
/* 122 */     IEntityStats statsProps = EntityStatsCapability.get(user);
/*     */     
/* 124 */     boolean hasHardeningUnlocked = abilityProps.hasUnlockedAbility(BusoshokuHakiHardeningAbility.INSTANCE);
/*     */     
/* 126 */     return ((statsProps.getDoriki() > 4000.0D && props.getBusoshokuHakiExp() > HakiHelper.getBusoshokuHardeningExpNeeded(user)) || hasHardeningUnlocked);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\haki\BusoshokuHakiImbuingAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */