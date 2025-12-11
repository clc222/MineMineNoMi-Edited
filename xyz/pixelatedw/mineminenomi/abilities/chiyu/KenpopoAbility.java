/*    */ package xyz.pixelatedw.mineminenomi.abilities.chiyu;
/*    */ import java.util.function.Predicate;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.PunchAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTriggerComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEntityPredicates;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.items.DandelionItem;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ public class KenpopoAbility extends PunchAbility2 {
/* 30 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "kenpopo", new Pair[] {
/* 31 */         (Pair)ImmutablePair.of("Takes an ally's life force, transforming it into a Dandelion. Dandelions can be eaten for healing", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 1000.0F;
/* 35 */   public static final AbilityCore<KenpopoAbility> INSTANCE = (new AbilityCore.Builder("Kenpopo", AbilityCategory.DEVIL_FRUITS, KenpopoAbility::new))
/* 36 */     .addDescriptionLine(DESCRIPTION)
/* 37 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(1000.0F)
/* 38 */       }).setSourceType(new SourceType[] { SourceType.FIST
/* 39 */       }).build();
/*    */   
/*    */   public KenpopoAbility(AbilityCore<KenpopoAbility> core) {
/* 42 */     super(core);
/*    */     
/* 44 */     this.hitTriggerComponent.setBypassSameGroupProtection();
/*    */     
/* 46 */     this.hitTriggerComponent.addTryHitEvent(this::tryHitEvent);
/*    */   }
/*    */   
/*    */   private HitTriggerComponent.HitResult tryHitEvent(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/* 50 */     if (canActivate().test(entity)) {
/* 51 */       source.setBypassGroupProtection();
/*    */     }
/* 53 */     return HitTriggerComponent.HitResult.PASS;
/*    */   }
/*    */ 
/*    */   
/*    */   public Predicate<LivingEntity> canActivate() {
/* 58 */     return entity -> this.continuousComponent.isContinuous();
/*    */   }
/*    */ 
/*    */   
/*    */   public int getUseLimit() {
/* 63 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getPunchCooldown() {
/* 68 */     return 1000.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onHitEffect(LivingEntity entity, LivingEntity target, ModDamageSource source) {
/* 73 */     if (!this.continuousComponent.isContinuous()) {
/* 74 */       return true;
/*    */     }
/*    */     
/* 77 */     Predicate<Entity> isAllyPredicate = ModEntityPredicates.getFriendlyFactions(entity);
/*    */     
/* 79 */     if (target.func_70660_b(Effects.field_76437_t) != null || !isAllyPredicate.test(target)) {
/* 80 */       return false;
/*    */     }
/*    */     
/* 83 */     float healAmount = target.func_110143_aJ() * 0.25F;
/*    */     
/* 85 */     ItemStack dandelionStack = new ItemStack((IItemProvider)ModItems.DANDELION.get());
/*    */     
/* 87 */     DandelionItem.setHealAmount(dandelionStack, healAmount);
/*    */     
/* 89 */     if (entity instanceof PlayerEntity) {
/* 90 */       ((PlayerEntity)entity).func_191521_c(dandelionStack);
/*    */     }
/*    */     
/* 93 */     target.func_70606_j(target.func_110143_aJ() - healAmount);
/*    */     
/* 95 */     target.func_195064_c(new EffectInstance(Effects.field_76437_t, 1800, 0));
/*    */     
/* 97 */     WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.CHIYUPOPO.get(), (Entity)entity, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_());
/*    */     
/* 99 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\chiyu\KenpopoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */