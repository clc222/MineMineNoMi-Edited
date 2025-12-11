/*    */ package xyz.pixelatedw.mineminenomi.abilities.pero;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.pero.CandyMaidenProjectile;
/*    */ 
/*    */ public class CandyMaidenAbility extends Ability {
/* 11 */   public static final AbilityCore<CandyMaidenAbility> INSTANCE = (new AbilityCore.Builder("Candy Maiden", AbilityCategory.DEVIL_FRUITS, CandyMaidenAbility::new))
/* 12 */     .addDescriptionLine("Creates a gigant maiden shaped projectile that slows down any entity in it's way", new Object[0])
/* 13 */     .build();
/*    */ 
/*    */   
/*    */   public CandyMaidenAbility(AbilityCore<CandyMaidenAbility> core) {
/* 17 */     super(core);
/*    */     
/* 19 */     setMaxCooldown(10.0D);
/* 20 */     setOnUseEvent(this::onUseEvent);
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 25 */     CandyMaidenProjectile proj = new CandyMaidenProjectile(player.field_70170_p, (LivingEntity)player);
/* 26 */     player.field_70170_p.func_217376_c((Entity)proj);
/* 27 */     proj.func_234612_a_((Entity)player, player.field_70125_A, player.field_70177_z, 0.0F, 2.0F, 1.0F);
/* 28 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\pero\CandyMaidenAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */