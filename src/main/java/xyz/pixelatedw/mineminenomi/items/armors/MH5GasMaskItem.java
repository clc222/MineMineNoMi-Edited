/*    */ package xyz.pixelatedw.mineminenomi.items.armors;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ArmorItem;
/*    */ import net.minecraft.item.IArmorMaterial;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*    */ import xyz.pixelatedw.mineminenomi.models.armors.MH5GasMaskModel;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MH5GasMaskItem
/*    */   extends ArmorItem
/*    */ {
/*    */   public MH5GasMaskItem() {
/* 23 */     super((IArmorMaterial)ModArmors.METALIC_FACE_MATERIAL, EquipmentSlotType.HEAD, (new Item.Properties()).func_200916_a(ModCreativeTabs.EQUIPMENT).func_200918_c(1000));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @Nullable
/*    */   public <A extends net.minecraft.client.renderer.entity.model.BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
/* 31 */     return (A)new MH5GasMaskModel();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public String getArmorTexture(ItemStack itemStack, Entity entity, EquipmentSlotType slot, String type) {
/* 40 */     return String.format("%s:textures/models/armor/mh5_gas_mask.png", new Object[] { "mineminenomi" });
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\armors\MH5GasMaskItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */