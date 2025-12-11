/*    */ package xyz.pixelatedw.mineminenomi.integrations.curios;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import top.theillusivec4.curios.api.SlotContext;
/*    */ import top.theillusivec4.curios.api.type.capability.ICurio;
/*    */ import xyz.pixelatedw.mineminenomi.events.abilities.AbilityProgressionEvents;
/*    */ import xyz.pixelatedw.mineminenomi.integrations.curios.renderers.ICurioRenderer;
/*    */ 
/*    */ public class SimpleCurioItem
/*    */   implements ICurio {
/*    */   private final ItemStack stack;
/*    */   
/*    */   public SimpleCurioItem(ItemStack stack) {
/* 18 */     this.stack = stack;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(String identifier, int index, MatrixStack matrixStack, IRenderTypeBuffer renderTypeBuffer, int light, LivingEntity livingEntity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/* 24 */     ICurioRenderer renderer = CuriosIntegration.getRenderer(this.stack.func_77973_b());
/* 25 */     if (renderer != null) {
/* 26 */       renderer.render(identifier, index, matrixStack, renderTypeBuffer, light, livingEntity, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, this.stack);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void curioTick(String identifier, int index, LivingEntity livingEntity) {
/* 32 */     if (livingEntity instanceof PlayerEntity) {
/* 33 */       this.stack.func_77973_b().onArmorTick(this.stack, livingEntity.field_70170_p, (PlayerEntity)livingEntity);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEquip(SlotContext slotContext, ItemStack prevStack) {
/* 39 */     AbilityProgressionEvents.updateEquipmentProgression(slotContext.getWearer(), this.stack);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUnequip(SlotContext slotContext, ItemStack newStack) {
/* 44 */     AbilityProgressionEvents.updateEquipmentProgression(slotContext.getWearer(), this.stack);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canRender(String identifier, int index, LivingEntity livingEntity) {
/* 49 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canEquipFromUse(SlotContext slotContext) {
/* 54 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\integrations\curios\SimpleCurioItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */