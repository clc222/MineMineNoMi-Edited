/*    */ package xyz.pixelatedw.mineminenomi.abilities.giro;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.Util;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import net.minecraft.world.IWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.PunchAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*    */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*    */ 
/*    */ public class PeepingMindAbility extends PunchAbility {
/* 24 */   public static final AbilityCore<PeepingMindAbility> INSTANCE = (new AbilityCore.Builder("Peeping Mind", AbilityCategory.DEVIL_FRUITS, PeepingMindAbility::new))
/* 25 */     .addDescriptionLine("The user looks into the enemies mind, learning about its abilities and where it lives", new Object[0])
/* 26 */     .build();
/*    */ 
/*    */   
/*    */   public PeepingMindAbility(AbilityCore<PeepingMindAbility> core) {
/* 30 */     super(core);
/*    */     
/* 32 */     setMaxCooldown(10.0D);
/* 33 */     setOnUseEvent(this::onUseEvent);
/* 34 */     this.onHitEntityEvent = this::onHitEntity;
/*    */   }
/*    */ 
/*    */   
/*    */   private float onHitEntity(PlayerEntity entity, LivingEntity target) {
/* 39 */     IEntityStats statsData = EntityStatsCapability.get(target);
/* 40 */     IDevilFruit fruitData = DevilFruitCapability.get(target);
/* 41 */     IAbilityData abilityData = AbilityDataCapability.get(target);
/* 42 */     IHakiData hakiData = HakiDataCapability.get(target);
/* 43 */     ExtendedWorldData worldData = ExtendedWorldData.get((IWorld)target.field_70170_p);
/* 44 */     Crew crew = worldData.getCrewWithMember(target.func_110124_au());
/*    */     
/* 46 */     StringBuilder builder = new StringBuilder();
/*    */     
/* 48 */     builder.append("===============================================\n");
/*    */     
/* 50 */     builder.append("Name: " + target.func_145748_c_().getString() + "\n");
/* 51 */     builder.append("Faction: " + statsData.getFaction() + "\n");
/* 52 */     if (statsData.isMarine() || statsData.isRevolutionary()) {
/*    */       
/* 54 */       builder.append("Loyalty: " + statsData.getLoyalty() + "\n");
/* 55 */       builder.append("Rank: " + statsData.getMarineRank().getLocalizedName() + "\n");
/*    */     } else {
/*    */       
/* 58 */       builder.append("Crew: " + ((crew != null) ? crew.getName() : "None") + "\n");
/*    */     } 
/*    */     
/* 61 */     builder.append("Race: " + statsData.getRace() + "\n");
/* 62 */     builder.append("Style: " + statsData.getFightingStyle() + "\n");
/* 63 */     builder.append("Doriki: " + statsData.getDoriki() + "\n");
/* 64 */     builder.append("Belly: " + statsData.getBelly() + "\n");
/* 65 */     builder.append("Extol: " + statsData.getExtol() + "\n");
/* 66 */     builder.append("Bounty: " + statsData.getBounty() + "\n");
/*    */     
/* 68 */     if (target.func_213374_dv().isPresent())
/* 69 */       builder.append("Spawn Point: " + target.func_213374_dv().isPresent() + "\n"); 
/* 70 */     builder.append("Haki level: " + (hakiData.getTotalHakiExp() / 6.0F) + "\n");
/*    */     
/* 72 */     builder.append("Unlocked Abilities: \n");
/* 73 */     for (IAbility ability : abilityData.getEquippedAbilities()) {
/*    */       
/* 75 */       if (ability != null) {
/* 76 */         builder.append("- " + ability.getCore().getLocalizedName().toString() + " \n");
/*    */       }
/*    */     } 
/* 79 */     builder.append("===============================================");
/*    */     
/* 81 */     entity.func_145747_a((ITextComponent)new StringTextComponent(builder.toString()), Util.field_240973_b_);
/* 82 */     return 0.0F;
/*    */   }
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity playerEntity) {
/* 86 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\abilities\giro\PeepingMindAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */