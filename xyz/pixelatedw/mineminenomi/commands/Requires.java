/*    */ package xyz.pixelatedw.mineminenomi.commands;
/*    */ import java.util.function.Predicate;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.command.CommandSource;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.integrations.perms.WyPermissions;
/*    */ 
/*    */ public class Requires {
/*    */   public static Predicate<CommandSource> hasPermission(int code, @Nullable WyPermissions.Permission perm) {
/* 12 */     return source -> {
/*    */         if (!CommonConfig.INSTANCE.hasPermissionsEnabled() || perm == null) {
/*    */           return source.func_197034_c(code);
/*    */         }
/*    */         
/*    */         boolean hasPermission = false;
/*    */         
/*    */         try {
/*    */           ServerPlayerEntity player = source.func_197035_h();
/*    */           hasPermission = WyPermissions.hasPermission((PlayerEntity)player, perm);
/* 22 */         } catch (Exception ex) {
/*    */           ex.printStackTrace();
/*    */         } 
/*    */         return hasPermission;
/*    */       };
/*    */   }
/*    */ 
/*    */   
/*    */   public static Predicate<CommandSource> hasEitherPermission(int code, @Nullable WyPermissions.Permission... perms) {
/* 31 */     return source -> {
/*    */         if (!CommonConfig.INSTANCE.hasPermissionsEnabled() || perms == null) {
/*    */           return source.func_197034_c(code);
/*    */         }
/*    */         
/*    */         boolean hasPermission = false;
/*    */         
/*    */         try {
/*    */           ServerPlayerEntity player = source.func_197035_h();
/*    */           
/*    */           for (WyPermissions.Permission perm : perms) {
/*    */             if (WyPermissions.hasPermission((PlayerEntity)player, perm)) {
/*    */               hasPermission = true;
/*    */               break;
/*    */             } 
/*    */           } 
/* 47 */         } catch (Exception ex) {
/*    */           ex.printStackTrace();
/*    */         } 
/*    */         return hasPermission;
/*    */       };
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\commands\Requires.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */