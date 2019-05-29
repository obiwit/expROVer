#!/bin/bash

# Initialize analog camera for NTSC mode.
# You may need to install v4l-utils for this command...
v4l2-ctl -s pal --device=2
#v4l2-ctl -d2 --set-fmt-video=width=640,height=480,pixelformat=0
ffplay -loglevel quiet -f v4l2 -input_format yuyv422 -i /dev/video0 -vf format=yuv420p &

#v4l2-ctl -s ntsc --device=0

# Ensure that ttyUSB serial ports are writable
# NUCLEAR MEASURE ADAPT AS NECESSARY
sudo chmod 666 /dev/ttyUSB*
sudo chmod 777 /dev/input/js*
sudo chmod 666 /dev/video*

if [ "$1" == "--enable-sonar" ]; then
    # Launch the VideoRay ROS Package with the BlueView Sonar nodes
    roslaunch videoray deploy_with_sonar.launch

else
    # Launch the VideoRay ROS Package
    roslaunch videoray deploy.launch
fi
