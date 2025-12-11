/*     */ package xyz.pixelatedw.mineminenomi.packets.client.trade;
/*     */ 
/*     */ import java.util.Optional;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.fml.network.NetworkDirection;
/*     */ import net.minecraftforge.fml.network.NetworkEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.TradeEntry;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.TraderEntity;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.Currency;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.StatChangeSource;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ 
/*     */ public class CBuyFromTraderPacket
/*     */ {
/*     */   private int traderId;
/*     */   private ItemStack stack;
/*     */   
/*     */   public CBuyFromTraderPacket(int traderId, ItemStack stack, int amount) {
/*  31 */     this.traderId = traderId;
/*  32 */     this.stack = stack;
/*  33 */     this.amount = amount;
/*     */   }
/*     */   private int amount;
/*     */   public CBuyFromTraderPacket() {}
/*     */   public void encode(PacketBuffer buffer) {
/*  38 */     buffer.writeInt(this.traderId);
/*  39 */     buffer.writeItemStack(this.stack, true);
/*  40 */     buffer.writeInt(this.amount);
/*     */   }
/*     */ 
/*     */   
/*     */   public static CBuyFromTraderPacket decode(PacketBuffer buffer) {
/*  45 */     CBuyFromTraderPacket msg = new CBuyFromTraderPacket();
/*  46 */     msg.traderId = buffer.readInt();
/*  47 */     msg.stack = buffer.func_150791_c();
/*  48 */     msg.amount = buffer.readInt();
/*  49 */     return msg;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void handle(CBuyFromTraderPacket message, Supplier<NetworkEvent.Context> ctx) {
/*  54 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
/*     */     {
/*  56 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*     */             ServerPlayerEntity player = ((NetworkEvent.Context)ctx.get()).getSender();
/*     */             
/*     */             TraderEntity trader = (player.field_70170_p.func_73045_a(message.traderId) instanceof TraderEntity) ? (TraderEntity)player.field_70170_p.func_73045_a(message.traderId) : null;
/*     */             
/*     */             if (trader == null) {
/*     */               return;
/*     */             }
/*     */             
/*     */             if (player.func_70068_e((Entity)trader) > 25.0D || message.amount <= 0) {
/*     */               return;
/*     */             }
/*     */             
/*     */             ItemStack stack = message.stack;
/*     */             
/*     */             if (stack == null || stack.func_190926_b()) {
/*     */               return;
/*     */             }
/*     */             
/*     */             Optional<TradeEntry> optional = trader.getTradingItems().stream().filter(()).findFirst();
/*     */             
/*     */             if (!optional.isPresent()) {
/*     */               return;
/*     */             }
/*     */             
/*     */             TradeEntry tradeEntry = optional.get();
/*     */             
/*     */             long emptySlots = player.field_71071_by.field_70462_a.stream().filter(()).count();
/*     */             
/*     */             if (emptySlots < MathHelper.func_76123_f((message.amount / 64))) {
/*     */               return;
/*     */             }
/*     */             
/*     */             if (message.amount > tradeEntry.getCount() && !tradeEntry.hasInfiniteStock()) {
/*     */               return;
/*     */             }
/*     */             
/*     */             IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*     */             
/*     */             int totalPrice = tradeEntry.getPrice() * message.amount;
/*     */             
/*     */             long currency = (trader.getCurrency() == Currency.BELLY) ? props.getBelly() : props.getExtol();
/*     */             
/*     */             if (currency < totalPrice) {
/*     */               return;
/*     */             }
/*     */             
/*     */             ItemStack boughtStack = new ItemStack((IItemProvider)tradeEntry.getItemStack().func_77973_b());
/*     */             boughtStack.func_190920_e(message.amount);
/*     */             if (tradeEntry.getItemStack().func_196082_o().func_74767_n("isClone")) {
/*     */               boughtStack.func_196082_o().func_74757_a("isClone", true);
/*     */             }
/*     */             if (!tradeEntry.hasInfiniteStock()) {
/*     */               int count = tradeEntry.getCount() - message.amount;
/*     */               if (count <= 0) {
/*     */                 trader.getTradingItems().remove(tradeEntry);
/*     */               } else {
/*     */                 tradeEntry.setCount(count);
/*     */               } 
/*     */             } 
/*     */             player.field_71071_by.func_70441_a(boughtStack);
/*     */             if (trader.getCurrency() == Currency.BELLY) {
/*     */               props.alterBelly(-totalPrice, StatChangeSource.NATURAL);
/*     */             } else if (trader.getCurrency() == Currency.EXTOL) {
/*     */               props.alterExtol(-totalPrice, StatChangeSource.NATURAL);
/*     */             } 
/*     */             WyNetwork.sendTo(new SSyncEntityStatsPacket(player.func_145782_y(), props), (PlayerEntity)player);
/*     */             trader.setTradingItems(trader.getTradingItems());
/*     */           });
/*     */     }
/* 126 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\client\trade\CBuyFromTraderPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */