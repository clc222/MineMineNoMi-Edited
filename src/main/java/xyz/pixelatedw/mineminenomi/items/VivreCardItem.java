/*    */ package xyz.pixelatedw.mineminenomi.items;
/*    */ 
/*    */ import com.google.common.base.Strings;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import net.minecraft.util.text.TextFormatting;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.VivreCardEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class VivreCardItem
/*    */   extends Item
/*    */ {
/*    */   public VivreCardItem() {
/* 26 */     super((new Item.Properties()).func_200916_a(ModCreativeTabs.MISC).func_200917_a(1));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onDroppedByPlayer(ItemStack itemStack, PlayerEntity player) {
/* 32 */     VivreCardEntity vivreCard = new VivreCardEntity(player.field_70170_p);
/*    */     
/* 34 */     if (itemStack.func_77978_p() == null) {
/* 35 */       return true;
/*    */     }
/* 37 */     LivingEntity owner = (LivingEntity)((ServerWorld)player.field_70170_p).func_217461_a(UUID.fromString(itemStack.func_77978_p().func_74779_i("ownerUUID")));
/*    */     
/* 39 */     if (owner == null)
/* 40 */       return true; 
/* 41 */     vivreCard.func_70012_b(player.func_226277_ct_(), player.func_226278_cu_(), player.func_226281_cx_(), player.field_70177_z, player.field_70125_A);
/*    */     
/* 43 */     float f8 = MathHelper.func_76126_a(player.field_70125_A * 0.017453292F);
/* 44 */     float f2 = MathHelper.func_76134_b(player.field_70125_A * 0.017453292F);
/* 45 */     float f3 = MathHelper.func_76126_a(player.field_70177_z * 0.017453292F);
/* 46 */     float f4 = MathHelper.func_76134_b(player.field_70177_z * 0.017453292F);
/* 47 */     float f5 = Item.field_77697_d.nextFloat() * 6.2831855F;
/* 48 */     float f6 = 0.02F * Item.field_77697_d.nextFloat();
/* 49 */     AbilityHelper.setDeltaMovement((Entity)vivreCard, (-f3 * f2 * 0.3F) + Math.cos(f5) * f6, (-f8 * 0.3F + 0.1F + (Item.field_77697_d.nextFloat() - Item.field_77697_d.nextFloat()) * 0.1F), (f4 * f2 * 0.3F) + Math.sin(f5) * f6);
/*    */     
/* 51 */     vivreCard.setOwner(owner.func_110124_au());
/*    */     
/* 53 */     player.field_70170_p.func_217376_c((Entity)vivreCard);
/*    */     
/* 55 */     itemStack.func_190920_e(0);
/*    */     
/* 57 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_77663_a(ItemStack itemStack, World world, Entity entity, int itemSlot, boolean isSelected) {
/* 63 */     if (world.field_72995_K) {
/*    */       return;
/*    */     }
/* 66 */     if (itemStack.func_77978_p() != null) {
/*    */       
/* 68 */       String uuidString = itemStack.func_77978_p().func_74779_i("ownerUUID");
/* 69 */       if (Strings.isNullOrEmpty(uuidString)) {
/*    */         return;
/*    */       }
/* 72 */       LivingEntity owner = (LivingEntity)((ServerWorld)world).func_217461_a(UUID.fromString(uuidString));
/*    */       
/* 74 */       if (owner != null && owner.func_110143_aJ() <= 0.0F) {
/* 75 */         itemStack.func_190920_e(0);
/*    */       }
/* 77 */     } else if (itemStack.func_77978_p() == null || itemStack.func_77978_p().isEmpty()) {
/*    */       
/* 79 */       setOwner(itemStack, (LivingEntity)entity);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void setOwner(ItemStack itemStack, LivingEntity entity) {
/* 85 */     itemStack.func_196082_o().func_74778_a("ownerUUID", entity.func_110124_au().toString());
/* 86 */     itemStack.func_196082_o().func_74778_a("ownerUsername", entity.func_145748_c_().getString());
/* 87 */     String itemName = itemStack.func_200301_q().getString();
/* 88 */     itemStack.func_200302_a((ITextComponent)new StringTextComponent(TextFormatting.RESET + entity.func_145748_c_().getString() + "'s " + itemName));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\VivreCardItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */