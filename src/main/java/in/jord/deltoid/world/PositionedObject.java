package in.jord.deltoid.world;

import in.jord.deltoid.vector.Vec3;

public class PositionedObject<T> {
    private Vec3 position;
    private T object;

    public PositionedObject(Vec3 position, T object) {
        this.position = position;
        this.object = object;
    }

    public Vec3 getPosition() {
        return this.position;
    }

    public T getObject() {
        return this.object;
    }
}
