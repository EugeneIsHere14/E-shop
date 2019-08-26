package com.epam.preproduction.koshevyi.gzip;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Optional;

/**
 * Class that gives back an OutputStream to a Servlet or JSP.
 */
public class GZipServletResponseWrapper extends HttpServletResponseWrapper {

    private GZipServletOutputStream gzipOutputStream;
    private PrintWriter printWriter;

    public GZipServletResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    public void close() {
        Optional.ofNullable(printWriter).ifPresent(PrintWriter::close);
        Optional.ofNullable(gzipOutputStream).ifPresent(GZipServletOutputStream::close);
    }

    @Override
    public void flushBuffer() throws IOException {
        Optional.ofNullable(printWriter).ifPresent(PrintWriter::flush);
        Optional.ofNullable(gzipOutputStream).ifPresent(GZipServletOutputStream::flush);
        super.flushBuffer();
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (printWriter != null) {
            throw new IllegalStateException("PrintWriter obtained already - cannot get OutputStream");
        }
        return Optional.ofNullable(gzipOutputStream).orElse(new GZipServletOutputStream(getResponse().getOutputStream()));
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if (printWriter == null && gzipOutputStream != null) {
            throw new IllegalStateException("OutputStream obtained already - cannot get PrintWriter");
        }
        if (printWriter == null) {
            gzipOutputStream = new GZipServletOutputStream(getResponse().getOutputStream());
            printWriter = new PrintWriter(new OutputStreamWriter(gzipOutputStream, getResponse().getCharacterEncoding()));
        }
        return printWriter;
    }

    @Override
    public void setContentLength(int len) {
        throw new UnsupportedOperationException();
    }
}