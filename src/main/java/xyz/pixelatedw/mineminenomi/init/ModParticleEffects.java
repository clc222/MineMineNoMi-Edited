/*     */ package xyz.pixelatedw.mineminenomi.init;
/*     */ 
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraftforge.eventbus.api.IEventBus;
/*     */ import net.minecraftforge.fml.RegistryObject;
/*     */ import xyz.pixelatedw.mineminenomi.config.ClientConfig;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.LogiaParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.SpecialFlyingParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.artofweather.ChargedWeatherBallParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.suna.SandBladeParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModParticleEffects
/*     */ {
/* 141 */   public static final RegistryObject<ParticleEffect<?>> KYOKAEN = WyRegistry.registerParticleEffect("Kyokaen", xyz.pixelatedw.mineminenomi.particles.effects.mera.KyokaenParticleEffect::new);
/* 142 */   public static final RegistryObject<ParticleEffect<?>> JUJIKA = WyRegistry.registerParticleEffect("Jujika", xyz.pixelatedw.mineminenomi.particles.effects.mera.JujikaParticleEffect::new);
/* 143 */   public static final RegistryObject<ParticleEffect<?>> HIBASHIRA = WyRegistry.registerParticleEffect("Hibashira", xyz.pixelatedw.mineminenomi.particles.effects.mera.HibashiraParticleEffect::new);
/* 144 */   public static final RegistryObject<ParticleEffect<?>> HEAT_DASH = WyRegistry.registerParticleEffect("Heat Dash", xyz.pixelatedw.mineminenomi.particles.effects.mera.HeatDashParticleEffect::new);
/* 145 */   public static final RegistryObject<ParticleEffect<?>> DAI_ENKAI_1 = WyRegistry.registerParticleEffect("Dai Enkai 1", xyz.pixelatedw.mineminenomi.particles.effects.mera.DaiEnkaiParticleEffect::new);
/* 146 */   public static final RegistryObject<ParticleEffect<?>> DAI_ENKAI_2 = WyRegistry.registerParticleEffect("Dai Enkai 2", xyz.pixelatedw.mineminenomi.particles.effects.mera.DaiEnkai2ParticleEffect::new);
/*     */ 
/*     */   
/* 149 */   public static final RegistryObject<ParticleEffect<?>> DESERT_SPADA = WyRegistry.registerParticleEffect("Desert Spada", xyz.pixelatedw.mineminenomi.particles.effects.suna.DesertSpadaParticleEffect::new);
/* 150 */   public static final RegistryObject<ParticleEffect<?>> DESERT_ENCIERRO = WyRegistry.registerParticleEffect("Desert Encierro", xyz.pixelatedw.mineminenomi.particles.effects.suna.DesertEncierroParticleEffect::new);
/* 151 */   public static final RegistryObject<ParticleEffect<?>> DESERT_GIRASOLE_1 = WyRegistry.registerParticleEffect("Desert Girasole 1", xyz.pixelatedw.mineminenomi.particles.effects.suna.DesertGirasoleParticleEffect::new);
/* 152 */   public static final RegistryObject<ParticleEffect<?>> DESERT_GIRASOLE_2 = WyRegistry.registerParticleEffect("Desert Girasole 2", xyz.pixelatedw.mineminenomi.particles.effects.suna.DesertGirasole2ParticleEffect::new);
/* 153 */   public static final RegistryObject<ParticleEffect<?>> GROUND_DEATH = WyRegistry.registerParticleEffect("Ground Death", xyz.pixelatedw.mineminenomi.particles.effects.suna.GroundDeathParticleEffect::new);
/* 154 */   public static final RegistryObject<ParticleEffect<?>> SABLES = WyRegistry.registerParticleEffect("Sables", xyz.pixelatedw.mineminenomi.particles.effects.suna.SablesParticleEffect::new);
/* 155 */   public static final RegistryObject<ParticleEffect<?>> SABLES_NEW = WyRegistry.registerParticleEffect("Sables New", xyz.pixelatedw.mineminenomi.particles.effects.suna.SablesNewParticleEffect::new);
/* 156 */   public static final RegistryObject<ParticleEffect<?>> SABLES_PESADO = WyRegistry.registerParticleEffect("Sables Pesado", xyz.pixelatedw.mineminenomi.particles.effects.suna.SablesPesadoChargingParticleEffect::new);
/* 157 */   public static final RegistryObject<ParticleEffect<?>> SAND_BLADE_IDLE = WyRegistry.registerParticleEffect("Sand Blade Idle", () -> new SandBladeParticleEffect(false));
/* 158 */   public static final RegistryObject<ParticleEffect<?>> SAND_BLADE_ACTIVE = WyRegistry.registerParticleEffect("Sand Blade Active", () -> new SandBladeParticleEffect(true));
/* 159 */   public static final RegistryObject<ParticleEffect<?>> BARJAN = WyRegistry.registerParticleEffect("Barjan", xyz.pixelatedw.mineminenomi.particles.effects.suna.BarjanParticleEffect::new);
/*     */ 
/*     */   
/* 162 */   public static final RegistryObject<ParticleEffect<?>> FUBUKI = WyRegistry.registerParticleEffect("Fubuki", xyz.pixelatedw.mineminenomi.particles.effects.yuki.FubukiParticleEffect::new);
/* 163 */   public static final RegistryObject<ParticleEffect<?>> KAMAKURA = WyRegistry.registerParticleEffect("Kamakura", xyz.pixelatedw.mineminenomi.particles.effects.yuki.KamakuraParticleEffect::new);
/*     */ 
/*     */   
/* 166 */   public static final RegistryObject<ParticleEffect<?>> ICE_AGE = WyRegistry.registerParticleEffect("Ice Age", xyz.pixelatedw.mineminenomi.particles.effects.hie.IceAgeParticleEffect::new);
/* 167 */   public static final RegistryObject<ParticleEffect<?>> ICE_BLOCK_AVALANCHE = WyRegistry.registerParticleEffect("Ice Block Avalanche", xyz.pixelatedw.mineminenomi.particles.effects.hie.IceBlockAvalancheParticleEffect::new);
/*     */ 
/*     */   
/* 170 */   public static final RegistryObject<ParticleEffect<?>> CHLORO_BALL_CLOUD = WyRegistry.registerParticleEffect("Chloro Ball Cloud", xyz.pixelatedw.mineminenomi.particles.effects.doku.ChloroBallCloudParticleEffect::new);
/* 171 */   public static final RegistryObject<ParticleEffect<?>> CHLORO_BALL = WyRegistry.registerParticleEffect("Chloro Ball", xyz.pixelatedw.mineminenomi.particles.effects.doku.ChloroBallParticleEffect::new);
/* 172 */   public static final RegistryObject<ParticleEffect<?>> DOKU_GUMO = WyRegistry.registerParticleEffect("Doku Gumo", xyz.pixelatedw.mineminenomi.particles.effects.doku.DokuGumoParticleEffect::new);
/* 173 */   public static final RegistryObject<ParticleEffect<?>> VENOM_DEMON = WyRegistry.registerParticleEffect("Venom Demon", xyz.pixelatedw.mineminenomi.particles.effects.doku.VenomDemonParticleEffect::new);
/*     */ 
/*     */   
/* 176 */   public static final RegistryObject<ParticleEffect<?>> WHITE_LAUNCHER = WyRegistry.registerParticleEffect("White Launcher", xyz.pixelatedw.mineminenomi.particles.effects.moku.WhiteLauncherParticleEffect::new);
/* 177 */   public static final RegistryObject<ParticleEffect<?>> WHITE_STRIKE = WyRegistry.registerParticleEffect("White Strike", xyz.pixelatedw.mineminenomi.particles.effects.moku.WhiteStrikeParticleEffect::new);
/* 178 */   public static final RegistryObject<ParticleEffect<?>> SMOKE_DEBUFF = WyRegistry.registerParticleEffect("Smoke Debuff", xyz.pixelatedw.mineminenomi.particles.effects.moku.SmokeDebuffParticleEffect::new);
/*     */ 
/*     */   
/* 181 */   public static final RegistryObject<ParticleEffect<?>> PIKA_CHARGING = WyRegistry.registerParticleEffect("Pika Charging", xyz.pixelatedw.mineminenomi.particles.effects.pika.ChargingPikaParticleEvent::new);
/* 182 */   public static final RegistryObject<ParticleEffect<?>> FLASH = WyRegistry.registerParticleEffect("Flash", xyz.pixelatedw.mineminenomi.particles.effects.pika.FlashParticleEffect::new);
/* 183 */   public static final RegistryObject<ParticleEffect<?>> YATA_NO_KAGAMI = WyRegistry.registerParticleEffect("Yata no Kagami", xyz.pixelatedw.mineminenomi.particles.effects.pika.YataNoKagamiParticleEffect::new);
/*     */ 
/*     */   
/* 186 */   public static final RegistryObject<ParticleEffect<?>> BLUE_BIRD = WyRegistry.registerParticleEffect("Blue Bird", xyz.pixelatedw.mineminenomi.particles.effects.toriphoenix.BlueBirdParticleEffect::new);
/* 187 */   public static final RegistryObject<ParticleEffect<?>> BLUE_FLAMES = WyRegistry.registerParticleEffect("Blue Flames", xyz.pixelatedw.mineminenomi.particles.effects.toriphoenix.BlueFlamesParticleEffect::new);
/* 188 */   public static final RegistryObject<ParticleEffect<?>> FUJIZAMI = WyRegistry.registerParticleEffect("Fujizami", xyz.pixelatedw.mineminenomi.particles.effects.toriphoenix.FujizamiParticleEffect::new);
/* 189 */   public static final RegistryObject<ParticleEffect<?>> TENSEI_NO_SOEN_1 = WyRegistry.registerParticleEffect("Tensei no Soen 1", xyz.pixelatedw.mineminenomi.particles.effects.toriphoenix.TenseiNoSoenParticleEffect::new);
/* 190 */   public static final RegistryObject<ParticleEffect<?>> TENSEI_NO_SOEN_2 = WyRegistry.registerParticleEffect("Tensei no Soen 2", xyz.pixelatedw.mineminenomi.particles.effects.toriphoenix.TenseiNoSoen2ParticleEffect::new);
/* 191 */   public static final RegistryObject<ParticleEffect<?>> FLAMES_OF_REGEN = WyRegistry.registerParticleEffect("Flames of Regen", () -> new LogiaParticleEffect((Supplier)ModParticleTypes.BLUE_FLAME));
/* 192 */   public static final RegistryObject<ParticleEffect<?>> PHOENIX_GOEN = WyRegistry.registerParticleEffect("Phoenix Goen", xyz.pixelatedw.mineminenomi.particles.effects.toriphoenix.PhoenixGoenParticleEffect::new);
/*     */ 
/*     */   
/* 195 */   public static final RegistryObject<ParticleEffect<?>> GRAVI_ZONE = WyRegistry.registerParticleEffect("Gravi Zone", xyz.pixelatedw.mineminenomi.particles.effects.zushi.GraviZoneParticleEffect::new);
/* 196 */   public static final RegistryObject<ParticleEffect<?>> GRAVI_PULL_1 = WyRegistry.registerParticleEffect("Gravi Pull 1", xyz.pixelatedw.mineminenomi.particles.effects.zushi.GraviPull1ParticleEffect::new);
/* 197 */   public static final RegistryObject<ParticleEffect<?>> GRAVI_PULL_2 = WyRegistry.registerParticleEffect("Gravi Pull 2", xyz.pixelatedw.mineminenomi.particles.effects.zushi.GraviPull2ParticleEffect::new);
/*     */ 
/*     */   
/* 200 */   public static final RegistryObject<ParticleEffect<?>> EL_THOR = WyRegistry.registerParticleEffect("El Thor", xyz.pixelatedw.mineminenomi.particles.effects.goro.ElThorParticleEffect::new);
/* 201 */   public static final RegistryObject<ParticleEffect<?>> EL_THOR_AIM = WyRegistry.registerParticleEffect("El Thor Aim", xyz.pixelatedw.mineminenomi.particles.effects.goro.ElThorAimParticleEffect::new);
/* 202 */   public static final RegistryObject<ParticleEffect<?>> GENERIC_LIGHTNING_USE = WyRegistry.registerParticleEffect("Generic Lighting Use", xyz.pixelatedw.mineminenomi.particles.effects.goro.GenericUseLightningEffect::new);
/* 203 */   public static final RegistryObject<ParticleEffect<?>> GENERIC_YELLOW_LIGHTNING_USE = WyRegistry.registerParticleEffect("Generic Yellow Lighting Use", xyz.pixelatedw.mineminenomi.particles.effects.goro.GenericUseYellowLightningEffect::new);
/* 204 */   public static final RegistryObject<ParticleEffect<?>> KARI = WyRegistry.registerParticleEffect("Kari", xyz.pixelatedw.mineminenomi.particles.effects.goro.KariParticleEffect::new);
/* 205 */   public static final RegistryObject<ParticleEffect<?>> LIGHTING_EXPLOSION = WyRegistry.registerParticleEffect("Lighting Explosion", xyz.pixelatedw.mineminenomi.particles.effects.goro.LightningExplosionParticleEffect::new);
/* 206 */   public static final RegistryObject<ParticleEffect<?>> RAIGO = WyRegistry.registerParticleEffect("Raigo", xyz.pixelatedw.mineminenomi.particles.effects.goro.RaigoParticleEffect::new);
/* 207 */   public static final RegistryObject<ParticleEffect<?>> SANGO = WyRegistry.registerParticleEffect("Sango", xyz.pixelatedw.mineminenomi.particles.effects.goro.SangoParticleEffect::new);
/*     */ 
/*     */   
/* 210 */   public static final RegistryObject<ParticleEffect<?>> HORU_WINK = WyRegistry.registerParticleEffect("Horu Wink", xyz.pixelatedw.mineminenomi.particles.effects.horu.WinkParticleEffect::new);
/*     */ 
/*     */   
/* 213 */   public static final RegistryObject<ParticleEffect<?>> KUMO_NO_SUGAKI = WyRegistry.registerParticleEffect("Kumo no Sugaki", xyz.pixelatedw.mineminenomi.particles.effects.ito.KumoNoSugakiParticleEffect::new);
/*     */ 
/*     */   
/* 216 */   public static final RegistryObject<ParticleEffect<?>> IMPACT_WAVE = WyRegistry.registerParticleEffect("Impact Wave", xyz.pixelatedw.mineminenomi.particles.effects.hitodaibutsu.ImpactWaveParticleEffect::new);
/*     */ 
/*     */   
/* 219 */   public static final RegistryObject<ParticleEffect<?>> NETSU_ENCHANTMENT = WyRegistry.registerParticleEffect("Netsu Enchantment", xyz.pixelatedw.mineminenomi.particles.effects.netsu.NetsuEnhancementParticleEffect::new);
/*     */ 
/*     */   
/* 222 */   public static final RegistryObject<ParticleEffect<?>> COUNTER_SHOCK = WyRegistry.registerParticleEffect("Counter Shock", xyz.pixelatedw.mineminenomi.particles.effects.ope.CounterShockParticleEffect::new);
/*     */ 
/*     */   
/* 225 */   public static final RegistryObject<ParticleEffect<?>> CANDLE_LOCK = WyRegistry.registerParticleEffect("Candle Lock", xyz.pixelatedw.mineminenomi.particles.effects.doru.CandleLockParticleEffect::new);
/* 226 */   public static final RegistryObject<ParticleEffect<ParticleEffect.NoDetails>> TOKUDAI_CANDLE = WyRegistry.registerParticleEffect("Tokudai Candle", xyz.pixelatedw.mineminenomi.particles.effects.doru.TokudaiCandleParticleEffect::new);
/*     */ 
/*     */   
/* 229 */   public static final RegistryObject<ParticleEffect<?>> CANDY_WAVE = WyRegistry.registerParticleEffect("Candy Wave", xyz.pixelatedw.mineminenomi.particles.effects.pero.CandyWaveParticleEffect::new);
/*     */ 
/*     */   
/* 232 */   public static final RegistryObject<ParticleEffect<?>> RUST_TOUCH = WyRegistry.registerParticleEffect("Rust Touch", xyz.pixelatedw.mineminenomi.particles.effects.sabi.RustTouchParticleEffect::new);
/* 233 */   public static final RegistryObject<ParticleEffect<?>> RUST_SKIN = WyRegistry.registerParticleEffect("Rust Skin", () -> new LogiaParticleEffect((Supplier)ModParticleTypes.RUST));
/* 234 */   public static final RegistryObject<ParticleEffect<?>> RUST_BREAK = WyRegistry.registerParticleEffect("Rust Break", xyz.pixelatedw.mineminenomi.particles.effects.sabi.RustBreakParticleEffect::new);
/*     */ 
/*     */   
/* 237 */   public static final RegistryObject<ParticleEffect<?>> BLACK_HOLE = WyRegistry.registerParticleEffect("Black Hole", xyz.pixelatedw.mineminenomi.particles.effects.yami.BlackHoleParticleEffect::new);
/* 238 */   public static final RegistryObject<ParticleEffect<?>> BLACK_WORLD = WyRegistry.registerParticleEffect("Black World", xyz.pixelatedw.mineminenomi.particles.effects.yami.BlackWorldParticleEffect::new);
/* 239 */   public static final RegistryObject<ParticleEffect<?>> DARK_MATTER = WyRegistry.registerParticleEffect("Dark Matter", xyz.pixelatedw.mineminenomi.particles.effects.yami.DarkMatterParticleEffect::new);
/* 240 */   public static final RegistryObject<ParticleEffect<?>> DARK_MATTER_CHARGING = WyRegistry.registerParticleEffect("Dark Matter Chargingg", xyz.pixelatedw.mineminenomi.particles.effects.yami.DarkMatterChargingParticleEffect::new);
/* 241 */   public static final RegistryObject<ParticleEffect<?>> KUROUZU = WyRegistry.registerParticleEffect("Kurouzu", xyz.pixelatedw.mineminenomi.particles.effects.yami.KurouzuParticleEffect::new);
/* 242 */   public static final RegistryObject<ParticleEffect<?>> KUROUZU_CHARGING = WyRegistry.registerParticleEffect("Kurouzu Charging", xyz.pixelatedw.mineminenomi.particles.effects.yami.KurouzuChargeParticleEffect::new);
/*     */ 
/*     */   
/* 245 */   public static final RegistryObject<ParticleEffect<?>> COOL_BALL_CHARGE = WyRegistry.registerParticleEffect("Cool Ball Charge", () -> new ChargedWeatherBallParticleEffect(WyHelper.hexToRGB("#0055FF"), (Supplier)ModParticleTypes.HIE));
/* 246 */   public static final RegistryObject<ParticleEffect<?>> HEAT_BALL_CHARGE = WyRegistry.registerParticleEffect("Heat Ball Charge", () -> new ChargedWeatherBallParticleEffect(WyHelper.hexToRGB("#FF0000"), (Supplier)ModParticleTypes.GASU));
/* 247 */   public static final RegistryObject<ParticleEffect<?>> THUNDER_BALL_CHARGE = WyRegistry.registerParticleEffect("Thunder Ball Charge", () -> new ChargedWeatherBallParticleEffect(WyHelper.hexToRGB("#FFFFFF"), (Supplier)ModParticleTypes.GORO_YELLOW));
/* 248 */   public static final RegistryObject<ParticleEffect<?>> THUNDER_LANCE = WyRegistry.registerParticleEffect("Thunder Lance", xyz.pixelatedw.mineminenomi.particles.effects.artofweather.ThunderLanceParticleEffect::new);
/* 249 */   public static final RegistryObject<ParticleEffect<?>> FAILED_TEMPO = WyRegistry.registerParticleEffect("Failed Tempo", xyz.pixelatedw.mineminenomi.particles.effects.artofweather.FailedTempoParticleEffect::new);
/* 250 */   public static final RegistryObject<ParticleEffect<?>> WEATHER_CLOUD = WyRegistry.registerParticleEffect("Weather Cloud", xyz.pixelatedw.mineminenomi.particles.effects.artofweather.WeatherCloudParticleEffect::new);
/* 251 */   public static final RegistryObject<ParticleEffect<?>> CHARGED_WEATHER_CLOUD = WyRegistry.registerParticleEffect("Charged Weather Cloud", xyz.pixelatedw.mineminenomi.particles.effects.artofweather.WeatherCloudChargedParticleEffect::new);
/* 252 */   public static final RegistryObject<ParticleEffect<?>> GUST_SWORD = WyRegistry.registerParticleEffect("Gust Sword", xyz.pixelatedw.mineminenomi.particles.effects.artofweather.GustSwordParticleEffect::new);
/*     */ 
/*     */   
/* 255 */   public static final RegistryObject<ParticleEffect<?>> KEMURI_BOSHI = WyRegistry.registerParticleEffect("Kemuri Boshi", xyz.pixelatedw.mineminenomi.particles.effects.sniper.KemuriBoshiParticleEffect::new);
/*     */ 
/*     */   
/* 258 */   public static final RegistryObject<ParticleEffect<?>> HIRYU_KAEN = WyRegistry.registerParticleEffect("Hiryu Kaen", xyz.pixelatedw.mineminenomi.particles.effects.swordsman.HiryuKaenParticleEffect::new);
/* 259 */   public static final RegistryObject<ParticleEffect<?>> O_TATSUMAKI = WyRegistry.registerParticleEffect("O Tatsumaki", xyz.pixelatedw.mineminenomi.particles.effects.swordsman.OTatsumakiParticleEffect::new);
/*     */ 
/*     */   
/* 262 */   public static final RegistryObject<ParticleEffect<?>> DIABLE_JAMBE = WyRegistry.registerParticleEffect("Diable Jambe", xyz.pixelatedw.mineminenomi.particles.effects.blackleg.DiableJambeParticleEffect::new);
/* 263 */   public static final RegistryObject<ParticleEffect<?>> BIEN_CUIT_GRILL_SHOT = WyRegistry.registerParticleEffect("Bien Cuit Grill Shot", xyz.pixelatedw.mineminenomi.particles.effects.blackleg.BienCuitGrillShotParticleEffect::new);
/* 264 */   public static final RegistryObject<ParticleEffect<?>> PARTY_TABLE_KICK = WyRegistry.registerParticleEffect("Party Table Kick", xyz.pixelatedw.mineminenomi.particles.effects.blackleg.PartyTableKickCourseParticleEffect::new);
/* 265 */   public static final RegistryObject<ParticleEffect<?>> CONCASSE_DIABLE = WyRegistry.registerParticleEffect("Concasse Diable", xyz.pixelatedw.mineminenomi.particles.effects.blackleg.ConcasseDiableParticleEffect::new);
/* 266 */   public static final RegistryObject<ParticleEffect<?>> EXTRA_HACHI_DIABLE = WyRegistry.registerParticleEffect("Extra Hachi Diable", xyz.pixelatedw.mineminenomi.particles.effects.blackleg.ExtraHachiDiableParticleEffect::new);
/*     */ 
/*     */   
/* 269 */   public static final RegistryObject<ParticleEffect<?>> KING_PUNCH_CHARGING = WyRegistry.registerParticleEffect("King Punch Charging", xyz.pixelatedw.mineminenomi.particles.effects.brawler.KingPunchChargeParticleEffect::new);
/*     */ 
/*     */   
/* 272 */   public static final RegistryObject<ParticleEffect<?>> FIRST_AID = WyRegistry.registerParticleEffect("First Aid", xyz.pixelatedw.mineminenomi.particles.effects.doctor.FirstAidParticleEffect::new);
/* 273 */   public static final RegistryObject<ParticleEffect<?>> MEDIC_BAG_EXPLOSION = WyRegistry.registerParticleEffect("Medic Bag Explosion", xyz.pixelatedw.mineminenomi.particles.effects.doctor.MedicBagExplosionParticleEffect::new);
/*     */ 
/*     */   
/* 276 */   public static final RegistryObject<ParticleEffect<?>> GEPPO = WyRegistry.registerParticleEffect("Geppo", xyz.pixelatedw.mineminenomi.particles.effects.rokushiki.GeppoParticleEffect::new);
/*     */ 
/*     */   
/* 279 */   public static final RegistryObject<ParticleEffect<?>> SAMEHADA = WyRegistry.registerParticleEffect("Samehada", xyz.pixelatedw.mineminenomi.particles.effects.fishkarate.SamehadaParticleEffect::new);
/* 280 */   public static final RegistryObject<ParticleEffect<?>> KARAKUSAGAWARA_SEIKEN_CHARGING = WyRegistry.registerParticleEffect("Karakusagawara Seiken Charge", xyz.pixelatedw.mineminenomi.particles.effects.fishkarate.KarakusagawaraSeikenChargeParticleEffect::new);
/* 281 */   public static final RegistryObject<ParticleEffect<?>> MIZU_TAIHO = WyRegistry.registerParticleEffect("Mizu Taiho", xyz.pixelatedw.mineminenomi.particles.effects.fishkarate.MizuTaihoParticleEffect::new);
/* 282 */   public static final RegistryObject<ParticleEffect<?>> MIZU_OSU = WyRegistry.registerParticleEffect("Mizu Osu", xyz.pixelatedw.mineminenomi.particles.effects.fishkarate.MizuOsuParticleEffect::new);
/*     */ 
/*     */   
/* 285 */   public static final RegistryObject<ParticleEffect<?>> COUP_DE_BOO = WyRegistry.registerParticleEffect("Coup de Boo", xyz.pixelatedw.mineminenomi.particles.effects.cyborg.CoupDeBooParticleEffect::new);
/* 286 */   public static final RegistryObject<ParticleEffect<?>> FRESH_FIRE = WyRegistry.registerParticleEffect("Fresh Fire", xyz.pixelatedw.mineminenomi.particles.effects.cyborg.FreshFireParticleEffect::new);
/* 287 */   public static final RegistryObject<ParticleEffect<?>> CHARGE_RADICAL_BEAM = WyRegistry.registerParticleEffect("Charge Radical Beam", xyz.pixelatedw.mineminenomi.particles.effects.cyborg.ChargeRadicalBeamParticleEffect::new);
/*     */ 
/*     */   
/* 290 */   public static final RegistryObject<ParticleEffect<?>> ELECTRICAL_LUNA = WyRegistry.registerParticleEffect("Electrical Luna", xyz.pixelatedw.mineminenomi.particles.effects.electro.ElectricalLunaParticleEffect::new);
/* 291 */   public static final RegistryObject<ParticleEffect<?>> ELECTRICAL_TEMPESTA_2 = WyRegistry.registerParticleEffect("Electrical Tempesta 2", xyz.pixelatedw.mineminenomi.particles.effects.electro.ElectricalTempesta2ParticleEffect::new);
/* 292 */   public static final RegistryObject<ParticleEffect<?>> ELECTRO_CHARGING = WyRegistry.registerParticleEffect("Electro Charging", xyz.pixelatedw.mineminenomi.particles.effects.electro.ElectroChargingParticleEffect::new);
/*     */ 
/*     */   
/* 295 */   public static final RegistryObject<ParticleEffect<?>> GASTANET = WyRegistry.registerParticleEffect("Gastanet", xyz.pixelatedw.mineminenomi.particles.effects.gasu.GastanetParticleEffect::new);
/* 296 */   public static final RegistryObject<ParticleEffect<?>> KORO = WyRegistry.registerParticleEffect("Koro", xyz.pixelatedw.mineminenomi.particles.effects.gasu.KoroParticleEffect::new);
/* 297 */   public static final RegistryObject<ParticleEffect<?>> SHINOKUNI = WyRegistry.registerParticleEffect("Shinokuni", xyz.pixelatedw.mineminenomi.particles.effects.gasu.ShinokuniParticleEffect::new);
/* 298 */   public static final RegistryObject<ParticleEffect<?>> BIG_GASTILLE = WyRegistry.registerParticleEffect("Big Gastille", xyz.pixelatedw.mineminenomi.particles.effects.gasu.BigGastilleParticleEffect::new);
/* 299 */   public static final RegistryObject<ParticleEffect<?>> GASTILLE = WyRegistry.registerParticleEffect("Gastille", xyz.pixelatedw.mineminenomi.particles.effects.gasu.GastilleParticleEffect::new);
/* 300 */   public static final RegistryObject<ParticleEffect<?>> GAS_ROBE = WyRegistry.registerParticleEffect("Gas Robe", xyz.pixelatedw.mineminenomi.particles.effects.gasu.GasRobeParticleEffect::new);
/*     */ 
/*     */   
/* 303 */   public static final RegistryObject<ParticleEffect<?>> GOLDEN_HOUR = WyRegistry.registerParticleEffect("Golden Hour", xyz.pixelatedw.mineminenomi.particles.effects.awa.GoldenHourParticleEffect::new);
/* 304 */   public static final RegistryObject<ParticleEffect<?>> RELAX_HOUR = WyRegistry.registerParticleEffect("Relax Hour", xyz.pixelatedw.mineminenomi.particles.effects.awa.RelaxHourParticleEffect::new);
/* 305 */   public static final RegistryObject<ParticleEffect<?>> WASHED = WyRegistry.registerParticleEffect("Washed", xyz.pixelatedw.mineminenomi.particles.effects.awa.WashedParticleEffect::new);
/*     */ 
/*     */   
/* 308 */   public static final RegistryObject<ParticleEffect<?>> BLOOM = WyRegistry.registerParticleEffect("Bloom", xyz.pixelatedw.mineminenomi.particles.effects.hana.BloomParticleEffect::new);
/*     */ 
/*     */   
/* 311 */   public static final RegistryObject<ParticleEffect<?>> ATTRACT = WyRegistry.registerParticleEffect("Attract", xyz.pixelatedw.mineminenomi.particles.effects.jiki.AttractParticleEffect::new);
/*     */ 
/*     */   
/* 314 */   public static final RegistryObject<ParticleEffect<?>> GEAR_SECOND = WyRegistry.registerParticleEffect("Gear Second", xyz.pixelatedw.mineminenomi.particles.effects.gomu.GearSecondParticleEffect::new);
/*     */ 
/*     */   
/* 317 */   public static final RegistryObject<ParticleEffect<?>> EVAPORATE = WyRegistry.registerParticleEffect("Evaporate", xyz.pixelatedw.mineminenomi.particles.effects.kachi.EvaporateParticleEffect::new);
/*     */ 
/*     */   
/* 320 */   public static final RegistryObject<ParticleEffect<?>> SHOUREI = WyRegistry.registerParticleEffect("Shourei", xyz.pixelatedw.mineminenomi.particles.effects.kobu.ShoureiParticleEffect::new);
/*     */ 
/*     */   
/* 323 */   public static final RegistryObject<ParticleEffect<?>> CHIYUPOPO = WyRegistry.registerParticleEffect("Chiyupopo", xyz.pixelatedw.mineminenomi.particles.effects.chiyu.ChiyupopoParticleEffect::new);
/* 324 */   public static final RegistryObject<ParticleEffect<?>> HEALING_TOUCH = WyRegistry.registerParticleEffect("Healing Touch", xyz.pixelatedw.mineminenomi.particles.effects.chiyu.HealingTouchParticleEffect::new);
/*     */ 
/*     */   
/* 327 */   public static final RegistryObject<ParticleEffect<?>> BETA_COATING = WyRegistry.registerParticleEffect("Beta Coating", () -> new LogiaParticleEffect((Supplier)ModParticleTypes.BETA));
/* 328 */   public static final RegistryObject<ParticleEffect<?>> HANAMIZU_SHINKEN_SHIRADORI = WyRegistry.registerParticleEffect("Hanamizu Shinken Shirahadori", xyz.pixelatedw.mineminenomi.particles.effects.beta.HanamizuShinkenShirahadoriParticleEffect::new);
/*     */ 
/*     */   
/* 331 */   public static final RegistryObject<ParticleEffect<?>> KOKUTEI_CROSS = WyRegistry.registerParticleEffect("Kokutei Cross", xyz.pixelatedw.mineminenomi.particles.effects.ushibison.KokuteiCrossParticleEffect::new);
/*     */ 
/*     */   
/* 334 */   public static final RegistryObject<ParticleEffect<?>> GREAT_STOMP = WyRegistry.registerParticleEffect("Great Stomp", xyz.pixelatedw.mineminenomi.particles.effects.zou.GreatStompParticleEffect::new);
/*     */ 
/*     */   
/* 337 */   public static final RegistryObject<ParticleEffect<?>> SOUL_PARADE = WyRegistry.registerParticleEffect("Soul Parade", xyz.pixelatedw.mineminenomi.particles.effects.yomi.SoulParadeParticleEffect::new);
/* 338 */   public static final RegistryObject<ParticleEffect<?>> KASURIUTA_FUBUKI_GIRI = WyRegistry.registerParticleEffect("Kasuriuta Fubuki Giri", xyz.pixelatedw.mineminenomi.particles.effects.yomi.KasuriutaFubukiGiriParticleEffect::new);
/*     */ 
/*     */   
/* 341 */   public static final RegistryObject<ParticleEffect<?>> SLAVE_ARROW = WyRegistry.registerParticleEffect("Slave Arrow", xyz.pixelatedw.mineminenomi.particles.effects.mero.SlaveArrowParticleEffect::new);
/* 342 */   public static final RegistryObject<ParticleEffect<?>> PERFUME_FEMUR = WyRegistry.registerParticleEffect("Perfume Femur", xyz.pixelatedw.mineminenomi.particles.effects.mero.PerfumeFemurParticleEffect::new);
/*     */ 
/*     */   
/* 345 */   public static final RegistryObject<ParticleEffect<?>> CHARGE_KAGE = WyRegistry.registerParticleEffect("Charge Kage", xyz.pixelatedw.mineminenomi.particles.effects.kage.ChargeableKageParticleEffect::new);
/*     */ 
/*     */   
/* 348 */   public static final RegistryObject<ParticleEffect<?>> HAOSHOKU_HAKI = WyRegistry.registerParticleEffect("Haoshoku Haki", xyz.pixelatedw.mineminenomi.particles.effects.haki.HaoshokuHakiParticleEffect::new);
/* 349 */   public static final RegistryObject<ParticleEffect<?>> EMISSION_BURST = WyRegistry.registerParticleEffect("Emission Burst", xyz.pixelatedw.mineminenomi.particles.effects.haki.EmissionBurstParticleEffect::new);
/* 350 */   public static final RegistryObject<ParticleEffect<?>> INTERNAL_DESTRUCTION_BURST = WyRegistry.registerParticleEffect("Internal Destruction Burst", xyz.pixelatedw.mineminenomi.particles.effects.haki.InternalDestructionBurstParticleEffect::new);
/*     */ 
/*     */   
/* 353 */   public static final RegistryObject<ParticleEffect<?>> MERA_LOGIA = WyRegistry.registerParticleEffect("Mera Logia", () -> new LogiaParticleEffect((Supplier)ModParticleTypes.MERA));
/* 354 */   public static final RegistryObject<ParticleEffect<?>> GASU_LOGIA = WyRegistry.registerParticleEffect("Gasu Logia", () -> new LogiaParticleEffect((Supplier)ModParticleTypes.GASU));
/* 355 */   public static final RegistryObject<ParticleEffect<?>> GORO_LOGIA = WyRegistry.registerParticleEffect("Goro Logia", () -> new LogiaParticleEffect(ClientConfig.INSTANCE.isGoroBlue() ? (Supplier)ModParticleTypes.GORO : (Supplier)ModParticleTypes.GORO_YELLOW));
/* 356 */   public static final RegistryObject<ParticleEffect<?>> HIE_LOGIA = WyRegistry.registerParticleEffect("Hie Logia", () -> new LogiaParticleEffect((Supplier)ModParticleTypes.HIE));
/* 357 */   public static final RegistryObject<ParticleEffect<?>> MAGU_LOGIA = WyRegistry.registerParticleEffect("Magu Logia", () -> new LogiaParticleEffect((Supplier)ModParticleTypes.MAGU));
/* 358 */   public static final RegistryObject<ParticleEffect<?>> MOKU_LOGIA = WyRegistry.registerParticleEffect("Moku Logia", () -> new LogiaParticleEffect((Supplier)ModParticleTypes.MOKU2));
/* 359 */   public static final RegistryObject<ParticleEffect<?>> NUMA_LOGIA = WyRegistry.registerParticleEffect("Numa Logia", () -> new LogiaParticleEffect((Supplier)ModParticleTypes.SUNA2));
/* 360 */   public static final RegistryObject<ParticleEffect<?>> PIKA_LOGIA = WyRegistry.registerParticleEffect("Pika Logia", () -> new LogiaParticleEffect((Supplier)ModParticleTypes.PIKA));
/* 361 */   public static final RegistryObject<ParticleEffect<?>> SUNA_LOGIA = WyRegistry.registerParticleEffect("Suna Logia", () -> new LogiaParticleEffect((Supplier)ModParticleTypes.SUNA2));
/* 362 */   public static final RegistryObject<ParticleEffect<?>> YUKI_LOGIA = WyRegistry.registerParticleEffect("Yuki Logia", () -> new LogiaParticleEffect((Supplier)ModParticleTypes.YUKI));
/*     */ 
/*     */   
/* 365 */   public static final RegistryObject<ParticleEffect<?>> GASU_FLY = WyRegistry.registerParticleEffect("Gasu Fly", () -> new SpecialFlyingParticleEffect((Supplier)ModParticleTypes.GASU));
/* 366 */   public static final RegistryObject<ParticleEffect<?>> SUNA_FLY = WyRegistry.registerParticleEffect("Suna Fly", () -> new SpecialFlyingParticleEffect((Supplier)ModParticleTypes.SUNA2));
/* 367 */   public static final RegistryObject<ParticleEffect<?>> MOKU_FLY = WyRegistry.registerParticleEffect("Moku Fly", () -> new SpecialFlyingParticleEffect((Supplier)ModParticleTypes.MOKU));
/*     */ 
/*     */   
/* 370 */   public static final RegistryObject<ParticleEffect<?>> BREAKING_BLOCKS = WyRegistry.registerParticleEffect("Breaking Blocks", xyz.pixelatedw.mineminenomi.particles.effects.BreakingBlocksParticleEffect::new);
/* 371 */   public static final RegistryObject<ParticleEffect<?>> SHOCKWAVE = WyRegistry.registerParticleEffect("Shockwave", xyz.pixelatedw.mineminenomi.particles.effects.ShockwaveParticleEffect::new);
/* 372 */   public static final RegistryObject<ParticleEffect<?>> BERSERKER_POWERUP = WyRegistry.registerParticleEffect("Berserker Powerup", xyz.pixelatedw.mineminenomi.particles.effects.BerserkerPowerupParticleEffect::new);
/* 373 */   public static final RegistryObject<ParticleEffect<?>> HAKI_GUARD = WyRegistry.registerParticleEffect("Haki Guard", xyz.pixelatedw.mineminenomi.particles.effects.HakiGuardParticleEffect::new);
/* 374 */   public static final RegistryObject<ParticleEffect<?>> BON_CHARI_POPPING = WyRegistry.registerParticleEffect("Bon Chari Popping", xyz.pixelatedw.mineminenomi.particles.effects.BonChariPoppingParticleEffect::new);
/* 375 */   public static final RegistryObject<ParticleEffect<?>> COMMAND_MARK = WyRegistry.registerParticleEffect("Command Mark", xyz.pixelatedw.mineminenomi.particles.effects.CommandMarkParticleEffect::new);
/* 376 */   public static final RegistryObject<ParticleEffect<?>> MH5_GAS = WyRegistry.registerParticleEffect("MH5 Gas", xyz.pixelatedw.mineminenomi.particles.effects.extra.MH5GasParticleEffect::new);
/* 377 */   public static final RegistryObject<ParticleEffect<?>> CHARGE_MH5_BOMB = WyRegistry.registerParticleEffect("Charge MH5 Bomb", xyz.pixelatedw.mineminenomi.particles.effects.ChargeMH5ParticleEffect::new);
/*     */   
/*     */   public static void register(IEventBus eventBus) {
/* 380 */     WyRegistry.PARTICLE_EFFECTS.register(eventBus);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModParticleEffects.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */