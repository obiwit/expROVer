# expROVer
The ExpROVer software intents to add value to the ROV market by enabling the use an open source solution enabling an easy and intuitive user interface, available anywhere and at anytime and ready to support extensions from the open source community.

## Getting Started
Every module of the project contains READMEs that better detail that module. This page is intended as a general overview of the system.


### Installing
To replicate the work on this repository, you need to follow the installation proccess.


1. Make sure you have ROS installed, if not, click the following link:

    http://wiki.ros.org/ROS/Installation

2. Build all ROS catkin packages:
```bash
$ ./build.sh
```

3. After the build is complete, the build script will output a sequence of
   commands that you should place in your .bashrc (or similar) file to ensure
   that your system can "see" the newly built components. The following is an
   example sequence of commands that you should place in your .bashrc file:
```bash
CATKIN_WS1_SETUP=/path/to/gt-ros-pkg/setenv.sh
if [ -f ${CATKIN_WS1_SETUP} ]; then
source ${CATKIN_WS1_SETUP}
fi
```

4. Verify that you are connected to the ROV and to a standard controller (ex: Dualshock, Logitech, etc.)

5. Launch the control interface
```bash
$ rosrun videoray deploy.sh
```

## Protocols

### Computer Vision
Computer Vision isn't run in real time due to the latency it would cause. It can be run on the video captured from the ROV (during operation, the video feed from the ROV is both streamed and saved to a hard drive, making this possible).

To execute fish detection in a video, run the following command:
```bash
python yolo_video.py --input <input_video_path> --output <output_path>
# OR
python yolo_video.py [OPTIONS...] --image, for image detection mode
```

### Server Commands
The communication with the server is based on JSON.
#### Movement
The movement need a vector of three axis (movX, movY, movZ) 
```JSON
{
    "type": "move",
    "data":
    {
        "x": "movX",
        "y": "movY",
        "z": "movZ"
    }
}
```

#### Rotation
The rotation is made only by the vertical axis, and is given by degrees
```JSON
{
    "type": "rotate",
    "data":
    {
        "rotation": "axisZ",
    }
}
```
#### Lights
```JSON
{
    "type": "lights",
    "data":
    {
        "active": "true/false"
    }
}

```

## License
This project is under the license of **GPL-3.0** you can see more information in [LICENSE](LICENSE)

## More Information
You can find more information in [our website](http://xcoa.av.it.pt/~pei2018-2019_g010/).

## Acknowledgments
* Mentors
* Hard work
* ...

