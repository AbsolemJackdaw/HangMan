package subaraki.hangman.util;

public class EntityHangException {
    private final String entitytype;
    private final double offset;

    public EntityHangException(String entitytype, double offset) {
        this.entitytype=entitytype;
        this.offset=offset;
    }

    public double getOffset() {
        return offset;
    }

    public String getEntitytype() {
        return entitytype;
    }
}
