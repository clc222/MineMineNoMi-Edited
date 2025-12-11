/*    */ package xyz.pixelatedw.mineminenomi.items.weapons;
/*    */ 
/*    */ import java.util.Optional;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.item.ItemEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ItemSpawnComponent;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ 
/*    */ public class AbilitySwordItem extends ModSwordItem {
/* 19 */   private AbilityCore ability = null;
/*    */ 
/*    */   
/*    */   public AbilitySwordItem(AbilityCore ability, int damage) {
/* 23 */     this(ability, damage, -2.4F);
/* 24 */     this.ability = ability;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbilitySwordItem(AbilityCore ability, int damage, float attackSpeed) {
/* 29 */     super((new Item.Properties()).func_200917_a(1).func_200915_b(-1), damage, attackSpeed);
/* 30 */     this.ability = ability;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_77663_a(ItemStack itemStack, World world, Entity entity, int itemSlot, boolean isSelected) {
/* 36 */     super.func_77663_a(itemStack, world, entity, itemSlot, isSelected);
/* 37 */     if (entity instanceof PlayerEntity && !entity.field_70170_p.field_72995_K && this.ability != null) {
/*    */       
/* 39 */       PlayerEntity owner = (PlayerEntity)entity;
/* 40 */       IAbilityData abilityDataProps = AbilityDataCapability.get((LivingEntity)owner);
/*    */       
/* 42 */       boolean deleteSword = true;
/*    */       
/* 44 */       for (IAbility ability : abilityDataProps.getEquippedAbilities()) {
/*    */         
/* 46 */         if (ability == null) {
/*    */           continue;
/*    */         }
/*    */         
/* 50 */         Optional<ItemSpawnComponent> itemSpawnComponent = ability.getComponent(ModAbilityKeys.ITEM_SPAWN);
/* 51 */         if (itemSpawnComponent.isPresent() && (
/* 52 */           (ItemSpawnComponent)itemSpawnComponent.get()).isActive()) {
/* 53 */           deleteSword = false;
/*    */         }
/*    */       } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 68 */       if (deleteSword) {
/* 69 */         owner.field_71071_by.func_184437_d(itemStack);
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean onEntityItemUpdate(ItemStack itemStack, ItemEntity entityItem) {
/* 77 */     if (entityItem.func_70089_S())
/* 78 */       entityItem.func_70106_y(); 
/* 79 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_77616_k(ItemStack stack) {
/* 86 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\weapons\AbilitySwordItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */