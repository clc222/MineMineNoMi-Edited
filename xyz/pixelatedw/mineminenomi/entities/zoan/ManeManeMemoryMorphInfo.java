/*    */ package xyz.pixelatedw.mineminenomi.entities.zoan;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
/*    */ import net.minecraft.client.resources.DefaultPlayerSkin;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.mane.ManeManeMemoryAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphModel;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*    */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*    */ import xyz.pixelatedw.mineminenomi.renderers.morphs.ManeManeMemoryRenderer;
/*    */ 
/*    */ public class ManeManeMemoryMorphInfo
/*    */   extends MorphInfo
/*    */ {
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public IRenderFactory getRendererFactory(LivingEntity entity) {
/* 24 */     boolean isSlim = false;
/* 25 */     ManeManeMemoryAbility ability = (ManeManeMemoryAbility)AbilityDataCapability.get(entity).getEquippedAbility(ManeManeMemoryAbility.INSTANCE);
/* 26 */     if (ability != null && ability.isContinuous()) {
/* 27 */       UUID uuid = ability.getMemory().getUUID();
/* 28 */       PlayerEntity target = entity.field_70170_p.func_217371_b(uuid);
/* 29 */       if (target != null && target instanceof AbstractClientPlayerEntity) {
/* 30 */         isSlim = ((AbstractClientPlayerEntity)target).func_175154_l().equals("slim");
/*    */       } else {
/*    */         
/* 33 */         isSlim = DefaultPlayerSkin.func_177332_b(uuid).equals("slim");
/*    */       } 
/*    */     } 
/* 36 */     return (IRenderFactory)new ManeManeMemoryRenderer.Factory(this, isSlim);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public MorphModel getModel() {
/* 49 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   @OnlyIn(Dist.CLIENT)
/*    */   public boolean shouldRenderFirstPersonHand() {
/* 55 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public AkumaNoMiItem getDevilFruit() {
/* 60 */     return ModAbilities.MANE_MANE_NO_MI;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getForm() {
/* 65 */     return "mane_memory";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getDisplayName() {
/* 70 */     return ManeManeMemoryAbility.INSTANCE.getUnlocalizedName();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\zoan\ManeManeMemoryMorphInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */