/*    */ package xyz.pixelatedw.mineminenomi.renderers.morphs;
/*    */ import com.mojang.authlib.minecraft.MinecraftProfileTexture;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import java.util.Map;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.mane.ManeManeMemoryAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ 
/*    */ public class ManeManeMemoryRenderer<T extends AbstractClientPlayerEntity, M extends MorphModel> extends ZoanMorphRenderer<T, M> {
/*    */   public ManeManeMemoryRenderer(EntityRendererManager rendererManager, MorphInfo info, boolean hasSmallHands) {
/* 28 */     super(rendererManager, info, hasSmallHands);
/* 29 */     this.field_77045_g = (EntityModel)new NoMorphModel(hasSmallHands);
/* 30 */     func_177094_a((LayerRenderer)new BipedArmorLayer((IEntityRenderer)this, new BipedModel(0.5F), new BipedModel(1.0F)));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_225623_a_(AbstractClientPlayerEntity entity, float entityYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
/* 36 */     super.func_225623_a_(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
/*    */   }
/*    */ 
/*    */   
/*    */   public ResourceLocation func_110775_a(AbstractClientPlayerEntity entity) {
/* 41 */     ManeManeMemoryAbility ability = (ManeManeMemoryAbility)AbilityDataCapability.get((LivingEntity)entity).getEquippedAbility(ManeManeMemoryAbility.INSTANCE);
/* 42 */     if (ability != null && ability.isContinuous()) {
/* 43 */       if (ability.getMemory().getGameProfile() != null) {
/* 44 */         Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> map = Minecraft.func_71410_x().func_152342_ad().func_152788_a(ability
/* 45 */             .getMemory().getGameProfile());
/* 46 */         if (map.containsKey(MinecraftProfileTexture.Type.SKIN)) {
/* 47 */           ResourceLocation cachedSkin = Minecraft.func_71410_x().func_152342_ad().func_152792_a(map
/* 48 */               .get(MinecraftProfileTexture.Type.SKIN), MinecraftProfileTexture.Type.SKIN);
/*    */           
/* 50 */           if (cachedSkin != null) {
/* 51 */             return cachedSkin;
/*    */           }
/*    */         } 
/*    */       } 
/*    */       
/* 56 */       UUID uuid = ability.getMemory().getUUID();
/* 57 */       PlayerEntity player = entity.field_70170_p.func_217371_b(uuid);
/* 58 */       if (player != null) {
/* 59 */         return ((AbstractClientPlayerEntity)player).func_110306_p();
/*    */       }
/*    */       
/* 62 */       return DefaultPlayerSkin.func_177334_a(uuid);
/*    */     } 
/*    */ 
/*    */     
/* 66 */     return entity.func_110306_p();
/*    */   }
/*    */   
/*    */   public static class Factory<T extends PlayerEntity>
/*    */     implements IRenderFactory<T> {
/*    */     private MorphInfo info;
/*    */     private boolean hasSmallHands;
/*    */     
/*    */     public Factory(MorphInfo info, boolean hasSmallHands) {
/* 75 */       this.info = info;
/* 76 */       this.hasSmallHands = hasSmallHands;
/*    */     }
/*    */ 
/*    */     
/*    */     public EntityRenderer<? super T> createRenderFor(EntityRendererManager manager) {
/* 81 */       ManeManeMemoryRenderer<AbstractClientPlayerEntity, MorphModel> renderer = new ManeManeMemoryRenderer<>(manager, this.info, this.hasSmallHands);
/* 82 */       return (EntityRenderer)renderer;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\morphs\ManeManeMemoryRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */