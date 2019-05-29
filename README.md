# expROVer
The ExpROVer software intents to add value to the ROV market by enabling the use an open source solution enabling an easy and intuitive user interface, available anywhere and at anytime and ready to support extensions from the open source community.

## Getting Starter
If you want to replicate the work on this repository, you need to follow the installation proccess.

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

## License
This project is under the license of +++, you can see more information in [LICENSE](LICENSE)

## More Information
You can see more information in this page +++

## Acknowledgments
* Tutors
* Hard work
* ...

