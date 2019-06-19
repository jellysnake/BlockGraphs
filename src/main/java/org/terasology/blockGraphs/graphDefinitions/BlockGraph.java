/*
 * Copyright 2018 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.blockGraphs.graphDefinitions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.terasology.blockGraphs.graphDefinitions.nodes.EdgeNode;
import org.terasology.blockGraphs.graphDefinitions.nodes.GraphNode;
import org.terasology.blockGraphs.graphDefinitions.nodes.JunctionNode;
import org.terasology.blockGraphs.graphDefinitions.nodes.NodeType;
import org.terasology.blockGraphs.graphDefinitions.nodes.TerminusNode;
import org.terasology.world.block.BlockUri;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents an instance of a block graph.
 * All block graphs use this class, but have differing {@link JunctionNode} implementations
 */
public class BlockGraph {
    private static final Logger logger = LoggerFactory.getLogger(BlockGraph.class);

    private GraphType graphType;
    private GraphUri uri;

    private int nextId = 1;
    private Map<Integer, GraphNode> nodes = new HashMap<>();

    public BlockGraph(GraphType graphType, GraphUri uri) {
        this.graphType = graphType;
        this.uri = uri;
    }

    public GraphNode getNode(int id) {
        return nodes.get(id);
    }

    public <T extends GraphNode> T getNodeAsType(int id) {
        return (T) getNode(id);
    }

    public GraphUri getUri() {
        return uri;
    }

    public GraphType getGraphType() {
        return graphType;
    }


    /**
     * Creates a new node for the given block.
     * <p>
     * Will fail if there is no node type linked to the block or the node type could not be instantiated.
     *
     * @param block The block to create a node for
     * @return The new node type
     */
    public JunctionNode createJunctionNode(BlockUri block) {
        JunctionNode node = new JunctionNode(uri, nextId++, graphType.getDefinitionId(block));
        nodes.put(node.nodeId, node);
        return node;
    }

    /**
     * @see #createJunctionNode(BlockUri)
     */
    public EdgeNode createEdgeNode(BlockUri block) {
        EdgeNode node = new EdgeNode(uri, nextId++, graphType.getDefinitionId(block));
        nodes.put(node.nodeId, node);
        return node;
    }

    /**
     * @see #createJunctionNode(BlockUri)
     */
    public TerminusNode createTerminusNode(BlockUri block) {
        TerminusNode node = new TerminusNode(uri, nextId++, graphType.getDefinitionId(block));
        nodes.put(node.nodeId, node);
        return node;
    }

    public GraphNode createNode(BlockUri block, NodeType type) {
        switch (type) {
            case JUNCTION:
                return createJunctionNode(block);
            case EDGE:
                return createEdgeNode(block);
            case TERMINUS:
                return createTerminusNode(block);
            default:
                return null;
        }
    }

    /**
     * Handles the removal of the node from the graph
     *
     * @param node The node to remove
     */
    public void removeNode(GraphNode node) {
        /* Remove all connections into this node */
        node.getConnections().forEach(node::unlinkNode);
        /* Remove all connections out of this node */
        node.unlinkAll();
        /* Remove the node */
        nodes.remove(node.nodeId);
    }
}
