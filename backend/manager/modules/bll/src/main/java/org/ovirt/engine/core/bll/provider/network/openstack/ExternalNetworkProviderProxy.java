package org.ovirt.engine.core.bll.provider.network.openstack;

import org.ovirt.engine.core.common.businessentities.ExternalNetworkProviderProperties;
import org.ovirt.engine.core.common.businessentities.Provider;
import org.ovirt.engine.core.common.businessentities.network.ExternalSubnet;
import org.ovirt.engine.core.common.businessentities.network.Network;
import org.ovirt.engine.core.common.errors.EngineError;
import org.ovirt.engine.core.common.errors.EngineException;

import com.woorea.openstack.base.client.OpenStackTokenProvider;
import com.woorea.openstack.quantum.Quantum;

public class ExternalNetworkProviderProxy extends BaseNetworkProviderProxy<ExternalNetworkProviderProperties> {
    public ExternalNetworkProviderProxy(Provider<ExternalNetworkProviderProperties> provider) {
        super(provider);
    }

    @Override
    protected void setClientTokenProvider(Quantum client) {
        OpenStackTokenProvider tokenProvider = new ExternalNetworkTokenProvider(provider);
        client.setTokenProvider(tokenProvider);
    }

    @Override
    public String add(Network network) {
        testProviderIsNotReadOnly();
        return super.add(network);
    }

    @Override
    public void remove(String id) {
        testProviderIsNotReadOnly();
        super.remove(id);
    }

    @Override
    public void addSubnet(ExternalSubnet subnet) {
        testProviderIsNotReadOnly();
        super.addSubnet(subnet);
    }

    @Override
    public void removeSubnet(String id) {
        testProviderIsNotReadOnly();
        super.removeSubnet(id);
    }

    private boolean isReadOnly(){
        return provider.getAdditionalProperties().getReadOnly();
    }

    private void testProviderIsNotReadOnly() {
        if (isReadOnly()){
            throw new EngineException(EngineError.NO_IMPLEMENTATION);
        }
    }
}