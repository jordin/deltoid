package in.jord.deltoid.region;

import com.google.gson.annotations.SerializedName;
import in.jord.deltoid.utils.UnionUtilities;
import in.jord.deltoid.vector.Vec3;
import in.jord.deltoid.utils.MathUtilities;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CuboidRegion implements Region<CuboidRegion, Vec3> {
    /**
     * A {@link CuboidRegion} with coordinates <b>[0, 0, 0]</b> and volume <b>v = 0.0</b>.
     */
    public static final CuboidRegion ORIGIN = new CuboidRegion(Vec3.ORIGIN, Vec3.ORIGIN);

    /**
     * The lower bounds of this {@link CuboidRegion}.
     * Calculated by <b>min = Math.min(<i>pos1</i>, <i>pos2</i>)</b>
     *
     * @serial
     */
    @SerializedName("min")
    public final Vec3 min;

    /**
     * The upper bounds of this {@link CuboidRegion}.
     * Calculated by <b>max = Math.max(<i>pos1</i>, <i>pos2</i>)</b>.
     *
     * @serial
     */
    @SerializedName("max")
    public final Vec3 max;

    /**
     * The original first position of this {@link CuboidRegion}.
     *
     * @serial
     */
    @SerializedName("pos1")
    public final Vec3 pos1;

    /**
     * The original second position of this {@link CuboidRegion}.
     *
     * @serial
     */
    @SerializedName("pos2")
    public final Vec3 pos2;

    /**
     * The size of this {@link CuboidRegion}.
     * Calculated by <b>dimensions = max - min</b>
     *
     * @serial
     */
    @SerializedName("dimensions")
    public final Vec3 dimensions;

    /**
     * The total volume enclosed in this {@link CuboidRegion}.
     *
     * @serial
     */
    @SerializedName("volume")
    private final double volume;

    /**
     * The total surface area of this {@link CuboidRegion}.
     *
     * @serial
     */
    @SerializedName("surface_area")
    private final double surfaceArea;

    /**
     * A {@link List} of all of the {@link Vec3}s enclosed in this {@link CuboidRegion}.
     *
     * @serial
     */
    private List<Vec3> enclosedPoints;

    /**
     * Constructs a newly allocated {@link CuboidRegion} object.
     *
     * @param pos1 the first position of this {@link CuboidRegion}.
     * @param pos2 the second position of this {@link CuboidRegion}.
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
     * Returns the total volume enclosed in this {@link CuboidRegion}.
     *
     * @return the total volume of this {@link CuboidRegion}
     */
    @Override
    public double volume() {
        return this.volume;
    }

    /**
     * Returns the total surface area of this {@link CuboidRegion}.
     *
     * @return the total surface area of this {@link CuboidRegion}
     */
    @Override
    public double surfaceArea() {
        return this.surfaceArea;
    }

    /**
     * Returns {@code true} if the {@link CuboidRegion} has
     * a non-zero volume, {@code false} otherwise.
     *
     * @return {@code true} if the {@link CuboidRegion} exists, {@code false} otherwise
     */
    @Override
    public boolean exists() {
        return this.volume != 0;
    }

    /**
     * Returns {@code true} if the {@link CuboidRegion}
     * contains <b>location</b> {@code false} otherwise.
     *
     * @param location the {@link Vec3}to consider.
     * @return {@code true} if the {@link CuboidRegion} contains <b>location</b>, {@code false} otherwise
     */
    @Override
    public boolean contains(Vec3 location) {
        return (location.x >= min.x && location.x <= max.x)
                && (location.y >= min.y && location.y <= max.y)
                && (location.z >= min.z && location.z <= max.z);
    }

    /**
     * Returns a {@link List} of all of the {@link Vec3}s enclosed in this {@link CuboidRegion}.
     *
     * @return the {@link List} of the {@link Vec3}s
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
     * Creates the smallest possible {@link CuboidRegion}
     * that fully encloses this {@link CuboidRegion} and <b>region</b>.
     *
     * @param region the other {@link CuboidRegion} to be considered
     * @return the {@link CuboidRegion}
     */
    @Override
    public CuboidRegion union(CuboidRegion region) {
        return UnionUtilities.union(this, region);
    }

    /**
     * Returns a new {@link CuboidRegion} translated by <b>offset</b>.
     *
     * @param offset the amount to translate this {@link CuboidRegion} by.
     * @return the new {@link CuboidRegion}
     */
    @Override
    public CuboidRegion offset(Vec3 offset) {
        return new CuboidRegion(pos1.add(offset), pos2.add(offset));
    }

    /**
     * Returns a new {@link CuboidRegion} expanded by <b>expansion</b>.
     *
     * @param expansion the amount to expand this {@link CuboidRegion} by.
     * @return the new {@link CuboidRegion}
     */
    public CuboidRegion expand(Vec3 expansion) { // TODO: preserve pos1 and pos2
        Vec3 newMin = new Vec3(min.x - expansion.x, min.y - expansion.y, min.z - expansion.z);
        Vec3 newMax = new Vec3(max.x + expansion.x, max.y + expansion.y, max.z + expansion.z);

        return new CuboidRegion(newMin, newMax);
    }

    /**
     * Returns a new {@link CuboidRegion} expanded in all directions by <b>amount</b>.
     *
     * @param expansion the amount to expand this {@link CuboidRegion} by.
     * @return the new {@link CuboidRegion}
     */
    public CuboidRegion expand(double expansion) { // TODO: preserve pos1 and pos2
        Vec3 newMin = new Vec3(min.x - expansion, min.y - expansion, min.z - expansion);
        Vec3 newMax = new Vec3(max.x + expansion, max.y + expansion, max.z + expansion);

        return new CuboidRegion(newMin, newMax);
    }

    /**
     * Compares this {@link CuboidRegion} to the specified object.  The result is {@code
     * true} if and only if the argument is not {@code null} and is a {@link CuboidRegion}
     * object that represents the same rotation angles as this {@link CuboidRegion}.
     *
     * @param other the object to compare this {@link CuboidRegion} against
     * @return {@code true} if the given object represents a {@link CuboidRegion}
     * equivalent to this {@link CuboidRegion}, {@code false} otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        CuboidRegion that = (CuboidRegion) other;
        return Objects.equals(min, that.min) &&
                Objects.equals(max, that.max) &&
                Objects.equals(dimensions, that.dimensions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(min, max, dimensions);
    }
}
