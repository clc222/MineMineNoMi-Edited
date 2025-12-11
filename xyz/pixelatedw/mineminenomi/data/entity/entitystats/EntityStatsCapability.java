/*    */ package xyz.pixelatedw.mineminenomi.data.entity.entitystats;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.nbt.INBT;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraftforge.common.capabilities.Capability;
/*    */ import net.minecraftforge.common.capabilities.CapabilityInject;
/*    */ import net.minecraftforge.common.capabilities.CapabilityManager;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class EntityStatsCapability
/*    */ {
/*    */   @CapabilityInject(IEntityStats.class)
/* 15 */   public static final Capability<IEntityStats> INSTANCE = null;
/*    */ 
/*    */   
/*    */   public static void register() {
/* 19 */     CapabilityManager.INSTANCE.register(IEntityStats.class, new Capability.IStorage<IEntityStats>()
/*    */         {
/*    */ 
/*    */           
/*    */           public INBT writeNBT(Capability<IEntityStats> capability, IEntityStats instance, Direction side)
/*    */           {
/* 25 */             CompoundNBT props = new CompoundNBT();
/*    */             
/* 27 */             props.func_74780_a("doriki", instance.getDoriki());
/* 28 */             props.func_74768_a("cola", instance.getCola());
/* 29 */             props.func_74768_a("ultraCola", instance.getUltraCola());
/* 30 */             props.func_74780_a("loyalty", instance.getLoyalty());
/* 31 */             props.func_74768_a("invulnerableTime", instance.getInvulnerableTime());
/* 32 */             props.func_74780_a("damageMultiplier", instance.getDamageMultiplier());
/* 33 */             props.func_74772_a("bounty", instance.getBounty());
/* 34 */             props.func_74772_a("belly", instance.getBelly());
/* 35 */             props.func_74772_a("extol", instance.getExtol());
/* 36 */             props.func_74778_a("faction", instance.getFaction().toString());
/* 37 */             props.func_74778_a("race", instance.getRace().toString());
/* 38 */             props.func_74778_a("subRace", instance.getSubRace());
/* 39 */             props.func_74778_a("fightingStyle", instance.getFightingStyle().toString());
/* 40 */             props.func_74757_a("hasShadow", instance.hasShadow());
/* 41 */             props.func_74757_a("hadChiyuEffect", instance.hadChiyuEffect());
/* 42 */             props.func_74757_a("hasHeart", instance.hasHeart());
/* 43 */             props.func_74757_a("hasStrawDoll", instance.hasStrawDoll());
/* 44 */             props.func_74757_a("isInCombatMode", instance.isInCombatMode());
/* 45 */             props.func_74757_a("isRogue", instance.isRogue());
/* 46 */             props.func_74776_a("originalChiyupopoHealth", instance.getOriginalChiyupopoHealth());
/* 47 */             props.func_74768_a("freedSlaves", instance.getFreedSlaves());
/*    */             
/* 49 */             return (INBT)props;
/*    */           }
/*    */ 
/*    */ 
/*    */           
/*    */           public void readNBT(Capability<IEntityStats> capability, IEntityStats instance, Direction side, INBT nbt) {
/* 55 */             CompoundNBT props = (CompoundNBT)nbt;
/*    */             
/* 57 */             instance.setDoriki(props.func_74769_h("doriki"));
/* 58 */             instance.setUltraCola(props.func_74762_e("ultraCola"));
/* 59 */             instance.setForcedCola(props.func_74762_e("cola"));
/* 60 */             instance.setLoyalty(props.func_74769_h("loyalty"));
/* 61 */             instance.setInvulnerableTime(props.func_74762_e("invulnerableTime"));
/* 62 */             instance.setDamageMultiplier(props.func_74769_h("damageMultiplier"));
/* 63 */             instance.setBelly(props.func_74763_f("belly"));
/* 64 */             instance.setBounty(props.func_74763_f("bounty"));
/* 65 */             instance.setExtol(props.func_74763_f("extol"));
/* 66 */             instance.setFaction(WyHelper.getResourceLocation(props, "faction"));
/* 67 */             instance.setRace(WyHelper.getResourceLocation(props, "race"));
/* 68 */             instance.setSubRace(props.func_74779_i("subRace"));
/* 69 */             instance.setFightingStyle(WyHelper.getResourceLocation(props, "fightingStyle"));
/* 70 */             instance.setShadow(props.func_74767_n("hasShadow"));
/* 71 */             instance.setChiyuEffect(props.func_74767_n("hadChiyuEffect"));
/* 72 */             instance.setHeart(props.func_74767_n("hasHeart"));
/* 73 */             instance.setStrawDoll(props.func_74767_n("hasStrawDoll"));
/* 74 */             instance.setCombatMode(props.func_74767_n("isInCombatMode"));
/* 75 */             instance.setRogue(props.func_74767_n("isRogue"));
/* 76 */             instance.setOriginalChiyupopoHealth(props.func_74760_g("originalChiyupopoHealth"));
/* 77 */             instance.setFreedSlaves(props.func_74762_e("freedSlaves"));
/*    */           }
/*    */         }() -> new EntityStatsBase());
/*    */   }
/*    */ 
/*    */   
/*    */   public static IEntityStats get(LivingEntity entity) {
/* 84 */     IEntityStats props = (IEntityStats)entity.getCapability(INSTANCE, null).orElse(new EntityStatsBase());
/* 85 */     props.setOwner(entity);
/* 86 */     return props;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\entity\entitystats\EntityStatsCapability.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */