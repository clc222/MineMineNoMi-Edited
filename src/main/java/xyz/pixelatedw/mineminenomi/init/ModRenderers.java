/*     */ package xyz.pixelatedw.mineminenomi.init;
/*     */ import java.util.Iterator;
/*     */ import java.util.function.Function;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.RenderType;
/*     */ import net.minecraft.client.renderer.RenderTypeLookup;
/*     */ import net.minecraft.client.renderer.entity.EntityRenderer;
/*     */ import net.minecraft.client.renderer.entity.EntityRendererManager;
/*     */ import net.minecraft.client.renderer.entity.ItemRenderer;
/*     */ import net.minecraft.client.renderer.entity.model.BipedModel;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.item.IDyeableArmorItem;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.TileEntityType;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.fml.client.registry.ClientRegistry;
/*     */ import net.minecraftforge.fml.client.registry.IRenderFactory;
/*     */ import net.minecraftforge.fml.client.registry.RenderingRegistry;
/*     */ import xyz.pixelatedw.mineminenomi.api.IFruitColor;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.ExtraProjectiles;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.SniperTargetModel;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.animals.DenDenMushiModel;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.ArlongModel;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.ChewModel;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.DojoSenseiModel;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.DonKriegModel;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.HumanoidBruteModel;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.HumanoidFatModel;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.HumanoidModel;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.HumanoidSniperModel;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.KuroobiModel;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.MorganModel;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.Mr0Model;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.Mr1Model;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.PacifistaModel;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.SkypieanModel;
/*     */ import xyz.pixelatedw.mineminenomi.models.entities.mobs.humanoids.WeatherWizardModel;
/*     */ import xyz.pixelatedw.mineminenomi.models.morphs.NewPhoenixFlyModel;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.abilities.BottomHalfBodyRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.abilities.EmptyRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.abilities.ThrowingWeaponRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.abilities.TornadoRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.BananawaniRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.BigDuckRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.BombRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.BonChariRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.BuggyRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.CabajiRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.CannonRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.CelestialDragonRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.ChakramRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.CloudRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.DugongRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.DummyNewPhoenixRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.FlyingFishRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.GorillaRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.HumandrillRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.HumanoidRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.JangoRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.LapahnRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.NetRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.PacifistaRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.PandaSharkRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.SeaCowRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.SphereRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.SpikeRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.StrikerRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.ThrowingSpearRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.UnicycleRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.VivreCardRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.WantedPosterPackageRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.WhiteWalkieRenderer;
/*     */ import xyz.pixelatedw.mineminenomi.renderers.entities.YagaraBullRenderer;
/*     */ 
/*     */ public class ModRenderers {
/*     */   public static void registerRenderers() {
/*  80 */     Minecraft.func_71410_x().getItemColors().func_199877_a((itemStack, layer) -> (layer > 0) ? -1 : ((IDyeableArmorItem)itemStack.func_77973_b()).func_200886_f(itemStack), new IItemProvider[] { (IItemProvider)ModArmors.MARINE_CAPTAIN_CAPE
/*     */           
/*  82 */           .get(), (IItemProvider)ModArmors.PIRATE_CAPTAIN_CAPE.get(), (IItemProvider)ModItems.STRAW_DOLL.get(), (IItemProvider)ModArmors.BANDANA.get(), (IItemProvider)ModWeapons.DORU_DORU_ARTS_KEN.get(), (IItemProvider)ModWeapons.DORU_PICKAXE
/*  83 */           .get(), (IItemProvider)ModArmors.VICE_ADMIRAL_CHEST.get(), (IItemProvider)ModArmors.VICE_ADMIRAL_FEET.get(), (IItemProvider)ModArmors.VICE_ADMIRAL_LEGS.get(), (IItemProvider)ModArmors.TRICORNE.get(), (IItemProvider)ModArmors.FLUFFY_CAPE
/*  84 */           .get(), (IItemProvider)ModArmors.MIHAWK_HAT.get(), (IItemProvider)ModArmors.STRAW_HAT.get(), (IItemProvider)ModWeapons.AXE.get(), (IItemProvider)ModWeapons.SPEAR.get(), (IItemProvider)ModWeapons.KATANA.get(), (IItemProvider)ModWeapons.DAGGER.get(), (IItemProvider)ModWeapons.CUTLASS
/*  85 */           .get(), (IItemProvider)ModWeapons.BISENTO.get(), (IItemProvider)ModWeapons.BROADSWORD.get(), (IItemProvider)ModWeapons.JITTE.get(), (IItemProvider)ModArmors.DOFFY_GLASSES.get(), (IItemProvider)ModWeapons.CLEAVER.get(), (IItemProvider)ModWeapons.MACE.get(), (IItemProvider)ModArmors.PLUME_HAT
/*  86 */           .get(), (IItemProvider)ModArmors.BICORNE.get(), (IItemProvider)ModArmors.WIDE_BRIM_HAT.get() });
/*     */     
/*  88 */     for (Iterator<AkumaNoMiItem> iterator = ModValues.DEVIL_FRUITS.iterator(); iterator.hasNext(); ) { AkumaNoMiItem fruit = iterator.next();
/*  89 */       Minecraft.func_71410_x().getItemColors().func_199877_a((itemStack, layer) -> (layer > 0) ? ((IFruitColor)itemStack.func_77973_b()).getStemColor(itemStack) : ((IFruitColor)itemStack.func_77973_b()).getBaseColor(itemStack), new IItemProvider[] { (IItemProvider)fruit }); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  95 */     RenderTypeLookup.setRenderLayer((Block)ModBlocks.OPE.get(), RenderType.func_228645_f_());
/*  96 */     RenderTypeLookup.setRenderLayer((Block)ModBlocks.BARRIER.get(), RenderType.func_228645_f_());
/*  97 */     RenderTypeLookup.setRenderLayer((Block)ModBlocks.STRING_WALL.get(), RenderType.func_228643_e_());
/*  98 */     RenderTypeLookup.setRenderLayer((Block)ModBlocks.KAIROSEKI_BARS.get(), RenderType.func_228643_e_());
/*  99 */     RenderTypeLookup.setRenderLayer((Block)ModBlocks.CANDY.get(), RenderType.func_228645_f_());
/* 100 */     RenderTypeLookup.setRenderLayer((Block)ModBlocks.CUSTOM_SPAWNER.get(), RenderType.func_228643_e_());
/* 101 */     RenderTypeLookup.setRenderLayer((Block)ModBlocks.ORI_BARS.get(), RenderType.func_228643_e_());
/* 102 */     RenderTypeLookup.setRenderLayer((Block)ModBlocks.FLAG.get(), RenderType.func_228643_e_());
/* 103 */     RenderTypeLookup.setRenderLayer((Block)ModBlocks.MANGROVE_LEAVES.get(), RenderType.func_228643_e_());
/* 104 */     RenderTypeLookup.setRenderLayer((Block)ModBlocks.MANGROVE_SAPLING.get(), RenderType.func_228643_e_());
/* 105 */     RenderTypeLookup.setRenderLayer((Block)ModBlocks.TANGERINE_CROP.get(), RenderType.func_228643_e_());
/*     */ 
/*     */     
/* 108 */     ClientRegistry.bindTileEntityRenderer((TileEntityType)ModTileEntities.WANTED_POSTER_PACKAGE.get(), xyz.pixelatedw.mineminenomi.renderers.blocks.WantedPostersPackageTileEntityRenderer::new);
/* 109 */     ClientRegistry.bindTileEntityRenderer((TileEntityType)ModTileEntities.WANTED_POSTER.get(), xyz.pixelatedw.mineminenomi.renderers.blocks.WantedPosterTileEntityRenderer::new);
/* 110 */     ClientRegistry.bindTileEntityRenderer((TileEntityType)ModTileEntities.FLAG.get(), xyz.pixelatedw.mineminenomi.renderers.blocks.FlagTileEntityRenderer::new);
/*     */ 
/*     */ 
/*     */     
/* 114 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.MARINE_GRUNT.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F));
/* 115 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.MARINE_BRUTE.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidBruteModel(), 1.5F, 1.25F, 1.5F));
/* 116 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.MARINE_SNIPER.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidSniperModel(), 1.0F));
/* 117 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.MARINE_CAPTAIN.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F));
/* 118 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.MARINE_TRADER.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F));
/* 119 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.PACIFISTA.get(), (IRenderFactory)new PacifistaRenderer.Factory((BipedModel)new PacifistaModel(), 1.75F, "pacifista"));
/* 120 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.MARINE_VICE_ADMIRAL.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.1F));
/* 121 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.MORGAN.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new MorganModel(), 1.0F, "morgan"));
/*     */ 
/*     */     
/* 124 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.CELESTIAL_DRAGON.get(), (IRenderFactory)new CelestialDragonRenderer.Factory());
/*     */ 
/*     */     
/* 127 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.PIRATE_GRUNT.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F));
/* 128 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.PIRATE_BRUTE.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidBruteModel(), 1.5F, 1.25F, 1.5F));
/* 129 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.PIRATE_CAPTAIN.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F));
/* 130 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.PIRATE_TRADER.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F));
/* 131 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.PIRATE_BOMBER.get(), (IRenderFactory)new HumanoidBigBomberRenderer.Factory());
/* 132 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.PIRATE_NOTORIOUS_CAPTAIN.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.1F));
/*     */ 
/*     */     
/* 135 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.BUGGY.get(), (IRenderFactory)new BuggyRenderer.Factory());
/* 136 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.ALVIDA.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidFatModel(), 1.07F, 1.0F, 1.07F, "alvida"));
/* 137 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.ALVIDA_SLIM.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F, 1.0F, 1.0F, "alvida_slim"));
/* 138 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.CABAJI.get(), (IRenderFactory)new CabajiRenderer.Factory());
/*     */ 
/*     */     
/* 141 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.SHAM.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 0.9F, 1.0F, 0.9F, "sham"));
/* 142 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.BUCHI.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidFatModel(), 1.12F, 1.1F, 1.12F, "buchi"));
/* 143 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.JANGO.get(), (IRenderFactory)new JangoRenderer.Factory());
/* 144 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.KURO.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(64, 32), 1.0F, "kuro"));
/*     */ 
/*     */     
/* 147 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.GIN.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F, "gin"));
/* 148 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.PEARL.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.5F, 1.25F, 1.5F, "pearl"));
/* 149 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.DON_KRIEG.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new DonKriegModel(), 1.4F, "don_krieg"));
/*     */ 
/*     */     
/* 152 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.CHEW.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new ChewModel(), 1.4F, "chew"));
/* 153 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.KUROOBI.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new KuroobiModel(), 1.37F, "kuroobi"));
/* 154 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.ARLONG.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new ArlongModel(), 1.4F, "arlong"));
/*     */ 
/*     */     
/* 157 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.MR5.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F, "mr5"));
/* 158 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.MISS_VALENTINE.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F, "miss_valentine"));
/* 159 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.MR4.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidBruteModel(), 1.5F, 1.2F, 1.5F, "mr4"));
/* 160 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.MISS_MERRY_CHRISTMAS.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidBruteModel(), 0.7F, "miss_merry_christmas"));
/* 161 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.MR3.get(), xyz.pixelatedw.mineminenomi.renderers.entities.Mr3Renderer::new);
/* 162 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.MR1.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new Mr1Model(), 1.25F, 1.2F, 1.25F, "mr1"));
/* 163 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.MR0.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new Mr0Model(), 1.1F, "mr0"));
/*     */ 
/*     */     
/* 166 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.BANDIT_GRUNT.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F));
/* 167 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.BANDIT_BRUTE.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidBruteModel(), 1.5F, 1.25F, 1.5F));
/* 168 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.BANDIT_SNIPER.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidSniperModel(), 1.0F));
/* 169 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.BANDIT_CAPTAIN.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.1F, 1.1F, 1.1F));
/* 170 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.HIGUMA.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.1F, "higuma"));
/*     */ 
/*     */     
/* 173 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.SWORDSMAN_TRAINER.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new DojoSenseiModel(), 1.0F));
/* 174 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.SNIPER_TRAINER.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F));
/* 175 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.ART_OF_WEATHER_TRAINER.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new WeatherWizardModel(), 1.0F));
/* 176 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.DOCTOR_TRAINER.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F));
/* 177 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.BRAWLER_TRAINER.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F));
/* 178 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.BLACK_LEG_TRAINER.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F));
/* 179 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.SKYPIEAN_CIVILIAN.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new SkypieanModel(), 1.0F));
/* 180 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.SKYPIEAN_TRADER.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new SkypieanModel(), 1.0F));
/* 181 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.BARKEEPER.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F));
/*     */ 
/*     */     
/* 184 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.DEN_DEN_MUSHI.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new DenDenMushiModel(), 1.0F));
/* 185 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.LAPAHN.get(), (IRenderFactory)new LapahnRenderer.Factory());
/* 186 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.YAGARA_BULL.get(), (IRenderFactory)new YagaraBullRenderer.Factory());
/* 187 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.HUMANDRILL.get(), (IRenderFactory)new HumandrillRenderer.Factory());
/* 188 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.FIGHTING_FISH.get(), (IRenderFactory)new FightingFishRenderer.Factory());
/* 189 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.BANANAWANI.get(), (IRenderFactory)new BananawaniRenderer.Factory());
/* 190 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.BIG_DUCK.get(), (IRenderFactory)new BigDuckRenderer.Factory());
/* 191 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.SEA_COW.get(), (IRenderFactory)new SeaCowRenderer.Factory());
/* 192 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.WHITE_WALKIE.get(), (IRenderFactory)new WhiteWalkieRenderer.Factory());
/* 193 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.PANDA_SHARK.get(), (IRenderFactory)new PandaSharkRenderer.Factory());
/* 194 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.FLYING_FISH.get(), (IRenderFactory)new FlyingFishRenderer.Factory());
/*     */ 
/*     */     
/* 197 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.BLUGORI.get(), (IRenderFactory)new GorillaRenderer.Factory(GorillaRenderer.BLUGORI_TEXTURE));
/* 198 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.BLAGORI.get(), (IRenderFactory)new GorillaRenderer.Factory(GorillaRenderer.BLAGORI_TEXTURE));
/*     */ 
/*     */     
/* 201 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.KUNG_FU_DUGONG.get(), (IRenderFactory)new DugongRenderer.Factory(new ResourceLocation("mineminenomi", "textures/models/kung_fu_dugong.png")));
/* 202 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.WRESTLING_DUGONG.get(), (IRenderFactory)new DugongRenderer.Factory(new ResourceLocation("mineminenomi", "textures/models/wrestling_dugong.png")));
/* 203 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.BOXING_DUGONG.get(), (IRenderFactory)new DugongRenderer.Factory(new ResourceLocation("mineminenomi", "textures/models/boxing_dugong.png")));
/* 204 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.LEGENDARY_MASTER_DUGONG.get(), (IRenderFactory)new DugongRenderer.Factory(new ResourceLocation("mineminenomi", "textures/models/legendary_master_dugong.png")));
/* 205 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.WANDERING_DUGONG.get(), (IRenderFactory)new WanderingDugongRenderer.Factory());
/*     */ 
/*     */     
/* 208 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ExtraProjectiles.CLOUD.get(), (IRenderFactory)new CloudRenderer.Factory());
/* 209 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.WANTED_POSTER_PACKAGE.get(), (IRenderFactory)new WantedPosterPackageRenderer.Factory());
/* 210 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.VIVRE_CARD.get(), (IRenderFactory)new VivreCardRenderer.Factory());
/* 211 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.SNIPER_TARGET.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new SniperTargetModel(), 1.0F, "sniper_target"));
/* 212 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.PHYSICAL_BODY.get(), (IRenderFactory)new PhysicalBodyRenderer.Factory());
/* 213 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.BOMB.get(), (IRenderFactory)new BombRenderer.Factory());
/* 214 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.SPIKE.get(), (IRenderFactory)new SpikeRenderer.Factory());
/* 215 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.DEVIL_FRUIT_ITEM.get(), manager -> new ItemRenderer(manager, Minecraft.func_71410_x().func_175599_af()));
/* 216 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.BOTTOM_HALF_BODY.get(), (IRenderFactory)new BottomHalfBodyRenderer.Factory());
/* 217 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.ENTITY_COLLECTOR.get(), (IRenderFactory)new EmptyRenderer.Factory());
/* 218 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.TORNADO.get(), (IRenderFactory)new TornadoRenderer.Factory());
/* 219 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.ROPE_NET.get(), (IRenderFactory)new NetRenderer.Factory());
/* 220 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.KAIROSEKI_NET.get(), (IRenderFactory)new NetRenderer.Factory());
/* 221 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.SPHERE.get(), (IRenderFactory)new SphereRenderer.Factory());
/*     */ 
/*     */     
/* 224 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.BON_CHARI.get(), (IRenderFactory)new BonChariRenderer.Factory());
/* 225 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.STRIKER.get(), (IRenderFactory)new StrikerRenderer.Factory());
/* 226 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.CANNON.get(), (IRenderFactory)new CannonRenderer.Factory());
/* 227 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.UNICYCLE.get(), (IRenderFactory)new UnicycleRenderer.Factory());
/*     */ 
/*     */     
/* 230 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.THROWING_WEAPON.get(), (IRenderFactory)new ThrowingWeaponRenderer.Factory());
/* 231 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.THROWING_SPEAR.get(), (IRenderFactory)new ThrowingSpearRenderer.Factory());
/* 232 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.CHAKRAM.get(), (IRenderFactory)new ChakramRenderer.Factory());
/*     */ 
/*     */     
/* 235 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.DUMMY_NEW_PHOENIX.get(), (IRenderFactory)new DummyNewPhoenixRenderer.Factory(new NewPhoenixFlyModel(), new ResourceLocation("mineminenomi", "textures/models/zoanmorph/new_phoenix.png")));
/* 236 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.DUMMY_CABAJI.get(), (IRenderFactory)new CabajiRenderer.Factory());
/* 237 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.DUMMY_JANGO.get(), (IRenderFactory)new JangoRenderer.Factory());
/* 238 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.DUMMY_ALVIDA_SLIM.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F, 1.0F, 1.0F, "alvida"));
/* 239 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.DUMMY_NOTORIOUS.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.1F, "marine_captain1"));
/* 240 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.DUMMY_BARKEEPER.get(), (IRenderFactory)new HumanoidRenderer.Factory((BipedModel)new HumanoidModel(), 1.0F, "barkeeper"));
/* 241 */     RenderingRegistry.registerEntityRenderingHandler((EntityType)ModEntities.DUMMY_FLYING_FISH.get(), (IRenderFactory)new FlyingFishRenderer.Factory());
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModRenderers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */