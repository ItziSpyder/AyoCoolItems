package me.improperissues.ayocoolitems.events;

import me.improperissues.ayocoolitems.Main;
import me.improperissues.ayocoolitems.entity.CustomTNT;
import me.improperissues.ayocoolitems.files.Files;
import me.improperissues.ayocoolitems.items.Items;
import me.improperissues.ayocoolitems.other.*;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

public class OnClick implements Listener {

    static Main plugin = Files.plugin;
    static List<UUID> melonized = new ArrayList<>();
    public static List<UUID> immortal = new ArrayList<>();
    static HashMap<String,Long> reaction = new HashMap<>();
    static HashMap<String,Long> clickCool = new HashMap<>();
    static HashMap<String,Material> airplace = new HashMap<>();
    public static List<String> outlineBelow = new ArrayList<>();
    public static List<String> highlightVector = new ArrayList<>();


    public static void reactionGame(Player player) {
        if (reaction.containsKey(player.getName()) || (clickCool.containsKey(player.getName()) && clickCool.get(player.getName()) > System.currentTimeMillis())) {
            player.sendMessage("§cYou are already playing this game! Left click to end it!");
            return;
        }
        clickCool.put(player.getName(), System.currentTimeMillis() + (5 * 1000));
        new BukkitRunnable() {
            int sec = 0;
            @Override
            public void run() {
                if (sec < 5) {
                    player.sendTitle("§e§l" + (5 - sec),"§eLeft click §fon §aGo §fto see your reaction result!",0,40,0);
                    player.playSound(player.getLocation(),Sound.BLOCK_NOTE_BLOCK_PLING,10,1);
                    sec ++;
                } else {
                    player.sendTitle("§aGo!","§eLeft click §fnow!",0,40,0);
                    player.playSound(player.getLocation(),Sound.BLOCK_NOTE_BLOCK_PLING,10,1.5F);
                    reaction.put(player.getName(),System.currentTimeMillis());
                    this.cancel();
                }
            }
        }.runTaskTimer(Files.plugin,0,20);
    }

    @EventHandler
    public static void PlayerInteractEvent(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (reaction.containsKey(p.getName())) {
            double time = Math.floor((System.currentTimeMillis() - reaction.get(p.getName())) / 10) / 100;
            p.sendTitle("§b§l§oGG!","§bYour reaction time was §e" + time + " §bseconds!",0,40,0);
            p.playSound(p.getLocation(),Sound.ENTITY_PLAYER_LEVELUP,10,10);
            Messages.bm("§e" + p.getName() + " §bplayed the reaction game and got a reaction time of §e" + time + " §b! GG! \n" +
                    "§bResults: §e" + time + " §bseconds, §e" + (System.currentTimeMillis() - reaction.get(p.getName())) +
                    " §bms! (Ping was §e" + p.getPing() + " §band tps was §e" + Main.tps + "§b)");
            reaction.remove(p.getName());
            return;
        }

        try {
            ItemStack item = getClickedItem(p);
            ItemMeta meta = item.getItemMeta();
            String display = meta.getDisplayName();

            if (!isCustom(item)) {
                return;
            }
            e.setCancelled(true);
            if (clickCool.containsKey(p.getName()) && clickCool.get(p.getName()) > System.currentTimeMillis()) {
                return;
            }
            clickCool.put(p.getName(),System.currentTimeMillis() + 50);
            if (e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
                if (display.contains(Items.air_place.getItemMeta().getDisplayName())) {
                    openAirPlaceMenu(p);
                }
                return;
            }

            if (display.contains(Items.fireball.getItemMeta().getDisplayName())) {
                deductStack(p);
                Sounds.playAll(p.getLocation(), Sound.ENTITY_BLAZE_SHOOT,1,1,500);
                Fireball fb = p.getWorld().spawn(p.getEyeLocation(), Fireball.class);
                fb.setShooter(p);
                fb.setBounce(false);
                fb.setYield(0);
                fb.setCustomName(display);
                fb.setVelocity(p.getLocation().getDirection());
            } else if (display.contains(Items.wither.getItemMeta().getDisplayName())) {
                deductStack(p);
                Sounds.playAll(p.getLocation(), Sound.ENTITY_WITHER_SHOOT,1,1,500);
                WitherSkull fb = p.getWorld().spawn(p.getEyeLocation(), WitherSkull.class);
                fb.setShooter(p);
                fb.setBounce(false);
                fb.setYield(0);
                fb.setCustomName(display);
                fb.setVelocity(p.getLocation().getDirection());
                if ((int) Math.ceil(Math.random() * 2) == 2) {
                    fb.setCharged(true);
                }
            } else if (display.contains(Items.thru.getItemMeta().getDisplayName())) {
                if (!p.getGameMode().equals(GameMode.SPECTATOR)) {
                    Sounds.playAll(p.getLocation(), Sound.ENTITY_ENDER_EYE_DEATH,1,1,500);
                    Particle.DustOptions dust = new Particle.DustOptions(Color.PURPLE,5);
                    p.getWorld().spawnParticle(Particle.REDSTONE,p.getLocation(),50,1,1,1,1,dust);
                    GameMode gm = p.getGameMode();
                    p.setGameMode(GameMode.SPECTATOR);
                    getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                        @Override
                        public void run() {
                            Sounds.playAll(p.getLocation(), Sound.ENTITY_ENDER_EYE_DEATH,1,1,500);
                            Particle.DustOptions dust = new Particle.DustOptions(Color.PURPLE,5);
                            p.getWorld().spawnParticle(Particle.REDSTONE,p.getLocation(),50,1,1,1,1,dust);
                            p.setGameMode(gm);
                        }
                    },60);
                }
            } else if (display.contains(Items.rocket.getItemMeta().getDisplayName())) {
                deductStack(p);
                Sounds.playAll(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH,1,1,500);
                p.setVelocity(p.getLocation().getDirection().multiply(3));
            } else if (display.contains(Items.magnet.getItemMeta().getDisplayName())) {
                Sounds.playAll(p.getLocation(), Sound.BLOCK_BELL_RESONATE,1,10,500);
                for (Entity entity : p.getNearbyEntities(20,20,20)) {
                    if (entity != p) {
                        entity.setVelocity((p.getLocation().toVector().subtract(entity.getLocation().toVector())).normalize());
                    }
                }
            } else if (display.contains(Items.reverse_magnet.getItemMeta().getDisplayName())) {
                Sounds.playAll(p.getLocation(), Sound.BLOCK_BELL_RESONATE,1,10,500);
                for (Entity entity : p.getNearbyEntities(20,20,20)) {
                    if (entity != p) {
                        entity.setVelocity((entity.getLocation().toVector().subtract(p.getLocation().toVector())).normalize());
                    }
                }
            } else if (display.contains(Items.ash_wand.getItemMeta().getDisplayName())) {
                clickCool.put(p.getName(),System.currentTimeMillis() + 2000);
                Sounds.playAll(p.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH,10,0.1F,500);
                Location loc = p.getEyeLocation();
                Vector dir = p.getLocation().getDirection().normalize();
                // raycast
                List<String> names = new ArrayList<>();
                for (double distance = 0; distance < 200; distance += 0.5) {
                    clickCool.put(p.getName(),System.currentTimeMillis() + 2000);
                    double x = dir.getX() * distance;
                    double y = dir.getY() * distance;
                    double z = dir.getZ() * distance;
                    Location newLoc = new Location(loc.getWorld(),loc.getX() + x,loc.getY() + y,loc.getZ() + z);
                    Particle.DustOptions dust = new Particle.DustOptions(Color.BLACK,5);
                    newLoc.getWorld().spawnParticle(Particle.REDSTONE,newLoc,2,0,0,0,0,dust);
                    // entity hit
                    for (Entity entity : newLoc.getWorld().getNearbyEntities(newLoc,2,2,2)) {
                        clickCool.put(p.getName(),System.currentTimeMillis() + 2000);
                        if (entity != p && entity instanceof LivingEntity) {
                            LivingEntity le = (LivingEntity) entity;
                            le.damage(5,p);
                            Sounds.playAll(p.getLocation(), Sound.ENTITY_BLAZE_SHOOT,10,0.1F,500);
                            newLoc.getWorld().spawnParticle(Particle.FLAME,newLoc,999,2,2,2,0.1);
                            // entity combust
                            for (Entity nearby : entity.getWorld().getNearbyEntities(entity.getLocation(),5,5,5)) {
                                if (nearby != p && nearby instanceof LivingEntity) {
                                    Particle.DustOptions dust2 = new Particle.DustOptions(Color.GRAY,5);
                                    nearby.getWorld().spawnParticle(Particle.REDSTONE,nearby.getLocation(),50,1,1,1,1,dust2);
                                    names.add(nearby.getName());
                                    if (nearby instanceof Player) {
                                        ((Player) nearby).setHealth(0F);
                                    } else {
                                        nearby.remove();
                                    }
                                }
                            }
                            distance = 200;
                            break;
                        }
                    }
                }
                String name = names.toString().substring(1,names.toString().length() - 1);
                if (names.size() > 0) {
                    Messages.bm("§7" + name + " §fturned to ashes by a beam shot from §7" + p.getName() + "'s §fash wand");
                }
            } else if (display.contains(Items.tp_stick.getItemMeta().getDisplayName())) {
                clickCool.put(p.getName(),System.currentTimeMillis() + 1000);
                Location loc = p.getEyeLocation();
                Vector dir = p.getLocation().getDirection().normalize();
                // raycast
                for (double distance = 0; distance < 500; distance += 0.2) {
                    double x = dir.getX() * distance;
                    double y = dir.getY() * distance;
                    double z = dir.getZ() * distance;
                    Location newLoc = new Location(loc.getWorld(),loc.getX() + x,loc.getY() + y,loc.getZ() + z,loc.getYaw(),loc.getPitch());
                    Particle.DustOptions dust = new Particle.DustOptions(Color.RED,1);
                    newLoc.getWorld().spawnParticle(Particle.REDSTONE,newLoc,2,0,0,0,0,dust);
                    // block hit
                    Block block = newLoc.getWorld().getBlockAt(newLoc);
                    if (!block.isPassable()) {
                        x = dir.getX() * (distance - 2);
                        y = dir.getY() * (distance - 2);
                        z = dir.getZ() * (distance - 2);
                        newLoc = new Location(loc.getWorld(),loc.getX() + x,loc.getY() + y,loc.getZ() + z,loc.getYaw(),loc.getPitch());
                        Location finalNewLoc = newLoc;
                        getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                            @Override
                            public void run() {
                                p.teleport(finalNewLoc);
                            }
                        },5);
                        break;
                    }
                }
                Sounds.playAll(p.getLocation(), Sound.ENTITY_SHULKER_TELEPORT,1,10,500);
            } else if (display.contains(Items.improperimpressions.getItemMeta().getDisplayName())) {
                clickCool.put(p.getName(),System.currentTimeMillis() + 5000);
                Displays.storm(p.getLocation());
            } else if (display.contains(Items.air_place.getItemMeta().getDisplayName())) {
                Material material = airplace.get(p.getName());
                if (material == null) {
                    material = Material.BLACK_CONCRETE;
                }
                Location loc = Vectors.getEyeTargetVector(p,5);
                Block block = loc.getBlock();
                if (block.isPassable()) {
                    block.setType(material);
                    Sounds.playAll(p.getLocation(), Sound.BLOCK_STONE_PLACE,1,1,500);
                }
            } else if (display.contains(Items.shield.getItemMeta().getDisplayName())) {
                clickCool.put(p.getName(),System.currentTimeMillis() + 2000);
                Displays.forceField(p,p.getLocation().clone().add(0,1,0));
            } else if (display.contains(Items.tazer.getItemMeta().getDisplayName())) {
                clickCool.put(p.getName(),System.currentTimeMillis() + 250);
                Raycast.tazer(p);
            } else if (display.contains(Items.magneticRevolver.getItemMeta().getDisplayName())) {
                clickCool.put(p.getName(),System.currentTimeMillis() + 2000);
                Raycast.magneticRevolver(p);
            } else if (display.contains(Items.falling_wand.getItemMeta().getDisplayName())) {
                clickCool.put(p.getName(),System.currentTimeMillis() + 1);
                Block block = p.getTargetBlockExact(5);
                block.getWorld().spawnFallingBlock(block.getLocation().add(0.5,0,0.5),block.getType(),(byte) 1);
                Displays.outline(block.getLocation());
                block.setType(Material.AIR);
            } else if (display.contains(Items.immortality.getItemMeta().getDisplayName())) {
                clickCool.put(p.getName(),System.currentTimeMillis() + 1000);
                if (isImmortal(p)) {
                    immortal.remove(p.getUniqueId());
                    p.playSound(p.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_DEPLETE,10,0.1F);
                    p.sendTitle("","§f§l§oYou are no longer immortal!",10,40,10);
                } else {
                    immortal.add(p.getUniqueId());
                    p.playSound(p.getLocation(), Sound.ITEM_TOTEM_USE,10,0.1F);
                    p.sendTitle("","§6§l§oYou have been granted immortality!",10,40,10);
                }
            } else if (display.contains(Items.TNTCrystal.getItemMeta().getDisplayName())) {
                clickCool.put(p.getName(),System.currentTimeMillis() + 500);

                if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                    Block block = e.getClickedBlock();
                    String name = block.getType().name();
                    if (name.contains("OBSIDIAN") || name.contains("BEDROCK")) {
                        Location spawn = block.getLocation().clone().add(0.5,1,0.5);
                        for (Entity nearby : spawn.getWorld().getNearbyEntities(spawn,1,1,1)) {
                            if (nearby.getScoreboardTags().contains("§cTNT_CRYSTAL") && nearby != null && nearby.getWorld() == spawn.getWorld() && nearby.getLocation().distanceSquared(spawn) < 1) {
                                return;
                            }
                        }
                        if (!p.getGameMode().equals(GameMode.CREATIVE)) {
                            deductStack(p);
                        }
                        CustomArmorStands.tntCrystal(spawn);
                    }
                }
            } else if (display.contains(Items.flintAndSteel.getItemMeta().getDisplayName())) {
                clickCool.put(p.getName(),System.currentTimeMillis() + 500);

                if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                    Block block = e.getClickedBlock();
                    CustomTNT.spawnNew(block,p);
                    block.setType(Material.AIR);
                        p.playSound(p.getLocation(),Sound.ITEM_FLINTANDSTEEL_USE,1,1);
                    p.playSound(p.getLocation(),Sound.ENTITY_TNT_PRIMED,1,1);
                }
            }
        } catch (NullPointerException exception) {
            // empty
        }
    }

    static List<Entity> tokill = new ArrayList<>();
    @EventHandler
    public static void EntityDamageByEntityEvent(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();

        if (!(damager instanceof Player)) {
            return;
        }
        Player p = (Player) damager;
        if (clickCool.containsKey(p.getName()) && clickCool.get(p.getName()) > System.currentTimeMillis()) {
            return;
        }
        if (tokill.contains(e.getEntity())) {
            e.setCancelled(true);
            return;
        }
        clickCool.put(p.getName(),System.currentTimeMillis() + 50);

        try {
            ItemStack item = getClickedItem(p);
            ItemMeta meta = item.getItemMeta();
            String display = meta.getDisplayName();


            if (display.contains(Items.anime_sword.getItemMeta().getDisplayName())) {
                clickCool.put(p.getName(),System.currentTimeMillis() + 1000);
                List<Entity> entities = new ArrayList<>(p.getNearbyEntities(5,5,5));
                entities.removeIf(entity -> !(entity instanceof LivingEntity) || entity == p);
                tokill.addAll(entities);

                new BukkitRunnable() {
                    int count = 0;
                    @Override
                    public void run() {
                        if (count < entities.size()) {
                            Entity entity = entities.get(count);
                            Location loc = Vectors.getTargetVector(entity,-2);
                            loc.setY(entity.getLocation().getY());
                            p.teleport(loc);
                            p.getWorld().spawnParticle(Particle.SWEEP_ATTACK,Vectors.getEyeTargetVector(p,1),1,0,0,0,0);
                            p.getWorld().spawnParticle(Particle.REDSTONE,Vectors.getEyeTargetVector(p,1).add(0,-0.5,0),20,0.5,0.5,0.5,0, new Particle.DustOptions(Color.RED,1));
                            Sounds.playAll(p.getLocation(),Sound.BLOCK_GLASS_BREAK,2,10,200);
                            Sounds.playAll(p.getLocation(),Sound.ENTITY_PLAYER_ATTACK_SWEEP,1,0.1F,200);
                            Sounds.playAll(p.getLocation(),Sound.ITEM_TRIDENT_THROW,1,1F,200);
                            ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOW,999999,255,false));
                            ((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,999999,1,false));
                            count ++;
                        } else {
                            getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                @Override
                                public void run() {
                                    for (Entity entity : entities) {
                                        ((LivingEntity) entity).damage(1,p);
                                        ((LivingEntity) entity).setHealth(0);
                                    }
                                }
                            },20);
                            this.cancel();
                        }
                    }
                }.runTaskTimer(plugin,0,1);
            }
        } catch (NullPointerException exception) {
            // empty
        }
    }

    @EventHandler
    public static void PlayerInteractAtEntityEvent(PlayerInteractAtEntityEvent e) {
        Player p = e.getPlayer();
        Entity entity = e.getRightClicked();

        try {
            ItemStack item = getClickedItem(p);
            ItemMeta meta = item.getItemMeta();
            String display = meta.getDisplayName();

            if (!isCustom(item)) {
                return;
            }
            e.setCancelled(true);
            if (clickCool.containsKey(p.getName()) && clickCool.get(p.getName()) > System.currentTimeMillis()) {
                return;
            }
            clickCool.put(p.getName(),System.currentTimeMillis() + 50);

            if (display.contains(Items.melonizer.getItemMeta().getDisplayName())) {
                clickCool.put(p.getName(),System.currentTimeMillis() + 2000);
                if (!(entity instanceof LivingEntity)) {
                    return;
                }
                if (isMelonized(entity)) {
                    return;
                }
                LivingEntity le = (LivingEntity) entity;
                deductStack(p);
                Sounds.playAll(le.getLocation(), Sound.BLOCK_COMPOSTER_EMPTY,1,1,500);
                Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB(212,70,56),5);
                le.getWorld().spawnParticle(Particle.REDSTONE,le.getLocation(),50,1,1,1,1,dust);
                UUID id = le.getUniqueId();
                melonized.add(id);
                Block block = le.getWorld().getBlockAt(le.getLocation());
                Material type = block.getType();
                if (block.isPassable()) {
                    block.setType(Material.MELON);
                }
                GameMode gm = null;
                if (le instanceof Player) {
                    Player target = (Player) le;
                    gm = target.getGameMode();
                    target.setGameMode(GameMode.SPECTATOR);
                } else {
                    le.remove();
                    le.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,999999,1,false));
                    le.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,999999,255,false));
                }
                Messages.bm("§a" + le.getName() + " §2was melonized and frozen");
                GameMode finalGm = gm;
                getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                    @Override
                    public void run() {
                        Sounds.playAll(p.getLocation(), Sound.BLOCK_COMPOSTER_EMPTY,1,1,500);
                        Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB(212,70,56),5);
                        le.getWorld().spawnParticle(Particle.REDSTONE,le.getLocation(),50,1,1,1,1,dust);
                        if (block.getType().equals(Material.MELON)) {
                            block.setType(type);
                        }

                        if (le instanceof Player) {
                            Player target = (Player) le;
                            target.setGameMode(finalGm);
                            target.setHealth(0);
                        } else {
                            Messages.bm("§a" + le.getName() + " §2was squished and died");
                        }
                        melonized.remove(id);
                    }
                },60);
            } else if (display.contains(Items.taker.getItemMeta().getDisplayName())) {
                clickCool.put(p.getName(),System.currentTimeMillis() + 2000);
                // living entity filter
                if (!(entity instanceof LivingEntity)) {
                    return;
                }
                // add the potion effects
                LivingEntity le = (LivingEntity) entity;
                le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,999999,255,false));
                le.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,999999,255,false));
                le.addPotionEffect(new PotionEffect(PotionEffectType.JUMP,999999,360,false));
                Displays.wave(le.getLocation(),2,0.3,Color.BLACK,3);
                // sink the ship
                getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                    @Override
                    public void run() {
                        new BukkitRunnable() {
                            double waves = 0;
                            @Override
                            public void run() {
                                if (waves < 16) {
                                    le.teleport(le.getLocation().add(0,-0.07,0));
                                    waves += 0.3;
                                } else {
                                    le.teleport(le.getLocation().add(0,-999999,0));
                                    le.setHealth(0);
                                    Messages.bm("§c" + le.getName() + " §4was taken");
                                    this.cancel();
                                    return;
                                }
                            }
                        }.runTaskTimer(plugin,0,1);
                    }
                },20);
            }
        } catch (NullPointerException | IllegalArgumentException | ClassCastException exception) {
            // empty
        }
    }

    @EventHandler
    public static void PlayerMoveEvent(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (isMelonized(p)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public static void PlayerDeathEvent(PlayerDeathEvent e) {
        Player p = e.getEntity().getPlayer();

        if (p != null && isMelonized(p)) {
            Messages.bm("§a" + p.getName() + " §2was squished and died");
        }
    }

    public static ItemStack getClickedItem(Player player) {
        ItemStack main = player.getInventory().getItemInMainHand();
        ItemStack off = player.getInventory().getItemInOffHand();

        if (isCustom(main)) {
            return main;
        } else {
            return off;
        }
    }

    public static void deductStack(Player player) {
        if (!player.getGameMode().equals(GameMode.CREATIVE)) {
            getClickedItem(player).setAmount(getClickedItem(player).getAmount() - 1);
        }
    }

    public static boolean isCustom(ItemStack item) {
        try {
            return item.getItemMeta().getLore().contains("§8§o#ayocoolitems");
        } catch (NullPointerException exception) {
            return false;
        }
    }

    public static boolean isMelonized(Entity entity) {
        return melonized.contains(entity.getUniqueId());
    }

    public static boolean isImmortal(Entity entity) {
        return immortal.contains(entity.getUniqueId());
    }

    public static void registerEvents() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (outlineBelow.contains(p.getName())) {
                if (p.isOnGround()) Displays.outline(p.getLocation().add(0,-1,0));
            }
            if (highlightVector.contains(p.getName())) {
                Displays.draw(p.getEyeLocation(),Vectors.getEyeTargetVector(p,4.5),Color.BLUE);
                Displays.outline(Vectors.getEyeTargetVector(p,4.5));
            }
            try {
                ItemStack item = getClickedItem(p);
                ItemMeta meta = item.getItemMeta();
                String display = meta.getDisplayName();

                if (display.contains(Items.air_place.getItemMeta().getDisplayName())) {
                    Displays.outline(Vectors.getEyeTargetVector(p,5));
                }
            } catch (NullPointerException exception) {
                // empty
            }
        }
    }


    @EventHandler
    public static void InventionClickEvent(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        Inventory inv = e.getClickedInventory();
        String title = e.getView().getTitle();

        try {
            if (title.contains(Messages.starter) && !inv.getType().equals(InventoryType.PLAYER)) {
                e.setCancelled(true);
                ItemStack item = e.getCurrentItem();
                ItemMeta meta = item.getItemMeta();

                if (title.contains("1Airplace block type selection")) {
                    airplace.put(p.getName(),item.getType());
                    p.playSound(p.getLocation(),Sound.UI_BUTTON_CLICK,10,10);
                    p.closeInventory();
                    p.sendMessage(Messages.starter + "7Set your air place block type to §f" + item.getType().name().toLowerCase());
                }
            }
        } catch (NullPointerException exception) {
            // empty
        }
    }

    public static void openAirPlaceMenu(Player player) {
        Inventory menu = Bukkit.createInventory(player,54,Messages.starter + "1Airplace block type selection");

        for (Material material : Material.class.getEnumConstants()) {
            String name = material.name().toLowerCase();
            try {
                if ((name.contains("_concrete") && !name.contains("_powder"))
                        || (name.contains("terracotta") && !name.contains("glazed"))
                ) {
                    ItemStack item = new ItemStack(material);
                    menu.setItem(menu.firstEmpty(),item);
                }
            } catch (ArrayIndexOutOfBoundsException exception) {
                break;
            }
        }

        player.openInventory(menu);
    }
}
