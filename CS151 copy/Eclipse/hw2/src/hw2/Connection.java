package hw2;

/**
Connects a phone to the mail system. The purpose of this
class is to keep track of the state of a connection, since
the phone itself is just a source of individual key presses.
 */
public class Connection
{
	/**
   Construct a Connection object.
   @param s a MailSystem object
   @param p a Telephone object
	 */
	public Connection(MailSystem s, Telephone p)
	{
		system = s;
		phone = p;
		resetConnection();
	}

	/**
   Respond to the user's pressing a key on the phone touchpad
   @param key the phone key pressed by the user
	 */
	public void dial(String key)
	{
		if (state == CONNECTED)
			chooseMenu(key);
		else if(state == LEAVE_MESSAGE)
			pickMailBox(key);
		else if(state == PICK_YOUR_MAILBOX)
			pickYourMailBox(key);
		else if (state == LOGIN)
			login(key);
		else if (state == CHANGE_PASSCODE)
			changePasscode(key);
		else if (state == CHANGE_GREETING)
			changeGreeting(key);
		else if (state == MAILBOX_MENU)
			mailboxMenu(key);
		else if (state == MESSAGE_MENU)
			messageMenu(key);
	}

	/**
   Record voice.
   @param voice voice spoken by the user
	 */
	public void record(String voice)
	{
		if (state == RECORDING || state == CHANGE_GREETING)
			currentRecording += voice;
		else if (state == CONNECTED) {
			phone.speak("Invalid input");
			resetConnection();
		}else if (state == MAILBOX_MENU) 
			phone.speak(MAILBOX_MENU_TEXT);
		 else if (state == MESSAGE_MENU) 
			phone.speak(MESSAGE_MENU_TEXT);
		
	}

	/**
   The user hangs up the phone.
	 */
	public void hangup()
	{
		if (state == RECORDING)
			currentMailbox.addMessage(new Message(currentRecording));
		resetConnection();
	}

	/**
   Reset the connection to the initial state and prompt
   for mailbox number
	 */
	private void resetConnection()
	{
		currentRecording = "";
		accumulatedKeys = "";
		state = CONNECTED;
		phone.speak(INITIAL_PROMPT);
	}

	
	private void chooseMenu(String key) {
		if(key.equals("1")) {
			state = LEAVE_MESSAGE;
			phone.speak(LEAVING_MESSAGE_MENU);
		}else if (key.equals("2")) {
			state = PICK_YOUR_MAILBOX;
			phone.speak(LOGIN_MESSAGE);
		}else
			phone.speak("Invalid entry");
	}
	private void pickYourMailBox(String key) 
	{
		if (key.equals("#"))
		{
			try{
				currentMailbox = system.findMailbox(accumulatedKeys);
			}catch(NumberFormatException x) {
				phone.speak("Invalid entry, try again");
				accumulatedKeys = "";
				return;
			}
			if (currentMailbox != null)
			{
				state = LOGIN;
				phone.speak("Please enter your password followed by #");
				
			}
			else
				phone.speak("Incorrect mailbox number. Try again!");
			accumulatedKeys = "";
		}
		else
			accumulatedKeys += key;
	}
	/**
   Try to connect the user with the specified mailbox.
   @param key the phone key pressed by the user
	 */
	private void pickMailBox(String key)
	{
		if (key.equals("#"))
		{
			try{
				currentMailbox = system.findMailbox(accumulatedKeys);
			}catch(NumberFormatException x) {
				phone.speak("Invalid entry, try again");
				accumulatedKeys = "";
				return;
			}
			if (currentMailbox != null)
			{
				state = RECORDING;
				phone.speak(currentMailbox.getGreeting());
			}
			else
				phone.speak("Incorrect mailbox number. Try again!");
			accumulatedKeys = "";
		}
		else
			accumulatedKeys += key;
	}

	/**
   Try to log in the user.
   @param key the phone key pressed by the user
	 */
	private void login(String key)
	{
		if (key.equals("#"))
		{
			if (currentMailbox.checkPasscode(accumulatedKeys))
			{
				state = MAILBOX_MENU;
				phone.speak(MAILBOX_MENU_TEXT);
			}
			else
				phone.speak("Incorrect passcode. Try again!");
			accumulatedKeys = "";
		}
		else
			accumulatedKeys += key;
	}

	/**
  Change passcode.
  @param key the phone key pressed by the user
	 */
	private void changePasscode(String key)
	{
		if (key.equals("#"))
		{
			currentMailbox.setPasscode(accumulatedKeys);
			state = MAILBOX_MENU;
			phone.speak(MAILBOX_MENU_TEXT);
			accumulatedKeys = "";
		}
		else
			accumulatedKeys += key;
	}

	/**
  Change greeting.
  @param key the phone key pressed by the user
	 */
	private void changeGreeting(String key)
	{
		if (key.equals("#"))
		{
			currentMailbox.setGreeting(currentRecording);
			currentRecording = "";
			state = MAILBOX_MENU;
			phone.speak(MAILBOX_MENU_TEXT);
		}
	}

	/**
  Respond to the user's selection from mailbox menu.
  @param key the phone key pressed by the user
	 */
	private void mailboxMenu(String key)
	{
		if (key.equals("1"))
		{
			state = MESSAGE_MENU;
			phone.speak(MESSAGE_MENU_TEXT);
		}
		else if (key.equals("2"))
		{
			state = CHANGE_PASSCODE;
			phone.speak("Enter new passcode followed by the # key");
		}
		else if (key.equals("3"))
		{
			state = CHANGE_GREETING;
			phone.speak("Record your greeting, then press the # key");
		}else if(key.equals("*"))
			phone.speak(MAILBOX_MENU_TEXT);
		else
			phone.speak(MAILBOX_MENU_TEXT);
	}

	/**
  Respond to the user's selection from message menu.
  @param key the phone key pressed by the user
	 */
	private void messageMenu(String key)
	{
		if (key.equals("1"))
		{
			String output = "";
			Message m = currentMailbox.getCurrentMessage();
			if (m == null) output += "No messages." + "\n";
			else output += m.getText() + "\n";
			output += MESSAGE_MENU_TEXT;
			phone.speak(output);
		}
		else if (key.equals("2"))
		{
			currentMailbox.saveCurrentMessage();
			phone.speak(MESSAGE_MENU_TEXT);
		}
		else if (key.equals("3"))
		{
			currentMailbox.removeCurrentMessage();
			phone.speak(MESSAGE_MENU_TEXT);
		}
		else if (key.equals("4"))
		{
			state = MAILBOX_MENU;
			phone.speak(MAILBOX_MENU_TEXT);
		}else if (key.equals("*"))
			phone.speak(MESSAGE_MENU_TEXT);
		else
			phone.speak(MESSAGE_MENU_TEXT);
	}

	private MailSystem system;
	private Mailbox currentMailbox;
	private String currentRecording;
	private String accumulatedKeys;
	private Telephone phone;
	private int state;

	private static final int DISCONNECTED = 0;
	private static final int CONNECTED = 1;
	private static final int RECORDING = 2;
	private static final int MAILBOX_MENU = 3;
	private static final int MESSAGE_MENU = 4;
	private static final int CHANGE_PASSCODE = 5;
	private static final int CHANGE_GREETING = 6;
	private static final int LEAVE_MESSAGE = 7;
	private static final int LOGIN = 8;
	private static final int PICK_YOUR_MAILBOX = 9;

	private static final String LEAVING_MESSAGE_MENU = 
			"Enter mailbox number followed by #";
	private static final String INITIAL_PROMPT =
			"To leave a message to a mailbox, press (1), to access\n" + 
			"your mailbox, press (2)";
	private static final String LOGIN_MESSAGE =
			"Please enter your mailbox number followed by #";
	private static final String MAILBOX_MENU_TEXT = 
			"Enter 1 to listen to your messages\n"
					+ "Enter 2 to change your passcode\n"
					+ "Enter 3 to change your greeting";
	private static final String MESSAGE_MENU_TEXT = 
			"Enter 1 to listen to the current message\n"
					+ "Enter 2 to save the current message\n"
					+ "Enter 3 to delete the current message\n"
					+ "Enter 4 to return to the main menu";
}