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
/*    */ import xyz.pixelatedw.mineminenomi.models.armors.CabajiScarfModel;
/*    */ 
/*    */ public class CabajiScarfItem
/*    */   extends ArmorItem
/*    */ {
/*    */   public CabajiScarfItem() {
/* 21 */     super((IArmorMaterial)ModArmors.BANDANA_MATERIAL, EquipmentSlotType.HEAD, (new Item.Properties()).func_200916_a(ModCreativeTabs.EQUIPMENT));
/*    */   }
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @Nullable
/*    */   public <A extends net.minecraft.client.renderer.entity.model.BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
/* 28 */     CabajiScarfModel model = new CabajiScarfModel();
/* 29 */     model.func_225597_a_(entityLiving, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
/* 30 */     return (A)model;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public String getArmorTexture(ItemStack itemStack, Entity entity, EquipmentSlotType slot, String type) {
/* 36 */     return String.format("%s:textures/models/armor/cabaji_scarf.png", new Object[] { "mineminenomi" });
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\armors\CabajiScarfItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */