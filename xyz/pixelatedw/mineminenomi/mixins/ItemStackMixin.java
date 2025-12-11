/*    */ package xyz.pixelatedw.mineminenomi.mixins;
/*    */ 
/*    */ import java.util.function.Consumer;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({ItemStack.class})
/*    */ public class ItemStackMixin
/*    */ {
/*    */   @Inject(method = {"hurtAndBreak"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public <T extends LivingEntity> void hurtAndBreak(int amount, T entity, Consumer<T> onBroken, CallbackInfo callback) {
/* 22 */     Item item = ((ItemStack)this).func_77973_b();
/* 23 */     if (item.func_219967_s() != null || CommonConfig.INSTANCE.isItemBannedFromImbuing(item)) {
/*    */       return;
/*    */     }
/* 26 */     boolean hakiActiveFlag = HakiHelper.hasImbuingActive((LivingEntity)entity);
/* 27 */     if (hakiActiveFlag)
/* 28 */       callback.cancel(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\ItemStackMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */