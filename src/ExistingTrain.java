//Rezvan Nafee
//112936468
//Recitation 04

/**
 * This is a custom error class that is made to help manage the Trains on the Track. When adding a new Train to the
 * Track and there is already a Train with the same train number and/or is conflicting with a Train already schedule,
 * the error is thrown and is displayed on the console to inform the user.
 *
 * @author Rezvan Nafee
 * @ID 112936468
 * @Recitation Section 04
 */
public class ExistingTrain extends Exception {
    /**
     * Constructs the error message for the custom error.
     *
     * @param error The custom error message to be displayed on the console.
     */
    public ExistingTrain(String error) {
        super(error);
    }
}
