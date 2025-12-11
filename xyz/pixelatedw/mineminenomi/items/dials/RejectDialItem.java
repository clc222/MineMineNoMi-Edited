/*    */ package xyz.pixelatedw.mineminenomi.items.dials;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.BlockItem;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*    */ 
/*    */ public class RejectDialItem extends BlockItem {
/* 15 */   private static final DamageSource REJECT_DIAL = (new DamageSource("reject_dial")).func_76348_h().func_82726_p().func_151518_m();
/*    */ 
/*    */   
/*    */   public RejectDialItem(Block block) {
/* 19 */     super(block, (new Item.Properties()).func_200916_a(ModCreativeTabs.MISC).func_200917_a(16));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_77644_a(ItemStack itemStack, LivingEntity target, LivingEntity attacker) {
/* 25 */     if (!attacker.field_70170_p.field_72995_K && attacker instanceof PlayerEntity) {
/*    */       
/* 27 */       PlayerEntity playerAttacker = (PlayerEntity)attacker;
/* 28 */       if (playerAttacker.func_184811_cZ().func_185143_a(getItem(), 0.0F) > 0.0F) {
/* 29 */         return false;
/*    */       }
/* 31 */       playerAttacker.func_184811_cZ().func_185145_a(getItem(), 400);
/* 32 */       playerAttacker.func_184586_b(playerAttacker.func_184600_cs()).func_190918_g(1);
/*    */       
/* 34 */       playerAttacker.func_195064_c(new EffectInstance(Effects.field_76437_t, 600, 2, false, false));
/* 35 */       playerAttacker.func_195064_c(new EffectInstance(Effects.field_76421_d, 600, 1, false, false));
/* 36 */       playerAttacker.func_195064_c(new EffectInstance(Effects.field_76419_f, 600, 1, false, false));
/*    */       
/* 38 */       if (!playerAttacker.func_184812_l_())
/* 39 */         attacker.func_70097_a(REJECT_DIAL, attacker.func_110138_aP() - 1.0F); 
/* 40 */       target.func_70097_a(REJECT_DIAL, target.func_110138_aP() + 1.0F);
/* 41 */       return true;
/*    */     } 
/*    */     
/* 44 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\dials\RejectDialItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */