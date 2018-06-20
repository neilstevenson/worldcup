package com.hazelcast.demo.worldcup;

import java.io.IOException;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;

import lombok.Data;

/**
 * <p>
 * Opinion about a game, good words, bad words, about each side.
 * </p>
 * <p>
 * This class is not {@link java.io.Serializable}. Instead it
 * is {@link com.hazelcast.nio.serialization.IdentifiedDataSerializable}.
 * This allows non-Java clients to read it too, but it means we hjave
 * to code the serialization/de-serialization methods ourselves,
 * since {@link java.io.Serializable} is only for Java.
 * </p>
 */
@Data
public class Sentiment implements IdentifiedDataSerializable {

	private long   homePositive;
	private long   homeNeutral;
	private long   homeNegative;
	
	private long   awayPositive;
	private long   awayNeutral;
	private long   awayNegative;

	@Override
	public void writeData(ObjectDataOutput objectDataOutput) throws IOException {
		objectDataOutput.writeLong(this.homeNegative);
		objectDataOutput.writeLong(this.homeNeutral);
		objectDataOutput.writeLong(this.homePositive);
		objectDataOutput.writeLong(this.awayNegative);
		objectDataOutput.writeLong(this.awayNeutral);
		objectDataOutput.writeLong(this.awayPositive);
	}
	@Override
	public void readData(ObjectDataInput objectDataInput) throws IOException {
		this.homeNegative = objectDataInput.readLong();
		this.homeNeutral = objectDataInput.readLong();
		this.homePositive = objectDataInput.readLong();
		this.awayNegative = objectDataInput.readLong();
		this.awayNeutral = objectDataInput.readLong();
		this.awayPositive = objectDataInput.readLong();
	}
	@Override
	public int getFactoryId() {
		return ApplicationConstants.MY_DATA_SERIALIZABLE_FACTORY;
	}
	@Override
	public int getId() {
		return ApplicationConstants.CLASS_SENTIMENT;
	}

}
