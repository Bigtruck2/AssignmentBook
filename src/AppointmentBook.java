import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.IntStream;

public class AppointmentBook
{
    //public HashMap<Integer, ArrayList<Integer>> takenTimes;
    boolean[][] takenTimes = new boolean[8][60];
    public AppointmentBook(boolean[][] takenTimes){
        this.takenTimes = takenTimes;
    }
    /**
     * Returns true if minute in period is available for an appointment and returns
     * false otherwise
     * Preconditions: 1 <= period <= 8; 0 <= minute <= 59
     */
    private boolean isMinuteFree(int period, int minute)
    { /* implementation not shown */
        return !takenTimes[period][minute];
    }
    /**
     * Marks the block of minutes that starts at startMinute in period and
     * is duration minutes long as reserved for an appointment
     * Preconditions: 1 <= period <= 8; 0 <= startMinute <= 59;
     * 1 <= duration <= 60
     */
    private void reserveBlock(int period, int startMinute, int duration)
    {
        for (int j = startMinute-1; j < startMinute+duration-1; j++) takenTimes[period][j] = true;
    }
    /**
     * Searches for the first block of duration free minutes during period, as described in
     * part (a). Returns the first minute in the block if such a block is found or returns -1 if no
     * such block is found.
     * Preconditions: 1 <= period <= 8; 1 <= duration <= 60
     */
    public int findFreeBlock(int period, int duration) {
        int startingMinute = 0;
        while (startingMinute+duration<60) {
            for (int i = 0; i <= duration; i++) {
                if(!isMinuteFree(period,startingMinute+i)) {
                    startingMinute = startingMinute + i + 1;
                    break;
                }
                if(i==duration && isMinuteFree(period,startingMinute+duration)){
                    return startingMinute;
                }
            }
        }
        return -1;
    }
    /**
     * Searches periods from startPeriod to endPeriod, inclusive, for a block
     * of duration free minutes, as described in part (b). If such a block is found,
     * calls reserveBlock to reserve the block of minutes and returns true; otherwise
     * returns false.
     * Preconditions: 1 <= startPeriod <= endPeriod <= 8; 1 <= duration <= 60
     */
    public boolean makeAppointment(int startPeriod, int endPeriod, int duration) {
        for (int i = startPeriod; i <= endPeriod; i++) {
            int appointment = findFreeBlock(i,duration);
            if(appointment !=-1) {
                reserveBlock(i,appointment+1,duration);
                return true;
            }
        }
        return false;
    }
// There may be instance variables, constructors, and methods that are not shown.
}