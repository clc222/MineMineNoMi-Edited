/*    */ package xyz.pixelatedw.mineminenomi.api.abilities.components;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilityKeys;
/*    */ 
/*    */ public class GaugeComponent
/*    */   extends AbilityComponent<IAbility>
/*    */ {
/*    */   private IGaugeRenderer renderer;
/*    */   
/*    */   public <A extends IAbility> GaugeComponent(IAbility ability, IGaugeRenderer<A> renderer) {
/* 16 */     super(ModAbilityKeys.GAUGE, ability);
/* 17 */     this.renderer = renderer;
/*    */     
/* 19 */     setClientSide();
/*    */   }
/*    */   
/*    */   public <A extends IAbility> IGaugeRenderer<A> getRenderer() {
/* 23 */     return this.renderer;
/*    */   }
/*    */   
/*    */   @FunctionalInterface
/*    */   public static interface IGaugeRenderer<A extends IAbility> {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     void renderGauge(PlayerEntity param1PlayerEntity, MatrixStack param1MatrixStack, int param1Int1, int param1Int2, A param1A);
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\abilities\components\GaugeComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */