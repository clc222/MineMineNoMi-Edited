/*    */ package xyz.pixelatedw.mineminenomi.abilities.sui;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.DashAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DealDamageComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ 
/*    */ public class NekomimiPunchAbility
/*    */   extends DashAbility
/*    */ {
/* 22 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "nekomimi_punch", new Pair[] {
/* 23 */         (Pair)ImmutablePair.of("Propels the swimming user forward and deals huge damage to all entities they hit.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 120;
/*    */   private static final float RANGE = 1.6F;
/*    */   private static final int DAMAGE = 20;
/* 29 */   public static final AbilityCore<NekomimiPunchAbility> INSTANCE = (new AbilityCore.Builder("Nekomimi Punch", AbilityCategory.DEVIL_FRUITS, NekomimiPunchAbility::new))
/* 30 */     .addDescriptionLine(DESCRIPTION)
/* 31 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(120.0F), RangeComponent.getTooltip(1.6F, RangeComponent.RangeType.AOE), DealDamageComponent.getTooltip(20.0F)
/* 32 */       }).setSourceHakiNature(SourceHakiNature.HARDENING)
/* 33 */     .setSourceType(new SourceType[] { SourceType.FIST
/* 34 */       }).build();
/*    */   
/*    */   public NekomimiPunchAbility(AbilityCore<NekomimiPunchAbility> core) {
/* 37 */     super(core);
/*    */     
/* 39 */     addCanUseCheck(SuiHelper::isFreeSwimming);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onTargetHit(LivingEntity entity, LivingEntity target, float damage, DamageSource source) {}
/*    */ 
/*    */   
/*    */   public boolean isParallel() {
/* 48 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getDashCooldown() {
/* 53 */     return 120.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getDamage() {
/* 58 */     return 20.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getRange() {
/* 63 */     return 1.6F;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getSpeed() {
/* 68 */     return 3.0D;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHoldTime() {
/* 73 */     return 20;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\sui\NekomimiPunchAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */