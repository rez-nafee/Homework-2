//Rezvan Nafee
//112936468
//Recitation 04

/**
 * This class represents a custom error that is created to help manage the arrival time formatting when adding a Train
 * to a Track. It is thrown when the arrival time given is not correctly formatted in 24-hour format.
 *
 * @author Rezvan Nafee
 * @ID 112936468
 * @Recitation Section 04
 */
public class InvalidTime extends Exception {
    /**
     * Constructs the error message for the custom error.
     *
     * @param error The custom error message to be displayed on the console.
     */
    public InvalidTime(String error) {
        super(error);
    }
}
