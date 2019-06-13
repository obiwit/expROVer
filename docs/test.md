---
layout: page
title: Test and Validation
description: How is ExpROVer tested and validated for its users?
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

## Test and Validation

The tests will be specified according to the following topics:  

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Test identifier;  

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Feature to be tested;  

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Evaluation procedure;  

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Pass/fail criteria.

The pass/fail criteria will be derived from the following series of binary queries which assess the correct functioning of the system.

<ul style="list-style: none; padding-left: 0;">
  <li><strong>1.</strong> To test the connection with the ROV, and the developed app’s interface, questions such as the following will be considered:</li>
  <div style="padding-left: 5%">
    <li><strong>1.1.</strong> Can a user connect with the ROV via a mobile device?</li>
    <li><strong>1.2.</strong> Can a user connect with the ROV when using a computer running the Linux OS?</li>
    <li><strong>1.3.</strong> Can a user unfamiliar with the developed app, but familiar with ROVs, correctly use and understand the interface?</li>
    <li><strong>1.4.</strong> Can a non-expert ROV user maneuver the ROV as they desire, through the developed interface?</li>
  </div>
  <li><strong>2.</strong> To test the computer vision capabilities, questions such as the following will be considered:</li>
  <div style="padding-left: 5%">
    <li><strong>2.1.</strong> Can the ROV detect obstacles?</li>
    <li><strong>2.2.</strong> Can the ROV correctly detect fishes?</li>
  </div>
  <li><strong>3.</strong> To test semi-autonomous movement capabilities, questions such as the following will be considered:</li>
  <div style="padding-left: 5%">
    <li><strong>3.1.</strong> Can the ROV execute simple autonomous maneuvers, like collision avoidance?</li>
  </div>
</ul>
