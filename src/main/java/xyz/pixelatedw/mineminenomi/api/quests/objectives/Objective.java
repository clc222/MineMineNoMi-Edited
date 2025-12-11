/*     */ package xyz.pixelatedw.mineminenomi.api.quests.objectives;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.quest.SFinishObjectivePacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyNetwork;
/*     */ 
/*     */ 
/*     */ public abstract class Objective
/*     */ {
/*     */   private String title;
/*     */   private String description;
/*     */   private boolean isHidden;
/*     */   private boolean isOptional;
/*  20 */   private double maxProgress = 1.0D;
/*     */   
/*     */   private double progress;
/*     */   private boolean hasEvent;
/*     */   private boolean hasStartedEvent;
/*  25 */   private List<Objective> requirements = new ArrayList<>();
/*     */   
/*     */   public IStartEvent onStartEvent = player -> {
/*     */     
/*     */     };
/*     */   public IRestartEvent onRestartEvent = player -> true;
/*     */   
/*     */   public Objective(String title) {
/*  33 */     this.title = title;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void triggerStartEvent(PlayerEntity player) {
/*  43 */     this.hasStartedEvent = true;
/*  44 */     this.onStartEvent.start(player);
/*     */   }
/*     */ 
/*     */   
/*     */   public void triggerRestartEvent(PlayerEntity player) {
/*  49 */     this.hasStartedEvent = false;
/*  50 */     this.onRestartEvent.restart(player);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setProgress(PlayerEntity player, double progress, boolean force) {
/*  60 */     if (isComplete() && !force) {
/*     */       return;
/*     */     }
/*     */     
/*  64 */     double previousProgress = getProgress();
/*  65 */     this.progress = MathHelper.func_151237_a(progress, 0.0D, getMaxProgress());
/*     */     
/*  67 */     if (isComplete()) {
/*  68 */       boolean shouldPing = (previousProgress != getProgress());
/*  69 */       if (shouldPing) {
/*  70 */         WyNetwork.sendTo(new SFinishObjectivePacket(), player);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void alterProgress(PlayerEntity player, double progress, boolean force) {
/*  76 */     setProgress(player, this.progress + progress, force);
/*     */   }
/*     */   
/*     */   public void alterProgress(PlayerEntity player, double progress) {
/*  80 */     setProgress(player, this.progress + progress, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getProgress() {
/*  85 */     return this.progress;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaxProgress(double progress) {
/*  90 */     this.maxProgress = progress;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMaxProgress() {
/*  95 */     return this.maxProgress;
/*     */   }
/*     */ 
/*     */   
/*     */   public Objective addRequirements(Objective... objectives) {
/* 100 */     for (Objective obj : objectives) {
/* 101 */       addRequirement(obj);
/*     */     }
/* 103 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Objective addRequirement(Objective objective) {
/* 108 */     if (!this.requirements.contains(objective)) {
/* 109 */       this.requirements.add(objective);
/*     */     }
/* 111 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Objective setDescription(String desc) {
/* 116 */     this.description = desc;
/* 117 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Objective setOptional() {
/* 122 */     this.isOptional = true;
/* 123 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOptional() {
/* 128 */     return this.isOptional;
/*     */   }
/*     */ 
/*     */   
/*     */   public Objective markHidden() {
/* 133 */     this.isHidden = true;
/* 134 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getId() {
/* 139 */     return WyHelper.getResourceName(this.title);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHidden() {
/* 144 */     return (this.isHidden && isLocked());
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTitle() {
/* 149 */     return this.title;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDescription() {
/* 154 */     return this.description;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isComplete() {
/* 159 */     return (this.progress >= this.maxProgress);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLocked() {
/* 164 */     if (this.requirements.size() <= 0) {
/* 165 */       return false;
/*     */     }
/* 167 */     if (this.requirements.stream().filter(o -> (o != null)).allMatch(o -> (!o.isOptional() && o.isComplete()))) {
/* 168 */       return false;
/*     */     }
/* 170 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLocalizedTitle() {
/* 175 */     String objectiveKey = (new TranslationTextComponent(String.format("quest.objective.mineminenomi.%s", new Object[] { getId() }))).func_150268_i();
/* 176 */     return (new TranslationTextComponent(objectiveKey, new Object[] { Integer.valueOf((int)getMaxProgress()) })).getString();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHasEvent(boolean flag) {
/* 181 */     this.hasEvent = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasEvent() {
/* 186 */     return this.hasEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasStartedEvent() {
/* 191 */     return this.hasStartedEvent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundNBT save() {
/* 201 */     CompoundNBT nbt = new CompoundNBT();
/*     */     
/* 203 */     nbt.func_74778_a("id", getId());
/* 204 */     nbt.func_74757_a("isHidden", this.isHidden);
/* 205 */     nbt.func_74780_a("progress", this.progress);
/* 206 */     nbt.func_74757_a("hasStartedEvent", this.hasStartedEvent);
/*     */     
/* 208 */     return nbt;
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(CompoundNBT nbt) {
/* 213 */     this.isHidden = nbt.func_74767_n("isHidden");
/* 214 */     this.progress = nbt.func_74769_h("progress");
/* 215 */     this.hasStartedEvent = nbt.func_74767_n("hasStartedEvent");
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface IRestartEvent {
/*     */     boolean restart(PlayerEntity param1PlayerEntity);
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface IStartEvent {
/*     */     void start(PlayerEntity param1PlayerEntity);
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\quests\objectives\Objective.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */