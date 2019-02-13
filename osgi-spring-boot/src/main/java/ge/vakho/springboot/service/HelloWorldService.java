package ge.vakho.springboot.service;

import java.util.List;

import org.osgi.framework.InvalidSyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ge.vakho.springboot.api.HelloWorld;

@Service
public class HelloWorldService {

	@Autowired
	private BundleService bundleService;

	public List<HelloWorld> getAll() throws InvalidSyntaxException {
		return bundleService.getServicesForClass(HelloWorld.class);
	}
	
}