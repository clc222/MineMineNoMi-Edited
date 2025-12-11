/*    */ package xyz.pixelatedw.mineminenomi.mixins;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.PlayerInventory;
/*    */ import net.minecraft.inventory.container.AbstractRepairContainer;
/*    */ import net.minecraft.inventory.container.ContainerType;
/*    */ import net.minecraft.inventory.container.SmithingTableContainer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.crafting.SmithingRecipe;
/*    */ import net.minecraft.util.IWorldPosCallable;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import xyz.pixelatedw.mineminenomi.api.events.SmithingTableEvent;
/*    */ 
/*    */ 
/*    */ @Mixin({SmithingTableContainer.class})
/*    */ public abstract class SmithingTableContainerMixin
/*    */   extends AbstractRepairContainer
/*    */ {
/*    */   @Shadow
/*    */   @Final
/*    */   private World field_234651_g_;
/*    */   @Nullable
/*    */   @Shadow
/*    */   private SmithingRecipe field_234652_h_;
/*    */   private boolean customRecipe;
/*    */   private int baseQty;
/*    */   private int additionQty;
/*    */   
/*    */   public SmithingTableContainerMixin(ContainerType<?> pType, int pContainerId, PlayerInventory pPlayerInventory, IWorldPosCallable pAccess) {
/* 41 */     super(pType, pContainerId, pPlayerInventory, pAccess);
/*    */   }
/*    */   
/*    */   @Inject(method = {"createResult"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void createResult(CallbackInfo ci) {
/* 46 */     this.customRecipe = false;
/* 47 */     SmithingTableContainer container = (SmithingTableContainer)this;
/* 48 */     ItemStack leftSlot = this.field_234643_d_.func_70301_a(0);
/* 49 */     ItemStack rightSlot = this.field_234643_d_.func_70301_a(1);
/*    */     
/* 51 */     SmithingTableEvent event = new SmithingTableEvent(container, this.field_234645_f_, leftSlot, rightSlot);
/* 52 */     MinecraftForge.EVENT_BUS.post((Event)event);
/* 53 */     if (event.getResultSlot().func_190926_b()) {
/*    */       return;
/*    */     }
/*    */     
/* 57 */     if (leftSlot.func_190916_E() < event.getBaseQuantity() || rightSlot.func_190916_E() < event.getAdditionQuantity()) {
/*    */       return;
/*    */     }
/*    */     
/* 61 */     this.baseQty = event.getBaseQuantity();
/* 62 */     this.additionQty = event.getAdditionQuantity();
/* 63 */     this.customRecipe = true;
/*    */     
/* 65 */     ItemStack itemstack = event.getResultSlot();
/* 66 */     this.field_234642_c_.func_70299_a(0, itemstack);
/* 67 */     ci.cancel();
/*    */   }
/*    */   
/*    */   @Inject(method = {"onTake"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void onTake(PlayerEntity player, ItemStack inputItem, CallbackInfoReturnable<ItemStack> cir) {
/* 72 */     if (this.customRecipe) {
/* 73 */       inputItem.func_77980_a(player.field_70170_p, player, inputItem.func_190916_E());
/* 74 */       this.field_234642_c_.func_201560_d(player);
/* 75 */       shrinkStackInSlot(0, this.baseQty);
/* 76 */       shrinkStackInSlot(1, this.additionQty);
/* 77 */       this.field_234644_e_.func_221486_a((world, pos) -> world.func_217379_c(1044, pos, 0));
/* 78 */       cir.setReturnValue(inputItem);
/*    */       return;
/*    */     } 
/*    */   }
/*    */   
/*    */   private void shrinkStackInSlot(int index, int qty) {
/* 84 */     ItemStack itemstack = this.field_234643_d_.func_70301_a(index);
/* 85 */     itemstack.func_190918_g(qty);
/* 86 */     this.field_234643_d_.func_70299_a(index, itemstack);
/*    */   }
/*    */   
/*    */   @Inject(method = {"mayPickup"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void mayPickup(PlayerEntity player, boolean hasStack, CallbackInfoReturnable<Boolean> cir) {
/* 91 */     if (this.customRecipe) {
/* 92 */       cir.setReturnValue(Boolean.valueOf(true));
/*    */       return;
/*    */     } 
/* 95 */     cir.setReturnValue(Boolean.valueOf((this.field_234652_h_ != null && this.field_234652_h_.func_77569_a(this.field_234643_d_, this.field_234651_g_))));
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\SmithingTableContainerMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */