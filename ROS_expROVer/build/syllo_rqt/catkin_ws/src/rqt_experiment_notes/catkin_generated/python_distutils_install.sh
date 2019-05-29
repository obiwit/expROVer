#!/bin/sh

if [ -n "$DESTDIR" ] ; then
    case $DESTDIR in
        /*) # ok
            ;;
        *)
            /bin/echo "DESTDIR argument must be absolute... "
            /bin/echo "otherwise python's distutils will bork things."
            exit 1
    esac
    DESTDIR_ARG="--root=$DESTDIR"
fi

echo_and_run() { echo "+ $@" ; "$@" ; }

echo_and_run cd "/home/gjmarques/Documents/pei-2018-2019-g10/humans/src/syllo_rqt/catkin_ws/src/rqt_experiment_notes"

# ensure that Python install destination exists
echo_and_run mkdir -p "$DESTDIR/home/gjmarques/Documents/pei-2018-2019-g10/humans/install/lib/python2.7/dist-packages"

# Note that PYTHONPATH is pulled from the environment to support installing
# into one location when some dependencies were installed in another
# location, #123.
echo_and_run /usr/bin/env \
    PYTHONPATH="/home/gjmarques/Documents/pei-2018-2019-g10/humans/install/lib/python2.7/dist-packages:/home/gjmarques/Documents/pei-2018-2019-g10/humans/build/lib/python2.7/dist-packages:$PYTHONPATH" \
    CATKIN_BINARY_DIR="/home/gjmarques/Documents/pei-2018-2019-g10/humans/build" \
    "/usr/bin/python2" \
    "/home/gjmarques/Documents/pei-2018-2019-g10/humans/src/syllo_rqt/catkin_ws/src/rqt_experiment_notes/setup.py" \
    build --build-base "/home/gjmarques/Documents/pei-2018-2019-g10/humans/build/syllo_rqt/catkin_ws/src/rqt_experiment_notes" \
    install \
    $DESTDIR_ARG \
    --install-layout=deb --prefix="/home/gjmarques/Documents/pei-2018-2019-g10/humans/install" --install-scripts="/home/gjmarques/Documents/pei-2018-2019-g10/humans/install/bin"
