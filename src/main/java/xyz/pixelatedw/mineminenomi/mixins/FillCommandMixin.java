/*    */ package xyz.pixelatedw.mineminenomi.mixins;
/*    */ 
/*    */ import net.minecraft.command.impl.FillCommand;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.ModifyVariable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({FillCommand.class})
/*    */ public class FillCommandMixin
/*    */ {
/*    */   @ModifyVariable(method = {"fillBlocks"}, at = @At("STORE"), ordinal = 0)
/*    */   private static int areaSize(int x) {
/* 20 */     return 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\FillCommandMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */