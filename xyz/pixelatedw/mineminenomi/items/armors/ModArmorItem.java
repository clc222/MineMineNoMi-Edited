/*    */ package xyz.pixelatedw.mineminenomi.items.armors;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ArmorItem;
/*    */ import net.minecraft.item.IArmorMaterial;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import xyz.pixelatedw.mineminenomi.api.GenericArmorMaterial;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*    */ 
/*    */ public class ModArmorItem
/*    */   extends ArmorItem
/*    */ {
/*    */   private String name;
/*    */   private boolean hasOverlay;
/*    */   
/*    */   public ModArmorItem(String name, EquipmentSlotType type) {
/* 25 */     this(name, type, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public ModArmorItem(GenericArmorMaterial material, String name, EquipmentSlotType type, boolean hasOverlay) {
/* 30 */     super((IArmorMaterial)material, type, (new Item.Properties()).func_200916_a(ModCreativeTabs.EQUIPMENT));
/* 31 */     this.name = name;
/* 32 */     this.hasOverlay = hasOverlay;
/*    */   }
/*    */ 
/*    */   
/*    */   public ModArmorItem(String name, EquipmentSlotType type, boolean hasOverlay) {
/* 37 */     super((IArmorMaterial)ModArmors.BASIC_ARMOR_MATERIAL, type, (new Item.Properties()).func_200916_a(ModCreativeTabs.EQUIPMENT));
/* 38 */     this.name = name;
/* 39 */     this.hasOverlay = hasOverlay;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @Nullable
/*    */   public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
/* 47 */     return (A)new BipedModel((this.field_77881_a == EquipmentSlotType.LEGS) ? 0.25F : 0.5F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public String getArmorTexture(ItemStack itemStack, Entity entity, EquipmentSlotType slot, String type) {
/* 56 */     return String.format("%s:textures/models/armor/%s_%d%s.png", new Object[] { "mineminenomi", this.name, Integer.valueOf((slot == EquipmentSlotType.LEGS) ? 2 : 1), (type == null || !this.hasOverlay) ? "" : String.format("_%s", new Object[] { type }) });
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\armors\ModArmorItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */