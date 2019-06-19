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

import org.terasology.blockGraphs.graphDefinitions.nodes.JunctionNode;
import org.terasology.entitySystem.Component;

/**
 * Indicates that a block is apart of a graph.
 * This should only be placed onto block entities
 *
 * @see BlockGraph
 * @see JunctionNode
 */
public class GraphNodeComponent implements Component {
    /**
     * The URI of the graph that this block belongs to
     */
    public GraphUri graphUri;
    /**
     * The ID of the node that this graph belongs to.
     */
    public int nodeId;
}
