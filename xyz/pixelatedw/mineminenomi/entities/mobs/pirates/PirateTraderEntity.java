/*    */ package xyz.pixelatedw.mineminenomi.entities.mobs.pirates;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.entities.TraderEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.enums.Currency;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.MobsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModLootTables;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*    */ 
/*    */ public class PirateTraderEntity
/*    */   extends TraderEntity {
/*    */   public PirateTraderEntity(EntityType type, World world) {
/* 21 */     super(type, world, MobsHelper.PIRATE_TRADERS_TEXTURES);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void func_184651_r() {
/* 27 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)this);
/* 28 */     props.setFaction(ModValues.PIRATE);
/* 29 */     super.func_184651_r();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canTrade(PlayerEntity player) {
/* 35 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 36 */     return (props.isPirate() || props.isRevolutionary() || props.isBandit());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ResourceLocation getTradeTable() {
/* 42 */     return ModLootTables.PIRATE_TRADER;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getTradeFailMessage() {
/* 48 */     return (new TranslationTextComponent(ModI18n.TRADER_NO_MARINE)).getString();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Currency getCurrency() {
/* 54 */     return Currency.BELLY;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\pirates\PirateTraderEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */