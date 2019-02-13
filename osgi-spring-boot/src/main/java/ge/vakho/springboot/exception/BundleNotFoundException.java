package ge.vakho.springboot.exception;

@SuppressWarnings("serial")
public class BundleNotFoundException extends Exception {

	public BundleNotFoundException(long bundleId) {
		super("No bundle was found for id " + bundleId);
	}

}