/*    */ package xyz.pixelatedw.mineminenomi.abilities.bari;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
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
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.bari.BarrierbilityStairsProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ 
/*    */ public class BarrierbilityStairsAbility extends Ability {
/* 25 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "barrierbility_stairs", new Pair[] {
/* 26 */         (Pair)ImmutablePair.of("By shaping the Barrier, the user can create stairs.", null)
/*    */       });
/*    */   
/*    */   private static final int COOLDOWN = 100;
/* 30 */   public static final AbilityCore<BarrierbilityStairsAbility> INSTANCE = (new AbilityCore.Builder("Barrierbility Stairs", AbilityCategory.DEVIL_FRUITS, BarrierbilityStairsAbility::new))
/* 31 */     .addDescriptionLine(DESCRIPTION)
/* 32 */     .addAdvancedDescriptionLine(new AbilityDescriptionLine.IDescriptionLine[] { AbilityDescriptionLine.NEW_LINE, CooldownComponent.getTooltip(100.0F), ContinuousComponent.getTooltip()
/* 33 */       }).build();
/*    */   
/* 35 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(this::startContinuityEvent).addEndEvent(this::endContinuityEvent);
/*    */   
/* 37 */   private List<BlockPos> posList = new ArrayList<>();
/*    */   
/*    */   public BarrierbilityStairsAbility(AbilityCore<BarrierbilityStairsAbility> core) {
/* 40 */     super(core);
/*    */     
/* 42 */     this.isNew = true;
/* 43 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent });
/*    */     
/* 45 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 49 */     this.continuousComponent.triggerContinuity(entity);
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 53 */     if (this.posList.isEmpty()) {
/* 54 */       BarrierbilityStairsProjectile proj = new BarrierbilityStairsProjectile(entity.field_70170_p, entity);
/* 55 */       entity.field_70170_p.func_217376_c((Entity)proj);
/* 56 */       proj.func_234612_a_((Entity)entity, entity.field_70125_A, entity.field_70177_z, 0.0F, 2.0F, 1.0F);
/*    */     } 
/*    */   }
/*    */   
/*    */   private void endContinuityEvent(LivingEntity entity, IAbility ability) {
/* 61 */     for (BlockPos pos : this.posList) {
/* 62 */       if (entity.field_70170_p.func_180495_p(pos).func_177230_c() == ModBlocks.BARRIER.get()) {
/* 63 */         entity.field_70170_p.func_175656_a(pos, Blocks.field_150350_a.func_176223_P());
/*    */       }
/*    */     } 
/* 66 */     this.posList.clear();
/* 67 */     this.cooldownComponent.startCooldown(entity, 100.0F);
/*    */   }
/*    */   
/*    */   public void fillBlocksList(List<BlockPos> entityList) {
/* 71 */     this.posList.addAll(entityList);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\bari\BarrierbilityStairsAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */