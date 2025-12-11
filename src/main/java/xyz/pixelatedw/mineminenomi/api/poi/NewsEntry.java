/*    */ package xyz.pixelatedw.mineminenomi.api.poi;
/*    */ 
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.entities.mobs.NotoriousEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18nInteractions;
/*    */ 
/*    */ public class NewsEntry {
/*  9 */   private static final String[] PIRATE_NEWS = new String[] { ModI18nInteractions.PIRATE_NEWS_1_MESSAGE, ModI18nInteractions.PIRATE_NEWS_2_MESSAGE, ModI18nInteractions.PIRATE_NEWS_3_MESSAGE, ModI18nInteractions.PIRATE_NEWS_4_MESSAGE, ModI18nInteractions.PIRATE_NEWS_5_MESSAGE, ModI18nInteractions.PIRATE_NEWS_6_MESSAGE, ModI18nInteractions.PIRATE_NEWS_7_MESSAGE };
/*    */ 
/*    */ 
/*    */   
/* 13 */   private static final String[] MARINE_NEWS = new String[] { ModI18nInteractions.MARINE_NEWS_1_MESSAGE, ModI18nInteractions.MARINE_NEWS_2_MESSAGE, ModI18nInteractions.MARINE_NEWS_3_MESSAGE, ModI18nInteractions.MARINE_NEWS_4_MESSAGE, ModI18nInteractions.MARINE_NEWS_5_MESSAGE, ModI18nInteractions.MARINE_NEWS_6_MESSAGE };
/*    */   
/*    */   private final TrackedNPC tracked;
/*    */   
/*    */   private TranslationTextComponent message;
/*    */   
/*    */   private NewsEntry(TrackedNPC tracked) {
/* 20 */     this.tracked = tracked;
/*    */   }
/*    */   
/*    */   public static NewsEntry createNewsFor(TrackedNPC tracked, World world) {
/* 24 */     NewsEntry entry = new NewsEntry(tracked);
/* 25 */     NotoriousEntity entity = tracked.createTrackedMob(world);
/*    */     
/* 27 */     if (tracked.isPirate()) {
/* 28 */       long bounty = tracked.getBounty();
/* 29 */       int kills = 100 + tracked.getRandom().nextInt(255);
/* 30 */       int razed = 2 + tracked.getRandom().nextInt(10);
/*    */       
/* 32 */       int newsId = tracked.getRandom().nextInt(PIRATE_NEWS.length);
/* 33 */       Object[] args = { entity.func_145748_c_().getString() };
/* 34 */       switch (newsId) {
/*    */         
/*    */         case 0:
/*    */         case 2:
/*    */         case 6:
/* 39 */           args = new Object[] { entity.func_145748_c_().getString(), Integer.valueOf(razed) };
/*    */           break;
/*    */         
/*    */         case 1:
/*    */         case 3:
/* 44 */           args = new Object[] { entity.func_145748_c_().getString(), Long.valueOf(bounty) };
/*    */           break;
/*    */         
/*    */         case 5:
/* 48 */           args = new Object[] { entity.func_145748_c_().getString(), Integer.valueOf(kills) };
/*    */           break;
/*    */       } 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 55 */       entry.message = new TranslationTextComponent(PIRATE_NEWS[newsId], args);
/*    */     }
/* 57 */     else if (tracked.isMarine()) {
/* 58 */       int arrests = 50 + tracked.getRandom().nextInt(200);
/* 59 */       int razed = 2 + tracked.getRandom().nextInt(10);
/*    */       
/* 61 */       int newsId = tracked.getRandom().nextInt(MARINE_NEWS.length);
/* 62 */       Object[] args = { entity.func_145748_c_().getString() };
/* 63 */       switch (newsId) {
/*    */         
/*    */         case 0:
/*    */         case 3:
/*    */         case 4:
/* 68 */           args = new Object[] { entity.func_145748_c_().getString(), Integer.valueOf(razed) };
/*    */           break;
/*    */         
/*    */         case 1:
/*    */         case 2:
/* 73 */           args = new Object[] { entity.func_145748_c_().getString(), Integer.valueOf(arrests) };
/*    */           break;
/*    */       } 
/*    */ 
/*    */ 
/*    */       
/* 79 */       entry.message = new TranslationTextComponent(MARINE_NEWS[newsId], args);
/*    */     } 
/*    */     
/* 82 */     return entry;
/*    */   }
/*    */   
/*    */   public TranslationTextComponent getMessage() {
/* 86 */     return this.message;
/*    */   }
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\poi\NewsEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */