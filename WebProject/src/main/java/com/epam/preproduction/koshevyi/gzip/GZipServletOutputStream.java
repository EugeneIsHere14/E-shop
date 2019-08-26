package com.epam.preproduction.koshevyi.gzip;

import com.epam.preproduction.koshevyi.filter.LocaleFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Class that compresses the content written to it.
 */
class GZipServletOutputStream extends ServletOutputStream {

    private static final Logger LOGGER = LogManager.getLogger(LocaleFilter.class);

    private GZIPOutputStream gzipOutputStream;

    public GZipServletOutputStream(OutputStream output) throws IOException {
        super();
        gzipOutputStream = new GZIPOutputStream(output);
    }

    @Override
    public void close() {
        try {
            gzipOutputStream.close();
        } catch (IOException e) {
            LOGGER.error("Exception occurred: " + e.getMessage());
        }
    }

    @Override
    public void flush() {
        try {
            gzipOutputStream.flush();
        } catch (IOException e) {
            LOGGER.error("Exception occurred: " + e.getMessage());
        }
    }

    @Override
    public void write(byte b[]) throws IOException {
        gzipOutputStream.write(b);
    }

    @Override
    public void write(byte b[], int off, int len) throws IOException {
        gzipOutputStream.write(b, off, len);
    }

    @Override
    public void write(int b) throws IOException {
        gzipOutputStream.write(b);
    }

    @Override
    public boolean isReady() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {
        throw new UnsupportedOperationException();
    }
}