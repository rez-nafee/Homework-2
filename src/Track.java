//Rezvan Nafee
//112936468
//Recitation 04

import java.text.DecimalFormat;

/**
 * This class represent a Tack in the Station that will manage the Trains that approach the station. The Track also has
 * it's own unique track number for Trains to arrive and depart from.
 *
 * @author Rezvan Nafee
 * @ID 112936468
 * @Recitation Section 04
 */
public class Track {

    /**
     * Creates a DecimalFormat to allow the program to display the utilization percentage rounded to the nearest 2
     * decimal places.
     */
    public DecimalFormat df = new DecimalFormat("0.00");
    private int countTrains;
    private Train head;
    private Train tail;
    private Train cursor;
    private Track next;
    private Track prev;
    private double utilizationRate;
    private int trackNumber;

    /**
     * This is a constructor that creates the a default Track with it's respective initial values.
     */
    public Track() {
    }

    /**
     * This is a constructor that creates a Track with a specified number to hold and manage trains that arrive in the
     * station.
     *
     * @param trackNumber
     */
    public Track(int trackNumber) {
        this.trackNumber = trackNumber;
    }

    /**
     * Returns the next Track in the Station
     *
     * @return Returns the next track.
     */
    public Track getNext() {
        return next;
    }

    /**
     * Returns the percentage of utilization of the day respective to its Track. In other words it is the total time of
     * Train waiting on the Track over the total minutes in a day.
     *
     * @return
     */
    public double getUtilizationRate() {
        return utilizationRate;
    }

    /**
     * Returns the track number of the Track object.
     *
     * @return The trackNumber.
     */
    public int getTrackNumber() {
        return trackNumber;
    }

    /**
     * Returns the previous Track in the Station
     *
     * @return Returns the previous track.
     */
    public Track getPrev() {
        return prev;
    }

    /**
     * Returns the selected Train on the Track.
     *
     * @return Returns cursor.
     */
    public Train getCursor() {
        return cursor;
    }

    /**
     * Returns the first Train on the Track.
     * Returns head.
     */
    public Train getHead() {
        return head;
    }

    /**
     * Returns the last Train on the Track.
     *
     * @return Returns tail.
     */
    public Train getTail() {
        return tail;
    }

    /**
     * Sets the cursor to point to a specified Train.
     *
     * @param cursor
     */
    public void setCursor(Train cursor) {
        this.cursor = cursor;
    }

    /**
     * Sets the head to point to a specified Train.
     *
     * @param head
     */
    public void setHead(Train head) {
        this.head = head;
    }

    /**
     * Sets the tail to point to a specified Train.
     *
     * @param tail
     */
    public void setTail(Train tail) {
        this.tail = tail;
    }

    /**
     * Sets the track number of the Track to a specified number.
     *
     * @param trackNumber
     */
    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }

    /**
     * Sets the utilization rate to a specified percentage.
     *
     * @param utilizationRate
     */
    public void setUtilizationRate(double utilizationRate) {
        this.utilizationRate = utilizationRate;
    }

    /**
     * Sets the previous Track to a specified Track.
     *
     * @param prev
     */
    public void setPrev(Track prev) {
        this.prev = prev;
    }

    /**
     * Sets the previous Track to a specified Track.
     *
     * @param next
     */
    public void setNext(Track next) {
        this.next = next;
    }

    /**
     * Returns the number of Trains on the Track.
     *
     * @return
     */
    public int getCountTrains() {
        return countTrains;
    }

    /**
     * Sets the number of Trains on the Track to a specified amount.
     *
     * @param countTrains
     */
    public void setCountTrains(int countTrains) {
        this.countTrains = countTrains;
    }

    /**
     * Adds a specified Train to the Track. If there are no Trains currently on the Track, the Train is added to Track
     * and is set as the head,cursor, and tail. If there is already a Train on the Track, the method adds it to the end
     * of the Train list, but it also makes sure that the Train doesn't already exists on the Track or if it interferes
     * with an already scheduled train on the Track, if it does, than the program prompts the user with an error
     * message.
     *
     * @param newTrain The train that will be determined if it can be added to the Track or not.
     * @throws ExistingTrain
     */
    public void addTrain(Train newTrain) throws ExistingTrain {
        if (countTrains == 0) {
            this.head = newTrain;
            this.tail = newTrain;
            countTrains++;
        } else if (conflictingTime(newTrain)) {
            throw new ExistingTrain("Train not added: There is a Train already scheduled on Track " +
                    this.getTrackNumber() + " at that time! ");
        } else if (exists(newTrain)) {
            throw new ExistingTrain("Train not added: Train already exists on Track " + this.trackNumber + "!");
        } else {
            Train train = getHead();
            while (train.getNext() != null)
                train = train.getNext();
            newTrain.setPrev(train);
            train.setNext(newTrain);
            this.tail = newTrain;
            countTrains++;
        }
        this.cursor = newTrain;
        calculateUtilizationRate();
    }

    /**
     * Prints the selected Train's information to the console. If there is nothing selected or specified in the cursor
     * data field, then the program prompts the user with an error message.
     *
     * @throws Exception
     */
    public void printSelectedTrain() throws Exception {
        if (this.cursor == null) {
            throw new Exception("Printing Train Error: There is no Train on Track " + this.trackNumber + "!");
        } else {
            System.out.println("\n" + cursor.toString());
        }
    }

    /**
     * Removes the selected Train on the track and returns the removed Train to display to the user which Train was
     * removed. If there are no Trains on the Track, it returns a null value which prompts the user there is nothing
     * to be removed. Else, the program removes the selected Train, and re-established the link between each Train on
     * the Track.
     *
     * @return The removed train
     */
    public Train removeSelectedTrain() {
        if (this.countTrains == 0)
            return null;
        else if (this.cursor.equals(this.head) && this.countTrains == 1) {
            Train temp = this.head;
            this.head = null;
            this.tail = null;
            countTrains--;
            calculateUtilizationRate();
            return temp;
        } else if (this.cursor.equals(this.head)) {
            Train temp = this.head;
            this.head.getNext().setPrev(null);
            this.head = this.head.getNext();
            cursor = this.head;
            countTrains--;
            calculateUtilizationRate();
            return temp;
        } else if (this.cursor.equals(this.tail)) {
            Train temp = this.tail;
            this.tail.getPrev().setNext(null);
            this.tail = this.tail.getPrev();
            cursor = this.tail;
            countTrains--;
            calculateUtilizationRate();
            return temp;
        } else {
            Train temp = this.cursor;
            this.cursor.getPrev().setNext(this.cursor.getNext());
            this.cursor.getNext().setPrev(this.cursor.getPrev());
            countTrains--;
            if (this.cursor.getNext() == null)
                this.cursor = this.cursor.getPrev();
            else
                this.cursor = this.cursor.getNext();
            calculateUtilizationRate();
            return temp;
        }
    }

    /**
     * Calculates the utilization rate by finding the sum of the transfer time of each Train on the Track and dividing
     * it by the total number of minutes in a day. The percentage is later stored in the utilizationRate data field.
     */
    public void calculateUtilizationRate() {
        Train temp = this.head;
        double totalTransfer = 0.0;
        while (temp != null) {
            totalTransfer += temp.getTransferTime();
            temp = temp.getNext();
        }
        double percent = (totalTransfer / 1440) * 100;
        setUtilizationRate(percent);
    }

    /**
     * Moves the cursor to the next Train in the list. If the cursor is unable to move to the next Train, then it
     * prompts the user that it's at the end of the list. If it was able to move to the next Train, it returns true.
     *
     * @return
     * @throws Exception
     */
    public boolean selectNextTrain() throws Exception {
        if (cursor.getNext() != null) {
            cursor = cursor.getNext();
            return true;
        } else {
            throw new Exception("Selecting Next Train Error: You're already at the end of the list!");
        }
    }

    /**
     * Moves the cursor to the previous Train in the list. If the cursor is unable to move to the previous Train, then
     * it prompts the user that it's at the end of the list. If it was able to move to the previous Train, it returns
     * true.
     *
     * @return
     * @throws Exception
     */
    public boolean selectPrevTrain() throws Exception {
        if (cursor.getPrev() != null) {
            cursor = cursor.getPrev();
            return true;
        } else {
            throw new Exception("Selecting Previous Train Error: You're already at the beginning of the list!");
        }
    }

    /**
     * Iterates thorough the list of Trains on the Track and checks if the specified Train is already on the Track. If
     * the specified Train exists, it returns true. If the Train does not exist, it returns false.
     *
     * @param train
     * @return Returns true if the Train already found in the list.
     * Returns false if the Train is not found in the list.
     */
    public boolean exists(Train train) {
        Train temp = this.head;
        if (temp.equals(train))
            return true;
        while (temp.getNext() != null) {
            if (temp.getNext().equals(train))
                return true;
            else
                temp = temp.getNext();
        }
        return false;
    }

    /**
     * Checks if another Object is of type Track and is the same as the current Track by checking the value of track
     * number.
     *
     * @param o
     * @return Returns true if the Object is a Track and/or is the same as the current Transaction
     * Returns false if the Object is not a Track and/or is not the same as the current Transaction.
     */
    public boolean equals(Object o) {
        return this.trackNumber == ((Track) o).getTrackNumber();
    }

    /**
     * Adds the arrival time and the transfer time to determine the departure time of the Trains on the Track. Also
     * correctly formats the departure time to be in 24-hour format to be printed later.
     *
     * @param a The arrival time of the Train.
     * @param b The transfer time of the Train.
     * @return Returns the correctly formatted departure time of the Train in 24-hour format.
     */
    public static int formatDepartureTime(int a, int b) {
        int transfer = a + b;
        ;
        String format = String.format("%04d", transfer);
        int formatted = 0;
        if (Integer.parseInt(format.substring(2)) >= 60) {
            formatted = (Integer.parseInt(format.substring(0, 2)) + 1) * 100;
            formatted += Integer.parseInt(format.substring(2)) % 60;
        } else {
            return transfer;
        }
        return formatted;
    }

    /**
     * Iterates through the Train list and determines if the the Train to be added will arrive during the same time
     * as a scheduled Train on the Track already.
     *
     * @param newTrain
     * @return Returns true if the Train that would like to be added to the Track conflicts with a schedule Train on
     *         the Track.
     *         Returns false if there is no conflict with any Train on the Track.
     */
    public boolean conflictingTime(Train newTrain) {
        Train temp = this.head;
        while (temp != null) {
            if (newTrain.getArrivalTime() < Track.formatDepartureTime(temp.getArrivalTime(), temp.getTransferTime())) {
                return true;
            }
            temp = temp.getNext();
        }
        return false;
    }

    /**
     * Returns the String representation of a Track object by returning a table with information of the train such as
     * if the Train is the selected train, its train number, its destination, its arrival time, and its transfer
     * time.
     *
     * @return The String representation of a Track.
     */
    public String toString() {
        String result = "";
        calculateUtilizationRate();
        String trackInfo = String.format("%-10s%5s\n", ("Track " + getTrackNumber()),
                "(" + df.format(getUtilizationRate()) + "% Utilization Rate)");
        String header = String.format("%-15s%-20s%-32s%-25s%-25s", "Selected", "Train Number", "Train Destination",
                "Arrival Time", "Departure Time");
        String divider = "\n---------------------------------------------------------------------------------------" +
                "----------------------\n";
        result += trackInfo + header + divider;
        Train temp = this.head;
        while (temp != null) {
            if (temp.equals(this.cursor))
                result += String.format("%-15s%-20s%-32s%-25s%-25s\n", "*", temp.getTrainNumber(),
                        temp.getDestination()
                        , String.format("%04d", temp.getArrivalTime()),
                        String.format("%04d", Track.formatDepartureTime(temp.getArrivalTime(),
                                temp.getTransferTime())));
            else
                result += String.format("%-15s%-20s%-32s%-25s%-25s\n", "", temp.getTrainNumber(),
                        temp.getDestination()
                        , String.format("%04d", temp.getArrivalTime()),
                        String.format("%04d", Track.formatDepartureTime(temp.getArrivalTime(),
                                temp.getTransferTime())));
            temp = temp.getNext();
        }
        return result;
    }

    /**
     * Similar to the toString method, however this is a special table that returns a String representation of Track
     * if it's the currently the selected Track.
     *
     * @return The String representation of the selected Track.
     */
    public String cursorTrack() {
        String result = "";
        calculateUtilizationRate();
        String trackInfo = String.format("%-10s%5s\n", ("Track " + getTrackNumber() + "*"),
                "(" + df.format(getUtilizationRate()) + "% Utilization Rate)");
        String header = String.format("%-15s%-20s%-32s%-25s%-25s", "Selected", "Train Number", "Train Destination",
                "Arrival Time", "Departure Time");
        String divider = "\n---------------------------------------------------------------------------------------" +
                "----------------------\n";
        result += trackInfo + header + divider;
        Train temp = this.head;
        while (temp != null) {
            if (temp.equals(this.cursor))
                result += String.format("%-15s%-20s%-32s%-25s%-25s\n", "*", temp.getTrainNumber(),
                        temp.getDestination()
                        , String.format("%04d", temp.getArrivalTime()),
                        String.format("%04d", Track.formatDepartureTime(temp.getArrivalTime(),
                                temp.getTransferTime())));
            else
                result += String.format("%-15s%-20s%-32s%-25s%-25s\n", "", temp.getTrainNumber(),
                        temp.getDestination()
                        , String.format("%04d", temp.getArrivalTime()),
                        String.format("%04d", Track.formatDepartureTime(temp.getArrivalTime(),
                                temp.getTransferTime())));
            temp = temp.getNext();
        }
        return result.trim();
    }
}

