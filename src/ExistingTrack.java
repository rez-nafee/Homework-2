//Rezvan Nafee
//112936468
//Recitation 04

/**
 * This is a custom error class that is made to help manage the Tracks in the Station. When adding a new Track to the
 * Station and there is already a Track with the same track number, the error is thrown and is displayed on the console
 * to inform the user.
 *
 * @author Rezvan Nafee
 * @ID 112936468
 * @Recitation Section 04
 */
public class ExistingTrack extends Exception {
    /**
     * Constructs the error message for the custom error.
     *
     * @param error The custom error message to be displayed on the console.
     */
    public ExistingTrack(String error) {
        super(error);
    }
}
