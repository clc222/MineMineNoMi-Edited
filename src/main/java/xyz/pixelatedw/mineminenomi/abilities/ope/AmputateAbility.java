/*    */ package xyz.pixelatedw.mineminenomi.abilities.ope;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.ope.SpatialSlashProjectile;
/*    */ 
/*    */ public class AmputateAbility extends Ability {
/* 14 */   public static final AbilityCore<AmputateAbility> INSTANCE = (new AbilityCore.Builder("Amputate", AbilityCategory.DEVIL_FRUITS, AmputateAbility::new))
/* 15 */     .addDescriptionLine("The user cuts the every block on his sight, horizontal sneaking causes a vertical cut", new Object[0])
/* 16 */     .build();
/*    */ 
/*    */   
/*    */   public AmputateAbility(AbilityCore<AmputateAbility> core) {
/* 20 */     super(core);
/* 21 */     setMaxCooldown(5.0D);
/*    */     
/* 23 */     setOnUseEvent(this::onUseEvent);
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 28 */     RoomAbility ability = (RoomAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility(RoomAbility.INSTANCE);
/* 29 */     if (ability == null || !ability.isEntityInRoom((Entity)player)) {
/*    */       
/* 31 */       player.func_145747_a((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_ONLY_IN_ROOM, new Object[] { getName() }), player.func_110124_au());
/* 32 */       return false;
/*    */     } 
/*    */     
/* 35 */     SpatialSlashProjectile proj = new SpatialSlashProjectile(player.field_70170_p, (LivingEntity)player);
/* 36 */     player.field_70170_p.func_217376_c((Entity)proj);
/* 37 */     proj.shoot((Entity)player, player.field_70125_A, player.field_70177_z, 0.0F, 2.0F, 1.0F);
/*    */     
/* 39 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\ope\AmputateAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */