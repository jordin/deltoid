package in.jord.deltoid.region;

import com.fasterxml.jackson.annotation.JsonProperty;
import in.jord.deltoid.utils.MathUtilities;
import in.jord.deltoid.utils.UnionUtilities;
import in.jord.deltoid.vector.Vec3;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class CuboidRegion implements Region<CuboidRegion, Vec3> {
    /**
     * A {@link CuboidRegion} with coordinates <b>[0, 0, 0]</b> and volume <b>v = 0.0</b>.
     */
    public static final CuboidRegion ORIGIN = new CuboidRegion(Vec3.ORIGIN, Vec3.ORIGIN);

    /**
     * This is used to represent an invalid or "null" value being returned from a function or similar.
     */
    public static final CuboidRegion INVALID = new CuboidRegion(Vec3.INVALID, Vec3.INVALID);

    /**
     * The lower bounds of this {@link CuboidRegion}.
     * Calculated by <b>min = Math.min(<i>pos1</i>, <i>pos2</i>)</b>
     *
     * @serial
     */
    @JsonProperty("min")
    public final Vec3 min;

    /**
     * The upper bounds of this {@link CuboidRegion}.
     * Calculated by <b>max = Math.max(<i>pos1</i>, <i>pos2</i>)</b>.
     *
     * @serial
     */
    @JsonProperty("max")
    public final Vec3 max;

    /**
     * The original first position of this {@link CuboidRegion}.
     *
     * @serial
     */
    @JsonProperty("pos1")
    public final Vec3 pos1;

    /**
     * The original second position of this {@link CuboidRegion}.
     *
     * @serial
     */
    @JsonProperty("pos2")
    public final Vec3 pos2;

    /**
     * The size of this {@link CuboidRegion}.
     * Calculated by <b>dimensions = max - min</b>
     *
     * @serial
     */
    @JsonProperty("dimensions")
    public final Vec3 dimensions;

    /**
     * The total volume enclosed in this {@link CuboidRegion}.
     *
     * @serial
     */
    @JsonProperty("volume")
    private final double volume;

    /**
     * The total surface area of this {@link CuboidRegion}.
     *
     * @serial
     */
    @JsonProperty("surface_area")
    private final double surfaceArea;

    /**
     * A {@link List} of all of the {@link Vec3}s enclosed in this {@link CuboidRegion}.
     *
     * @serial
     */
    private transient List<Vec3> enclosedPoints;

    /**
     * Constructs a newly allocated {@link CuboidRegion} object.
     *
     * @param pos1 the first position of this {@link CuboidRegion}.
     * @param pos2 the second position of this {@link CuboidRegion}.
     */
    public CuboidRegion(Vec3 pos1, Vec3 pos2) {
        this.pos1 = pos1;
        this.pos2 = pos2;

        if (!pos1.isValid() || !pos2.isValid()) {
            this.min = Vec3.INVALID;
            this.max = Vec3.INVALID;

            this.dimensions = Vec3.INVALID;

            this.volume = 0;
            this.surfaceArea = 0;
            return;
        }

        this.min = new Vec3(Math.min(pos1.x, pos2.x), Math.min(pos1.y, pos2.y), Math.min(pos1.z, pos2.z));
        this.max = new Vec3(Math.max(pos1.x, pos2.x), Math.max(pos1.y, pos2.y), Math.max(pos1.z, pos2.z));

        this.dimensions = new Vec3(this.max.x - this.min.x, this.max.y - this.min.y, this.max.z - this.min.z);

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
        return (location.x >= this.min.x && location.x <= this.max.x)
                && (location.y >= this.min.y && location.y <= this.max.y)
                && (location.z >= this.min.z && location.z <= this.max.z);
    }

    /**
     * Returns a {@link List} of all of the {@link Vec3}s enclosed in this {@link CuboidRegion}.
     *
     * @return the {@link List} of the {@link Vec3}s
     */
    @Override
    public List<Vec3> enclosedPoints() {
        if (this.enclosedPoints == null) {
            int startX = MathUtilities.floor(this.min.x);
            int startY = MathUtilities.floor(this.min.y);
            int startZ = MathUtilities.floor(this.min.z);

            int endX = MathUtilities.floor(this.max.x);
            int endY = MathUtilities.floor(this.max.y);
            int endZ = MathUtilities.floor(this.max.z);

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

        return Collections.unmodifiableList(this.enclosedPoints);
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
        return new CuboidRegion(this.pos1.add(offset), this.pos2.add(offset));
    }

    /**
     * Returns a new {@link CuboidRegion} expanded by <b>expansion</b>.
     *
     * @param expansion the amount to expand this {@link CuboidRegion} by.
     * @return the new {@link CuboidRegion}
     */
    public CuboidRegion expand(Vec3 expansion) { // TODO: preserve pos1 and pos2
        Vec3 newMin = new Vec3(this.min.x - expansion.x, this.min.y - expansion.y, this.min.z - expansion.z);
        Vec3 newMax = new Vec3(this.max.x + expansion.x, this.max.y + expansion.y, this.max.z + expansion.z);

        return new CuboidRegion(newMin, newMax);
    }

    /**
     * Returns a new {@link CuboidRegion} expanded in all directions by <b>amount</b>.
     *
     * @param expansion the amount to expand this {@link CuboidRegion} by.
     * @return the new {@link CuboidRegion}
     */
    public CuboidRegion expand(double expansion) { // TODO: preserve pos1 and pos2
        Vec3 newMin = new Vec3(this.min.x - expansion, this.min.y - expansion, this.min.z - expansion);
        Vec3 newMax = new Vec3(this.max.x + expansion, this.max.y + expansion, this.max.z + expansion);

        return new CuboidRegion(newMin, newMax);
    }

    /**
     * Compares this {@link CuboidRegion} to the specified object.  The result is {@code
     * true} if and only if the argument is not {@code null} and is a {@link CuboidRegion}
     * object that represents the same rotation angles as this {@link CuboidRegion}.
     *
     * @param object the object to compare this {@link CuboidRegion} against
     * @return {@code true} if the given object represents a {@link CuboidRegion}
     * equivalent to this {@link CuboidRegion}, {@code false} otherwise
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || this.getClass() != object.getClass()) return false;
        CuboidRegion other = (CuboidRegion) object;
        return Objects.equals(this.min, other.min) &&
               Objects.equals(this.max, other.max) &&
               Objects.equals(this.dimensions, other.dimensions);
    }

    @Override
    public int hashCode() {
        return this.min.hashCode() * 31 + this.max.hashCode();
    }
}
