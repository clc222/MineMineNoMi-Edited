/*    */ package xyz.pixelatedw.mineminenomi.data.entity.challenges;
/*    */ 
/*    */ import java.util.concurrent.Callable;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.nbt.INBT;
/*    */ import net.minecraft.nbt.ListNBT;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.common.capabilities.Capability;
/*    */ import net.minecraftforge.common.capabilities.CapabilityInject;
/*    */ import net.minecraftforge.common.capabilities.CapabilityManager;
/*    */ import net.minecraftforge.fml.common.registry.GameRegistry;
/*    */ import xyz.pixelatedw.mineminenomi.api.ModRegistries;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ArenaStyle;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.Challenge;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeCore;
/*    */ 
/*    */ public class ChallengesDataCapability {
/*    */   @CapabilityInject(IChallengesData.class)
/* 21 */   public static final Capability<IChallengesData> INSTANCE = null;
/*    */   
/*    */   public static void register() {
/* 24 */     CapabilityManager.INSTANCE.register(IChallengesData.class, new Capability.IStorage<IChallengesData>()
/*    */         {
/*    */           public INBT writeNBT(Capability<IChallengesData> capability, IChallengesData instance, Direction side) {
/* 27 */             CompoundNBT props = new CompoundNBT();
/*    */             
/* 29 */             props.func_74757_a("isArenaDirty", instance.isArenaDirty());
/* 30 */             if (instance.getPreviousChallenge() != null) {
/* 31 */               props.func_74778_a("previousChallenge", instance.getPreviousChallenge().getRegistryName().toString());
/* 32 */               props.func_74778_a("previousArenaStyle", instance.getPreviousArenaStyle().name());
/* 33 */               props.func_74778_a("previousArenaClass", instance.getPreviousArenaClass());
/*    */             } 
/*    */             
/* 36 */             ListNBT challenges = new ListNBT();
/* 37 */             for (int i = 0; i < instance.getChallenges().size(); i++) {
/* 38 */               Challenge challenge = instance.getChallenges().get(i);
/* 39 */               CompoundNBT nbtData = new CompoundNBT();
/* 40 */               nbtData.func_74778_a("id", challenge.getCore().getRegistryName().toString());
/* 41 */               challenge.save(nbtData);
/* 42 */               challenges.add(nbtData);
/*    */             } 
/* 44 */             props.func_218657_a("challenges", (INBT)challenges);
/*    */             
/* 46 */             return (INBT)props;
/*    */           }
/*    */ 
/*    */           
/*    */           public void readNBT(Capability<IChallengesData> capability, IChallengesData instance, Direction side, INBT nbt) {
/* 51 */             CompoundNBT props = (CompoundNBT)nbt;
/*    */             
/* 53 */             instance.clearChallenges();
/*    */             
/*    */             try {
/* 56 */               instance.markArenaDirty(props.func_74767_n("isArenaDirty"));
/* 57 */               ChallengeCore<?> previousChallenge = (ChallengeCore)ModRegistries.CHALLENGES.getValue(new ResourceLocation(props.func_74779_i("previousChallenge")));
/* 58 */               ArenaStyle previousStyle = ArenaStyle.valueOf(props.func_74779_i("previousArenaStyle"));
/* 59 */               String previousArenaClass = props.func_74779_i("previousArenaClass");
/* 60 */               instance.setPreviousChallenge(previousChallenge, previousStyle, previousArenaClass);
/*    */             }
/* 62 */             catch (Exception exception) {}
/*    */ 
/*    */ 
/*    */             
/* 66 */             ListNBT challenges = props.func_150295_c("challenges", 10);
/* 67 */             for (int i = 0; i < challenges.size(); i++) {
/*    */               try {
/* 69 */                 CompoundNBT nbtData = challenges.func_150305_b(i);
/* 70 */                 ChallengeCore core = (ChallengeCore)GameRegistry.findRegistry(ChallengeCore.class).getValue(new ResourceLocation(nbtData.func_74779_i("id")));
/* 71 */                 if (core != null) {
/*    */                   
/* 73 */                   Challenge challenge = core.createChallenge();
/* 74 */                   challenge.load(nbtData);
/* 75 */                   instance.addChallenge(challenge);
/*    */                 } 
/* 77 */               } catch (Exception e) {}
/*    */             } 
/*    */           }
/*    */         }ChallengesDataBase::new);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static IChallengesData get(PlayerEntity entity) {
/* 87 */     IChallengesData data = (IChallengesData)entity.getCapability(INSTANCE, null).orElse(new ChallengesDataBase());
/* 88 */     data.setOwner(entity);
/* 89 */     return data;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\entity\challenges\ChallengesDataCapability.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */