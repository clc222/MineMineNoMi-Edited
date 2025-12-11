/*    */ package xyz.pixelatedw.mineminenomi.renderers.abilities;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.IEntityRenderer;
/*    */ import net.minecraft.client.renderer.entity.layers.LayerRenderer;
/*    */ import net.minecraft.client.renderer.entity.model.EntityModel;
/*    */ import net.minecraft.client.renderer.entity.model.IHasArm;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.electro.EleclawAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.electro.ElectricalShowerAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.electro.ElectricalTempestaAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.electro.SulongAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.events.passives.MinkPassiveEvents;
/*    */ 
/*    */ public class EleclawRenderer<T extends LivingEntity, M extends EntityModel<T>> extends LayerRenderer<T, M> {
/*    */   public EleclawRenderer(IEntityRenderer renderer) {
/* 22 */     super(renderer);
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int pPackedLight, T entity, float pLimbSwing, float pLimbSwingAmount, float partialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
/* 27 */     if (!(func_215332_c() instanceof IHasArm)) {
/*    */       return;
/*    */     }
/*    */     
/* 31 */     IAbilityData data = AbilityDataCapability.get((LivingEntity)entity);
/*    */     
/* 33 */     if (data == null) {
/*    */       return;
/*    */     }
/*    */     
/* 37 */     MatrixStack matrix = matrixStack;
/*    */     
/* 39 */     Ability eleClawAbility = (Ability)data.getEquippedAbility(EleclawAbility.INSTANCE);
/* 40 */     boolean eleClawEnabled = (eleClawAbility != null && eleClawAbility.isContinuous());
/*    */     
/* 42 */     Ability ability = (Ability)data.getEquippedAbility(SulongAbility.INSTANCE);
/* 43 */     boolean sulongEnabled = (ability != null && ability.isContinuous());
/*    */     
/* 45 */     ElectricalTempestaAbility tempestaAbility = (ElectricalTempestaAbility)data.getEquippedAbility(ElectricalTempestaAbility.INSTANCE);
/* 46 */     boolean tempestaEnabled = (tempestaAbility != null && tempestaAbility.isCharging());
/*    */     
/* 48 */     ElectricalShowerAbility showerAbility = (ElectricalShowerAbility)data.getEquippedAbility(ElectricalShowerAbility.INSTANCE);
/* 49 */     boolean showerEnabled = (showerAbility != null && showerAbility.isCharging());
/*    */     
/* 51 */     if (eleClawEnabled && !tempestaEnabled && !showerEnabled) {
/* 52 */       int lightningAmount = 5 + (sulongEnabled ? 3 : 0);
/* 53 */       matrix.func_227860_a_();
/* 54 */       matrix.func_227861_a_(-0.05D, 0.0D, 0.0D);
/* 55 */       MinkPassiveEvents.ClientEvents.renderElectro(matrix, (IHasArm)func_215332_c(), buffer, (LivingEntity)entity, partialTicks, 0.01F, lightningAmount);
/* 56 */       matrix.func_227865_b_();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\renderers\abilities\EleclawRenderer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */