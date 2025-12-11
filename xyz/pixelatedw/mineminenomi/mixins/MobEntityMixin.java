/*    */ package xyz.pixelatedw.mineminenomi.mixins;
/*    */ import net.minecraft.entity.MobEntity;
/*    */ import net.minecraft.entity.ai.goal.Goal;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.IBindHandsEffect;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ @Mixin({MobEntity.class})
/*    */ public abstract class MobEntityMixin {
/*    */   @Inject(method = {"serverAiStep"}, at = {@At("HEAD")}, cancellable = true)
/*    */   protected void updateEntityActionState(CallbackInfo ci) {
/* 17 */     MobEntity entity = (MobEntity)this;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 25 */     boolean hasEffect = (entity.func_70651_bq().stream().map(EffectInstance::func_188419_a).filter(IBindHandsEffect.class::isInstance).filter(eff -> (eff != ModEffects.NO_HANDS.get())).filter(eff -> ((IBindHandsEffect)eff).isBlockingSwings()).count() > 0L);
/*    */     
/* 27 */     if (hasEffect) {
/* 28 */       entity.func_70605_aq().func_75642_a(entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), 0.0D);
/* 29 */       entity.field_191988_bg = 0.0F;
/* 30 */       entity.func_70661_as().func_75499_g();
/* 31 */       entity.func_70624_b(null);
/* 32 */       entity.field_70714_bg.func_220880_a(Goal.Flag.JUMP);
/* 33 */       entity.field_70714_bg.func_220880_a(Goal.Flag.MOVE);
/* 34 */       entity.field_70714_bg.func_220880_a(Goal.Flag.LOOK);
/* 35 */       entity.field_70714_bg.func_220880_a(Goal.Flag.TARGET);
/* 36 */       if (entity.func_70644_a((Effect)ModEffects.UNCONSCIOUS.get()))
/* 37 */         ci.cancel(); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\MobEntityMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */