/*     */ package xyz.pixelatedw.mineminenomi.events.passives;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*     */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*     */ import net.minecraft.client.renderer.entity.LivingRenderer;
/*     */ import net.minecraft.client.renderer.entity.PlayerRenderer;
/*     */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*     */ import net.minecraft.client.renderer.entity.model.IHasArm;
/*     */ import net.minecraft.client.renderer.entity.model.PlayerModel;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.HandSide;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.vector.Quaternion;
/*     */ import net.minecraft.util.math.vector.Vector3f;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.client.event.RenderHandEvent;
/*     */ import net.minecraftforge.client.event.RenderLivingEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.gura.ShingenNoIchigekiAbility;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*     */ import xyz.pixelatedw.mineminenomi.models.abilities.SphereModel;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
/*     */ public class GuraPassiveEvents {
/*  37 */   private static final SphereModel<? extends Entity> GURA_SPHERE = new SphereModel();
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityRendered(RenderLivingEvent.Post<LivingEntity, EntityModel<LivingEntity>> event) {
/*  41 */     if (!(event.getEntity() instanceof LivingEntity)) {
/*     */       return;
/*     */     }
/*     */     
/*  45 */     LivingEntity entity = event.getEntity();
/*     */     
/*  47 */     LivingRenderer<LivingEntity, EntityModel<LivingEntity>> renderer = event.getRenderer();
/*     */     
/*  49 */     MatrixStack matrixStack = event.getMatrixStack();
/*     */     
/*  51 */     IAbilityData abilityProps = AbilityDataCapability.get(entity);
/*     */     
/*  53 */     if (abilityProps == null) {
/*     */       return;
/*     */     }
/*     */     
/*  57 */     ShingenNoIchigekiAbility ability = (ShingenNoIchigekiAbility)abilityProps.getEquippedAbility(ShingenNoIchigekiAbility.INSTANCE);
/*     */     
/*  59 */     if (ability != null && ability.isContinuous()) {
/*  60 */       matrixStack.func_227860_a_();
/*  61 */       matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229179_b_, 180.0F, true));
/*  62 */       matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229181_d_, 180.0F, true));
/*     */       
/*  64 */       float ageInTicks = entity.field_70173_aa + event.getPartialRenderTick();
/*  65 */       float headYawOffset = MathHelper.func_219805_h(event.getPartialRenderTick(), entity.field_70760_ar, entity.field_70761_aq);
/*     */       
/*  67 */       WyHelper.rotateCorpse(matrixStack, entity, ageInTicks, headYawOffset, event.getPartialRenderTick());
/*     */       
/*  69 */       matrixStack.func_227861_a_(-0.04D, -1.3D, 0.12D);
/*     */       
/*  71 */       ((IHasArm)renderer.func_217764_d()).func_225599_a_(HandSide.RIGHT, matrixStack);
/*     */       
/*  73 */       matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229181_d_, 90.0F, true));
/*  74 */       matrixStack.func_227862_a_(1.1F, 1.1F, 1.1F);
/*  75 */       matrixStack.func_227861_a_(0.1D, 0.35D, -0.01D);
/*     */       
/*  77 */       GURA_SPHERE.func_225598_a_(matrixStack, event.getBuffers().getBuffer(ModRenderTypes.ENERGY), 10000, 0, 0.4F, 0.4F, 0.4F, 0.7F);
/*     */       
/*  79 */       matrixStack.func_227865_b_();
/*     */     } 
/*     */     
/*  82 */     EffectInstance instance = entity.func_70660_b((Effect)ModEffects.SEISMIC_BUBBLE.get());
/*     */     
/*  84 */     if (instance != null) {
/*  85 */       matrixStack.func_227860_a_();
/*  86 */       matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229179_b_, 180.0F, true));
/*  87 */       matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229181_d_, 180.0F, true));
/*     */       
/*  89 */       float ageInTicks = entity.field_70173_aa + event.getPartialRenderTick();
/*  90 */       float headYawOffset = MathHelper.func_219805_h(event.getPartialRenderTick(), entity.field_70760_ar, entity.field_70761_aq);
/*     */       
/*  92 */       WyHelper.rotateCorpse(matrixStack, entity, ageInTicks, headYawOffset, event.getPartialRenderTick());
/*     */       
/*  94 */       matrixStack.func_227861_a_(0.0D, -entity.func_70047_e(), 0.0D);
/*     */       
/*  96 */       float startingTick = 10.0F;
/*     */       
/*  98 */       float minScale = 3.2F;
/*  99 */       float maxScale = 32.0F;
/*     */       
/* 101 */       float scale = minScale;
/*     */       
/* 103 */       if (instance.func_76459_b() <= startingTick) {
/* 104 */         float t = 1.0F - instance.func_76459_b() / startingTick;
/*     */         
/* 106 */         scale = minScale + t * t * (maxScale - minScale);
/*     */       } 
/*     */       
/* 109 */       matrixStack.func_227862_a_(scale, scale, scale);
/*     */       
/* 111 */       GURA_SPHERE.func_225598_a_(matrixStack, event.getBuffers().getBuffer(ModRenderTypes.ENERGY_NO_CULL), 10000, 0, 0.4F, 0.4F, 0.4F, 0.7F);
/*     */       
/* 113 */       matrixStack.func_227865_b_();
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onHandRendering(RenderHandEvent event) {
/* 119 */     if (event.getHand() != Hand.MAIN_HAND) {
/*     */       return;
/*     */     }
/*     */     
/* 123 */     if (!event.getItemStack().func_190926_b()) {
/*     */       return;
/*     */     }
/*     */     
/* 127 */     Minecraft mc = Minecraft.func_71410_x();
/*     */     
/* 129 */     ClientPlayerEntity player = mc.field_71439_g;
/*     */     
/* 131 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/* 133 */     if (abilityProps == null) {
/*     */       return;
/*     */     }
/*     */     
/* 137 */     ShingenNoIchigekiAbility ability = (ShingenNoIchigekiAbility)abilityProps.getEquippedAbility(ShingenNoIchigekiAbility.INSTANCE);
/*     */     
/* 139 */     if (ability != null && ability.isContinuous()) {
/* 140 */       MatrixStack matrixStack = event.getMatrixStack();
/*     */       
/* 142 */       EntityRendererManager renderManager = mc.func_175598_ae();
/*     */       
/* 144 */       EntityRenderer<? super ClientPlayerEntity> renderer = renderManager.func_78713_a((Entity)player);
/*     */       
/* 146 */       if (!(renderer instanceof PlayerRenderer)) {
/*     */         return;
/*     */       }
/*     */       
/* 150 */       event.setCanceled(true);
/*     */       
/* 152 */       matrixStack.func_227860_a_();
/*     */       
/* 154 */       float f = 1.0F;
/* 155 */       float f1 = MathHelper.func_76129_c(event.getSwingProgress());
/* 156 */       float f2 = -0.3F * MathHelper.func_76126_a(f1 * 3.1415927F);
/* 157 */       float f3 = 0.4F * MathHelper.func_76126_a(f1 * 6.2831855F);
/* 158 */       float f4 = -0.4F * MathHelper.func_76126_a(event.getSwingProgress() * 3.1415927F);
/*     */       
/* 160 */       matrixStack.func_227861_a_((f * (f2 + 0.64000005F)), (f3 + -0.6F + event.getEquipProgress() * -0.6F), (f4 + -0.71999997F));
/* 161 */       matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229181_d_, f * 45.0F, true));
/*     */       
/* 163 */       float f5 = MathHelper.func_76126_a(event.getSwingProgress() * event.getSwingProgress() * 3.1415927F);
/* 164 */       float f6 = MathHelper.func_76126_a(f1 * 3.1415927F);
/*     */       
/* 166 */       matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229181_d_, f * f6 * 70.0F, true));
/* 167 */       matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229183_f_, f * f5 * -20.0F, true));
/*     */       
/* 169 */       matrixStack.func_227861_a_((f * -1.0F), 3.5999999046325684D, 3.5D);
/* 170 */       matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229183_f_, f * 120.0F, true));
/* 171 */       matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229179_b_, 200.0F, true));
/* 172 */       matrixStack.func_227863_a_(new Quaternion(Vector3f.field_229181_d_, f * -135.0F, true));
/* 173 */       matrixStack.func_227861_a_((f * 5.6F), 0.0D, 0.0D);
/*     */       
/* 175 */       ((PlayerModel)((PlayerRenderer)renderer).func_217764_d()).field_178723_h.func_228309_a_(event.getMatrixStack(), event.getBuffers().getBuffer(ModRenderTypes.getAbilityBody(renderer.func_110775_a((Entity)player))), event.getLight(), 0, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */       
/* 177 */       matrixStack.func_227861_a_(-0.4D, 0.8D, 0.01D);
/* 178 */       matrixStack.func_227862_a_(1.5F, 1.5F, 1.5F);
/*     */       
/* 180 */       GURA_SPHERE.func_225598_a_(matrixStack, event.getBuffers().getBuffer(ModRenderTypes.ENERGY), 10000, 0, 0.4F, 0.4F, 0.4F, 0.7F);
/*     */       
/* 182 */       matrixStack.func_227865_b_();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\passives\GuraPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */