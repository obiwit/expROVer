# expROVer
The ExpROVer software intents to add value to the ROV market by enabling the use an open source solution enabling an easy and intuitive user interface, available anywhere and at anytime and ready to support extensions from the open source community.

## Getting Started
To replicate the work on this repository, you need to follow the installation proccess.

### Installing

## Protocols

### Server Commands
The communication with the server is based on JSON.
#### Movement
The movement need a vector of three axis (movX, movY, movZ) 
```JSON
{
    type: "move",
    data:
    {
        x: movX,
        y: movY,
        z: movZ
    }
}
```

#### Rotation
The rotation is made only by the vertical axis, and is given by degrees
```JSON
{
    type: "rotate",
    data:
    {
        rotation: axisZ,
    }
}
```
#### Lights
```JSON
{
    type:"lights",
    data:
    {
        active: true/false
    }
}

```






## License
This project is under the license of **GPL-3.0** you can see more information in [LICENSE](LICENSE)

## More Information
You can see more information in this page +++

## Acknowledgments
* Tutors
* Hard work
* ...

