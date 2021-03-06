/*******************************************************************************
 * Wobly - Wowd's byte-level serialization protocol
 * Copyright 2008-2011 Wowd Inc. All rights reserved.
 * http://code.google.com/p/wobly/
 *
 * Wobly is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 *
 * Wobly is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Wobly. If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package com.wowd.wobly.examples.benchmark.simple;

import com.wowd.wobly.WoblyUtils.Format;
import com.wowd.wobly.WoblyImpl;
import com.wowd.wobly.annotations.WoblyField;
import com.wowd.wobly.annotations.WoblyTypeOptions;

@WoblyTypeOptions(specialFormat = Format.BYTES_SIZE_COMPRESSED)
public class Image extends WoblyImpl
{
	enum Size { SMALL, LARGE, }
	
	@WoblyField(id = -1, required = true)
	String uri;
	@WoblyField(id = 0)
	String title;
	@WoblyField(id = -2, required = true)
	int width;
	@WoblyField(id = -3, required = true)
	int height;
	@WoblyField(id = -4, required = true)
	Size size;
	
	public Image(String uri, String title, int width, int height, Size size)
	{
		this.uri = uri;
		this.title = title;
		this.width = width;
		this.height = height;
		this.size = size;
	}

	
	//-------------- WOBLY AUTO GENERATED CODE FOR SERIALIZATION ----------
	//---------------------------------------------------------------------
	
	public static final com.wowd.wobly.WoblyReader<Image> objectReader = new com.wowd.wobly.WoblyReaderImpl<Image>() {
		@Override
		public Image readObject(java.nio.ByteBuffer buf)
		{
			return read(buf);
		}};
	@Override
	public void write(final java.nio.ByteBuffer buf) {
		try {
			int startPositionMark = buf.position();
			buf.position(buf.position()+1);
			int unknownsCounter = 0;
			if (unknownFields == null)
				unknownsCounter = Integer.MAX_VALUE;
			{
				com.wowd.wobly.WoblyUtils.Buffers.putVInt(buf,this.size.ordinal());
			}
			{
				buf.putInt(this.height);
			}
			{
				buf.putInt(this.width);
			}
			{
				com.wowd.wobly.WoblyUtils.Buffers.putStringUTF8(buf, this.uri, true);
			}
			
			unknownsCounter = writeUnknownsUpTo(unknownsCounter, 0, buf);
			if (this.title != null) {
				buf.put((byte)7);
				com.wowd.wobly.WoblyUtils.Buffers.putStringUTF8(buf, this.title, true);
			}
			writeUnknownsUpTo(unknownsCounter, Integer.MAX_VALUE, buf);
			com.wowd.wobly.WoblyUtils.Buffers.appendVariableSize(buf, startPositionMark);
		} catch (com.wowd.wobly.exceptions.WoblyWriteException e) {
			throw e;
		} catch (java.lang.Throwable t) {
			throw new com.wowd.wobly.exceptions.WoblyWriteException(t);
		}
	}
	private Image(final java.nio.ByteBuffer buf) {
		
		{
			this.size = Size.values()[com.wowd.wobly.WoblyUtils.Buffers.getVInt(buf)];
		}
		
		{
			this.height = buf.getInt();
		}
		
		{
			this.width = buf.getInt();
		}
		
		{
			this.uri = com.wowd.wobly.WoblyUtils.Buffers.getStringUTF8(buf, true);
		}
		int tag = com.wowd.wobly.WoblyUtils.Buffers.getVIntOrMax(buf);
		
		tag = readUnknownsUpTo(tag, 0, buf);
		if (com.wowd.wobly.WoblyUtils.getIDFromTag(tag) > 0)
			this.title = null;
		else {
			this.title = com.wowd.wobly.WoblyUtils.Buffers.getStringUTF8(buf, true);
			tag = com.wowd.wobly.WoblyUtils.Buffers.getVIntOrMax(buf);
		}
		readUnknownsUpTo(tag, Integer.MAX_VALUE, buf);
	}
	@com.wowd.wobly.annotations.ReadStatic
	public static Image read(java.nio.ByteBuffer buf) {
		try {
			int size = com.wowd.wobly.WoblyUtils.Buffers.getVInt(buf);
			int originalLimit = buf.limit();
			int newLimit = buf.position() + size;
			if (newLimit > originalLimit)
				throw new com.wowd.wobly.exceptions.WoblyReadException(newLimit + " " + originalLimit);
			buf.limit(newLimit);
			Image object = new Image(buf);
			buf.limit(originalLimit);
			return object;
		} catch (com.wowd.wobly.exceptions.WoblyReadException e) {
			throw e;
		} catch (java.lang.Throwable t) {
			throw new com.wowd.wobly.exceptions.WoblyReadException(t);
		}
	}
	public static Image read(byte[] buf) {
		return read(java.nio.ByteBuffer.wrap(buf));
	}
	@Override
	public int getSize() {
		int size = 0;
		{
			size += com.wowd.wobly.WoblyUtils.Buffers.sizeVInt(this.size.ordinal());
		}
		{
			size += 4;
		}
		{
			size += 4;
		}
		{
			size += com.wowd.wobly.WoblyUtils.Buffers.sizeStringUTF8(this.uri, true);
		}
		if (this.title != null) {
			size += 1;
			size += com.wowd.wobly.WoblyUtils.Buffers.sizeStringUTF8(this.title, true);
		}
		if (unknownFields != null)
			for (com.wowd.wobly.unknown.UnknownField uf : unknownFields)
				size += uf.getSize();
		size += com.wowd.wobly.WoblyUtils.Buffers.sizeVInt(size);
		return size;
	}
	
	//---------------------------------------------------------------------
	//-------------- END OF AUTO GENERATED CODE FOR SERIALIZATION ---------
}
