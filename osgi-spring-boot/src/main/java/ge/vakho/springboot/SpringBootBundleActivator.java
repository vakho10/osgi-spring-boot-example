package ge.vakho.springboot;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext;
import org.springframework.osgi.io.OsgiBundleResourcePatternResolver;

import ge.vakho.springboot.service.BundleService;

@SpringBootApplication
public class SpringBootBundleActivator implements BundleActivator {

	AnnotationConfigEmbeddedWebApplicationContext appContext;
	
	@Override
	public void start(BundleContext bundleContext) {

		// Set context classloader
		Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());

		// trick to enable scan: get osgi resource pattern resolver
		OsgiBundleResourcePatternResolver resourceResolver = new OsgiBundleResourcePatternResolver(
				bundleContext.getBundle());

		// and provide it to spring application
		appContext = (AnnotationConfigEmbeddedWebApplicationContext) new SpringApplication(resourceResolver,
				SpringBootBundleActivator.class) //
						.run();

		// Register bundle service bean dynamically and inject bundle context as a field...
		appContext.registerBeanDefinition("bundleService", //
				BeanDefinitionBuilder //
						.genericBeanDefinition(BundleService.class) //
						.addPropertyValue("bundleContext", bundleContext) //
						.getBeanDefinition());
	}

	@Override
	public void stop(BundleContext bundleContext) {
		SpringApplication.exit(appContext, () -> 0);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBundleActivator.class);
	}
}
