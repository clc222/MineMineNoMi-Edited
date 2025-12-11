/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.doku;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.doku.HydraAbility;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.AbilityProjectileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ public class HydraProjectile extends AbilityProjectileEntity {
/*    */   private boolean isDemonMode = false;
/*    */   
/*    */   public HydraProjectile(EntityType type, World world) {
/* 19 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public HydraProjectile(World world, LivingEntity player, boolean isDemonMode) {
/* 24 */     super((EntityType)DokuProjectiles.HYDRA.get(), world, player, HydraAbility.INSTANCE);
/*    */     
/* 26 */     setDamage(isDemonMode ? 30.0F : 20.0F);
/* 27 */     setEntityCollisionSize(1.75D);
/* 28 */     setMaxLife(isDemonMode ? 40 : 30);
/*    */     
/* 30 */     setPassThroughEntities();
/* 31 */     this.isDemonMode = isDemonMode;
/*    */     
/* 33 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity hitEntity) {
/* 38 */     hitEntity.func_195064_c(new EffectInstance((Effect)ModEffects.DOKU_POISON.get(), 400, this.isDemonMode ? 3 : 1));
/* 39 */     hitEntity.func_195064_c(new EffectInstance(Effects.field_76421_d, 400, this.isDemonMode ? 2 : 1));
/* 40 */     hitEntity.func_195064_c(new EffectInstance(Effects.field_76419_f, 400, this.isDemonMode ? 2 : 1));
/* 41 */     hitEntity.func_195064_c(new EffectInstance(Effects.field_76431_k, 400, this.isDemonMode ? 2 : 1));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void writeSpawnData(PacketBuffer buffer) {
/* 47 */     super.writeSpawnData(buffer);
/* 48 */     buffer.writeBoolean(this.isDemonMode);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void readSpawnData(PacketBuffer buffer) {
/* 54 */     super.readSpawnData(buffer);
/* 55 */     this.isDemonMode = buffer.readBoolean();
/*    */   }
/*    */   
/*    */   public boolean isDemonMode() {
/* 59 */     return this.isDemonMode;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\doku\HydraProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */