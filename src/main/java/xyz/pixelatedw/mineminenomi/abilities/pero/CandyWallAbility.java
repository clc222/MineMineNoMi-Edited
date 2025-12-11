/*    */ package xyz.pixelatedw.mineminenomi.abilities.pero;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ 
/*    */ public class CandyWallAbility extends WallAbility {
/* 20 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "candy_wall", new Pair[] {
/* 21 */         (Pair)ImmutablePair.of("Creates a wall made out of candy in front of the user.", null)
/*    */       });
/* 23 */   private static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule.Builder(new BlockProtectionRule[] { AirBlockProtectionRule.INSTANCE })).build();
/*    */   
/*    */   private static final int COOLDOWN = 100;
/*    */   
/* 27 */   public static final AbilityCore<CandyWallAbility> INSTANCE = (new AbilityCore.Builder("Candy Wall", AbilityCategory.DEVIL_FRUITS, CandyWallAbility::new))
/* 28 */     .addDescriptionLine(DESCRIPTION)
/* 29 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F)
/* 30 */       }).build();
/*    */   
/*    */   public CandyWallAbility(AbilityCore<CandyWallAbility> core) {
/* 33 */     super(core);
/*    */     
/* 35 */     this.continuousComponent.addEndEvent(100, this::endContinuityEvent);
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 39 */     this.cooldownComponent.startCooldown(entity, 100.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getThickness() {
/* 44 */     return 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 49 */     return 4;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getLength() {
/* 54 */     return 3;
/*    */   }
/*    */ 
/*    */   
/*    */   public Block getWallBlock() {
/* 59 */     return (Block)ModBlocks.CANDY.get();
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockProtectionRule getGriefingRule() {
/* 64 */     return GRIEF_RULE;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean stopAfterUse() {
/* 69 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\pero\CandyWallAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */