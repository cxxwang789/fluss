/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.fluss.fs;

import com.alibaba.fluss.annotation.Internal;
import com.alibaba.fluss.utils.WrappingProxy;

import java.io.IOException;

import static com.alibaba.fluss.utils.Preconditions.checkNotNull;

/** Simple forwarding wrapper around {@link FSDataInputStream}. */
@Internal
public class FSDataOutputStreamWrapper extends FSDataOutputStream
        implements WrappingProxy<FSDataOutputStream> {

    protected final FSDataOutputStream outputStream;

    public FSDataOutputStreamWrapper(FSDataOutputStream outputStream) {
        this.outputStream = checkNotNull(outputStream);
    }

    @Override
    public long getPos() throws IOException {
        return outputStream.getPos();
    }

    @Override
    public void flush() throws IOException {
        outputStream.flush();
    }

    @Override
    public void write(int b) throws IOException {
        outputStream.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        outputStream.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        outputStream.write(b, off, len);
    }

    @Override
    public void close() throws IOException {
        outputStream.close();
    }

    @Override
    public FSDataOutputStream getWrappedDelegate() {
        return outputStream;
    }
}
