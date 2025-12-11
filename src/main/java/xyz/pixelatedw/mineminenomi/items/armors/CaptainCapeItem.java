/*    */ package xyz.pixelatedw.mineminenomi.items.armors;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.DyeableArmorItem;
/*    */ import net.minecraft.item.IArmorMaterial;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.nbt.INBT;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*    */ import xyz.pixelatedw.mineminenomi.models.armors.CaptainCapeModel;
/*    */ 
/*    */ public class CaptainCapeItem
/*    */   extends DyeableArmorItem
/*    */ {
/*    */   private String texture;
/*    */   private boolean hasOverlay;
/* 26 */   private static final HashMap<Integer, Crew> CREW_CACHING = new HashMap<>();
/*    */ 
/*    */   
/*    */   public CaptainCapeItem(String texture, boolean hasOverlay) {
/* 30 */     super((IArmorMaterial)ModArmors.CAPTAIN_CAPE_MATERIAL, EquipmentSlotType.CHEST, (new Item.Properties()).func_200916_a(ModCreativeTabs.EQUIPMENT));
/* 31 */     this.texture = texture;
/* 32 */     this.hasOverlay = hasOverlay;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @Nullable
/*    */   public <A extends net.minecraft.client.renderer.entity.model.BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
/* 40 */     CaptainCapeModel armorModel = new CaptainCapeModel();
/* 41 */     armorModel.func_225597_a_(entityLiving, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
/* 42 */     return (A)armorModel;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public String getArmorTexture(ItemStack itemStack, Entity entity, EquipmentSlotType slot, String type) {
/* 49 */     return String.format("%s:textures/models/armor/%s%s.png", new Object[] { "mineminenomi", this.texture, (type == null || !this.hasOverlay) ? "" : String.format("_%s", new Object[] { type }) });
/*    */   }
/*    */   
/*    */   public static void setCapeCrew(ItemStack stack, @Nullable Crew crew) {
/* 53 */     if (crew == null) {
/*    */       return;
/*    */     }
/* 56 */     stack.func_196082_o().func_218657_a("crew", (INBT)crew.write());
/*    */   }
/*    */ 
/*    */   
/*    */   public static Crew getCapeCrew(ItemStack stack) {
/* 61 */     CompoundNBT tag = stack.func_196082_o().func_74775_l("crew");
/*    */     
/* 63 */     if (CREW_CACHING.containsKey(Integer.valueOf(tag.hashCode()))) {
/* 64 */       return CREW_CACHING.get(Integer.valueOf(tag.hashCode()));
/*    */     }
/*    */     
/* 67 */     Crew crew = Crew.from(tag);
/* 68 */     CREW_CACHING.put(Integer.valueOf(tag.hashCode()), crew);
/*    */     
/* 70 */     return crew;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\armors\CaptainCapeItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */