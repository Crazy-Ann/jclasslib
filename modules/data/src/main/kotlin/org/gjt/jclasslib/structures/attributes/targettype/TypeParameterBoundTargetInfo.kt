/*
 This library is free software; you can redistribute it and/or
 modify it under the terms of the GNU General Public
 License as published by the Free Software Foundation; either
 version 2 of the license, or (at your option) any later version.
 */

package org.gjt.jclasslib.structures.attributes.targettype

import org.gjt.jclasslib.structures.InvalidByteCodeException

import java.io.DataInput
import java.io.DataOutput
import java.io.IOException

/**
 * Target info for a TypeAnnotation structure with a parameter index an a bound index.
 */
class TypeParameterBoundTargetInfo : TargetInfo() {

    var typeParameterIndex: Int = 0
    var boundIndex: Int = 0

    @Throws(InvalidByteCodeException::class, IOException::class)
    override fun read(input: DataInput) {
        typeParameterIndex = input.readUnsignedByte()
        boundIndex = input.readUnsignedByte()
    }

    @Throws(InvalidByteCodeException::class, IOException::class)
    override fun write(output: DataOutput) {
        output.writeByte(typeParameterIndex)
        output.writeByte(boundIndex)
    }

    override val length: Int
        get() = 2

    override val verbose: String
        get() = "parameter index $typeParameterIndex, bound index $boundIndex"

    override val debugMessage: String
        get() = "TypeParameterBoundTargetInfo with $verbose"
}
