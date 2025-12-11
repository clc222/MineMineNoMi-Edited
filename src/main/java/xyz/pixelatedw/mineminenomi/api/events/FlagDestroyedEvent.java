/*    */ package xyz.pixelatedw.mineminenomi.api.events;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.FlagTileEntity;
/*    */ 
/*    */ public class FlagDestroyedEvent
/*    */   extends Event
/*    */ {
/*    */   @Nullable
/*    */   Entity destroyer;
/*    */   private BlockState state;
/*    */   private BlockPos pos;
/*    */   private FlagTileEntity flagTile;
/*    */   
/*    */   public FlagDestroyedEvent(BlockState state, BlockPos pos, @Nullable Entity destroyer, FlagTileEntity flagTile) {
/* 20 */     this.destroyer = destroyer;
/* 21 */     this.state = state;
/* 22 */     this.pos = pos;
/* 23 */     this.flagTile = flagTile;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public Entity getDestroyer() {
/* 28 */     return this.destroyer;
/*    */   }
/*    */   
/*    */   public BlockState getState() {
/* 32 */     return this.state;
/*    */   }
/*    */   
/*    */   public BlockPos getPos() {
/* 36 */     return this.pos;
/*    */   }
/*    */   
/*    */   public FlagTileEntity getTile() {
/* 40 */     return this.flagTile;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\events\FlagDestroyedEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */