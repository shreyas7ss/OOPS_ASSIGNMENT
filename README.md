# OOPS_ASSIGNMENT

# P.B Shreyas
# 23BDS041


# Introduction:
This code implements Kruskal's algorithm to compute the Minimum Spanning Tree (MST) for a set of cities based on their geographical distances. The algorithm works by finding the shortest set of edges (i.e., the minimal connection between all cities) that form a spanning tree, without creating cycles, using a greedy approach. Here's a breakdown of the key components:

# Classes:


## City Class:

This class represents a city with three properties:
name: the name of the city.
latitude: the latitude coordinate of the city.
longitude: the longitude coordinate of the city.
Constructor: It initializes a city with the given name, latitude, and longitude.


## Edge Class:

This class represents an edge in the graph that connects two cities.
city1: index of the first city in the cities list.
city2: index of the second city in the cities list.
weight: the geographical distance (weight) between city1 and city2.
Implements the Comparable interface to allow edges to be sorted based on their weight (distance). This is necessary for Kruskal's algorithm, where edges need to be processed in increasing order of their weight.

## Main KruskalMST Class
This class contains the main logic of the program. Here's how the methods and main function work:

## main() method
Load the Cities:

The file path .vscode/cleaned_uscities_1000_entries.txt is passed to the loadCities() method to read city data from the file.
Create Edges:

Once the cities are loaded, the createEdges() method is called. This creates edges between every pair of cities, where each edge's weight is the geographical distance between the two cities.
Find MST Using Kruskal's Algorithm:

The method kruskalMST() is called to apply Kruskal's algorithm to the list of edges, ultimately computing the MST (a list of edges).
Print the Result:

The program prints the cities connected by each edge in the MST and the total length of the MST (in kilometers).


## loadCities() method
This method reads the city data from a tab-delimited file. Each line represents a city and its attributes. The data is split into values, and for each valid line:
The city name, latitude, and longitude are extracted and used to create a City object, which is added to the cities list.

## createEdges() method
This method generates all possible pairs of cities and calculates the distance (weight) between them.
The distance between two cities is computed using the calculateDistance() method, which employs the Haversine formula.
The result is an edge that connects the two cities. These edges are stored in the edges list.
Once all the edges are created, they are sorted by their weight (distance) in ascending order.
calculateDistance() method
This method calculates the geographical distance between two cities using the Haversine formula.
The Haversine formula calculates the great-circle distance between two points on the Earth's surface, given their latitudes and longitudes.
It takes the latitudes and longitudes of both cities, converts them into radians, and computes the distance in kilometers using the formula:

## kruskalMST() method
This method implements Kruskal's algorithm to compute the MST:
It initializes a disjoint-set (union-find) data structure to keep track of which cities are connected. Each city starts as its own set (i.e., each city is its own parent).
The edges are processed in ascending order of weight. For each edge, the algorithm checks if the two cities are in the same set using the find() method:
If they are not in the same set, the edge is added to the MST (because adding it won't create a cycle).
The two cities are then merged into the same set by updating the parent of one city to be the other city's parent (using the union operation).
This process continues until all cities are connected, and the MST is formed.
find() method
This is a standard union-find method used to find the root of a set that a particular element belongs to. It employs path compression to optimize the process by making future queries faster.

## Output:
The program outputs the edges in the MST, including the names of the cities connected by each edge and the distance between them.
Finally, it outputs the total length of the MST, which represents the total minimal distance needed to connect all cities in the graph.
Summary of the Algorithm's Flow:
Load the cities and their geographical data.
Create a list of edges between each pair of cities with the geographical distances as weights.
Sort the edges by weight (distance).
Apply Kruskal's algorithm (using a union-find structure) to construct the MST.
Print the edges in the MST and the total length of the tree.

## Conclusion:
In conclusion, this code is a typical application of Kruskal's algorithm to compute a Minimum Spanning Tree, and it makes use of a geographic dataset of cities. The primary data structure used for cycle detection in Kruskal's algorithm is the union-find or disjoint-set.
