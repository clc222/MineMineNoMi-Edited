/*    */ package xyz.pixelatedw.mineminenomi.api;
/*    */ 
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import net.minecraftforge.registries.IForgeRegistry;
/*    */ import net.minecraftforge.registries.RegistryBuilder;
/*    */ import net.minecraftforge.registries.RegistryManager;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.ChallengeCore;
/*    */ import xyz.pixelatedw.mineminenomi.api.charactercreator.FactionId;
/*    */ import xyz.pixelatedw.mineminenomi.api.charactercreator.RaceId;
/*    */ import xyz.pixelatedw.mineminenomi.api.charactercreator.StyleId;
/*    */ import xyz.pixelatedw.mineminenomi.api.crew.JollyRogerElement;
/*    */ import xyz.pixelatedw.mineminenomi.api.interactions.Interaction;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.MorphInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.quests.QuestId;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class ModRegistries
/*    */ {
/*    */   static {
/* 24 */     make(new ResourceLocation("mineminenomi", "abilities"), AbilityCore.class);
/* 25 */     make(new ResourceLocation("mineminenomi", "morphs"), MorphInfo.class);
/* 26 */     make(new ResourceLocation("mineminenomi", "quests"), QuestId.class);
/* 27 */     make(new ResourceLocation("mineminenomi", "jolly_roger_elements"), JollyRogerElement.class);
/* 28 */     make(new ResourceLocation("mineminenomi", "challenges"), ChallengeCore.class);
/* 29 */     make(new ResourceLocation("mineminenomi", "particle_effect"), ParticleEffect.class);
/* 30 */     make(new ResourceLocation("mineminenomi", "race"), RaceId.class);
/* 31 */     make(new ResourceLocation("mineminenomi", "faction"), FactionId.class);
/* 32 */     make(new ResourceLocation("mineminenomi", "style"), StyleId.class);
/* 33 */     make(new ResourceLocation("mineminenomi", "interactions"), Interaction.class);
/*    */   }
/*    */   
/* 36 */   public static final IForgeRegistry<AbilityCore<?>> ABILITIES = RegistryManager.ACTIVE.getRegistry(AbilityCore.class);
/* 37 */   public static final IForgeRegistry<MorphInfo> MORPHS = RegistryManager.ACTIVE.getRegistry(MorphInfo.class);
/* 38 */   public static final IForgeRegistry<QuestId<?>> QUESTS = RegistryManager.ACTIVE.getRegistry(QuestId.class);
/* 39 */   public static final IForgeRegistry<JollyRogerElement> JOLLY_ROGER_ELEMENTS = RegistryManager.ACTIVE.getRegistry(JollyRogerElement.class);
/* 40 */   public static final IForgeRegistry<ChallengeCore<?>> CHALLENGES = RegistryManager.ACTIVE.getRegistry(ChallengeCore.class);
/* 41 */   public static final IForgeRegistry<ParticleEffect<?>> PARTICLE_EFFECTS = RegistryManager.ACTIVE.getRegistry(ParticleEffect.class);
/* 42 */   public static final IForgeRegistry<RaceId> RACES = RegistryManager.ACTIVE.getRegistry(RaceId.class);
/* 43 */   public static final IForgeRegistry<FactionId> FACTIONS = RegistryManager.ACTIVE.getRegistry(FactionId.class);
/* 44 */   public static final IForgeRegistry<StyleId> STYLES = RegistryManager.ACTIVE.getRegistry(StyleId.class);
/* 45 */   public static final IForgeRegistry<Interaction> INTERACTIONS = RegistryManager.ACTIVE.getRegistry(Interaction.class);
/*    */   
/*    */   public static <T extends net.minecraftforge.registries.IForgeRegistryEntry<T>> void make(ResourceLocation name, Class<T> type) {
/* 48 */     (new RegistryBuilder()).setName(name).setType(type).setMaxID(2147483646).create();
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\ModRegistries.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */