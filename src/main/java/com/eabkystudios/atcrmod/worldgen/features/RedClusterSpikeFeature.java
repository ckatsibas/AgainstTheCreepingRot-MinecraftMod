package com.eabkystudios.atcrmod.worldgen.features;

import com.eabkystudios.atcrmod.block.ATCRBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.phys.Vec3;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class RedClusterSpikeFeature extends Feature<NoneFeatureConfiguration> {
    private static final Direction[] DIRECTIONS = Direction.values();

    public RedClusterSpikeFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();
        int wanted = 1 + random.nextInt(4);
        int spawned = 0;
        Set<BlockPos> usedStarts = new HashSet<>();

        // Try nearby positions so several spikes can grow near each other.
        for (int attempt = 0; attempt < wanted * 12 && spawned < wanted; attempt++) {
            int dx = random.nextInt(5) - 2; // -2 through +2
            int dy = random.nextInt(3) - 1; // -1 through +1
            int dz = random.nextInt(5) - 2; // -2 through +2

            BlockPos start = origin.offset(dx, dy, dz);

            if (!usedStarts.add(start) || !level.getBlockState(start).isAir()) {
                continue;
            }

            Direction supportDirection = findSupportDirection(level, start, random);
            if (supportDirection == null) {
                continue;
            }

            if (growSpike(level, start, supportDirection, random)) {
                spawned++;
            }
        }

        return spawned > 0;
    }

    private boolean growSpike(WorldGenLevel level, BlockPos origin, Direction supportDirection, RandomSource random) {
        Vec3 outwardNormal = new Vec3(
                -supportDirection.getStepX(),
                -supportDirection.getStepY(),
                -supportDirection.getStepZ()
        );

        Vec3 growthDirection = makeGrowthDirection(outwardNormal, supportDirection, random);
        int length = 6 + random.nextInt(11); // 6–16 blocks from anchor to tip
        double baseRadius = 1.1 + random.nextDouble() * 1.2;
        BlockState clusterState = ATCRBlocks.CLUSTER_OF_RED.get().defaultBlockState();
        Set<BlockPos> candidates = new HashSet<>();

        // Sample every half-block so angled sections overlap.
        int samples = length * 2;

        for (int sample = 0; sample <= samples; sample++) {
            double distance = length * sample / (double) samples;
            double progress = distance / length;
            double radius = Math.max(0.4, baseRadius * (1.0 - progress));

            double centerX = origin.getX() + 0.5 + growthDirection.x * distance;
            double centerY = origin.getY() + 0.5 + growthDirection.y * distance;
            double centerZ = origin.getZ() + 0.5 + growthDirection.z * distance;

            BlockPos center = BlockPos.containing(centerX, centerY, centerZ);
            int searchRadius = (int) Math.ceil(radius + 0.5);

            for (int dx = -searchRadius; dx <= searchRadius; dx++) {
                for (int dy = -searchRadius; dy <= searchRadius; dy++) {
                    for (int dz = -searchRadius; dz <= searchRadius; dz++) {
                        BlockPos pos = center.offset(dx, dy, dz);

                        double offsetX = pos.getX() + 0.5 - centerX;
                        double offsetY = pos.getY() + 0.5 - centerY;
                        double offsetZ = pos.getZ() + 0.5 - centerZ;

                        if (offsetX * offsetX + offsetY * offsetY + offsetZ * offsetZ > radius * radius) {
                            continue;
                        }

                        if (pos.getY() < level.getMinY() || pos.getY() >= level.getMaxY()) {
                            continue;
                        }

                        BlockState existing = level.getBlockState(pos);
                        boolean replaceable = existing.isAir() || (existing.canBeReplaced() && existing.getFluidState().isEmpty());

                        if (replaceable || existing.equals(clusterState)) {
                            candidates.add(pos.immutable());
                        }
                    }
                }
            }
        }

        // Keep only blocks connected face-to-face to the starting block.
        Set<BlockPos> connected = new HashSet<>();
        Queue<BlockPos> queue = new ArrayDeque<>();

        if (candidates.contains(origin)) {
            connected.add(origin);
            queue.add(origin);
        }

        while (!queue.isEmpty()) {
            BlockPos current = queue.remove();

            for (Direction direction : DIRECTIONS) {
                BlockPos neighbor = current.relative(direction);

                if (candidates.contains(neighbor) && connected.add(neighbor)) {
                    queue.add(neighbor);
                }
            }
        }

        boolean placedAnything = false;

        for (BlockPos pos : connected) {
            BlockState existing = level.getBlockState(pos);

            if (!existing.equals(clusterState) && (existing.isAir() || (existing.canBeReplaced() && existing.getFluidState().isEmpty()))) {
                level.setBlock(pos, clusterState, 2);
                placedAnything = true;
            }
        }

        return placedAnything;
    }

    /**
     * Finds an adjacent solid support block.
     * Floor or ceiling supports are preferred over wall supports.
     */
    private Direction findSupportDirection(WorldGenLevel level, BlockPos origin, RandomSource random) {
        List<Direction> verticalSupports = new ArrayList<>();
        List<Direction> wallSupports = new ArrayList<>();

        for (Direction direction : DIRECTIONS) {
            BlockPos supportPos = origin.relative(direction);
            BlockState state = level.getBlockState(supportPos);

            boolean isSolidSupport = !state.isAir()
                    && state.getFluidState().isEmpty()
                    && !state.canBeReplaced();

            if (!isSolidSupport) {
                continue;
            }

            if (direction.getAxis() == Direction.Axis.Y) {
                verticalSupports.add(direction);
            } else {
                wallSupports.add(direction);
            }
        }

        List<Direction> supports =
                verticalSupports.isEmpty() ? wallSupports : verticalSupports;

        if (supports.isEmpty()) {
            return null;
        }

        return supports.get(random.nextInt(supports.size()));
    }

    private Vec3 makeGrowthDirection(Vec3 outwardNormal, Direction supportDirection, RandomSource random) {
        // Floor support: grow upward with a random lean.
        if (supportDirection == Direction.DOWN) {
            return new Vec3(random.nextDouble() * 0.7 - 0.35, 1.0, random.nextDouble() * 0.7 - 0.35).normalize();
        }

        // Ceiling support: grow downward with a random lean.
        if (supportDirection == Direction.UP) {
            return new Vec3(random.nextDouble() * 0.7 - 0.35, -1.0, random.nextDouble() * 0.7 - 0.35).normalize();
        }

        // Wall support: grow into the cave, leaning upward or downward.
        double verticalSign = random.nextBoolean() ? 1.0 : -1.0;
        double verticalAmount = 0.65 + random.nextDouble() * 0.45;

        return outwardNormal.scale(1.0).add(0.0, verticalSign * verticalAmount, 0.0).add(random.nextDouble() * 0.20 - 0.10, random.nextDouble() * 0.20 - 0.10, random.nextDouble() * 0.20 - 0.10).normalize();
    }
}
