import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class City {
    String name;
    double latitude;
    double longitude;

    City(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}

class Edge implements Comparable<Edge> {
    int city1;
    int city2;
    double weight;

    Edge(int city1, int city2, double weight) {
        this.city1 = city1;
        this.city2 = city2;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Double.compare(this.weight, other.weight);
    }
}

public class KruskalMST {
    private static List<City> cities = new ArrayList<>();
    private static List<Edge> edges = new ArrayList<>();

    public static void main(String[] args) {
        String filePath = ".vscode/cleaned_uscities_1000_entries.txt";
        loadCities(filePath);
        createEdges();
        List<Edge> mst = kruskalMST();

        double totalLength = 0.0;
        for (Edge edge : mst) {
            System.out.println("City1: " + cities.get(edge.city1).name +
                    ", City2: " + cities.get(edge.city2).name +
                    ", Distance: " + edge.weight);
            totalLength += edge.weight;
        }
        System.out.println("Total Length of MST: " + totalLength + " km");
    }

    private static void loadCities(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\t");
                if (values.length < 5) continue; // Skip incomplete rows

                String name = values[0];
                double latitude = Double.parseDouble(values[3]);
                double longitude = Double.parseDouble(values[4]);
                cities.add(new City(name, latitude, longitude));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createEdges() {
        for (int i = 0; i < cities.size(); i++) {
            for (int j = i + 1; j < cities.size(); j++) {
                double distance = calculateDistance(cities.get(i), cities.get(j));
                edges.add(new Edge(i, j, distance));
            }
        }
        Collections.sort(edges);
    }

    private static double calculateDistance(City city1, City city2) {
        double latDiff = Math.toRadians(city1.latitude - city2.latitude);
        double lonDiff = Math.toRadians(city1.longitude - city2.longitude);
        double a = Math.pow(Math.sin(latDiff / 2), 2) +
                   Math.cos(Math.toRadians(city1.latitude)) *
                   Math.cos(Math.toRadians(city2.latitude)) *
                   Math.pow(Math.sin(lonDiff / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return 6371 * c; 
    }

    private static List<Edge> kruskalMST() {
        int[] parent = new int[cities.size()];
        for (int i = 0; i < cities.size(); i++) parent[i] = i;

        List<Edge> mst = new ArrayList<>();
        for (Edge edge : edges) {
            int root1 = find(parent, edge.city1);
            int root2 = find(parent, edge.city2);

            if (root1 != root2) {
                mst.add(edge);
                parent[root1] = root2;
            }
        }
        return mst;
    }

    private static int find(int[] parent, int i) {
        if (parent[i] != i)
            parent[i] = find(parent, parent[i]);
        return parent[i];
    }
}
