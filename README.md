# expROVer
The ExpROVer software intents to add value to the ROV market by enabling the use an open source solution enabling an easy and intuitive user interface, available anywhere and at anytime and ready to support extensions from the open source community.

## Getting Started
To replicate the work on this repository, you need to follow the installation proccess.

### Installing


1. Make sure you have ROS installed, if not, click the following link:

    http://wiki.ros.org/ROS/Installation

2. Build all ROS catkin packages:

$ ./build.sh

3. After the build is complete, the build script will output a sequence of
   commands that you should place in your .bashrc (or similar) file to ensure
   that your system can "see" the newly built components. The following is an
   example sequence of commands that you should place in your .bashrc file:

CATKIN_WS1_SETUP=/path/to/gt-ros-pkg/setenv.sh
if [ -f ${CATKIN_WS1_SETUP} ]; then
source ${CATKIN_WS1_SETUP}
fi

4. Verify that you are connected to the ROV and to a standard controller (ex: Dualshock, Logitech, etc.)

5. Launch the control interface

$ rosrun videoray deploy.sh


## Protocols

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

