---
layout: page
title: Specifications
description: Find a more detailed specification of the ExpROVer project.
background: '/assets/img/underwater.jpg'
video_background: '/assets/vid/underwater.mp4'
---

# Introduction
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

# Positioning

<p class="small-margin-top">In this section, the main problems and the project’s locality are described.</p>

### Problem Statement

<table class="table table-striped" style="table-layout: fixed;">
  <tbody>
    <tr>
      <th scope="row" style="width: 30%">The problem of</th>
      <td class="td-padding">operating a ROV or improving its functionality range</td>
    </tr>
    <tr>
      <th scope="row" style="width: 30%">affects</th>
      <td class="td-padding">all business and research institutions which require underwater monitoring or operations</td>
    </tr>
    <tr>
      <th scope="row" style="width: 30%">the impact of which is</th>
      <td class="td-padding">high labor costs, accidents’ susceptibility and error-proneness during operations</td>
    </tr>
    <tr>
      <th scope="row" style="width: 30%">a successful solution would be</th>
      <td class="td-padding">the reduction of effort and inconvenience associated with operating a ROV, leading to lower expenses, higher efficiency and the enabling of new functionalities' creation.</td>
    </tr>
  </tbody>
</table>

<div class="small-separator"></div>

### Product Position Statement

<table class="table table-striped" style="table-layout: fixed;">
  <tbody>
    <tr>
      <th scope="row" style="width: 25%">For</th>
      <td class="td-padding">owners of the VRP4</td>
    </tr>
    <tr>
      <th scope="row" style="width: 25%">Who</th>
      <td class="td-padding">want to control the VRP4 with either  less specialized or more productive workers and have higher effectiveness in its operation</td>
    </tr>
    <tr>
      <th scope="row" style="width: 25%">The ExpROVer</th>
      <td class="td-padding">is a software solution</td>
    </tr>
    <tr>
      <th scope="row" style="width: 25%">That</th>
      <td class="td-padding">promotes a reduced workload and lower training requirements, offering several helper functionality and high accessibility to the ROV’s systems.</td>
    </tr>
    <tr>
      <th scope="row" style="width: 25%">Unlike</th>
      <td class="td-padding">VideoRay’s Cockpit software</td>
    </tr>
    <tr>
      <th scope="row" style="width: 25%">Our product</th>
      <td class="td-padding">will run on Linux, as well as Android, and will have several additional semi-autonomous features, such as object recognition  and smart maneuvering.</td>
    </tr>
  </tbody>
</table>

---

# Product overview

<div class="small-separator"></div>

### Features
<ul style="list-style: none; padding-left: 0;">
  <li><strong>FE# 1.</strong> Remote access to ROV through ROS</li>
  <li><strong>FE# 2.</strong> ROV control, anytime, anywhere, with any device with internet connectivity through a Web application</li>
  <li><strong>FE# 3.</strong> Specialized support for Android devices through an Android application</li>
  <li><strong>FE# 4.</strong> Several semi-autonomous maneuvers enablement</li>
  <div style="padding-left: 5%">
    <li><strong>FE# 4.1.</strong> pre-programmed paths following,</li>
    <div style="padding-left: 5%">
    	<li><strong>FE# 4.1.1.</strong> straight line movement,</li>
    	<li><strong>FE# 4.1.2.</strong> turn a given amount of degrees,</li>
    	<li><strong>FE# 4.1.3.</strong> combinations of <strong>FE# 4.1.1.</strong> and <strong>FE# 4.1.2.</strong>, and</li>
    	<li><strong>FE# 4.1.4.</strong> movement in a straight line until a change is detected in the video feed.</li>
    </div>
    <li><strong>FE# 4.2.</strong> automatic return to the surface, and</li>
    <li><strong>FE# 4.3.</strong> direction maintaining.</li>
  </div>
  <li><strong>FE# 5.</strong> Built-in object detection and recognition systems.</li>
  <li><strong>FE# 6.</strong> Fully open-source code, providing the possibility of alteration to suit more specific needs or features</li>
</ul>

<div class="small-separator"></div>

### Needs

<p class="small-margin-top">The table below describe the solution needs, their priorities, features and planned releases.</p>

<table class="table table-striped" style="table-layout: fixed;">
  <thead class="thead-dark">
    <tr style="text-align: center;">
      <th scope="col" style="width: 40%">Need</th>
      <th scope="col" style="width: 15%">Priority</th>
      <th scope="col" style="width: 18%">Features</th>
      <th scope="col" style="width: 27%">Planned Release</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row" style="width: 40%">Communication with VRP4 over ROS</th>
      <td class="td-padding">High</td>
      <td class="td-padding">FE# 1, FE# 6</td>
      <td class="td-padding">Alpha release (3rd April)</td>
    </tr>
    <tr>
      <th scope="row" style="width: 40%">Object and fish recognition</th>
      <td class="td-padding">Medium</td>
      <td class="td-padding">FE# 5</td>
      <td class="td-padding">Alpha (3rd April) and Closed Beta (8th May) releases</td>
    </tr>
    <tr>
      <th scope="row" style="width: 40%">Support for semi-autonomous maneuvers</th>
      <td class="td-padding">Medium</td>
      <td class="td-padding">FE# 4</td>
      <td class="td-padding">Closed Beta (8th May) release</td>
    </tr>
    <tr>
      <th scope="row" style="width: 40%">Control of ROV and leverage of the developed additional capabilities outside of Windows OS</th>
      <td class="td-padding">Medium</td>
      <td class="td-padding">FE# 1, FE #2, FE #4, FE #5, FE #6</td>
      <td class="td-padding">Open Beta (22nd May) release</td>
    </tr>
    <tr>
      <th scope="row" style="width: 40%">Remote control of ROV through an Android device</th>
      <td class="td-padding">Low</td>
      <td class="td-padding">FE# 1, FE# 2, FE# 3</td>
      <td class="td-padding">Closed Beta (8th May) release</td>
    </tr>
  </tbody>
</table>


<div class="small-separator"></div>

### Business Requirements

<p class="small-margin-top">To create the business model, various domains were succinctly described (following the model proposed on <em>Alexander Osterwalder, Yves Pigneur, Criar Modelos de Negócio, 2011</em>):</p>

<table class="table table-striped" style="table-layout: fixed;">
  <tbody>
    <tr>
      <th scope="row" style="width: 25%">Clients</th>
      <td class="td-padding">
        <ol type="1">
          <li><strong>Researchers</strong></li>
            Use ROVs for underwater research and experiments.
          <li><strong>Business owners</strong></li>
            Use ROVs for underwater operations and maintenance.
          <li><strong>IT-knowledgeable professionals</strong></li>
          Familiar with ROS, they seek to leverage and operate in a new environment.
          <li><strong>Hobbyists</strong></li>
            Owning ROVs, they aim to take maximum advantage of them.
        </ol>
      </td>
    </tr>
    <tr>
      <th scope="row" style="width: 25%">Value proposals</th>
      <td class="td-padding">
        <ol type="1">
          <li><strong>Higher operational efficiency</strong></li>
            Several helper functions reduce workload and concentration required to operate the ROV
          <li><strong>Costs reduction</strong></li>
            Less training required, fewer presential location requirements
          <li><strong>Higher flexibility in control options</strong></li>
          <li><strong>User mobility in ROV data monitoring from anywhere, at any time</strong></li>
        </ol>
      </td>
    </tr>
    <tr>
      <th scope="row" style="width: 25%">Channels</th>
      <td class="td-padding">
        <ul style="list-style-type:none; padding-left: 0;">
          <li>Website for project advertisement</li>
          <li>Open source repository</li>
          <li>Social media</li>
        </ul>
      </td>
    </tr>
    <tr>
      <th scope="row" style="width: 25%">Client Relations</th>
      <td class="td-padding">Digital touchpoints-based communication</td>
    </tr>
    <tr>
      <th scope="row" style="width: 25%">Profit sources</th>
      <td class="td-padding">
        <ul style="list-style-type:none; padding-left: 0;">
          <li>Sponsorships and subsidies</li>
          <li>Donations</li>
        </ul>
      </td>
    </tr>
    <tr>
      <th scope="row" style="width: 25%">Key resources</th>
      <td class="td-padding">VideoRay Pro 4 ROV, communications, Artificial Intelligence, Computer Vision, server, online application</td>
    </tr>
    <tr>
      <th scope="row" style="width: 25%">Key activities</th>
      <td class="td-padding">
        <ol type="1">
          <li><strong>Software development</strong></li>
            <ol type="a">
                <li>For promotional website</li>
                <li>For ROS back-end on the server machine</li>
                <li>For data analytics layer</li>
                <li>For the Web and Android front-end on the end devices</li>      
            </ol>
          <li><strong>Hardware integration</strong></li>
            <ol type="a">
              <li>VRP4 integration with a Linux server machine running back-end program and wireless connection to the end devices</li>
            </ol>
          <li><strong>Graphical User Interface Design</strong></li>
        </ol>
      </td>
    </tr>
    <tr>
      <th scope="row" style="width: 25%">Key partners</th>
      <td class="td-padding">VideoRay, universities and research institutes, fish farming and other underwater enterprises</td>
    </tr>
    <tr>
      <th scope="row" style="width: 25%">Cost structures</th>
      <td class="td-padding">VideoRay Pro 4, labor costs, server and processing units cost</td>
    </tr>
  </tbody>
</table>

<div class="small-separator"></div>

### Other Product Requirements

<p class="small-margin-top">Other product requirements are described bellow:</p>

<table class="table table-striped" style="table-layout: fixed;">
  <thead class="thead-dark">
    <tr style="text-align: center;">
      <th scope="col" style="width: 45%">Requirement</th>
      <th scope="col" style="width: 25%">Priority</th>
      <th scope="col" style="width: 30%">Planned Release</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">Performance under environmental stressful conditions</th>
      <td class="td-padding">Low</td>
      <td class="td-padding">Open Beta (22nd May)</td>
    </tr>
    <tr>
      <th scope="row">Security and data encryption</th>
      <td class="td-padding">Medium</td>
      <td class="td-padding">Open Beta (22nd May)</td>
    </tr>
    <tr>
      <th scope="row">Near real-time ROV video analysis for object detection and recognition features</th>
      <td class="td-padding">High</td>
      <td class="td-padding">Closed Beta (8th May)</td>
    </tr>
    <tr>
      <th scope="row">Dynamic path planning during maneuvers execution</th>
      <td class="td-padding">High</td>
      <td class="td-padding">Open Beta (22nd May)</td>
    </tr>
    <tr>
      <th scope="row">Closed  Backwards compatibility guarantee</th>
      <td class="td-padding">Low</td>
      <td class="td-padding">Open Beta (22nd May)</td>
    </tr>
    <tr>
      <th scope="row">Meticulous usability practices to ensure great customer experiences</th>
      <td class="td-padding">High</td>
      <td class="td-padding">Closed Beta (8th May)</td>
    </tr>
    <tr>
      <th scope="row">Detailed documentation of utilization practices in a User Manual</th>
      <td class="td-padding">High</td>
      <td class="td-padding">Alpha (4th April)</td>
    </tr>
  </tbody>
</table>

<div class="small-separator"></div>

### Project Risks

<p class="small-margin-top"> The following table presents the project's risks, and their proposed mitigating actions:</p>

<table class="table table-striped" style="table-layout: fixed;">
  <thead class="thead-dark">
    <tr style="text-align: center;">
      <th scope="col" style="width: 25%">Risk</th>
      <th scope="col">Severity</th>
      <th scope="col">Probability</th>
      <th scope="col" style="width: 40%">Mitigation</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">Requirements alterations</th>
      <td class="td-padding">Medium</td>
      <td class="td-padding">Low</td>
      <td class="td-padding">Frequent meetings until a shared project vision is defined.</td>
    </tr>
    <tr>
      <th scope="row">Delays and missed deadlines</th>
      <td class="td-padding">Medium-High</td>
      <td class="td-padding">Medium</td>
      <td class="td-padding">Continuous effort monitoring and personnel reassignment to critical condition tasks.n</td>
    </tr>
    <tr>
      <th scope="row">Difficulties in integration</th>
      <td class="td-padding">High</td>
      <td class="td-padding">Medium</td>
      <td class="td-padding">Frequent meetings to normalize all communicating interfaces and overreaching architecture.</td>
    </tr>
    <tr>
      <th scope="row">Rejected deliverables</th>
      <td class="td-padding">High</td>
      <td class="td-padding">Low</td>
      <td class="td-padding">Meticulous and incremental products and processes adjustment and improvement.</td>
    </tr>
    <tr>
      <th scope="row">Insufficient client adhesion</th>
      <td class="td-padding">High</td>
      <td class="td-padding">Low</td>
      <td class="td-padding">Market and trends monitoring.</td>
    </tr>
    <tr>
      <th scope="row">Different understandings of project vision</th>
      <td class="td-padding">Medium</td>
      <td class="td-padding">Medium</td>
      <td class="td-padding">Frequent meetings until a shared project vision is reached, with well-defined requirements and scenarios.</td>
    </tr>
    <tr>
      <th scope="row">ROV malfunction</th>
      <td class="td-padding">High</td>
      <td class="td-padding">Low</td>
      <td class="td-padding">Onboard of  new partners that will be able to contribute to the acquisition of a new ROV</td>
    </tr>
    <tr>
      <th scope="row">Insufficient VideoRay documentation</th>
      <td class="td-padding">Medium-High</td>
      <td class="td-padding">Medium</td>
      <td class="td-padding">Consultation of other sources and experimentation.</td>
    </tr>
    <tr>
      <th scope="row">Workforce shortage </th>
      <td class="td-padding">High</td>
      <td class="td-padding">Low</td>
      <td class="td-padding">Recruitment of other members to the team</td>
    </tr>
  </tbody>
</table>


---

# Minimum Viable Product (MVP)

<a href="{{"/assets/pdf/2019PEI_MVP_G10_ExpROVer.pdf" | prepend: relative_url | prepend: site.baseurl | prepend: site.url }}" target="_blank">Open the MVP definition in a new tab.</a>
