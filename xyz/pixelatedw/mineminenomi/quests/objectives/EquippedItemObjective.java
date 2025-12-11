/*    */ package xyz.pixelatedw.mineminenomi.quests.objectives;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.IEquipItemObjective;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*    */ 
/*    */ public class EquippedItemObjective
/*    */   extends Objective
/*    */   implements IEquipItemObjective
/*    */ {
/*    */   private Supplier<Item> itemTarget;
/*    */   private EquipmentSlotType slotTarget;
/*    */   
/*    */   public EquippedItemObjective(String title, int count, Supplier<Item> item, EquipmentSlotType slot) {
/* 21 */     super(title);
/* 22 */     setMaxProgress(count);
/* 23 */     this.itemTarget = item;
/* 24 */     this.slotTarget = slot;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean checkEquippedItem(PlayerEntity player, ItemStack toCheck) {
/* 30 */     return (toCheck.func_77973_b() == this.itemTarget.get());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean checkSlot(EquipmentSlotType slot) {
/* 36 */     return (slot == this.slotTarget);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getLocalizedTitle() {
/* 42 */     String objectiveKey = (new TranslationTextComponent(String.format("quest.objective.mineminenomi.%s", new Object[] { getId() }))).func_150268_i();
/* 43 */     return (new TranslationTextComponent(objectiveKey, new Object[] { (new ItemStack((IItemProvider)this.itemTarget.get())).func_200301_q().getString() })).getString();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\quests\objectives\EquippedItemObjective.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */