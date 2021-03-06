import com.tinkerforge.BrickletIO4;
import com.tinkerforge.IPConnection;

public class ExampleInterrupt {
	private static final String HOST = "localhost";
	private static final int PORT = 4223;
	private static final String UID = "ABC"; // Change to your UID
	
	// Note: To make the example code cleaner we do not handle exceptions. Exceptions you
	//       might normally want to catch are described in the documentation
	public static void main(String args[]) throws Exception {
		IPConnection ipcon = new IPConnection(); // Create IP connection
		BrickletIO4 io4 = new BrickletIO4(UID, ipcon); // Create device object

		ipcon.connect(HOST, PORT); // Connect to brickd
		// Don't use device before ipcon is connected

		// Add and implement listener for interrupt (called if pin 0 changes)
		io4.addInterruptListener(new BrickletIO4.InterruptListener() {
			public void interrupt(short interruptMask, short valueMask) {
				System.out.println("Interrupt by: " + Integer.toBinaryString(interruptMask));
				System.out.println("Value: " + Integer.toBinaryString(valueMask));
			}
		});

		// Enable interrupt on pin 0
		io4.setInterrupt((short)(1 << 0));

		System.out.println("Press key to exit"); System.in.read();
		ipcon.disconnect();
	}
}
