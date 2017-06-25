package me.jordin.deltoid.vector;

/**
 * Created by Jordin on 6/19/2017.
 * Jordin is still best hacker.
 */
public interface Vector<V> {
    /**
     * Returns the magnitude of the <b>Vector</b>.
     *
     * @return the magnitude of the <b>Vector</b>
     */
    double length();

    /**
     * Returns the manhattan (taxicab) length of the <b>Vector</b>.
     *
     * @return the manhattan length of the <b>Vector</b>
     */
    double manhattan();

    /**
     * Breaks this <b>Vector</b> into its underlying components.
     *
     * @return the components of this <b>Vector</b>
     */
    double[] components();

    /**
     * Returns the unit <b>Vector</b> parallel to this <b>Vector</b>.
     *
     * @return the <b>Vector</b>
     */
    V normalize();

    /**
     * Returns a scalar multiple of the unit <b>Vector</b> parallel to this <b>Vector</b>.
     *
     * @param length the desired length of the <b>Vector</b>.
     * @return the <b>Vector</b>
     */
    V normalize(double length);

    /**
     * Returns a scalar multiple of this <b>Vector</b>.
     *
     * @param scaleFactor the desired scale factor for the <b>Vector</b>.
     * @return the <b>Vector</b>
     */
    V scale(double scaleFactor);

    /**
     * Returns the <b>Vector</b> that is the sum of this <b>Vector</b> and {@code addend}.
     *
     * @param addend the <b>Vector</b> to be added to this <b>Vector</b>.
     * @return the <b>Vector</b>
     */
    V add(V addend);

    /**
     * Returns the <b>Vector</b> that is the difference of this <b>Vector</b> and {@code subtrahend}.
     *
     * @param subtrahend the <b>Vector</b> to be subtracted from this <b>Vector</b>.
     * @return the <b>Vector</b>
     */
    V subtract(V subtrahend);

    /**
     * Returns the <b>Vector</b> that is this <b>Vector</b> with floored coordinates.
     *
     * @return the <b>Vector</b>
     */
    V floor();

    /**
     * Returns the <b>Vector</b> that is this <b>Vector</b> with ceilinged coordinates.
     *
     * @return the <b>Vector</b>
     */
    V ceil();

    /**
     * Returns the <b>Vector</b> that is anti-parallel to this <b>Vector</b>.
     *
     * @return the <b>Vector</b>
     */
    V reverse();
}
