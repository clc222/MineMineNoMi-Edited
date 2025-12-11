/*    */ package xyz.pixelatedw.mineminenomi.items.weapons;
/*    */ import java.util.Optional;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.IItemTier;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.PickaxeItem;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityItemTier;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.ItemSpawnComponent;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*    */ 
/*    */ public class AbilityPickaxeItem extends PickaxeItem {
/* 23 */   private AbilityCore ability = null;
/*    */ 
/*    */   
/*    */   public AbilityPickaxeItem(AbilityCore ability, AbilityItemTier tier, int attackDamage, float attackSpeed) {
/* 27 */     super((IItemTier)tier, attackDamage, attackSpeed, new Item.Properties());
/* 28 */     this.ability = ability;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_77663_a(ItemStack itemStack, World world, Entity entity, int itemSlot, boolean isSelected) {
/* 34 */     super.func_77663_a(itemStack, world, entity, itemSlot, isSelected);
/* 35 */     if (entity instanceof PlayerEntity && this.ability != null) {
/*    */       
/* 37 */       PlayerEntity owner = (PlayerEntity)entity;
/* 38 */       IAbilityData abilityDataProps = AbilityDataCapability.get((LivingEntity)owner);
/*    */       
/* 40 */       boolean deletePicaxe = true;
/*    */       
/* 42 */       for (IAbility ability : abilityDataProps.getEquippedAbilities()) {
/*    */         
/* 44 */         if (ability == null) {
/*    */           continue;
/*    */         }
/*    */         
/* 48 */         Optional<ItemSpawnComponent> itemSpawnComponent = ability.getComponent(ModAbilityKeys.ITEM_SPAWN);
/* 49 */         if (itemSpawnComponent.isPresent() && (
/* 50 */           (ItemSpawnComponent)itemSpawnComponent.get()).isActive()) {
/* 51 */           deletePicaxe = false;
/*    */         }
/*    */       } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 59 */       if (deletePicaxe) {
/* 60 */         owner.field_71071_by.func_184437_d(itemStack);
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public float func_150893_a(ItemStack stack, BlockState state) {
/* 67 */     if (stack.func_77973_b() == ModWeapons.DORU_PICKAXE.get() && state.func_177230_c() == ModBlocks.WAX.get())
/* 68 */       return 999.0F; 
/* 69 */     return super.func_150893_a(stack, state);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_77616_k(ItemStack stack) {
/* 75 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\items\weapons\AbilityPickaxeItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */