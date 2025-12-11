/*    */ package xyz.pixelatedw.mineminenomi.items.armors;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ArmorItem;
/*    */ import net.minecraft.item.IArmorMaterial;
/*    */ import net.minecraft.item.IDyeableArmorItem;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*    */ import xyz.pixelatedw.mineminenomi.models.armors.TricorneModel;
/*    */ 
/*    */ public class TricorneItem
/*    */   extends ArmorItem implements IDyeableArmorItem {
/*    */   public TricorneItem() {
/* 21 */     super((IArmorMaterial)ModArmors.ACES_HAT_MATERIAL, EquipmentSlotType.HEAD, (new Item.Properties()).func_200916_a(ModCreativeTabs.EQUIPMENT));
/*    */   }
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @Nullable
/*    */   public <A extends net.minecraft.client.renderer.entity.model.BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
/* 28 */     return (A)new TricorneModel();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public String getArmorTexture(ItemStack itemStack, Entity entity, EquipmentSlotType slot, String type) {
/* 36 */     return String.format("%s:textures/models/armor/tricorne%s.png", new Object[] { "mineminenomi", (type == null) ? "" : String.format("_%s", new Object[] { type }) });
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\armors\TricorneItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */