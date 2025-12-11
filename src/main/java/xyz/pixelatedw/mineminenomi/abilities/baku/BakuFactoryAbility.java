/*    */ package xyz.pixelatedw.mineminenomi.abilities.baku;
/*    */ 
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.inventory.container.Container;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ContinuousComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ 
/*    */ public class BakuFactoryAbility
/*    */   extends Ability {
/* 23 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "baku_factory", new Pair[] {
/* 24 */         (Pair)ImmutablePair.of("Allows the user to craft items and blocks without the need for a Crafting Table.", null)
/*    */       });
/* 26 */   public static final AbilityCore<BakuFactoryAbility> INSTANCE = (new AbilityCore.Builder("Baku Factory", AbilityCategory.DEVIL_FRUITS, BakuFactoryAbility::new))
/* 27 */     .addDescriptionLine(DESCRIPTION)
/* 28 */     .build();
/*    */   
/* 30 */   private final ContinuousComponent continuousComponent = (new ContinuousComponent((IAbility)this)).addStartEvent(100, this::startContinuityEvent).addTickEvent(100, this::tickContinuityEvent);
/*    */   
/*    */   private BlockState previousBlock;
/*    */   
/*    */   public BakuFactoryAbility(AbilityCore<BakuFactoryAbility> core) {
/* 35 */     super(core);
/*    */     
/* 37 */     this.isNew = true;
/* 38 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.continuousComponent });
/*    */     
/* 40 */     addUseEvent(this::useEvent);
/*    */   }
/*    */   
/*    */   private void useEvent(LivingEntity entity, IAbility ability) {
/* 44 */     this.continuousComponent.triggerContinuity(entity);
/*    */   }
/*    */   
/*    */   private void startContinuityEvent(LivingEntity entity, IAbility ability) {
/* 48 */     if (entity instanceof PlayerEntity) {
/* 49 */       PlayerEntity player = (PlayerEntity)entity;
/* 50 */       BlockPos pos = player.func_233580_cy_();
/* 51 */       this.previousBlock = player.field_70170_p.func_180495_p(pos);
/* 52 */       player.field_70170_p.func_175656_a(pos, Blocks.field_150462_ai.func_176223_P());
/* 53 */       player.func_213829_a(player.field_70170_p.func_180495_p(pos).func_215699_b(player.field_70170_p, pos));
/* 54 */       player.field_70170_p.func_175656_a(pos, this.previousBlock);
/*    */     } 
/*    */   }
/*    */   
/*    */   private void tickContinuityEvent(LivingEntity entity, IAbility ability) {
/* 59 */     if (!entity.field_70170_p.field_72995_K && entity instanceof PlayerEntity) {
/* 60 */       Container container = ((ServerPlayerEntity)entity).field_71070_bA;
/* 61 */       if (!(container instanceof net.minecraft.inventory.container.WorkbenchContainer))
/* 62 */         this.continuousComponent.stopContinuity(entity); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\baku\BakuFactoryAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */