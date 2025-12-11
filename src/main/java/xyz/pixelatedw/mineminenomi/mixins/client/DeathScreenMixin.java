/*    */ package xyz.pixelatedw.mineminenomi.mixins.client;
/*    */ 
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*    */ import net.minecraft.client.gui.screen.DeathScreen;
/*    */ import net.minecraft.client.gui.screen.Screen;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import org.spongepowered.asm.mixin.Final;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.Shadow;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModMorphs;
/*    */ 
/*    */ 
/*    */ @Mixin({DeathScreen.class})
/*    */ public class DeathScreenMixin
/*    */   extends Screen
/*    */ {
/*    */   public DeathScreenMixin(ITextComponent title) {
/* 27 */     super(title);
/*    */   } @Shadow
/*    */   @Final
/*    */   public boolean field_213023_c; @Inject(method = {"init"}, at = {@At("HEAD")})
/*    */   public void init(CallbackInfo callback) {
/* 32 */     Minecraft mc = Minecraft.func_71410_x();
/*    */     
/* 34 */     ClientPlayerEntity clientPlayerEntity = mc.field_71439_g;
/* 35 */     IDevilFruit props = DevilFruitCapability.get((LivingEntity)clientPlayerEntity);
/*    */     
/* 37 */     if (props.hasDevilFruit(ModAbilities.YOMI_YOMI_NO_MI) && !props.hasMorphInQueue((MorphInfo)ModMorphs.YOMI_SKELETON.get()))
/* 38 */       this.field_213023_c = false; 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\client\DeathScreenMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */