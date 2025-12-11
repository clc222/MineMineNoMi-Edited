/*     */ package xyz.pixelatedw.mineminenomi.init;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.particle.IParticleFactory;
/*     */ import net.minecraft.client.particle.ParticleManager;
/*     */ import net.minecraft.particles.ParticleType;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
/*     */ import net.minecraftforge.eventbus.api.IEventBus;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.RegistryObject;
/*     */ import net.minecraftforge.fml.common.Mod;
/*     */ import xyz.pixelatedw.mineminenomi.particles.SimpleParticle;
/*     */ import xyz.pixelatedw.mineminenomi.particles.data.SimpleParticleData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi", bus = Mod.EventBusSubscriber.Bus.MOD)
/*     */ public class ModParticleTypes {
/*  20 */   public static final RegistryObject<ParticleType<SimpleParticleData>> YUKI = WyRegistry.registerParticleType("Yuki Particle", SimpleParticleData::new);
/*  21 */   public static final RegistryObject<ParticleType<SimpleParticleData>> YUKI2 = WyRegistry.registerParticleType("Yuki2 Particle", SimpleParticleData::new);
/*  22 */   public static final RegistryObject<ParticleType<SimpleParticleData>> YUKI3 = WyRegistry.registerParticleType("Yuki3 Particle", SimpleParticleData::new);
/*     */   
/*  24 */   public static final RegistryObject<ParticleType<SimpleParticleData>> PIKA = WyRegistry.registerParticleType("Pika Particle", SimpleParticleData::new);
/*     */   
/*  26 */   public static final RegistryObject<ParticleType<SimpleParticleData>> MERA = WyRegistry.registerParticleType("Mera Particle", SimpleParticleData::new);
/*  27 */   public static final RegistryObject<ParticleType<SimpleParticleData>> MERA2 = WyRegistry.registerParticleType("Mera2 Particle", SimpleParticleData::new);
/*     */   
/*  29 */   public static final RegistryObject<ParticleType<SimpleParticleData>> MOKU = WyRegistry.registerParticleType("Moku Particle", SimpleParticleData::new);
/*  30 */   public static final RegistryObject<ParticleType<SimpleParticleData>> MOKU2 = WyRegistry.registerParticleType("Moku2 Particle", SimpleParticleData::new);
/*     */   
/*  32 */   public static final RegistryObject<ParticleType<SimpleParticleData>> SUNA = WyRegistry.registerParticleType("Suna Particle", SimpleParticleData::new);
/*  33 */   public static final RegistryObject<ParticleType<SimpleParticleData>> SUNA2 = WyRegistry.registerParticleType("Suna2 Particle", SimpleParticleData::new);
/*     */   
/*  35 */   public static final RegistryObject<ParticleType<SimpleParticleData>> GASU = WyRegistry.registerParticleType("Gasu Particle", SimpleParticleData::new);
/*  36 */   public static final RegistryObject<ParticleType<SimpleParticleData>> GASU2 = WyRegistry.registerParticleType("Gasu2 Particle", SimpleParticleData::new);
/*     */   
/*  38 */   public static final RegistryObject<ParticleType<SimpleParticleData>> BLUE_FLAME = WyRegistry.registerParticleType("Blue Flame Particle", SimpleParticleData::new);
/*     */   
/*  40 */   public static final RegistryObject<ParticleType<SimpleParticleData>> DARKNESS = WyRegistry.registerParticleType("Darkness Particle", SimpleParticleData::new);
/*  41 */   public static final RegistryObject<ParticleType<SimpleParticleData>> DARKNESS_STATIC = WyRegistry.registerParticleType("Darkness Static Particle", SimpleParticleData::new);
/*  42 */   public static final RegistryObject<ParticleType<SimpleParticleData>> KUROUZU = WyRegistry.registerParticleType("Kurouzu Particle", SimpleParticleData::new);
/*     */   
/*  44 */   public static final RegistryObject<ParticleType<SimpleParticleData>> GORO = WyRegistry.registerParticleType("Goro Particle", SimpleParticleData::new);
/*  45 */   public static final RegistryObject<ParticleType<SimpleParticleData>> GORO2 = WyRegistry.registerParticleType("Goro2 Particle", SimpleParticleData::new);
/*  46 */   public static final RegistryObject<ParticleType<SimpleParticleData>> GORO3 = WyRegistry.registerParticleType("Goro3 Particle", SimpleParticleData::new);
/*     */   
/*  48 */   public static final RegistryObject<ParticleType<SimpleParticleData>> MAGU = WyRegistry.registerParticleType("Magu Particle", SimpleParticleData::new);
/*     */   
/*  50 */   public static final RegistryObject<ParticleType<SimpleParticleData>> DOKU = WyRegistry.registerParticleType("Doku Particle", SimpleParticleData::new);
/*  51 */   public static final RegistryObject<ParticleType<SimpleParticleData>> DOKU_PINK = WyRegistry.registerParticleType("Doku Pink Particle", SimpleParticleData::new);
/*     */   
/*  53 */   public static final RegistryObject<ParticleType<SimpleParticleData>> ITO = WyRegistry.registerParticleType("Ito Particle", SimpleParticleData::new);
/*     */   
/*  55 */   public static final RegistryObject<ParticleType<SimpleParticleData>> GURA = WyRegistry.registerParticleType("Gura Particle", SimpleParticleData::new);
/*  56 */   public static final RegistryObject<ParticleType<SimpleParticleData>> GURA2 = WyRegistry.registerParticleType("Gura2 Particle", SimpleParticleData::new);
/*     */   
/*  58 */   public static final RegistryObject<ParticleType<SimpleParticleData>> HIE = WyRegistry.registerParticleType("Hie Particle", SimpleParticleData::new);
/*  59 */   public static final RegistryObject<ParticleType<SimpleParticleData>> HIE_FLAKES = WyRegistry.registerParticleType("Hie Flakes Particle", SimpleParticleData::new);
/*     */   
/*  61 */   public static final RegistryObject<ParticleType<SimpleParticleData>> MERO = WyRegistry.registerParticleType("Mero Particle", SimpleParticleData::new);
/*     */   
/*  63 */   public static final RegistryObject<ParticleType<SimpleParticleData>> HORU = WyRegistry.registerParticleType("Horu Particle", SimpleParticleData::new);
/*     */   
/*  65 */   public static final RegistryObject<ParticleType<SimpleParticleData>> CHIYU = WyRegistry.registerParticleType("Chiyu Particle", SimpleParticleData::new);
/*     */   
/*  67 */   public static final RegistryObject<ParticleType<SimpleParticleData>> RUST = WyRegistry.registerParticleType("Rust Particle", SimpleParticleData::new);
/*     */   
/*  69 */   public static final RegistryObject<ParticleType<SimpleParticleData>> AWA = WyRegistry.registerParticleType("Awa Particle", SimpleParticleData::new);
/*  70 */   public static final RegistryObject<ParticleType<SimpleParticleData>> AWA2 = WyRegistry.registerParticleType("Awa2 Particle", SimpleParticleData::new);
/*  71 */   public static final RegistryObject<ParticleType<SimpleParticleData>> AWA3 = WyRegistry.registerParticleType("Awa3 Particle", SimpleParticleData::new);
/*  72 */   public static final RegistryObject<ParticleType<SimpleParticleData>> AWA_FOAM = WyRegistry.registerParticleType("Awa Foam Particle", SimpleParticleData::new);
/*     */   
/*  74 */   public static final RegistryObject<ParticleType<SimpleParticleData>> BETA = WyRegistry.registerParticleType("Beta Particle", SimpleParticleData::new);
/*     */   
/*  76 */   public static final RegistryObject<ParticleType<SimpleParticleData>> NETSU = WyRegistry.registerParticleType("Netsu Particle", SimpleParticleData::new);
/*  77 */   public static final RegistryObject<ParticleType<SimpleParticleData>> NETSU2 = WyRegistry.registerParticleType("Netsu2 Particle", SimpleParticleData::new);
/*     */   
/*  79 */   public static final RegistryObject<ParticleType<SimpleParticleData>> GRILL = WyRegistry.registerParticleType("Grill Particle", SimpleParticleData::new);
/*     */   
/*  81 */   public static final RegistryObject<ParticleType<SimpleParticleData>> MEDIC = WyRegistry.registerParticleType("Medic Particle", SimpleParticleData::new);
/*     */   
/*  83 */   public static final RegistryObject<ParticleType<SimpleParticleData>> GORO_YELLOW = WyRegistry.registerParticleType("Goro Yellow Particle", SimpleParticleData::new);
/*  84 */   public static final RegistryObject<ParticleType<SimpleParticleData>> GORO2_YELLOW = WyRegistry.registerParticleType("Goro2 Yellow Particle", SimpleParticleData::new);
/*  85 */   public static final RegistryObject<ParticleType<SimpleParticleData>> GORO3_YELLOW = WyRegistry.registerParticleType("Goro3 Yellow Particle", SimpleParticleData::new);
/*     */   
/*  87 */   public static final RegistryObject<ParticleType<SimpleParticleData>> HANA = WyRegistry.registerParticleType("Hana Particle", SimpleParticleData::new);
/*     */   
/*  89 */   public static final RegistryObject<ParticleType<SimpleParticleData>> MAGNET = WyRegistry.registerParticleType("Magnet Particle", SimpleParticleData::new);
/*     */   
/*  91 */   public static final RegistryObject<ParticleType<SimpleParticleData>> SIMPLE_CIRCLE = WyRegistry.registerParticleType("Simple Circle", SimpleParticleData::new);
/*  92 */   public static final RegistryObject<ParticleType<SimpleParticleData>> DOUBLE_CIRCLE = WyRegistry.registerParticleType("Double Circle", SimpleParticleData::new);
/*  93 */   public static final RegistryObject<ParticleType<SimpleParticleData>> HOLE = WyRegistry.registerParticleType("Hole", SimpleParticleData::new);
/*     */   
/*  95 */   public static final RegistryObject<ParticleType<SimpleParticleData>> COMMAND_MARK = WyRegistry.registerParticleType("Command Mark Particle", SimpleParticleData::new);
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   @SubscribeEvent
/*     */   public static void registerParticleFactories(ParticleFactoryRegisterEvent event) {
/* 100 */     ParticleManager manager = (Minecraft.func_71410_x()).field_71452_i;
/*     */     
/* 102 */     manager.func_199283_a((ParticleType)YUKI.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.YUKI));
/* 103 */     manager.func_199283_a((ParticleType)YUKI2.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.YUKI2));
/* 104 */     manager.func_199283_a((ParticleType)YUKI3.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.YUKI3));
/*     */     
/* 106 */     manager.func_199283_a((ParticleType)PIKA.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.PIKA));
/*     */     
/* 108 */     manager.func_199283_a((ParticleType)MERA.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.MERA, 8));
/* 109 */     manager.func_199283_a((ParticleType)MERA2.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.MERA2));
/*     */     
/* 111 */     manager.func_199283_a((ParticleType)MOKU.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.MOKU));
/* 112 */     manager.func_199283_a((ParticleType)MOKU2.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.MOKU2));
/*     */     
/* 114 */     manager.func_199283_a((ParticleType)SUNA.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.SUNA));
/* 115 */     manager.func_199283_a((ParticleType)SUNA2.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.SUNA2));
/*     */     
/* 117 */     manager.func_199283_a((ParticleType)GASU.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.GASU));
/* 118 */     manager.func_199283_a((ParticleType)GASU2.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.GASU2));
/*     */     
/* 120 */     manager.func_199283_a((ParticleType)BLUE_FLAME.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.BLUE_FLAME, 8));
/*     */     
/* 122 */     manager.func_199283_a((ParticleType)DARKNESS.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.DARKNESS, 8));
/* 123 */     manager.func_199283_a((ParticleType)DARKNESS_STATIC.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.DARKNESS_STATIC));
/* 124 */     manager.func_199283_a((ParticleType)KUROUZU.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.KUROUZU));
/*     */     
/* 126 */     manager.func_199283_a((ParticleType)GORO.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.GORO));
/* 127 */     manager.func_199283_a((ParticleType)GORO2.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.GORO2));
/* 128 */     manager.func_199283_a((ParticleType)GORO3.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.GORO3));
/*     */     
/* 130 */     manager.func_199283_a((ParticleType)MAGU.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.MAGU));
/*     */     
/* 132 */     manager.func_199283_a((ParticleType)DOKU.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.DOKU, 6));
/* 133 */     manager.func_199283_a((ParticleType)DOKU_PINK.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.DOKU_PINK));
/*     */     
/* 135 */     manager.func_199283_a((ParticleType)ITO.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.ITO));
/*     */     
/* 137 */     manager.func_199283_a((ParticleType)GURA.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.GURA, 4));
/* 138 */     manager.func_199283_a((ParticleType)GURA2.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.GURA2));
/*     */     
/* 140 */     manager.func_199283_a((ParticleType)HIE.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.HIE, 8));
/* 141 */     manager.func_199283_a((ParticleType)HIE_FLAKES.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.HIE_FLAKES));
/*     */     
/* 143 */     manager.func_199283_a((ParticleType)MERO.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.MERO));
/*     */     
/* 145 */     manager.func_199283_a((ParticleType)HORU.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.HORU));
/*     */     
/* 147 */     manager.func_199283_a((ParticleType)CHIYU.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.CHIYU));
/*     */     
/* 149 */     manager.func_199283_a((ParticleType)RUST.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.RUST));
/*     */     
/* 151 */     manager.func_199283_a((ParticleType)AWA.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.AWA));
/* 152 */     manager.func_199283_a((ParticleType)AWA2.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.AWA2));
/* 153 */     manager.func_199283_a((ParticleType)AWA3.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.AWA3));
/* 154 */     manager.func_199283_a((ParticleType)AWA_FOAM.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.AWA_FOAM));
/*     */     
/* 156 */     manager.func_199283_a((ParticleType)BETA.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.BETA));
/*     */     
/* 158 */     manager.func_199283_a((ParticleType)NETSU.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.NETSU));
/* 159 */     manager.func_199283_a((ParticleType)NETSU2.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.NETSU2));
/*     */     
/* 161 */     manager.func_199283_a((ParticleType)GRILL.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.GRILL));
/*     */     
/* 163 */     manager.func_199283_a((ParticleType)MEDIC.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.MEDIC));
/*     */     
/* 165 */     manager.func_199283_a((ParticleType)GORO_YELLOW.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.GORO_YELLOW));
/* 166 */     manager.func_199283_a((ParticleType)GORO2_YELLOW.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.GORO2_YELLOW));
/* 167 */     manager.func_199283_a((ParticleType)GORO3_YELLOW.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.GORO3_YELLOW));
/*     */     
/* 169 */     manager.func_199283_a((ParticleType)HANA.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.HANA));
/*     */     
/* 171 */     manager.func_199283_a((ParticleType)MAGNET.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.MAGNET));
/*     */     
/* 173 */     manager.func_199283_a((ParticleType)SIMPLE_CIRCLE.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.SIMPLE_CIRCLE));
/* 174 */     manager.func_199283_a((ParticleType)DOUBLE_CIRCLE.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.DOUBLE_CIRCLE));
/* 175 */     manager.func_199283_a((ParticleType)HOLE.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.HOLE));
/*     */     
/* 177 */     manager.func_199283_a((ParticleType)COMMAND_MARK.get(), (IParticleFactory)new SimpleParticle.Factory(ModResources.COMMAND_MARK));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void register(IEventBus eventBus) {
/* 183 */     WyRegistry.PARTICLE_TYPES.register(eventBus);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModParticleTypes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */