/*    */ package xyz.pixelatedw.mineminenomi.items.armors;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.DyeableArmorItem;
/*    */ import net.minecraft.item.IArmorMaterial;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*    */ import xyz.pixelatedw.mineminenomi.models.armors.FluffyCapeModel;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FluffyCapeItem
/*    */   extends DyeableArmorItem
/*    */ {
/*    */   private String texture;
/*    */   private boolean hasOverlay;
/*    */   
/*    */   public FluffyCapeItem(String texture, boolean hasOverlay) {
/* 26 */     super((IArmorMaterial)ModArmors.CAPTAIN_CAPE_MATERIAL, EquipmentSlotType.CHEST, (new Item.Properties()).func_200916_a(ModCreativeTabs.EQUIPMENT));
/* 27 */     this.texture = texture;
/* 28 */     this.hasOverlay = hasOverlay;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @Nullable
/*    */   public <A extends net.minecraft.client.renderer.entity.model.BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
/* 36 */     FluffyCapeModel armorModel = new FluffyCapeModel();
/* 37 */     armorModel.func_225597_a_(entityLiving, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
/* 38 */     return (A)armorModel;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public String getArmorTexture(ItemStack itemStack, Entity entity, EquipmentSlotType slot, String type) {
/* 45 */     return String.format("%s:textures/models/armor/%s_layer_1%s.png", new Object[] { "mineminenomi", this.texture, (type == null || !this.hasOverlay) ? "" : String.format("_%s", new Object[] { type }) });
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\armors\FluffyCapeItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */