/*
 * Copyright (c) 2019 - 2020 KazamaWataru
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
