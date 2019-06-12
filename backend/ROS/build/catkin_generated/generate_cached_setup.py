# -*- coding: utf-8 -*-
from __future__ import print_function
import argparse
import os
import stat
import sys

# find the import for catkin's python package - either from source space or from an installed underlay
if os.path.exists(os.path.join('/opt/ros/melodic/share/catkin/cmake', 'catkinConfig.cmake.in')):
    sys.path.insert(0, os.path.join('/opt/ros/melodic/share/catkin/cmake', '..', 'python'))
try:
    from catkin.environment_cache import generate_environment_script
except ImportError:
    # search for catkin package in all workspaces and prepend to path
    for workspace in "/home/gjmarques/Documents/pei-2018-2019-g10/humans/src/underwater/catkin_ws/devel;/home/gjmarques/Documents/pei-2018-2019-g10/humans/src/syllo_uwsim/catkin_ws/devel;/home/gjmarques/Documents/pei-2018-2019-g10/humans/src/sandbox/catkin_ws/devel;/home/gjmarques/Documents/pei-2018-2019-g10/humans/src/blueview/catkin_ws/devel;/home/gjmarques/Documents/pei-2018-2019-g10/humans/src/syllo_rqt/catkin_ws/devel;/home/gjmarques/Documents/pei-2018-2019-g10/humans/src/videoray/catkin_ws/devel;/home/gjmarques/Documents/pei-2018-2019-g10/humans/src/input/catkin_ws/devel;/home/gjmarques/Documents/pei-2018-2019-g10/humans/src/syllo/catkin_ws/devel;/opt/ros/melodic".split(';'):
        python_path = os.path.join(workspace, 'lib/python2.7/dist-packages')
        if os.path.isdir(os.path.join(python_path, 'catkin')):
            sys.path.insert(0, python_path)
            break
    from catkin.environment_cache import generate_environment_script

code = generate_environment_script('/home/gjmarques/Documents/pei-2018-2019-g10/humans/devel/env.sh')

output_filename = '/home/gjmarques/Documents/pei-2018-2019-g10/humans/build/catkin_generated/setup_cached.sh'
with open(output_filename, 'w') as f:
    #print('Generate script for cached setup "%s"' % output_filename)
    f.write('\n'.join(code))

mode = os.stat(output_filename).st_mode
os.chmod(output_filename, mode | stat.S_IXUSR)
