/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import 

/**
 *
 * @author luiz.barcellos
 */
public class WhatsappMSG {
    
    public String sendMessage(String to, String message) throws WhatsAppException {
        return sendMessage(to, message, null);
}

/**
 * Send a text message to the user/group.
 *
 * @param String to The recipient.
 * @param String message The text message.
 * @param String id
 *
 * @return String
 */
public String sendMessage(String to, String message, String id) throws WhatsAppException {
    message = parseMessageForEmojis(message);
    ProtocolNode bodyNode = new ProtocolNode("body", null, null, message.getBytes());
    try {
        return sendMessageNode(to, bodyNode, id);
    } catch (Exception e) {
        throw new WhatsAppException("Failed to send message", e);
    }
}

/**
 * Send node to the servers.
 *
 * @param to The recipient to send.
 * @param node The node that contains the message.
 * @return message id
 * @throws IOException
 * @throws InvalidTokenException
 * @throws InvalidMessageException
 * @throws IncompleteMessageException
 * @throws WhatsAppException
 * @throws JSONException
 * @throws NoSuchAlgorithmException
 */
private String sendMessageNode(String to, ProtocolNode node, String id) throws IOException, IncompleteMessageException, InvalidMessageException, InvalidTokenException, WhatsAppException, JSONException, NoSuchAlgorithmException {
    ProtocolNode serverNode = new ProtocolNode("server", null, null, null);
    List<ProtocolNode> list = new LinkedList<ProtocolNode>();
    list.add(serverNode);
    Map<String, String> xHash = new LinkedHashMap<String, String>();
    xHash.put("xmlns", "jabber:x:event");
    ProtocolNode xNode = new ProtocolNode("x", xHash, list, null);
    Map<String, String> notify = new LinkedHashMap<String, String>();
    notify.put("xmlns", "urn:xmpp:whatsapp");
    notify.put("name", name);
    ProtocolNode notnode = new ProtocolNode("notify", notify, null, null);
    Map<String, String> request = new LinkedHashMap<String, String>();
    request.put("xmlns", "urn:xmpp:receipts");
    ProtocolNode reqnode = new ProtocolNode("request", request, null, null);

    Map<String, String> messageHash = new LinkedHashMap<String, String>();
    messageHash.put("to", getJID(to));
    messageHash.put("type", "chat");
    messageHash.put("id", (id == null ? createMsgId("message") : id));
    messageHash.put("t", time());

    list = new LinkedList<ProtocolNode>();
    list.add(xNode);
    list.add(notnode);
    list.add(reqnode);
    list.add(node);
    ProtocolNode messageNode = new ProtocolNode("message", messageHash, list, null);
    if (lastId == null) {
        lastId = messageHash.get("id");
        sendNode(messageNode);
        //listen for response
        waitForServer(messageHash.get("id"));
    } else {
        outQueue.add(messageNode);
    }
    eventManager().fireSendMessage(
            phoneNumber,
            getJID(to),
            messageHash.get("id"),
            node
    );
    return messageHash.get("id");
}
    
}
