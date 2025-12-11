/*    */ package xyz.pixelatedw.mineminenomi.abilities.electro;
/*    */ 
/*    */ import com.mojang.blaze3d.matrix.MatrixStack;
/*    */ import com.mojang.blaze3d.systems.RenderSystem;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityType;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.PassiveAbility2;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.AbilityComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.components.GaugeComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.RendererHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ 
/*    */ public class SulongCheckAbility extends PassiveAbility2 {
/* 23 */   public static final AbilityCore<SulongCheckAbility> INSTANCE = (new AbilityCore.Builder("Sulong Check", AbilityCategory.RACIAL, AbilityType.PASSIVE, SulongCheckAbility::new))
/* 24 */     .setUnlockCheck(SulongCheckAbility::canUnlock)
/* 25 */     .setHidden()
/* 26 */     .build();
/*    */   
/*    */   public SulongCheckAbility(AbilityCore<SulongCheckAbility> core) {
/* 29 */     super(core);
/*    */     
/* 31 */     if (isClientSide()) {
/* 32 */       GaugeComponent gaugeComponent = new GaugeComponent((IAbility)this, this::renderGauge);
/* 33 */       addComponents(new AbilityComponent[] { (AbilityComponent)gaugeComponent });
/*    */     } 
/*    */   }
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public void renderGauge(PlayerEntity player, MatrixStack matrixStack, int posX, int posY, SulongCheckAbility ability) {
/* 39 */     RenderSystem.enableBlend();
/* 40 */     Minecraft mc = Minecraft.func_71410_x();
/* 41 */     mc.func_110434_K().func_110577_a(ModResources.WIDGETS);
/*    */     
/* 43 */     if (ElectroHelper.canTransform(player.field_70170_p)) {
/* 44 */       RendererHelper.drawAbilityIcon(SulongAbility.INSTANCE, matrixStack, posX, (posY - 38), 0, 32.0F, 32.0F);
/*    */     }
/* 46 */     RenderSystem.disableBlend();
/*    */   }
/*    */   
/*    */   private static boolean canUnlock(LivingEntity entity) {
/* 50 */     IEntityStats props = EntityStatsCapability.get(entity);
/* 51 */     return props.isMink();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\electro\SulongCheckAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */