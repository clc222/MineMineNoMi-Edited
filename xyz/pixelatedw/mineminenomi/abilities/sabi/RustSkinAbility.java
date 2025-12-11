/*     */ package xyz.pixelatedw.mineminenomi.abilities.sabi;
/*     */ 
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.tags.ITag;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.BusoshokuHakiImbuingAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.PassiveAbility2;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DamageTakenComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEnchantments;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModTags;
/*     */ import xyz.pixelatedw.mineminenomi.items.weapons.ModSwordItem;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class RustSkinAbility extends PassiveAbility2 {
/*  38 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "rust_skin", new Pair[] {
/*  39 */         (Pair)ImmutablePair.of("Makes the user immune to attacks received from iron based items, damaging them in the processes", null)
/*     */       });
/*  41 */   public static final AbilityCore<RustSkinAbility> INSTANCE = (new AbilityCore.Builder("Rust Skin", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, RustSkinAbility::new))
/*  42 */     .addDescriptionLine(DESCRIPTION)
/*  43 */     .build();
/*     */   
/*  45 */   private final DamageTakenComponent damageTakenComponent = (new DamageTakenComponent((IAbility)this)).addOnAttackEvent(this::damageTakenEvent);
/*     */   
/*     */   public RustSkinAbility(AbilityCore<RustSkinAbility> ability) {
/*  48 */     super(ability);
/*     */     
/*  50 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.damageTakenComponent });
/*     */     
/*  52 */     addDuringPassiveEvent(this::onDuringPassive);
/*     */   }
/*     */   
/*     */   private void onDuringPassive(LivingEntity entity) {
/*  56 */     if (entity.func_70644_a((Effect)ModEffects.PUNK_CROSS.get())) {
/*  57 */       entity.func_195063_d((Effect)ModEffects.PUNK_CROSS.get());
/*     */     }
/*     */     
/*  60 */     if (entity.func_70644_a((Effect)ModEffects.GENOCIDE_RAID.get())) {
/*  61 */       entity.func_195063_d((Effect)ModEffects.GENOCIDE_RAID.get());
/*     */     }
/*     */   }
/*     */   
/*     */   public float damageTakenEvent(LivingEntity entity, IAbility ability, DamageSource source, float damage) {
/*  66 */     if (isPaused()) {
/*  67 */       return damage;
/*     */     }
/*     */     
/*  70 */     Entity directEntity = source.func_76364_f();
/*  71 */     Entity sourceEntity = source.func_76346_g();
/*     */     
/*  73 */     if (directEntity != null && (directEntity instanceof net.minecraft.entity.projectile.ProjectileEntity || directEntity instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity) && source instanceof ModDamageSource && ((ModDamageSource)source).getElement().equals(SourceElement.METAL)) {
/*  74 */       directEntity.func_174812_G();
/*     */       
/*  76 */       WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.RUST_SKIN.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*     */       
/*  78 */       return 0.0F;
/*     */     } 
/*     */     
/*  81 */     if (sourceEntity != null && sourceEntity instanceof LivingEntity) {
/*  82 */       if (source instanceof ModDamageSource && ((ModDamageSource)source).getElement().equals(SourceElement.METAL)) {
/*  83 */         WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.RUST_SKIN.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*     */         
/*  85 */         return 0.0F;
/*     */       } 
/*     */       
/*  88 */       LivingEntity attacker = (LivingEntity)sourceEntity;
/*     */       
/*  90 */       ItemStack mainhandGear = attacker.func_184582_a(EquipmentSlotType.MAINHAND);
/*  91 */       ItemStack offhandGear = attacker.func_184582_a(EquipmentSlotType.OFFHAND);
/*     */       
/*  93 */       ItemStack toDamage = null;
/*     */       
/*  95 */       IAbilityData abilityProps = AbilityDataCapability.get(attacker);
/*     */       
/*  97 */       BusoshokuHakiImbuingAbility hakiImbuingAbility = (BusoshokuHakiImbuingAbility)abilityProps.getEquippedAbility(BusoshokuHakiImbuingAbility.INSTANCE);
/*     */       
/*  99 */       boolean isActive = (hakiImbuingAbility != null && hakiImbuingAbility.isContinuous());
/*     */       
/* 101 */       if (isActive) {
/* 102 */         return damage;
/*     */       }
/*     */       
/* 105 */       boolean isMainhand = (mainhandGear.func_77973_b().func_206844_a((ITag)ModTags.Items.RUSTY) && EnchantmentHelper.func_77506_a((Enchantment)ModEnchantments.KAIROSEKI.get(), mainhandGear) <= 0);
/* 106 */       boolean isOffhand = (offhandGear.func_77973_b().func_206844_a((ITag)ModTags.Items.RUSTY) && EnchantmentHelper.func_77506_a((Enchantment)ModEnchantments.KAIROSEKI.get(), offhandGear) <= 0);
/*     */       
/* 108 */       if (isMainhand || isOffhand) {
/* 109 */         toDamage = mainhandGear;
/*     */       }
/*     */       
/* 112 */       Item mainhandItem = mainhandGear.func_77973_b();
/* 113 */       Item offhandItem = offhandGear.func_77973_b();
/*     */       
/* 115 */       if (mainhandItem instanceof ModSwordItem && !(mainhandItem instanceof xyz.pixelatedw.mineminenomi.items.weapons.AbilitySwordItem)) {
/* 116 */         boolean immunity = ((ModSwordItem)mainhandItem).isRustImmune();
/* 117 */         if (!immunity) {
/* 118 */           toDamage = mainhandGear;
/*     */         }
/*     */       } 
/*     */       
/* 122 */       if (offhandItem instanceof ModSwordItem && !(offhandItem instanceof xyz.pixelatedw.mineminenomi.items.weapons.AbilitySwordItem)) {
/* 123 */         boolean immunity = ((ModSwordItem)offhandItem).isRustImmune();
/* 124 */         if (!immunity) {
/* 125 */           toDamage = offhandGear;
/*     */         }
/*     */       } 
/*     */       
/* 129 */       if (toDamage != null) {
/* 130 */         if (toDamage.func_77984_f()) {
/* 131 */           toDamage.func_222118_a(toDamage.func_77958_k() / 4 + 1, entity, e -> e.func_213361_c(EquipmentSlotType.MAINHAND));
/*     */         } else {
/*     */           
/* 134 */           toDamage.func_190918_g(1 + this.random.nextInt(8));
/*     */         } 
/* 136 */         WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.RUST_SKIN.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/* 137 */         return 0.0F;
/*     */       } 
/*     */     } 
/*     */     
/* 141 */     return damage;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\sabi\RustSkinAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */