/*    */ package xyz.pixelatedw.mineminenomi.abilities.magu;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.tags.FluidTags;
/*    */ import net.minecraft.tags.ITag;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityDescriptionLine;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.CooldownComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.LavaImmuneProtectionRule;
/*    */ 
/*    */ public class LavaFlowAbility extends Ability {
/* 25 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "lava_flow", new Pair[] {
/* 26 */         (Pair)ImmutablePair.of("The user covers their legs into lava creating a path while walking trough it", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 300;
/*    */   private static final int HOLD_TIME = 100;
/* 31 */   public static final AbilityCore<LavaFlowAbility> INSTANCE = (new AbilityCore.Builder("Lava Flow", AbilityCategory.DEVIL_FRUITS, LavaFlowAbility::new))
/* 32 */     .addDescriptionLine(DESCRIPTION)
/* 33 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(300.0F), ContinuousComponent.getTooltip(100.0F)
/* 34 */       }).build();
/*    */   
/* 36 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this))
/* 37 */     .addStartEvent(this::onContinuityStart)
/* 38 */     .addTickEvent(this::onContinuityTick)
/* 39 */     .addEndEvent(this::onContinuityEnd);
/*    */   
/* 41 */   private static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule.Builder(new BlockProtectionRule[] { DefaultProtectionRules.CORE_FOLIAGE_ORE, LavaImmuneProtectionRule.INSTANCE
/* 42 */       })).addApprovedMaterials(new Material[] { Material.field_151587_i
/* 43 */       }).build();
/*    */   
/* 45 */   private int originY = -1;
/*    */   
/*    */   public LavaFlowAbility(AbilityCore<LavaFlowAbility> core) {
/* 48 */     super(core);
/*    */     
/* 50 */     this.isNew = true;
/*    */     
/* 52 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent });
/*    */     
/* 54 */     addUseEvent(this::onUse);
/*    */   }
/*    */   
/*    */   private void onUse(LivingEntity entity, IAbility ability) {
/* 58 */     this.continuousComponent.triggerContinuity(entity, 100.0F);
/*    */   }
/*    */   
/*    */   private void onContinuityStart(LivingEntity entity, IAbility ability) {
/* 62 */     this.originY = entity.func_233580_cy_().func_177956_o() - 5;
/*    */   }
/*    */   
/*    */   private void onContinuityTick(LivingEntity entity, IAbility ability) {
/* 66 */     if (entity.field_70170_p.field_72995_K) {
/*    */       return;
/*    */     }
/*    */     
/* 70 */     if (this.originY < 0) {
/*    */       return;
/*    */     }
/*    */     
/* 74 */     BlockPos pos = entity.func_233580_cy_().func_177977_b();
/* 75 */     BlockState state = entity.field_70170_p.func_180495_p(pos);
/*    */     
/* 77 */     boolean isBlockBelowOrigin = (pos.func_177956_o() < this.originY);
/* 78 */     boolean areEyesInLava = entity.func_208600_a((ITag)FluidTags.field_206960_b);
/* 79 */     boolean canPlaceBlocks = (GRIEF_RULE.check(entity.field_70170_p, pos, state) && !areEyesInLava);
/*    */     
/* 81 */     if (canPlaceBlocks) {
/* 82 */       AbilityHelper.createFilledSphere(entity.field_70170_p, (int)entity.func_226277_ct_(), (int)entity.func_226278_cu_() - 2, (int)entity.func_226281_cx_(), 4, Blocks.field_150353_l, GRIEF_RULE);
/* 83 */     } else if (areEyesInLava && !isBlockBelowOrigin) {
/* 84 */       AbilityHelper.createFilledSphere(entity.field_70170_p, (int)entity.func_226277_ct_(), (int)entity.func_226278_cu_() + 1, (int)entity.func_226281_cx_(), 3, Blocks.field_150353_l, GRIEF_RULE);
/*    */     } 
/*    */   }
/*    */   
/*    */   private void onContinuityEnd(LivingEntity entity, IAbility ability) {
/* 89 */     this.cooldownComponent.startCooldown(entity, 300.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\magu\LavaFlowAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */