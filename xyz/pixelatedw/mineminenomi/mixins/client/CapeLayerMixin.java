/*    */ package xyz.pixelatedw.mineminenomi.mixins.client;
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import java.util.Arrays;
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*    */ import net.minecraft.client.renderer.IRenderTypeBuffer;
/*    */ import net.minecraft.client.renderer.entity.layers.CapeLayer;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import org.spongepowered.asm.mixin.Mixin;
/*    */ import org.spongepowered.asm.mixin.injection.At;
/*    */ import org.spongepowered.asm.mixin.injection.Inject;
/*    */ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*    */ 
/*    */ @Mixin({CapeLayer.class})
/*    */ public class CapeLayerMixin {
/* 19 */   private static final Supplier<?>[] BLOCKED_ITEMS = new Supplier[] { (Supplier)ModArmors.PIRATE_CAPTAIN_CAPE, (Supplier)ModArmors.MARINE_CAPTAIN_CAPE, (Supplier)ModArmors.FLUFFY_CAPE };
/*    */   
/*    */   @Inject(method = {"render"}, at = {@At("HEAD")}, cancellable = true)
/*    */   public void render(MatrixStack pMatrixStack, IRenderTypeBuffer pBuffer, int pPackedLight, AbstractClientPlayerEntity entity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch, CallbackInfo ci) {
/* 23 */     ItemStack stack = entity.func_184582_a(EquipmentSlotType.CHEST);
/* 24 */     boolean isCape = (!stack.func_190926_b() && Arrays.<Supplier<?>>stream(BLOCKED_ITEMS).anyMatch(supp -> (supp.get() == stack.func_77973_b())));
/* 25 */     if (isCape)
/* 26 */       ci.cancel(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\mixins\client\CapeLayerMixin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */