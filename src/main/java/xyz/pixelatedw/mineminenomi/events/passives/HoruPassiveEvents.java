/*    */ package xyz.pixelatedw.mineminenomi.events.passives;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.model.ModelRenderer;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.Effect;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.event.entity.living.LivingEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.api.events.AgeableRendererEvent;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class HoruPassiveEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
/* 23 */     LivingEntity entity = event.getEntityLiving();
/*    */     
/* 25 */     if (!entity.func_70089_S()) {
/*    */       return;
/*    */     }
/*    */     
/* 29 */     if (entity.func_70644_a((Effect)ModEffects.CHIYU_HORMONE.get())) {
/* 30 */       EffectInstance effect = entity.func_70660_b((Effect)ModEffects.CHIYU_HORMONE.get());
/*    */       
/* 32 */       if (effect.func_76459_b() <= 2) {
/* 33 */         entity.func_195064_c(new EffectInstance(Effects.field_76438_s, 200, 1));
/*    */       }
/*    */     } 
/*    */     
/* 37 */     if (entity.func_70644_a((Effect)ModEffects.TENSION_HORMONE.get())) {
/* 38 */       EffectInstance effect = entity.func_70660_b((Effect)ModEffects.TENSION_HORMONE.get());
/*    */       
/* 40 */       if (effect.func_76459_b() <= 2) {
/* 41 */         entity.func_195064_c(new EffectInstance(Effects.field_76431_k, 400, 1));
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   @SubscribeEvent
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public static void onAgeableRenderer(AgeableRendererEvent<LivingEntity> event) {
/* 49 */     if (!(event.getModel() instanceof BipedModel)) {
/*    */       return;
/*    */     }
/*    */     
/* 53 */     LivingEntity entity = (LivingEntity)event.getEntity();
/*    */     
/* 55 */     if (entity == null) {
/*    */       return;
/*    */     }
/*    */     
/* 59 */     if (!entity.func_70644_a((Effect)ModEffects.GANMEN_SEICHO_HORMONE.get())) {
/*    */       return;
/*    */     }
/*    */     
/* 63 */     BipedModel<LivingEntity> model = (BipedModel<LivingEntity>)event.getModel();
/*    */     
/* 65 */     ModelRenderer renderer = event.getRenderer();
/*    */     
/* 67 */     MatrixStack matrixStack = event.getMatrixStack();
/*    */     
/* 69 */     if (renderer == model.field_78116_c || renderer == model.field_178720_f) {
/* 70 */       if (entity.func_213453_ef()) {
/* 71 */         matrixStack.func_227861_a_(0.0D, -0.75D, 0.0D);
/*    */       }
/*    */       
/* 74 */       matrixStack.func_227862_a_(3.75F, 3.75F, 3.75F);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\passives\HoruPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */