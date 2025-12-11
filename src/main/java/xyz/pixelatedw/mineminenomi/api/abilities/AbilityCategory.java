/*    */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*    */ 
/*    */ import java.util.function.Function;
/*    */ import java.util.function.Predicate;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.common.IExtensibleEnum;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.mera.DaiEnkaiAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ 
/*    */ 
/*    */ public enum AbilityCategory
/*    */   implements IExtensibleEnum
/*    */ {
/* 16 */   DEVIL_FRUITS(AbilityHelper.DF_CATEGORY_ICON),
/* 17 */   RACIAL(AbilityHelper.RACE_CATEGORY_ICON),
/* 18 */   STYLE(null),
/* 19 */   HAKI(AbilityHelper.HAKI_CATEGORY_ICON),
/* 20 */   FACTION(null),
/* 21 */   EQUIPMENT(null),
/* 22 */   ALL(null);
/*    */ 
/*    */   
/*    */   private Function<PlayerEntity, ResourceLocation> iconFunction = entity -> DaiEnkaiAbility.INSTANCE.getIcon();
/*    */ 
/*    */ 
/*    */   
/*    */   AbilityCategory(Function<PlayerEntity, ResourceLocation> function) {
/* 30 */     this.iconFunction = function;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public ResourceLocation getIcon(PlayerEntity player) {
/* 36 */     if (this.iconFunction == null || player == null) {
/* 37 */       return null;
/*    */     }
/* 39 */     return this.iconFunction.apply(player);
/*    */   }
/*    */ 
/*    */   
/*    */   public Predicate<IAbility> isAbilityPartofCategory() {
/* 44 */     if (this == ALL) return abl -> true; 
/* 45 */     return abl -> abl.getCore().getCategory().equals(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public Predicate<AbilityCore> isCorePartofCategory() {
/* 50 */     if (this == ALL) return core -> true; 
/* 51 */     return core -> core.getCategory().equals(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public static AbilityCategory create(String name, Function<PlayerEntity, ResourceLocation> function) {
/* 56 */     throw new IllegalStateException("Enum not extended");
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\AbilityCategory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */