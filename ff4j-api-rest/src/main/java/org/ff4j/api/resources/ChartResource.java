package org.ff4j.api.resources;

/*-
 * #%L
 * ff4j-api-rest
 * %%
 * Copyright (C) 2013 - 2019 FF4J
 * %%
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
 * #L%
 */

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.swagger.annotations.Api;

/** 
 * Expos API to draw charts (use by JavaScript frameworks)
 *
 * @author Cedrick LUNVEN (@clunven)
 */
@Api(ChartResource.PATH)
@Path(ChartResource.PATH)
@Produces(MediaType.APPLICATION_JSON)
public class ChartResource  extends AbstractResource {
  
    /** Path for the resource. */
    public static final String PATH = FF4jResource.PATH + "/charts";
    
    /**
     * Provide core information on store and available sub resources.
     *
    @POST
    @Path("/{uid}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value= "Display <b>Monitoring</b> for a <b><u>single</u></b> feature",
                  notes= "Each feature will display a pieChart and a barChart for hits",
                  response=FeatureMonitoringApiBean.class)
    @ApiResponses({
        @ApiResponse(code = 200, message= "Status of current ff4j monitoring bean", response=FeatureMonitoringApiBean.class), 
        @ApiResponse(code = 404, message= "Feature not found", response=String.class) })
    public Response getFeatureMonitoring(
            @ApiParam(required=true, name="uid", value="Unique identifier of feature")
            @PathParam("uid") String uid, 
            @ApiParam(required=false, name="start", value="Start of window <br>(default is today 00:00)")
            @QueryParam(PARAM_START) Long start,
            @ApiParam(required=false, name="end", value="End  of window <br>(default is tomorrow 00:00)")
            @QueryParam(PARAM_END) Long end) {
        
        AuditT
        if (!ff4j.getFeatureStore().exist(uid)) {
            String errMsg = new FeatureNotFoundException(uid).getMessage();
            return Response.status(Response.Status.NOT_FOUND).entity(errMsg).build();
        }
        // Today
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        if (start == null) {
            start = c.getTimeInMillis();
        }
        // Tomorrow 00:00
        Calendar c2 = Calendar.getInstance();
        c2.setTime(new Date(System.currentTimeMillis() + 1000 * 3600 * 24));
        c2.set(Calendar.HOUR_OF_DAY, 0);
        c2.set(Calendar.MINUTE, 0);
        c2.set(Calendar.SECOND, 0);
        if (end == null) {
            end = c2.getTimeInMillis();
        }
        // Build response
        FeatureMonitoringApiBean fmab = new FeatureMonitoringApiBean(uid);
        int hitcount = 0;
        for (PieSectorApiBean sec : fmab.getEventsPie().getSectors()) {
            hitcount+= sec.getValue();
        }
        fmab.setHitCount(hitcount);
        return Response.ok().entity(fmab).build();
    }*/
    

}