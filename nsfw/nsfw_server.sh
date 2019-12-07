model_server_port = '11220'

nsfw_servsr_host = '127.0.0.1'
nsfw_server_port = '11221'

if [ ! -d "models" ]; then

  wget https://s3.amazonaws.com/nsfwdetector/nsfw.299x299.h5

  python3 export_saved_model.py
  
fi

tensorflow_model_server \
  --model_base_path=$(pwd)/models/nsfw.299x299 \
  --rest_api_port=model_server_port \
  --model_name=nsfw &
  
python3 flask_server.py model_server_port nsfw_servsr_host nsfw_server_port