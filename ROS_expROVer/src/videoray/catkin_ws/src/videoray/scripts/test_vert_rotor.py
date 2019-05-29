#!/usr/bin/env python
# license removed for brevity
import rospy
from datetime import datetime 
from std_msgs.msg import String
from std_msgs.msg import Header
from sensor_msgs.msg import Joy

def talker():
    pub = rospy.Publisher('videoray/joystick', Joy, queue_size=1)
    rospy.init_node('joystick_test', anonymous=True)
    rate = rospy.Rate(1000) # 10hz
    count = 0
    while not rospy.is_shutdown():

        h = Header()
        h.stamp = rospy.Time.now()

        axes = [0.0, count, -32767.0, 0.0, -2.0, -32767.0, 0.0, 0.0]
        buttons = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
        pub.publish(h, axes, buttons)
        count = count + 10
        rate.sleep()

if __name__ == '__main__':
    try:
        talker()
    except rospy.ROSInterruptException:
        pass