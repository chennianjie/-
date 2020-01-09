package arithmetic.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    public static void bfs(Node node) {

        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> map = new HashSet<>();

        queue.add(node);
        map.add(node);
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            System.out.print(poll.value);
            for (Node next : poll.nexts) {
                if (!map.contains(next)) {
                    queue.add(next);
                    map.add(next);
                }
            }
        }
    }
}
