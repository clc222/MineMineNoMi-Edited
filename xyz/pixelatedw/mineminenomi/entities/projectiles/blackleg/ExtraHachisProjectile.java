/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.blackleg;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceHakiNature;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.SourceType;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class ExtraHachisProjectile
/*    */   extends AbilityProjectileEntity {
/*    */   public ExtraHachisProjectile(EntityType<Entity> type, World world) {
/* 18 */     super(type, world);
/*    */   }
/*    */   
/*    */   public ExtraHachisProjectile(World world, LivingEntity player, Ability abl) {
/* 22 */     super((EntityType)BlackLegProjectiles.EXTRA_HACHIS.get(), world, player, abl.getCore(), SourceElement.NONE, SourceHakiNature.HARDENING, new ArrayList(Arrays.asList((Object[])new SourceType[] { SourceType.FIST })));
/*    */     
/* 24 */     setDamage(8.0F);
/* 25 */     setMaxLife(2);
/* 26 */     setHurtTime(5);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\blackleg\ExtraHachisProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */