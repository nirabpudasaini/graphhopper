/*
 *  Licensed to Peter Karich under one or more contributor license 
 *  agreements. See the NOTICE file distributed with this work for 
 *  additional information regarding copyright ownership.
 * 
 *  Peter Karich licenses this file to you under the Apache License, 
 *  Version 2.0 (the "License"); you may not use this file except 
 *  in compliance with the License. You may obtain a copy of the 
 *  License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.graphhopper.storage.index;

import com.graphhopper.storage.Graph;
import com.graphhopper.storage.MMapDirectory;
import com.graphhopper.storage.RAMDirectory;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Peter Karich
 */
public class Location2IDQuadtreeTest extends AbstractLocation2IDIndexTester {

    @Override
    public Location2IDIndex createIndex(Graph g, int resolution) {
        return new Location2IDQuadtree(g, new MMapDirectory(location + "loc2idIndex")).resolution(resolution).prepareIndex();
    }

    @Test
    public void testNormedDist() {
        Location2IDQuadtree index = new Location2IDQuadtree(createGraph(), new RAMDirectory());
        index.initAlgo(5, 6);
        assertEquals(1, index.normedDist(0, 1), 1e-6);
        assertEquals(2, index.normedDist(0, 7), 1e-6);
        assertEquals(2, index.normedDist(7, 2), 1e-6);
        assertEquals(1, index.normedDist(7, 1), 1e-6);
        assertEquals(4, index.normedDist(13, 25), 1e-6);
        assertEquals(8, index.normedDist(15, 25), 1e-6);
    }
}