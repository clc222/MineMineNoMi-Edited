/*     */ package xyz.pixelatedw.mineminenomi.data.entity.haki;
/*     */ 
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.HakiType;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.StatChangeSource;
/*     */ import xyz.pixelatedw.mineminenomi.api.events.stats.HakiExpEvent;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ 
/*     */ public class HakiDataBase implements IHakiData {
/*     */   private LivingEntity owner;
/*     */   private float kenbunshokuExp;
/*     */   private float busoshokuHardeningExp;
/*     */   private int hakiOveruse;
/*     */   private int haoshokuHakiColour;
/*     */   
/*     */   public IHakiData setOwner(LivingEntity owner) {
/*  21 */     this.owner = owner;
/*  22 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getTotalHakiExp() {
/*  28 */     return this.kenbunshokuExp + this.busoshokuHardeningExp;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getMaxHakiExp() {
/*  34 */     return (CommonConfig.INSTANCE.getHakiExpLimit() * ((HakiType.values()).length - 1));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHakiOveruse() {
/*  39 */     return this.hakiOveruse;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxOveruse() {
/*  45 */     return (int)(getTotalHakiExp() * 140.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void alterHakiOveruse(int value) {
/*  50 */     this.hakiOveruse = MathHelper.func_76125_a(this.hakiOveruse + value, 0, getMaxOveruse());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHakiOveruse(int value) {
/*  55 */     this.hakiOveruse = MathHelper.func_76125_a(value, 0, getMaxOveruse());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getKenbunshokuHakiExp() {
/*  61 */     return this.kenbunshokuExp;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean alterKenbunshokuHakiExp(float value, StatChangeSource source) {
/*  67 */     if (this.owner instanceof PlayerEntity) {
/*     */       
/*  69 */       HakiExpEvent.Pre pre = new HakiExpEvent.Pre((PlayerEntity)this.owner, value, HakiType.KENBUNSHOKU, source);
/*  70 */       if (MinecraftForge.EVENT_BUS.post((Event)pre))
/*  71 */         return false; 
/*  72 */       value = pre.getHakiExp();
/*     */     } 
/*  74 */     this.kenbunshokuExp = MathHelper.func_76131_a(this.kenbunshokuExp + value, 0.0F, CommonConfig.INSTANCE.getHakiExpLimit());
/*  75 */     HakiExpEvent.Post post = new HakiExpEvent.Post((PlayerEntity)this.owner, value, HakiType.KENBUNSHOKU, source);
/*  76 */     MinecraftForge.EVENT_BUS.post((Event)post);
/*  77 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setKenbunshokuHakiExp(float value) {
/*  82 */     this.kenbunshokuExp = MathHelper.func_76131_a(value, 0.0F, CommonConfig.INSTANCE.getHakiExpLimit());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getBusoshokuHakiExp() {
/*  88 */     return this.busoshokuHardeningExp;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean alterBusoshokuHakiExp(float value, StatChangeSource source) {
/*  94 */     if (this.owner instanceof PlayerEntity) {
/*     */       
/*  96 */       HakiExpEvent.Pre pre = new HakiExpEvent.Pre((PlayerEntity)this.owner, value, HakiType.BUSOSHOKU, source);
/*  97 */       if (MinecraftForge.EVENT_BUS.post((Event)pre))
/*  98 */         return false; 
/*  99 */       value = pre.getHakiExp();
/*     */     } 
/* 101 */     this.busoshokuHardeningExp = MathHelper.func_76131_a(this.busoshokuHardeningExp + value, 0.0F, CommonConfig.INSTANCE.getHakiExpLimit());
/* 102 */     HakiExpEvent.Post post = new HakiExpEvent.Post((PlayerEntity)this.owner, value, HakiType.BUSOSHOKU, source);
/* 103 */     MinecraftForge.EVENT_BUS.post((Event)post);
/* 104 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBusoshokuHakiExp(float value) {
/* 109 */     this.busoshokuHardeningExp = MathHelper.func_76131_a(value, 0.0F, CommonConfig.INSTANCE.getHakiExpLimit());
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHaoshokuHakiColour() {
/* 114 */     return this.haoshokuHakiColour;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHaoshokuHakiColour(int colour) {
/* 119 */     this.haoshokuHakiColour = colour;
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\data\entity\haki\HakiDataBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */