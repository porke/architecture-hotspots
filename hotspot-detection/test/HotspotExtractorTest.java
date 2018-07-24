import org.jgrapht.graph.DirectedMultigraph;

import java.util.ArrayList;
import java.util.HashMap;

public class HotspotExtractorTest {
    @org.junit.Test
    public void extractType0HotspotTest() {
        DirectedMultigraph<FileVertex, DependencyEdge> depGraph = createType0Hotspot();
        HotspotExtractor he = new HotspotExtractor();

        ArrayList<Hotspot> hotspots = he.extractHotspots(depGraph);
    }

    @org.junit.Test
    public void extractType1HotspotTest() {
        DirectedMultigraph<FileVertex, DependencyEdge> depGraph = createType1Hotspot();
        HotspotExtractor he = new HotspotExtractor();

        ArrayList<Hotspot> hotspots = he.extractHotspots(depGraph);
    }

    private DirectedMultigraph<FileVertex, DependencyEdge> createType1Hotspot() {
        HashMap<String, FileVertex> files = new HashMap<>();
        files.put("AbstractActionManager.java", new FileVertex("AbstractActionManager.java"));
        files.put("SubmitAction.java", new FileVertex("SubmitAction.java"));
        files.put("UriAction.java", new FileVertex("UriAction.java"));
        files.put("NamedAction.java", new FileVertex("NamedAction.java"));
        files.put("HideAction.java", new FileVertex("HideAction.java"));
        files.put("ThreadAction.java", new FileVertex("ThreadAction.java"));
        files.put("UndefAction.java", new FileVertex("UndefAction.java"));
        files.put("GoToAction.java", new FileVertex("GoToAction.java"));
        files.put("InvalidAction.java", new FileVertex("InvalidAction.java"));
        files.put("ActionManagerFactory.java", new FileVertex("ActionManagerFactory.java"));
        DirectedMultigraph<FileVertex, DependencyEdge> depGraph = new DirectedMultigraph<>(DependencyEdge.class);
        for (FileVertex v : files.values()) {
            depGraph.addVertex(v);
        }

        addEdgeToGraph(depGraph, files, "SubmitAction.java", "AbstractActionManager.java", Relationship.Inheritance);
        addEdgeToGraph(depGraph, files, "UriAction.java", "AbstractActionManager.java", Relationship.Inheritance);
        addEdgeToGraph(depGraph, files, "NamedAction.java", "AbstractActionManager.java", Relationship.Inheritance);
        addEdgeToGraph(depGraph, files, "HideAction.java", "AbstractActionManager.java", Relationship.Inheritance);
        addEdgeToGraph(depGraph, files, "ThreadAction.java", "AbstractActionManager.java", Relationship.Inheritance);
        addEdgeToGraph(depGraph, files, "GoToAction.java", "AbstractActionManager.java", Relationship.Inheritance);
        addEdgeToGraph(depGraph, files, "InvalidAction.java", "AbstractActionManager.java", Relationship.Inheritance);
        addEdgeToGraph(depGraph, files, "UndefAction.java", "AbstractActionManager.java", Relationship.Inheritance);

        addEdgeToGraph(depGraph, files, "ActionManagerFactory.java", "AbstractActionManager.java", Relationship.Type);
        addEdgeToGraph(depGraph, files, "ActionManagerFactory.java", "SubmitAction.java", Relationship.Call);
        addEdgeToGraph(depGraph, files, "ActionManagerFactory.java", "UriAction.java", Relationship.Call);
        addEdgeToGraph(depGraph, files, "ActionManagerFactory.java", "NamedAction.java", Relationship.Call);
        addEdgeToGraph(depGraph, files, "ActionManagerFactory.java", "HideAction.java", Relationship.Call);
        addEdgeToGraph(depGraph, files, "ActionManagerFactory.java", "ThreadAction.java", Relationship.Call);
        addEdgeToGraph(depGraph, files, "ActionManagerFactory.java", "GoToAction.java", Relationship.Call);
        addEdgeToGraph(depGraph, files, "ActionManagerFactory.java", "InvalidAction.java", Relationship.Call);
        addEdgeToGraph(depGraph, files, "ActionManagerFactory.java", "UndefAction.java", Relationship.Call);

        return depGraph;
    }

    private DirectedMultigraph<FileVertex, DependencyEdge> createType0Hotspot() {
        HashMap<String, FileVertex> files = new HashMap<>();
        DirectedMultigraph<FileVertex, DependencyEdge> depGraph = new DirectedMultigraph<>(DependencyEdge.class);
        files.put("LabelVisitor.java", new FileVertex("LabelVisitor.java"));
        files.put("Label.java", new FileVertex("Label.java"));
        files.put("LabelExpression.java", new FileVertex("LabelExpression.java"));
        files.put("LabelAtom.java", new FileVertex("LabelAtom.java"));
        for (FileVertex v : files.values()) {
            depGraph.addVertex(v);
        }

        addEdgeToGraph(depGraph, files, "Label.java", "LabelVisitor.java", Relationship.Type);
        addEdgeToGraph(depGraph, files,"LabelExpression.java", "LabelVisitor.java", Relationship.Type);
        addEdgeToGraph(depGraph, files,"LabelAtom.java", "LabelVisitor.java", Relationship.Type);
        addEdgeToGraph(depGraph, files,"LabelExpression.java", "Label.java", Relationship.Inheritance);
        addEdgeToGraph(depGraph, files,"LabelAtom.java", "Label.java", Relationship.Inheritance);
        addEdgeToGraph(depGraph, files,"Label.java", "LabelExpression.java", Relationship.Type);
        addEdgeToGraph(depGraph, files,"Label.java", "LabelAtom.java", Relationship.Type);
        addEdgeToGraph(depGraph, files,"LabelVisitor.java", "LabelExpression.java", Relationship.Type);
        addEdgeToGraph(depGraph, files,"LabelVisitor.java", "LabelAtom.java", Relationship.Type);

        return depGraph;
    }

    private void addEdgeToGraph(DirectedMultigraph<FileVertex, DependencyEdge> depGraph, HashMap<String, FileVertex> files, String from, String to, Relationship rel) {
        depGraph.addEdge(
                files.get(from),
                files.get(to),
                new DependencyEdge(files.get(from),  files.get(to),  rel, 1));
    }
}