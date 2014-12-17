import com.tinkerforge.BrickletLinearPoti;
import com.tinkerforge.IPConnection;

public class ExampleCallback {
	private static final String HOST = "localhost";
	private static final int PORT = 4223;
	private static final String UID = "ABC"; // Change to your UID

	// Note: To make the example code cleaner we do not handle exceptions. Exceptions you
	//       might normally want to catch are described in the documentation
	public static void main(String args[]) throws Exception {
		IPConnection ipcon = new IPConnection(); // Create IP connection
		BrickletLinearPoti poti = new BrickletLinearPoti(UID, ipcon); // Create device object

		ipcon.connect(HOST, PORT); // Connect to brickd
		// Don't use device before ipcon is connected

		// Set Period for position callback to 0.05s (50ms)
		// Note: The position callback is only called every second if the 
		//       position has changed since the last call!
		poti.setPositionCallbackPeriod(50);

		// Add and implement position listener (called if position changes)
		poti.addPositionListener(new BrickletLinearPoti.PositionListener() {
			public void position(int position) {
				System.out.println("Position: " + position);
			}
		});

		System.out.println("Press key to exit"); System.in.read();
		ipcon.disconnect();
	}
}
