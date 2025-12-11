/*    */ package xyz.pixelatedw.mineminenomi.events;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.IBlockDisplayReader;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.eventbus.api.EventPriority;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ 
/*    */ @EventBusSubscriber(value = {Dist.CLIENT}, modid = "mineminenomi")
/*    */ public class ColorHandlerEvent {
/* 14 */   static int greenShade = 5025616;
/*    */   
/*    */   @SubscribeEvent(priority = EventPriority.HIGHEST)
/*    */   public static void registerLeavesColors(net.minecraftforge.client.event.ColorHandlerEvent.Block event) {
/* 18 */     event.getBlockColors().func_186722_a((state, world, pos, tintIndex) -> greenShade, new Block[] { (Block)ModBlocks.MANGROVE_LEAVES.get() });
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\ColorHandlerEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */