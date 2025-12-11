/*    */ package xyz.pixelatedw.mineminenomi.abilities.noro;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ItemAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.HitTriggerComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ 
/*    */ public class NoroNoroBeamSwordAbility extends ItemAbility2 {
/* 27 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "noro_noro_beam_sword", new Pair[] {
/* 28 */         (Pair)ImmutablePair.of("Focuses photons inside a hilt to create a sword, which slows enemies upon hit", null)
/*    */       });
/*    */   
/*    */   private static final float COOLDOWN = 20.0F;
/* 32 */   public static final AbilityCore<NoroNoroBeamSwordAbility> INSTANCE = (new AbilityCore.Builder("Noro Noro Beam Sword", AbilityCategory.DEVIL_FRUITS, NoroNoroBeamSwordAbility::new))
/* 33 */     .addDescriptionLine(DESCRIPTION)
/* 34 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(20.0F), ContinuousComponent.getTooltip()
/* 35 */       }).setSourceHakiNature(SourceHakiNature.IMBUING)
/* 36 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 37 */       }).build();
/*    */   
/* 39 */   private final HitTriggerComponent hitTriggerComponent = (new HitTriggerComponent((IAbility)this)).addOnHitEvent(this::hitEvent).addTryHitEvent(this::tryHitEvent);
/*    */   
/*    */   public NoroNoroBeamSwordAbility(AbilityCore<NoroNoroBeamSwordAbility> core) {
/* 42 */     super(core);
/*    */     
/* 44 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.hitTriggerComponent });
/*    */     
/* 46 */     this.continuousComponent.addEndEvent(100, this::endContinuityEvent);
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 50 */     this.cooldownComponent.startCooldown(entity, 20.0F);
/*    */   }
/*    */   
/*    */   private HitTriggerComponent.HitResult tryHitEvent(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/* 54 */     if (!this.continuousComponent.isContinuous()) {
/* 55 */       return HitTriggerComponent.HitResult.PASS;
/*    */     }
/*    */     
/* 58 */     return HitTriggerComponent.HitResult.HIT;
/*    */   }
/*    */   
/*    */   public boolean hitEvent(LivingEntity entity, LivingEntity target, ModDamageSource source, IAbility ability) {
/* 62 */     target.func_195064_c(new EffectInstance((Effect)ModEffects.NORO_SLOWNESS.get(), 40, 1));
/*    */     
/* 64 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack createItemStack(LivingEntity entity) {
/* 69 */     return new ItemStack((IItemProvider)ModWeapons.NORO_NORO_BEAM_SWORD.get());
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\noro\NoroNoroBeamSwordAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */