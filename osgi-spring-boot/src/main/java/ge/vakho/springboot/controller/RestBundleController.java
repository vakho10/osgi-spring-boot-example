package ge.vakho.springboot.controller;

import java.util.Arrays;

import org.osgi.framework.Bundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ge.vakho.springboot.exception.BundleNotFoundException;
import ge.vakho.springboot.model.JSONBundle;
import ge.vakho.springboot.service.BundleService;

@RequestMapping("/bundle")
@RestController
public class RestBundleController {

	private static final Logger LOG = LoggerFactory.getLogger(RestBundleController.class.getName());
	
	@Autowired
	private BundleService bundleService;

	@GetMapping
	public JSONBundle[] getAll() {
		Bundle[] bundles = bundleService.getBundleContext().getBundles();
		LOG.info("Got back {} bundles", bundles.length);
		
		// Map Bundle to JSONBundle representation
		return Arrays.stream(bundles) //
				.parallel() //
				.map(this::toJSONBundle) //
				.toArray(JSONBundle[]::new);
	}

	@GetMapping("/{id}")
	public JSONBundle getById(@PathVariable long id) throws BundleNotFoundException {
		final Bundle bundle = bundleService.getBundleContext().getBundle(id);
		if (bundle == null) {
			throw new BundleNotFoundException(id);
		}
		return toJSONBundle(bundle);
	}
	
	private JSONBundle toJSONBundle(Bundle bundle) {
		JSONBundle eapsBundle = new JSONBundle();
		eapsBundle.setId(bundle.getBundleId());
		eapsBundle.setSymbolicName(bundle.getSymbolicName());
		eapsBundle.setVersion(bundle.getVersion().toString());
		eapsBundle.setState(JSONBundle.State.fromIntValue(bundle.getState()));
		return eapsBundle;
	}
}