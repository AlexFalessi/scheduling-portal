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
package org.ow2.proactive_grid_cloud_portal.common.server;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import org.apache.commons.io.FileUtils;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.ow2.proactive.http.HttpClientBuilder;
import org.ow2.proactive_grid_cloud_portal.common.shared.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Return the content of the motd.txt file,
 * of the reponse of the *.motd.url if defined
 *
 * @author mschnoor
 */
@SuppressWarnings("serial")
public class MotdServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(MotdServlet.class);

    private static final String MOTD_FILE_NAME = "motd.txt";

    private static long lastModified = 0L;

    private static String fileContent = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("text/html");

        try {
            String url = Config.get().getMotdUrl();

            // no MOTD URL : use local file
            if (url == null || url.trim().length() == 0) {

                File f = new File(this.getServletContext().getRealPath(MOTD_FILE_NAME));
                long ft = f.lastModified();
                if (ft != lastModified) {
                    lastModified = ft;
                    try {
                        fileContent = FileUtils.readFileToString(f);
                    } catch (IOException e) {
                        LOGGER.debug("Failed to read MOTD file", e);
                        response.getWriter().write("");
                        return;
                    }
                }
                response.getWriter().write(fileContent);

            } else {
                try (CloseableHttpClient httpclient = new HttpClientBuilder().allowAnyCertificate(Config.get()
                                                                                                        .isHttpsAllowAnyCertificate())
                                                                             .allowAnyHostname(Config.get()
                                                                                                     .isHttpsAllowAnyHostname())
                                                                             .build()) {
                    HttpGet httpget = new HttpGet(url);

                    ResponseHandler<String> responseHandler = new BasicResponseHandler();
                    String responseBody = httpclient.execute(httpget, responseHandler);
                    response.setStatus(Response.Status.OK.getStatusCode());
                    response.getWriter().write(responseBody);
                } catch (Exception e) {
                    response.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
                    response.getWriter().write("Server error");
                }
            }

        } catch (IOException e) {
            LOGGER.debug("Failed to provide MOTD file", e);
        }
    }
}
