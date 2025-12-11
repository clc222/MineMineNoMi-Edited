/*     */ package xyz.pixelatedw.mineminenomi.events.passives;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Optional;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*     */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*     */ import net.minecraft.client.renderer.entity.model.IHasArm;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.HandSide;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.vector.Matrix4f;
/*     */ import net.minecraft.util.math.vector.Quaternion;
/*     */ import net.minecraft.util.math.vector.Vector3f;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.client.event.RenderHandEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.electro.EleclawAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.electro.ElectricalShowerAbility;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.electro.ElectricalTempestaAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.MorphHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ 
/*     */ public class MinkPassiveEvents
/*     */ {
/*  41 */   public static final Color LIGHTNING_COLOR = WyHelper.hexToRGB("#F0EC7155");
/*  42 */   public static final Color MINK_LIGHTNING_COLOR = WyHelper.hexToRGB("#71A3F055");
/*     */   
/*     */   @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
/*     */   public static class ClientEvents {
/*  46 */     private static ArrayList<Long> lightningValues1 = new ArrayList<>();
/*  47 */     private static ArrayList<Long> lightningValues2 = new ArrayList<>();
/*     */     
/*     */     public static void renderElectro(MatrixStack matrix, IHasArm model, IRenderTypeBuffer buffer, LivingEntity entity, float partialRenderTick, float scale, int lightningAmount) {
/*  50 */       for (int i = 0; i < lightningAmount; i++) {
/*  51 */         for (int z = 1; z < 5; z++) {
/*  52 */           matrix.func_227860_a_();
/*     */           
/*  54 */           Color finalColor = (i == 1 && Math.random() > 0.6D && !Minecraft.func_71410_x().func_147113_T()) ? Color.white : WyHelper.hexToRGB("#71A3F0");
/*  55 */           model.func_225599_a_(entity.func_184591_cq(), matrix);
/*  56 */           matrix.func_227862_a_(scale * 0.8F, scale * 1.4F, scale * 0.75F);
/*  57 */           matrix.func_227861_a_(((entity.func_184591_cq() == HandSide.LEFT) ? true : -1), 0.0D, 0.0D);
/*  58 */           drawMinkLightning(getRandomLightningLong(lightningValues1, (Entity)entity, 2, i + z - 1), matrix, buffer, 3, 6.0F, 8, finalColor, 255);
/*     */           
/*  60 */           matrix.func_227865_b_();
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public static long getRandomLightningLong(ArrayList<Long> oldValues, Entity entity, int ticks, int size) {
/*  67 */       boolean validValue = (oldValues.size() > size);
/*  68 */       if (Minecraft.func_71410_x().func_147113_T() || entity == null) {
/*  69 */         return validValue ? ((Long)oldValues.get(size)).longValue() : 0L;
/*     */       }
/*  71 */       if (entity.field_70173_aa % ticks == 0) {
/*     */         
/*  73 */         long value = entity.field_70170_p.field_73012_v.nextLong();
/*  74 */         if (validValue) {
/*  75 */           oldValues.set(size, Long.valueOf(value));
/*     */         } else {
/*  77 */           oldValues.add(Long.valueOf(value));
/*  78 */         }  return value;
/*     */       } 
/*     */       
/*  81 */       return validValue ? ((Long)oldValues.get(size)).longValue() : 0L;
/*     */     }
/*     */ 
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onHandRendering(RenderHandEvent event) {
/*  87 */       if (event.getHand() != Hand.MAIN_HAND || !event.getItemStack().func_190926_b()) {
/*     */         return;
/*     */       }
/*  90 */       Minecraft mc = Minecraft.func_71410_x();
/*  91 */       ClientPlayerEntity player = mc.field_71439_g;
/*     */       
/*  93 */       IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*     */       
/*  95 */       if (abilityProps == null) {
/*     */         return;
/*     */       }
/*     */       
/*  99 */       Ability eleClawAbility = (Ability)abilityProps.getEquippedAbility(EleclawAbility.INSTANCE);
/* 100 */       boolean eleClawEnabled = (eleClawAbility != null && eleClawAbility.isContinuous());
/*     */       
/* 102 */       ElectricalTempestaAbility tempestaAbility = (ElectricalTempestaAbility)abilityProps.getEquippedAbility(ElectricalTempestaAbility.INSTANCE);
/* 103 */       boolean tempestaEnabled = (tempestaAbility != null && tempestaAbility.isCharging());
/*     */       
/* 105 */       ElectricalShowerAbility showerAbility = (ElectricalShowerAbility)abilityProps.getEquippedAbility(ElectricalShowerAbility.INSTANCE);
/* 106 */       boolean showerEnabled = (showerAbility != null && showerAbility.isCharging());
/*     */       
/* 108 */       if (eleClawEnabled && !tempestaEnabled && !showerEnabled) {
/*     */         
/* 110 */         int lightningAmount = 5;
/*     */ 
/*     */         
/* 113 */         boolean flag = (player.func_184591_cq() != HandSide.LEFT);
/*     */         
/* 115 */         Optional<AbilityOverlay> overlay = Optional.ofNullable(AbilityHelper.getCurrentOverlay((PlayerEntity)player));
/* 116 */         MorphHelper.renderLimbFirstPerson(event.getMatrixStack(), event.getBuffers(), event.getLight(), event.getEquipProgress(), event.getSwingProgress(), player.func_184591_cq(), overlay, null);
/*     */         
/* 118 */         event.getMatrixStack().func_227860_a_();
/*     */         
/* 120 */         float f = flag ? 1.0F : -1.0F;
/* 121 */         float f1 = MathHelper.func_76129_c(event.getSwingProgress());
/* 122 */         float f2 = -0.3F * MathHelper.func_76126_a(f1 * 3.1415927F);
/* 123 */         float f3 = 0.4F * MathHelper.func_76126_a(f1 * 6.2831855F);
/* 124 */         float f4 = -0.4F * MathHelper.func_76126_a(event.getSwingProgress() * 3.1415927F);
/* 125 */         event.getMatrixStack().func_227861_a_((f * (f2 + 0.64000005F)), (f3 + -0.6F + event.getEquipProgress() * -0.6F), (f4 + -0.71999997F));
/* 126 */         event.getMatrixStack().func_227863_a_(new Quaternion(Vector3f.field_229181_d_, f * 45.0F, true));
/* 127 */         float f5 = MathHelper.func_76126_a(event.getSwingProgress() * event.getSwingProgress() * 3.1415927F);
/* 128 */         float f6 = MathHelper.func_76126_a(f1 * 3.1415927F);
/* 129 */         event.getMatrixStack().func_227863_a_(new Quaternion(Vector3f.field_229181_d_, f * f6 * 70.0F, true));
/* 130 */         event.getMatrixStack().func_227863_a_(new Quaternion(Vector3f.field_229183_f_, f * f5 * -20.0F, true));
/*     */         
/* 132 */         event.getMatrixStack().func_227861_a_((f * -1.0F), 3.5999999046325684D, 3.5D);
/* 133 */         event.getMatrixStack().func_227863_a_(new Quaternion(Vector3f.field_229183_f_, f * 120.0F, true));
/* 134 */         event.getMatrixStack().func_227863_a_(new Quaternion(Vector3f.field_229179_b_, 200.0F, true));
/* 135 */         event.getMatrixStack().func_227863_a_(new Quaternion(Vector3f.field_229181_d_, f * -135.0F, true));
/* 136 */         event.getMatrixStack().func_227861_a_((f * 5.6F), 0.0D, 0.0D);
/*     */         
/* 138 */         for (int i = 0; i < lightningAmount; i++) {
/*     */           
/* 140 */           for (int z = 1; z < 5; z++) {
/*     */             
/* 142 */             Color finalColor = (i == 1 && Math.random() > 0.6D && !Minecraft.func_71410_x().func_147113_T()) ? Color.white : MinkPassiveEvents.MINK_LIGHTNING_COLOR;
/* 143 */             event.getMatrixStack().func_227860_a_();
/* 144 */             event.getMatrixStack().func_227862_a_(0.01F, 0.035F, 0.01F);
/* 145 */             event.getMatrixStack().func_227861_a_(flag ? -40.0D : 40.0D, -24.0D, 0.0D);
/* 146 */             drawMinkLightning(getRandomLightningLong(lightningValues2, (Entity)player, (Math.random() > 0.5D) ? 2 : 3, i + z - 1), event.getMatrixStack(), event.getBuffers(), 4, 6.0F, 6, finalColor, 255);
/* 147 */             event.getMatrixStack().func_227865_b_();
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 152 */         event.getMatrixStack().func_227865_b_();
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public static void drawMinkLightning(long seed, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, float length, int size, Color color, int offset) {
/* 158 */       drawMinkLightning(seed, matrixStackIn, bufferIn, packedLightIn, length, size, color, offset, 5);
/*     */     }
/*     */ 
/*     */     
/*     */     public static void drawMinkLightning(long seed, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, float length, int size, Color color, int offset, int layers) {
/* 163 */       float[] afloat = new float[256], afloat1 = new float[256];
/* 164 */       float f = 0.0F, f1 = 0.0F;
/* 165 */       IVertexBuilder builder = bufferIn.getBuffer(ModRenderTypes.ENERGY);
/* 166 */       Matrix4f matrix = matrixStackIn.func_227866_c_().func_227870_a_();
/* 167 */       Random randPrev = new Random(seed), rand = new Random(seed);
/* 168 */       offset = Math.min(offset, 255);
/*     */       
/*     */       int i;
/* 171 */       for (i = offset; i >= 0; i--) {
/*     */         
/* 173 */         afloat[i] = f;
/* 174 */         afloat1[i] = f1;
/* 175 */         f += (randPrev.nextInt(11) - 5);
/* 176 */         f1 += (randPrev.nextInt(11) - 5);
/*     */       } 
/*     */       
/* 179 */       for (i = 0; i < 3; i++) {
/*     */         
/* 181 */         int l = 7;
/* 182 */         int i1 = 0;
/* 183 */         if (i > 0) {
/*     */           
/* 185 */           l = 7 - i;
/* 186 */           i1 = l - 2;
/*     */         } 
/*     */         
/* 189 */         float f2 = afloat[l] - f;
/* 190 */         float f3 = afloat1[l] - f1;
/*     */         
/* 192 */         for (int j1 = l; j1 >= i1; j1--) {
/*     */           
/* 194 */           float f4 = f2;
/* 195 */           float f5 = f3;
/* 196 */           f2 += (rand.nextInt(11) - 5);
/* 197 */           f3 += (rand.nextInt(11) - 5);
/*     */           
/* 199 */           Color color1 = color;
/* 200 */           for (int j = 1; j <= layers; j++) {
/*     */             
/* 202 */             float f6 = 0.1F + size * 0.015F * j;
/* 203 */             color = (Math.round(layers / 3.0F) > j) ? new Color(255, 255, 255, color1.getAlpha()) : color1;
/* 204 */             drawLightningQuad(matrix, builder, f2, f3, j1, length, f4, f5, f6, false, false, true, false, packedLightIn, color);
/* 205 */             drawLightningQuad(matrix, builder, f2, f3, j1, length, f4, f5, f6, true, false, true, true, packedLightIn, color);
/* 206 */             drawLightningQuad(matrix, builder, f2, f3, j1, length, f4, f5, f6, true, true, false, true, packedLightIn, color);
/* 207 */             drawLightningQuad(matrix, builder, f2, f3, j1, length, f4, f5, f6, false, true, false, false, packedLightIn, color);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     private static void drawLightningQuad(Matrix4f matrix4f, IVertexBuilder builder, float x, float z, int y, float endY, float x2, float z2, float additional, boolean a, boolean c, boolean b, boolean d, int packedLight, Color color) {
/* 215 */       builder.func_227888_a_(matrix4f, x + (a ? additional : -additional), y * endY, z + (c ? additional : -additional)).func_227885_a_(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, color.getAlpha() / 255.0F).func_227886_a_(packedLight).func_181675_d();
/* 216 */       builder.func_227888_a_(matrix4f, x2 + (a ? additional : -additional), (y + 1) * endY, z2 + (c ? additional : -additional)).func_227885_a_(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, color.getAlpha() / 255.0F).func_227886_a_(packedLight).func_181675_d();
/* 217 */       builder.func_227888_a_(matrix4f, x2 + (b ? additional : -additional), (y + 1) * endY, z2 + (d ? additional : -additional)).func_227885_a_(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, color.getAlpha() / 255.0F).func_227886_a_(packedLight).func_181675_d();
/* 218 */       builder.func_227888_a_(matrix4f, x + (b ? additional : -additional), y * endY, z + (d ? additional : -additional)).func_227885_a_(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, color.getAlpha() / 255.0F).func_227886_a_(packedLight).func_181675_d();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\events\passives\MinkPassiveEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */