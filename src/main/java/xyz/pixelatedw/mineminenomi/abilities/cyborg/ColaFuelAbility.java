/*    */ package xyz.pixelatedw.mineminenomi.abilities.cyborg;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import java.awt.Color;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.client.gui.GuiUtils;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.PassiveAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.GaugeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class ColaFuelAbility extends PassiveAbility2 {
/* 25 */   public static final AbilityCore<ColaFuelAbility> INSTANCE = (new AbilityCore.Builder("Cola Fuel", AbilityCategory.RACIAL, AbilityType.PASSIVE, ColaFuelAbility::new))
/* 26 */     .setHidden()
/* 27 */     .setUnlockCheck(ColaFuelAbility::canUnlock)
/* 28 */     .build();
/*    */   
/*    */   public ColaFuelAbility(AbilityCore<ColaFuelAbility> ability) {
/* 31 */     super(ability);
/*    */     
/* 33 */     if (isClientSide()) {
/* 34 */       GaugeComponent gaugeComponent = new GaugeComponent((IAbility)this, this::renderGauge);
/*    */       
/* 36 */       addComponents(new AbilityComponent[] { (AbilityComponent)gaugeComponent });
/*    */     } 
/*    */   }
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   private void renderGauge(PlayerEntity player, MatrixStack matrixStack, int posX, int posY, ColaFuelAbility ability) {
/* 42 */     RenderSystem.enableBlend();
/* 43 */     IEntityStats entityStatsProps = EntityStatsCapability.get((LivingEntity)player);
/*    */     
/* 45 */     Minecraft mc = Minecraft.func_71410_x();
/* 46 */     mc.func_110434_K().func_110577_a(ModResources.WIDGETS);
/*    */     
/* 48 */     GuiUtils.drawTexturedModalRect(matrixStack, posX + 6, posY - 42, 0, 52, 23, 40, 0.0F);
/* 49 */     int barHeight = (int)(entityStatsProps.getCola() / entityStatsProps.getMaxCola() * 30.0F) + 23;
/*    */     
/* 51 */     if (barHeight > 0 && barHeight < 24) {
/* 52 */       barHeight = 24;
/*    */     }
/* 54 */     else if (barHeight > 52) {
/* 55 */       barHeight = 52;
/*    */     } 
/*    */     
/* 58 */     GuiUtils.drawTexturedModalRect(matrixStack, posX + 10, posY - 42, 32, barHeight, 16, 32, 0.0F);
/* 59 */     String cola = entityStatsProps.getCola() + "";
/* 60 */     WyHelper.drawStringWithBorder(mc.field_71466_p, matrixStack, cola, posX + 18 - mc.field_71466_p.func_78256_a(cola) / 2, posY - 12, Color.WHITE.getRGB());
/* 61 */     RenderSystem.disableBlend();
/*    */   }
/*    */   
/*    */   private static boolean canUnlock(LivingEntity entity) {
/* 65 */     return EntityStatsCapability.get(entity).isCyborg();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\cyborg\ColaFuelAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */