---
layout: page
title: Vision and Personas
description: Discover our vision and target costumer base.
background: '/assets/img/underwater.jpg'
video_background: '/assets/vid/underwater.mp4'
---

<style>
      [data-toggle="collapse"][aria-expanded="true"] > .rotate-icon {
        -webkit-transform: rotate(90deg);
        -moz-transform: rotate(90deg);
        -ms-transform: rotate(90deg);
        -o-transform: rotate(90deg);
        transform: rotate(90deg);
      }
</style>


# Personas
### Ralph Schmidt
<div class="col-md-12">
  <p>
  <img class="img-responsive" src="{{"/assets/img/personas/ralph.jpg" | prepend: relative_url | prepend: site.baseurl | prepend: site.url }}" style="max-width: 25%; text-align: center; margin: 2px 10px; float:left;">
  Ralph is a college graduate with a degree in computer science working as professional programmer. Owning a VideoRay Pro 4, he also develops simple software for the VideoRay community as a hobby.  He wants to implement new pre-programmed actions in his ROV, but he is faced with the herculean task of modifying VideoRay’s proprietary Cockpit software. What he needs is an open source solution that provides him with both a control interface and the ability to add these new actions.</p>
</div>
<br>

### Michelle Kahoru
<div class="col-md-12">
  <p>
  <img class="img-responsive pull-left" src="{{"/assets/img/personas/michelle.jpg" | prepend: relative_url | prepend: site.baseurl | prepend: site.url }}" style="max-width: 25%; text-align: center; margin: 2px 10px; float:right;">
  Michelle is part of a specialized emergency response team and sometimes goes on emergency missions to find survivors trapped in shipwrecks. She recently started using a ROV as the main tool to find survivors and help them out as fast as possible. Every day, Michelle is more interested in ROVs and their potential for additional capabilities, enabled by the integration of new sensors and actuators. She aims to leverage ROVs to be more efficient in her job.As a result, she is looking for a solution that enables her to control the ROV in harsh conditions, at anytime, from anywhere. It comes as a useful bonus that this software also allows her to add support for new functionalities and peripherals in a relatively simple manner</p>
</div>
<br>

### Peter Smith
<div class="col-md-12">
  <p>
  <img class="img-responsive pull-left" src="{{"/assets/img/personas/peter.jpg" | prepend: relative_url | prepend: site.baseurl | prepend: site.url }}" style="max-width: 25%; text-align: center; margin: 2px 10px; float:left;">
  Peter owns an aquaculture enterprise were he grows fish. He needs a way to control the health of his fish population, while also perform inspections on nets, pens, mooring points, and other elements of his farms. This type of jobs present risky work conditions for divers, so he is inclined towards using a remotely operated solution. It would allow him to monitor his fishes’ feeding habits and make periodical inspections. However, he is not very experienced neither with ROV handling nor ROV operations</p>
</div>

---

# Scenarios
Below, 4 distinct scenarios are described. Out of them, only scenarios 1 and 3 will be selected for the system’s first release.

<!--Accordion wrapper-->
<div class="accordion md-accordion" id="accordionEx" role="tablist" aria-multiselectable="true" style="margin-top: 5%;">

  <div class="card">
    <div class="card-header" role="tab" id="headingOne1">
      <a data-toggle="collapse" data-parent="#accordionEx" href="#collapseOne1" aria-expanded="false"
        aria-controls="collapseOne1">
        <h5 class="mb-0">
          Remote ROV control and monitoring via mobile devices <i class="fas fa-angle-down" style="float: right;"></i>
        </h5>
      </a>
    </div>
    <div id="collapseOne1" class="collapse" role="tabpanel" aria-labelledby="headingOne1"
      data-parent="#accordionEx">
      <div class="card-body">
        Peter wants to quickly visualize his aquafarm. However, he has gone on holiday with his family, and is not present at his office, where he could directly access the VRP4 through a computer connected to its umbilical cord. Before he left on holiday, Peter installed and ran ExpROVer’s back-end program, allowing him to now monitor his ROV anytime, from anywhere. On his tablet, which he has linked to the ROV via the ExpROVer’s mobile app, he can now monitor and control his VRP4.
      </div>
    </div>
  </div>

  <div class="card">
    <div class="card-header" role="tab" id="headingTwo2">
      <a class="collapsed" data-toggle="collapse" data-parent="#accordionEx" href="#collapseTwo2"
        aria-expanded="false" aria-controls="collapseTwo2">
        <h5 class="mb-0">
          Lighter workload during harsh environmental conditions <i class="fas fa-angle-down" style="float: right;"></i>
        </h5>
      </a>
    </div>
    <div id="collapseTwo2" class="collapse" role="tabpanel" aria-labelledby="headingTwo2"
      data-parent="#accordionEx">
      <div class="card-body">
        Michelle is rudely awakened by a loud crash. As she was turning on the news, she gets a call informing her that a coastal building has just collapsed and that she is needed. After a long and exhausting trip to the area of the accident to meetup with the rest of the team, and on very little sleep, Michelle needs to work with the VideoRay Pro 4 to rescue what she can.The ambient is muddy, dirty and clouded and details or static objects can easily escape the operator’s eye. Not only is it crucial for Michelle’s job that she notices these, since they can necessitate to be rescued, some of these objects can also  harm the ROV. As such, with the help of the ExpROVer program, Michelle can easily both track objects with higher ease by using the Computer Vision functionalities, but also avoid obstacles due to the semi-autonomous maneuvers module.
      </div>
    </div>
  </div>

  <div class="card">
    <div class="card-header" role="tab" id="headingThree3">
      <a class="collapsed" data-toggle="collapse" data-parent="#accordionEx" href="#collapseThree3"
        aria-expanded="false" aria-controls="collapseThree3">
        <h5 class="mb-0">
          Adding support for new peripherals <i class="fas fa-angle-down" style="float: right;"></i>
        </h5>
      </a>
    </div>
    <div id="collapseThree3" class="collapse" role="tabpanel" aria-labelledby="headingThree3"
      data-parent="#accordionEx">
      <div class="card-body">
        Ralph knows how to program, and nowadays works almost exclusively using the Linux OS. VideoRay’s Cockpit software forced him to work on Windows 7, since the Windows 10 version caused several bugs, requiring a factory reset to the VRP4. Not only that, but Ralph was also confined to the functionality provided out of the box by VideoRay’s Cockpit software, since it was proprietary and therefore not open to modifications and expansions. Unhappy about his situation, Ralph decided to instead adopt the ExpROVer software, and though he is not particularly interested in controlling his ROV with other devices, he is happy that the code is open source and easy to modify. He has developed and shared an extension to integrate control of a grappler arm, and even tweaked his own interface to better suit his use of the software.
      </div>
    </div>
  </div>



    <div class="card">
      <div class="card-header" role="tab" id="headingFour4">
        <a data-toggle="collapse" data-parent="#accordionEx" href="#collapseFour4" aria-expanded="false"
          aria-controls="collapseFour4">
          <h5 class="mb-0">
            Using community extensions <i class="fas fa-angle-down" style="float: right;"></i>
          </h5>
        </a>
      </div>
      <div id="collapseFour4" class="collapse" role="tabpanel" aria-labelledby="headingFour4"
        data-parent="#accordionEx">
        <div class="card-body">
          Michelle’s rescue company recently decided to invest in a grappler arm for the VRP4, which was quickly revealing itself to be a major asset in their operations. Michelle read ExpROVer’s user manual to check whether or not this peripheral was supported by the base software. She saw it was indeed not supported, but, undeterred and directed by the manual to check the community extensions,  she found a popular extension, by Ralph Schmidt, adding just the functionality she needed. She downloaded and integrated his code, and after reading Ralph’s documentation and doing a field test, Michelle now found herself capable of using the grappler arm as well, despite her short training period.
        </div>
      </div>
    </div>

</div>
