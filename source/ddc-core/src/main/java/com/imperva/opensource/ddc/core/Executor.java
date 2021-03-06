package com.imperva.opensource.ddc.core;

import com.imperva.opensource.ddc.core.exceptions.AuthenticationException;
import com.imperva.opensource.ddc.core.exceptions.InvalidConnectionException;
import com.imperva.opensource.ddc.core.exceptions.ProtocolException;
import com.imperva.opensource.ddc.core.exceptions.UnknownException;
import com.imperva.opensource.ddc.core.query.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by gabi.beyo on 08/06/2015.
 */
class Executor {

    private static final Logger LOGGER = LoggerFactory.getLogger(Executor.class.getName());

    /**
     * Execute request
     *
     * @param queryRequest the request object containing all the needed parameters
     * @return A {@link QueryResponse} containing the result
     */
    QueryResponse execute(QueryRequest queryRequest) {
        QueryRequestExecutor queryRequestExecutor = queryRequestExecutorFactoryGetInstance().create(queryRequest);
        return queryRequestExecutor.execute();
    }

    void execute(ChangeRequest changeRequest) {
        ChangeRequestExecutor changeRequestExecutor = new ChangeRequestExecutor(changeRequest);
        changeRequestExecutor.execute();
    }


    /**
     * Test endpoint connectivity
     *
     * @param endpoint the final connection-endpoint object
     * @throws AuthenticationException    if credentials doesn't match
     * @throws InvalidConnectionException if server is not reachable
     * @throws ProtocolException          if request is not supported by protocol
     * @throws UnknownException           unknown exception
     */
    ConnectionResponse testConnection(Endpoint endpoint) {
        String host = endpoint.getHost();
        LdapConnectionResult ldapConnectionResult;
        DriverBase driver = new DriverHostResolverDecorator(new DriverRobustDecorator(new Driver()));
        LOGGER.debug("Check connection: " + host);
        ldapConnectionResult = driver.connect(endpoint);
        ConnectionResponse connectionResponse = new ConnectionResponse();
        connectionResponse.setStatuses(ldapConnectionResult.getStatuses());
        return connectionResponse;

    }

    RequestExecutorFactory queryRequestExecutorFactoryGetInstance() {
        return new RequestExecutorFactory();
    }
}
