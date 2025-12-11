/*    */ package xyz.pixelatedw.mineminenomi.abilities.hie;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.PassiveAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.block.OceanPlantsProtectionRule;
/*    */ 
/*    */ public class FrostWalkerAbility extends PassiveAbility2 {
/* 20 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "frost_walker", new Pair[] {
/* 21 */         (Pair)ImmutablePair.of("Turns all water the user walks on into ice", null), 
/* 22 */         (Pair)ImmutablePair.of("§aCrouching§r temporarily disables the ability", null)
/*    */       });
/* 24 */   public static final AbilityCore<FrostWalkerAbility> INSTANCE = (new AbilityCore.Builder("Frost Walker", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, FrostWalkerAbility::new))
/* 25 */     .addDescriptionLine(DESCRIPTION)
/* 26 */     .build();
/*    */   
/* 28 */   private static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule.Builder(new BlockProtectionRule[] { OceanPlantsProtectionRule.INSTANCE })).addApprovedBlocks(new Block[] { Blocks.field_150355_j }).build();
/*    */   
/*    */   public FrostWalkerAbility(AbilityCore<FrostWalkerAbility> ability) {
/* 31 */     super(ability);
/*    */     
/* 33 */     addDuringPassiveEvent(this::duringPassiveEvent);
/*    */   }
/*    */   
/*    */   private void duringPassiveEvent(LivingEntity entity) {
/* 37 */     if (entity.field_70170_p.field_72995_K) {
/*    */       return;
/*    */     }
/*    */     
/* 41 */     if (entity.func_213453_ef() || entity.func_70090_H() || entity.func_184187_bx() != null || entity.func_110143_aJ() < entity.func_110138_aP() / 5.0F) {
/*    */       return;
/*    */     }
/*    */     
/* 45 */     if (entity instanceof PlayerEntity && 
/* 46 */       ((PlayerEntity)entity).field_71075_bZ.field_75100_b) {
/*    */       return;
/*    */     }
/*    */ 
/*    */     
/* 51 */     BlockPos.Mutable mutpos = new BlockPos.Mutable();
/* 52 */     for (int x = -1; x <= 1; x++) {
/* 53 */       for (int y = -2; y <= 2; y++) {
/* 54 */         for (int z = -1; z <= 1; z++) {
/* 55 */           mutpos.func_189532_c(entity.func_226277_ct_() + x, entity.func_226278_cu_() + y, entity.func_226281_cx_() + z);
/* 56 */           if (AbilityHelper.placeBlockIfAllowed(entity, (BlockPos)mutpos, Blocks.field_185778_de.func_176223_P(), 3, GRIEF_RULE))
/* 57 */             entity.field_70170_p.func_205220_G_().func_205360_a((BlockPos)mutpos, Blocks.field_185778_de, MathHelper.func_76136_a(entity.func_70681_au(), 20, 60)); 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\hie\FrostWalkerAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */