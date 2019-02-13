package ge.vakho.springboot.controller;

import java.util.List;

import org.osgi.framework.InvalidSyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ge.vakho.springboot.api.HelloWorld;
import ge.vakho.springboot.service.HelloWorldService;

@RequestMapping("/hello")
@RestController
public class RestHelloWorldController {

	private static final Logger LOG = LoggerFactory.getLogger(RestHelloWorldController.class);

	@Autowired
	private HelloWorldService helloWorldService;

	@GetMapping
	public String[] call() throws InvalidSyntaxException {
		final List<HelloWorld> helloWords = helloWorldService.getAll();
		LOG.info("Got back {} hello service(s).", helloWords.size());
		return helloWords.parallelStream() //
				.map(service -> service.sayHelloWorld()) //
				.toArray(String[]::new);
	}

}