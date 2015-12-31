/*
    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU General Public
    License as published by the Free Software Foundation; either
    version 2 of the license, or (at your option) any later version.
*/

package org.gjt.jclasslib.structures.constants

import org.gjt.jclasslib.structures.CPInfo
import org.gjt.jclasslib.structures.ConstantType
import org.gjt.jclasslib.structures.InvalidByteCodeException

import java.io.DataInput
import java.io.DataOutput
import java.io.IOException

/**
 * Describes a CONSTANT_MethodType_info constant pool data structure.

 * @author [Hannes Kegel](mailto:jclasslib@ej-technologies.com)
 */
class ConstantMethodTypeInfo : CPInfo() {

    /**
     * Index of the constant pool entry containing the descriptor of the method.
     */
    var descriptorIndex: Int = 0

    override val constantType: ConstantType
        get() = ConstantType.METHOD_TYPE

    override val verbose: String
        @Throws(InvalidByteCodeException::class)
        get() = name

    /**
     * The descriptor.
     */
    val name: String
        @Throws(InvalidByteCodeException::class)
        get() = classFile.getConstantPoolUtf8Entry(descriptorIndex).string

    @Throws(InvalidByteCodeException::class, IOException::class)
    override fun read(input: DataInput) {
        descriptorIndex = input.readUnsignedShort()
        debugRead()
    }

    @Throws(InvalidByteCodeException::class, IOException::class)
    override fun write(output: DataOutput) {
        output.writeByte(ConstantType.METHOD_TYPE.tag)
        output.writeShort(descriptorIndex)
        debugWrite()
    }

    override fun equals(other: Any?): Boolean {
        if (other !is ConstantMethodTypeInfo) {
            return false
        }
        return super.equals(other) && other.descriptorIndex == descriptorIndex
    }

    override fun hashCode(): Int = super.hashCode() xor descriptorIndex

    override val debugMessage: String
        get() = "$constantType with descriptor_index $descriptorIndex"
}
