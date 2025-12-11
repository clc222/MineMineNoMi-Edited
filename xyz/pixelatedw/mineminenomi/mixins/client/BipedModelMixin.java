/*    */ package xyz.pixelatedw.mineminenomi.mixins.client;
/*    */ 
/*    */ import net.minecraft.client.renderer.entity.model.AgeableModel;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.math.MathHelper;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.effects.ModEffect;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*    */ import xyz.pixelatedw.mineminenomi.config.ClientConfig;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Mixin({BipedModel.class})
/*    */ public abstract class BipedModelMixin<T extends LivingEntity>
/*    */   extends AgeableModel<T>
/*    */ {
/*    */   @Shadow
/*    */   public ModelRenderer field_78116_c;
/*    */   @Shadow
/*    */   public ModelRenderer field_78115_e;
/*    */   @Shadow
/*    */   public ModelRenderer field_178723_h;
/*    */   
/*    */   @Inject(method = {"setupAnim"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void preSetupAnimation(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo callback) {
/* 40 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)entity);
/* 41 */     BipedModel model = (BipedModel)this;
/* 42 */     RendererHelper.resetHumanoidModelToDefaultPivots((EntityModel)model);
/*    */     
/* 44 */     boolean isRotationBlocked = entity.func_70651_bq().stream().anyMatch(instance -> (instance.func_188419_a() instanceof ModEffect && ((ModEffect)instance.func_188419_a()).isBlockingRotations()));
/* 45 */     if (isRotationBlocked) {
/* 46 */       RendererHelper.animationAngles((LivingEntity)entity, (EntityModel)model, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/* 47 */       callback.cancel();
/*    */     } 
/*    */     
/* 50 */     if (props.isBlackLeg() && entity.func_184614_ca().func_190926_b()) {
/* 51 */       this.field_217112_c = 0.0F;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   @Shadow
/*    */   public ModelRenderer field_178724_i;
/*    */   
/*    */   @Shadow
/*    */   public ModelRenderer field_178721_j;
/*    */   
/*    */   @Shadow
/*    */   public ModelRenderer field_178722_k;
/*    */   
/*    */   @Shadow
/*    */   public ModelRenderer field_178720_f;
/*    */   
/*    */   @Inject(method = {"setupAnim"}, at = {@At("TAIL")})
/*    */   public void legKickAnimation(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo callback) {
/* 70 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)entity);
/*    */     
/* 72 */     if (props.isBlackLeg() && (props.isInCombatMode() || ClientConfig.INSTANCE.isBlackLegAlwaysUp())) {
/* 73 */       if (((LivingEntity)entity).field_70169_q - entity.func_226277_ct_() == 0.0D && ((LivingEntity)entity).field_70166_s - entity.func_226281_cx_() == 0.0D && !entity.func_70644_a((Effect)ModEffects.MOVEMENT_BLOCKED.get())) {
/* 74 */         this.field_178721_j.field_78797_d -= 4.0F;
/* 75 */         this.field_178721_j.field_78798_e -= 3.0F;
/*    */       } 
/*    */       
/* 78 */       if (((LivingEntity)entity).field_70733_aJ > 0.0F && entity.func_184614_ca().func_190926_b()) {
/* 79 */         float swingProgress = ((LivingEntity)entity).field_70733_aJ;
/* 80 */         this.field_178721_j.field_78795_f -= ((LivingEntity)entity).field_70733_aJ * 2.0F;
/* 81 */         this.field_178721_j.field_78808_h += ((LivingEntity)entity).field_70733_aJ * 2.0F;
/* 82 */         this.field_78115_e.field_78796_g = MathHelper.func_76126_a(MathHelper.func_76129_c(swingProgress) * 6.2831855F) * 0.2F;
/* 83 */         this.field_178723_h.field_78798_e = MathHelper.func_76126_a(this.field_78115_e.field_78796_g) * 5.0F;
/* 84 */         this.field_178723_h.field_78800_c = -MathHelper.func_76134_b(this.field_78115_e.field_78796_g) * 5.0F;
/* 85 */         this.field_178724_i.field_78798_e = -MathHelper.func_76126_a(this.field_78115_e.field_78796_g) * 5.0F;
/* 86 */         this.field_178724_i.field_78800_c = MathHelper.func_76134_b(this.field_78115_e.field_78796_g) * 5.0F;
/* 87 */         this.field_178723_h.field_78796_g += this.field_78115_e.field_78796_g;
/* 88 */         this.field_178724_i.field_78796_g += this.field_78115_e.field_78796_g;
/* 89 */         this.field_178724_i.field_78795_f += this.field_78115_e.field_78796_g;
/*    */         
/* 91 */         this.field_78116_c.func_217177_a(this.field_178720_f);
/*    */       } 
/*    */     } 
/*    */     
/* 95 */     BipedModel model = (BipedModel)this;
/* 96 */     RendererHelper.animationAngles((LivingEntity)entity, (EntityModel)model, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\client\BipedModelMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */