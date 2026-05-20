package semicolon.noStrings.data.repositories;

import semicolon.noStrings.data.models.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageRepositoryImpl implements MessageRepository{

    private List<Message> messages = new ArrayList<>();

    private int idCounter = 1;


    @Override
    public Message save(Message message) {

        if (message.getId() == 0) {
            message.setId(idCounter++);
            messages.add(message);
            return message;
        }

        for (int i = 0; i < messages.size(); i++) {
            if (messages.get(i).getId() == message.getId()) {
                // Swap the old Seeker at this position with the updated one
                messages.set(i, message);
                return message;
            }
        }

        messages.add(message);
        return message;
    }

    @Override
    public Message findById(int id) {
        for (Message message : messages) {
            if (message.getId() == id) {
                return message; // Found — return immediately
            }
        }
        return null;
    }


    @Override
    public List<Message> findAll() {
        return new ArrayList<>(messages);
    }


    @Override
    public void deleteById(int id) {
        messages.removeIf(seeker -> seeker.getId() == id);
    }


    @Override
    public void deleteAll() {
        messages.clear();
    }

    @Override
    public int count() {
        return messages.size();
    }


}
