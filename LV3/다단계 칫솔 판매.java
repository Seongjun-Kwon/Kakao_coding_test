import java.util.HashMap;

class Solution {
    class Node {
        String parent; // 부모 노드의 이름
        int price; // 자신의 가격

        public Node(String parent, int price) {
            this.parent = parent;
            this.price = price;
        }
    }

    HashMap<String, Node> map = new HashMap<>();

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        for (int i = 0; i < enroll.length; i++) {
            map.put(enroll[i], new Node(referral[i], 0));
        }

        for (int i = 0; i < seller.length; i++) {
            String name = seller[i];
            dfs(name, 100 * amount[i]);
        }

        int[] answer = new int[enroll.length];
        for (int i = 0; i < enroll.length; i++) {
            answer[i] = map.get(enroll[i]).price;
        }

        return answer;
    }

    private void dfs(String curName, int curPrice) {
        Node child = map.get(curName);

        if (child.parent.equals("-")) {
            int fee = curPrice / 10;
            curPrice -= fee;
            child.price += curPrice;
            map.put(curName, child);
            return;
        }
        if (curPrice == 0) {
            return;
        }

        int fee = curPrice / 10;
        curPrice -= fee;
        child.price += curPrice;
        map.put(curName, child);

        dfs(map.get(curName).parent, fee);
    }
}
