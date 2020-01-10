package tookox.core.nsfw;

import org.bson.*;
import org.bson.codecs.*;

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
