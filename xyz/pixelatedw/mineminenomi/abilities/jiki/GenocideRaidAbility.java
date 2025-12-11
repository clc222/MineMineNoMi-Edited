/*     */ package xyz.pixelatedw.mineminenomi.abilities.jiki;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.sabi.RustSkinAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ProjectileComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*     */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.jiki.GenocideRaidEffectEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.jiki.GenocideRaidProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ 
/*     */ public class GenocideRaidAbility extends Ability {
/*  34 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "genocide_raid", new Pair[] {
/*  35 */         (Pair)ImmutablePair.of("Uses %s magnetic items and sends them towards the target, spiriling around it and dealing damage over time.", new Object[] { AbilityHelper.mentionText(Integer.valueOf(20)) }) });
/*     */   
/*     */   public static final int REQUIRED_IRON = 20;
/*     */   private static final int COOLDOWN = 200;
/*     */   public static final int EFFECT_TIMER = 100;
/*  40 */   public static final AbilityCore<GenocideRaidAbility> INSTANCE = (new AbilityCore.Builder("Genocide Raid", AbilityCategory.DEVIL_FRUITS, GenocideRaidAbility::new))
/*  41 */     .addDescriptionLine(DESCRIPTION)
/*  42 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F)
/*  43 */       }).setSourceHakiNature(SourceHakiNature.IMBUING)
/*  44 */     .setSourceElement(SourceElement.METAL)
/*  45 */     .setSourceType(new SourceType[] { SourceType.BLUNT
/*  46 */       }).build();
/*     */   
/*  48 */   private final ProjectileComponent projectileComponent = new ProjectileComponent((IAbility)this, this::createProjectile);
/*     */   
/*     */   public GenocideRaidAbility(AbilityCore<GenocideRaidAbility> core) {
/*  51 */     super(core);
/*     */     
/*  53 */     this.isNew = true;
/*  54 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.projectileComponent });
/*     */     
/*  56 */     addCanUseCheck(JikiHelper.getMetalicItemsCheck(20));
/*  57 */     addUseEvent(this::useEvent);
/*     */   }
/*     */   
/*     */   private void useEvent(LivingEntity entity, IAbility ability) {
/*  61 */     JikiHelper.spawnAttractEffect((Entity)entity);
/*  62 */     this.projectileComponent.shoot(entity, 6.0F, 0.0F);
/*  63 */     this.cooldownComponent.startCooldown(entity, 200.0F);
/*     */   }
/*     */   
/*     */   private GenocideRaidProjectile createProjectile(LivingEntity entity) {
/*  67 */     List<ItemStack> inventory = ItemsHelper.getAllInventoryItems(entity);
/*  68 */     List<ItemStack> magneticItems = JikiHelper.getMagneticItemsNeeded(inventory, 20.0F);
/*     */     
/*  70 */     GenocideRaidProjectile proj = new GenocideRaidProjectile(entity.field_70170_p, entity);
/*     */     
/*  72 */     proj.onEntityImpactEvent = (target -> {
/*     */         IAbilityData abilityDataProps = AbilityDataCapability.get(target);
/*     */         
/*     */         if (abilityDataProps != null) {
/*     */           RustSkinAbility rustSkinAbility = (RustSkinAbility)abilityDataProps.getPassiveAbility(RustSkinAbility.INSTANCE);
/*     */           
/*     */           if (rustSkinAbility != null && !rustSkinAbility.isPaused()) {
/*     */             proj.func_174812_G();
/*     */             
/*     */             return;
/*     */           } 
/*     */         } 
/*     */         
/*     */         target.func_195064_c(new EffectInstance((Effect)ModEffects.GENOCIDE_RAID.get(), 100, 0));
/*     */         
/*     */         JikiHelper.spawnAttractEffect((Entity)target);
/*     */         
/*     */         GenocideRaidEffectEntity effect = new GenocideRaidEffectEntity(entity.field_70170_p);
/*     */         
/*     */         effect.func_70012_b(target.func_226277_ct_(), target.func_226278_cu_() + 1.0D, target.func_226281_cx_(), 0.0F, 0.0F);
/*     */         
/*     */         effect.setTarget(target);
/*     */         
/*     */         effect.setOwner(entity);
/*     */         effect.setItemsUsed(magneticItems);
/*     */         entity.field_70170_p.func_217376_c((Entity)effect);
/*     */       });
/*  99 */     proj.onBlockImpactEvent = (hitPos -> {
/*     */         GenocideRaidEffectEntity effect = new GenocideRaidEffectEntity(entity.field_70170_p);
/*     */         
/*     */         effect.func_70012_b(hitPos.func_177958_n(), (hitPos.func_177956_o() + 1), hitPos.func_177952_p(), 0.0F, 0.0F);
/*     */         
/*     */         effect.setTarget(null);
/*     */         
/*     */         effect.setOwner(entity);
/*     */         effect.setItemsUsed(magneticItems);
/*     */         entity.field_70170_p.func_217376_c((Entity)effect);
/*     */       });
/* 110 */     return proj;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\jiki\GenocideRaidAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */