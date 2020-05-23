//Rezvan Nafee
//112936468
//Recitation 04

/**
 * This class represents a custom error to help manage the Trains that are being added to the Track. If the Train that
 * would like to be added do not follow the specified information, then this error will be thrown to inform the user
 * that the Train is invalid.
 *
 * @author Rezvan Nafee
 * @ID 112936468
 * @Recitation Section 04
 */
public class InvalidTrain extends Exception {
    /**
     * Constructs the error message for the custom error.
     *
     * @param error The custom error message to be displayed on the console.
     */
    public InvalidTrain(String error) {
        super(error);
    }
}
