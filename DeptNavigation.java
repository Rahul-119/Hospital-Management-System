import java.util.*;

public class DeptNavigation {
    private final HashMap<String, Integer> deptMap;
    private final List<List<Integer>> graph;
    private final String[] deptNames;

    public DeptNavigation() {
        deptMap = new HashMap<>();

        deptMap.put("Entrance", 0);
        deptMap.put("Reception", 1);
        deptMap.put("Emergency", 2);
        deptMap.put("OPD", 3);
        deptMap.put("Radiology", 4);
        deptMap.put("Cardiology", 5);
        deptMap.put("ICU", 6);
        deptMap.put("Pharmacy", 7);

        graph = new ArrayList<>();

        for (int i = 0; i < deptMap.size(); i++) {
            graph.add(new ArrayList<>());
        }

        graph.get(deptMap.get("Entrance")).add(deptMap.get("Reception")); // Entrance -> [Reception]
        graph.get(deptMap.get("Reception")).add(deptMap.get("Entrance"));

        graph.get(deptMap.get("Reception")).add(deptMap.get("Emergency")); // Reception -> [Entrance, Emergency, OPD]
        graph.get(deptMap.get("Emergency")).add(deptMap.get("Reception")); // Emergency -> [Reception]

        graph.get(deptMap.get("Reception")).add(deptMap.get("OPD"));
        graph.get(deptMap.get("OPD")).add(deptMap.get("Reception")); // OPD -> [Reception]

        graph.get(deptMap.get("Radiology")).add(deptMap.get("Entrance"));
        graph.get(deptMap.get("Radiology")).add(deptMap.get("Pharmacy")); // Radiology -> [Entrance, Pharmacy]

        graph.get(deptMap.get("Cardiology")).add(deptMap.get("Entrance")); // Cardiology -> [Entrance, ICU]

        graph.get(deptMap.get("Cardiology")).add(deptMap.get("ICU"));
        graph.get(deptMap.get("ICU")).add(deptMap.get("Cardiology")); // ICU <-> [Cardiology]

        graph.get(deptMap.get("Pharmacy")).add(deptMap.get("Entrance"));
        graph.get(deptMap.get("Pharmacy")).add(deptMap.get("Radiology")); // Pharmacy -> [Entrance, Radiology]

        deptNames = new String[deptMap.size()];
        for (Map.Entry<String, Integer> entry : deptMap.entrySet()) {
            deptNames[entry.getValue()] = entry.getKey();
        }
    }

    public void dfs(int node, boolean vis[]) {
        vis[node] = true;
        System.out.print(deptNames[node] + " -> ");
        for (int adjNode : graph.get(node)) {
            if (!vis[adjNode]) {
                dfs(adjNode, vis);
            }
        }
    }

    public void findPath(String startDept, String endDept) {
        int start = deptMap.get(startDept);
        int end = deptMap.get(endDept);

        boolean vis[] = new boolean[deptMap.size()];
        int[] parent = new int[deptMap.size()];
        Arrays.fill(parent, -1);

        Queue<Integer> q = new LinkedList<>();
        vis[start] = true;
        q.add(start);

        while (!q.isEmpty()) {
            int node = q.poll();

            for (int adjNode : graph.get(node)) {
                if (!vis[adjNode]) {
                    vis[adjNode] = true;
                    parent[adjNode] = node;
                    q.add(adjNode);
                }
            }
        }

        if (!vis[end]) {
            System.out.println("No path found from " + startDept + " to " + endDept);
            return;
        }
        List<String> path = new ArrayList<>();
        for (int at = end; at != -1; at = parent[at]) {
            path.add(deptNames[at]);
        }
        Collections.reverse(path);
        System.out.println("Path: " + String.join(" -> ", path));
    }

    @SuppressWarnings("ConvertToTryWithResources")
    public void runNavigationMenu() {
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("\nDepartment Navigation Menu:");
            System.out.println("1. Show all departments and connections");
            System.out.println("2. Find path from one department to another");
            System.out.println("3. Show all reachable departments from a department");
            System.out.println("4. Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    for (int i = 0; i < deptMap.size(); i++) {
                        System.out.print(deptNames[i] + " -> ");

                        for (int n : graph.get(i)) {
                            System.out.print(deptNames[n] + " ");
                        }
                        System.out.println();
                    }
                    break;

                case 2:
                    System.out.println("Enter start department: ");
                    String startDept = sc.nextLine();
                    System.out.println("Enter end department: ");
                    String endDept = sc.nextLine();
                    findPath(startDept, endDept);
                    break;

                case 3:
                    System.out.println("Enter start department: ");
                    String dfsDept = sc.nextLine();
                    boolean[] vis = new boolean[deptMap.size()];
                    dfs(deptMap.get(dfsDept), vis);
                    System.out.println("END");
                    break;

                case 4:
                    running = false;
                    System.out.println("Exiting Department Navigation");
                    break;

                default:
                    System.out.println("Invalid Option");
                    break;
            }
        }
        sc.close();
    }
}
