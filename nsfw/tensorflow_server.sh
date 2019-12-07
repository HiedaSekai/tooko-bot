model_server_port = '11210'

if [ ! -d "nsfw.299x299" ]; then

  wget https://s3.amazonaws.com/nsfwdetector/nsfw.299x299.h5

  python3 export_saved_model.py
  
fi

tensorflow_model_server \
  --model_base_path=$(pwd)/models/nsfw.299x299 \
  --rest_api_port=model_server_port \
  --model_name=nsfw