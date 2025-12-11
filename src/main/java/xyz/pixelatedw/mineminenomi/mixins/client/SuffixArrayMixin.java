/*    */ package xyz.pixelatedw.mineminenomi.mixins.client;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import com.google.common.collect.Sets;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import net.minecraft.client.util.SuffixArray;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({SuffixArray.class})
/*    */ public class SuffixArrayMixin<T>
/*    */ {
/*    */   @Inject(method = {"search"}, at = {@At("RETURN")}, cancellable = true)
/*    */   public void search(CallbackInfoReturnable<List<T>> info) {
/* 25 */     if (CommonConfig.INSTANCE.getRandomizedFruits()) {
/*    */       
/* 27 */       List<T> list = (List<T>)info.getReturnValue();
/* 28 */       Set<T> set = Sets.newLinkedHashSet();
/* 29 */       if (list != null && list.size() > 0)
/*    */       {
/* 31 */         for (T item : list) {
/*    */           
/* 33 */           if (item == null) {
/*    */             continue;
/*    */           }
/* 36 */           if (item instanceof ItemStack) {
/*    */             
/* 38 */             ItemStack stack = (ItemStack)item;
/* 39 */             if (!stack.func_190926_b() && stack.func_77973_b() instanceof xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem) {
/*    */               continue;
/*    */             }
/*    */           } 
/* 43 */           set.add(item);
/*    */         } 
/*    */       }
/* 46 */       info.setReturnValue(Lists.newArrayList(set));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\client\SuffixArrayMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */