/*    */ package xyz.pixelatedw.mineminenomi.abilities;
/*    */ 
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.PassiveAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ 
/*    */ public abstract class RideableAbility
/*    */   extends PassiveAbility2
/*    */ {
/*    */   public RideableAbility(AbilityCore core) {
/* 12 */     super(core);
/*    */     
/* 14 */     setDisplayName((ITextComponent)ModI18n.ABILITY_NAME_RIDEABLE);
/*    */   }
/*    */   
/*    */   protected void setCustomIcon(String str) {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\RideableAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */