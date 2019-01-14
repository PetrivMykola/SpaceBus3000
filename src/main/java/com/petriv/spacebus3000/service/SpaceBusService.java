package com.petriv.spacebus3000.service;

import com.petriv.spacebus3000.model.Vertex;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
public class SpaceBusService {

    private static final String PATH_TO_FILE = "./Spacebus300.txt";
    private static final Map<Vertex, List<Vertex>> graph = new HashMap<>();

    @PostConstruct
    public void init() {
        readFile();
    }

    public Set<String> getSpacePorts() {
        return graph.keySet().stream()
                .map(Vertex::getStation)
                .collect(Collectors.toSet());
    }

    public boolean isConnection(String from, String to) {
        if (graph.containsKey(Vertex.of(from)) & graph.containsKey(Vertex.of(to))) {
            return depthFirstTraversal(from, to);
        } else {
            return false;
        }

    }

    @SneakyThrows
    private static void readFile() {
        File file = new ClassPathResource(PATH_TO_FILE).getFile();
        List<String> lines = FileUtils.readLines(file, "UTF8");
        lines.forEach(addStations);
        lines.forEach(buildRoutes);
    }

    private static Consumer<String> addStations = s -> {
        String[] arr = s.split(", ");
        graph.put(Vertex.of(arr[0]), new ArrayList<>());
        graph.put(Vertex.of(arr[1]), new ArrayList<>());
    };

    private static Consumer<String> buildRoutes = s -> {
        String[] arr = s.split(", ");
        graph.get(Vertex.of(arr[0])).add(Vertex.of(arr[1]));
        graph.get(Vertex.of(arr[1])).add(Vertex.of(arr[0]));
    };

    private static boolean depthFirstTraversal(String from, String to) {
        Set<String> visited = new LinkedHashSet<>();
        Stack<String> stack = new Stack<>();
        stack.push(from);
        while (!stack.isEmpty()) {
            String vertex = stack.pop();
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                for (Vertex v : graph.get(Vertex.of(vertex))) {
                    stack.push(v.getStation());
                    if (v.getStation().equals(to)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}


