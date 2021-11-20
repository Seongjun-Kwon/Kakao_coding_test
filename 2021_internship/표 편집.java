import java.util.Stack;

public class Solution {
    class Node {
        boolean removed;
        Node prev;
        Node next;
    }

    Node[] arr = new Node[1000000];

    public String solution(int n, int k, String[] cmd) {
        Stack<Node> sta = new Stack<>();

        for (int i = 0; i < n; i++)
            arr[i] = new Node();

        for (int i = 1; i < n; i++) {
            arr[i - 1].next = arr[i];
            arr[i].prev = arr[i - 1];
        }

        Node cur = arr[k];

        for (String str : cmd) {
            if (str.charAt(0) == 'U') {
                int dis = Integer.parseInt(str.substring(2));

                for (int i = 0; i < dis; i++)
                    cur = cur.prev;
            }
            else if (str.charAt(0) == 'D') {
                int dis = Integer.parseInt(str.substring(2));

                for (int i = 0; i < dis; i++)
                    cur = cur.next;
            }
            else if (str.charAt(0) == 'C') {
                sta.push(cur);
                cur.removed = true;
                Node up = cur.prev;
                Node down = cur.next;

                if (up != null)
                    up.next = down;

                if (down != null) {
                    down.prev = up;
                    cur = down;
                }
                else
                    cur = up;
            }
            else {
                Node reNode = sta.pop();
                reNode.removed = false;
                Node up = reNode.prev;
                Node down = reNode.next;

                if (up != null)
                    up.next = reNode;

                if (down != null)
                    down.prev = reNode;
            }
        }

        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < n; i++) {
            if(arr[i].removed)
                answer.append("X");
            else
                answer.append("O");
        }

        return answer.toString();
    }
}
