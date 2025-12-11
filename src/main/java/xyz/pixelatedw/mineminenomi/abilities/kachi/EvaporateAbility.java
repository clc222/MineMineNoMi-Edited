/*    */ package xyz.pixelatedw.mineminenomi.abilities.kachi;
/*    */ import java.util.List;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.state.Property;
/*    */ import net.minecraft.state.properties.BlockStateProperties;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.world.World;
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
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleEffects;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ public class EvaporateAbility extends Ability {
/* 29 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "evaporate", new Pair[] {
/* 30 */         (Pair)ImmutablePair.of("Evaporates the water and melts ice around the user.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 240;
/*    */   private static final int HOLD_TIME = 120;
/* 35 */   public static final AbilityCore<EvaporateAbility> INSTANCE = (new AbilityCore.Builder("Evaporate", AbilityCategory.DEVIL_FRUITS, EvaporateAbility::new))
/* 36 */     .addDescriptionLine(DESCRIPTION)
/* 37 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(240.0F), ContinuousComponent.getTooltip(120.0F)
/* 38 */       }).build();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static final BlockProtectionRule GRIEF_RULE;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   static {
/* 55 */     GRIEF_RULE = (new BlockProtectionRule.Builder(new BlockProtectionRule[] { LiquidBlockProtectionRule.INSTANCE, SnowLayerBlockProtectionRule.INSTANCE })).addBannedBlocks(new Block[] { Blocks.field_150353_l }).addReplaceRules((world, pos, state) -> { if (state.func_235901_b_((Property)BlockStateProperties.field_208198_y)) { world.func_175656_a(pos, (BlockState)state.func_206870_a((Property)BlockStateProperties.field_208198_y, Boolean.valueOf(false))); return true; }  if (state.func_185904_a() == Material.field_151588_w || state.func_185904_a() == Material.field_151598_x || state.func_177230_c() == Blocks.field_203214_jx || state.func_177230_c() == Blocks.field_203198_aQ || state.func_177230_c() == Blocks.field_203199_aR) { world.func_175656_a(pos, Blocks.field_150355_j.func_176223_P()); return true; }  return false; }).build();
/*    */   }
/* 57 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addTickEvent(this::duringContinuityEvent).addEndEvent(this::endContinuityEvent);
/*    */   
/*    */   public EvaporateAbility(AbilityCore<EvaporateAbility> core) {
/* 60 */     super(core);
/*    */     
/* 62 */     this.isNew = true;
/* 63 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent });
/*    */     
/* 65 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility abiltiy) {
/* 69 */     this.continuousComponent.triggerContinuity(entity, 120.0F);
/*    */   }
/*    */   
/*    */   private void duringContinuityEvent(LivingEntity entity, IAbility ability) {
/* 73 */     List<BlockPos> coords = AbilityHelper.createFilledSphere(entity.field_70170_p, (int)entity.func_226277_ct_(), (int)entity.func_226278_cu_(), (int)entity.func_226281_cx_(), 6, Blocks.field_150350_a, GRIEF_RULE);
/*    */     
/* 75 */     for (BlockPos pos : coords) {
/* 76 */       if (entity.field_70170_p.func_180495_p(pos).func_177230_c() == Blocks.field_150350_a) {
/* 77 */         WyHelper.spawnParticleEffect((ParticleEffect)ModParticleEffects.EVAPORATE.get(), (Entity)entity, pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p());
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 83 */     this.cooldownComponent.startCooldown(entity, 240.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\kachi\EvaporateAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */