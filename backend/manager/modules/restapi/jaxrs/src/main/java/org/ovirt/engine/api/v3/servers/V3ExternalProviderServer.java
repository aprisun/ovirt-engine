/*
Copyright (c) 2016 Red Hat, Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package org.ovirt.engine.api.v3.servers;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.ovirt.engine.api.model.Actionable;
import org.ovirt.engine.api.resource.ExternalProviderResource;
import org.ovirt.engine.api.v3.V3Server;
import org.ovirt.engine.api.v3.types.V3Action;

@Produces({"application/xml", "application/json"})
public class V3ExternalProviderServer extends V3Server<ExternalProviderResource> {
    public V3ExternalProviderServer(ExternalProviderResource delegate) {
        super(delegate);
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    @Actionable
    @Path("importcertificates")
    public Response importCertificates(V3Action action) {
        return adaptAction(getDelegate()::importCertificates, action);
    }

    @POST
    @Consumes({"application/xml", "application/json"})
    @Actionable
    @Path("testconnectivity")
    public Response testConnectivity(V3Action action) {
        return adaptAction(getDelegate()::testConnectivity, action);
    }

    @Path("certificates")
    public V3ExternalProviderCertificatesServer getCertificatesResource() {
        return new V3ExternalProviderCertificatesServer(getDelegate().getCertificatesResource());
    }

    @Path("{action: (importcertificates|testconnectivity)}/{oid}")
    public V3ActionServer getActionResource(@PathParam("action") String action, @PathParam("oid") String oid) {
        return new V3ActionServer(getDelegate().getActionResource(action, oid));
    }
}
