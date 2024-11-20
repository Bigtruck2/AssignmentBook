import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        boolean[][] schedule = new boolean[8][60];
        for (int i = 0; i < 8; i++) for (int k = 0; k < 60; k++) schedule[i][k] = false;
        for (int i = 0; i < 25; i++) schedule[2][i] = true;
        for (int i = 30; i < 60; i++) schedule[2][i] = true;
        for (int i = 15; i < 41; i++) schedule[3][i] = true;
        for (int i = 0; i < 5; i++) schedule[4][i] = true;
        for (int i = 30; i < 44; i++) schedule[4][i] = true;

        AppointmentBook appointmentBook = new AppointmentBook(schedule);
        appointmentBook.makeAppointment(2, 4, 22);
        appointmentBook.makeAppointment(3, 4, 3);
        appointmentBook.makeAppointment(2, 4, 30);
        int count = 0;
        for (boolean min : schedule[4]){
            count++;
            System.out.printf("Period 4 Min: %d Taken %b\n",count,min);
        }
    }
}
/*
2 0–24 (25 minutes) No
2 30–59 (30 minutes) No
3 15–40 (26 minutes) No
4 0–4 (5 minutes) No
4 30–43 (14 minutes) No
*/