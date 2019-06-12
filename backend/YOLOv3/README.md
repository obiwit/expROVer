# Fish Detection using YOLOv3

## Introduction

Adapted and developed from the original [qqwweee's Keras implementation of YOLOv3, available on github](https://github.com/qqwweee/keras-yolo3).

---

## Quick Start

You will first need to unzip the file `model_data/model_weights`, to generate the neural network's weights.

Afterwards, to execute fish detection in a video, run the following command:
```bash
python yolo_video.py --input <input_video_path> --output <output_path>
# OR
python yolo_video.py [OPTIONS...] --image, for image detection mode
```

### Usage

*(From the original repository:)*

Use --help to see usage of yolo_video.py:
```
usage: yolo_video.py [-h] [--model MODEL] [--anchors ANCHORS]
                     [--classes CLASSES] [--gpu_num GPU_NUM] [--image]
                     [--input] [--output]

positional arguments:
  --input        Video input path
  --output       Video output path

optional arguments:
  -h, --help         show this help message and exit
  --model MODEL      path to model weight file, default model_data/yolo.h5
  --anchors ANCHORS  path to anchor definitions, default
                     model_data/yolo_anchors.txt
  --classes CLASSES  path to class definitions, default
                     model_data/coco_classes.txt
  --gpu_num GPU_NUM  Number of GPU to use, default 1
  --image            Image detection mode, will ignore all positional arguments
```
Use `--gpu_num N` to use N GPUs. It is passed to the [Keras multi_gpu_model()](https://keras.io/utils/#multi_gpu_model).
