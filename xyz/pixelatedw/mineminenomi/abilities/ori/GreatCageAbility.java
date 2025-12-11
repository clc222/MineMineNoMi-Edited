/*    */ package xyz.pixelatedw.mineminenomi.abilities.ori;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.LiquidBlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ 
/*    */ public class GreatCageAbility extends Ability {
/* 29 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "great_cage", new Pair[] {
/* 30 */         (Pair)ImmutablePair.of("Creates a big cage trapping the user and all nearby entities in it.", null)
/*    */       });
/*    */   
/*    */   private static final int HOLD_TIME = 1200;
/*    */   private static final int MIN_COOLDOWN = 40;
/*    */   private static final int MAX_COOLDOWN = 340;
/* 36 */   public static final AbilityCore<GreatCageAbility> INSTANCE = (new AbilityCore.Builder("Great Cage", AbilityCategory.DEVIL_FRUITS, GreatCageAbility::new))
/* 37 */     .addDescriptionLine(DESCRIPTION)
/* 38 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(40.0F, 340.0F), ContinuousComponent.getTooltip(1200.0F)
/* 39 */       }).build();
/*    */   
/* 41 */   private static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule.Builder(new BlockProtectionRule[] { AirBlockProtectionRule.INSTANCE, FoliageBlockProtectionRule.INSTANCE, LiquidBlockProtectionRule.INSTANCE })).setBypassGriefRule().build();
/*    */   
/* 43 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addStartEvent(this::startContinuityEvent).addEndEvent(this::endContinuityEvent);
/*    */   
/* 45 */   protected List<BlockPos> blockList = new ArrayList<>();
/*    */   
/*    */   public GreatCageAbility(AbilityCore<GreatCageAbility> core) {
/* 48 */     super(core);
/*    */     
/* 50 */     this.isNew = true;
/* 51 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent });
/*    */     
/* 53 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 57 */     this.continuousComponent.triggerContinuity(entity, 1200.0F);
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 61 */     if (this.blockList.isEmpty()) {
/* 62 */       this.blockList.addAll(AbilityHelper.createEmptyCube((Entity)entity, ((int)entity.func_226277_ct_() - 1), (int)entity.func_226278_cu_(), ((int)entity.func_226281_cx_() - 1), 20, 20, 20, 258, (Block)ModBlocks.ORI_BARS.get(), GRIEF_RULE));
/*    */     }
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 67 */     for (BlockPos pos : this.blockList) {
/* 68 */       Block currentBlock = entity.field_70170_p.func_180495_p(pos).func_177230_c();
/* 69 */       if (currentBlock == ModBlocks.ORI_BARS.get()) {
/* 70 */         entity.field_70170_p.func_175656_a(pos, Blocks.field_150350_a.func_176223_P());
/*    */       }
/*    */     } 
/* 73 */     this.blockList.clear();
/*    */     
/* 75 */     float cooldown = 40.0F + this.continuousComponent.getContinueTime() / 4.0F;
/* 76 */     this.cooldownComponent.startCooldown(entity, cooldown);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\ori\GreatCageAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */