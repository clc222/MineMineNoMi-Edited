/*    */ package xyz.pixelatedw.mineminenomi.abilities.netsu;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.world.server.ServerWorld;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.RangeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class NekkaiJigokuAbility
/*    */   extends Ability {
/* 29 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "nekkai_jigoku", new Pair[] {
/* 30 */         (Pair)ImmutablePair.of("Boils water around the user, damaging entities inside it.", null)
/*    */       });
/*    */   
/*    */   private static final int HOLD_TIME = 600;
/*    */   private static final int MIN_COOLDOWN = 20;
/*    */   private static final int MAX_COOLDOWN = 300;
/*    */   private static final int RANGE = 30;
/* 37 */   public static final AbilityCore<NekkaiJigokuAbility> INSTANCE = (new AbilityCore.Builder("Nekkai Jigoku", AbilityCategory.DEVIL_FRUITS, NekkaiJigokuAbility::new))
/* 38 */     .addDescriptionLine(DESCRIPTION)
/* 39 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(20.0F, 300.0F), ContinuousComponent.getTooltip(600.0F), RangeComponent.getTooltip(30.0F, RangeComponent.RangeType.AOE)
/* 40 */       }).build();
/*    */   
/* 42 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this, true)).addTickEvent(this::duringContinuityEvent);
/* 43 */   private final RangeComponent rangeComponent = new RangeComponent((IAbility)this);
/*    */   
/*    */   public NekkaiJigokuAbility(AbilityCore<NekkaiJigokuAbility> core) {
/* 46 */     super(core);
/*    */     
/* 48 */     this.isNew = true;
/* 49 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent, (AbilityComponent)this.rangeComponent });
/*    */     
/* 51 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 55 */     this.continuousComponent.triggerContinuity(entity, 600.0F);
/*    */   }
/*    */   
/*    */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/* 59 */     List<LivingEntity> targets = this.rangeComponent.getTargetsInArea(entity, 30.0F);
/* 60 */     List<BlockPos> blocks = WyHelper.getNearbyBlocks((Entity)entity, 30);
/*    */     
/* 62 */     for (LivingEntity target : targets) {
/* 63 */       if (target.func_70090_H()) {
/* 64 */         target.func_70097_a(DamageSource.field_76370_b, 2.0F);
/*    */       }
/*    */     } 
/*    */     
/* 68 */     if (!entity.field_70170_p.field_72995_K) {
/* 69 */       BlockPos.Mutable blockUp = new BlockPos.Mutable();
/*    */       
/* 71 */       for (BlockPos blockPos : blocks) {
/* 72 */         blockUp.func_181079_c(blockPos.func_177958_n(), blockPos.func_177956_o() + 1, blockPos.func_177952_p());
/*    */         
/* 74 */         if (entity.field_70170_p.func_180495_p(blockPos).func_177230_c() == Blocks.field_150355_j && entity.field_70170_p.func_180495_p((BlockPos)blockUp).func_177230_c() == Blocks.field_150350_a && this.continuousComponent.getContinueTime() % 5.0F == 0.0F)
/* 75 */           WyHelper.spawnParticles((IParticleData)ParticleTypes.field_197612_e, (ServerWorld)entity.field_70170_p, blockPos.func_177958_n() + WyHelper.randomDouble() / 2.0D, blockPos.func_177956_o() + 0.8D, blockPos.func_177952_p() + WyHelper.randomDouble() / 2.0D); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\netsu\NekkaiJigokuAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */