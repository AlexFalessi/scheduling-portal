/*
 * ProActive Parallel Suite(TM):
 * The Open Source library for parallel and distributed
 * Workflows & Scheduling, Orchestration, Cloud Automation
 * and Big Data Analysis on Enterprise Grids & Clouds.
 *
 * Copyright (c) 2007 - 2017 ActiveEon
 * Contact: contact@activeeon.com
 *
 * This library is free software: you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation: version 3 of
 * the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 * If needed, contact us to obtain a release under GPL Version 2 or 3
 * or a different license than the AGPL.
 */
package org.ow2.proactive_grid_cloud_portal.scheduler.shared;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.rpc.IsSerializable;


/**
 * @author ActiveEon Team
 * @since Mar 3, 2017
 */
public class SharedProperties implements IsSerializable {

    private Map<String, String> properties;

    private Map<String, String> portalProperties;

    public SharedProperties() {
        super();
        this.properties = new HashMap<>();
        this.portalProperties = new HashMap<>();
    }

    public SharedProperties(Map<String, String> properties, Map<String, String> portalProperties) {
        super();
        this.properties = properties;
        this.portalProperties = portalProperties;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public Map<String, String> getPortalProperties() {
        return portalProperties;
    }

    public void setPortalProperties(Map<String, String> portalProperties) {
        this.portalProperties = portalProperties;
    }

}
