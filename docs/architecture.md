---
layout: page
title: Architecture
description: How is ExpROVer implemented?
background: '/assets/img/underwater.jpg'
video_background: '/assets/vid/underwater.mp4'
---
<!--
## Introduction
Remotely Operated Vehicles (ROVs) are underwater vehicles used across several sea- and ocean-related industries, for fish management, research purposes, dangerous maintenance operations and several other tasks.

<figure style="max-width:80%; text-align: center; margin: 0 auto 1rem;">
  <img src="{{"/assets/img/overview/rov.png" | prepend: relative_url | prepend: site.url }}" class="figure-img img-fluid" alt="The VideoRay Pro 4">
  <figcaption class="figure-caption">The VideoRay Pro 4. <em>&copy; Katemcgarry via Wikimedia Commons</em></figcaption>
</figure>

The VideoRay Pro 4 (VP4) is the world's most popular small underwater ROV. It incorporates the latest design and technology, making it stand out on the market as the most advanced, capable, and versatile small ROV.

With a maximum depth of 300m, the VP4 is controlled through an umbilical cord which directly connects it to a computer, which is used by the ROV to send data to the computer and to receive commands from it.

The VP4 is controlled through the VideoRay Cockpit software, developed by VideoRay, executable only on Windows - and unfortunately known to be liable to problems and bugs.

Finally, this software requires training, being complex and demanding elevated levels of prolonged concentration from its users to ensure the proper maneuvering of the VP4.


---

## Positioning
In this section, the main problems and the project’s locality are described.

### Problem Statement  

<table class="table table-striped">
  <tr>
    <th scope="row">The problem of</th>
    <td>operating a ROV or improving its functionality range</td>
  </tr>
  <tr>
    <th scope="row">affects</th>
    <td>all business and research institutions which require underwater monitoring or operations</td>
  </tr>
  <tr>
    <th scope="row">the impact of which is</th>
    <td>high labor costs, accidents’ susceptibility and error-proneness during operations</td>
  </tr>
  <tr>
    <th scope="row">a successful solution would be</th>
    <td>the reduction of effort and inconvenience associated with operating a ROV, leading to lower expenses, higher efficiency and the enabling of new functionalities’ creation.</td>
  </tr>
</table>

### Product Position Statement

<table class="table table-striped">
  <tr>
    <th scope="row">For</th>
    <td>owners of the VRP4</td>
  </tr>
  <tr>
    <th scope="row">Who</th>
    <td>want to control the VRP4 with either  less specialized or more productive workers and have higher effectiveness in its operation</td>
  </tr>
  <tr>
    <th scope="row">The ExpROVer</th>
    <td> is a software solution</td>
  </tr>
  <tr>
    <th scope="row">That</th>
    <td>promotes a reduced workload and lower training requirements, offering several helper functionality and high accessibility to the ROV’s systems. </td>
  </tr>
  <tr>
    <th scope="row">Unlike</th>
    <td>VideoRay’s Cockpit software</td>
  </tr>
  <tr>
    <th scope="row">Our product</th>
    <td>will run on Linux, as well as Android, and will have several additional semi-autonomous features, such as object recognition  and smart maneuvering.</td>
  </tr>
</table>

---
-->

## Architecture

The ExpROVer system sports a layered architecture with 3 levels:
1. The sensory and actuation layer, composed by the VRP4 and its integrated sensors and actuators
2. The data processing and control layer, composed of a Linux computer or server machine directly connected to the VRP4 through its umbilical cord, both integrating the information received and sent to the ROV with the ROS; and analyzing the video information, running an object detection algorithms, as well as the logic responsible for semi-autonomous maneuvers
3. The application layer, running the Android or web ExpROVer application, interacts with users over the end devices

<figure style="max-width:80%; text-align: center; margin: 0 auto 1rem;">
  <img src="{{"/assets/img/architecture/detailed_layers.png" | prepend: relative_url | prepend: site.url }}" class="figure-img img-fluid" alt="The VideoRay Pro 4">
</figure>

The VRP4 supports a wide array of sensors and other peripherals that either are or can be integrated with the ROV. These peripherals enable additional functionality, such as distance and size estimation, enabled through a fan beam laser and two parallel lasers, respectively. The attached sensors (as the laser peripherals, manipulator arms) are connected to the ROV through the VRP4’s unique port, that supports RS-485.

The ROV must be connected through an umbilical cord that powers the system and allows the user to both send commands to control the ROV and receive the sensory inputs which allow them to monitor its status.

This raw video and sensor data are processed and analyzed by the server machine, and the results are relayed to the active end devices. These devices are responsible for issuing high level instructions which the server machine will then break down into the actual ROS messages that the VRP4 will execute.

Another of the system’s functioning and modules is depicted in Figure 9:

<figure style="max-width:80%; text-align: center; margin: 0 auto 1rem;">
  <img src="{{"/assets/img/architecture/architecture2.png" | prepend: relative_url | prepend: site.url }}" class="figure-img img-fluid" alt="The VideoRay Pro 4">
</figure>

The VPR4 integrates several sensors:
*     A camera,
*     A pressure sensor, which indicates its current depth,
*     A 3 axes compass, which indicates its heading,
*     3 axes accelerometers, which indicate its attitude,
*     A water temperature sensor, which indicates the temperature of the water surrounding the VRP4,
*     An internal temperature sensor, which indicates its internal temperature,
*     And an internal humidity sensor, which indicates the relative humidity of the air inside the ROV.


It also presents a myriad of actuators, amongst which the most relevant are the following:
*     Camera controllers, affecting configurations such as focus and tilt,
*     Port, starboard and vertical  thrusters’ velocity and acceleration controllers,
*     And its lights’ intensity controller.
