/*     */ package xyz.pixelatedw.mineminenomi.abilities;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.stream.Collectors;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.PotionPassiveAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.mixins.EffectInstanceMixin;
/*     */ 
/*     */ public abstract class EffectImmunityAbility extends PotionPassiveAbility {
/*  18 */   public static final ImmunityInfo HEAT_RESISTANCES = (new ImmunityInfo()).<ImmunityInfo>addImmunityEffects((Supplier<Effect>[])new Supplier[] { (Supplier)ModEffects.FROSTBITE, (Supplier)ModEffects.CANDY_STUCK, (Supplier)ModEffects.CANDLE_LOCK }).<ImmunityInfo>addResistanceEffect((Supplier<Effect>)ModEffects.FROZEN, 10).addResistanceEffect((Supplier<Effect>)ModEffects.STICKY, 6);
/*     */   
/*     */   private final ImmunityInfo immunityInfo;
/*     */   
/*     */   public EffectImmunityAbility(AbilityCore<EffectImmunityAbility> core, ImmunityInfo info) {
/*  23 */     super(core);
/*  24 */     this.checkPotionEvent = this::checkPotionEvent;
/*  25 */     this.immunityInfo = info;
/*     */   }
/*     */   
/*     */   private boolean checkPotionEvent(PlayerEntity player, EffectInstance effect) {
/*  29 */     if (AbilityHelper.isAffectedByWater((LivingEntity)player)) {
/*  30 */       return true;
/*     */     }
/*     */     
/*  33 */     ArrayList<Supplier<Effect>> immunityEffects = this.immunityInfo.getImmunityEffects();
/*  34 */     ArrayList<Supplier<Effect>> resistanceEffects = this.immunityInfo.getResistanceEffects();
/*  35 */     ArrayList<Integer> resistanceEffectsReduction = this.immunityInfo.getResistanceEffectsReduction();
/*     */     
/*  37 */     if (resistanceEffects.size() > 0) {
/*  38 */       for (int i = 0; i < resistanceEffects.size(); i++) {
/*  39 */         if (((Effect)((Supplier<Effect>)resistanceEffects.get(i)).get()).equals(effect.func_188419_a()) && effect.func_76458_c() < 1) {
/*  40 */           int duration = effect.func_76459_b() / ((Integer)resistanceEffectsReduction.get(i)).intValue();
/*  41 */           if (duration <= 20) {
/*  42 */             return false;
/*     */           }
/*     */           
/*  45 */           ((EffectInstanceMixin)effect).setDuration(duration);
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*  51 */     return !((List)immunityEffects.stream().map(s -> (Effect)s.get()).collect(Collectors.toList())).contains(effect.func_188419_a());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class ImmunityInfo
/*     */   {
/*  60 */     private ArrayList<Supplier<Effect>> immunityEffects = new ArrayList<>();
/*  61 */     private ArrayList<Supplier<Effect>> resistanceEffects = new ArrayList<>();
/*  62 */     private ArrayList<Integer> resistanceEffectsReduction = new ArrayList<>();
/*     */ 
/*     */ 
/*     */     
/*     */     public ImmunityInfo clone() {
/*  67 */       ImmunityInfo info = new ImmunityInfo();
/*  68 */       info.immunityEffects = this.immunityEffects;
/*  69 */       info.resistanceEffects = this.resistanceEffects;
/*  70 */       info.resistanceEffectsReduction = this.resistanceEffectsReduction;
/*  71 */       return info;
/*     */     }
/*     */     
/*     */     public ImmunityInfo combine(ImmunityInfo other) {
/*  75 */       this.immunityEffects.addAll(other.immunityEffects);
/*  76 */       this.resistanceEffects.addAll(other.resistanceEffects);
/*  77 */       this.resistanceEffectsReduction.addAll(other.resistanceEffectsReduction);
/*  78 */       return this;
/*     */     }
/*     */     
/*     */     public ArrayList<Supplier<Effect>> getImmunityEffects() {
/*  82 */       return this.immunityEffects;
/*     */     }
/*     */     
/*     */     public ArrayList<Supplier<Effect>> getResistanceEffects() {
/*  86 */       return this.resistanceEffects;
/*     */     }
/*     */     
/*     */     public ArrayList<Integer> getResistanceEffectsReduction() {
/*  90 */       return this.resistanceEffectsReduction;
/*     */     }
/*     */     
/*     */     public <T extends ImmunityInfo> T addImmunityEffects(Effect... immunityEffects) {
/*  94 */       for (Effect effect : immunityEffects) {
/*  95 */         this.immunityEffects.add(() -> effect);
/*     */       }
/*  97 */       return (T)this;
/*     */     }
/*     */     
/*     */     public <T extends ImmunityInfo> T addImmunityEffects(Supplier<Effect>... immunityEffects) {
/* 101 */       this.immunityEffects.addAll(Arrays.asList(immunityEffects));
/* 102 */       return (T)this;
/*     */     }
/*     */     
/*     */     public <T extends ImmunityInfo> T addResistanceEffect(Effect resistanceEffect, int reduction) {
/* 106 */       this.resistanceEffects.add(() -> resistanceEffect);
/* 107 */       this.resistanceEffectsReduction.add(Integer.valueOf(reduction));
/* 108 */       return (T)this;
/*     */     }
/*     */     
/*     */     public <T extends ImmunityInfo> T addResistanceEffect(Supplier<Effect> resistanceEffect, int reduction) {
/* 112 */       this.resistanceEffects.add(resistanceEffect);
/* 113 */       this.resistanceEffectsReduction.add(Integer.valueOf(reduction));
/* 114 */       return (T)this;
/*     */     }
/*     */     
/*     */     public <T extends ImmunityInfo> T addLogiaImmunities() {
/* 118 */       addResistanceEffect((Supplier<Effect>)ModEffects.MOVEMENT_BLOCKED, 2);
/* 119 */       return (T)this;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\EffectImmunityAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */