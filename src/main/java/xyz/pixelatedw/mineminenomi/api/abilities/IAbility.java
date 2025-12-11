/*    */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*    */ 
/*    */ import java.util.Map;
/*    */ import java.util.Optional;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponentKey;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface IAbility
/*    */   extends Comparable<IAbility>
/*    */ {
/*    */   default int compareTo(IAbility other) {
/* 43 */     if (other == null) {
/* 44 */       return 1;
/*    */     }
/* 46 */     return getCore().compareTo(other.getCore());
/*    */   }
/*    */   
/*    */   void load(CompoundNBT paramCompoundNBT);
/*    */   
/*    */   CompoundNBT save(CompoundNBT paramCompoundNBT);
/*    */   
/*    */   boolean isOGCD();
/*    */   
/*    */   boolean hasComponent(AbilityComponentKey paramAbilityComponentKey);
/*    */   
/*    */   Map<AbilityComponentKey<?>, AbilityComponent<?>> getComponents();
/*    */   
/*    */   <C extends AbilityComponent<?>> Optional<C> getComponent(AbilityComponentKey<C> paramAbilityComponentKey);
/*    */   
/*    */   void onRemove(LivingEntity paramLivingEntity);
/*    */   
/*    */   void onEquip(LivingEntity paramLivingEntity);
/*    */   
/*    */   ResourceLocation getIcon(LivingEntity paramLivingEntity);
/*    */   
/*    */   ITextComponent getDisplayName();
/*    */   
/*    */   AbilityCore getCore();
/*    */   
/*    */   AbilityUseResult canUse(LivingEntity paramLivingEntity);
/*    */   
/*    */   void tick(LivingEntity paramLivingEntity);
/*    */   
/*    */   void use(LivingEntity paramLivingEntity);
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\IAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */