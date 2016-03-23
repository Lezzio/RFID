package fr.pag.rfid.handler;

import java.io.IOException;
import java.util.ArrayList;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortPacketListener;

import fr.pag.rfid.async.ThreadPool;
import fr.pag.rfid.board.Board;
import fr.pag.rfid.utils.Debugger;

public class BoardHandler implements Runnable {

	private Board holder; // We need to keep a reference to the Board
	private ArrayList<BoardAction> actions = new ArrayList<BoardAction>();

	public BoardHandler(Board holder, ArrayList<BoardAction> actions) {
		this.holder = holder;
		this.actions = actions;
	}

	@Override
	public void run() {
		holder.getSerialPort().addDataListener(new SerialPortListener());
		reset(); //Reset last data
	}

	public void stop() {
		holder.getSerialPort().removeDataListener();
	}

	public void reset() {
		try {
			holder.getSerialPort().getInputStream().skip(holder.getSerialPort().bytesAvailable());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public class SerialPortListener implements SerialPortPacketListener {

		@Override
		public int getListeningEvents() {
			return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
		}

		@Override
		public void serialEvent(SerialPortEvent event) {
			if (event.getEventType() == SerialPort.LISTENING_EVENT_DATA_RECEIVED) {

				if (holder.getSerialPort().bytesAvailable() > 0) {

					final byte[] readBuffer = new byte[holder.getSerialPort().bytesAvailable()];

					try {
						holder.getSerialPort().getInputStream().read(readBuffer);
						String data = new String(readBuffer);
						
						// Perform actions
						for (BoardAction action : actions) {

							// Check role
							if (action.getNeededRole() == holder.getRole()) {

								// Call in another thread if possible to
								// optimize:
								if (action.isAsync()) {
									ThreadPool.executeTask(() -> action.handle(holder, data));
								} else {
									// Else call in current thread
									action.handle(holder, data);
								}

							}

						}

					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			} else
				return;
		}

		@Override
		public int getPacketSize() {
			return 0;
		}

	}

	public void execute(int indication) {
		execute(null, indication);
	}

	public void execute(Class<?> container, int indication) {

		// Perform actions
		for (BoardAction action : actions) {

			// Type match ?
			if (container.getTypeName() == action.getClass().getTypeName()) {

				// Check role
				if (action.getNeededRole() == holder.getRole()) {

					// Call in another thread if possible to optimize:
					if (action.isAsync()) {
						ThreadPool.executeTask(() -> action.execute(holder, indication));
					} else {
						// Else call in current thread
						action.execute(holder, indication);
					}
				}
			}

		}

	}

	@Override
	public void finalize() {
		System.out.println("DEAD");
	}
}
