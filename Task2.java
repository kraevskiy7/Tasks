import java.util.*;

public class ShortestPath {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Number of test cases
        int tests = Integer.parseInt(scanner.nextLine());

        // Process each test case
        for (int t = 0; t < tests; t++) {
            // Number of cities
            int cities = Integer.parseInt(scanner.nextLine());

            // Graph representation: city name -> list of neighbors
            Map<String, List<int[]>> graph = new HashMap<>();
            // Map city names to their index
            Map<String, Integer> cityIndex = new HashMap<>();

            // Reading city data
            for (int i = 0; i < cities; i++) {
                String city = scanner.nextLine();
                cityIndex.put(city, i); // Map city to its index
                int neighbors = Integer.parseInt(scanner.nextLine()); // Number of neighbors

                // Initialize neighbor list for the city
                graph.put(city, new ArrayList<>());

                // Add each neighbor and its cost
                for (int j = 0; j < neighbors; j++) {
                    String[] edge = scanner.nextLine().split(" ");
                    int neighborIndex = Integer.parseInt(edge[0]) - 1; // Neighbor city index
                    int cost = Integer.parseInt(edge[1]); // Transportation cost
                    graph.get(city).add(new int[]{neighborIndex, cost});
                }
            }

            // Number of paths to find
            int paths = Integer.parseInt(scanner.nextLine());

            // Process each query for shortest path
            for (int p = 0; p < paths; p++) {
                String[] route = scanner.nextLine().split(" ");
                String startCity = route[0];
                String endCity = route[1];

                // Find and print the shortest path cost
                System.out.println(findShortestPath(graph, cityIndex, startCity, endCity));
            }

            if (t < tests - 1) scanner.nextLine(); // Skip the empty line between test cases
        }

        scanner.close();
    }


    private static int findShortestPath(Map<String, List<int[]>> graph, Map<String, Integer> cityIndex, String start, String end) {
        int n = cityIndex.size(); // Total number of cities
        int[] dist = new int[n]; // Distance array to store minimum costs
        Arrays.fill(dist, Integer.MAX_VALUE); // Initialize distances to infinity
        dist[cityIndex.get(start)] = 0; // Distance to the starting city is 0

        // Priority queue to store cities by their current minimum cost
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{cityIndex.get(start), 0}); // Add the starting city

        // Process the priority queue
        while (!pq.isEmpty()) {
            int[] current = pq.poll(); // Get the city with the smallest cost
            int currentCity = current[0];
            int currentCost = current[1];

            // If we reached the destination city, return the cost
            if (currentCity == cityIndex.get(end)) return currentCost;

            // Explore all neighbors of the current city
            for (int[] neighbor : graph.getOrDefault(currentCity, new ArrayList<>())) {
                int nextCity = neighbor[0]; // Neighbor city index
                int travelCost = neighbor[1]; // Cost to travel to the neighbor

                // Relax the edge if a cheaper path is found
                if (currentCost + travelCost < dist[nextCity]) {
                    dist[nextCity] = currentCost + travelCost;
                    pq.add(new int[]{nextCity, dist[nextCity]}); // Add updated neighbor to the queue
                }
            }
        }

        return -1; // If no path to the destination exists
    }
}
