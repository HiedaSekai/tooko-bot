package tooko.main.utils.nsfw;

import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

public enum NSRC {

    DRAWINGS, HENTAI, PORN, SEXY;

    public static class CODEC implements Codec<NSRC> {

        @Override
        public NSRC decode(BsonReader bsonReader, DecoderContext decoderContext) {

            if (bsonReader.getCurrentBsonType() == BsonType.STRING) {

                // OLD FORMAT

                try {

                    return valueOf(bsonReader.readString());

                } catch (IllegalArgumentException ignored) {
                }

            } else {

                switch (bsonReader.readInt32()) {

                    case 0:
                        return DRAWINGS;
                    case 1:
                        return HENTAI;
                    case 2:
                        return PORN;
                    case 3:
                        return SEXY;

                }

            }

            return null;

        }

        @Override
        public void encode(BsonWriter bsonWriter, NSRC nsrc, EncoderContext encoderContext) {

            switch (nsrc) {

                case DRAWINGS:
                    bsonWriter.writeInt32(0);
                    break;
                case HENTAI:
                    bsonWriter.writeInt32(1);
                    break;
                case PORN:
                    bsonWriter.writeInt32(2);
                    break;
                case SEXY:
                    bsonWriter.writeInt32(3);
                    break;

            }

        }

        @Override
        public Class<NSRC> getEncoderClass() {

            return NSRC.class;

        }

    }

}