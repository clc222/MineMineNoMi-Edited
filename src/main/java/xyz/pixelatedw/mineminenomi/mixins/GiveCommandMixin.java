/*    */ package xyz.pixelatedw.mineminenomi.mixins;
/*    */ 
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import java.util.Collection;
/*    */ import net.minecraft.command.CommandSource;
/*    */ import net.minecraft.command.arguments.ItemInput;
/*    */ import net.minecraft.command.impl.GiveCommand;
/*    */ import net.minecraft.entity.item.ItemEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import net.minecraft.util.SoundEvents;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import net.minecraft.world.World;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*    */ 
/*    */ 
/*    */ @Mixin({GiveCommand.class})
/*    */ public class GiveCommandMixin
/*    */ {
/*    */   @Inject(method = {"giveItem"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private static void giveItem(CommandSource source, ItemInput item, Collection<ServerPlayerEntity> targets, int count, CallbackInfoReturnable<Integer> callback) {
/* 31 */     if (CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic() && item.func_197319_a() instanceof AkumaNoMiItem) {
/* 32 */       source.func_197021_a((ITextComponent)new StringTextComponent("/give command cannot give fruit items while One Fruit per World is enabled!"));
/* 33 */       callback.cancel();
/*    */     } 
/*    */     
/* 36 */     if (CommonConfig.INSTANCE.getRandomizedFruits() && item.func_197319_a() instanceof AkumaNoMiItem) {
/*    */       
/* 38 */       for (ServerPlayerEntity serverplayerentity : targets) {
/*    */         
/* 40 */         int i = count;
/*    */         
/* 42 */         while (i > 0) {
/*    */           
/* 44 */           int j = Math.min(item.func_197319_a().func_77639_j(), i);
/* 45 */           i -= j;
/*    */           
/*    */           try {
/* 48 */             ItemStack oldStack = item.func_197320_a(j, false);
/* 49 */             AkumaNoMiItem fruit = ((AkumaNoMiItem)oldStack.func_77973_b()).getReverseShiftedFruit((World)source.func_197023_e());
/* 50 */             ItemStack itemstack = new ItemStack((IItemProvider)fruit);
/* 51 */             itemstack.func_190920_e(j);
/* 52 */             boolean flag = serverplayerentity.field_71071_by.func_70441_a(itemstack);
/* 53 */             if (flag && itemstack.func_190926_b()) {
/*    */               
/* 55 */               itemstack.func_190920_e(1);
/* 56 */               serverplayerentity.field_70170_p.func_184148_a((PlayerEntity)null, serverplayerentity.func_226277_ct_(), serverplayerentity.func_226278_cu_(), serverplayerentity.func_226281_cx_(), SoundEvents.field_187638_cR, SoundCategory.PLAYERS, 0.2F, ((serverplayerentity.func_70681_au().nextFloat() - serverplayerentity.func_70681_au().nextFloat()) * 0.7F + 1.0F) * 2.0F);
/* 57 */               serverplayerentity.field_71069_bz.func_75142_b();
/*    */               
/*    */               continue;
/*    */             } 
/* 61 */             ItemEntity itementity = serverplayerentity.func_71019_a(itemstack, false);
/* 62 */             if (itementity != null)
/*    */             {
/* 64 */               itementity.func_174868_q();
/* 65 */               itementity.func_200217_b(serverplayerentity.func_110124_au());
/*    */             }
/*    */           
/*    */           }
/* 69 */           catch (CommandSyntaxException e) {
/*    */             
/* 71 */             e.printStackTrace();
/*    */           } 
/*    */         } 
/*    */       } 
/* 75 */       callback.setReturnValue(Integer.valueOf(targets.size()));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\GiveCommandMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */