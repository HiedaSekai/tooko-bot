package org.bson.codecs.pojo;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

import java.util.HashMap;
import java.util.LinkedList;

public class SubClassPropertyCodecProvider<T> implements PropertyCodecProvider, Codec<T> {

    public Class<T> clazz;

    public LinkedList<Class> subClazz;

    public HashMap<String, Codec> subCodec;

    public SubClassPropertyCodecProvider(Class<T> clazz, LinkedList<Class> subClazz) {

        this.clazz = clazz;

        this.subClazz = subClazz;

    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public <T> Codec<T> get(TypeWithTypeParameters<T> type, PropertyCodecRegistry registry) {

        if (type.getType().equals(clazz)) {

            if (subCodec == null) {

                subCodec = new HashMap<>();

                for (Class subType : subClazz) {

                    subCodec.put(subType.getSimpleName(), registry.get(TypeData.builder(subType).build()));

                }

            }

            return (Codec<T>) this;

        }

        return null;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void encode(final BsonWriter writer, final T absObject, final EncoderContext encoderContext) {

        if (!subCodec.containsKey(absObject.getClass().getSimpleName())) {

            throw new RuntimeException("SubClass " + absObject.getClass().getName() + " not registered");

        }

        writer.writeStartArray();

        writer.writeString(absObject.getClass().getSimpleName());

        subCodec.get(absObject.getClass().getSimpleName()).encode(writer, absObject, encoderContext);

        writer.writeEndArray();

    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public T decode(final BsonReader reader, final DecoderContext context) {

        reader.readStartArray();

        String subType = reader.readString();

        if (!subCodec.containsKey(subType)) {

            throw new RuntimeException("SubClass " + subType + " not registered");

        }

        T obj = ((Codec<T>) subCodec.get(subType)).decode(reader, context);

        reader.readEndArray();

        return obj;

    }

    @Override
    public Class<T> getEncoderClass() {

        return clazz;

    }


}
