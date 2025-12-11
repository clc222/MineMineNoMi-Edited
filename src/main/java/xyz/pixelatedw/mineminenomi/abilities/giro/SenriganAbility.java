/*    */ package xyz.pixelatedw.mineminenomi.abilities.giro;
/*    */ 
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ContinuousAbility;
/*    */ import xyz.pixelatedw.mineminenomi.entities.PhysicalBodyEntity;
/*    */ 
/*    */ public class SenriganAbility
/*    */   extends ContinuousAbility {
/* 11 */   public static final AbilityCore<SenriganAbility> INSTANCE = (new AbilityCore.Builder("Senrigan", AbilityCategory.DEVIL_FRUITS, SenriganAbility::new))
/* 12 */     .addDescriptionLine("The spirit leaves the body, allowing them to freely explore the nearby areas from huge heights", new Object[0])
/* 13 */     .build();
/*    */   
/*    */   private PhysicalBodyEntity body;
/*    */   
/*    */   private BlockPos pivotPoint;
/*    */   
/*    */   public SenriganAbility(AbilityCore<SenriganAbility> core) {
/* 20 */     super(core);
/* 21 */     setThreshold(60.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\giro\SenriganAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */