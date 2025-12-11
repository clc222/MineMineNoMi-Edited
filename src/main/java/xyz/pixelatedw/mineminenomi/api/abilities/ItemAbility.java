/*    */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*    */ 
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.Util;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ 
/*    */ @Deprecated
/*    */ public abstract class ItemAbility
/*    */   extends ContinuousAbility implements IParallelContinuousAbility {
/*    */   public ItemAbility(AbilityCore core) {
/* 14 */     super(core);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void startContinuity(PlayerEntity player) {
/* 23 */     ItemStack stack = player.func_184614_ca();
/* 24 */     if (getItemStack(player).func_190926_b()) {
/*    */       
/* 26 */       player.func_145747_a((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_EMPTY_STACK), Util.field_240973_b_);
/*    */       
/*    */       return;
/*    */     } 
/* 30 */     if (!stack.func_190926_b() && !stack.equals(getItemStack(player))) {
/*    */       
/* 32 */       player.func_145747_a((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_ANOTHER_ITEM_IN_HAND), Util.field_240973_b_);
/*    */       
/*    */       return;
/*    */     } 
/* 36 */     super.startContinuity(player);
/* 37 */     if (canSpawnItem(player))
/* 38 */       player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, getItemStack(player)); 
/*    */   }
/*    */   
/*    */   public abstract ItemStack getItemStack(PlayerEntity paramPlayerEntity);
/*    */   
/*    */   public abstract boolean canSpawnItem(PlayerEntity paramPlayerEntity);
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\ItemAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */