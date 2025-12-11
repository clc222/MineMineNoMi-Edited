/*     */ package xyz.pixelatedw.mineminenomi.events.passives;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*     */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*     */ import net.minecraft.client.renderer.entity.LivingRenderer;
/*     */ import net.minecraft.client.renderer.entity.PlayerRenderer;
/*     */ import net.minecraft.client.renderer.entity.model.IHasArm;
/*     */ import net.minecraft.client.renderer.entity.model.PlayerModel;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
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
/*     */ import xyz.pixelatedw.mineminenomi.abilities.bari.BariBariNoPistolAbility;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*     */ import xyz.pixelatedw.mineminenomi.models.abilities.SphereModel;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
/*     */ public class BariPassiveEvents {
/*  35 */   private static final SphereModel BARI_SPHERE = new SphereModel();
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityRendered(RenderLivingEvent.Post event) {
/*  40 */     if (!(event.getEntity() instanceof PlayerEntity)) {
/*     */       return;
/*     */     }
/*  43 */     PlayerEntity player = (PlayerEntity)event.getEntity();
/*  44 */     LivingRenderer renderer = event.getRenderer();
/*     */     
/*  46 */     IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
/*  47 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/*  49 */     if (abilityProps == null) {
/*     */       return;
/*     */     }
/*     */     
/*  53 */     if (!devilFruitProps.hasDevilFruit(ModAbilities.BARI_BARI_NO_MI)) {
/*     */       return;
/*     */     }
/*     */     
/*  57 */     BariBariNoPistolAbility ability = (BariBariNoPistolAbility)abilityProps.getEquippedAbility(BariBariNoPistolAbility.INSTANCE);
/*     */     
/*  59 */     if (ability != null && ability.isContinuous()) {
/*  60 */       event.getMatrixStack().func_227860_a_();
/*  61 */       event.getMatrixStack().func_227863_a_(new Quaternion(Vector3f.field_229179_b_, 180.0F, true));
/*  62 */       event.getMatrixStack().func_227863_a_(new Quaternion(Vector3f.field_229181_d_, 180.0F, true));
/*     */       
/*  64 */       float ageInTicks = player.field_70173_aa + event.getPartialRenderTick();
/*  65 */       float headYawOffset = MathHelper.func_219805_h(event.getPartialRenderTick(), player.field_70760_ar, player.field_70761_aq);
/*     */       
/*  67 */       WyHelper.rotateCorpse(event.getMatrixStack(), (LivingEntity)player, ageInTicks, headYawOffset, event.getPartialRenderTick());
/*     */       
/*  69 */       event.getMatrixStack().func_227861_a_(-0.04D, -1.3D, 0.12D);
/*     */       
/*  71 */       ((IHasArm)renderer.func_217764_d()).func_225599_a_(HandSide.RIGHT, event.getMatrixStack());
/*  72 */       event.getMatrixStack().func_227863_a_(new Quaternion(Vector3f.field_229181_d_, 90.0F, true));
/*  73 */       event.getMatrixStack().func_227862_a_(1.25F, 1.25F, 1.25F);
/*  74 */       event.getMatrixStack().func_227861_a_(0.1D, 0.4D, -0.01D);
/*     */       
/*  76 */       BARI_SPHERE.func_225598_a_(event.getMatrixStack(), event.getBuffers().getBuffer(ModRenderTypes.TRANSPARENT_COLOR), event.getLight(), 0, 0.5F, 1.0F, 0.8F, 0.4F);
/*     */       
/*  78 */       event.getMatrixStack().func_227865_b_();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onHandRendering(RenderHandEvent event) {
/*  85 */     if (event.getHand() != Hand.MAIN_HAND) {
/*     */       return;
/*     */     }
/*  88 */     if (!event.getItemStack().func_190926_b()) {
/*     */       return;
/*     */     }
/*  91 */     Minecraft mc = Minecraft.func_71410_x();
/*  92 */     ClientPlayerEntity player = mc.field_71439_g;
/*     */     
/*  94 */     IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
/*  95 */     IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/*  97 */     if (abilityProps == null) {
/*     */       return;
/*     */     }
/*     */     
/* 101 */     if (!devilFruitProps.hasDevilFruit(ModAbilities.BARI_BARI_NO_MI)) {
/*     */       return;
/*     */     }
/* 104 */     BariBariNoPistolAbility ability = (BariBariNoPistolAbility)abilityProps.getEquippedAbility(BariBariNoPistolAbility.INSTANCE);
/*     */     
/* 106 */     if (ability != null && ability.isContinuous()) {
/*     */       
/* 108 */       EntityRendererManager renderManager = mc.func_175598_ae();
/* 109 */       EntityRenderer renderer = renderManager.func_78713_a((Entity)player);
/*     */       
/* 111 */       if (!(renderer instanceof PlayerRenderer)) {
/*     */         return;
/*     */       }
/* 114 */       event.setCanceled(true);
/*     */       
/* 116 */       event.getMatrixStack().func_227860_a_();
/*     */       
/* 118 */       float f = 1.0F;
/* 119 */       float f1 = MathHelper.func_76129_c(event.getSwingProgress());
/* 120 */       float f2 = -0.3F * MathHelper.func_76126_a(f1 * 3.1415927F);
/* 121 */       float f3 = 0.4F * MathHelper.func_76126_a(f1 * 6.2831855F);
/* 122 */       float f4 = -0.4F * MathHelper.func_76126_a(event.getSwingProgress() * 3.1415927F);
/* 123 */       event.getMatrixStack().func_227861_a_((f * (f2 + 0.64000005F)), (f3 + -0.6F + event.getEquipProgress() * -0.6F), (f4 + -0.71999997F));
/* 124 */       event.getMatrixStack().func_227863_a_(new Quaternion(Vector3f.field_229181_d_, f * 45.0F, true));
/* 125 */       float f5 = MathHelper.func_76126_a(event.getSwingProgress() * event.getSwingProgress() * 3.1415927F);
/* 126 */       float f6 = MathHelper.func_76126_a(f1 * 3.1415927F);
/* 127 */       event.getMatrixStack().func_227863_a_(new Quaternion(Vector3f.field_229181_d_, f * f6 * 70.0F, true));
/* 128 */       event.getMatrixStack().func_227863_a_(new Quaternion(Vector3f.field_229183_f_, f * f5 * -20.0F, true));
/*     */       
/* 130 */       event.getMatrixStack().func_227861_a_((f * -1.0F), 3.5999999046325684D, 3.5D);
/* 131 */       event.getMatrixStack().func_227863_a_(new Quaternion(Vector3f.field_229183_f_, f * 120.0F, true));
/* 132 */       event.getMatrixStack().func_227863_a_(new Quaternion(Vector3f.field_229179_b_, 200.0F, true));
/* 133 */       event.getMatrixStack().func_227863_a_(new Quaternion(Vector3f.field_229181_d_, f * -135.0F, true));
/* 134 */       event.getMatrixStack().func_227861_a_((f * 5.6F), 0.0D, 0.0D);
/*     */       
/* 136 */       ((PlayerModel)((PlayerRenderer)renderer).func_217764_d()).field_178723_h.func_228309_a_(event.getMatrixStack(), event.getBuffers().getBuffer(ModRenderTypes.getAbilityBody(renderer.func_110775_a((Entity)player))), event.getLight(), 0, 1.0F, 1.0F, 1.0F, 1.0F);
/*     */       
/* 138 */       event.getMatrixStack().func_227861_a_(-0.4D, 0.8D, 0.01D);
/* 139 */       event.getMatrixStack().func_227862_a_(2.0F, 2.0F, 2.0F);
/*     */       
/* 141 */       BARI_SPHERE.func_225598_a_(event.getMatrixStack(), event.getBuffers().getBuffer(ModRenderTypes.TRANSPARENT_COLOR), event.getLight(), 0, 0.5F, 1.0F, 0.8F, 0.4F);
/*     */       
/* 143 */       event.getMatrixStack().func_227865_b_();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\passives\BariPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */