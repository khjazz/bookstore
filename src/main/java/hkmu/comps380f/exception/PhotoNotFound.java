package hkmu.comps380f.exception;

public class PhotoNotFound extends Throwable {
    public PhotoNotFound(String name) {
            super("Book: " + name + " does not have a cover photo.");
    }
}
