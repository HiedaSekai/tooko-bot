package org.bson.codecs.pojo;

import cn.hutool.core.util.*;
import org.bson.*;
import org.bson.codecs.*;

import java.util.*;

public class ArrayPropertyCodecProvider<E> implements PropertyCodecProvider {

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public <T> Codec<T> get(TypeWithTypeParameters<T> type, PropertyCodecRegistry registry) {

        if (type.getType().isArray()) {

            Class<E> clazz = (Class<E>) type.getType().getComponentType();

            Codec<E> codec = registry.get(TypeData.builder(clazz).build());

            return new ArrayCodec<T, E>(type.getType(), clazz, codec);

        }

        return null;
    }

    private static class ArrayCodec<T, E> implements Codec<T> {

        private final Class<T> arrClazz;
        private final Class<E> clazz;
        private final Codec<E> codec;

        public ArrayCodec(Class<T> arrClazz, Class<E> clazz, Codec<E> codec) {

            this.arrClazz = arrClazz;
            this.clazz = clazz;
            this.codec = codec;
        }

        @SuppressWarnings({"rawtypes", "unchecked"})
        @Override
        public void encode(final BsonWriter writer, final T arrayObject, final EncoderContext encoderContext) {

            writer.writeStartArray();

            E[] array = (E[]) arrayObject;

            for (int index = 0; index < array.length; index++) {

                if (array[index] == null) {

                    writer.writeNull();


                } else {

                    codec.encode(writer, array[index], encoderContext);

                }

            }

            writer.writeEndArray();

        }

        @SuppressWarnings({"rawtypes", "unchecked"})
        @Override
        public T decode(final BsonReader reader, final DecoderContext context) {

            LinkedList<E> array = new LinkedList<>();

            reader.readStartArray();

            while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {

                if (reader.getCurrentBsonType() == BsonType.NULL) {

                    array.add(null);

                    reader.readNull();

                } else {

                    array.add(codec.decode(reader, context));

                }

            }

            reader.readEndArray();

            return (T) array.toArray(ArrayUtil.newArray(clazz, array.size()));

        }

        @Override
        public Class<T> getEncoderClass() {

            return arrClazz;

        }

    }

}
