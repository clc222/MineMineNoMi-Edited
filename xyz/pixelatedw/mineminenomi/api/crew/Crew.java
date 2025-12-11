/*     */ package xyz.pixelatedw.mineminenomi.api.crew;
/*     */ 
/*     */ import java.time.Instant;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.nbt.ListNBT;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAdvancements;
/*     */ 
/*     */ public class Crew
/*     */ {
/*     */   private String name;
/*     */   private boolean isTemporary;
/*     */   private long creationDate;
/*  27 */   private List<Member> members = new ArrayList<>();
/*  28 */   private JollyRoger jollyRoger = new JollyRoger();
/*  29 */   private Set<Claim> claims = new HashSet<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Crew(String name, LivingEntity entity) {
/*  35 */     this(name, entity.func_110124_au(), entity.func_145748_c_().getString(), Instant.now().getEpochSecond());
/*     */   }
/*     */ 
/*     */   
/*     */   public static Crew from(CompoundNBT nbt) {
/*  40 */     Crew crew = new Crew();
/*  41 */     crew.read(nbt);
/*  42 */     return crew;
/*     */   }
/*     */ 
/*     */   
/*     */   public Crew(String name, UUID capId, String username, long creationDate) {
/*  47 */     this.name = name;
/*  48 */     this.isTemporary = true;
/*  49 */     addMember(capId, username).setIsCaptain(true);
/*  50 */     this.creationDate = creationDate;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setName(String name) {
/*  55 */     this.name = name;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  60 */     return this.name;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTemporary() {
/*  65 */     return this.isTemporary;
/*     */   }
/*     */   
/*     */   public Member addMember(LivingEntity entity, boolean saveMember) {
/*  69 */     Member member = addMember(entity.func_110124_au(), entity.func_145748_c_().getString(), saveMember);
/*     */     
/*  71 */     if (member != null && entity instanceof ServerPlayerEntity) {
/*  72 */       ModAdvancements.JOIN_CREW.trigger((ServerPlayerEntity)entity);
/*     */     }
/*     */     
/*  75 */     return member;
/*     */   }
/*     */   
/*     */   public Member addMember(UUID uuid, String username) {
/*  79 */     return addMember(uuid, username, true);
/*     */   }
/*     */   
/*     */   public Member addMember(UUID uuid, String username, boolean saveMember) {
/*  83 */     Member member = (new Member(uuid, username)).setSaved(saveMember);
/*  84 */     this.members.add(member);
/*  85 */     return member;
/*     */   }
/*     */   
/*     */   public void removeMember(World level, UUID id) {
/*  89 */     Member member = getMember(id);
/*  90 */     if (member == null) {
/*     */       return;
/*     */     }
/*  93 */     if (member.isCaptain()) {
/*  94 */       Optional<Member> nextCaptain = getMembers().stream().filter(mem -> !mem.isCaptain()).findFirst();
/*  95 */       if (nextCaptain.isPresent()) {
/*  96 */         member.setIsCaptain(false);
/*  97 */         ((Member)nextCaptain.get()).setIsCaptain(true);
/*     */         
/*  99 */         PlayerEntity oldCaptainPlayer = level.func_217371_b(member.getUUID());
/* 100 */         if (oldCaptainPlayer != null && oldCaptainPlayer instanceof ServerPlayerEntity) {
/* 101 */           ModAdvancements.LEAVE_CREW.trigger((ServerPlayerEntity)oldCaptainPlayer);
/*     */         }
/*     */         
/* 104 */         PlayerEntity nextCaptainPlayer = level.func_217371_b(((Member)nextCaptain.get()).getUUID());
/* 105 */         if (nextCaptainPlayer != null && nextCaptainPlayer instanceof ServerPlayerEntity) {
/* 106 */           ModAdvancements.SET_CREW_CAPTAIN.trigger((ServerPlayerEntity)nextCaptainPlayer);
/*     */         }
/* 108 */         member.markedForDeletion = true;
/*     */       } else {
/*     */         
/* 111 */         for (Member mem : this.members) {
/* 112 */           mem.markedForDeletion = true;
/*     */         }
/*     */       } 
/*     */     } else {
/*     */       
/* 117 */       member.markedForDeletion = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Member getMember(UUID id) {
/* 124 */     return this.members.stream().filter(member -> member.getUUID().equals(id)).findFirst().orElse(null);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasMember(UUID id) {
/* 129 */     return (getMember(id) != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void create(World world) {
/* 134 */     this.isTemporary = false;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Member getCaptain() {
/* 140 */     return this.members.stream().filter(member -> member.isCaptain()).findFirst().orElse(null);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Member> getMembers() {
/* 145 */     return this.members;
/*     */   }
/*     */ 
/*     */   
/*     */   public JollyRoger getJollyRoger() {
/* 150 */     return this.jollyRoger;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setJollyRoger(JollyRoger jr) {
/* 155 */     this.jollyRoger = jr;
/*     */   }
/*     */   
/*     */   public long getCreationDate() {
/* 159 */     return this.creationDate;
/*     */   }
/*     */   
/*     */   public void setCreationDate(long creationDate) {
/* 163 */     this.creationDate = creationDate;
/*     */   }
/*     */   
/*     */   public void tick() {
/* 167 */     if (!this.members.isEmpty()) {
/* 168 */       Iterator<Member> iter = this.members.iterator();
/* 169 */       while (iter.hasNext()) {
/* 170 */         Member mem = iter.next();
/* 171 */         if (mem.markedForDeletion) {
/* 172 */           iter.remove();
/*     */         }
/*     */       } 
/*     */     } else {
/*     */       
/* 177 */       ExtendedWorldData.get().removeCrew(this);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public CompoundNBT write() {
/* 183 */     CompoundNBT crewNBT = new CompoundNBT();
/* 184 */     crewNBT.func_74778_a("name", getName());
/*     */     
/* 186 */     ListNBT members = new ListNBT();
/* 187 */     for (Member member : getMembers()) {
/*     */       
/* 189 */       if (!member.isSaved()) {
/*     */         continue;
/*     */       }
/* 192 */       CompoundNBT memberNBT = new CompoundNBT();
/* 193 */       memberNBT.func_186854_a("id", member.getUUID());
/* 194 */       memberNBT.func_74778_a("username", member.getUsername());
/* 195 */       memberNBT.func_74757_a("isCaptain", member.isCaptain());
/* 196 */       members.add(memberNBT);
/*     */     } 
/* 198 */     crewNBT.func_218657_a("members", (INBT)members);
/*     */     
/* 200 */     CompoundNBT jollyRogerData = this.jollyRoger.write();
/* 201 */     crewNBT.func_218657_a("jollyRoger", (INBT)jollyRogerData);
/*     */     
/* 203 */     crewNBT.func_74772_a("creationDate", this.creationDate);
/*     */     
/* 205 */     return crewNBT;
/*     */   }
/*     */ 
/*     */   
/*     */   public void read(CompoundNBT nbt) {
/* 210 */     String name = nbt.func_74779_i("name");
/* 211 */     setName(name);
/*     */     
/* 213 */     ListNBT members = nbt.func_150295_c("members", 10);
/* 214 */     for (int j = 0; j < members.size(); j++) {
/*     */       
/* 216 */       CompoundNBT memberNBT = members.func_150305_b(j);
/* 217 */       Member member = addMember(memberNBT.func_186857_a("id"), memberNBT.func_74779_i("username"));
/* 218 */       member.setIsCaptain(memberNBT.func_74767_n("isCaptain"));
/*     */     } 
/*     */     
/* 221 */     CompoundNBT jollyRogerData = nbt.func_74775_l("jollyRoger");
/* 222 */     this.jollyRoger.read(jollyRogerData);
/*     */     
/* 224 */     Long creationDate = Long.valueOf(nbt.func_74763_f("creationDate"));
/* 225 */     if (creationDate.longValue() == 0L) {
/* 226 */       this.creationDate = Instant.now().getEpochSecond();
/*     */       return;
/*     */     } 
/* 229 */     this.creationDate = creationDate.longValue();
/*     */   }
/*     */   
/*     */   private Crew() {}
/*     */   
/*     */   public static class Member {
/*     */     private UUID uuid;
/*     */     private String username;
/*     */     private boolean isCaptain;
/*     */     private boolean isSaved;
/*     */     private boolean markedForDeletion;
/*     */     
/*     */     public Member(LivingEntity entity) {
/* 242 */       this(entity.func_110124_au(), entity.func_145748_c_().getString());
/*     */     }
/*     */ 
/*     */     
/*     */     public Member(UUID uuid, String username) {
/* 247 */       this.uuid = uuid;
/* 248 */       this.username = username;
/*     */     }
/*     */ 
/*     */     
/*     */     public Member setIsCaptain(boolean flag) {
/* 253 */       this.isCaptain = flag;
/* 254 */       return this;
/*     */     }
/*     */     
/*     */     public Member setSaved(boolean flag) {
/* 258 */       this.isSaved = flag;
/* 259 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isCaptain() {
/* 264 */       return this.isCaptain;
/*     */     }
/*     */     
/*     */     public boolean isSaved() {
/* 268 */       return this.isSaved;
/*     */     }
/*     */ 
/*     */     
/*     */     public UUID getUUID() {
/* 273 */       return this.uuid;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getUsername() {
/* 278 */       return this.username;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\herrc\Downloads\mine-mine-no-mi-1.16.5-0.10.8.jar!\xyz\pixelatedw\mineminenomi\api\crew\Crew.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */