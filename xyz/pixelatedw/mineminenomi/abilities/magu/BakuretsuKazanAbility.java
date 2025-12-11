/*    */ package xyz.pixelatedw.mineminenomi.abilities.magu;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.SoundEvent;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ChargeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.LavaImmuneProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ 
/*    */ public class BakuretsuKazanAbility extends Ability {
/* 25 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "bakuretsu_kazan", new Pair[] {
/* 26 */         (Pair)ImmutablePair.of("By spreading magma to the surroundings, the user turns everything into lava", null)
/*    */       });
/*    */   
/*    */   private static final float MAX_CHARGE_TIME = 100.0F;
/*    */   private static final float MIN_COOLDOWN = 100.0F;
/*    */   private static final float MAX_COOLDOWN = 600.0F;
/* 32 */   public static final AbilityCore<BakuretsuKazanAbility> INSTANCE = (new AbilityCore.Builder("Bakuretsu Kazan", AbilityCategory.DEVIL_FRUITS, BakuretsuKazanAbility::new))
/* 33 */     .addDescriptionLine(DESCRIPTION)
/* 34 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F, 600.0F)
/* 35 */       }).setSourceElement(SourceElement.MAGMA)
/* 36 */     .build();
/*    */   
/*    */   private final ChargeComponent chargeComponent;
/*    */   
/* 40 */   private static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule.Builder(new BlockProtectionRule[] { DefaultProtectionRules.CORE_FOLIAGE_ORE, LavaImmuneProtectionRule.INSTANCE })).build();
/*    */   
/*    */   public BakuretsuKazanAbility(AbilityCore<BakuretsuKazanAbility> core) {
/* 43 */     super(core);
/*    */     this.chargeComponent = (new ChargeComponent((IAbility)this, comp -> (comp.getChargePercentage() >= 0.3D))).addEndEvent(this::onChargeEnd);
/* 45 */     this.isNew = true;
/*    */     
/* 47 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.chargeComponent });
/*    */     
/* 49 */     addUseEvent(this::onUse);
/*    */   }
/*    */   
/*    */   private void onUse(LivingEntity entity, IAbility ability) {
/* 53 */     this.chargeComponent.startCharging(entity, 100.0F);
/*    */   }
/*    */   
/*    */   private void onChargeEnd(LivingEntity entity, IAbility ability) {
/* 57 */     if (entity.field_70170_p.field_72995_K) {
/*    */       return;
/*    */     }
/*    */     
/* 61 */     int range = (int)(this.chargeComponent.getChargePercentage() * 16.0F);
/*    */     
/* 63 */     AbilityHelper.createFilledSphere(entity.field_70170_p, (int)entity.func_226277_ct_(), (int)entity.func_226278_cu_(), (int)entity.func_226281_cx_(), range, Blocks.field_150353_l, GRIEF_RULE);
/*    */     
/* 65 */     entity.field_70170_p.func_184133_a(null, entity.func_233580_cy_(), (SoundEvent)ModSounds.BAKURETSU_KAZAN.get(), SoundCategory.PLAYERS, 10.0F, 1.0F);
/*    */     
/* 67 */     float cooldown = 100.0F + this.chargeComponent.getChargeTime() * 5.0F;
/* 68 */     this.cooldownComponent.startCooldown(entity, cooldown);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\magu\BakuretsuKazanAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */