/*
 *  *
 * ProActive Parallel Suite(TM): The Java(TM) library for
 *    Parallel, Distributed, Multi-Core Computing for
 *    Enterprise Grids & Clouds
 *
 * Copyright (C) 1997-2011 INRIA/University of
 *                 Nice-Sophia Antipolis/ActiveEon
 * Contact: proactive@ow2.org or contact@activeeon.com
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation; version 3 of
 * the License.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307
 * USA
 *
 * If needed, contact us to obtain a release under GPL Version 2 or 3
 * or a different license than the AGPL.
 *
 *  Initial developer(s):               The ProActive Team
 *                        http://proactive.inria.fr/team_members.htm
 *  Contributor(s):
 *
 *  * $$PROACTIVE_INITIAL_DEV$$
 */
package org.ow2.proactive_grid_cloud_portal.scheduler.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class JobUsage implements IsSerializable {

    private String owner;
    private String project;
    private String jobId;
    private String jobName;
    private long jobDuration;

    private List<TaskUsage> taskUsages = new ArrayList<TaskUsage>();

    public JobUsage(String owner, String project, String jobId, String jobName, long jobDuration) {
        this.owner = owner;
        this.project = project;
        this.jobId = jobId;
        this.jobName = jobName;
        this.jobDuration = jobDuration;
    }

    public JobUsage() {
    }

    public void add(TaskUsage taskUsage) {
        taskUsages.add(taskUsage);
    }

    public String getOwner() {
        return owner;
    }

    public String getProject() {
        return project;
    }

    public String getJobId() {
        return jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public long getJobDuration() {
        return jobDuration;
    }

    public List<TaskUsage> getTaskUsages() {
        return taskUsages;
    }
}
