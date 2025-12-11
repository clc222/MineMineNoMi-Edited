/*    */ package xyz.pixelatedw.mineminenomi.mixins;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.item.BoatEntity;
/*    */ import net.minecraft.entity.item.ItemEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.IItemProvider;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.IVanishEffect;
/*    */ import xyz.pixelatedw.mineminenomi.events.items.KairosekiCoatingEvents;
/*    */ 
/*    */ @Mixin({Entity.class})
/*    */ public class EntityMixin {
/*    */   @Inject(method = {"spawnAtLocation(Lnet/minecraft/util/IItemProvider;I)Lnet/minecraft/entity/item/ItemEntity;"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void spawnAtLocation(IItemProvider provider, int offset, CallbackInfoReturnable<ItemEntity> cir) {
/* 21 */     Entity entity = (Entity)this;
/* 22 */     if (entity instanceof BoatEntity) {
/* 23 */       ItemEntity item = KairosekiCoatingEvents.dropCoatedBoat((BoatEntity)entity, new ItemStack(provider));
/* 24 */       cir.setReturnValue(item);
/*    */       return;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   @Inject(method = {"canSpawnSprintParticle"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void canSpawnSprintParticle(CallbackInfoReturnable<Boolean> ci) {
/* 32 */     Entity entity = (Entity)this;
/* 33 */     if (entity instanceof LivingEntity) {
/* 34 */       for (EffectInstance inst : ((LivingEntity)entity).func_70651_bq()) {
/* 35 */         if (inst.func_188419_a() instanceof IVanishEffect && ((IVanishEffect)inst
/* 36 */           .func_188419_a()).isVanished((LivingEntity)entity, inst.func_76459_b(), inst.func_76458_c()) && ((IVanishEffect)inst
/* 37 */           .func_188419_a()).disableParticles()) {
/* 38 */           ci.setReturnValue(Boolean.valueOf(false));
/*    */           return;
/*    */         } 
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   @Inject(method = {"isInvisible"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void isInvisible(CallbackInfoReturnable<Boolean> ci) {
/* 48 */     Entity entity = (Entity)this;
/* 49 */     if (entity instanceof LivingEntity)
/* 50 */       for (EffectInstance inst : ((LivingEntity)entity).func_70651_bq()) {
/* 51 */         if (inst.func_188419_a() instanceof IVanishEffect && ((IVanishEffect)inst
/* 52 */           .func_188419_a()).isVanished((LivingEntity)entity, inst.func_76459_b(), inst.func_76458_c())) {
/* 53 */           ci.setReturnValue(Boolean.valueOf(true));
/*    */           return;
/*    */         } 
/*    */       }  
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\EntityMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */