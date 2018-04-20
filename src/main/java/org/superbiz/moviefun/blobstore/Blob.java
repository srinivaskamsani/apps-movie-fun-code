package org.superbiz.moviefun.blobstore;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public class Blob{

    public final String name;
    public final InputStream inputStream;
    public final String contentType;

    public Blob(String name, InputStream inputStream, String contentType) {
        this.name = name;
        this.inputStream = inputStream;
        this.contentType = contentType;
    }


}
