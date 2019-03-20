/*
 * Copyright (c) 2018, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * The Universal Permissive License (UPL), Version 1.0
 *
 * Subject to the condition set forth below, permission is hereby granted to any
 * person obtaining a copy of this software, associated documentation and/or
 * data (collectively the "Software"), free of charge and under any and all
 * copyright rights in the Software, and any and all patent rights owned or
 * freely licensable by each licensor hereunder covering either (i) the
 * unmodified Software as contributed to or provided by such licensor, or (ii)
 * the Larger Works (as defined below), to deal in both
 *
 * (a) the Software, and
 *
 * (b) any piece of software and/or hardware listed in the lrgrwrks.txt file if
 * one is included with the Software each a "Larger Work" to which the Software
 * is contributed by such licensors),
 *
 * without restriction, including without limitation the rights to copy, create
 * derivative works of, display, perform, and distribute the Software and make,
 * use, sell, offer for sale, import, export, have made, and have sold the
 * Software and the Larger Work(s), and to sublicense the foregoing rights on
 * either these or other terms.
 *
 * This license is subject to the following condition:
 *
 * The above copyright notice and either this complete permission notice or at a
 * minimum a reference to the UPL must be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.oracle.truffle.api.interop;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.library.ExportLibrary;
import com.oracle.truffle.api.library.ExportMessage;

@ExportLibrary(value = InteropLibrary.class, receiverType = Integer.class)
@SuppressWarnings("unused")
final class DefaultIntegerExports {

    // slow methods
    @ExportMessage
    static boolean isNumber(@SuppressWarnings("unused") Integer receiver) {
        return true;
    }

    @ExportMessage
    static boolean fitsInByte(Integer receiver) {
        int i = receiver;
        byte b = (byte) i;
        return b == i;
    }

    @ExportMessage
    static boolean fitsInShort(Integer receiver) {
        int i = receiver;
        short s = (short) i;
        return s == i;
    }

    @ExportMessage
    static boolean fitsInFloat(Integer receiver) {
        return NumberUtils.inSafeFloatRange(receiver);
    }

    @ExportMessage
    static byte asByte(Integer receiver) throws UnsupportedMessageException {
        int i = receiver;
        byte b = (byte) i;
        if (b == i) {
            return b;
        }
        CompilerDirectives.transferToInterpreter();
        throw UnsupportedMessageException.create();
    }

    @ExportMessage
    static short asShort(Integer receiver) throws UnsupportedMessageException {
        int i = receiver;
        short s = (short) i;
        if (s == i) {
            return s;
        }
        CompilerDirectives.transferToInterpreter();
        throw UnsupportedMessageException.create();
    }

    @ExportMessage
    static float asFloat(Integer receiver) throws UnsupportedMessageException {
        int i = receiver;
        if (NumberUtils.inSafeFloatRange(i)) {
            return i;
        }
        CompilerDirectives.transferToInterpreter();
        throw UnsupportedMessageException.create();
    }

    // fast methods

    @ExportMessage
    static boolean fitsInInt(Integer receiver) {
        return true;
    }

    @ExportMessage
    static boolean fitsInLong(Integer receiver) {
        return true;
    }

    @ExportMessage
    static boolean fitsInDouble(Integer receiver) {
        return true;
    }

    @ExportMessage
    static int asInt(Integer receiver) {
        return receiver;
    }

    @ExportMessage
    static long asLong(Integer receiver) {
        return receiver;
    }

    @ExportMessage
    static double asDouble(Integer receiver) {
        return receiver;
    }
}