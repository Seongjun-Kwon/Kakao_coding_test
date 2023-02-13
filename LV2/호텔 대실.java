import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Solution {

    class Reservation implements Comparable<Reservation> {
        int startMin;
        int endMin;

        public Reservation(int startMin, int endMin) {
            this.startMin = startMin;
            this.endMin = endMin;
        }

        @Override
        public int compareTo(Reservation o) {
            if (this.startMin == o.startMin) {
                return this.endMin - o.endMin;
            } else {
                return this.startMin - o.startMin;
            }
        }
    }

    public int solution(String[][] book_time) {
        List<Integer> room = new ArrayList<>();

        Reservation[] reservation = new Reservation[book_time.length];

        for (int i = 0; i < book_time.length; i++) {
            reservation[i] = new Reservation(getMinutes(book_time[i][0]), getMinutes(book_time[i][1]) + 10);
        }

        Arrays.sort(reservation);

        for (int i = 0; i < reservation.length; i++) {
            boolean addRoom = true;

            for (int j = 0; j < room.size(); j++) {
                if (room.get(j) <= reservation[i].startMin) {
                    room.set(j, reservation[i].endMin);
                    addRoom = false;
                    break;
                }
            }

            if (addRoom) {
                room.add(reservation[i].endMin);
            }
        }

        return room.size();
    }

    private int getMinutes(String time) {
        StringTokenizer st = new StringTokenizer(time, ":");
        int hour = Integer.parseInt(st.nextToken());
        int minute = Integer.parseInt(st.nextToken());

        return hour * 60 + minute;
    }
}
