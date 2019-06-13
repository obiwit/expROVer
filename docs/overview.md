---
layout: page
title: Overview
description: What exactly is the ExpROVer project all about?
background: '/assets/img/underwater.jpg'
video_background: '/assets/vid/underwater.mp4'
---

## Introduction
Remotely Operated Vehicles (ROVs) are underwater vehicles used across several sea- and ocean-related industries, for fish management, research purposes, dangerous maintenance operations and several other tasks.

<figure style="max-width:80%; text-align: center; margin: 0 auto 1rem;">
  <img src="{{"/assets/img/overview/rov.png" | prepend: relative_url | prepend: site.baseurl | prepend: site.url }}" class="figure-img img-fluid" alt="The VideoRay Pro 4">
  <figcaption class="figure-caption">The VideoRay Pro 4. <em>&copy; Katemcgarry via Wikimedia Commons</em></figcaption>
</figure>

The VideoRay Pro 4 (VP4) is the world's most popular small underwater ROV. It incorporates the latest design and technology, making it stand out on the market as the most advanced, capable, and versatile small ROV.

With a maximum depth of 300m, the VP4 is controlled through an umbilical cord which directly connects it to a computer, which is used by the ROV to send data to the computer and to receive commands from it.

The VP4 is controlled through the VideoRay Cockpit software, developed by VideoRay, executable only on Windows - and unfortunately known to be liable to problems and bugs.

Finally, this software requires training, being complex and demanding elevated levels of prolonged concentration from its users to ensure the proper maneuvering of the VP4.


---

## Overview
**Underwater exploration, system’s management and maintenance, and research. At the tip of your fingertips.**
There is no doubt that Remotely Operated Vehicles (ROVs) are one of the most important underwater tools in today’s world. However, their proper maneuvering requires extensive training and is restricted by proprietary software.

The ExpROVer software will allow people from a myriad of backgrounds to easily operate the VideoRay Pro 4 (VP4).
* It will be a **free, open-source software**, easy to modify and tailor to your needs
* It will feature **several smart functionalities not present in any other competing software**, like obstacles’ detection and objects’ recognition, and well as several semi-autonomous pre-programmed movements
* It will **support several devices and platforms, such as Linux and Android**, without requiring these devices to be connected to the VP4’s umbilical cord (unlike the VP4’s shipping software, VideoRay Cockpit, which is only executable on a Windows computer directly connected to the VP4)

## Video Overview

<div class="video-container">
<iframe width="560" height="315" src="https://www.youtube.com/embed/nNTeIgoWbVo?rel=0&cc_load_policy=1" frameborder="0" allow="accelerometer; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
</div>

---
<div class="page-header" style="margin-bottom: 20px;">
  <h2>Features</h2>
</div>
<div class="container">
  <div class="row">

    <div class="col-sm feature-box">
       <div class="center">
          <i class="fa fa-gamepad fa-3x" aria-hidden="true"></i>
          <h5 class="font-weight-bold my-4">Control</h5>
        </div>
        <p class="grey-text mb-0 small">Wirelessly monitor and control your ROV, from any device of your choice.</p>
    </div>

    <div class="col-sm feature-box">
      <div class="center">
        <i class="fa fa-mobile fa-3x" aria-hidden="true"></i>
        <h5 class="font-weight-bold my-4">Freedom</h5>
      </div>
      <p class="grey-text mb-0 small">Android and web applications enable remote control from a wide range of possible devices.</p>
    </div>

    <div class="col-sm feature-box">
      <div class="center">
        <i class="fa fa-location-arrow fa-3x" aria-hidden="true"></i>
        <h5 class="font-weight-bold my-4">Navigate</h5>
      </div>
      <p class="grey-text mb-0 small">Configure your ROV to have higher autonomy, by executing various semi-autonomous maneuvers, all while avoiding obstacles!</p>
    </div>
  </div>

  <div class="row">
    <div class="col-sm feature-box">
      <div class="center">
        <i class="fa fa-eye fa-3x" aria-hidden="true"></i>
        <h5 class="font-weight-bold my-4">Monitor</h5>
      </div>
      <p class="grey-text mb-0 small">Easily monitor fish farms, through the built-in object detection and recognition systems, capable of identifying and classifying fish species.</p>
    </div>

    <div class="col-sm feature-box">
      <div class="center">
        <i class="fa fa-unlock fa-3x" aria-hidden="true"></i>
        <h5 class="font-weight-bold my-4">Expand</h5>
      </div>
      <p class="grey-text mb-0 small">Finally, all code is open-source - easy to tinker with, and add support for other functionalities and/or peripherals specific to your needs!</p>
    </div>

  </div>
</div>

---

The ExpROVer is a software solution that will serve as a bridge between the user and the VP4. It is comprised of 3 components:

1. One or more end devices, with the ExpROVer Android or Web App,
2. A server computer, connected to the ROV and running the ExpROVer backend application,
3. And the VideoRay Pro 4 ROV.

<figure style="max-width:80%; text-align: center; margin: 0 auto 1rem;">
  <img src="{{"/assets/img/overview/architecture.png" | prepend: relative_url | prepend: site.baseurl | prepend: site.url }}" class="figure-img img-fluid" alt="Basic Architecture">
  <figcaption class="figure-caption">The system's basic architecture.</figcaption>
</figure>

For the communication between the end devices and the server machine, a wireless network will be used.

To ensure the ExpROVer software’s correct functioning, the server machine must be running on the Linux OS. There are no requirements concerning the end devices, only that they have wireless capabilities.

Besides the remote capability, an increased abstraction level will also be made possible the ExpROVer system. This abstraction will allow users to easily execute complex commands through semi-autonomous maneuvers, like rising to the surface, maintaining a direction, following a preprogrammed path and attempting to avoid obstacles. It will also reduce users’ cognitive load by pre-processing the video’s frames and detecting objects and fishes.

### Evolution
The ExpROVer software is coming along nicely!

The ROV can already be fully controlled through a computer directly connected to it (using the keyboard, a programmed input or a joystick as shown), as you can see in the video below:

<div class="video-container">
<iframe width="560" height="315" src="https://www.youtube.com/embed/gDadCIIN0t8" frameborder="0" allow="accelerometer; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
</div>

By June, the system was complete! Check out this short (and unfortunately with some background noise) video to see the system operating and listen to a brief overview of this part of the ExpROVer package:

<div class="video-container">
<iframe width="560" height="315" src="https://www.youtube.com/embed/eDbLtBOV-fo?rel=0&cc_load_policy=1&mute=1" frameborder="0" allow="accelerometer; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
</div>


Furthermore, the Computer Vision functionalities are also very promising, and already show significant improvement! Check the video below for some Star Wars inspired description of this process:

<div class="video-container">
<iframe width="560" height="315" src="https://www.youtube.com/embed/va6NaSPFcYM" frameborder="0" allow="accelerometer; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
</div>

Or you can also check a side by side comparison of the improvement (the video on the left is out-of-the-box YOLOv3, the one in the middle is the first versin, from March, and the one on the right is the second (and final) version, from April):

<div class="video-container">
<iframe width="560" height="315" src="https://www.youtube.com/embed/ZMp_UqdHdmY" frameborder="0" allow="accelerometer; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
</div>

---

## Schedule and Risks
The ExpROVer project’s roadmap is depicted in Figure 4:

<figure style="max-width:100%; text-align: center; margin: 0 auto 1rem; padding-left: 30px;">
  <img src="{{"/assets/img/overview/schedule.png" | prepend: relative_url | prepend: site.baseurl | prepend: site.url }}" class="figure-img img-fluid" alt="Project Schedule">
  <figcaption class="figure-caption">The ExpROVer project's roadmap.</figcaption>
</figure>

The following table present the main risks and challenges to the successful and timely implementation of this project as well as the mitigating actions to be taken:

<table class="table table-striped">
  <thead class="thead-dark">
    <tr>
      <th scope="col">Risk</th>
      <th scope="col">Severity/Impact</th>
      <th scope="col">Probability</th>
      <th scope="col">Mitigation</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">ROV breaking/malfunction</th>
      <td>High</td>
      <td>Low</td>
      <td>Onboard of new partners that will be able to contribute to the acquisition of a new ROV.</td>
    </tr>
    <tr>
      <th scope="row">Insufficient VideoRay documentation</th>
      <td>Medium-High</td>
      <td>Medium</td>
      <td>Consultation of other sources and experimentation.</td>
    </tr>
    <tr>
      <th scope="row">Workforce shortage</th>
      <td>High</td>
      <td>Low</td>
      <td>Recruitment of other members to the team.</td>
    </tr>
  </tbody>
</table>
