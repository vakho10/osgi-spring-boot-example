package ge.vakho.springboot.model;

public class JSONBundle {

	private long id;
	private String symbolicName;
	private String version;
	private State state;

	public JSONBundle() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSymbolicName() {
		return symbolicName;
	}

	public void setSymbolicName(String symbolicName) {
		this.symbolicName = symbolicName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public static enum State {
		ACTIVE(32), //
		INSTALLED(2), //
		RESOLVED(4), //
		SIGNERS_ALL(1), //
		SIGNERS_TRUSTED(2), //
		START_ACTIVATION_POLICY(2), //
		START_TRANSIENT(1), //
		STARTING(8), //
		STOP_TRANSIENT(1), //
		STOPPING(16), //
		UNINSTALLED(1);

		int _intValue;

		State(int intValue) {
			this._intValue = intValue;
		}

		public int getIntValue() {
			return _intValue;
		}

		public static State fromIntValue(int value) {
			for (State v : values()) {
				if (v._intValue == value) {
					return v;
				}
			}
			return null;
		}
	}
}