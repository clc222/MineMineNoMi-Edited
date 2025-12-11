/*    */ package xyz.pixelatedw.mineminenomi.api.helpers;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SoulboundItemHelper
/*    */ {
/*    */   @Nullable
/*    */   public static Pair<UUID, LivingEntity> getOwner(World world, ItemStack itemStack) {
/* 25 */     UUID uuid = itemStack.func_196082_o().func_186857_a("ownerUUID");
/* 26 */     return (Pair<UUID, LivingEntity>)ImmutablePair.of(uuid, ((ServerWorld)world).func_217461_a(uuid));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public static UUID getOwnerUUID(World world, ItemStack itemStack) {
/* 36 */     CompoundNBT tag = itemStack.func_196082_o();
/* 37 */     if (!tag.func_186855_b("ownerUUID")) {
/* 38 */       return null;
/*    */     }
/* 40 */     return tag.func_186857_a("ownerUUID");
/*    */   }
/*    */   
/*    */   public static void setOwner(ItemStack itemStack, LivingEntity owner) {
/* 44 */     itemStack.func_196082_o().func_186854_a("ownerUUID", owner.func_110124_au());
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\helpers\SoulboundItemHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */