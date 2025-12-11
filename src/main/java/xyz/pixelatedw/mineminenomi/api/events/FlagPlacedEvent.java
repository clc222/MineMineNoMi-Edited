/*    */ package xyz.pixelatedw.mineminenomi.api.events;
/*    */ 
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraftforge.eventbus.api.Cancelable;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.FlagTileEntity;
/*    */ 
/*    */ @Cancelable
/*    */ public class FlagPlacedEvent
/*    */   extends Event {
/*    */   private PlayerEntity placer;
/*    */   private BlockState state;
/*    */   private BlockPos pos;
/*    */   private FlagTileEntity flagTile;
/*    */   
/*    */   public FlagPlacedEvent(PlayerEntity placer, BlockState state, BlockPos pos, FlagTileEntity flagTile) {
/* 19 */     this.placer = placer;
/* 20 */     this.state = state;
/* 21 */     this.pos = pos;
/* 22 */     this.flagTile = flagTile;
/*    */   }
/*    */   
/*    */   public PlayerEntity getPlacer() {
/* 26 */     return this.placer;
/*    */   }
/*    */   
/*    */   public BlockState getState() {
/* 30 */     return this.state;
/*    */   }
/*    */   
/*    */   public BlockPos getPos() {
/* 34 */     return this.pos;
/*    */   }
/*    */   
/*    */   public FlagTileEntity getTile() {
/* 38 */     return this.flagTile;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\events\FlagPlacedEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */