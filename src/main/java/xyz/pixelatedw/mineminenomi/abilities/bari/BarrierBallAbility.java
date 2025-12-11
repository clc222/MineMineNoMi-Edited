/*    */ package xyz.pixelatedw.mineminenomi.abilities.bari;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.RayTraceResult;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.world.World;
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
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class BarrierBallAbility extends Ability {
/* 29 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "barrier_ball", new Pair[] {
/* 30 */         (Pair)ImmutablePair.of("The user creates a spherical barrier where they're pointing.", null)
/*    */       });
/*    */   
/*    */   private static final int HOLD_TIME = 300;
/*    */   private static final int COOLDOWN = 200;
/* 35 */   public static final AbilityCore<BarrierBallAbility> INSTANCE = (new AbilityCore.Builder("Barrier Ball", AbilityCategory.DEVIL_FRUITS, BarrierBallAbility::new))
/* 36 */     .addDescriptionLine(DESCRIPTION)
/* 37 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(200.0F), ContinuousComponent.getTooltip(300.0F)
/* 38 */       }).build();
/*    */   
/* 40 */   private static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule.Builder(new BlockProtectionRule[] { AirBlockProtectionRule.INSTANCE })).build();
/*    */   
/* 42 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(this::startContinuityEvent).addEndEvent(this::endContinuityEvent);
/*    */   
/* 44 */   private List<BlockPos> posList = new ArrayList<>();
/*    */   
/*    */   public BarrierBallAbility(AbilityCore<BarrierBallAbility> core) {
/* 47 */     super(core);
/*    */     
/* 49 */     this.isNew = true;
/* 50 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent });
/*    */     
/* 52 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 56 */     this.continuousComponent.triggerContinuity(entity, 300.0F);
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 60 */     if (this.posList.isEmpty()) {
/* 61 */       RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)entity, 64.0D);
/* 62 */       World world = entity.field_70170_p;
/* 63 */       if (mop != null) {
/* 64 */         this.posList.addAll(AbilityHelper.createEmptySphere(world, (int)(mop.func_216347_e()).field_72450_a, (int)(mop.func_216347_e()).field_72448_b, (int)(mop.func_216347_e()).field_72449_c, 5, (Block)ModBlocks.BARRIER.get(), GRIEF_RULE));
/* 65 */         this.posList.addAll(AbilityHelper.createEmptySphere(world, (int)(mop.func_216347_e()).field_72450_a, (int)(mop.func_216347_e()).field_72448_b, (int)(mop.func_216347_e()).field_72449_c, 6, (Block)ModBlocks.BARRIER.get(), GRIEF_RULE));
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 71 */     for (BlockPos pos : this.posList) {
/* 72 */       if (entity.field_70170_p.func_180495_p(pos).func_177230_c() == ModBlocks.BARRIER.get()) {
/* 73 */         entity.field_70170_p.func_175656_a(pos, Blocks.field_150350_a.func_176223_P());
/*    */       }
/*    */     } 
/*    */     
/* 77 */     this.posList.clear();
/*    */     
/* 79 */     this.cooldownComponent.startCooldown(entity, 200.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\bari\BarrierBallAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */