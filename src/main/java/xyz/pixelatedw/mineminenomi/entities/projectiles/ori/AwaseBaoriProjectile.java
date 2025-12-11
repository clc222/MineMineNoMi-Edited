/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.ori;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.ori.AwaseBaoriAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ 
/*    */ public class AwaseBaoriProjectile
/*    */   extends AbilityProjectileEntity {
/*    */   public AwaseBaoriProjectile(EntityType type, World world) {
/* 21 */     super(type, world);
/*    */   }
/*    */   private List<BlockPos> blocks;
/*    */   public AwaseBaoriProjectile(World world, LivingEntity player) {
/* 25 */     super((EntityType)OriProjectiles.AWASE_BAORI.get(), world, player, AwaseBaoriAbility.INSTANCE);
/*    */     
/* 27 */     setDamage(6.0F);
/* 28 */     setMaxLife(10);
/*    */     
/* 30 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/*    */   }
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity target) {
/* 34 */     if (this.blocks == null) {
/* 35 */       this.blocks = AbilityHelper.createEmptyCube((Entity)getThrower(), target.func_226277_ct_(), target.func_226278_cu_(), target.func_226281_cx_(), 2, 2, 2, 258, (Block)ModBlocks.ORI_BARS.get(), DefaultProtectionRules.AIR_FOLIAGE);
/*    */     }
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public List<BlockPos> getBlocks() {
/* 41 */     return this.blocks;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\ori\AwaseBaoriProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */