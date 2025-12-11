/*    */ package xyz.pixelatedw.mineminenomi.events.passives;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.Items;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.nbt.INBT;
/*    */ import net.minecraft.nbt.ListNBT;
/*    */ import net.minecraft.nbt.StringNBT;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraft.world.IWorld;
/*    */ import net.minecraftforge.event.entity.living.LivingEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class HisoPassiveEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
/* 27 */     if (!(event.getEntityLiving() instanceof net.minecraft.entity.passive.AnimalEntity)) {
/*    */       return;
/*    */     }
/* 30 */     if (event.getEntityLiving().func_70644_a((Effect)ModEffects.ANIMAL_LOOKOUT.get())) {
/*    */       
/* 32 */       LivingEntity animal = event.getEntityLiving();
/* 33 */       if (animal.func_184582_a(EquipmentSlotType.CHEST).func_77973_b() != Items.field_221585_m) {
/*    */         
/* 35 */         ItemStack itemStack = new ItemStack((IItemProvider)Items.field_221585_m);
/* 36 */         CompoundNBT nbt = new CompoundNBT();
/* 37 */         nbt.func_218657_a("seen", (INBT)new ListNBT());
/* 38 */         itemStack.func_77982_d(nbt);
/* 39 */         animal.func_184201_a(EquipmentSlotType.CHEST, itemStack);
/*    */       } 
/* 41 */       List<LivingEntity> around = WyHelper.getNearbyLiving(animal.func_213303_ch(), (IWorld)animal.field_70170_p, 10.0D, null);
/* 42 */       around.remove(animal);
/* 43 */       CompoundNBT tag = animal.func_184582_a(EquipmentSlotType.CHEST).func_77978_p();
/* 44 */       ListNBT seen = (ListNBT)tag.func_74781_a("seen");
/* 45 */       around.forEach(entity -> {
/*    */             INBT hasn = null;
/*    */             
/*    */             for (INBT previous : seen) {
/*    */               if (previous.func_150285_a_().split(" ")[0].equals(entity.func_200200_C_().getString())) {
/*    */                 hasn = previous;
/*    */               }
/*    */             } 
/*    */             
/*    */             if (hasn != null) {
/*    */               seen.remove(hasn);
/*    */             }
/*    */             
/*    */             seen.add(StringNBT.func_229705_a_(entity.func_200200_C_().getString() + " " + entity.field_70170_p.func_82737_E()));
/*    */           });
/* 60 */       tag.func_218657_a("seen", (INBT)seen);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\passives\HisoPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */