package com.practice.dijkstra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DijkstraApplication {

    public static void main(java.lang.String[] args) {
//        Map<String, Map<String, Double>> countryCodesGraph = new HashMap<String, Map<String, Double>>() {{
//            put("0", new HashMap<String, Double>() {{
//                put("2", 1D);
//                put("1", 2D);
//            }});
//            put("2", new HashMap<String, Double>() {{
//                put("3", 1D);
//            }});
//            put("3", new HashMap<String, Double>() {{
//                put("4", 3D);
//            }});
//            put("1", new HashMap<String, Double>() {{
//                put("4", 2D);
//            }});
//            put("4", new HashMap<>());
//        }};
//
//        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(countryCodesGraph);
//        dijkstra.execute("0");
//        List<String> path = dijkstra.getPath("4");
//
//        for (String vertex : path) {
//            System.out.println(vertex);
//        }

        SpringApplication.run(DijkstraApplication.class, args);
    }

}
