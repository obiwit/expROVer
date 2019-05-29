/*
 * Copyright (c) 2013, Kevin DeMarco
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   * Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   * Redistributions in binary form must reproduce the above
 *     copyright notice, this list of conditions and the following
 *     disclaimer in the documentation and/or other materials provided
 *     with the distribution.
 *   * Neither the name of the TU Darmstadt nor the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
 * ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
#include <pluginlib/class_list_macros.h>
#include <ros/master.h>

#include <std_msgs/String.h>
#include <std_msgs/Int32.h>

#include <rqt_rov_status/rov_status.h>

#include <QMessageBox>
#include <QPainter>

using std::cout;
using std::endl;

namespace rqt_rov_status {

     rov_status::rov_status()
          : rqt_gui_cpp::Plugin()
          , widget_(0)
     {
          setObjectName("ROV Status");
     }

     void rov_status::initPlugin(qt_gui_cpp::PluginContext& context)
     {
          widget_ = new QWidget();
          ui_.setupUi(widget_);

          if (context.serialNumber() > 1)
          {
               widget_->setWindowTitle(widget_->windowTitle() + " (" + QString::number(context.serialNumber()) + ")");
          }
          context.addWidget(widget_);

          this->subscriber_ = getNodeHandle().subscribe<videoray::Status>("videoray_status", 1, &rov_status::callback_status, this);

          ui_.firmware_label->setText("not set");

          // Start GUI update timer
          timer_ = new QTimer(this);
          connect(timer_, SIGNAL(timeout()), this, SLOT(updateGUI()));
          timer_->start(100);
     }
     
     void rov_status::updateGUI()
     {
          ui_.water_temp_spinbox->setValue(status_.water_temp);
          ui_.tether_voltage_spinbox->setValue(status_.tether_voltage);
          ui_.voltage_12v_spinbox->setValue(status_.voltage_12V);
          ui_.current_12v_spinbox->setValue(status_.current_12V);
          ui_.internal_temp_spinbox->setValue(status_.internal_temp);
          ui_.humidity_spinbox->setValue(status_.internal_relative_humidity);
          ui_.comm_err_count_spinbox->setValue(status_.comm_err_count);

          std::ostringstream convert;
          convert << status_.firmware_version;
          ui_.firmware_label->setText(convert.str().c_str());
     }

     bool rov_status::eventFilter(QObject* watched, QEvent* event)
     {
          return QObject::eventFilter(watched, event);
     }

     void rov_status::shutdownPlugin()
     {
          timer_->stop();
          subscriber_.shutdown();
     }
     
     void rov_status::saveSettings(qt_gui_cpp::Settings& plugin_settings, qt_gui_cpp::Settings& instance_settings) const
     {
          //instance_settings.setValue("desired_heading", ui_.desired_heading_double_spin_box->value());
     }

     void rov_status::restoreSettings(const qt_gui_cpp::Settings& plugin_settings, const qt_gui_cpp::Settings& instance_settings)
     {
          //double desired_heading = instance_settings.value("desired_heading", ui_.desired_heading_double_spin_box->value()).toDouble();
          //ui_.desired_heading_double_spin_box->setValue(desired_heading);
          
     }
     
     void rov_status::callback_status(const videoray::StatusConstPtr& msg)
     {          
          status_ = *msg;
     }     
}

PLUGINLIB_EXPORT_CLASS(rqt_rov_status::rov_status, rqt_gui_cpp::Plugin)
