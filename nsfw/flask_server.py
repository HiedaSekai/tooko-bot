import json, requests, base64, sys

from flask import Flask, request
from keras.preprocessing import image

model_server_port = sys.argv[1]
model_name = 'nsfw'

server_host = sys.argv[2]
server_port = int(sys.argv[3])

target_size=(299, 299)

app = Flask(__name__)

def predict_raw(image_array):

    payload = { 
    
        "instances": []
        
    }
    
    for img in image_array:
    
        img = image.img_to_array(img) / 255.
       
        img = img.astype('float16')

        payload['instances'].append({
        
            "input_image": img
            
        })

    r = requests.post('http://127.0.0.1:' + model_server_port + '/v1/models/' + model_name + ':predict', json=payload)
    
    return json.loads(r.content.decode('utf-8'))

@app.route('/predict', methods=['POST'])
def predict():

    image_array = []
    
    for image_base64 in request.json:
    
        image_array.append(image.load_img(BytesIO(base64.b64decode(image_base64)),target_size))
        
    return predict_raw(image_array)
    
@app.route('/predict_local', methods=['POST'])
def predict_local():

    image_array = []
    
    for image_path in request.json:
    
        image_array.append(image.load_img(image_path,target_size))

    return predict_raw(image_array)
 
app.run(host=server_host, port=server_port)