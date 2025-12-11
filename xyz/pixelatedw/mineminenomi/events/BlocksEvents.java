/*    */ package xyz.pixelatedw.mineminenomi.events;
/*    */ 
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraftforge.event.entity.living.LivingEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.blocks.traps.TrapAbilityBlock;
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class BlocksEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
/* 17 */     LivingEntity entity = event.getEntityLiving();
/*    */     
/* 19 */     BlockState belowBlockState = entity.field_70170_p.func_180495_p(new BlockPos(entity.func_226277_ct_(), entity.func_226278_cu_() + 0.15D, entity.func_226281_cx_()));
/*    */     
/* 21 */     if (belowBlockState.func_177230_c() instanceof TrapAbilityBlock && !entity.field_70170_p.field_72995_K) {
/* 22 */       TrapAbilityBlock block = (TrapAbilityBlock)belowBlockState.func_177230_c();
/*    */       
/* 24 */       if (block.check(entity)) {
/* 25 */         entity.func_70107_b(entity.func_226277_ct_(), entity.func_226278_cu_() + 1.0D, entity.func_226281_cx_());
/* 26 */         entity.field_70143_R = 0.0F;
/* 27 */         entity.func_230245_c_(true);
/*    */       } else {
/* 29 */         if (entity instanceof PlayerEntity) {
/* 30 */           PlayerEntity playerEntity = (PlayerEntity)entity;
/*    */           
/* 32 */           if (!playerEntity.func_184812_l_()) {
/* 33 */             playerEntity.field_71075_bZ.field_75100_b = false;
/*    */           }
/*    */         } 
/*    */         
/* 37 */         if (block.getPotionEffect() != null) {
/* 38 */           entity.func_195064_c(block.getPotionEffect());
/*    */         }
/*    */         
/* 41 */         if (TrapAbilityBlock.isEntityInsideOpaqueBlock(entity, 0))
/* 42 */           entity.func_70097_a(block.getDamageSource(), block.getDamageDealt()); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\BlocksEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */