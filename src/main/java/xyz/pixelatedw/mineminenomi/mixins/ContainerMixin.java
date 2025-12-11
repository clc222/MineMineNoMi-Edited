/*    */ package xyz.pixelatedw.mineminenomi.mixins;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.inventory.container.ClickType;
/*    */ import net.minecraft.inventory.container.Container;
/*    */ import net.minecraft.inventory.container.Slot;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IWorldPosCallable;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.baku.BakuFactoryAbility;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({Container.class})
/*    */ public class ContainerMixin
/*    */ {
/*    */   @Inject(method = {"stillValid"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private static void isWithinUsableDistance(IWorldPosCallable worldPos, PlayerEntity player, Block targetBlock, CallbackInfoReturnable<Boolean> callback) {
/* 30 */     if (targetBlock == Blocks.field_150462_ai.getBlock()) {
/* 31 */       IAbilityData abilityData = AbilityDataCapability.get((LivingEntity)player);
/* 32 */       BakuFactoryAbility ability = (BakuFactoryAbility)abilityData.getEquippedAbility(BakuFactoryAbility.INSTANCE);
/* 33 */       boolean hasAbility = (ability != null && ability.isContinuous());
/* 34 */       if (hasAbility) {
/* 35 */         callback.setReturnValue(Boolean.valueOf(true));
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Inject(method = {"doClick"}, at = {@At("HEAD")}, cancellable = true)
/*    */   private void doClick(int slotId, int dragType, ClickType clickType, PlayerEntity player, CallbackInfoReturnable<ItemStack> ci) {
/* 44 */     if (CommonConfig.INSTANCE.hasOneFruitPerWorldExtendedLogic()) {
/* 45 */       Container cont = (Container)this;
/* 46 */       if (clickType == ClickType.QUICK_MOVE && (dragType == 0 || dragType == 1)) {
/* 47 */         Slot slot = cont.field_75151_b.get(slotId);
/* 48 */         if (slot != null) {
/* 49 */           ItemStack slotStack = slot.func_75211_c();
/* 50 */           if (!slotStack.func_190926_b() && slotStack.func_77973_b() instanceof xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem && slot.func_82869_a(player))
/* 51 */             ci.setReturnValue(slotStack); 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\ContainerMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */