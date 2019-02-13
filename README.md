# OSGI Spring Boot Example
## Overview
This is an example project which demonstrates how to integrate Spring Boot application into OSGI environment. 
This example takes after [this](https://github.com/dimmik/osgi-spring-boot-demo) example, but I've added ```BundleContext``` as a property of ```BundleService``` bean class. 
Hence, it is now possible to use ```@Autowired``` annotation and retrieve ```BundleContext``` class dynamically.
## Project Structure
* **osgi-spring-boot** - *Exposes ```HelloService``` API interface. Has REST services to call ```HelloService``` implementers and see the results.*
* **hello-service-eng** - *Implements HelloService provided by our osgi-spring-boot applicaiton.*

![Scheme Image](./Spring%20Boot%20OSGI%20Scheme.png)

## FAQs 
There are no FAQs. If you have questions, just ask :) 