// ChatServer.java
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
    private ArrayList<String> messages;
    private List<ChatClient> clients;
//              constructor
    public ChatServer() {
        this.messages = new ArrayList<>();
        this.clients = new ArrayList<>();
    }
// adding a new client to the server
    public void registerClient(ChatClient client) {
        clients.add(client);
    }

    public void broadcastMessage(String message, ChatClient sender) {
        messages.add(message);
        for (ChatClient client : clients) {
            if (client != sender) {
                client.receiveMessage(message);
            }
        }
    }

    public List<String> getMessageHistory() {
        return new ArrayList<>(messages);
    }
}


class ChatClient {
    private String userId;
    private String userType; // "doctor" or "patient"
    private ChatServer server;

    public ChatClient(String userId, String userType, ChatServer server) {
        this.userId = userId;
        this.userType = userType;
        this.server = server;
        server.registerClient(this);
    }

    public void sendMessage(String message) {
        String message_send = "[" + userType + " " + userId + "]: " + message;
        server.broadcastMessage(message_send, this);
    }

    public void receiveMessage(String message) {
        System.out.println(userId + " received: " + message);
    }
}

// VideoCall.java
 class VideoCall {
    private String meetingLink;
    private String doctorId;
    private String patientId;

    public VideoCall(String doctorId, String patientId, String meetingLink) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.meetingLink=meetingLink;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setMeetingLink(String meetingLink) {
        this.meetingLink = meetingLink;
    }

    public void startCall() {
        System.out.println("Starting video call between doctor " + doctorId + " and patient " + patientId);
        System.out.println("Join meeting at: " + meetingLink);
    }

    public String getMeetingLink() {
        return meetingLink;
    }
}
