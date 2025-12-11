/*    */ package xyz.pixelatedw.mineminenomi.integrations.perms;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.server.permission.DefaultPermissionLevel;
/*    */ import net.minecraftforge.server.permission.PermissionAPI;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import xyz.pixelatedw.mineminenomi.ModMain;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ 
/*    */ public class WyPermissions
/*    */ {
/* 15 */   public static final Permission REMOVE_DF_COMMAND = new Permission("command.removedf");
/* 16 */   public static final Permission REMOVE_DF_COMMAND_SELF = new Permission("command.removedf.self");
/* 17 */   public static final Permission ABILITY_PROTECTION_COMMAND = new Permission("command.ability_protection");
/* 18 */   public static final Permission CHECK_FRUITS_COMMAND_LIST = new Permission("command.check_fruits.list");
/* 19 */   public static final Permission CHECK_FRUITS_COMMAND_HISTORY = new Permission("command.check_fruits.history");
/* 20 */   public static final Permission CHECK_PLAYER_COMMAND = new Permission("command.check_player");
/* 21 */   public static final Permission ISSUE_BOUNTY_COMMAND = new Permission("command.issue_bounty");
/* 22 */   public static final Permission ABILITY_COMMAND = new Permission("command.ability");
/* 23 */   public static final Permission ABILITY_COMMAND_RESET_COOLDOWN = new Permission("command.ability.reset_cooldown");
/* 24 */   public static final Permission BELLY_COMMAND = new Permission("command.belly");
/* 25 */   public static final Permission EXTOL_COMMAND = new Permission("command.extol");
/* 26 */   public static final Permission BOUNTY_COMMAND = new Permission("command.bounty");
/* 27 */   public static final Permission DORIKI_COMMAND = new Permission("command.doriki");
/* 28 */   public static final Permission HAKIEXP_COMMAND = new Permission("command.hakiexp");
/* 29 */   public static final Permission LOYALTY_COMMAND = new Permission("command.loyalty");
/* 30 */   public static final Permission QUEST_COMMAND = new Permission("command.quest");
/* 31 */   public static final Permission CHALLENGE_COMMAND = new Permission("command.challenge");
/* 32 */   public static final Permission CHANGE_CHARACTER_COMMAND = new Permission("command.change_character");
/* 33 */   public static final Permission EVENTS_COMMAND = new Permission("command.events");
/*    */ 
/*    */   
/* 36 */   public static final Permission BUSTER_CALL_ITEM = new Permission("item.buster_call");
/*    */   
/*    */   public static void init() {
/* 39 */     LogManager.getLogger(ModMain.class).info("Permissions registered:");
/* 40 */     Collections.sort(Permission.PERMISSIONS, (p1, p2) -> p1.name.compareToIgnoreCase(p2.name));
/* 41 */     for (Permission perm : Permission.PERMISSIONS) {
/* 42 */       String node = PermissionAPI.registerNode(perm.name, perm.level, perm.name);
/* 43 */       LogManager.getLogger(ModMain.class).info("  " + node);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static boolean hasPermission(PlayerEntity player, Permission permission) {
/* 48 */     if (!CommonConfig.INSTANCE.hasPermissionsEnabled()) {
/* 49 */       return false;
/*    */     }
/* 51 */     return PermissionAPI.hasPermission(player, permission.name);
/*    */   }
/*    */   
/*    */   public static class Permission {
/* 55 */     private static final List<Permission> PERMISSIONS = new ArrayList<>();
/*    */     
/*    */     public final String name;
/*    */     public final DefaultPermissionLevel level;
/*    */     
/*    */     public Permission(String name) {
/* 61 */       this(name, DefaultPermissionLevel.ALL);
/*    */     }
/*    */     
/*    */     public Permission(String name, DefaultPermissionLevel level) {
/* 65 */       this.name = "mineminenomi." + name;
/* 66 */       this.level = level;
/* 67 */       PERMISSIONS.add(this);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\integrations\perms\WyPermissions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */