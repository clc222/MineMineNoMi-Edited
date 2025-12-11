/*    */ package xyz.pixelatedw.mineminenomi.items.armors;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
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
/*    */ 
/*    */ public class BandanaArmorItem
/*    */   extends DyeableArmorItem
/*    */ {
/*    */   public BandanaArmorItem() {
/* 21 */     super((IArmorMaterial)ModArmors.BANDANA_MATERIAL, EquipmentSlotType.HEAD, (new Item.Properties()).func_200916_a(ModCreativeTabs.EQUIPMENT));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @Nullable
/*    */   public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
/* 29 */     return (A)new BipedModel(0.5F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public String getArmorTexture(ItemStack itemStack, Entity entity, EquipmentSlotType slot, String type) {
/* 38 */     return super.getArmorTexture(itemStack, entity, slot, type);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\armors\BandanaArmorItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */