/*    */ package xyz.pixelatedw.mineminenomi.init;
/*    */ 
/*    */ import java.time.Instant;
/*    */ import java.util.UUID;
/*    */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModNPCGroups
/*    */ {
/*    */   public static final String CAPTAIN_MARKER = "CaptainMarker";
/* 13 */   public static final String UNGROUPED = WyRegistry.registerName("challenge.category.ungrouped", "Ungrouped");
/*    */ 
/*    */   
/* 16 */   public static final String MARINES = WyRegistry.registerName("challenge.category.marines", "Marines");
/*    */ 
/*    */   
/* 19 */   private static final UUID BUGGY_PIRATES_ID = UUID.fromString("0d565a0b-0daf-4465-b9e9-bb2becb2a45a");
/* 20 */   private static final String BUGGY_PIRATES_NAME = WyRegistry.registerName("crew.name.buggy_pirates", "Buggy Pirates");
/* 21 */   public static final Crew BUGGY_PIRATES = new Crew(BUGGY_PIRATES_NAME, BUGGY_PIRATES_ID, "CaptainMarker", Instant.now().getEpochSecond());
/*    */   
/* 23 */   private static final UUID BLACK_CAT_PIRATES_ID = UUID.fromString("d9d66778-a3ff-4f31-be73-0c719fc8d8d2");
/* 24 */   private static final String BLACK_CAT_PIRATES_NAME = WyRegistry.registerName("crew.name.black_cat_pirates", "Black Cat Pirates");
/* 25 */   public static final Crew BLACK_CAT_PIRATES = new Crew(BLACK_CAT_PIRATES_NAME, BLACK_CAT_PIRATES_ID, "CaptainMarker", Instant.now().getEpochSecond());
/*    */   
/* 27 */   private static final UUID KRIEG_PIRATES_ID = UUID.fromString("750f8af7-417a-47d7-b267-148259e7bec8");
/* 28 */   private static final String KRIEG_PIRATES_NAME = WyRegistry.registerName("crew.name.krieg_pirates", "Krieg Pirates");
/* 29 */   public static final Crew KRIEG_PIRATES = new Crew(KRIEG_PIRATES_NAME, KRIEG_PIRATES_ID, "CaptainMarker", Instant.now().getEpochSecond());
/*    */   
/* 31 */   private static final UUID ARLONG_PIRATES_ID = UUID.fromString("5971ee94-829b-472d-b5dc-31cec6deb294");
/* 32 */   private static final String ARLONG_PIRATES_NAME = WyRegistry.registerName("crew.name.arlong_pirates", "Arlong Pirates");
/* 33 */   public static final Crew ARLONG_PIRATES = new Crew(ARLONG_PIRATES_NAME, ARLONG_PIRATES_ID, "CaptainMarker", Instant.now().getEpochSecond());
/*    */ 
/*    */   
/* 36 */   private static final UUID BAROQUE_WORKS_ID = UUID.fromString("0c2a61f0-f2d6-47ac-931a-aadf5241b24d");
/* 37 */   private static final String BAROQUE_WORKS_NAME = WyRegistry.registerName("crew.name.baroque_works", "Baroque Works");
/* 38 */   public static final Crew BAROQUE_WORKS = new Crew(BAROQUE_WORKS_NAME, BAROQUE_WORKS_ID, "CaptainMarker", Instant.now().getEpochSecond());
/*    */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\init\ModNPCGroups.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */