import java.io.IOException;

public class MasterControl {

	static MediatorMasterControl mediatorMasterControl;

	public static void main(String[] args) throws IOException {
		mediatorMasterControl.start();
	}
}
