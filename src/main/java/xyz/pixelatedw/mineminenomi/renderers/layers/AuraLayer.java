/*     */ package xyz.pixelatedw.mineminenomi.renderers.layers;
/*     */ 
/*     */ import com.mojang.blaze3d.matrix.MatrixStack;
/*     */ import com.mojang.blaze3d.vertex.IVertexBuilder;
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Optional;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*     */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*     */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*     */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*     */ import net.minecraft.client.renderer.texture.OverlayTexture;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.math.vector.Vector3f;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.haki.KenbunshokuHakiAuraAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.components.SlotDecorationComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypeBuffers;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.buffers.HakiAuraBuffer;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ 
/*     */ public class AuraLayer<T extends LivingEntity, M extends EntityModel<T>>
/*     */   extends LayerRenderer<T, M>
/*     */ {
/*  44 */   private static final HashMap<Integer, ArrayList<AbilityDisplayData>> ABILITIES_TO_SHOW = new HashMap<>();
/*     */   
/*  46 */   private final long PHASE_IN_DURATION = 500L;
/*  47 */   private final long FULL_VISIBLE_DURATION = 3000L;
/*  48 */   private final long PHASE_OUT_DURATION = 500L;
/*     */   
/*     */   public AuraLayer(IEntityRenderer<T, M> renderer) {
/*  51 */     super(renderer);
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
/*  56 */     Minecraft mc = Minecraft.func_71410_x();
/*     */     
/*  58 */     ClientPlayerEntity clientPlayerEntity = mc.field_71439_g;
/*     */     
/*  60 */     if (entity == clientPlayerEntity) {
/*     */       return;
/*     */     }
/*     */     
/*  64 */     IEntityStats entityData = EntityStatsCapability.get((LivingEntity)clientPlayerEntity);
/*  65 */     IAbilityData abilityData = AbilityDataCapability.get((LivingEntity)clientPlayerEntity);
/*  66 */     IHakiData hakiProps = HakiDataCapability.get((LivingEntity)clientPlayerEntity);
/*     */     
/*  68 */     if (abilityData == null) {
/*     */       return;
/*     */     }
/*     */     
/*  72 */     Ability auraAbility = (Ability)abilityData.getEquippedAbility(KenbunshokuHakiAuraAbility.INSTANCE);
/*     */     
/*  74 */     boolean isActive = (auraAbility != null && auraAbility.isContinuous());
/*     */     
/*  76 */     if (!isActive) {
/*     */       return;
/*     */     }
/*     */     
/*  80 */     double dorikiPower = entityData.getDoriki() / 1000.0D;
/*     */     
/*  82 */     float hakiPower = hakiProps.getKenbunshokuHakiExp() / 2.0F;
/*     */     
/*  84 */     double finalPower = (dorikiPower + hakiPower) * 1.06D * (DevilFruitCapability.get((LivingEntity)clientPlayerEntity).hasDevilFruit(ModAbilities.GORO_GORO_NO_MI) ? 4.0D : 1.0D);
/*     */     
/*  86 */     if (entity.func_70032_d((Entity)clientPlayerEntity) > finalPower) {
/*     */       return;
/*     */     }
/*     */     
/*  90 */     matrixStack.func_227860_a_();
/*     */     
/*  92 */     String color = "#5555FF";
/*     */     
/*  94 */     if (entity instanceof net.minecraft.entity.passive.AnimalEntity) {
/*  95 */       color = "#55FF55";
/*  96 */     } else if (entity instanceof net.minecraft.entity.monster.MonsterEntity) {
/*  97 */       color = "#FF0000";
/*  98 */     } else if (entity instanceof net.minecraft.entity.player.PlayerEntity) {
/*  99 */       color = "#00FFFF";
/*     */     } 
/*     */     
/* 102 */     Color rgbColor = WyHelper.hexToRGB(color);
/*     */     
/* 104 */     float red = rgbColor.getRed() / 255.0F;
/* 105 */     float green = rgbColor.getGreen() / 255.0F;
/* 106 */     float blue = rgbColor.getBlue() / 255.0F;
/*     */     
/* 108 */     HakiAuraBuffer layerBuffer = ModRenderTypeBuffers.getInstance().getHakiAuraBuffer();
/*     */     
/* 110 */     layerBuffer.setColor((int)(red * 255.0F), (int)(green * 255.0F), (int)(blue * 255.0F), 255);
/*     */     
/* 112 */     IVertexBuilder vertex = layerBuffer.getBuffer(ModRenderTypes.getNewAuraRenderType());
/*     */     
/* 114 */     func_215332_c().func_225598_a_(matrixStack, vertex, packedLight, OverlayTexture.field_229196_a_, red, green, blue, 0.0F);
/*     */     
/* 116 */     layerBuffer.endBatch();
/*     */     
/* 118 */     matrixStack.func_227865_b_();
/*     */     
/* 120 */     if (hakiProps.getKenbunshokuHakiExp() < 50.0F) {
/*     */       return;
/*     */     }
/*     */     
/* 124 */     ArrayList<AbilityDisplayData> abilitiesToShow = ABILITIES_TO_SHOW.get(Integer.valueOf(entity.func_145782_y()));
/*     */     
/* 126 */     if (abilitiesToShow == null) {
/*     */       return;
/*     */     }
/*     */     
/* 130 */     float height = 0.0F;
/*     */     
/* 132 */     for (int i = 0; i < abilitiesToShow.size(); i++) {
/* 133 */       AbilityDisplayData abilityDisplayData = abilitiesToShow.get(i);
/*     */       
/* 135 */       IAbility ability = abilityDisplayData.ability;
/*     */       
/* 137 */       long elapsedTime = System.currentTimeMillis() - abilityDisplayData.startTime;
/*     */       
/* 139 */       float iconAlpha = 0.0F;
/*     */       
/* 141 */       Optional<SlotDecorationComponent> slotDecorationComponentOptional = ability.getComponent(ModAbilityKeys.SLOT_DECORATION);
/*     */       
/* 143 */       if (slotDecorationComponentOptional.isPresent()) {
/* 144 */         SlotDecorationComponent slotDecorationComponent = slotDecorationComponentOptional.get();
/*     */         
/* 146 */         slotDecorationComponent.triggerPreRenderEvents((LivingEntity)clientPlayerEntity, mc, matrixStack, 1.25F, -entity.func_70047_e() + 1.0F - height, partialTicks);
/*     */         
/* 148 */         if (elapsedTime <= 500L) {
/* 149 */           iconAlpha = slotDecorationComponent.getIconAlpha() / 500.0F * (float)elapsedTime;
/* 150 */         } else if (elapsedTime <= 3500L) {
/* 151 */           iconAlpha = slotDecorationComponent.getIconAlpha();
/* 152 */         } else if (elapsedTime <= 4000L) {
/* 153 */           iconAlpha = slotDecorationComponent.getIconAlpha() - (float)(elapsedTime - 3500L) / 500.0F;
/*     */         } 
/*     */         
/* 156 */         matrixStack.func_227860_a_();
/*     */         
/* 158 */         mc.func_110434_K().func_110577_a(ModResources.WIDGETS);
/*     */         
/* 160 */         float number = slotDecorationComponent.getCurrentValue();
/* 161 */         float maxNumber = slotDecorationComponent.getMaxValue();
/* 162 */         float slotHeight = MathHelper.func_76131_a(23.0F - number / maxNumber * 23.0F, 0.0F, Float.MAX_VALUE);
/*     */         
/* 164 */         RendererHelper.drawTexturedModalRect(matrixStack, 1.25F, -entity.func_70047_e() + 1.0F - height, 0.0F, 0.0F, 23.0F, 23.0F, 0.0F, 0.0625F, 0.0625F, slotDecorationComponent.getSlotRed(), slotDecorationComponent.getSlotGreen(), slotDecorationComponent.getSlotBlue(), iconAlpha / slotDecorationComponent.getIconAlpha() * 1.0F);
/* 165 */         RendererHelper.drawTexturedModalRect(matrixStack, 1.25F, -entity.func_70047_e() + 1.0F - height, 24.0F, 0.0F, 23.0F, slotHeight, 0.0F, 0.0625F, 0.0625F, 1.0F, 1.0F, 1.0F, iconAlpha / slotDecorationComponent.getIconAlpha() * 1.0F);
/*     */         
/* 167 */         matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(180.0F));
/*     */         
/* 169 */         RendererHelper.drawTexturedModalRect(matrixStack, -2.25F, -entity.func_70047_e() + 1.0F - height, 0.0F, 0.0F, 23.0F, 23.0F, 0.0F, 0.0625F, 0.0625F, slotDecorationComponent.getSlotRed(), slotDecorationComponent.getSlotGreen(), slotDecorationComponent.getSlotBlue(), iconAlpha / slotDecorationComponent.getIconAlpha() * 1.0F);
/* 170 */         RendererHelper.drawTexturedModalRect(matrixStack, -2.25F, -entity.func_70047_e() + 1.0F - height, 24.0F, 0.0F, 23.0F, slotHeight, 0.0F, 0.0625F, 0.0625F, 1.0F, 1.0F, 1.0F, iconAlpha / slotDecorationComponent.getIconAlpha() * 1.0F);
/*     */         
/* 172 */         matrixStack.func_227865_b_();
/* 173 */         matrixStack.func_227860_a_();
/*     */         
/* 175 */         RendererHelper.drawIcon(ability.getIcon((LivingEntity)entity), matrixStack, 1.5F, -entity.func_70047_e() + 1.0F - height + 0.25F, 0.0F, 1.0F, 1.0F, slotDecorationComponent.getIconRed(), slotDecorationComponent.getIconGreen(), slotDecorationComponent.getIconBlue(), iconAlpha);
/*     */         
/* 177 */         matrixStack.func_227863_a_(Vector3f.field_229181_d_.func_229187_a_(180.0F));
/*     */         
/* 179 */         RendererHelper.drawIcon(ability.getIcon((LivingEntity)entity), matrixStack, -2.0F, -entity.func_70047_e() + 1.0F - height + 0.25F, -0.0F, 1.0F, 1.0F, slotDecorationComponent.getIconRed(), slotDecorationComponent.getIconGreen(), slotDecorationComponent.getIconBlue(), iconAlpha, true, false);
/*     */         
/* 181 */         matrixStack.func_227865_b_();
/*     */         
/* 183 */         height += 1.5F;
/*     */       } 
/*     */       
/* 186 */       if (elapsedTime >= 4000L) {
/* 187 */         abilitiesToShow.remove(abilityDisplayData);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void addAbilityIcon(LivingEntity entity, IAbility ability) {
/* 193 */     if (!entity.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*     */     
/* 197 */     ABILITIES_TO_SHOW.putIfAbsent(Integer.valueOf(entity.func_145782_y()), new ArrayList<>());
/*     */     
/* 199 */     ArrayList<AbilityDisplayData> abilitiesToShow = ABILITIES_TO_SHOW.get(Integer.valueOf(entity.func_145782_y()));
/*     */     
/* 201 */     boolean abilityFound = false;
/*     */     
/* 203 */     for (int i = 0; i < abilitiesToShow.size(); i++) {
/* 204 */       if ((abilitiesToShow.get(i)).ability.equals(ability)) {
/* 205 */         abilityFound = true;
/*     */       }
/*     */     } 
/*     */     
/* 209 */     if (!abilityFound) {
/* 210 */       abilitiesToShow.add(0, new AbilityDisplayData(ability, System.currentTimeMillis()));
/*     */     }
/*     */   }
/*     */   
/*     */   private static class AbilityDisplayData
/*     */   {
/*     */     private IAbility ability;
/*     */     private long startTime;
/*     */     
/*     */     private AbilityDisplayData(IAbility ability, long startTime) {
/* 220 */       this.ability = ability;
/* 221 */       this.startTime = startTime;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\layers\AuraLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */