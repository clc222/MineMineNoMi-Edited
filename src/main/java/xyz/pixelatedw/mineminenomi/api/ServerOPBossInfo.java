/*    */ package xyz.pixelatedw.mineminenomi.api;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import net.minecraft.util.text.Style;
/*    */ import net.minecraft.world.BossInfo;
/*    */ import net.minecraft.world.server.ServerBossInfo;
/*    */ import xyz.pixelatedw.mineminenomi.api.challenges.IChallengeBoss;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.ui.SUpdateOPBossInfoPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*    */ 
/*    */ public class ServerOPBossInfo extends ServerBossInfo {
/* 15 */   private int totalBars = -1;
/* 16 */   private int activeBars = -1;
/*    */   private final int type;
/*    */   
/*    */   public ServerOPBossInfo(ITextComponent name) {
/* 20 */     super(name, BossInfo.Color.RED, BossInfo.Overlay.NOTCHED_10);
/* 21 */     this.type = 2;
/*    */   }
/*    */   
/*    */   @Deprecated
/*    */   public ServerOPBossInfo(ITextComponent name, BossInfo.Color color, BossInfo.Overlay overlay) {
/* 26 */     this(name);
/*    */   }
/*    */ 
/*    */   
/*    */   public void tick(LivingEntity boss) {
/* 31 */     if (boss instanceof IChallengeBoss && ((IChallengeBoss)boss).getChallengeInfo().isDifficultyHard()) {
/* 32 */       Style style = func_186744_e().func_150256_b().func_240721_b_(TextFormatting.DARK_RED).func_240713_a_(Boolean.valueOf(true));
/* 33 */       func_186739_a((ITextComponent)(new StringTextComponent(func_186744_e().getString())).func_230530_a_(style));
/*    */     } 
/*    */     
/* 36 */     if (this.type == 0) {
/* 37 */       func_186746_a(BossInfo.Overlay.NOTCHED_10);
/* 38 */       func_186745_a(BossInfo.Color.RED);
/* 39 */       float percentage = boss.func_110143_aJ() / boss.func_110138_aP();
/* 40 */       func_186735_a(percentage);
/*    */     }
/* 42 */     else if (this.type == 1) {
/* 43 */       BossInfo.Color[] colors = { BossInfo.Color.GREEN, BossInfo.Color.YELLOW, BossInfo.Color.PINK, BossInfo.Color.RED, BossInfo.Color.PURPLE };
/* 44 */       int maxBars = (int)(boss.func_110138_aP() / 500.0F * 5.0F);
/* 45 */       maxBars = Math.max(1, maxBars);
/* 46 */       maxBars = Math.min(colors.length, maxBars);
/* 47 */       float hpThreshold = boss.func_110138_aP() / maxBars;
/*    */       
/* 49 */       if (boss.func_110143_aJ() == boss.func_110138_aP()) {
/* 50 */         func_186745_a(colors[maxBars - 1]);
/* 51 */         func_186735_a(1.0F);
/*    */         
/*    */         return;
/*    */       } 
/* 55 */       float percentage = boss.func_110143_aJ() % hpThreshold / hpThreshold;
/* 56 */       int currentBar = (int)Math.ceil((boss.func_110143_aJ() / boss.func_110138_aP() * maxBars)) - 1;
/* 57 */       currentBar = Math.max(0, currentBar);
/* 58 */       BossInfo.Color currentColor = colors[currentBar];
/* 59 */       func_186745_a(currentColor);
/* 60 */       func_186735_a(percentage);
/*    */     }
/* 62 */     else if (this.type == 2) {
/* 63 */       float hpThreshold = Math.min(boss.func_110138_aP(), 100.0F);
/* 64 */       int maxBars = (int)Math.ceil(((boss.func_110138_aP() - hpThreshold) / hpThreshold));
/* 65 */       int activeBars = (int)Math.ceil(((boss.func_110143_aJ() - hpThreshold) / hpThreshold));
/* 66 */       setBars(maxBars, activeBars);
/* 67 */       if (boss.func_110143_aJ() == boss.func_110138_aP()) {
/* 68 */         func_186735_a(1.0F);
/*    */         return;
/*    */       } 
/* 71 */       float finalBarThreshold = Math.min(boss.func_110138_aP() - (activeBars * 100), 100.0F);
/* 72 */       float percentage = boss.func_110143_aJ() % hpThreshold / finalBarThreshold;
/* 73 */       func_186735_a(percentage);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void setBars(int total, int active) {
/* 78 */     boolean needsUpdate = false;
/* 79 */     if (this.totalBars != total) {
/* 80 */       this.totalBars = total;
/* 81 */       needsUpdate = true;
/*    */     } 
/* 83 */     if (this.activeBars != active) {
/* 84 */       this.activeBars = active;
/* 85 */       needsUpdate = true;
/*    */     } 
/* 87 */     if (needsUpdate) {
/* 88 */       update();
/*    */     }
/*    */   }
/*    */   
/*    */   public void update() {
/* 93 */     if (func_201359_g()) {
/* 94 */       SUpdateOPBossInfoPacket packet = new SUpdateOPBossInfoPacket(func_186737_d(), this.totalBars, this.activeBars);
/* 95 */       for (ServerPlayerEntity serverplayerentity : func_186757_c())
/* 96 */         WyNetwork.sendTo(packet, (PlayerEntity)serverplayerentity); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\ServerOPBossInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */