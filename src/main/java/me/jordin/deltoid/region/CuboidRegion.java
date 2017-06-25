package me.jordin.deltoid.region;

import com.google.gson.annotations.SerializedName;
import me.jordin.deltoid.utils.MathUtilities;
import me.jordin.deltoid.utils.UnionUtilities;
import me.jordin.deltoid.vector.Vec3;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Jordin on 6/19/2017.
 * Jordin is still best hacker.
 */
public class CuboidRegion implements Region<CuboidRegion, Vec3> {
    /**
     * A <b>CuboidRegion</b> with coordinates <b>[0, 0, 0]</b> and volume <b>v = 0.0</b>.
     */
    public static final CuboidRegion ORIGIN = new CuboidRegion(Vec3.ORIGIN, Vec3.ORIGIN);

    /**
     * The lower bounds of this <b>CuboidRegion</b>.
     * Calculated by <b>min = Math.min(<i>pos1</i>, <i>pos2</i>)</b>
     *
     * @serial
     */
    @SerializedName("min")
    public final Vec3 min;

    /**
     * The upper bounds of this <b>CuboidRegion</b>.
     * Calculated by <b>max = Math.max(<i>pos1</i>, <i>pos2</i>)</b>.
     *
     * @serial
     */
    @SerializedName("max")
    public final Vec3 max;

    /**
     * The original first position of this <b>CuboidRegion</b>.
     *
     * @serial
     */
    @SerializedName("pos1")
    public final Vec3 pos1;

    /**
     * The original second position of this <b>CuboidRegion</b>.
     *
     * @serial
     */
    @SerializedName("pos2")
    public final Vec3 pos2;

    /**
     * The size of this <b>CuboidRegion</b>.
     * Calculated by <b>dimensions = max - min</b>
     *
     * @serial
     */
    @SerializedName("dimensions")
    public final Vec3 dimensions;

    /**
     * The total volume enclosed in this <b>CuboidRegion</b>.
     *
     * @serial
     */
    @SerializedName("volume")
    private final double volume;

    /**
     * The total surface area of this <b>CuboidRegion</b>.
     *
     * @serial
     */
    @SerializedName("surface_area")
    private final double surfaceArea;

    /**
     * A <b>List</b> of all of the <b>Vec3</b>s enclosed in this <b>CuboidRegion</b>.
     *
     * @serial
     */
    private List<Vec3> enclosedPoints;

    /**
     * Constructs a newly allocated <b>CuboidRegion</b> object.
     *
     * @param pos1 the first position of this <b>CuboidRegion</b>.
     * @param pos2 the second position of this <b>CuboidRegion</b>.
     */
    public CuboidRegion(Vec3 pos1, Vec3 pos2) {
        this.pos1 = pos1;
        this.pos2 = pos2;

        this.min = new Vec3(Math.min(pos1.x, pos2.x), Math.min(pos1.y, pos2.y), Math.min(pos1.z, pos2.z));
        this.max = new Vec3(Math.max(pos1.x, pos2.x), Math.max(pos1.y, pos2.y), Math.max(pos1.z, pos2.z));

        this.dimensions = new Vec3(max.x - min.x, max.y - min.y, max.z - min.z);

        this.volume = this.dimensions.x * this.dimensions.y * this.dimensions.z;
        this.surfaceArea = 2 * (this.dimensions.x * this.dimensions.y + this.dimensions.y * this.dimensions.z + this.dimensions.x * this.dimensions.z);
    }

    /**
     * Returns the total volume enclosed in this <b>CuboidRegion</b>.
     *
     * @return the total volume of this <b>CuboidRegion</b>
     */
    @Override
    public double volume() {
        return this.volume;
    }

    /**
     * Returns the total surface area of this <b>CuboidRegion</b>.
     *
     * @return the total surface area of this <b>CuboidRegion</b>
     */
    @Override
    public double surfaceArea() {
        return this.surfaceArea;
    }

    /**
     * Returns {@code true} if the <b>CuboidRegion</b> has
     * a non-zero volume, {@code false} otherwise.
     *
     * @return {@code true} if the <b>CuboidRegion</b> exists, {@code false} otherwise
     */
    @Override
    public boolean exists() {
        return this.volume != 0;
    }

    /**
     * Returns {@code true} if the <b>CuboidRegion</b>
     * contains <b>location</b> {@code false} otherwise.
     *
     * @param location the <b>Vec3</b>to consider.
     * @return {@code true} if the <b>CuboidRegion</b> contains <b>location</b>, {@code false} otherwise
     */
    @Override
    public boolean contains(Vec3 location) {
        return (location.x >= min.x && location.x <= max.x)
                && (location.y >= min.y && location.y <= max.y)
                && (location.z >= min.z && location.z <= max.z);
    }

    /**
     * Returns a <b>List</b> of all of the <b>Vec3</b>s enclosed in this <b>CuboidRegion</b>.
     *
     * @return the <b>List</b> of the <b>Vec3</b>s
     */
    @Override
    public List<Vec3> enclosedPoints() {
        if (this.enclosedPoints == null) {
            int startX = MathUtilities.floor(min.x);
            int startY = MathUtilities.floor(min.y);
            int startZ = MathUtilities.floor(min.z);

            int endX = MathUtilities.floor(max.x);
            int endY = MathUtilities.floor(max.y);
            int endZ = MathUtilities.floor(max.z);

            Vec3[] points = new Vec3[(endX - startX + 1) * (endY - startY + 1) * (endZ - startZ + 1)];

            int i = 0;
            for (int x = startX; x <= endX; x++) {
                for (int y = startY; y <= endY; y++) {
                    for (int z = startZ; z <= endZ; z++) {
                        points[i] = new Vec3(x, y, z);
                        i++;
                    }
                }
            }
            this.enclosedPoints = Arrays.asList(points);
        }

        return this.enclosedPoints;
    }

    /**
     * Creates the smallest possible <b>CuboidRegion</b>
     * that fully encloses this <b>CuboidRegion</b> and <b>CuboidRegion</b>.
     *
     * @param region the other <b>CuboidRegion</b> to be considered
     * @return the <b>CuboidRegion</b>
     */
    @Override
    public CuboidRegion union(CuboidRegion region) {
        return UnionUtilities.union(this, region);
    }

    /**
     * Returns a new <b>CuboidRegion</b> translated by <b>offset</b>.
     *
     * @return the new <b>CuboidRegion</b>
     */
    @Override
    public CuboidRegion offset(Vec3 offset) {
        return new CuboidRegion(pos1.add(offset), pos2.add(offset));
    }

    /**
     * Returns a new <b>CuboidRegion</b> expanded by <b>expansion</b>.
     *
     * @return the new <b>CuboidRegion</b>
     */
    public CuboidRegion expand(Vec3 expansion) { // TODO: preserve pos1 and pos2
        Vec3 newMin = new Vec3(min.x - expansion.x, min.y - expansion.y, min.z - expansion.z);
        Vec3 newMax = new Vec3(max.x + expansion.x, max.y + expansion.y, max.z + expansion.z);

        return new CuboidRegion(newMin, newMax);
    }

    /**
     * Returns a new <b>CuboidRegion</b> expanded in all directions by <b>amount</b>.
     *
     * @return the new <b>CuboidRegion</b>
     */
    public CuboidRegion expand(double amount) { // TODO: preserve pos1 and pos2
        Vec3 newMin = new Vec3(min.x - amount, min.y - amount, min.z - amount);
        Vec3 newMax = new Vec3(max.x + amount, max.y + amount, max.z + amount);

        return new CuboidRegion(newMin, newMax);
    }
}
