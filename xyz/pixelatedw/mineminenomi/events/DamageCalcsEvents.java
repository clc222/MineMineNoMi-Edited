/*     */ package xyz.pixelatedw.mineminenomi.events;
/*     */ 
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.Attribute;
/*     */ import net.minecraft.entity.ai.attributes.Attributes;
/*     */ import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.CombatRules;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import net.minecraftforge.registries.IForgeRegistryEntry;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IHitAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.HandcuffType;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.NetType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.projectilesextra.ProjectileExtrasCapability;
/*     */ import xyz.pixelatedw.mineminenomi.effects.CaughtInNetEffect;
/*     */ import xyz.pixelatedw.mineminenomi.effects.HandcuffedEffect;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModTags;
/*     */ import xyz.pixelatedw.mineminenomi.items.HandcuffsItem;
/*     */ import xyz.pixelatedw.mineminenomi.mixins.EffectInstanceMixin;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSetEffectDetailsPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class DamageCalcsEvents
/*     */ {
/*     */   public static float calcExtraDamage(LivingEntity target, DamageSource source, Entity directEntity, float amount) {
/*  41 */     if (directEntity != null && 
/*  42 */       directEntity instanceof LivingEntity) {
/*  43 */       LivingEntity livingAttacker = (LivingEntity)directEntity;
/*     */       
/*  45 */       boolean result = handleHandcuffActivation(livingAttacker, target, amount);
/*     */       
/*  47 */       if (result) {
/*  48 */         return -1.0F;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  53 */     handleArmorConductivity(target, source);
/*     */     
/*  55 */     amount = handleEntityConductivity((Entity)target, source, amount);
/*  56 */     amount = calcDamageReduction(target, source, amount);
/*     */     
/*  58 */     handleHandcuffDamage(target, amount);
/*     */     
/*  60 */     return amount;
/*     */   }
/*     */   
/*     */   private static float calcDamageReduction(LivingEntity target, DamageSource source, float initialDamage) {
/*  64 */     if (source.func_76357_e()) {
/*  65 */       return initialDamage;
/*     */     }
/*     */     
/*  68 */     ModifiableAttributeInstance damageReductionAttribute = target.func_110148_a((Attribute)ModAttributes.DAMAGE_REDUCTION.get());
/*  69 */     ModifiableAttributeInstance fauxProtectionAttribute = target.func_110148_a((Attribute)ModAttributes.FAUX_PROTECTION.get());
/*  70 */     ModifiableAttributeInstance toughnessAttribute = target.func_110148_a((Attribute)ModAttributes.TOUGHNESS.get());
/*  71 */     ModifiableAttributeInstance armorAttribute = target.func_110148_a(Attributes.field_233826_i_);
/*     */     
/*  73 */     Entity sourceEntity = source.func_76346_g();
/*  74 */     Entity directEntity = source.func_76364_f();
/*     */     
/*  76 */     if (toughnessAttribute == null || armorAttribute == null) {
/*  77 */       return initialDamage;
/*     */     }
/*     */     
/*  80 */     if (sourceEntity instanceof PlayerEntity) {
/*  81 */       Predicate<IAbility> hitAbilityCheck = a -> 
/*  82 */         (a instanceof IHitAbility && ((IHitAbility)a).isActive((PlayerEntity)sourceEntity));
/*     */ 
/*     */       
/*  85 */       for (IAbility ability : AbilityDataCapability.get((LivingEntity)sourceEntity).getEquippedAbilities(hitAbilityCheck)) {
/*  86 */         ModDamageSource modDamageSource = ((IHitAbility)ability).getDamageSource((PlayerEntity)sourceEntity, null);
/*     */         
/*  88 */         if (modDamageSource instanceof ModDamageSource) {
/*  89 */           handleArmorConductivity(target, (DamageSource)modDamageSource);
/*     */           
/*  91 */           initialDamage = handleEntityConductivity((Entity)target, (DamageSource)modDamageSource, initialDamage);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  96 */     float piercing = 0.0F;
/*     */     
/*  98 */     if (directEntity instanceof net.minecraft.entity.projectile.ProjectileEntity) {
/*  99 */       piercing = ProjectileExtrasCapability.get(directEntity).getPiercing();
/*     */     }
/*     */     
/* 102 */     if (source instanceof ModDamageSource) {
/* 103 */       source.func_76348_h();
/*     */ 
/*     */       
/* 106 */       ModDamageSource modSource = (ModDamageSource)source;
/*     */       
/* 108 */       initialDamage = handleWeaponConductivity((DamageSource)modSource, initialDamage);
/*     */       
/* 110 */       if (modSource.getPierce() == 1.0F) {
/* 111 */         source.func_76348_h();
/*     */       }
/*     */       
/* 114 */       piercing = modSource.getPierce();
/*     */     } 
/*     */     
/* 117 */     double toughness = toughnessAttribute.func_111126_e() * 1.28D;
/* 118 */     double armor = armorAttribute.func_111126_e() * 0.72D * (1.0D - piercing);
/* 119 */     double totalDef = Math.min((toughness + armor) / 40.0D, 1.0D);
/*     */     
/* 121 */     float totalReduction = (float)(initialDamage * totalDef * 0.95D);
/*     */     
/* 123 */     if (target instanceof xyz.pixelatedw.mineminenomi.api.challenges.IChallengeBoss) {
/* 124 */       float finalDamageAfterProt = CombatRules.func_188401_b(initialDamage - totalReduction, (float)fauxProtectionAttribute.func_111126_e());
/* 125 */       return finalDamageAfterProt;
/*     */     } 
/*     */     
/* 128 */     float damageAfterToughness = initialDamage - totalReduction;
/*     */     
/* 130 */     if (damageReductionAttribute != null && damageReductionAttribute.func_111126_e() != 0.0D) {
/* 131 */       damageAfterToughness = (float)(damageAfterToughness * (1.0D - damageReductionAttribute.func_111126_e()));
/*     */     }
/*     */     
/* 134 */     return damageAfterToughness;
/*     */   }
/*     */ 
/*     */   
/*     */   private static void handleArmorConductivity(LivingEntity target, DamageSource source) {
/* 139 */     if (!(source instanceof ModDamageSource)) {
/*     */       return;
/*     */     }
/*     */     
/* 143 */     ModDamageSource modSource = (ModDamageSource)source;
/*     */     
/* 145 */     if (modSource.getElement() != SourceElement.LIGHTNING) {
/*     */       return;
/*     */     }
/*     */     
/* 149 */     for (ItemStack itemStack : target.func_184193_aE()) {
/* 150 */       float conductivity = ModTags.Items.CONDUCTIVE.getValue((IForgeRegistryEntry)itemStack.func_77973_b());
/*     */       
/* 152 */       if (conductivity > 0.0F) {
/* 153 */         modSource.setPiercing(modSource.getPierce() + conductivity);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static float handleEntityConductivity(Entity entity, DamageSource source, float damage) {
/* 160 */     if (!(source instanceof ModDamageSource)) {
/* 161 */       return damage;
/*     */     }
/*     */     
/* 164 */     ModDamageSource modSource = (ModDamageSource)source;
/*     */     
/* 166 */     if (modSource.getElement() != SourceElement.LIGHTNING) {
/* 167 */       return damage;
/*     */     }
/*     */     
/* 170 */     float conductivity = ModTags.Entities.CONDUCTIVE.getValue((IForgeRegistryEntry)entity.func_200600_R());
/*     */     
/* 172 */     return damage + damage * conductivity;
/*     */   }
/*     */ 
/*     */   
/*     */   private static float handleWeaponConductivity(DamageSource source, float damage) {
/* 177 */     if (!(source instanceof ModDamageSource)) {
/* 178 */       return damage;
/*     */     }
/*     */     
/* 181 */     ModDamageSource modSource = (ModDamageSource)source;
/*     */     
/* 183 */     if (modSource.getElement() != SourceElement.LIGHTNING) {
/* 184 */       return damage;
/*     */     }
/*     */     
/* 187 */     float conductivity = 0.0F;
/*     */     
/* 189 */     if (modSource.hasType(SourceType.PROJECTILE)) {
/* 190 */       conductivity = ModTags.Entities.CONDUCTIVE.getValue((IForgeRegistryEntry)source.func_76364_f().func_200600_R());
/* 191 */     } else if (modSource.isPhysical() && !modSource.isFistDamage()) {
/* 192 */       LivingEntity attacker = (LivingEntity)source.func_76346_g();
/*     */ 
/*     */       
/* 195 */       conductivity = attacker.func_184614_ca().func_190926_b() ? 1.0F : ModTags.Items.CONDUCTIVE.getValue((IForgeRegistryEntry)attacker.func_184614_ca().func_77973_b());
/*     */     } else {
/* 197 */       return damage;
/*     */     } 
/*     */     
/* 200 */     return damage * conductivity;
/*     */   }
/*     */   
/*     */   private static void handleHandcuffDamage(LivingEntity entity, float damage) {
/* 204 */     for (EffectInstance effectInstance : entity.func_70651_bq()) {
/* 205 */       if (effectInstance.func_188419_a() instanceof HandcuffedEffect) {
/* 206 */         int dur = effectInstance.func_76459_b();
/* 207 */         int newDur = (int)(dur - damage * 30.0F);
/*     */         
/* 209 */         ((EffectInstanceMixin)effectInstance).setDuration(newDur);
/* 210 */         WyNetwork.sendToAllTrackingAndSelf(new SSetEffectDetailsPacket(entity.func_145782_y(), effectInstance), (Entity)entity);
/*     */         
/* 212 */         if (effectInstance.func_76459_b() <= 1 && ((HandcuffedEffect)effectInstance.func_188419_a()).getType() == HandcuffType.KAIROSEKI && 
/* 213 */           entity instanceof PlayerEntity) {
/* 214 */           PlayerEntity player = (PlayerEntity)entity;
/* 215 */           AbilityHelper.enableAbilities((LivingEntity)player, ability -> (ability.getCore().getCategory() == AbilityCategory.DEVIL_FRUITS));
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 220 */       if (effectInstance.func_188419_a() instanceof CaughtInNetEffect) {
/* 221 */         int dur = effectInstance.func_76459_b();
/* 222 */         int newDur = (int)(dur - damage * 50.0F);
/*     */         
/* 224 */         ((EffectInstanceMixin)effectInstance).setDuration(newDur);
/* 225 */         WyNetwork.sendToAllTrackingAndSelf(new SSetEffectDetailsPacket(entity.func_145782_y(), effectInstance), (Entity)entity);
/*     */         
/* 227 */         if (effectInstance.func_76459_b() <= 1 && ((CaughtInNetEffect)effectInstance.func_188419_a()).getType() == NetType.KAIROSEKI && 
/* 228 */           entity instanceof PlayerEntity) {
/* 229 */           PlayerEntity player = (PlayerEntity)entity;
/* 230 */           AbilityHelper.enableAbilities((LivingEntity)player, ability -> (ability.getCore().getCategory() == AbilityCategory.DEVIL_FRUITS));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean handleHandcuffActivation(LivingEntity entity, LivingEntity target, float damage) {
/* 238 */     if (damage <= 0.0F || target.func_110143_aJ() - damage > 0.0F) {
/* 239 */       return false;
/*     */     }
/*     */     
/* 242 */     return HandcuffsItem.handleHandcuffActivation(entity.func_184592_cb(), target, damage);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\DamageCalcsEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */