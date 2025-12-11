/*    */ package xyz.pixelatedw.mineminenomi.init;
/*    */ 
/*    */ import net.minecraft.enchantment.Enchantment;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraftforge.eventbus.api.IEventBus;
/*    */ import net.minecraftforge.fml.RegistryObject;
/*    */ import xyz.pixelatedw.mineminenomi.api.GenericEnchantment;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ public class ModEnchantments
/*    */ {
/* 12 */   public static final RegistryObject<Enchantment> DIAL_IMPACT = WyRegistry.registerEnchantment("Impact Dial", () -> new GenericEnchantment(Enchantment.Rarity.RARE, false, new EquipmentSlotType[] { EquipmentSlotType.MAINHAND }));
/* 13 */   public static final RegistryObject<Enchantment> DIAL_FLASH = WyRegistry.registerEnchantment("Flash Dial", () -> new GenericEnchantment(Enchantment.Rarity.RARE, false, new EquipmentSlotType[] { EquipmentSlotType.MAINHAND }));
/* 14 */   public static final RegistryObject<Enchantment> KAIROSEKI = WyRegistry.registerEnchantment("Kairoseki", () -> new GenericEnchantment(Enchantment.Rarity.VERY_RARE, false, new EquipmentSlotType[] { EquipmentSlotType.MAINHAND }));
/*    */   
/*    */   public static void register(IEventBus eventBus) {
/* 17 */     WyRegistry.ENCHANTMENTS.register(eventBus);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModEnchantments.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */