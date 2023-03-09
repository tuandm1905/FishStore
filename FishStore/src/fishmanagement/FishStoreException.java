package fishmanagement;

/**
 *
 * @author Dang Minh Tuan SE150430
 */
public class FishStoreException extends Exception {

    /**
     * Creates new ComicBookException
     *
     * @param message
     */
    public FishStoreException(String message) {
        super(message);
    }

    /**
     * Gets the exception message
     *
     * @param
     */
    @Override
    public String getMessage() {
        return "ComicBookException: " + super.getMessage();
    }
}
