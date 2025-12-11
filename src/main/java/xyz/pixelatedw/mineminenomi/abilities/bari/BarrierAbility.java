/*    */ package xyz.pixelatedw.mineminenomi.abilities.bari;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.WallAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ 
/*    */ public class BarrierAbility
/*    */   extends WallAbility {
/* 23 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "barrier", new Pair[] {
/* 24 */         (Pair)ImmutablePair.of("The user creates an impenetrable wall in front of themselves, which shields them from attacks.", null)
/*    */       });
/*    */   
/*    */   private static final int HOLD_TIME = 600;
/*    */   private static final int MIN_COOLDOWN = 100;
/*    */   private static final int MAX_COOLDOWN = 400;
/* 30 */   public static final AbilityCore<BarrierAbility> INSTANCE = (new AbilityCore.Builder("Barrier", AbilityCategory.DEVIL_FRUITS, BarrierAbility::new))
/* 31 */     .addDescriptionLine(DESCRIPTION)
/* 32 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F, 400.0F), ContinuousComponent.getTooltip(600.0F)
/* 33 */       }).build();
/*    */   
/* 35 */   private static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule.Builder(new BlockProtectionRule[] { AirBlockProtectionRule.INSTANCE, FoliageBlockProtectionRule.INSTANCE })).setBypassGriefRule().build();
/*    */   
/*    */   public BarrierAbility(AbilityCore<BarrierAbility> core) {
/* 38 */     super(core);
/*    */     
/* 40 */     this.continuousComponent.addEndEvent(this::endContinuityEvent);
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 44 */     float cooldown = 100.0F + this.continuousComponent.getContinueTime() / 2.0F;
/* 45 */     this.cooldownComponent.startCooldown(entity, cooldown);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHoldTime() {
/* 50 */     return 600;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getThickness() {
/* 55 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 60 */     return 6;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getLength() {
/* 65 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public Block getWallBlock() {
/* 70 */     return (Block)ModBlocks.BARRIER.get();
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockProtectionRule getGriefingRule() {
/* 75 */     return GRIEF_RULE;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean stopAfterUse() {
/* 80 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\bari\BarrierAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */