package ge.vakho.hello_service_eng;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import ge.vakho.springboot.api.HelloWorld;

public class Activator implements BundleActivator {

	ServiceRegistration<HelloWorld> helloServiceRegistration;

	public void start(BundleContext context) throws Exception {
		helloServiceRegistration = context.registerService(HelloWorld.class, () -> "Hello, world!", null);
	}

	public void stop(BundleContext context) throws Exception {
		helloServiceRegistration.unregister();
	}
}