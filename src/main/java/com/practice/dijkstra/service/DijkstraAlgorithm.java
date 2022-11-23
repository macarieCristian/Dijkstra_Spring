package com.practice.dijkstra.service;

import lombok.RequiredArgsConstructor;

import java.util.*;

@RequiredArgsConstructor
public class DijkstraAlgorithm {
    private final Map<String, Map<String, Double>> countryCodesGraph;

    private Set<String> settledNodes;
    private Set<String> unsettledNodes;
    private Map<String, String> predecessorsMap;
    private Map<String, Double> distancesMap;

    public void execute(String source) {
        settledNodes = new HashSet<>();
        unsettledNodes = new HashSet<>();
        distancesMap = new HashMap<>();
        predecessorsMap = new HashMap<>();

        distancesMap.put(source, 0D);
        unsettledNodes.add(source);

        while (unsettledNodes.size() > 0) {
            String node = getClosestNeighbor(unsettledNodes);

            settledNodes.add(node);
            unsettledNodes.remove(node);

            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(String node) {
        countryCodesGraph.get(node).entrySet().stream()
                .filter(entry -> !settledNodes.contains(entry.getKey()))
                .forEach(entry -> {
                    double newDistance = getShortestDistance(node) + entry.getValue();
                    if (getShortestDistance(entry.getKey()) > newDistance) {
                        distancesMap.put(entry.getKey(), newDistance);
                        predecessorsMap.put(entry.getKey(), node);
                        unsettledNodes.add(entry.getKey());
                    }
                });
    }

    private String getClosestNeighbor(Set<String> nodes) {
        String minimum = null;
        for (String node : nodes) {
            if (minimum == null) {
                minimum = node;
            } else {
                if (getShortestDistance(node) < getShortestDistance(minimum)) {
                    minimum = node;
                }
            }
        }

        return minimum;
    }

    private double getShortestDistance(String target) {
        Double distance = distancesMap.get(target);
        if (distance == null) {
            return Double.MAX_VALUE;
        } else {
            return distance;
        }
    }

    public List<String> getPath(String target) {
        if (predecessorsMap.get(target) == null) {
            return null;
        }
        List<String> path = new LinkedList<>();
        String step = target;
        path.add(step);

        while (predecessorsMap.get(step) != null) {
            step = predecessorsMap.get(step);
            path.add(step);
        }

        Collections.reverse(path);
        return path;
    }

}