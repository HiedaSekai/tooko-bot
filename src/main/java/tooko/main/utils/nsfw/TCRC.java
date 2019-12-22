package tooko.main.utils.nsfw;

import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

public enum TCRC {

    POLITICS, SPAM, PORN, AD;

    public static class CODEC implements Codec<TCRC> {

        @Override
        public TCRC decode(BsonReader bsonReader, DecoderContext decoderContext) {

            if (bsonReader.getCurrentBsonType() == BsonType.STRING) {

                // OLD FORMAT

                try {

                    return valueOf(bsonReader.readString());

                } catch (IllegalArgumentException ignored) {
                }

            } else {

                switch (bsonReader.readInt32()) {

                    case 0:
                        return POLITICS;
                    case 1:
                        return SPAM;
                    case 2:
                        return PORN;
                    case 3:
                        return AD;

                }

            }

            return null;

        }

        @Override
        public void encode(BsonWriter bsonWriter, TCRC tcrc, EncoderContext encoderContext) {

            switch (tcrc) {

                case POLITICS:
                    bsonWriter.writeInt32(0);
                    break;
                case SPAM:
                    bsonWriter.writeInt32(1);
                    break;
                case PORN:
                    bsonWriter.writeInt32(2);
                    break;
                case AD:
                    bsonWriter.writeInt32(3);
                    break;

            }

        }

        @Override
        public Class<TCRC> getEncoderClass() {

            return TCRC.class;

        }

    }

}
