package in.jord.deltoid.region;

import com.fasterxml.jackson.annotation.JsonProperty;
import in.jord.deltoid.utils.MathUtilities;
import in.jord.deltoid.utils.UnionUtilities;
import in.jord.deltoid.vector.Vec2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class RectangleRegion implements Region<RectangleRegion, Vec2> {
    /**
     * A {@link RectangleRegion} with coordinates <b>[0, 0]</b> and area <b>a = 0.0</b>.
     */
    public static final RectangleRegion ORIGIN = new RectangleRegion(Vec2.ORIGIN, Vec2.ORIGIN);

    /**
     * This is used to represent an invalid or "null" value being returned from a function or similar.
     */
    public static final RectangleRegion INVALID = new RectangleRegion(Vec2.INVALID, Vec2.INVALID);

    /**
     * The lower bounds of this {@link RectangleRegion}.
     * Calculated by <b>min = Math.min(<i>pos1</i>, <i>pos2</i>)</b>
     *
     * @serial
     */
    @JsonProperty("min")
    public final Vec2 min;

    /**
     * The upper bounds of this {@link RectangleRegion}.
     * Calculated by <b>max = Math.max(<i>pos1</i>, <i>pos2</i>)</b>.
     *
     * @serial
     */
    @JsonProperty("max")
    public final Vec2 max;

    /**
     * The original first position of this {@link RectangleRegion}.
     *
     * @serial
     */
    @JsonProperty("pos1")
    public final Vec2 pos1;

    /**
     * The original second position of this {@link RectangleRegion}.
     *
     * @serial
     */
    @JsonProperty("pos2")
    public final Vec2 pos2;

    /**
     * The size of this {@link RectangleRegion}.
     * Calculated by <b>dimensions = max - min</b>
     *
     * @serial
     */
    @JsonProperty("dimensions")
    public final Vec2 dimensions;

    /**
     * The total surface area of this {@link RectangleRegion}.
     *
     * @serial
     */
    @JsonProperty("area")
    private final double surfaceArea;

    /**
     * A {@link List} of all of the {@link Vec2}s enclosed in this {@link RectangleRegion}.
     *
     * @serial
     */
    private transient List<Vec2> enclosedPoints;

    /**
     * Constructs a newly allocated {@link RectangleRegion} object.
     *
     * @param pos1 the first position of this {@link RectangleRegion}.
     * @param pos2 the second position of this {@link RectangleRegion}.
     */
    public RectangleRegion(Vec2 pos1, Vec2 pos2) {
        this.pos1 = pos1;
        this.pos2 = pos2;

        if (!pos1.isValid() || !pos2.isValid()) {
            this.min = Vec2.INVALID;
            this.max = Vec2.INVALID;

            this.dimensions = Vec2.INVALID;

            this.surfaceArea = 0;
            return;
        }

        this.min = new Vec2(Math.min(pos1.x, pos2.x), Math.min(pos1.y, pos2.y));
        this.max = new Vec2(Math.max(pos1.x, pos2.x), Math.max(pos1.y, pos2.y));

        this.dimensions = new Vec2(this.max.x - this.min.x, this.max.y - this.min.y);

        this.surfaceArea = this.dimensions.x * this.dimensions.y;
    }

    /**
     * Returns the total volume enclosed in this {@link RectangleRegion}.
     *
     * @return <b>0.0</b>, Rectangles do not have a volume!
     */
    @Override
    public double volume() {
        return 0;
    }

    /**
     * Returns the total area of this {@link RectangleRegion}.
     *
     * @return the total area of this {@link RectangleRegion}
     */
    @Override
    public double surfaceArea() {
        return this.surfaceArea;
    }

    /**
     * Returns {@code true} if the {@link RectangleRegion} has
     * a non-zero area, {@code false} otherwise.
     *
     * @return {@code true} if the {@link RectangleRegion} exists, {@code false} otherwise
     */
    @Override
    public boolean exists() {
        return this.surfaceArea != 0;
    }

    /**
     * Returns {@code true} if the {@link RectangleRegion}
     * contains <b>location</b> {@code false} otherwise.
     *
     * @param location the {@link Vec2}to consider.
     * @return {@code true} if the {@link RectangleRegion} contains <b>location</b>, {@code false} otherwise
     */
    @Override
    public boolean contains(Vec2 location) {
        return (location.x >= this.min.x && location.x <= this.max.x)
                && (location.y >= this.min.y && location.y <= this.max.y);
    }

    /**
     * Returns a {@link List} of all of the {@link Vec2}s enclosed in this {@link RectangleRegion}.
     *
     * @return the {@link List} of the {@link Vec2}s
     */
    @Override
    public List<Vec2> enclosedPoints() {
        if (this.enclosedPoints == null) {
            int startX = MathUtilities.floor(this.min.x);
            int startY = MathUtilities.floor(this.min.y);

            int endX = MathUtilities.floor(this.max.x);
            int endY = MathUtilities.floor(this.max.y);

            Vec2[] points = new Vec2[(endX - startX + 1) * (endY - startY + 1)];

            int i = 0;
            for (int x = startX; x <= endX; x++) {
                for (int y = startY; y <= endY; y++) {
                    points[i] = new Vec2(x, y);
                    i++;
                }
            }

            this.enclosedPoints = Arrays.asList(points);
        }

        return Collections.unmodifiableList(this.enclosedPoints);
    }

    /**
     * Creates the smallest possible {@link RectangleRegion}
     * that fully encloses this {@link RectangleRegion} and <b>region</b>.
     *
     * @param region the other {@link RectangleRegion} to be considered
     * @return the {@link RectangleRegion}
     */
    @Override
    public RectangleRegion union(RectangleRegion region) {
        return UnionUtilities.union(this, region);
    }

    /**
     * Returns a new {@link RectangleRegion} translated by <b>offset</b>.
     *
     * @param offset the amount to translate this {@link RectangleRegion} by.
     * @return the new {@link RectangleRegion}
     */
    @Override
    public RectangleRegion offset(Vec2 offset) {
        return new RectangleRegion(this.pos1.add(offset), this.pos2.add(offset));
    }

    /**
     * Returns a new {@link RectangleRegion} expanded by <b>expansion</b>.
     *
     * @param expansion the amount to expand this {@link RectangleRegion} by.
     * @return the new {@link RectangleRegion}
     */
    public RectangleRegion expand(Vec2 expansion) { // TODO: preserve pos1 and pos2
        Vec2 newMin = new Vec2(this.min.x - expansion.x, this.min.y - expansion.y);
        Vec2 newMax = new Vec2(this.max.x + expansion.x, this.max.y + expansion.y);

        return new RectangleRegion(newMin, newMax);
    }

    /**
     * Returns a new {@link RectangleRegion} expanded in all directions by <b>amount</b>.
     *
     * @param expansion the amount to expand this {@link RectangleRegion} by.
     * @return the new {@link RectangleRegion}
     */
    public RectangleRegion expand(double expansion) { // TODO: preserve pos1 and pos2
        Vec2 newMin = new Vec2(this.min.x - expansion, this.min.y - expansion);
        Vec2 newMax = new Vec2(this.max.x + expansion, this.max.y + expansion);

        return new RectangleRegion(newMin, newMax);
    }

    /**
     * Compares this {@link RectangleRegion} to the specified object.  The result is {@code
     * true} if and only if the argument is not {@code null} and is a {@link RectangleRegion}
     * object that represents the same rotation angles as this {@link RectangleRegion}.
     *
     * @param object the object to compare this {@link RectangleRegion} against
     * @return {@code true} if the given object represents a {@link RectangleRegion}
     * equivalent to this {@link RectangleRegion}, {@code false} otherwise
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || this.getClass() != object.getClass()) return false;
        RectangleRegion other = (RectangleRegion) object;
        return Objects.equals(this.min, other.min) &&
               Objects.equals(this.max, other.max) &&
               Objects.equals(this.dimensions, other.dimensions);
    }

    @Override
    public int hashCode() {
        return this.min.hashCode() * 31 + this.max.hashCode();
    }
}
