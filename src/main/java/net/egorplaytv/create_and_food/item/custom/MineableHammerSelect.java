package net.egorplaytv.create_and_food.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class MineableHammerSelect {
    public static void execute(Level world, BlockPos pos, Entity entity) {
        if (entity instanceof ServerPlayer player) {
            ItemStack item = player.getItemInHand(InteractionHand.MAIN_HAND);
            if (item.getDamageValue() <= item.getMaxDamage() - 8) {
                if (player.gameMode.getGameModeForPlayer() != GameType.SPECTATOR) {
                    if (!player.isShiftKeyDown()) {
                        if ((player.level.clip(new ClipContext(player.getEyePosition(1f),
                                player.getEyePosition(1f).add(player.getViewVector(1f).scale(5)),
                                ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player)).getDirection() == Direction.DOWN ||
                                player.level.clip(new ClipContext(player.getEyePosition(1f),
                                        player.getEyePosition(1f).add(player.getViewVector(1f).scale(5)),
                                        ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player)).getDirection() == Direction.UP)
                                && world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ())).getBlock() != Blocks.BEDROCK) {
                            int damage = 0;
                            BlockPos posForDrop = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
                            BlockPos pos1 = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
                            Block.dropResources(world.getBlockState(pos1), world, posForDrop, null);
                            world.destroyBlock(pos1, false);
                            if (world.getBlockState(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ())).getBlock() != Blocks.AIR &&
                                    world.getBlockState(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ())).getBlock() != Blocks.BEDROCK) {
                                BlockPos pos2 = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ());
                                Block.dropResources(world.getBlockState(pos2), world, posForDrop, null);
                                world.destroyBlock(pos2, false);
                                damage = damage + 1;
                            }
                            if (world.getBlockState(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ())).getBlock() != Blocks.AIR &&
                                    world.getBlockState(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ())).getBlock() != Blocks.BEDROCK) {
                                BlockPos pos3 = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ());
                                Block.dropResources(world.getBlockState(pos3), world, posForDrop, null);
                                world.destroyBlock(pos3, false);
                                damage = damage + 1;
                            }
                            if (world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1)).getBlock() != Blocks.AIR &&
                                    world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1)).getBlock() != Blocks.BEDROCK) {
                                BlockPos pos4 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1);
                                Block.dropResources(world.getBlockState(pos4), world, posForDrop, null);
                                world.destroyBlock(pos4, false);
                                damage = damage + 1;
                            }
                            if (world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1)).getBlock() != Blocks.AIR &&
                                    world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1)).getBlock() != Blocks.BEDROCK) {
                                BlockPos pos5 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1);
                                Block.dropResources(world.getBlockState(pos5), world, posForDrop, null);
                                world.destroyBlock(pos5, false);
                                damage = damage + 1;
                            }
                            if (world.getBlockState(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() + 1)).getBlock() != Blocks.AIR &&
                                    world.getBlockState(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() + 1)).getBlock() != Blocks.BEDROCK) {
                                BlockPos pos6 = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() + 1);
                                Block.dropResources(world.getBlockState(pos6), world, posForDrop, null);
                                world.destroyBlock(pos6, false);
                                damage = damage + 1;
                            }
                            if (world.getBlockState(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() - 1)).getBlock() != Blocks.AIR &&
                                    world.getBlockState(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() - 1)).getBlock() != Blocks.BEDROCK) {
                                BlockPos pos7 = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() - 1);
                                Block.dropResources(world.getBlockState(pos7), world, posForDrop, null);
                                world.destroyBlock(pos7, false);
                                damage = damage + 1;
                            }
                            if (world.getBlockState(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() - 1)).getBlock() != Blocks.AIR &&
                                    world.getBlockState(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() - 1)).getBlock() != Blocks.BEDROCK) {
                                BlockPos pos8 = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() - 1);
                                Block.dropResources(world.getBlockState(pos8), world, posForDrop, null);
                                world.destroyBlock(pos8, false);
                                damage = damage + 1;
                            }
                            if (world.getBlockState(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() + 1)).getBlock() != Blocks.AIR &&
                                    world.getBlockState(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() + 1)).getBlock() != Blocks.BEDROCK) {
                                BlockPos pos9 = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() + 1);
                                Block.dropResources(world.getBlockState(pos9), world, posForDrop, null);
                                world.destroyBlock(pos9, false);
                                damage = damage + 1;
                            }
                            item.hurtAndBreak(damage, player, (player1) -> player1.broadcastBreakEvent(EquipmentSlot.MAINHAND));
                        } else if ((player.level.clip(new ClipContext(player.getEyePosition(1f),
                                player.getEyePosition(1f).add(player.getViewVector(1f).scale(5)),
                                ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player)).getDirection() == Direction.NORTH ||
                                player.level.clip(new ClipContext(player.getEyePosition(1f),
                                        player.getEyePosition(1f).add(player.getViewVector(1f).scale(5)),
                                        ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player)).getDirection() == Direction.SOUTH)
                                && world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ())).getBlock() != Blocks.BEDROCK) {
                            int damage = 0;
                            BlockPos posForDrop = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
                            BlockPos pos1 = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
                            Block.dropResources(world.getBlockState(pos1), world, posForDrop, null);
                            world.destroyBlock(pos1, false);
                            if (world.getBlockState(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ())).getBlock() != Blocks.BEDROCK) {
                                BlockPos pos2 = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ());
                                Block.dropResources(world.getBlockState(pos2), world, posForDrop, null);
                                world.destroyBlock(pos2, false);
                                damage = damage + 1;
                            }
                            if (world.getBlockState(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ())).getBlock() != Blocks.AIR &&
                                    world.getBlockState(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ())).getBlock() != Blocks.BEDROCK) {
                                BlockPos pos3 = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ());
                                Block.dropResources(world.getBlockState(pos3), world, posForDrop, null);
                                world.destroyBlock(pos3, false);
                                damage = damage + 1;
                            }
                            if (world.getBlockState(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ())).getBlock() != Blocks.AIR &&
                                    world.getBlockState(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ())).getBlock() != Blocks.BEDROCK) {
                                BlockPos pos4 = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
                                Block.dropResources(world.getBlockState(pos4), world, posForDrop, null);
                                world.destroyBlock(pos4, false);
                                damage = damage + 1;
                            }
                            if (world.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ())).getBlock() != Blocks.AIR &&
                                    world.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ())).getBlock() != Blocks.BEDROCK) {
                                BlockPos pos5 = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());
                                Block.dropResources(world.getBlockState(pos5), world, posForDrop, null);
                                world.destroyBlock(pos5, false);
                                damage = damage + 1;
                            }
                            if (world.getBlockState(new BlockPos(pos.getX() + 1, pos.getY() + 1, pos.getZ())).getBlock() != Blocks.AIR &&
                                    world.getBlockState(new BlockPos(pos.getX() + 1, pos.getY() + 1, pos.getZ())).getBlock() != Blocks.BEDROCK) {
                                BlockPos pos6 = new BlockPos(pos.getX() + 1, pos.getY() + 1, pos.getZ());
                                Block.dropResources(world.getBlockState(pos6), world, posForDrop, null);
                                world.destroyBlock(pos6, false);
                                damage = damage + 1;
                            }
                            if (world.getBlockState(new BlockPos(pos.getX() - 1, pos.getY() - 1, pos.getZ())).getBlock() != Blocks.AIR &&
                                    world.getBlockState(new BlockPos(pos.getX() - 1, pos.getY() - 1, pos.getZ())).getBlock() != Blocks.BEDROCK) {
                                BlockPos pos7 = new BlockPos(pos.getX() - 1, pos.getY() - 1, pos.getZ());
                                Block.dropResources(world.getBlockState(pos7), world, posForDrop, null);
                                world.destroyBlock(pos7, false);
                                damage = damage + 1;
                            }
                            if (world.getBlockState(new BlockPos(pos.getX() + 1, pos.getY() - 1, pos.getZ())).getBlock() != Blocks.AIR &&
                                    world.getBlockState(new BlockPos(pos.getX() + 1, pos.getY() - 1, pos.getZ())).getBlock() != Blocks.BEDROCK) {
                                BlockPos pos8 = new BlockPos(pos.getX() + 1, pos.getY() - 1, pos.getZ());
                                Block.dropResources(world.getBlockState(pos8), world, posForDrop, null);
                                world.destroyBlock(pos8, false);
                                damage = damage + 1;
                            }
                            if (world.getBlockState(new BlockPos(pos.getX() - 1, pos.getY() + 1, pos.getZ())).getBlock() != Blocks.AIR &&
                                    world.getBlockState(new BlockPos(pos.getX() - 1, pos.getY() + 1, pos.getZ())).getBlock() != Blocks.BEDROCK) {
                                BlockPos pos9 = new BlockPos(pos.getX() - 1, pos.getY() + 1, pos.getZ());
                                Block.dropResources(world.getBlockState(pos9), world, posForDrop, null);
                                world.destroyBlock(pos9, false);
                                damage = damage + 1;
                            }
                            item.hurtAndBreak(damage, player, (player1) -> player1.broadcastBreakEvent(EquipmentSlot.MAINHAND));
                        } else if ((player.level.clip(new ClipContext(player.getEyePosition(1f),
                                player.getEyePosition(1f).add(player.getViewVector(1f).scale(5)),
                                ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player)).getDirection() == Direction.WEST ||
                                player.level.clip(new ClipContext(player.getEyePosition(1f),
                                        player.getEyePosition(1f).add(player.getViewVector(1f).scale(5)),
                                        ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player)).getDirection() == Direction.EAST)
                                && world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ())).getBlock() != Blocks.BEDROCK) {
                            int damage = 0;
                            BlockPos posForDrop = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
                            BlockPos pos1 = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
                            Block.dropResources(world.getBlockState(pos1), world, posForDrop, null);
                            world.destroyBlock(pos1, false);
                            if (world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1)).getBlock() != Blocks.AIR &&
                                    world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1)).getBlock() != Blocks.BEDROCK) {
                                BlockPos pos2 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1);
                                Block.dropResources(world.getBlockState(pos2), world, posForDrop, null);
                                world.destroyBlock(pos2, false);
                                damage = damage + 1;
                            }
                            if (world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1)).getBlock() != Blocks.AIR &&
                                    world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1)).getBlock() != Blocks.BEDROCK) {
                                BlockPos pos3 = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1);
                                Block.dropResources(world.getBlockState(pos3), world, posForDrop, null);
                                world.destroyBlock(pos3, false);
                                damage = damage + 1;
                            }
                            if (world.getBlockState(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ())).getBlock() != Blocks.AIR &&
                                    world.getBlockState(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ())).getBlock() != Blocks.BEDROCK) {
                                BlockPos pos4 = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
                                Block.dropResources(world.getBlockState(pos4), world, posForDrop, null);
                                world.destroyBlock(pos4, false);
                                damage = damage + 1;
                            }
                            if (world.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ())).getBlock() != Blocks.AIR &&
                                    world.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ())).getBlock() != Blocks.BEDROCK) {
                                BlockPos pos5 = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());
                                Block.dropResources(world.getBlockState(pos5), world, posForDrop, null);
                                world.destroyBlock(pos5, false);
                                damage = damage + 1;
                            }
                            if (world.getBlockState(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ() + 1)).getBlock() != Blocks.AIR &&
                                    world.getBlockState(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ() + 1)).getBlock() != Blocks.BEDROCK) {
                                BlockPos pos6 = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ() + 1);
                                Block.dropResources(world.getBlockState(pos6), world, posForDrop, null);
                                world.destroyBlock(pos6, false);
                                damage = damage + 1;
                            }
                            if (world.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ() - 1)).getBlock() != Blocks.AIR &&
                                    world.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ() - 1)).getBlock() != Blocks.BEDROCK) {
                                BlockPos pos7 = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ() - 1);
                                Block.dropResources(world.getBlockState(pos7), world, posForDrop, null);
                                world.destroyBlock(pos7, false);
                                damage = damage + 1;
                            }
                            if (world.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ() + 1)).getBlock() != Blocks.AIR &&
                                    world.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ() + 1)).getBlock() != Blocks.BEDROCK) {
                                BlockPos pos8 = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ() + 1);
                                Block.dropResources(world.getBlockState(pos8), world, posForDrop, null);
                                world.destroyBlock(pos8, false);
                                damage = damage + 1;
                            }
                            if (world.getBlockState(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ() - 1)).getBlock() != Blocks.AIR &&
                                    world.getBlockState(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ() - 1)).getBlock() != Blocks.BEDROCK) {
                                BlockPos pos9 = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ() - 1);
                                Block.dropResources(world.getBlockState(pos9), world, posForDrop, null);
                                world.destroyBlock(pos9, false);
                                damage = damage + 1;
                            }
                            item.hurtAndBreak(damage, player, (player1) -> player1.broadcastBreakEvent(EquipmentSlot.MAINHAND));
                        }

                    }
                }
            }
        }
    }
}
