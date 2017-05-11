package ru.incretio.juja.sqlcmd;

import java.io.ByteArrayOutputStream;

class TestOutputStream extends ByteArrayOutputStream {
    public String getData() {
        return new String(this.toByteArray());
    }
}
