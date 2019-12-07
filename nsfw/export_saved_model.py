import tensorflow as tf

tf.keras.backend.set_learning_phase(0)

model = tf.keras.models.load_model('nsfw.299x299.h5')

tf.saved_model.simple_save(
  tf.keras.backend.get_session(),
  'models/nsfw.299x299/1',
  inputs={'input_image': model.input},
  outputs={t.name: t for t in model.outputs})