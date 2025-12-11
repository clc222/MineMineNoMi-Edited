/*     */ package xyz.pixelatedw.mineminenomi.packets.client.trade;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.fml.network.NetworkDirection;
/*     */ import net.minecraftforge.fml.network.NetworkEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.StatChangeSource;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.CurrencyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.SkypieanTraderEntity;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ public class CSellToTraderPacket
/*     */ {
/*     */   private int traderId;
/*     */   private int amount;
/*     */   
/*     */   public CSellToTraderPacket() {}
/*     */   
/*     */   public CSellToTraderPacket(int traderId, int amount) {
/*  32 */     this.traderId = traderId;
/*  33 */     this.amount = amount;
/*     */   }
/*     */ 
/*     */   
/*     */   public void encode(PacketBuffer buffer) {
/*  38 */     buffer.writeInt(this.traderId);
/*  39 */     buffer.writeInt(this.amount);
/*     */   }
/*     */ 
/*     */   
/*     */   public static CSellToTraderPacket decode(PacketBuffer buffer) {
/*  44 */     CSellToTraderPacket msg = new CSellToTraderPacket();
/*  45 */     msg.traderId = buffer.readInt();
/*  46 */     msg.amount = buffer.readInt();
/*  47 */     return msg;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void handle(CSellToTraderPacket message, Supplier<NetworkEvent.Context> ctx) {
/*  52 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
/*     */     {
/*  54 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*     */             ServerPlayerEntity player = ((NetworkEvent.Context)ctx.get()).getSender();
/*     */             
/*     */             Entity target = player.field_70170_p.func_73045_a(message.traderId);
/*     */             
/*     */             if (player.func_70068_e(target) > 25.0D) {
/*     */               return;
/*     */             }
/*     */             
/*     */             if (target != null && target instanceof SkypieanTraderEntity) {
/*     */               SkypieanTraderEntity trader = (SkypieanTraderEntity)target;
/*     */               
/*     */               IEntityStats entityData = EntityStatsCapability.get((LivingEntity)player);
/*     */               
/*     */               int dirtBlocksAvailable = 0;
/*     */               
/*     */               List<ItemStack> markedStacks = new ArrayList<>();
/*     */               
/*     */               for (int i = 0; i < player.field_71071_by.func_70302_i_(); i++) {
/*     */                 ItemStack stack = player.field_71071_by.func_70301_a(i);
/*     */                 
/*     */                 if (stack != null && !stack.func_190926_b() && stack.func_77973_b() == Blocks.field_150346_d.func_199767_j()) {
/*     */                   dirtBlocksAvailable += stack.func_190916_E();
/*     */                   
/*     */                   if (!markedStacks.contains(stack)) {
/*     */                     markedStacks.add(stack);
/*     */                   }
/*     */                 } 
/*     */               } 
/*     */               
/*     */               dirtBlocksAvailable = MathHelper.func_76125_a(dirtBlocksAvailable, 0, message.amount);
/*     */               
/*     */               if (dirtBlocksAvailable >= message.amount) {
/*     */                 int markedBlocks = dirtBlocksAvailable;
/*     */                 
/*     */                 for (ItemStack stack : markedStacks) {
/*     */                   if (markedBlocks <= 0) {
/*     */                     break;
/*     */                   }
/*     */                   int stackSize = stack.func_190916_E();
/*     */                   if (markedBlocks - stackSize >= 0) {
/*     */                     markedBlocks -= stackSize;
/*     */                     stack.func_190918_g(stackSize);
/*     */                     continue;
/*     */                   } 
/*     */                   int blocksNeeded = markedBlocks;
/*     */                   markedBlocks -= blocksNeeded;
/*     */                   stack.func_190918_g(blocksNeeded);
/*     */                 } 
/*     */                 trader.removeTradesLeft(message.amount);
/*     */                 long value = CurrencyHelper.getExtolFromBelly(dirtBlocksAvailable);
/*     */                 entityData.alterExtol(value, StatChangeSource.NATURAL);
/*     */                 WyNetwork.sendTo(new SSyncEntityStatsPacket(player.func_145782_y(), entityData), (PlayerEntity)player);
/*     */               } 
/*     */             } 
/*     */           });
/*     */     }
/* 111 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\packets\client\trade\CSellToTraderPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */