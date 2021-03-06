package com.imperva.opensource.ddc.core.query;

import com.imperva.opensource.ddc.core.commons.Utils;
import com.imperva.opensource.ddc.core.language.QueryAssembler;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by gabi.beyo on 18/06/2015.
 */
public abstract class Request implements AutoCloseable {

    private DirectoryType directoryType = DirectoryType.MS_ACTIVE_DIRECTORY;
    private Boolean ignoreSSLValidations = null;

    /**
     * @param directoryType A {@link DirectoryType} enumerator which indicates the concrete Directory to connect
     */
    public void setDirectoryType(DirectoryType directoryType) {
        this.directoryType = directoryType;
    }

    /**
     * @return A {@link DirectoryType} enumerator which indicates the concrete Directory to connect
     */
    public DirectoryType getDirectoryType() {
        return this.directoryType;
    }

    public void setIgnoreSSLValidations(boolean ignoreSSLValidations) {
        this.ignoreSSLValidations = ignoreSSLValidations;
    }

    public Boolean isIgnoreSSLValidations() {
        return ignoreSSLValidations;
    }

    @Override
    public abstract void close();
}