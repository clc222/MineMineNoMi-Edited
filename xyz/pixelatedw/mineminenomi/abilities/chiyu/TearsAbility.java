/*    */ package xyz.pixelatedw.mineminenomi.abilities.chiyu;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.apache.commons.lang3.tuple.ImmutablePair;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.PassiveAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.DamageTakenComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ import xyz.pixelatedw.mineminenomi.items.WateringCanItem;
/*    */ 
/*    */ public class TearsAbility extends PassiveAbility2 {
/* 25 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "tears", new Pair[] {
/* 26 */         (Pair)ImmutablePair.of("Each time the user is hurt their Watering Can has a 10% chance of being filled with a tear, which can be drank to heal themselves.", null)
/*    */       });
/* 28 */   public static final AbilityCore<TearsAbility> INSTANCE = (new AbilityCore.Builder("Tears", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, TearsAbility::new))
/* 29 */     .addDescriptionLine(DESCRIPTION)
/* 30 */     .build();
/*    */   
/* 32 */   private final DamageTakenComponent damageTakenComponent = (new DamageTakenComponent((IAbility)this)).addOnHurtEvent(this::onDamageTaken);
/*    */   
/*    */   public TearsAbility(AbilityCore<TearsAbility> ability) {
/* 35 */     super(ability);
/*    */     
/* 37 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.damageTakenComponent });
/*    */   }
/*    */   
/*    */   private float onDamageTaken(LivingEntity entity, IAbility ability, DamageSource damageSource, float damage) {
/* 41 */     if (canUse(entity).isFail()) {
/* 42 */       return damage;
/*    */     }
/*    */     
/* 45 */     if (!(entity instanceof PlayerEntity)) {
/* 46 */       return damage;
/*    */     }
/*    */     
/* 49 */     PlayerEntity player = (PlayerEntity)entity;
/*    */     
/* 51 */     ItemStack wateringCan = null;
/*    */     
/* 53 */     List<ItemStack> inventory = new ArrayList<>();
/*    */     
/* 55 */     inventory.addAll((Collection<? extends ItemStack>)player.field_71071_by.field_70462_a);
/* 56 */     inventory.addAll((Collection<? extends ItemStack>)player.field_71071_by.field_184439_c);
/*    */     
/* 58 */     for (ItemStack element : inventory) {
/* 59 */       if (element != null && element.func_77973_b() == ModItems.WATERING_CAN.get()) {
/* 60 */         wateringCan = element;
/*    */         
/*    */         break;
/*    */       } 
/*    */     } 
/*    */     
/* 66 */     if (wateringCan == null) {
/* 67 */       return damage;
/*    */     }
/*    */     
/* 70 */     if (this.random.nextInt(10) == 0) {
/* 71 */       WateringCanItem.alterTearAmount(wateringCan, 1);
/*    */     }
/*    */     
/* 74 */     return damage;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\chiyu\TearsAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */