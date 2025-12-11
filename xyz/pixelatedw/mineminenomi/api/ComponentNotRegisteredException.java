/*   */ package xyz.pixelatedw.mineminenomi.api;
/*   */ 
/*   */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*   */ 
/*   */ public class ComponentNotRegisteredException
/*   */   extends RuntimeException {
/*   */   public ComponentNotRegisteredException(AbilityComponent<?> comp) {
/* 8 */     super("Component " + comp.getKey().getId() + " is not registered for " + comp.getAbility().getCore());
/*   */   }
/*   */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\ComponentNotRegisteredException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */