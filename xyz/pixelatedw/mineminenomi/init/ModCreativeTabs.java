/*    */ package xyz.pixelatedw.mineminenomi.init;
/*    */ import net.minecraft.item.ItemGroup;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ 
/*    */ public class ModCreativeTabs {
/*  9 */   public static final ItemGroup DEVIL_FRUITS = new ItemGroup("devil_fruits")
/*    */     {
/*    */       @OnlyIn(Dist.CLIENT)
/*    */       public ItemStack func_78016_d() {
/* 13 */         return new ItemStack((IItemProvider)ModAbilities.MERA_MERA_NO_MI);
/*    */       }
/*    */     };
/*    */   
/* 17 */   public static final ItemGroup WEAPONS = new ItemGroup("weapons")
/*    */     {
/*    */       @OnlyIn(Dist.CLIENT)
/*    */       public ItemStack func_78016_d() {
/* 21 */         return new ItemStack((IItemProvider)ModWeapons.YORU.get());
/*    */       }
/*    */     };
/*    */   
/* 25 */   public static final ItemGroup EQUIPMENT = new ItemGroup("equipment")
/*    */     {
/*    */       @OnlyIn(Dist.CLIENT)
/*    */       public ItemStack func_78016_d() {
/* 29 */         return new ItemStack((IItemProvider)ModArmors.STRAW_HAT.get());
/*    */       }
/*    */     };
/*    */   
/* 33 */   public static final ItemGroup MISC = new ItemGroup("misc")
/*    */     {
/*    */       @OnlyIn(Dist.CLIENT)
/*    */       public ItemStack func_78016_d() {
/* 37 */         return new ItemStack((IItemProvider)ModItems.KAIROSEKI.get());
/*    */       }
/*    */     };
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModCreativeTabs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */