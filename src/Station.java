//Rezvan Nafee
//112936468
//Recitation 04

import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class represents the Station which will mange the Tracks in the Station such as removing and adding Tracks to
 * the Station.
 *
 * @author Rezvan Nafee
 * @ID 112936468
 * @Recitation Section 04
 */
public class Station {

    /**
     * Creates a DecimalFormat to allow the programto display the utilization percentage rounded to the nearest 2
     * decimal places.
     */
    public DecimalFormat df = new DecimalFormat("0.00");
    private int countTracks;
    private Track head;
    private Track tail;
    private Track cursor;

    /**
     * This is a constructor that creates the a default Station with it's respective initial values.
     */
    public Station() {
    }

    /**
     * Sets the tail to point to a specific Track.
     *
     * @param tail The specific Track in which the Station will refer it as its tail.
     */
    public void setTail(Track tail) {
        this.tail = tail;
    }

    /**
     * Sets the head to point to a specific Track.
     *
     * @param head The specific Track in which the Station will refer it as its head.
     */
    public void setHead(Track head) {
        this.head = head;
    }

    /**
     * Sets the cursor of the Station to a specific track.
     *
     * @param cursor The specific track in which the Station will refer as its cursor. In other words, the selected
     *               Track.
     */
    public void setCursor(Track cursor) {
        this.cursor = cursor;
    }

    /**
     * Returns the selected Track of the Station.
     *
     * @return Returns cursor.
     */
    public Track getCursor() {
        return cursor;
    }

    /**
     * Returns the head of the Station. In other words, the first Track in the Station.
     *
     * @return Returns head.
     */
    public Track getHead() {
        return head;
    }

    /**
     * Returns the tail of the Station. In other words, the last Track in the Station.
     *
     * @return Returns tail.
     */
    public Track getTail() {
        return tail;
    }

    /**
     * Adds a specified Track to the Station. If there are no Trains currently in the Station, the Track is added to
     * the Station and is set as the head,cursor, and tail. If there is already a Track in the Station, the method adds
     * it to the end of the Track list, but it also makes sure that the Track doesn't already exists on the Track
     * if it does, than the program prompts the user with an error message.
     *
     * @param newTrack The Track that will be determined if it can be added to the Station or not.
     * @throws ExistingTrack
     */
    public void addTrack(Track newTrack) throws ExistingTrack {
        if (countTracks == 0) {
            this.head = newTrack;
            this.cursor = newTrack;
            this.tail = newTrack;
            countTracks++;
        } else if (exists(newTrack)) {
            throw new ExistingTrack("Track not added: Track " + newTrack.getTrackNumber() +
                    " exists in the Station!");
        } else {
            Track track = getHead();
            while (track.getNext() != null)
                track = track.getNext();
            newTrack.setPrev(track);
            track.setNext(newTrack);
            this.tail = newTrack;
            this.cursor = this.tail;
            this.countTracks++;
        }
    }

    /**
     * Iterates thorough the list of Tracks in the Station and checks if the specified Track is already in the Station.
     * If thhe specified Track exists, it returns true. If the Track does not exist, it returns false.
     *
     * @param track
     * @return Returns true if the Track already found in the list.
     * Returns false if the Track is not found in the list.
     */
    public boolean exists(Track track) {
        Track temp = this.head;
        if (temp.equals(track))
            return true;
        while (temp.getNext() != null) {
            if (temp.getNext().equals(track))
                return true;
            else
                temp = temp.getNext();
        }
        return false;
    }

    /**
     * Removes the selected Track in the Station and returns the removed Track to display to the user which Track was
     * removed. If there are no Tracks in the Station, it returns a null value which prompts the user there is nothing
     * to be removed. Else, the program removes the selected Track, and re-established the link between each Track in
     * the Station.
     *
     * @return The removed Track
     */
    public Track removeSelectedTrack() {
        if (this.countTracks == 0)
            return null;
        else if (this.cursor.equals(this.head) && this.countTracks == 1) {
            Track temp = this.head;
            this.head = null;
            this.tail = null;
            this.countTracks--;
            return temp;
        } else if (this.cursor.equals(this.head)) {
            Track temp = this.head;
            this.head.getNext().setPrev(null);
            this.head = this.head.getNext();
            this.cursor = this.head;
            this.countTracks--;
            return temp;
        } else if (this.cursor.equals(this.tail)) {
            Track temp = this.tail;
            this.tail.getPrev().setNext(null);
            this.tail = this.tail.getPrev();
            this.cursor = this.tail;
            this.countTracks--;
            return temp;
        } else {
            Track temp = this.cursor;
            this.cursor.getPrev().setNext(this.cursor.getNext());
            this.cursor.getNext().setPrev(this.cursor.getPrev());
            this.countTracks--;
            if (this.cursor.getNext() == null)
                this.cursor = this.cursor.getPrev();
            else
                this.cursor = this.cursor.getNext();
            return temp;
        }
    }

    /**
     * Prints out the selected Track in the Station. In other words, it uses the cursor's toString method.
     */
    public void printSelectedTrack() {
        System.out.println();
        System.out.println(this.cursor.toString());
    }

    /**
     * Prints out all the Tracks in the Station. When it finds the cursor, it prints out a specific table to identify it
     * as the selected Track to the user. (It's marked with '*' in the Track name.)
     */
    public void printAllTracks() {
        Track temp = this.head;
        while (temp != null) {
            if (temp.equals(this.cursor))
                System.out.println(temp.cursorTrack());
            else
                System.out.println(temp);
            temp = temp.getNext();
        }
    }

    /**
     * Moves the cursor to a specified track number. If the cursor is unable to find the specified track number in
     * the Track list, it returns false. If it was able find the specific track number, it returns true and sets the
     * cursor to that specified Track. If the user is already on the specified track, the method throws an error and
     * prompts the user that the user is already on the specified Track.
     *
     * @param trackToSelect The track number to try to select in the Station.
     * @return Returns true if the Track is found in the station and sets the cursor to that specified Track.
     * Returns false if the Track is not found within the Station.
     * @throws Exception
     */
    public boolean selectTrack(int trackToSelect) throws Exception {
        if (this.getCursor().getTrackNumber() == trackToSelect) {
            throw new Exception("Switching Track Error: You are already on Track " + trackToSelect);
        }
        if (this.head.getTrackNumber() == trackToSelect) {
            cursor = this.head;
            return true;
        }
        if (this.tail.getTrackNumber() == trackToSelect) {
            cursor = this.getTail();
            return true;
        }
        Track temp = this.head;
        while (temp.getNext() != null) {
            if (temp.getTrackNumber() == trackToSelect) {
                cursor = temp;
                return true;
            } else {
                temp = temp.getNext();
            }
        }
        return false;
    }

    /**
     * Returns the String representation of the Station object by displaying each Track with it's respective track
     * number and utilization rate.
     *
     * @return
     */
    public String toString() {
        String result = "";
        String stationInfo = "\nStation (" + this.countTracks + " tracks):\n";
        result += stationInfo;
        Track temp = this.head;
        while (temp != null) {
            result += "\tTrack " + temp.getTrackNumber() + ": " + temp.getCountTrains()
                    + " Trains arriving " + "(" + df.format(temp.getUtilizationRate()) + "% Utilization Rate)\n";
            temp = temp.getNext();
        }
        return result;
    }

    /**
     * Prints the menu of operation that the user can do in order to simulate a Station, Track, and Train.
     */
    public static void printUserTable() {
        System.out.println("\n|-----------------------------------------------------------------------------|");
        for (int i = 0; i < 6; i++) {
            if (i == 0)
                System.out.println("|Train Options                   |Track Options                               |");
            if (i == 1)
                System.out.println("|\tA. Add new Train             |\t TA. Add Track                            |");
            if (i == 2)
                System.out.println("|\tN. Select next Train         |\t TR. Remove selected Track                |");
            if (i == 3)
                System.out.println("|\tV. Select previous Train     |\t TS. Switch Track                         |");
            if (i == 4)
                System.out.println("|\tR. Remove selected Train     |\tTPS. Print selected Track                 |");
            if (i == 5)
                System.out.println("|\tP. Print selected Train      |\tTPA. Print all Tracks                     |");

        }
        System.out.println("|-----------------------------------------------------------------------------|");
        for (int i = 0; i < 3; i++) {
            if (i == 0)
                System.out.println("|Station Options:                                                             |");
            if (i == 1)
                System.out.println("|\tSI. Print Station Information                                             |");
            if (i == 2)
                System.out.println("|\t Q. Quit                                                                  |");
        }
        System.out.println("|-----------------------------------------------------------------------------|");
    }

    /**
     * The main method where the user can simulate a Station by adding and removing Trains and Tracks within the
     * Station. The user can also select different Tracks and Trains and manage them as well. Furthermore, the user is
     * able to display information about a specific train, specific Track, and all Tracks in the Station.
     *
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Station station = new Station();
        Track track = new Track();
        String key = "";
        boolean trackExists = false;
        while (!key.equals("Q")) {
            printUserTable();
            if (station.countTracks == 0)
                trackExists = false;
            System.out.print("\nChoose an operation: ");
            key = input.nextLine().trim().toUpperCase();
            switch (key) {
                case ("A"):
                    int num;
                    String destination;
                    String arrivalTime;
                    int transferTime;
                    if (!trackExists) {
                        System.out.print("\nEnter Train number: ");
                        input.nextLine();
                        System.out.print("Enter train destination: ");
                        input.nextLine();
                        System.out.print("Enter arrival time: ");
                        input.nextLine();
                        System.out.print("Enter transfer time: ");
                        input.nextLine();
                        System.out.println("\nTrain not added: There is no Track to add the Train to!");
                        continue;
                    }
                    try {
                        System.out.print("\nEnter train number: ");
                        num = input.nextInt();
                        input.nextLine();
                    } catch (InputMismatchException ex) {
                        input.nextLine();
                        System.out.print("Enter train destination: ");
                        destination = input.nextLine().trim();
                        try {
                            System.out.print("Enter train arrival time: ");
                            arrivalTime = input.nextLine();
                        } catch (InputMismatchException e) {
                            try {
                                System.out.print("Enter train transfer time: ");
                                transferTime = input.nextInt();
                                input.nextLine();
                            } catch (InputMismatchException d) {
                                input.nextLine();
                                System.out.print("\nTrain not added: Invalid information given!");
                                continue;
                            }
                            System.out.print("\nTrain not added: Invalid information given!");
                            continue;
                        }
                        System.out.print("\nTrain not added: Invalid information given!");
                        continue;
                    }
                    System.out.print("Enter train destination: ");
                    destination = input.nextLine().trim();
                    try {
                        System.out.print("Enter arrival time: ");
                        arrivalTime = input.nextLine();
                    } catch (InputMismatchException ex) {
                        input.nextLine();
                        try {
                            System.out.print("Enter transfer time: ");
                            transferTime = input.nextInt();
                            input.nextLine();
                        } catch (InputMismatchException ec) {
                            input.nextLine();
                            System.out.print("\nTrain not added: Invalid information given!");
                            continue;
                        }
                        System.out.print("\nTrain not added: Invalid information given!");
                        continue;
                    }
                    try {
                        System.out.print("Enter transfer time: ");
                        transferTime = input.nextInt();
                        input.nextLine();
                    } catch (InputMismatchException ex) {
                        input.nextLine();
                        System.out.print("\nTrain not added: Invalid information given!");
                        continue;
                    }
                    try {
                        boolean b = Train.checkTime(arrivalTime);
                    } catch (InvalidTime ex) {
                        System.out.println("\nTrain not added: Invalid arrival time given!");
                        continue;
                    }
                    if (num <= 0) {
                        System.out.println("\nTrain not added: Invalid Train number!");
                        continue;
                    }
                    if (transferTime < 0) {
                        System.out.println("\nTrain not added: Invalid transfer time given!");
                        continue;
                    }
                    Train train = new Train(num, destination, Integer.parseInt(arrivalTime),
                            transferTime);
                    try {
                        station.getCursor().addTrain(train);
                        System.out.println("\nTrain No." + train.getTrainNumber() + " to " +
                                train.getDestination() + " " +
                                "added " + "to Track " + station.getCursor().getTrackNumber() + "!");
                    } catch (ExistingTrain existingTrain) {
                        System.out.println("\n" + existingTrain.getLocalizedMessage());
                        continue;
                    }
                    break;
                case ("N"):
                    if (!trackExists) {
                        System.out.println("\nSelecting Next Train Error: There are no Tracks to select" +
                                " the next Train!");
                        continue;
                    }
                    try {
                        station.getCursor().selectNextTrain();
                        System.out.println("\nCursor has been moved to the next train!");
                    } catch (Exception ex) {
                        System.out.println("\n" + ex.getLocalizedMessage());
                    }
                    break;
                case ("V"):
                    if (!trackExists) {
                        System.out.println("\nSelecting Previous Train Error: There are no Tracks to select " +
                                "the previous Train!");
                        continue;
                    }
                    try {
                        station.getCursor().selectPrevTrain();
                        System.out.println("\nCursor has been moved to the previous train!");
                    } catch (Exception ex) {
                        System.out.println("\n" + ex.getLocalizedMessage());
                    }
                    break;
                case ("R"):
                    if (!trackExists) {
                        System.out.println("\nRemoving Train Error: There are no Tracks to remove a Train!");
                        continue;
                    }
                    if (station.getCursor().getCountTrains() == 0) {
                        System.out.println("\nRemoving Train Error: There are no Trains to remove from Track " +
                                station.getCursor().getTrackNumber() + "!");
                        continue;
                    }
                    Train removedTrain = station.getCursor().removeSelectedTrain();
                    if (removedTrain != null)
                        System.out.println("\nTrain No." + removedTrain.getTrainNumber() + " to "
                                + removedTrain.getDestination()
                                + " has been removed from Track " + station.getCursor().getTrackNumber() + "!");
                    break;
                case ("P"):
                    if (!trackExists) {
                        System.out.println("\nPrinting Train Error: There are no Tracks in the station!");
                        continue;
                    }
                    try {
                        station.getCursor().printSelectedTrain();
                    } catch (Exception ex) {
                        System.out.println("\n" + ex.getLocalizedMessage());
                    }
                    break;
                case ("TA"):
                    int trackNum;
                    try {
                        System.out.print("\nEnter track number: ");
                        trackNum = input.nextInt();
                        input.nextLine();
                    } catch (InputMismatchException ex) {
                        input.nextLine();
                        System.out.println("\nTrack not added: Invalid track number given!");
                        continue;
                    }
                    track = new Track(trackNum);
                    try {
                        if (!trackExists) {
                            trackExists = true;
                            station.addTrack(track);
                        } else {
                            station.addTrack(track);
                        }
                    } catch (ExistingTrack ex) {
                        System.out.println("\n" + ex.getLocalizedMessage());
                        continue;
                    }
                    System.out.println("\nTrack " + track.getTrackNumber() + " added to the Station!");
                    break;
                case ("TR"):
                    if (!trackExists) {
                        System.out.println("\nRemoving Track Error: There are no Tracks to remove from the station!");
                        continue;
                    }
                    if (station.countTracks == 0) {
                        System.out.println("\nRemoving Track Error: There are no Tracks to remove from the station!");
                        continue;
                    }
                    Track removedTrack = station.removeSelectedTrack();
                    if (removedTrack != null)
                        System.out.println("\nTrack " + removedTrack.getTrackNumber() + " has been removed from " +
                                "the station!");
                    break;
                case ("TS"):
                    int selectedTrack;
                    if (!trackExists) {
                        System.out.println("\nSwitching Track Error: There are no Tracks in the Station!");
                        continue;
                    }
                    try {
                        System.out.print("\nEnter track number: ");
                        selectedTrack = input.nextInt();
                        input.nextLine();
                    } catch (InputMismatchException ex) {
                        input.nextLine();
                        System.out.println("\nSwitching Track error: Inputted information is not valid!");
                        continue;
                    }
                    try {
                        if (station.selectTrack(selectedTrack)) {
                            System.out.println("\nSwitched to Track " + selectedTrack + " successfully!");
                        } else {
                            System.out.println("\nSwitching Track error: Track " + selectedTrack + " doesn't " +
                                    "exist in Station!");
                        }
                    } catch (Exception ex) {
                        System.out.println("\n" + ex.getLocalizedMessage() + "!");
                    }
                    break;
                case ("TPS"):
                    if (!trackExists) {
                        System.out.println("\nPrinting Selected Track Error: There are no Tracks in the Station to be "
                                + "printed!");
                        continue;
                    }
                    station.printSelectedTrack();
                    break;
                case ("TPA"):
                    if (!trackExists) {
                        System.out.println("\nPrinting Tracks Error: There are no Tracks in the Station to be " +
                                "printed!");
                        continue;
                    }
                    station.printAllTracks();
                    break;
                case ("SI"):
                    if (!trackExists) {
                        System.out.println("Print Station Information Error: There are no Tracks in the Station!");
                        continue;
                    }
                    System.out.print(station);
                    break;
                case ("Q"):
                    System.out.println("\nProgram terminating normally...");
                    break;
                default:
                    System.out.println("\nNot a valid a menu option!");
            }
        }
    }
}
