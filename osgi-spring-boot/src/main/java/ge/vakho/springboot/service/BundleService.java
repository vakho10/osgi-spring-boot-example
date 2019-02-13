package ge.vakho.springboot.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BundleService {

	private static final Logger LOG = LoggerFactory.getLogger(BundleService.class);

	private BundleContext bundleContext;

	/**
	 * Injected automatically
	 * 
	 * @param bundleContext
	 */
	public void setBundleContext(BundleContext bundleContext) {
		this.bundleContext = bundleContext;
	}

	public BundleContext getBundleContext() {
		return bundleContext;
	}

	/**
	 * @return bundles
	 */
	public List<Bundle> getBundles() {
		return Arrays.asList(bundleContext.getBundles());
	}

	/**
	 * @param clazz
	 * @return services which implement clazz interface
	 */
	public <T> List<T> getServicesForClass(Class<T> clazz) {
		try {
			return getServicesForClass(clazz, null);
		} catch (InvalidSyntaxException e) {
			// This error won't occur for null filter!
			LOG.error(e.getMessage(), e);
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * @param clazz
	 * @param filter
	 * @return services which implement clazz interface and satisfy the filter
	 * @throws InvalidSyntaxException
	 */
	public <T> List<T> getServicesForClass(Class<T> clazz, String filter) throws InvalidSyntaxException {
		Collection<ServiceReference<T>> serviceReferences = bundleContext.getServiceReferences(clazz, filter);
		final List<T> services = new ArrayList<>();
		for (ServiceReference<T> reference : serviceReferences) {
			services.add(bundleContext.getService(reference));
		}
		return services;
	}

}