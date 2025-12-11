/*    */ package xyz.pixelatedw.mineminenomi.abilities;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
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
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ 
/*    */ public class SlashDamageImmunityAbility
/*    */   extends PassiveAbility2 {
/* 25 */   private static final ITextComponent[] DESCRIPTION = (ITextComponent[])AbilityHelper.registerDescriptionText("mineminenomi", "slash_damage_immunity", new Pair[] {
/* 26 */         (Pair)ImmutablePair.of("Makes the user immune to slash based attacks", null)
/*    */       });
/* 28 */   public static final AbilityCore<SlashDamageImmunityAbility> BARA_INSTANCE = (new AbilityCore.Builder("Slash Damage Immunity", AbilityCategory.DEVIL_FRUITS, AbilityType.PASSIVE, SlashDamageImmunityAbility::new))
/* 29 */     .addDescriptionLine(DESCRIPTION)
/* 30 */     .build();
/*    */   
/* 32 */   private final DamageTakenComponent damageTakenComponent = (new DamageTakenComponent((IAbility)this)).addOnAttackEvent(this::onDamageTaken);
/*    */   
/*    */   public SlashDamageImmunityAbility(AbilityCore<SlashDamageImmunityAbility> ability) {
/* 35 */     super(ability);
/*    */     
/* 37 */     addComponents(new AbilityComponent[] { (AbilityComponent)this.damageTakenComponent });
/*    */   }
/*    */   
/*    */   private float onDamageTaken(LivingEntity entity, IAbility ability, DamageSource damageSource, float damage) {
/* 41 */     boolean isSlashDamage = (damageSource instanceof ModDamageSource && ((ModDamageSource)damageSource).isSlash());
/* 42 */     boolean isSwordDamage = false;
/*    */     
/* 44 */     if (damageSource instanceof net.minecraft.util.EntityDamageSource || damageSource instanceof xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource) {
/* 45 */       Entity entitySource = damageSource.func_76346_g();
/* 46 */       if (entitySource != null && entitySource.func_70089_S() && entitySource instanceof LivingEntity) {
/* 47 */         LivingEntity livingSource = (LivingEntity)entitySource;
/* 48 */         isSwordDamage = (checkItemStack(livingSource.func_184614_ca()) || checkItemStack(livingSource.func_184592_cb()));
/*    */       } 
/*    */     } 
/*    */     
/* 52 */     if (isSlashDamage || isSwordDamage) {
/* 53 */       return 0.0F;
/*    */     }
/*    */     
/* 56 */     return damage;
/*    */   }
/*    */   
/*    */   private boolean checkItemStack(ItemStack stack) {
/* 60 */     if (stack != null && !stack.func_190926_b()) {
/* 61 */       boolean isSword = ItemsHelper.isSword(stack);
/* 62 */       boolean hasDamageAttribute = (stack.func_111283_C(EquipmentSlotType.MAINHAND).get(Attributes.field_233823_f_).size() > 0);
/*    */       
/* 64 */       if (isSword || hasDamageAttribute) {
/* 65 */         return true;
/*    */       }
/*    */     } 
/*    */     
/* 69 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\SlashDamageImmunityAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */