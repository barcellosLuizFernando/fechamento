/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author luiz.barcellos
 */
public class TestSendMessageWhatsapp {

    public TestSendMessageWhatsapp() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void hello() throws NoSuchAlgorithmException, WhatsAppException, IOException {

        String username = "5591XXXXXXXX", password = "/vuL76lOtEFfE5PE5Pw3Z3o6cXs=",
                identity = "mentorid", nickname = "mentor";

        boolean running = true;
        boolean loggedIn = false;
        WhatsApi wa = null;
        try {
            wa = new WhatsApi(username, identity, nickname);

            EventManager eventManager = new ExampleEventManager();
            wa.setEventManager(eventManager);
            MessageProcessor mp = new ExampleMessageProcessor();
            wa.setNewMessageBind(mp);

            if (!wa.connect()) {
                System.out.println("Failed to connect to WhatsApp");
                System.exit(1);
            }

            if (password != null) {
                wa.loginWithPassword(password);
                loggedIn = true;
            }
            ExampleMessagePoller poller = new ExampleMessagePoller(wa);
            poller.start();
            String msg = "send message by java...";

            //send message group  
            wa.sendMessage("5591XXXXXXXX-Mentor", msg);

            System.out.print("$ ");

            poller.setRunning(false);
            System.out.println("Done! Logging out");
            wa.disconnect();
        } catch (Exception e) {
            System.out.println("Caught exception: " + e.getMessage());
            e.printStackTrace();
            if (wa != null) {
                wa.disconnect();
            }
            System.exit(1);
        }
    }
}
