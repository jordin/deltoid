package in.jord.deltoid.region;

import com.fasterxml.jackson.annotation.JsonProperty;
import in.jord.deltoid.utils.UnionUtilities;
import in.jord.deltoid.vector.Vec3;

import java.util.List;
import java.util.Objects;

public class SphereRegion implements Region<SphereRegion, Vec3> {
    /**
     * A {@link SphereRegion} with coordinates <b>[0, 0, 0]</b> and radius <b>r = 0.0</b>.
     */
    public static final SphereRegion ORIGIN = new SphereRegion(Vec3.ORIGIN, 0);

    /**
     * This is used to represent an invalid or "null" value being returned from a function or similar.
     */
    public static final SphereRegion INVALID = new SphereRegion(Vec3.INVALID, 0);

    private static double TWO_TAU = 4.0 * Math.PI;
    private static double TWO_OVER_THREE_TIMES_TAU = 4.0 / 3.0 * Math.PI;

    /**
     * The location of the centre of this {@link SphereRegion}.
     *
     * @serial
     */
    @JsonProperty("centre")
    public final Vec3 centre;

    /**
     * The radius of this {@link SphereRegion}.
     *
     * @serial
     */
    @JsonProperty("radius")
    public final double radius;

    /**
     * The total volume enclosed in this {@link SphereRegion}.
     *
     * @serial
     */
    @JsonProperty("volume")
    private double volume;

    /**
     * The total surface area of this {@link SphereRegion}.
     *
     * @serial
     */
    @JsonProperty("surface_area")
    private double surfaceArea;

    public SphereRegion(Vec3 centre, double radius) {
        this.centre = centre;
        this.radius = radius;

        if (centre.isValid()) {
            this.volume = TWO_OVER_THREE_TIMES_TAU * radius * radius * radius;
            this.surfaceArea = TWO_TAU * radius * radius;
        } else {
            this.volume = 0;
            this.surfaceArea = 0;
        }
    }

    /**
     * Returns the total volume enclosed in this {@link SphereRegion}.
     *
     * @return the total volume of this {@link SphereRegion}
     */
    @Override
    public double volume() {
        return this.volume;
    }

    /**
     * Returns the total surface area of this {@link SphereRegion}.
     *
     * @return the total surface area of this {@link SphereRegion}
     */
    @Override
    public double surfaceArea() {
        return this.surfaceArea;
    }

    /**
     * Returns {@code true} if the {@link SphereRegion} has
     * a non-zero volume, {@code false} otherwise.
     *
     * @return {@code true} if the {@link SphereRegion} exists, {@code false} otherwise
     */
    @Override
    public boolean exists() {
        return this.volume != 0;
    }

    /**
     * Returns {@code true} if the {@link SphereRegion}
     * contains <b>location</b> {@code false} otherwise.
     *
     * @param location the {@link Vec3} to consider.
     * @return {@code true} if the {@link SphereRegion} contains <b>location</b>, {@code false} otherwise
     */
    @Override
    public boolean contains(Vec3 location) {
        return this.centre.subtract(location.add(Vec3.CENTRE)).length() <= this.radius;
    }

    /**
     * Returns a {@link List} of all of the {@link Vec3}s enclosed in this {@link SphereRegion}.
     *
     * @return the {@link List} of the {@link Vec3}s
     */
    @Override
    public List<Vec3> enclosedPoints() {
        Vec3 bounds = new Vec3(this.radius, this.radius, this.radius);
        return UnionUtilities.overlap(new CuboidRegion(this.centre.add(bounds), this.centre.subtract(bounds)), this);
    }

    /**
     * Creates the smallest possible {@link SphereRegion}
     * that fully encloses this {@link SphereRegion} and <b>region</b>.
     *
     * @param region the other {@link SphereRegion} to be considered
     * @return the {@link SphereRegion}
     */
    @Override
    public SphereRegion union(SphereRegion region) {
        return UnionUtilities.union(this, region);
    }

    /**
     * Returns a new {@link SphereRegion} translated by <b>offset</b>.
     *
     * @return the new {@link SphereRegion}
     */
    @Override
    public SphereRegion offset(Vec3 offset) {
        return new SphereRegion(this.centre.add(offset), this.radius);
    }

    /**
     * Compares this {@link SphereRegion} to the specified object.  The result is {@code
     * true} if and only if the argument is not {@code null} and is a {@link SphereRegion}
     * object that represents the same rotation angles as this {@link SphereRegion}.
     *
     * @param other the object to compare this {@link SphereRegion} against
     * @return {@code true} if the given object represents a {@link SphereRegion}
     * equivalent to this {@link SphereRegion}, {@code false} otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        SphereRegion that = (SphereRegion) other;
        return Double.compare(that.radius, radius) == 0 &&
                Objects.equals(centre, that.centre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(centre, radius);
    }
}
