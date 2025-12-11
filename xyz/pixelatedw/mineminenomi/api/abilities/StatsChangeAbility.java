/*     */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*     */ 
/*     */ import com.google.common.base.Predicates;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraftforge.fml.RegistryObject;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ public abstract class StatsChangeAbility
/*     */   extends ContinuousAbility
/*     */   implements IChangeStats
/*     */ {
/*  28 */   private HashMap<Supplier<Attribute>, Pair<Predicate<LivingEntity>, AttributeModifier>> modifiers = new HashMap<>();
/*     */   
/*     */   private boolean isDynamic;
/*     */   
/*     */   public StatsChangeAbility(AbilityCore core) {
/*  33 */     super(core);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void startContinuity(PlayerEntity player) {
/*  39 */     super.startContinuity(player);
/*  40 */     applyModifiers(player);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void tickContinuity(PlayerEntity player) {
/*  46 */     super.tickContinuity(player);
/*  47 */     getModifiers().forEach((attr, pair) -> {
/*     */           if (((Predicate<PlayerEntity>)pair.getLeft()).test(player)) {
/*     */             applyMissingModifier((LivingEntity)player, attr.get(), (AttributeModifier)pair.getRight());
/*     */           } else {
/*     */             removeModifier((LivingEntity)player, attr.get(), (AttributeModifier)pair.getRight());
/*     */           } 
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void stopContinuity(PlayerEntity player) {
/*  61 */     super.stopContinuity(player);
/*  62 */     removeModifiers(player);
/*  63 */     if (this.isDynamic) {
/*  64 */       getModifiers().clear();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDynamicModifiers() {
/*  72 */     this.isDynamic = true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyModifiers(PlayerEntity player) {
/*  77 */     for (Map.Entry<Supplier<Attribute>, Pair<Predicate<LivingEntity>, AttributeModifier>> entry : getModifiers().entrySet()) {
/*     */       
/*  79 */       if (((Predicate<PlayerEntity>)((Pair)entry.getValue()).getLeft()).test(player)) {
/*  80 */         applyModifier((LivingEntity)player, ((Supplier<Attribute>)entry.getKey()).get(), (AttributeModifier)((Pair)entry.getValue()).getRight());
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void applyMissingModifiers(PlayerEntity player) {
/*  86 */     for (Map.Entry<Supplier<Attribute>, Pair<Predicate<LivingEntity>, AttributeModifier>> entry : getModifiers().entrySet()) {
/*     */       
/*  88 */       if (((Predicate<PlayerEntity>)((Pair)entry.getValue()).getLeft()).test(player)) {
/*  89 */         applyMissingModifier((LivingEntity)player, ((Supplier<Attribute>)entry.getKey()).get(), (AttributeModifier)((Pair)entry.getValue()).getRight());
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void removeModifiers(PlayerEntity player) {
/*  95 */     for (Map.Entry<Supplier<Attribute>, Pair<Predicate<LivingEntity>, AttributeModifier>> entry : getModifiers().entrySet()) {
/*     */       
/*  97 */       removeModifier((LivingEntity)player, ((Supplier<Attribute>)entry.getKey()).get(), (AttributeModifier)((Pair)entry.getValue()).getRight());
/*     */       
/*  99 */       if (((Attribute)((Supplier<Attribute>)entry.getKey()).get()).equals(Attributes.field_233818_a_)) {
/*     */         
/* 101 */         float leftHp = player.func_110143_aJ() - player.func_110138_aP();
/* 102 */         if (leftHp > 0.0F) {
/* 103 */           player.func_70606_j(player.func_110143_aJ() - leftHp);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addModifier(RegistryObject<Attribute> attr, AttributeModifier modifier) {
/* 110 */     getModifiers().put(attr, ImmutablePair.of(Predicates.alwaysTrue(), modifier));
/*     */   }
/*     */ 
/*     */   
/*     */   public void addModifier(Attribute attr, AttributeModifier modifier) {
/* 115 */     getModifiers().put(() -> attr, ImmutablePair.of(Predicates.alwaysTrue(), modifier));
/*     */   }
/*     */ 
/*     */   
/*     */   public void addModifier(RegistryObject<Attribute> attr, AttributeModifier modifier, Predicate<LivingEntity> test) {
/* 120 */     getModifiers().put(attr, ImmutablePair.of(test, modifier));
/*     */   }
/*     */ 
/*     */   
/*     */   public void addModifier(Attribute attr, AttributeModifier modifier, Predicate<LivingEntity> test) {
/* 125 */     getModifiers().put(() -> attr, ImmutablePair.of(test, modifier));
/*     */   }
/*     */ 
/*     */   
/*     */   public HashMap<Supplier<Attribute>, Pair<Predicate<LivingEntity>, AttributeModifier>> getModifiers() {
/* 130 */     return this.modifiers;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean getEmptyHandedModifier(LivingEntity entity) {
/* 135 */     return entity.func_184614_ca().func_190926_b();
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\StatsChangeAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */