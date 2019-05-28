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

echo_and_run cd "/home/pergunta/Desktop/PEI/humans/src/syllo_rqt/catkin_ws/src/rqt_compass"

# ensure that Python install destination exists
echo_and_run mkdir -p "$DESTDIR/home/pergunta/Desktop/PEI/humans/install/lib/python2.7/dist-packages"

# Note that PYTHONPATH is pulled from the environment to support installing
# into one location when some dependencies were installed in another
# location, #123.
echo_and_run /usr/bin/env \
    PYTHONPATH="/home/pergunta/Desktop/PEI/humans/install/lib/python2.7/dist-packages:/home/pergunta/Desktop/PEI/humans/build/lib/python2.7/dist-packages:$PYTHONPATH" \
    CATKIN_BINARY_DIR="/home/pergunta/Desktop/PEI/humans/build" \
    "/usr/bin/python2" \
    "/home/pergunta/Desktop/PEI/humans/src/syllo_rqt/catkin_ws/src/rqt_compass/setup.py" \
    build --build-base "/home/pergunta/Desktop/PEI/humans/build/syllo_rqt/catkin_ws/src/rqt_compass" \
    install \
    $DESTDIR_ARG \
    --install-layout=deb --prefix="/home/pergunta/Desktop/PEI/humans/install" --install-scripts="/home/pergunta/Desktop/PEI/humans/install/bin"
