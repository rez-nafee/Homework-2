//Rezvan Nafee
//112936468
//Recitation Section: 04

/**
 * This class represents a Train that is approaching the station with basic information such as the train number, it's
 * destination, when the train is going to arrive in the station, and the time the train will wait in the station before
 * it departs the station.
 *
 * @author Rezvan Nafee
 * @ID 112936468
 * @Recitation Section 04
 */

public class Train {

    private Train next;
    private Train prev;
    private int trainNumber;
    private String destination;
    private int arrivalTime;
    private int transferTime;

    /**
     * This is a constructor that creates a generic Train with it's respective initial values.
     */
    public Train() {
    }

    /**
     * This is a constructor that makes a specified Train object with its Train number, destination, arrival time, and
     * transfer time.
     *
     * @param trainNumber  The number of the Train arriving in the Station.
     * @param destination  The place the Train will be going to.
     * @param arrivalTime  The time when the train arrives in 24-hour format.
     * @param transferTime The time the train will remain in the station before it proceeds to go the
     *                     respective destination it was scheduled for.
     */
    public Train(int trainNumber, String destination, int arrivalTime, int transferTime) {
        this.trainNumber = trainNumber;
        this.destination = destination;
        this.arrivalTime = arrivalTime;
        this.transferTime = transferTime;
    }

    /**
     * Returns the arrival time of the Train.
     *
     * @return Returns arrivalTime.
     */
    public int getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Returns the train number of the Train.
     *
     * @return Returns trainNumber.
     */
    public int getTrainNumber() {
        return trainNumber;
    }

    /**
     * Returns the transfer time of the Train.
     *
     * @return Returns transferTime.
     */
    public int getTransferTime() {
        return transferTime;
    }

    /**
     * Returns the destination of the Train.
     *
     * @return Returns destination.
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Returns the next Train in the Station.
     *
     * @return Returns next.
     */
    public Train getNext() {
        return next;
    }

    /**
     * Returns the previous Train in the Station.
     *
     * @return Returns prev.
     */
    public Train getPrev() {
        return prev;
    }

    /**
     * Sets the arrival time of the train to a specified time.
     *
     * @param arrivalTime The time in 24-hour format of when the Train will arrive in the Station.
     */
    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * Sets the destination of the Train to a specified destination.
     *
     * @param destination The destination of the Train.
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * Sets the next Train in the Station.
     *
     * @param next The next train in the Station.
     */
    public void setNext(Train next) {
        this.next = next;
    }

    /**
     * Sets the previous Train in the Station.
     *
     * @param prev The previous Train in the Station.
     */
    public void setPrev(Train prev) {
        this.prev = prev;
    }

    /**
     * Sets the train number of the Train to a specified number.
     *
     * @param trainNumber The train number of the Train.
     */
    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    /**
     * Sets the transfer Time of the Train to a specified amount of time.
     *
     * @param transferTime The time the train will remain in the station.
     */
    public void setTransferTime(int transferTime) {
        this.transferTime = transferTime;
    }

    /**
     * Checks if another Objects is of the type of Train and contains the same number as the current Train object.
     *
     * @param o Object that will be compared to the current Train.
     * @return Returns true if the Object is a Train and/or is the same as the current Train.
     * Returns false if the Object is not a Train and/or is not the same as the current Train.
     */
    public boolean equals(Object o) {
        if (!(o instanceof Train)) {
            return false;
        }
        Train temp = (Train) o;
        return this.trainNumber == temp.trainNumber;
    }

    /**
     * Returns a String representation of Train, specifying the train number, the destination of the Train, the arrival
     * time of the Train, and the departure time of the Train.
     *
     * @return The String representation of the Train object.
     */
    public String toString() {
        String msg = "Selected Train:";
        String trainNumber = "\tTrain Number: " + this.trainNumber;
        String destination = "\tTrain Destination: " + this.destination;
        String arrivalTime = "\tArrival Time: " + String.format("%04d", this.arrivalTime);
        String departureTime = "\tDeparture Time: " + String.format("%04d", this.transferTime);
        return msg + "\n" + trainNumber + "\n" + destination + "\n" + arrivalTime + "\n" + departureTime;
    }

    /**
     * Returns true if the inputted arrival time inputted by the user is valid according to the 24-hour format. If not,
     * the method throws an error to the user.
     *
     * @param str The inputted time by the user to be checked if it's in a 24-hour format.
     * @return Returns true if the inputted information is correctly formatted in 24-hour format or it throws an error
     * if the inputted information is not correctly formatted.
     * @throws InvalidTime
     */
    public static boolean checkTime(String str) throws InvalidTime {
        int abc;
        try {
            abc = Integer.parseInt(str);
        } catch (NumberFormatException ex) {
            throw new InvalidTime("");
        }
        if (!(abc < 2400 && abc >= 0))
            throw new InvalidTime("");
        if (str.length() != 4)
            throw new InvalidTime("");
        if (Integer.parseInt(str.substring(2)) >= 60)
            throw new InvalidTime("");
        return true;
    }
}
