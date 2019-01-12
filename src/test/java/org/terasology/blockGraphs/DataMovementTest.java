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
package org.terasology.blockGraphs;

import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.terasology.blockGraphs.dataMovement.GraphMovementSystem;
import org.terasology.blockGraphs.graphDefinitions.BlockGraph;
import org.terasology.moduletestingenvironment.ModuleTestingEnvironment;

import java.util.Set;

public class DataMovementTest extends ModuleTestingEnvironment {
    private static final Logger logger = LoggerFactory.getLogger(DataMovementTest.class);

    private BlockGraph graph;
    private GraphMovementSystem movementSystem;

    @Override
    public Set<String> getDependencies() {
        return Sets.newHashSet("BlockGraphs");
    }

    @Before
    public void initialize() {
        movementSystem = getHostContext().get(GraphMovementSystem.class);
    }


    @Test
    public void someTest() {
        logger.info(movementSystem.getTime() + "");
    }
}
