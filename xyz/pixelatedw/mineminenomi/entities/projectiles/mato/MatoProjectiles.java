/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.mato;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ 
/*    */ public class MatoProjectiles
/*    */ {
/* 13 */   public static final EntityType TARGET_PROJECTILE = WyRegistry.createEntityType(TargetProjectile::new).func_220321_a(2.0F, 0.5F).func_206830_a("target_projectile");
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   @SubscribeEvent
/*    */   public static void registerEntityRenderers(FMLClientSetupEvent e) {}
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\mato\MatoProjectiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */