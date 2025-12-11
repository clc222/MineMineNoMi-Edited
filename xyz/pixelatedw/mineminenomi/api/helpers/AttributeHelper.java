/*    */ package xyz.pixelatedw.mineminenomi.api.helpers;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.ai.attributes.Attribute;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.entity.ai.attributes.Attributes;
/*    */ import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.network.play.server.SUpdateHealthPacket;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ 
/*    */ public class AttributeHelper {
/* 18 */   public static final UUID MORPH_HEALTH_UUID = UUID.fromString("47f8957b-e442-4f12-ac91-f7bedf821e89");
/* 19 */   public static final UUID MORPH_MOVEMENT_SPEED_UUID = UUID.fromString("73ec9572-1641-48f5-a464-f1a5f361cd2a");
/* 20 */   public static final UUID MORPH_ARMOR_UUID = UUID.fromString("7963cb37-cf63-4358-a5c6-7793f08e6beb");
/* 21 */   public static final UUID MORPH_ARMOR_TOUGHNESS_UUID = UUID.fromString("4eb40c49-8e26-450c-a42d-6e6e772d5848");
/* 22 */   public static final UUID MORPH_STRENGTH_UUID = UUID.fromString("99309bfc-1622-4ba5-8fa9-b0cad5152f6a");
/* 23 */   public static final UUID MORPH_ATTACK_SPEED_UUID = UUID.fromString("da37e4fa-67e1-4bfd-802a-72997d0b10c9");
/* 24 */   public static final UUID MORPH_ATTACK_REACH_UUID = UUID.fromString("7c3df274-a274-4fee-9cc8-b8cd90741b10");
/* 25 */   public static final UUID MORPH_STEP_HEIGHT_UUID = UUID.fromString("82e093a0-3762-48f9-befa-eed78ff34fa1");
/* 26 */   public static final UUID MORPH_KNOCKBACK_RESISTANCE_UUID = UUID.fromString("5e0a4164-1560-4d10-b627-90e0c24db30f");
/* 27 */   public static final UUID MORPH_FALL_RESISTANCE_UUID = UUID.fromString("1bac6f14-8f9a-4199-b2b7-96d68c1fcd90");
/* 28 */   public static final UUID MORPH_TOUGHNESS_UUID = UUID.fromString("eb3978a7-1056-401b-8d79-1ab76b5c9418");
/* 29 */   public static final UUID MORPH_JUMP_BOOST_UUID = UUID.fromString("f9e7706a-a628-4af9-8d62-e49ac7d59c02");
/* 30 */   public static final UUID MORPH_REGEN_RATE_UUID = UUID.fromString("44e331cd-347a-43dc-93ab-e8a55de54c77");
/* 31 */   public static final UUID MORPH_DAMAGE_REDUCTION_UUID = UUID.fromString("4807404c-713c-4795-b9df-dffbc24915ff");
/* 32 */   public static final UUID MORPH_SWIM_SPEED_UUID = UUID.fromString("f0a12d8e-7398-4e6a-afc1-9ceee88572e6");
/* 33 */   public static final UUID MORPH_GRAVITY_UUID = UUID.fromString("b3a23595-08de-45b8-9629-fdd6dc5f7de1");
/* 34 */   public static final UUID MORPH_MINING_SPEED_UUID = UUID.fromString("0f623bed-fd48-4619-9d4d-60a6e40614bc");
/*    */   
/* 36 */   public static final UUID PASSIVE_TOUGHNESS_UUID = UUID.fromString("18837309-bfc8-483d-8c40-44ab292e6776");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 42 */   public static final UUID ATTACK_DAMAGE_MODIFIER = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 48 */   public static final UUID SPEED_MODIFIER_SPRINTING_UUID = UUID.fromString("662A6B8D-DA3E-4C1C-8813-96EA6097278D");
/*    */ 
/*    */   
/*    */   public static void updateHPAttribute(PlayerEntity player) {
/* 52 */     if (player != null && !player.field_70170_p.field_72995_K && CommonConfig.INSTANCE.isExtraHeartsEnabled()) {
/*    */       
/* 54 */       ModifiableAttributeInstance maxHpAttribute = player.func_110148_a(Attributes.field_233818_a_);
/* 55 */       IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/* 56 */       int freq = CommonConfig.INSTANCE.getHealthGainFrequency();
/*    */       
/* 58 */       if (props.getDoriki() / freq > 20.0D) {
/* 59 */         maxHpAttribute.func_111128_a(props.getDoriki() / freq);
/*    */       } else {
/* 61 */         maxHpAttribute.func_111128_a(20.0D);
/*    */       } 
/* 63 */       if (player.func_110143_aJ() > maxHpAttribute.func_111126_e()) {
/* 64 */         player.func_70606_j((float)maxHpAttribute.func_111126_e());
/*    */       }
/* 66 */       ((ServerPlayerEntity)player).field_71135_a.func_147359_a((IPacket)new SUpdateHealthPacket(player.func_110143_aJ(), player.func_71024_bL().func_75116_a(), player.func_71024_bL().func_75115_e()));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void updateToughnessAttribute(LivingEntity entity) {
/* 72 */     if (entity != null && !entity.field_70170_p.field_72995_K) {
/* 73 */       ModifiableAttributeInstance toughnessAttribute = entity.func_110148_a((Attribute)ModAttributes.TOUGHNESS.get());
/* 74 */       if (toughnessAttribute == null) {
/*    */         return;
/*    */       }
/* 77 */       IEntityStats props = EntityStatsCapability.get(entity);
/*    */       
/* 79 */       AttributeModifier mod = new AttributeModifier(PASSIVE_TOUGHNESS_UUID, "Passive Toughness Bonus", 0.08D * props.getDoriki() / CommonConfig.INSTANCE.getDorikiLimit() * 100.0D, AttributeModifier.Operation.ADDITION);
/*    */       
/* 81 */       entity.func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_188479_b(PASSIVE_TOUGHNESS_UUID);
/* 82 */       entity.func_110148_a((Attribute)ModAttributes.TOUGHNESS.get()).func_233767_b_(mod);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static double getAttackRangeDistance(LivingEntity entity, double baseReachDistance) {
/* 88 */     ModifiableAttributeInstance reachDistance = entity.func_110148_a((Attribute)ModAttributes.ATTACK_RANGE.get());
/* 89 */     return (reachDistance != null) ? (baseReachDistance + reachDistance.func_111126_e()) : baseReachDistance;
/*    */   }
/*    */ 
/*    */   
/*    */   public static double getSquaredAttackRangeDistance(LivingEntity entity, double sqBaseReachDistance) {
/* 94 */     double reachDistance = getAttackRangeDistance(entity, Math.sqrt(sqBaseReachDistance));
/* 95 */     return reachDistance * reachDistance;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\helpers\AttributeHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */