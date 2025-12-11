/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.util.ActionResultType;
/*    */ import net.minecraft.util.Hand;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.TraderEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.enums.Currency;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.CurrencyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModLootTables;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.trade.SUpdateTraderOffersPacket;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenTraderScreenPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*    */ 
/*    */ public class SkypieanTraderEntity
/*    */   extends TraderEntity {
/* 21 */   private int dirtTradesLeft = 120;
/*    */ 
/*    */   
/*    */   public SkypieanTraderEntity(EntityType type, World world) {
/* 25 */     super(type, world, MobsHelper.SKYPEAN_TRADERS_TEXTURES);
/* 26 */     setCanBuyFromPlayers();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canTrade(PlayerEntity player) {
/* 32 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getTradeTable() {
/* 38 */     return ModLootTables.SKYPIEAN_TRADER;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_213281_b(CompoundNBT nbt) {
/* 44 */     super.func_213281_b(nbt);
/* 45 */     nbt.func_74768_a("tradesLeft", this.dirtTradesLeft);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_70037_a(CompoundNBT nbt) {
/* 51 */     super.func_70037_a(nbt);
/* 52 */     this.dirtTradesLeft = nbt.func_74762_e("tradesLeft");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
/* 58 */     if (!player.field_70170_p.field_72995_K) {
/*    */       
/* 60 */       WyNetwork.sendTo(new SOpenTraderScreenPacket(func_145782_y()), player);
/* 61 */       WyNetwork.sendTo(new SUpdateTraderOffersPacket(func_145782_y(), this.tradeEntries, this.dirtTradesLeft), player);
/* 62 */       return ActionResultType.SUCCESS;
/*    */     } 
/* 64 */     return ActionResultType.PASS;
/*    */   }
/*    */ 
/*    */   
/*    */   public void removeTradesLeft(int value) {
/* 69 */     if (this.dirtTradesLeft - value > 0) {
/* 70 */       this.dirtTradesLeft -= value;
/*    */     } else {
/* 72 */       this.dirtTradesLeft = 0;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void setTradesLeft(int value) {
/* 77 */     this.dirtTradesLeft = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getTradesLeft() {
/* 82 */     return this.dirtTradesLeft;
/*    */   }
/*    */ 
/*    */   
/*    */   public long getExtolLeftInStock() {
/* 87 */     return CurrencyHelper.getExtolFromBelly(this.dirtTradesLeft);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getTradeFailMessage() {
/* 93 */     return "";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Currency getCurrency() {
/* 99 */     return Currency.EXTOL;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\SkypieanTraderEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */