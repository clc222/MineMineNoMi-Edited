/*     */ package xyz.pixelatedw.mineminenomi.api.abilities.components;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.util.PriorityEventPool;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ 
/*     */ 
/*     */ public class ItemSpawnComponent
/*     */   extends AbilityComponent<IAbility>
/*     */ {
/*     */   public static final String SPAWN_TAG = "spawnedByAbility";
/*  21 */   private List<ItemStack> trackedStacks = new ArrayList<>();
/*     */   
/*     */   private boolean isActive;
/*  24 */   private final PriorityEventPool<IMissingItemEvent> missingItemEvents = new PriorityEventPool();
/*     */   
/*     */   public ItemSpawnComponent(IAbility ability) {
/*  27 */     super(ModAbilityKeys.ITEM_SPAWN, ability);
/*  28 */     setTickRate(5);
/*     */   }
/*     */   
/*     */   public ItemSpawnComponent addMissingItemEvent(int priority, IMissingItemEvent event) {
/*  32 */     this.missingItemEvents.addEvent(priority, event);
/*  33 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public void doTick(LivingEntity entity) {
/*  38 */     if (!entity.field_70170_p.field_72995_K && 
/*  39 */       this.isActive && this.missingItemEvents.countEventsInPool() > 0L && !this.trackedStacks.isEmpty())
/*     */     {
/*     */ 
/*     */ 
/*     */       
/*  44 */       for (Iterator<ItemStack> iterator = this.trackedStacks.iterator(); iterator.hasNext(); ) { ItemStack stack = iterator.next();
/*  45 */         if (stack.func_190926_b()) {
/*  46 */           this.missingItemEvents.dispatch(event -> event.missingItem(entity, getAbility()));
/*     */           return;
/*     */         }  }
/*     */     
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List<ItemStack> getTrackedItems() {
/*  59 */     return this.trackedStacks;
/*     */   }
/*     */   
/*     */   public void spawnItem(LivingEntity entity, ItemStack stack) {
/*  63 */     spawnItem(entity, stack, EquipmentSlotType.MAINHAND, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void spawnItem(LivingEntity entity, ItemStack stack, @Nullable EquipmentSlotType slot, boolean forceSlot) {
/*  78 */     ensureIsRegistered();
/*  79 */     this.isActive = true;
/*     */     
/*  81 */     ItemStack itemStack = entity.func_184582_a(slot);
/*  82 */     if (itemStack.func_190926_b()) {
/*  83 */       entity.func_184201_a(slot, stack);
/*  84 */       this.trackedStacks.add(stack);
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  90 */     if (entity instanceof PlayerEntity) {
/*  91 */       PlayerEntity player = (PlayerEntity)entity;
/*  92 */       player.func_191521_c(stack);
/*  93 */       this.trackedStacks.add(stack);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void despawnItems(LivingEntity entity) {
/*  98 */     this.isActive = false;
/*     */     
/* 100 */     if (this.trackedStacks.size() > 0) {
/* 101 */       for (ItemStack stack : this.trackedStacks) {
/* 102 */         ItemsHelper.removeItemStackFromInventory(entity, stack);
/*     */       }
/*     */     }
/*     */     
/* 106 */     this.trackedStacks.clear();
/*     */   }
/*     */   
/*     */   public boolean isActive() {
/* 110 */     return this.isActive;
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface IMissingItemEvent {
/*     */     void missingItem(LivingEntity param1LivingEntity, IAbility param1IAbility);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\components\ItemSpawnComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */