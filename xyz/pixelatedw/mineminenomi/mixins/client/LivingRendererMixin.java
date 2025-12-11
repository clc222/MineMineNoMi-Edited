/*     */ package xyz.pixelatedw.mineminenomi.mixins.client;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import java.util.Optional;
/*     */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.client.renderer.entity.LivingRenderer;
/*     */ import net.minecraft.client.renderer.entity.MobRenderer;
/*     */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.Pose;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.vector.Matrix4f;
/*     */ import net.minecraft.util.math.vector.Vector3d;
/*     */ import net.minecraft.util.math.vector.Vector3f;
/*     */ import net.minecraft.world.LightType;
/*     */ import org.spongepowered.asm.mixin.Mixin;
/*     */ import org.spongepowered.asm.mixin.injection.At;
/*     */ import org.spongepowered.asm.mixin.injection.Inject;
/*     */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.effects.ModEffect;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Mixin({LivingRenderer.class})
/*     */ public class LivingRendererMixin<T extends LivingEntity, M extends EntityModel<T>>
/*     */ {
/*  35 */   private Optional<Float> prevYaw = Optional.empty();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Inject(method = {"render"}, at = {@At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/model/EntityModel;setupAnim(Lnet/minecraft/entity/Entity;FFFFF)V", shift = At.Shift.AFTER)})
/*     */   public void renderPost(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, CallbackInfo callback) {
/*  51 */     RendererHelper.animationSetup((LivingEntity)entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*     */   }
/*     */   
/*     */   @Inject(method = {"render"}, at = {@At("TAIL")})
/*     */   public void render(T entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, CallbackInfo callback) {
/*  56 */     if (entity instanceof net.minecraft.entity.player.PlayerEntity) {
/*  57 */       LivingEntity leashHolder = EntityStatsCapability.get((LivingEntity)entity).getLeashHolder();
/*  58 */       if (leashHolder != null) {
/*  59 */         renderLeash(entity, partialTicks, matrixStack, buffer, leashHolder);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private <E extends Entity> void renderLeash(T entity, float pPartialTicks, MatrixStack pMatrixStack, IRenderTypeBuffer pBuffer, E pLeashHolder) {
/*  65 */     pMatrixStack.func_227860_a_();
/*  66 */     Vector3d vector3d = pLeashHolder.func_241843_o(pPartialTicks);
/*  67 */     double d0 = (MathHelper.func_219799_g(pPartialTicks, ((LivingEntity)entity).field_70761_aq, ((LivingEntity)entity).field_70760_ar) * 0.017453292F) + 1.5707963267948966D;
/*  68 */     Vector3d vector3d1 = Vector3d.field_186680_a.func_72441_c(0.0D, entity.func_213302_cg() / 1.65D, 0.0D);
/*  69 */     double d1 = Math.cos(d0) * vector3d1.field_72449_c + Math.sin(d0) * vector3d1.field_72450_a;
/*  70 */     double d2 = Math.sin(d0) * vector3d1.field_72449_c - Math.cos(d0) * vector3d1.field_72450_a;
/*  71 */     double d3 = MathHelper.func_219803_d(pPartialTicks, ((LivingEntity)entity).field_70169_q, entity.func_226277_ct_()) + d1;
/*  72 */     double d4 = MathHelper.func_219803_d(pPartialTicks, ((LivingEntity)entity).field_70167_r, entity.func_226278_cu_()) + vector3d1.field_72448_b;
/*  73 */     double d5 = MathHelper.func_219803_d(pPartialTicks, ((LivingEntity)entity).field_70166_s, entity.func_226281_cx_()) + d2;
/*  74 */     pMatrixStack.func_227861_a_(d1, vector3d1.field_72448_b, d2);
/*  75 */     float f = (float)(vector3d.field_72450_a - d3);
/*  76 */     float f1 = (float)(vector3d.field_72448_b - d4);
/*  77 */     float f2 = (float)(vector3d.field_72449_c - d5);
/*  78 */     float f3 = 0.025F;
/*  79 */     IVertexBuilder ivertexbuilder = pBuffer.getBuffer(RenderType.func_228649_h_());
/*  80 */     Matrix4f matrix4f = pMatrixStack.func_227866_c_().func_227870_a_();
/*  81 */     float f4 = MathHelper.func_226165_i_(f * f + f2 * f2) * 0.025F / 2.0F;
/*  82 */     float f5 = f2 * f4;
/*  83 */     float f6 = f * f4;
/*  84 */     BlockPos blockpos = new BlockPos(entity.func_174824_e(pPartialTicks));
/*  85 */     BlockPos blockpos1 = new BlockPos(pLeashHolder.func_174824_e(pPartialTicks));
/*  86 */     int i = entity.func_70027_ad() ? 15 : ((LivingEntity)entity).field_70170_p.func_226658_a_(LightType.BLOCK, blockpos);
/*  87 */     int j = pLeashHolder.func_70027_ad() ? 15 : ((Entity)pLeashHolder).field_70170_p.func_226658_a_(LightType.BLOCK, blockpos1);
/*  88 */     int k = ((LivingEntity)entity).field_70170_p.func_226658_a_(LightType.SKY, blockpos);
/*  89 */     int l = ((LivingEntity)entity).field_70170_p.func_226658_a_(LightType.SKY, blockpos1);
/*  90 */     MobRenderer.func_229119_a_(ivertexbuilder, matrix4f, f, f1, f2, i, j, k, l, 0.025F, 0.025F, f5, f6);
/*  91 */     MobRenderer.func_229119_a_(ivertexbuilder, matrix4f, f, f1, f2, i, j, k, l, 0.025F, 0.0F, f5, f6);
/*  92 */     pMatrixStack.func_227865_b_();
/*     */   }
/*     */ 
/*     */   
/*     */   @Inject(method = {"setupRotations"}, at = {@At("HEAD")}, cancellable = true)
/*     */   public void applyRotations(T entity, MatrixStack matrixStack, float ageInTicks, float yaw, float partialTicks, CallbackInfo callback) {
/*  98 */     boolean isRotationBlocked = entity.func_70651_bq().stream().anyMatch(instance -> (instance.func_188419_a() instanceof ModEffect && ((ModEffect)instance.func_188419_a()).isBlockingRotations()));
/*  99 */     if (isRotationBlocked) {
/* 100 */       if (!this.prevYaw.isPresent()) {
/* 101 */         this.prevYaw = Optional.of(Float.valueOf(yaw));
/*     */       }
/* 103 */       Pose pose = entity.func_213283_Z();
/* 104 */       if (pose != Pose.SLEEPING) {
/* 105 */         matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(180.0F - ((Float)this.prevYaw.get()).floatValue()));
/*     */       }
/* 107 */       callback.cancel();
/*     */     
/*     */     }
/* 110 */     else if (this.prevYaw.isPresent()) {
/* 111 */       this.prevYaw = Optional.empty();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\client\LivingRendererMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */