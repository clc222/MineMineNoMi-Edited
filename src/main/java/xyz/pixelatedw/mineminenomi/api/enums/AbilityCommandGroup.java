/*    */ package xyz.pixelatedw.mineminenomi.api.enums;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraftforge.common.IExtensibleEnum;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ 
/*    */ public enum AbilityCommandGroup
/*    */   implements IExtensibleEnum
/*    */ {
/* 13 */   HUMAN(() -> ModAbilities.HUMAN_ABILITIES),
/* 14 */   FISHMAN(() -> ModAbilities.FISHMAN_ABILITIES),
/* 15 */   CYBORG(() -> ModAbilities.CYBORG_ABILITIES),
/* 16 */   MINK(() -> ModAbilities.MINK_ABILITIES),
/*    */   
/* 18 */   SWORDSMAN(() -> ModAbilities.SWORDSMAN_ABILITIES),
/* 19 */   SNIPER(() -> ModAbilities.SNIPER_ABILITIES),
/* 20 */   DOCTOR(() -> ModAbilities.DOCTOR_ABILITIES),
/* 21 */   ART_OF_WEATHER(() -> ModAbilities.ART_OF_WEATHER_ABILITIES),
/* 22 */   BRAWLER(() -> ModAbilities.BRAWLER_ABILITIES),
/* 23 */   BLACK_LEG(() -> ModAbilities.BLACK_LEG_ABILITIES),
/*    */   
/* 25 */   HAKI(() -> ModAbilities.HAKI_ABILITIES);
/*    */   
/*    */   private Supplier<AbilityCore[]> abilities;
/*    */ 
/*    */   
/*    */   AbilityCommandGroup(Supplier<AbilityCore[]> abilities) {
/* 31 */     this.abilities = abilities;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<AbilityCore> getAbilities() {
/* 36 */     return Arrays.asList((Object[])this.abilities.get());
/*    */   }
/*    */ 
/*    */   
/*    */   public static AbilityCommandGroup create(String name, Supplier<AbilityCore[]> abilities) {
/* 41 */     throw new IllegalStateException("Enum not extended");
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\enums\AbilityCommandGroup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */